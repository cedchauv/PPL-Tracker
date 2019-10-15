package com.example.ppltracker;


import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class TextListener implements TextWatcher {
    private String key;
    private EditText text;
    private writeInterface act;
    public TextListener(String keyName, EditText edt,  writeInterface parent){
        key = keyName;
        text = edt;
        act = parent;
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        act.writeFloat(key, Float.parseFloat(text.getText().toString()));

    }
}
