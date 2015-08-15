package com.kido.testtask.ui;


import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.kido.testtask.R;


public class TwoFragment extends Fragment {

    private static String FONT_PATH = "fonts/festus.ttf";
    private View rootView;
    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;
    private TextView txtTitle;
    private TextView txtResult;
    private Context fc;


    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_two, container, false);
        return rootView;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fc = getActivity().getApplicationContext();
        txtTitle = (TextView) rootView.findViewById(R.id.txtTitle);
        txtResult = (TextView) rootView.findViewById(R.id.txtResult);
        cb1 = (CheckBox) rootView.findViewById(R.id.cb1);
        cb2 = (CheckBox) rootView.findViewById(R.id.cb2);
        cb3 = (CheckBox) rootView.findViewById(R.id.cb3);

        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckboxClicked();
            }
        });
        cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckboxClicked();
            }
        });
        cb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckboxClicked();
            }
        });

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), FONT_PATH);
        // Applying font
        txtTitle.setTypeface(typeface);
        txtTitle.setText(TwoFragment.class.getSimpleName());
        onCheckboxClicked();
    }

    public void onCheckboxClicked() {

        int rez = 0;
        if (cb1.isChecked()) {
            rez = rez + Integer.parseInt(cb1.getText().toString());
        }
        if (cb2.isChecked()) {
            rez = rez + Integer.parseInt(cb2.getText().toString());
        }
        if (cb3.isChecked()) {
            rez = rez + Integer.parseInt(cb3.getText().toString());
        }

        txtResult.setText(String.valueOf(rez));
    }
}



