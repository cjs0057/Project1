package edu.auburn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Charlie Stejskal
 * Project 1
 * 16 October, 2019
 */

public class AddCustomerView {

    public JFrame view;

    public JButton btnAdd = new JButton("Add");
    public JButton btnCancel = new JButton("Cancel");

    public JTextField txtCustomerID = new JTextField(20);
    public JTextField txtName = new JTextField(20);
    public JTextField txtPhone = new JTextField(20);
    public JTextField txtEmail = new JTextField(20);


    public AddCustomerView()   {
        this.view = new JFrame();

        view.setTitle("Add Customer");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        String[] labels = {"CustomerID ", "Name ", "Phone ", "Email "};

        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("CustomerID "));
        line1.add(txtCustomerID);
        view.getContentPane().add(line1);

        JPanel line2 = new JPanel(new FlowLayout());
        line2.add(new JLabel("Name "));
        line2.add(txtName);
        view.getContentPane().add(line2);

        JPanel line3 = new JPanel(new FlowLayout());
        line3.add(new JLabel("Phone "));
        line3.add(txtPhone);
        view.getContentPane().add(line3);

        JPanel line4 = new JPanel(new FlowLayout());
        line4.add(new JLabel("Email "));
        line4.add(txtEmail);
        view.getContentPane().add(line4);

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnAdd);
        panelButtons.add(btnCancel);
        view.getContentPane().add(panelButtons);

        btnAdd.addActionListener(new AddButtonListener());

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Customer Add Cancelled");
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
            CustomerModel customer = new CustomerModel();

            String id = txtCustomerID.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "Customer ID cannot be null");
                return;
            }

            try {
                customer.mCustomerID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Customer ID is invalid");
                return;
            }

            String name = txtName.getText();
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Customer name cannot be empty");
                return;
            }

            customer.mName = name;

            String phone = txtPhone.getText();
            try {
                customer.mPhone = Double.parseDouble(phone);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Phone is invalid");
                return;
            }

            String email = txtEmail.getText();
            customer.mEmail = email;

            switch (StoreManager.getInstance().getDataAdapter().saveCustomer(customer)) {
                case SQLiteDataAdapter.CUSTOMER_DUPLICATE_ERROR:
                    JOptionPane.showMessageDialog(null, "Duplicate Customer ID, customer not added");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Customer added successfully " + customer);
            }
        }
    }

}