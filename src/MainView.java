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

public class MainView {

    public JFrame view;

    public JButton btnAddProduct = new JButton("Add Product");
    public JButton btnAddCustomer = new JButton("Add Customer");
    public JButton btnAddPurchase = new JButton("Add Purchase");

    public MainView()   {
        this.view = new JFrame();

        view.setTitle("Store Management System");
        view.setSize(600, 100);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnAddProduct);
        panelButtons.add(btnAddCustomer);
        panelButtons.add(btnAddPurchase);
        view.getContentPane().add(panelButtons);

        btnAddProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddProductView apr = new AddProductView();
                apr.run();;
            }
        });

        btnAddCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddCustomerView ac = new AddCustomerView();
                ac.run();;
            }
        });

        btnAddPurchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddPurchaseView apu = new AddPurchaseView();
                apu.run();;
            }
        });

    }

    public void run() {
        view.setVisible(true);
    }

}