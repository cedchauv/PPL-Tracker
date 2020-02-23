package com.example.ppltracker.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ppltracker.R;
import com.example.ppltracker.TextListener;
import com.example.ppltracker.writeInterface;

public class PushFragment extends Fragment implements writeInterface {
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private EditText hbenchKG;
    private EditText lbenchkg;
    private EditText hohpkg;
    private EditText lohpKG;
    private EditText incKG;
    private EditText dipKG;

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PushFragment newInstance(int index) {
        PushFragment fragment = new PushFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.pushfragment_weight_settings, container, false);
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
        hbenchKG = getView().findViewById(R.id.HeavyBench_Weight);
        hbenchKG.addTextChangedListener(new TextListener("HB_KG", hbenchKG, this));
        hbenchKG.setText(Float.toString(prefs.getFloat("HB_KG", 60f)));

        lbenchkg = getView().findViewById(R.id.LightBench_Weight);
        lbenchkg.addTextChangedListener(new TextListener("LB_KG", lbenchkg, this));
        lbenchkg.setText(Float.toString(prefs.getFloat("LB_KG", 40f)));

        hohpkg = getView().findViewById(R.id.HeavyOHP_weight);
        hohpkg.addTextChangedListener(new TextListener("hohp_KG", hohpkg, this));
        hohpkg.setText(Float.toString(prefs.getFloat("hohp_KG", 40f)));

        lohpKG = getView().findViewById(R.id.LightOhp_weight);
        lohpKG.addTextChangedListener(new TextListener("lohp_KG", lohpKG, this));
        lohpKG.setText(Float.toString(prefs.getFloat("lohp_KG", 30f)));

        incKG = getView().findViewById(R.id.inc_weight);
        incKG.addTextChangedListener(new TextListener("inc_KG", incKG, this));
        incKG.setText(Float.toString(prefs.getFloat("inc_KG", 12f)));

        dipKG = getView().findViewById(R.id.Dips_weight);
        dipKG.addTextChangedListener(new TextListener("dips_KG", dipKG, this));
        dipKG.setText(Float.toString(prefs.getFloat("dips_KG", 0f)));
    }

}
