package com.example.ppltracker.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ppltracker.R;
import com.example.ppltracker.TextListener;
import com.example.ppltracker.writeInterface;


public class LegsFragment extends Fragment implements writeInterface {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private EditText squatKG;
    private EditText rdlKG;
    private EditText legPressKG;
    private EditText hamcurlKG;
    private EditText calfKG;


    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static LegsFragment newInstance(int index) {
        LegsFragment fragment = new LegsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void writeFloat(String key, float data){
        editor.putFloat(key, data);
        editor.apply();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);

        prefs = this.getActivity().getSharedPreferences("PPL_prefs.xml", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_legs, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        squatKG = getView().findViewById(R.id.Squat_Weight);
        squatKG.addTextChangedListener(new TextListener("SQUAT_KG", squatKG, this));
        squatKG.setText(Float.toString(prefs.getFloat("SQUAT_KG", 70f)));

        rdlKG = getView().findViewById(R.id.rdl_Weight);
        rdlKG.addTextChangedListener(new TextListener("RDL_KG", rdlKG, this));
        rdlKG.setText(Float.toString(prefs.getFloat("RDL_KG", 30f)));

        legPressKG = getView().findViewById(R.id.LegP_weight);
        legPressKG.addTextChangedListener(new TextListener("legP_KG", legPressKG, this));
        legPressKG.setText(Float.toString(prefs.getFloat("legP_KG", 0f)));

        hamcurlKG = getView().findViewById(R.id.Hamcurl_weight);
        hamcurlKG.addTextChangedListener(new TextListener("hamcurl_KG", hamcurlKG, this));
        hamcurlKG.setText(Float.toString(prefs.getFloat("hamcurl_KG", 40f)));

        calfKG = getView().findViewById(R.id.calves_weight);
        calfKG.addTextChangedListener(new TextListener("calf_KG", calfKG, this));
        calfKG.setText(Float.toString(prefs.getFloat("calf_KG", 15f)));
    }
}