package com.baharan.service.Fragment.Main;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.baharan.service.Activity.MainActivity;
import com.baharan.service.Database.AppDatabase;
import com.baharan.service.Database.Family.Family;
import com.baharan.service.R;
import com.baharan.service.Util.Const;
import com.baharan.service.Widget.Btn;
import com.baharan.service.Widget.EditTxt;
import com.tapadoo.alerter.Alerter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FamilyFragment extends Fragment implements View.OnTouchListener {

    private ImageButton father;
    private ImageButton mother;
    private ImageButton child;
    private ImageButton other;
    private ImageButton brother;
    private ImageButton sister;
    private Btn addButton;

    private EditTxt name;
    private EditTxt lastName;
    private EditTxt code;
    private EditTxt insurance;

    private String nameString;
    private String lastNameString;
    private String relative;
    private String codeString;
    private String insuranceString;
    private ArrayList<ImageButton> buttons;

    private boolean success;
    private static final Pattern RTL_CHARACTERS =
            Pattern.compile("[\u0600-\u06FF\u0750-\u077F\u0590-\u05FF\uFE70-\uFEFF]");


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.family_fragment, container, false);
        final Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "isans.ttf");

       /* AppDatabase.getAppDatabase(getActivity()).familyDao().deleteAllFamilies();
        Toast.makeText(getContext(), AppDatabase.getAppDatabase(getActivity()).familyDao().getAllFamilies().size() + ""
                , Toast.LENGTH_SHORT).show();
*/
        //TODO init
        Const.number = 0;
        ((MainActivity) getActivity()).setTitle(getResources().getString(R.string.Title7));
        ((MainActivity) getActivity()).showMenu();
        relative = null;
        nameString = null;
        codeString = null;
        insuranceString = null;

        //TODO ID
        addButton = view.findViewById(R.id.familyButton);
        father = view.findViewById(R.id.familyButton1);
        father.setOnTouchListener(this);
        mother = view.findViewById(R.id.familyButton2);
        mother.setOnTouchListener(this);
        child = view.findViewById(R.id.familyButton3);
        child.setOnTouchListener(this);
        other = view.findViewById(R.id.familyButton4);
        other.setOnTouchListener(this);
        brother = view.findViewById(R.id.familyButton5);
        brother.setOnTouchListener(this);
        sister = view.findViewById(R.id.familyButton6);
        sister.setOnTouchListener(this);

        name = view.findViewById(R.id.nameEditText);
        lastName = view.findViewById(R.id.lastNameEditText);
        code = view.findViewById(R.id.codeMelliEditText);
        insurance = view.findViewById(R.id.insuranceEditText);

        buttons = new ArrayList<>();
        buttons.add(father);
        buttons.add(mother);
        buttons.add(child);
        buttons.add(other);
        buttons.add(brother);
        buttons.add(sister);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameString = name.getText().toString();
                lastNameString = lastName.getText().toString();
                codeString = code.getText().toString();
                insuranceString = insurance.getText().toString();
                success = true;
                String relativeWith = Const.Code;
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
                    lastName.setError(getResources().getString(R.string.nameProfile3));
                    success = false;
                }

                //check insuranceCode
                if (insuranceString.equals("")) {
                    insurance.setError(getResources().getString(R.string.insuranceCode));
                    success = false;
                } else if (insuranceString.length() < 8) {
                    insurance.setError(getResources().getString(R.string.insuranceCode1));
                    success = false;
                } else if (AppDatabase.getAppDatabase(getActivity()).familyDao()
                        .findFamilyByInsuranceCode(insuranceString) != null) {
                    insurance.setError(getResources().getString(R.string.InsuranceCodeFamilyError));
                    success = false;
                }

                //check codeMelli
                if (codeString.equals("")) {
                    code.setError(getResources().getString(R.string.register4));
                    success = false;
                } else if (codeString.length() < 10) {
                    code.setError(getResources().getString(R.string.register5));
                    success = false;
                } else if (!Const.IsValidCode(codeString)) {
                    code.setError(getResources().getString(R.string.register9));
                    success = false;
                } else if (AppDatabase.getAppDatabase(getActivity()).familyDao()
                        .findFamilyByCodeMelli(codeString) != null) {
                    code.setError(getResources().getString(R.string.codeFamilyError));
                    success = false;
                }

                //check relative
                if (relative == null) {
                    Alerter.create(((MainActivity) getActivity()))
                            .setText(R.string.familyError)
                            .setDismissable(true)
                            .setTextTypeface(typeface)
                            .setTextAppearance(14)
                            .setBackgroundColorRes(R.color.pink)
                            .setIcon(R.drawable.error)
                            .show();
                    success = false;
                }

                if (success) {
                    Family family = new Family(nameString, lastNameString, codeString, insuranceString, relative, relativeWith);
                    AppDatabase.getAppDatabase(getActivity()).familyDao().insertFamily(family);
                    Toast.makeText(getContext(), getResources().getString(R.string.addFamily), Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag, ((MainActivity) getActivity()).getMainFragment()).commit();
                }
            }
        });

        return view;
    }

    public void setBackgroundButton(ImageButton btn, ArrayList<ImageButton> btns) {
        btn.setPressed(true);
        btns.remove(btn);
        for (ImageButton button : btns) {
            button.setPressed(false);
        }
        btns.add(btn);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.familyButton1:
                setBackgroundButton(father, buttons);
                relative = "پدر";
                break;
            case R.id.familyButton2:
                setBackgroundButton(mother, buttons);
                relative = "مادر";
                break;
            case R.id.familyButton3:
                setBackgroundButton(child, buttons);
                relative = "فرزند";
                break;
            case R.id.familyButton4:
                setBackgroundButton(other, buttons);
                relative = "سایر";
                break;
            case R.id.familyButton5:
                setBackgroundButton(brother, buttons);
                relative = "برادر";
                break;
            case R.id.familyButton6:
                setBackgroundButton(sister, buttons);
                relative = "خواهر";
                break;
        }
        return true;
    }

}
