package com.cmpe277.lab1.loancalculator;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

public class MainActivityFragment extends Fragment {

    private Button calculateButton;
    private Spinner termsCombo;


    public MainActivityFragment() {
    }

    ButtonListener activityCallback;

    public interface ButtonListener {
        public void onButtonClick();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ButtonListener) {
            activityCallback = (ButtonListener) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implemnet MyListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);


        termsCombo = (Spinner) v.findViewById(R.id.terms);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(v.getContext(),
                R.array.terms_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        termsCombo.setAdapter(adapter);


        calculateButton = (Button) v.findViewById(R.id.calculate);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePaymentDetails();
            }
        });

        return v;
    }

    private void calculatePaymentDetails() {
        activityCallback.onButtonClick();
    }
}

