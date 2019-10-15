package com.example.ppltracker.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ppltracker.R;
import com.example.ppltracker.TextListener;
import com.example.ppltracker.writeInterface;

/**
 * A placeholder fragment containing a simple view.
 */
public class PullFragment extends Fragment implements writeInterface {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private EditText dlKG;
    private EditText bbrKG;
    private EditText pullupKG;
    private EditText cableKG;
    private EditText faceKG;
    private EditText curlKG;

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PullFragment newInstance(int index) {
        PullFragment fragment = new PullFragment();
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
        View root = inflater.inflate(R.layout.pullfragment_weight_settings, container, false);
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
        dlKG = getView().findViewById(R.id.Deadlift_Weight);
        dlKG.addTextChangedListener(new TextListener("DL_KG", dlKG, this));
        dlKG.setText(Float.toString(prefs.getFloat("DL_KG", 70f)));

        bbrKG = getView().findViewById(R.id.BBRow_Weight);
        bbrKG.addTextChangedListener(new TextListener("BBR_KG", bbrKG, this));
        bbrKG.setText(Float.toString(prefs.getFloat("BBE_KG", 30f)));

        pullupKG = getView().findViewById(R.id.pullup_weight);
        pullupKG.addTextChangedListener(new TextListener("pullUp_KG", pullupKG, this));
        pullupKG.setText(Float.toString(prefs.getFloat("pullUp_KG", 0f)));

        cableKG = getView().findViewById(R.id.cable_weight);
        cableKG.addTextChangedListener(new TextListener("cableRow_KG", cableKG, this));
        cableKG.setText(Float.toString(prefs.getFloat("cableRow_KG", 40f)));

        faceKG = getView().findViewById(R.id.face_weight);
        faceKG.addTextChangedListener(new TextListener("face_KG", faceKG, this));
        faceKG.setText(Float.toString(prefs.getFloat("face_KG", 15f)));

        curlKG = getView().findViewById(R.id.curl_weight);
        curlKG.addTextChangedListener(new TextListener("curl_KG", curlKG, this));
        curlKG.setText(Float.toString(prefs.getFloat("curl_KG", 8f)));
    }
}