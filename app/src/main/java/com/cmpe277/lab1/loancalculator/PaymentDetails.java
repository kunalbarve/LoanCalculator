package com.cmpe277.lab1.loancalculator;


import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.math.BigDecimal;


public class PaymentDetails extends Fragment {

    private static EditText totalTaxPlace;
    private static EditText totalInterestPlace;
    private static EditText monthlyPaymentPlace;
    private static EditText dueDatePlace;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_details, container, false);

        totalTaxPlace = (EditText) view.findViewById(R.id.totalTax);
        totalInterestPlace = (EditText) view.findViewById(R.id.totalInterest);
        monthlyPaymentPlace = (EditText) view.findViewById(R.id.monthlyPayment);
        dueDatePlace = (EditText) view.findViewById(R.id.dueDate);

        return view;
    }

    public void updateDetails(Loan loan){
        String totalTax = String.valueOf(LoanCalculator.round(loan.getTotalTax(), 3, BigDecimal.ROUND_HALF_UP));
        String interest = String.valueOf(LoanCalculator.round(loan.getTotalInterest(), 3, BigDecimal.ROUND_HALF_UP));
        String monthlyPayment = String.valueOf(LoanCalculator.round(loan.getMonthlyPayment(), 3, BigDecimal.ROUND_HALF_UP));
        String dueDate = loan.getDueDate();

        totalTaxPlace.setText("Total Tax Paid: $"+totalTax);
        totalInterestPlace.setText("Total Interest Paid: $"+interest);
        monthlyPaymentPlace.setText("Monthly Installment: $"+monthlyPayment);
        dueDatePlace.setText("Due Date: "+dueDate);
    }

    public void resetOutputValues(){
        totalTaxPlace.setText("");
        totalInterestPlace.setText("");
        monthlyPaymentPlace.setText("");
        dueDatePlace.setText("");
    }
}

