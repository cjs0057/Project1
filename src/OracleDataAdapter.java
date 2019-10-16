package edu.auburn;

/**
 * Charlie Stejskal
 * Project 1
 * 16 October, 2019
 */

public class OracleDataAdapter implements IDataAdapter {
    public int connect() {
        // for future implementation
        return CONNECTION_OPEN_OK;
    }

    public int disconnect() {
        // for future implementation
        return CONNECTION_CLOSE_OK;
    }

    public ProductModel loadProduct(int id) {
        return null;
    }
    public int saveProduct(ProductModel model) {
        return PRODUCT_SAVED_OK;
    }

    public CustomerModel loadCustomer(int id) {
        return null;
    }
    public int saveCustomer(CustomerModel model) {
        return CUSTOMER_SAVED_OK;
    }

    public PurchaseModel loadPurchase(int id) {
        return null;
    }
    public int savePurchase(PurchaseModel model) {
        return PURCHASE_SAVED_OK;
    }

}
