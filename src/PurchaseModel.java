package edu.auburn;

/**
 * Charlie Stejskal
 * Project 1
 * 16 October, 2019
 */

public class PurchaseModel {
    public int mPurchaseID, mProductID, mCustomerID;
    public double mQuantity, mPrice, mTax, mTotalCost;

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(mPurchaseID).append(",");
        sb.append(mProductID).append(",");
        sb.append(mCustomerID).append(",");
        sb.append(mQuantity).append(",");
        sb.append(mPrice).append(",");
        sb.append(mTax).append(",");
        sb.append(mTotalCost).append(")");
        return sb.toString();
    }
}
