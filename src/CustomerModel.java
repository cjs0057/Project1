package edu.auburn;

/**
 * Charlie Stejskal
 * Project 1
 * 16 October, 2019
 */

public class CustomerModel {
    public int mCustomerID;
    public String mName, mEmail;
    public double mPhone;

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(mCustomerID).append(",");
        sb.append("\"").append(mName).append("\"").append(",");
        sb.append(mPhone).append(",");
        sb.append("\"").append(mEmail).append("\"").append(")");
        return sb.toString();
    }
}
