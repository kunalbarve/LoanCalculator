package com.cmpe277.lab1.loancalculator;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by knbarve on 9/16/15.
 */
public class LoanCalculator {

    public static void calculateMonthlyMortgagePayment(Loan loan){
        double P = loan.getLoanAmount();
        double i = loan.getMonthlyInterestRate();
        int N = loan.getTotalTerms();

        double temp = recursivePower(i+1, N);

        double monthlyMortgagePayment = (P*i*temp)/(temp-1);

        double monthlyPropertyTax = loan.getMonthlyPropertyTax();

        double monthlyPayment = monthlyMortgagePayment + monthlyPropertyTax;

        loan.setMonthlyPayment(monthlyPayment);
    }

    public static double round(double unRounded, int precision, int roundingMode){
        BigDecimal bd = new BigDecimal(unRounded);
        BigDecimal rounded = bd.setScale(precision, roundingMode);
        return rounded.doubleValue();
    }

    public static double recursivePower(double x, int k) {
        if (k < 0) {
            return recursivePower(x, ++k) / x;
        } else if (k == 0) {
            return 1;
        } else {
            return recursivePower(x, --k) / (1 / x);
        }
    }

    public static void calculateTotalInterest(Loan loan){
        double monthlyPayment = loan.getMonthlyPayment();
        double totalPayment = monthlyPayment * loan.getTotalTerms();

        double totalInterest = totalPayment - loan.getLoanAmount() - loan.getTotalTax();

        loan.setTotalInterest(totalInterest);

    }

    private static void calculateDueDate(Loan loan) {
        int totalMonths = (int) loan.getTotalTerms();
        Date current = new Date();
        System.out.println(current);
        Calendar cal = Calendar.getInstance();
        cal.setTime(current);
        cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH) + totalMonths));
        current = cal.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        String finalDate = sdf.format(current);

        System.out.println(finalDate);
        loan.setDueDate(finalDate);
    }

    public static Loan doPaymentCalculation(Loan loan){
        calculateMonthlyMortgagePayment(loan);
        calculateTotalInterest(loan);
        calculateDueDate(loan);
        return loan;
    }
}
