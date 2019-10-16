package edu.auburn;

/**
 * Charlie Stejskal
 * Project 1
 * 16 October, 2019
 */

public class ProductModel {
    public int mProductID;
    public String mName;
    public double mPrice, mQuantity;

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(mProductID).append(",");
        sb.append("\"").append(mName).append("\"").append(",");
        sb.append(mPrice).append(",");
        sb.append(mQuantity).append(")");
        return sb.toString();
    }
}
