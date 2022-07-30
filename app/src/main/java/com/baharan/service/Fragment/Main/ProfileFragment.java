package com.baharan.service.Fragment.Main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.baharan.service.Activity.MainActivity;
import com.baharan.service.Database.AppDatabase;
import com.baharan.service.Database.User.User;
import com.baharan.service.R;
import com.baharan.service.Util.Const;
import com.baharan.service.Widget.Btn;
import com.baharan.service.Widget.EditTxt;
import com.baharan.service.Widget.Text;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileFragment extends Fragment {

    final static int REQUEST_CODE = 1;

    private User user;

    private FloatingActionButton choosePhoto;
    private NavigationView navigationView;
    private ImageView profile;
    private ImageView headerProfile;

    private ByteArrayOutputStream baos;
    private InputStream stream = null;
    private Bitmap temp;
    private Bitmap bitmap;
    private Btn okButton;

    private EditTxt name;
    private EditTxt lastName;
    private EditTxt insuranceCode;
    private EditTxt email;
    private EditTxt address;
    private EditTxt state;
    private EditTxt city;
    private Text nameHeader;
    private Text codeMelli;
    private Text phone;

    private String nameString;
    private String lastNameString;
    private String insuranceCodeString;
    private String emailString;
    private String addressString;
    private String stateString;
    private String cityString;

    private boolean profileChange;
    private boolean show;
    private boolean success;

    private static final Pattern RTL_CHARACTERS =
            Pattern.compile("[\u0600-\u06FF\u0750-\u077F\u0590-\u05FF\uFE70-\uFEFF]");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.profile_fragment, container, false);

        user = AppDatabase.getAppDatabase(getContext()).userDao().findUserByCodeMelli(Const.Code);

        //TODO init
        Const.number = 0;
        bitmap = null;
        profileChange = false;
        show = false;
        baos = new ByteArrayOutputStream();
        ((MainActivity) getActivity()).setTitle(getResources().getString(R.string.Title6));
        ((MainActivity) getActivity()).showMenu();

        //TODO ID
        navigationView = getActivity().findViewById(R.id.navigate);
        choosePhoto = view.findViewById(R.id.choose);
        profile = view.findViewById(R.id.profile);
        codeMelli = view.findViewById(R.id.userCode);
        phone = view.findViewById(R.id.userPhone);
        name = view.findViewById(R.id.userName);
        lastName = view.findViewById(R.id.userLastName);
        insuranceCode = view.findViewById(R.id.userInsuranceCode);
        email = view.findViewById(R.id.userEmail);
        state = view.findViewById(R.id.state);
        city = view.findViewById(R.id.city);
        address = view.findViewById(R.id.userAddress);
        okButton = view.findViewById(R.id.okButton);

        final View headerLayout = navigationView.getHeaderView(0);
        headerProfile = headerLayout.findViewById(R.id.Imgheader);
        nameHeader = headerLayout.findViewById(R.id.name);

        //TODO set user's information
        codeMelli.setText(user.getCodeMelli());
        phone.setText(user.getPhone());
        if (user.getFirstName() != null) {
            name.setText(user.getFirstName());
        }
        if (user.getLastName() != null) {
            lastName.setText(user.getLastName());
        }
        if (user.getInsuranceCode() != null) {
            insuranceCode.setText(user.getInsuranceCode());
        }
        if (user.getEmail() != null) {
            email.setText(user.getEmail());
        }
        if (user.getState() != null) {
            state.setText(user.getState());
        }
        if (user.getCity() != null) {
            city.setText(user.getCity());
        }
        if (user.getAddress() != null) {
            address.setText(user.getAddress());
        }

        //TODO if profile is available -> set Image
        if (((MainActivity) getActivity()).isProfileBoolean()) {
            Glide.with(view).load(((MainActivity) getActivity()).getBitmap()).into(profile);
        }

        //TODO select profile
        choosePhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        //TODO edit information
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameString = name.getText().toString();
                lastNameString = lastName.getText().toString();
                insuranceCodeString = insuranceCode.getText().toString();
                emailString = email.getText().toString();
                stateString = state.getText().toString();
                cityString = city.getText().toString();
                addressString = address.getText().toString();
                success = true;
                Matcher matcher = RTL_CHARACTERS.matcher(nameString);
                Matcher matcher2 = RTL_CHARACTERS.matcher(lastNameString);

                //check name
                if (nameString.equals("")) {
                    name.setError(getResources().getString(R.string.nameProfile));
                    success = false;
                } else if (!matcher.find()) {
                    name.setError(getResources().getString(R.string.nameProfile1));
                    success = false;
                }
                if (lastNameString.equals("")) {
                    lastName.setError(getResources().getString(R.string.nameProfile2));
                    success = false;
                } else if (!matcher2.find()) {
                    name.setError(getResources().getString(R.string.nameProfile3));
                    success = false;
                }

                //check insuranceCode
                if (insuranceCodeString.equals("")) {
                    insuranceCode.setError(getResources().getString(R.string.insuranceCode));
                    success = false;
                } else if (insuranceCodeString.length() < 8) {
                    insuranceCode.setError(getResources().getString(R.string.insuranceCode1));
                    success = false;
                }

                //check email
                if (emailString.equals("")) {
                    email.setError(getResources().getString(R.string.email));
                    success = false;
                } else if (!emailIsValid(emailString)) {
                    email.setError(getResources().getString(R.string.email1));
                    success = false;
                }

                //check State
                if (stateString.equals("")) {
                } else {
                    AppDatabase.getAppDatabase(getContext()).userDao().updateState(Const.Code, stateString);
                }

                //check City
                if (cityString.equals("")) {
                } else {
                    AppDatabase.getAppDatabase(getContext()).userDao().updateCity(Const.Code, cityString);
                }

                //check address
                if (addressString.equals("")) {
                } else {
                    AppDatabase.getAppDatabase(getContext()).userDao().updateAddress(Const.Code, addressString);
                }

                if (success) {
                    AppDatabase.getAppDatabase(getContext()).userDao().updateFirstName(Const.Code, nameString);
                    AppDatabase.getAppDatabase(getContext()).userDao().updateLastName(Const.Code, lastNameString);
                    AppDatabase.getAppDatabase(getContext()).userDao().updateInsuranceCode(Const.Code, insuranceCodeString);
                    AppDatabase.getAppDatabase(getContext()).userDao().updateEmail(Const.Code, emailString);

                    //set Profile in drawer
                    RequestOptions glideOptions2 = new RequestOptions();
                    glideOptions2.transform(new CenterCrop(), new RoundedCorners(500));
                    if (bitmap != null) {
                        if (bitmap != null) {
                            ((MainActivity) getActivity()).setBitmap(bitmap);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            ((MainActivity) getActivity()).setProfileBoolean(true);
                            Glide.with(headerLayout).load(bitmap).apply(glideOptions2).into(headerProfile);
                            byte[] imageInByte = baos.toByteArray();
                            AppDatabase.getAppDatabase(getContext()).userDao().updateProfile(Const.Code, imageInByte);
                        }
                    }

                    nameHeader.setText(nameString + " " + lastNameString);
                    Toast.makeText(getContext(), getResources().getString(R.string.change), Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag, ((MainActivity) getActivity()).getMainFragment()).commit();
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK)
            try {
                if (bitmap != null) {
                    bitmap.recycle();
                }
                stream = getActivity().getContentResolver().openInputStream(data.getData());
                temp = BitmapFactory.decodeStream(stream);
                int nh = (int) (temp.getHeight() * (1024.0 / temp.getWidth()));
                bitmap = Bitmap.createScaledBitmap(temp, 1024, nh, true);
                profileChange = true;

                RequestOptions glideOptions = new RequestOptions();
                glideOptions.transform(new CenterCrop());
                Glide.with(this).load(bitmap).apply(glideOptions).into(profile);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (stream != null)
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
    }

    public static boolean emailIsValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

}
