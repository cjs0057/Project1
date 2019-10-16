package edu.auburn;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Charlie Stejskal
 * Project 1
 * 16 October, 2019
 */

public class SQLiteDataAdapter implements IDataAdapter {

    Connection conn = null;

    public int connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:C:/Users/charl/Documents/software/store.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return CONNECTION_OPEN_FAILED;
        }
        return CONNECTION_OPEN_OK;
    }


    @Override
    public int disconnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return CONNECTION_CLOSE_FAILED;
        }
        return CONNECTION_CLOSE_OK;
    }


    public ProductModel loadProduct(int productID) {
        ProductModel product = new ProductModel();

        try {
            String sql = "SELECT ProductId, Name, Price, Quantity FROM Products WHERE ProductId = " + productID;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next() == false) {
                product.mProductID = -1;
            } else {
                product.mProductID = rs.getInt("ProductId");
            }
            product.mName = rs.getString("Name");
            product.mPrice = rs.getDouble("Price");
            product.mQuantity = rs.getDouble("Quantity");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return product;
    }


    public int saveProduct(ProductModel product) {
        try {
            String sql = "INSERT INTO Products(ProductId, Name, Price, Quantity) VALUES " + product;
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            String msg = e.getMessage();
            System.out.println(msg);
            if (msg.contains("UNIQUE constraint failed"))
                return PRODUCT_DUPLICATE_ERROR;
        }

        return PRODUCT_SAVED_OK;
    }


    public CustomerModel loadCustomer(int customerID) {
        CustomerModel customer = new CustomerModel();

        try {
            String sql = "SELECT CustomerId, Name, Phone, Email FROM Customers WHERE CustomerId = " + customerID;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next() == false) {
                customer.mCustomerID = -1;
            } else {
                customer.mCustomerID = rs.getInt("CustomerId");
            }
            customer.mName = rs.getString("Name");
            customer.mPhone = rs.getDouble("Phone");
            customer.mEmail = rs.getString("Email");


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return customer;
    }


    public int saveCustomer(CustomerModel customer) {
        try {
            String sql = "INSERT INTO Customers(CustomerId, Name, Phone, Email) VALUES " + customer;
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            String msg = e.getMessage();
            System.out.println(msg);
            if (msg.contains("UNIQUE constraint failed"))
                return CUSTOMER_DUPLICATE_ERROR;
        }

        return CUSTOMER_SAVED_OK;
    }


    public PurchaseModel loadPurchase(int purchaseID) {
        PurchaseModel purchase = new PurchaseModel();

        try {
            String sql = "SELECT PurchaseId, ProductID, CustomerID, Quantity, Price, TotalCost FROM Purchases WHERE PurchaseId = " + purchaseID;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            purchase.mPurchaseID = rs.getInt("PurchaseID");
            purchase.mProductID = rs.getInt("ProductID");
            purchase.mCustomerID = rs.getInt("CustomerID");
            purchase.mQuantity = rs.getDouble("Quantity");
            purchase.mPrice = rs.getDouble("Price");
            purchase.mTax = rs.getDouble("Tax");
            purchase.mTotalCost = rs.getDouble("TotalCost");


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return purchase;
    }


    public int savePurchase(PurchaseModel purchase) {
        try {
            String sql = "INSERT INTO Purchases(PurchaseID, ProductID, CustomerID, Quantity, Price, Tax, TotalCost) VALUES " + purchase;
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            String msg = e.getMessage();
            System.out.println(msg);
            if (msg.contains("UNIQUE constraint failed"))
                return PURCHASE_DUPLICATE_ERROR;
        }

        return PURCHASE_SAVED_OK;
    }
}
