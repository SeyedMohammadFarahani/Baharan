package com.baharan.service.Activity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.alirezaafkar.toolbar.RtlToolbar;
import com.baharan.service.Adapter.MyAdapter;
import com.baharan.service.App;
import com.baharan.service.Database.AppDatabase;
import com.baharan.service.Database.User.User;
import com.baharan.service.Fragment.Main.DoctorsFragment;
import com.baharan.service.Fragment.Main.DocumentFragment;
import com.baharan.service.Fragment.Main.FamilyFragment;
import com.baharan.service.Fragment.Main.FamilyListFragment;
import com.baharan.service.Fragment.Main.MainFragment;
import com.baharan.service.Fragment.Main.ProfileFragment;
import com.baharan.service.Fragment.Main.TempFragment;
import com.baharan.service.R;
import com.baharan.service.Util.Const;
import com.baharan.service.Util.RtlActionBarDrawerToggle;
import com.baharan.service.Widget.Btn;
import com.baharan.service.Widget.Text;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class MainActivity extends AppCompatActivity {

    //User
    private User user;
    private String phone;
    private String firstName;
    private String lastName;
    private byte[] byteArray;

    //Drawer
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private RtlToolbar toolbar;
    private ImageView profileImage;
    private Bitmap bitmap;
    private ListView listView;
    private View headerLayout;
    private Text nameHeader;
    private Text phoneHeader;
    private Text title;
    private boolean profileBoolean;


    //Fragment
    private ProfileFragment profileFragment;
    private MainFragment mainFragment;
    private DoctorsFragment doctorsFragment;
    private FamilyFragment familyFragment;
    private DocumentFragment documentFragment;
    private FamilyListFragment familyListFragment;
    private TempFragment tempFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO FullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Const.number = 1;
        user = AppDatabase.getAppDatabase(getApplicationContext()).userDao()
                .findUserByCodeMelli(Const.Code);


        //TODO ID
        drawerLayout = findViewById(R.id.draw);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigate);
        listView = findViewById(R.id.list);
        title = findViewById(R.id.title);

        headerLayout = navigationView.getHeaderView(0);
        nameHeader = headerLayout.findViewById(R.id.name);
        phoneHeader = headerLayout.findViewById(R.id.phoneHeader);
        profileImage = headerLayout.findViewById(R.id.Imgheader);

        profileFragment = new ProfileFragment();
        mainFragment = new MainFragment();
        doctorsFragment = new DoctorsFragment();
        familyFragment = new FamilyFragment();
        documentFragment = new DocumentFragment();
        familyListFragment = new FamilyListFragment();
        tempFragment = new TempFragment();

        //TODO replace MainFragment
        getSupportFragmentManager().beginTransaction().replace(R.id.frag, mainFragment).commit();


        // DrawerList
        final MyAdapter myAdapter = new MyAdapter(this, Const.getList());
        listView.setAdapter(myAdapter);
        RtlActionBarDrawerToggle actionBarDrawerToggle = new RtlActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_menu));
        toolbar.setNavigationIcon(R.drawable.ic_menu);

        //TODO Back item
        toolbar.setOnMenuItemClickListener(new RtlToolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.backItem) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag, mainFragment).commit();
                    setTitle(getResources().getString(R.string.mainTitle));
                    doNotShowMenu();
                }
                return false;
            }
        });


        //TODO Drawer List
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                    changeFrag(mainFragment);
                if (position == 1)
                    changeFrag(profileFragment);
                if (position == 2)
                    changeFrag(familyListFragment);
                if (position == 3)
                    exit();
            }
        });

        if (AppDatabase.getAppDatabase(MainActivity.this).userDao().findUserByCodeMelli(Const.Code).getImage() != null) {
            byteArray = AppDatabase.getAppDatabase(MainActivity.this).userDao().findUserByCodeMelli(Const.Code).getImage();
            if (byteArray != null) {
                setProfileBoolean(true);
                RequestOptions glideOptions2 = new RequestOptions();
                glideOptions2.transform(new CenterCrop(), new RoundedCorners(500));
                bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                setBitmap(bitmap);
                Glide.with(headerLayout).load(bitmap).apply(glideOptions2).into(profileImage);
            }
        }


        //TODO headerImage
        navigationView = findViewById(R.id.navigate);
        navigationView.setItemIconTintList(null);

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFrag(profileFragment);
            }
        });
    }

    @Override
    public void finish() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        drawerLayout.closeDrawers();

        Const.number++;

        if (Const.number == 2) {
            Const.number = 0;
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.exit_dialog);
            dialog.setCancelable(true);
            Btn cancel = dialog.findViewById(R.id.returnMain);
            Btn delete = dialog.findViewById(R.id.exit);

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    Const.number = 1;
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    MainActivity.this.finishAffinity();

                }
            });
            dialog.show();

        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.frag, mainFragment).commit();
            setTitle(getResources().getString(R.string.mainTitle));
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateDrawer();

    }

    public boolean showMenu() {
        MenuItem back = toolbar.getMenu().findItem(R.id.backItem);
        back.setVisible(true);
        return true;
    }

    public boolean doNotShowMenu() {
        MenuItem back = toolbar.getMenu().findItem(R.id.backItem);
        back.setVisible(false);
        return true;
    }

    public void setProfileBoolean(boolean profileBoolean) {
        this.profileBoolean = profileBoolean;
    }

    public boolean isProfileBoolean() {
        return profileBoolean;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void changeFrag(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.push_left_enter, R.anim.push_left_exit).replace(R.id.frag, fragment).commit();
        drawerLayout.closeDrawers();
    }

    public MainFragment getMainFragment() {
        return mainFragment;
    }

    public ProfileFragment getProfileFragment() {
        return profileFragment;
    }

    public FamilyFragment getFamilyFragment() {
        return familyFragment;
    }

    public TempFragment getTempFragment() {
        return tempFragment;
    }

    public DoctorsFragment getDoctorsFragment() {
        return doctorsFragment;
    }

    public DocumentFragment getDocumentFragment() {
        return documentFragment;
    }

    public FamilyListFragment getFamilyListFragment() {
        return familyListFragment;
    }

    public void setTitle(String string) {
        title.setText(string);
    }

    public void exit() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.exit_account_dialog);
        Btn cancel = dialog.findViewById(R.id.cancelButton);
        Btn delete = dialog.findViewById(R.id.exitButton);
        dialog.setCancelable(true);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Const.number = 1;
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                App.getMyApplication().saveSharedPreferences(Const.App, Const.Flag, "0", getApplicationContext());
                AppDatabase.getAppDatabase(getApplicationContext()).currentDao().updateCode("Code");
                MainActivity.this.finishAffinity();
            }
        });
        dialog.show();
    }

    public void updateDrawer() {

        phone = user.getPhone();
        firstName = user.getFirstName();
        lastName = user.getLastName();

        phoneHeader.setText(phone);

        if (firstName == null)
            firstName = "";
        if (lastName == null)
            lastName = "";

        nameHeader = headerLayout.findViewById(R.id.name);
        nameHeader.setText(firstName + " " + lastName);
    }

}
