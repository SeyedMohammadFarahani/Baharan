package com.baharan.service.Fragment.Main;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.baharan.service.Activity.MainActivity;
import com.baharan.service.Adapter.ReserveAdapter;
import com.baharan.service.Database.AppDatabase;
import com.baharan.service.Database.Family.Family;
import com.baharan.service.Database.Reserve.Reserve;
import com.baharan.service.Database.User.User;
import com.baharan.service.R;
import com.baharan.service.Util.Const;
import com.baharan.service.Widget.Btn;
import com.baharan.service.Widget.Text;
import com.isapanah.awesomespinner.AwesomeSpinner;
import com.tapadoo.alerter.Alerter;

import java.util.ArrayList;
import java.util.List;

public class DoctorsFragment extends Fragment {

    private User user;
    private List<Family> selectFamilies;
    private ArrayList<String> nameOfFamilies;
    private ArrayList<String> doctorList = new ArrayList<>();
    private ArrayList<String> tempList = new ArrayList<>();

    private List<Reserve> selectReserves = new ArrayList<>();
    private RecyclerView reserveList;
    private ReserveAdapter reserveAdapter;

    private ConstraintLayout transferPlaceholder;
    private Text goToProfile;

    private AwesomeSpinner state;
    private AwesomeSpinner city;
    private AwesomeSpinner hospital;
    private AwesomeSpinner field;
    private AwesomeSpinner doctor;

    private ImageButton removeDoctor;

    private String stateString;
    private String cityString;
    private String hospitalString;
    private String fieldString;
    private String doctorString;
    private Btn search;

