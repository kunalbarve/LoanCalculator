package com.cmpe277.lab1.loancalculator;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainActivityFragment.ButtonListener {

    private EditText homeValuePlace;
    private EditText downPaymentPlace;
    private EditText aprPlace;
    private Spinner termsPlace;
    private EditText taxRatePlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeValuePlace = (EditText)findViewById(R.id.homeValue);
        downPaymentPlace = (EditText)findViewById(R.id.downPayment);
        aprPlace = (EditText)findViewById(R.id.anualRate);
        termsPlace = (Spinner)findViewById(R.id.terms);
        taxRatePlace = (EditText)findViewById(R.id.taxRate);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            resetInputValues();

            PaymentDetails payment = (PaymentDetails) getFragmentManager().findFragmentById(R.id.fragment_output);
            payment.resetOutputValues();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void resetInputValues() {
        homeValuePlace.setText("");
        downPaymentPlace.setText("");
        aprPlace.setText("");
        taxRatePlace.setText("");

        termsPlace = (Spinner) findViewById(R.id.terms);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.terms_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        termsPlace.setSelection(adapter.getPosition("Select Years"));

    }

    @Override
    public void onButtonClick() {
        String homeValue = homeValuePlace.getText().toString().trim();
        String downPayment = downPaymentPlace.getText().toString().trim();
        String apr = aprPlace.getText().toString().trim();
        String taxRate = taxRatePlace.getText().toString().trim();
        String terms = termsPlace.getSelectedItem().toString();

        boolean error = false;

        Log.d("Details------>", apr);

        if(homeValue.equalsIgnoreCase("") || downPayment.equalsIgnoreCase("") || apr.equalsIgnoreCase("") || taxRate.equalsIgnoreCase("") || terms.equalsIgnoreCase("Select Years")){
            error = true;
            Toast.makeText(this, "Please enter all above fields.", Toast.LENGTH_LONG).show();
        }else{
            if(!homeValue.equalsIgnoreCase("") && !downPayment.equalsIgnoreCase("")){
                double homeTemp = Double.parseDouble(homeValue);
                double dpTemp = Double.parseDouble(downPayment);
                if(homeTemp < dpTemp){
                    error = true;
                    Toast.makeText(this, "Down payment can not be more than home value.", Toast.LENGTH_LONG).show();
                }
            }
            if(!apr.equalsIgnoreCase("")){
                double aprTemp = Double.parseDouble(apr);
                Log.d("My APR",String.valueOf(aprTemp));
                if(aprTemp < 0.0 || aprTemp > 100.0){
                    error = true;
                    Toast.makeText(this, "Annual Rate needs to be in between 0 & 100", Toast.LENGTH_LONG).show();
                }
            }
            if(!taxRate.equalsIgnoreCase("")){
                double taxTemp = Double.parseDouble(taxRate);
                if(taxTemp < 0.0 || taxTemp > 100.0){
                    error = true;
                    Toast.makeText(this, "Tax rate needs to be in between 0 & 100", Toast.LENGTH_LONG).show();
                }
            }
        }

        if(!error){
            Loan loan = new Loan();
            loan.setHomeValue(homeValue);
            loan.setDownPayment(downPayment);
            loan.setApr(apr);
            loan.setTerms(terms);
            loan.setTaxRate(taxRate);

            Log.d("Before Calculation", loan.toString());
            loan = LoanCalculator.doPaymentCalculation(loan);

            Log.d("After Calculation", loan.toString());

            PaymentDetails payment = (PaymentDetails) getFragmentManager().findFragmentById(R.id.fragment_output);
            payment.updateDetails(loan);
        }
    }
}
