package com.cmpe277.lab1.loancalculator;

/**
 * Created by knbarve on 9/16/15.
 */
public class Loan {

    private double homeValue;
    private double downPayment;
    private double apr;
    private int terms;
    private double taxRate;

    private double totalTax;
    private double totalInterest;
    private double monthlyPayment;
    private String dueDate;

    private int totalTerms;
    private double monthlyInterest;
    private double monthlyPropertyTax;

    private double loanAmount;

    public double getHomeValue() {
        return homeValue;
    }

    public void setHomeValue(String homeValue) {
        this.homeValue = Double.parseDouble(homeValue);
    }

    public double getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(String downPayment) {
        this.downPayment = Double.parseDouble(downPayment);
    }

    public double getApr() {
        return apr;
    }

    public void setApr(String apr) {
        this.apr = Double.parseDouble(apr);
    }

    public int getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = Integer.parseInt(terms);
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = Double.parseDouble(taxRate);
    }

    public double getTotalTax() {
        totalTax = (homeValue*terms*taxRate/100);
        return totalTax;
    }

    public double getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(double totalInterest) {
        this.totalInterest = totalInterest;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public double getLoanAmount() {
        loanAmount = getHomeValue()-getDownPayment();
        return loanAmount;
    }

    public int getTotalTerms() {
        totalTerms = terms*12;
        return totalTerms;
    }

    public double getMonthlyInterestRate() {
        monthlyInterest = (apr/1200);
        return monthlyInterest;
    }

    public double getMonthlyPropertyTax() {
        monthlyPropertyTax = (getTotalTax()/ getTotalTerms());
        return monthlyPropertyTax;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "homeValue=" + homeValue +
                ", downPayment=" + downPayment +
                ", apr=" + apr +
                ", terms=" + terms +
                ", taxRate=" + taxRate +
                ", totalTax=" + totalTax +
                ", totalInterest=" + totalInterest +
                ", monthlyPayment=" + monthlyPayment +
                ", dueDate='" + dueDate + '\'' +
                ", totalTerms=" + totalTerms +
                ", monthlyInterest=" + monthlyInterest +
                ", monthlyPropertyTax=" + monthlyPropertyTax +
                ", loanAmount=" + loanAmount +
                '}';
    }
}
