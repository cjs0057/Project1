package edu.auburn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * Charlie Stejskal
 * Project 1
 * 16 October, 2019
 */

public class AddPurchaseView {

    public JFrame view;

    public JButton btnAdd = new JButton("Add");
    public JButton btnCancel = new JButton("Cancel");

    public JTextField txtPurchaseID = new JTextField(20);
    public JTextField txtProductID = new JTextField(20);
    public JTextField txtCustomerID = new JTextField(20);
    public JTextField txtQuantity = new JTextField(20);

    private static DecimalFormat dollars_df = new DecimalFormat("0.00");
    private static DecimalFormat integer_df = new DecimalFormat("0");


    public AddPurchaseView()   {
        this.view = new JFrame();

        view.setTitle("Add Purchase");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        String[] labels = {"PurchaseID ", "ProductID ", "CustomerID", "Quantity ", "Price ", "Tax ", "Total Cost "};

        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("PurchaseID "));
        line1.add(txtPurchaseID);
        view.getContentPane().add(line1);

        JPanel line2 = new JPanel(new FlowLayout());
        line2.add(new JLabel("ProductID "));
        line2.add(txtProductID);
        view.getContentPane().add(line2);

        JPanel line3 = new JPanel(new FlowLayout());
        line3.add(new JLabel("CustomerID "));
        line3.add(txtCustomerID);
        view.getContentPane().add(line3);

        JPanel line4 = new JPanel(new FlowLayout());
        line4.add(new JLabel("Quantity "));
        line4.add(txtQuantity);
        view.getContentPane().add(line4);

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnAdd);
        panelButtons.add(btnCancel);
        view.getContentPane().add(panelButtons);

        btnAdd.addActionListener(new AddButtonListener());

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Purchase Add Cancelled");
                view.setVisible(false);
            }
        });

    }


    public void run() {
        view.setVisible(true);
    }


    class AddButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PurchaseModel purchase = new PurchaseModel();

            // Purchase ID
            String pu_id = txtPurchaseID.getText();
            if (pu_id.length() == 0) {
                JOptionPane.showMessageDialog(null, "PurchaseID cannot be null");
                return;
            }
            try {
                purchase.mPurchaseID = Integer.parseInt(pu_id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "PurchaseID is invalid");
                return;
            }

            // Product ID
            String pr_id = txtProductID.getText();
            if (pr_id.length() == 0) {
                JOptionPane.showMessageDialog(null, "ProductID cannot be null");
                return;
            }
            try {
                purchase.mProductID = Integer.parseInt(pr_id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ProductID is invalid");
                return;
            }
            int prodID = Integer.parseInt(pr_id);
            ProductModel product = StoreManager.getInstance().getDataAdapter().loadProduct(prodID);
            if (product.mProductID == -1) {
                JOptionPane.showMessageDialog(null, "ProductID does not exist");
                return;
            }


            // Customer ID
            String cu_id = txtCustomerID.getText();
            if (cu_id.length() == 0) {
                JOptionPane.showMessageDialog(null, "CustomerID cannot be null");
                return;
            }
            try {
                purchase.mCustomerID = Integer.parseInt(cu_id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "CustomerID is invalid");
                return;
            }
            int custID = Integer.parseInt(cu_id);
            CustomerModel customer = StoreManager.getInstance().getDataAdapter().loadCustomer(custID);
            if (customer.mCustomerID == -1) {
                JOptionPane.showMessageDialog(null, "CustomerID does not exist");
                return;
            }

            // Quantity
            String quant = txtQuantity.getText();
            try {
                purchase.mQuantity = Double.parseDouble(quant);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Quantity is invalid");
                return;
            }

            // Price
            purchase.mPrice = product.mPrice * purchase.mQuantity;

            // Tax
            purchase.mTax = purchase.mPrice * 0.08;

            // Total Cost
            purchase.mTotalCost = purchase.mPrice + purchase.mTax;


            switch (StoreManager.getInstance().getDataAdapter().savePurchase(purchase)) {
                case SQLiteDataAdapter.PURCHASE_DUPLICATE_ERROR:
                    JOptionPane.showMessageDialog(null, "Duplicate Purchase ID, purchase not added");
                    break;
                default:
                    // Receipt
                    String msg = "Purchase added successfully\n\n"
                            + "Customer: " + customer.mName + "\n"
                            + "Product: " + product.mName + " (" + integer_df.format(product.mQuantity) + ")\n"
                            + "Quantity: " + integer_df.format(purchase.mQuantity) + "\n"
                            + "Price: $" + dollars_df.format(purchase.mPrice) + "\n"
                            + "Tax: $" + dollars_df.format(purchase.mTax) + "\n"
                            + "Total Cost: $" + dollars_df.format(purchase.mTotalCost) + "\n";
                    JOptionPane.showMessageDialog(null, msg);

            }
        }
    }

}