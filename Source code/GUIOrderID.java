package edu.ucalgary.ensf409;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GUIOrderID extends JFrame implements ActionListener, MouseListener {

    private ArrayList<Map<String, String>> orderList = new ArrayList<>();
    private int maleCount;
    private int femaleCount;
    private int childUECount;
    private int childOECount;
    private JLabel instructions;
    private JLabel mCLabel;
    private JLabel fCLabel;
    private JLabel cUELabel;
    private JLabel cOELabel;
    private JTextField mCInput;
    private JTextField fCInput;
    private JTextField cUEInput;
    private JTextField cOEInput;

    public GUIOrderID() {
        super("Create an order!");
        setupGUI();
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            new GUIOrderID().setVisible(true);
        });
    }

    public void setupGUI() {
        instructions = new JLabel("Please enter your information to generate an order");
        mCLabel = new JLabel("Male:");
        fCLabel = new JLabel("Female:");
        cUELabel = new JLabel("Child Under Eight:");
        cOELabel = new JLabel("Child Over Eight:");

        mCInput = new JTextField("e.g. 2", 3);
        fCInput = new JTextField("e.g. 3", 3);
        cUEInput = new JTextField("e.g. 1", 3);
        cOEInput = new JTextField("e.g. 2", 3);

        mCInput.addMouseListener(this);
        fCInput.addMouseListener(this);
        cUEInput.addMouseListener(this);
        cOEInput.addMouseListener(this);

        JButton submitHamper = new JButton("Submit hamper");
        submitHamper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    Map<String, String> family = new HashMap<>();
                    family.put("Male", Integer.toString(getMaleCount()));
                    family.put("Female", Integer.toString(getFemaleCount()));
                    family.put("ChildUE", Integer.toString(getChildUECount()));
                    family.put("ChildOE", Integer.toString(getChildOECount()));
                    orderList.add(family);
                    JOptionPane.showMessageDialog(submitHamper, "Successfully submitted hamper!");
                } else JOptionPane.showMessageDialog(submitHamper, "The input is invalid!");
            }
        });

        JButton finishOrder = new JButton("Finish order");
        finishOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!orderList.isEmpty()) {
                    try {
                        JOptionPane.showMessageDialog(finishOrder, "The order is successfully finished! The order ID is " + idProcessing());
                        Order order = new Order(orderList);
                        OutputToFile output = new OutputToFile();
                        output.writeFile("test", order.getFamilies());
                    } catch (HamperAlreadyFoundException ex) {
                        JOptionPane.showMessageDialog(finishOrder, "The program encountered an error!");
                    } catch (StockNotAvailableException ex) {
                        JOptionPane.showMessageDialog(finishOrder, "There isn't enough stock to complete the order!");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else JOptionPane.showMessageDialog(finishOrder, "the order list is empty!");
                orderList = new ArrayList<>();
            }
        });

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());

        JPanel clientPanel = new JPanel();
        SpringLayout cPanel = new SpringLayout();

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout());

        headerPanel.add(instructions);
        clientPanel.add(mCLabel);
        clientPanel.add(mCInput);
        clientPanel.add(fCLabel);
        clientPanel.add(fCInput);
        clientPanel.add(cUELabel);
        clientPanel.add(cUEInput);
        clientPanel.add(cOELabel);
        clientPanel.add(cOEInput);
        submitPanel.add(submitHamper);
        submitPanel.add(finishOrder);

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.PAGE_END);
    }

    public void actionPerformed(ActionEvent event) {
    }

    public int getMaleCount() {
        return Integer.parseInt(mCInput.getText());
    }

    public int getFemaleCount() {
        return Integer.parseInt(fCInput.getText());
    }

    public int getChildUECount() {
        return Integer.parseInt(cUEInput.getText());
    }

    public int getChildOECount() {
        return Integer.parseInt(cOEInput.getText());
    }

    public void mouseClicked(MouseEvent event) {
        if (event.getSource().equals(mCInput)) mCInput.setText("");
        if (event.getSource().equals(fCInput)) fCInput.setText("");
        if (event.getSource().equals(cUEInput)) cUEInput.setText("");
        if (event.getSource().equals(cOEInput)) cOEInput.setText("");
    }

    public void mouseEntered(MouseEvent event) {
    }

    public void mouseExited(MouseEvent event) {
    }

    public void mousePressed(MouseEvent event) {
    }

    public void mouseReleased(MouseEvent event) {
    }

    // @TODO : Generate a random number for the order ID
    private String idProcessing() {
        String orderID = getMaleCount() + "" + getFemaleCount() + "" + getChildUECount() + "" + getChildOECount();
        return orderID;
    }

    private boolean validateInput() throws IllegalArgumentException {

        boolean allInputValid = true;

        try {
            Integer.parseInt(mCInput.getText());
        } catch(NumberFormatException e){
            allInputValid = false;
        }

        try {
            Integer.parseInt(fCInput.getText());
        } catch(NumberFormatException e){
            allInputValid = false;
        }

        try {
            Integer.parseInt(cOEInput.getText());
        } catch(NumberFormatException e){
            allInputValid = false;
        }

        try {
            Integer.parseInt(cUEInput.getText());
        } catch(NumberFormatException e){
            allInputValid = false;
        }

        if (getMaleCount() < 0) {
            allInputValid = false;
            //JOptionPane.showMessageDialog(this, maleCount + " is an invalid input.");
        }

        if (getFemaleCount() < 0) {
            allInputValid = false;
            //JOptionPane.showMessageDialog(this, femaleCount + " is an invalid input.");
        }

        if (getChildUECount() < 0) {
            allInputValid = false;
            //JOptionPane.showMessageDialog(this, childUECount + " is an invalid input.");
        }

        if (getChildOECount() < 0) {
            allInputValid = false;
            //JOptionPane.showMessageDialog(this, childOECount + " is an invalid input.");
        }

        return allInputValid;
    }

}