    private boolean success;
    private boolean successSearch;
    private boolean searchWithField;
    private boolean searchWithDoctor;
    private boolean searchWithFieldAndDoctor;
    private boolean myReserveFlag;
    private boolean otherReserveFlag;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.doctors_fragment, container, false);
        final Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "isans.ttf");

        //TODO init
        Const.number = 0;
        ((MainActivity) getActivity()).setTitle(getResources().getString(R.string.Title5));
        ((MainActivity) getActivity()).showMenu();

        user = AppDatabase.getAppDatabase(getContext()).userDao().findUserByCodeMelli(Const.Code);

        reserveAdapter = new ReserveAdapter(getActivity(), (ArrayList<Reserve>) selectReserves);

        selectFamilies = new ArrayList<>();
        nameOfFamilies = new ArrayList<>();
        selectFamilies = AppDatabase.getAppDatabase(getContext()).familyDao().getselectFamilies(Const.Code);
        for (Family family : selectFamilies) {
            nameOfFamilies.add(family.getFirstName() + " " + family.getLastName());
        }

        //TODO id
        state = view.findViewById(R.id.stateSpinner);
        city = view.findViewById(R.id.citySpinner);
        hospital = view.findViewById(R.id.hospitalSpinner);
        field = view.findViewById(R.id.fieldSpinner);
        doctor = view.findViewById(R.id.doctorSpinner);
        search = view.findViewById(R.id.searchButton);
        transferPlaceholder = view.findViewById(R.id.transferPlaceholder);
        goToProfile = view.findViewById(R.id.goTo);
        removeDoctor = view.findViewById(R.id.removeDoctor);


        //TODO show placeHolder
        if (user.getFirstName() == null || user.getInsuranceCode() == null || user.getEmail() == null) {
            transferPlaceholder.setVisibility(View.VISIBLE);
        }

        //TODO go to Profile
        goToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeFrag(((MainActivity) getActivity()).getProfileFragment());
            }
        });

        //TODO init Adapters
        final ArrayAdapter<String> azarbayjan_sharghi = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Azarbayjan_Sharghi)) {

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                Typeface externalFont = Typeface.createFromAsset(getResources().getAssets(), "isans.ttf");
                ((TextView) v).setTypeface(externalFont);

                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);

                Typeface externalFont = Typeface.createFromAsset(getResources().getAssets(), "isans.ttf");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }
        };
        final ArrayAdapter<String> azarbayjan_gharbi = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Azarbayjan_Gharbi)) {

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                Typeface externalFont = Typeface.createFromAsset(getResources().getAssets(), "isans.ttf");
                ((TextView) v).setTypeface(externalFont);

                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);

                Typeface externalFont = Typeface.createFromAsset(getResources().getAssets(), "isans.ttf");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }
        };
        final ArrayAdapter<String> ardebil = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Ardebil)) {

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                Typeface externalFont = Typeface.createFromAsset(getResources().getAssets(), "isans.ttf");
                ((TextView) v).setTypeface(externalFont);

                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);

                Typeface externalFont = Typeface.createFromAsset(getResources().getAssets(), "isans.ttf");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }
        };
        final ArrayAdapter<String> esfehan = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Esfehan)) {

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                Typeface externalFont = Typeface.createFromAsset(getResources().getAssets(), "isans.ttf");
                ((TextView) v).setTypeface(externalFont);

                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);

                Typeface externalFont = Typeface.createFromAsset(getResources().getAssets(), "isans.ttf");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }
        };
        final ArrayAdapter<String> tehran = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Tehran)) {

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                Typeface externalFont = Typeface.createFromAsset(getResources().getAssets(), "isans.ttf");
                ((TextView) v).setTypeface(externalFont);

                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);

                Typeface externalFont = Typeface.createFromAsset(getResources().getAssets(), "isans.ttf");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }
        };
        final ArrayAdapter<String> khorasan = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Khorasan)) {

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                Typeface externalFont = Typeface.createFromAsset(getResources().getAssets(), "isans.ttf");
                ((TextView) v).setTypeface(externalFont);

                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);

                Typeface externalFont = Typeface.createFromAsset(getResources().getAssets(), "isans.ttf");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }
        };
        final ArrayAdapter<String> mazandaran = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Mazandaran)) {

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                Typeface externalFont = Typeface.createFromAsset(getResources().getAssets(), "isans.ttf");
                ((TextView) v).setTypeface(externalFont);

                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);

                Typeface externalFont = Typeface.createFromAsset(getResources().getAssets(), "isans.ttf");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }
        };


        //TODO State list
        final ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.States)) {

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                Typeface externalFont = Typeface.createFromAsset(getResources().getAssets(), "isans.ttf");
                ((TextView) v).setTypeface(externalFont);

                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);

                Typeface externalFont = Typeface.createFromAsset(getResources().getAssets(), "isans.ttf");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }
        };
        state.setAdapter(stateAdapter);
        state.setHintTextColor(getResources().getColor(R.color.red));
        state.setOnSpinnerItemClickListener(new AwesomeSpinner.onSpinnerItemClickListener<String>() {
            @Override
            public void onItemSelected(int position, String itemAtPosition) {
                if (position == 0) {
                    city.setAdapter(azarbayjan_sharghi);
                }
                if (position == 1) {
                    city.setAdapter(azarbayjan_gharbi);
                }
                if (position == 2) {
                    city.setAdapter(ardebil);
                }
                if (position == 3) {
                    city.setAdapter(esfehan);
                }
                if (position == 4) {
                    city.setAdapter(tehran);
                }
                if (position == 5) {
                    city.setAdapter(khorasan);
                }
                if (position == 6) {
                    city.setAdapter(mazandaran);
                }
            }
        });

        //TODO City list
        city.setHintTextColor(getResources().getColor(R.color.red));
        city.setOnSpinnerItemClickListener(new AwesomeSpinner.onSpinnerItemClickListener<String>() {
            @Override
            public void onItemSelected(int position, String itemAtPosition) {
                if (position == 0) {
                }
            }
        });

        //TODO Hospital list
        final ArrayAdapter<String> hospitalAdapter = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Hospitals)) {

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                Typeface externalFont = Typeface.createFromAsset(getResources().getAssets(), "isans.ttf");
                ((TextView) v).setTypeface(externalFont);

                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);

                Typeface externalFont = Typeface.createFromAsset(getResources().getAssets(), "isans.ttf");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }
        };
        hospital.setAdapter(hospitalAdapter);
        hospital.setHintTextColor(getResources().getColor(R.color.red));
        hospital.setOnSpinnerItemClickListener(new AwesomeSpinner.onSpinnerItemClickListener<String>() {
            @Override
            public void onItemSelected(int position, String itemAtPosition) {

                List<Reserve> temp = AppDatabase.getAppDatabase(getContext()).reserveDao().getHospitalReserves(itemAtPosition);
                doctorList.removeAll(doctorList);
                for (Reserve reserve : temp) {
                    if (!doctorList.contains(reserve.getDoctorName()))
                        doctorList.add(reserve.getDoctorName());
                }
                final ArrayAdapter<String> doctorAdapter = new ArrayAdapter<String>
                        (getContext(), android.R.layout.simple_spinner_item, doctorList) {

                    public View getView(int position, View convertView, ViewGroup parent) {
                        View v = super.getView(position, convertView, parent);

                        Typeface externalFont = Typeface.createFromAsset(getResources().getAssets(), "isans.ttf");
                        ((TextView) v).setTypeface(externalFont);

                        return v;
                    }

                    public View getDropDownView(int position, View convertView, ViewGroup parent) {
                        View v = super.getDropDownView(position, convertView, parent);

                        Typeface externalFont = Typeface.createFromAsset(getResources().getAssets(), "isans.ttf");
                        ((TextView) v).setTypeface(externalFont);
                        return v;
                    }
                };
                doctor.setAdapter(doctorAdapter);
            }
        });

        //TODO Field list
        final ArrayAdapter<String> fieldAdapter = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Fields)) {

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                Typeface externalFont = Typeface.createFromAsset(getResources().getAssets(), "isans.ttf");
                ((TextView) v).setTypeface(externalFont);

                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);

                Typeface externalFont = Typeface.createFromAsset(getResources().getAssets(), "isans.ttf");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }
        };
        field.setAdapter(fieldAdapter);
        field.setOnSpinnerItemClickListener(new AwesomeSpinner.onSpinnerItemClickListener<String>() {
            @Override
            public void onItemSelected(int position, String itemAtPosition) {
                List<Reserve> temp = AppDatabase.getAppDatabase(getContext()).reserveDao().getFieldReserves(hospital.getSelectedItem(), itemAtPosition);
                doctorList.removeAll(doctorList);
                for (Reserve reserve : temp) {
                    if (!doctorList.contains(reserve.getDoctorName()))
                        doctorList.add(reserve.getDoctorName());
                }
                final ArrayAdapter<String> doctorAdapter = new ArrayAdapter<String>
                        (getContext(), android.R.layout.simple_spinner_item, doctorList) {

                    public View getView(int position, View convertView, ViewGroup parent) {
                        View v = super.getView(position, convertView, parent);

                        Typeface externalFont = Typeface.createFromAsset(getResources().getAssets(), "isans.ttf");
                        ((TextView) v).setTypeface(externalFont);

                        return v;
                    }

                    public View getDropDownView(int position, View convertView, ViewGroup parent) {
                        View v = super.getDropDownView(position, convertView, parent);

                        Typeface externalFont = Typeface.createFromAsset(getResources().getAssets(), "isans.ttf");
                        ((TextView) v).setTypeface(externalFont);
                        return v;
                    }
                };
                doctor.setAdapter(doctorAdapter);
            }
        });

        //TODO Doctor list
        doctor.setOnSpinnerItemClickListener(new AwesomeSpinner.onSpinnerItemClickListener<String>() {
            @Override
            public void onItemSelected(int position, String itemAtPosition) {
                if (position == 0) {
                }
            }
        });

        removeDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctor.clearSelection();
                doctor.setSelected(false);
            }
        });

        //TODO  Search Result
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stateString = state.getSelectedItem();
                cityString = city.getSelectedItem();
                hospitalString = hospital.getSelectedItem();
                fieldString = field.getSelectedItem();
                if (doctor.isSelected()) {
                    doctorString = doctor.getSelectedItem();
                }
                successSearch = true;
                searchWithField = false;
                searchWithDoctor = false;
                searchWithFieldAndDoctor = false;

                //check State
                if (!state.isSelected()) {
                    state.setSpinnerHint(getResources().getString(R.string.stateError));
                    successSearch = false;
                }

                //check City
                if (!city.isSelected()) {
                    city.setSpinnerHint(getResources().getString(R.string.cityError));
                    successSearch = false;
                }

                //check Hospital
                if (!hospital.isSelected()) {
                    hospital.setSpinnerHint(getResources().getString(R.string.hospitalError));
                    successSearch = false;
                }

                //check Field & Doctor
                if ((!field.isSelected()) && (!doctor.isSelected())) {
                    successSearch = false;
                    Alerter.create(getActivity())
                            .setText(R.string.fieldAndDoctorError)
                            .setDismissable(true)
                            .setTextTypeface(typeface)
                            .setTextAppearance(14)
                            .setBackgroundColorRes(R.color.pink)
                            .setIcon(R.drawable.error)
                            .show();
                }

                if ((field.isSelected()) && (!doctor.isSelected())) {
                    searchWithField = true;
                }

                if ((!field.isSelected() && doctor.isSelected())) {
                    searchWithDoctor = true;
                }
                if ((field.isSelected() && doctor.isSelected())) {
                    searchWithFieldAndDoctor = true;
                }

                if (successSearch) {

                    if (searchWithDoctor) {
                        selectReserves = AppDatabase.getAppDatabase(getContext()).reserveDao().getDoctorReserves(hospitalString, doctorString);
                        reserveAdapter.setArray(selectReserves);
                    } else if (searchWithField) {
                        selectReserves = AppDatabase.getAppDatabase(getContext()).reserveDao().getFieldReserves(hospitalString, fieldString);
                        reserveAdapter.setArray(selectReserves);
                    } else if (searchWithFieldAndDoctor) {
                        selectReserves = AppDatabase.getAppDatabase(getContext()).reserveDao().getFieldAndDoctorReserves(hospitalString, fieldString, doctorString);
                        reserveAdapter.setArray(selectReserves);
                    }

                    if (selectReserves.size() == 0) {
                        Toast.makeText(getContext(), "موردی یافت نشد", Toast.LENGTH_SHORT).show();
                    } else {

                        //TODO search result dialog

                        //ID
                        final Dialog dialog = new Dialog(getContext());
                        dialog.setContentView(R.layout.search_result_dialog);
                        final AppCompatRadioButton myReserve = dialog.findViewById(R.id.myReserve);
                        final AppCompatRadioButton otherReserve = dialog.findViewById(R.id.otherReserve);
                        final AwesomeSpinner familySpinner = dialog.findViewById(R.id.familySpinner);
                        final Btn confirmed = dialog.findViewById(R.id.confirmedReserve);
                        final Btn cancel = dialog.findViewById(R.id.cancelReserve);

                        //reserveList
                        reserveList = dialog.findViewById(R.id.reserveList);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        reserveList.setLayoutManager(layoutManager);
                        reserveList.setAdapter(reserveAdapter);

                        final ArrayAdapter<String> nameAdapter = new ArrayAdapter<String>
                                (getContext(), android.R.layout.simple_spinner_item, nameOfFamilies);
                        nameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        familySpinner.setAdapter(nameAdapter);
                        familySpinner.setOnSpinnerItemClickListener(new AwesomeSpinner.onSpinnerItemClickListener<String>() {
                            @Override
                            public void onItemSelected(int position, String itemAtPosition) {
                                if (position == 0) {
                                }
                            }
                        });

                        otherReserve.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                familySpinner.setVisibility(View.VISIBLE);
                                myReserve.setChecked(false);
                                otherReserveFlag = true;
                                myReserveFlag = false;

                                if (!otherReserve.isChecked()) {
                                    familySpinner.setVisibility(View.GONE);
                                }
                            }
                        });

                        myReserve.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                familySpinner.setVisibility(View.GONE);
                                otherReserve.setChecked(false);

                                myReserveFlag = true;
                                otherReserveFlag = false;
                            }
                        });

                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        confirmed.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                success = true;

                                if (reserveAdapter.reserveCounter() == 0) {
                                    Toast.makeText(getContext(), R.string.countError, Toast.LENGTH_SHORT).show();
                                    success = false;
                                }

                                if (reserveAdapter.reserveCounter() > 1) {
                                    Toast.makeText(getContext(), R.string.countError2, Toast.LENGTH_SHORT).show();
                                    success = false;
                                }
                                if (!myReserveFlag && !otherReserveFlag) {
                                    Toast.makeText(getContext(), R.string.ownerError, Toast.LENGTH_SHORT).show();
                                    success = false;
                                }
                                if (success) {
                                    if (myReserveFlag) {
                                        String name = AppDatabase.getAppDatabase(getContext()).userDao().findUserByCodeMelli(Const.Code).getFirstName()
                                                + " "
                                                + AppDatabase.getAppDatabase(getContext()).userDao().findUserByCodeMelli(Const.Code).getLastName();
                                        AppDatabase.getAppDatabase(getContext()).reserveDao()
                                                .updateOwner(reserveAdapter.findReserve(), Const.Code);
                                        AppDatabase.getAppDatabase(getContext()).reserveDao()
                                                .updateOwnerName(reserveAdapter.findReserve(), name);
                                        dialog.dismiss();
                                        ((MainActivity) getActivity()).changeFrag(((MainActivity) getActivity()).getMainFragment());
                                    } else if (otherReserveFlag && familySpinner.isSelected()) {
                                        AppDatabase.getAppDatabase(getContext()).reserveDao()
                                                .updateOwner(reserveAdapter.findReserve(), Const.Code);
                                        AppDatabase.getAppDatabase(getContext()).reserveDao()
                                                .updateOwnerName(reserveAdapter.findReserve(),
                                                        familySpinner.getSelectedItem());
                                        dialog.dismiss();
                                        ((MainActivity) getActivity()).changeFrag(((MainActivity) getActivity()).getMainFragment());
                                    }

                                }
                            }
                        });

                        dialog.show();
                    }
                }
            }

        });

        return view;
    }
}
