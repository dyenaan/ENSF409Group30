package edu.ucalgary.ensf409;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.*;

/**
 * @author Ahmad Khaled, Pansilu Wickramasinghe, Dyenaan Dapoet, Esohe Aideyan.
 * @version 1.3
 * @since 1.0
 */

/*
 *   The purpose of the GUI class is to provide the user with a GUI so that they could input their hamper and order details
 *   and for the best hampers to get displayed to them.
 *
 *   The GUI class creates a gui box with text fields for the male, female, child under eight, and child over eight counts.
 *   It also has two button, the first button (submit hamper) adds the hamper to the order list and the second button
 *   (finish order) finishes the order and displays the best hampers.
 *
 *   The GUI class also validates each given input by checking if the inputs are numbers are positive and also checks
 *   if the hamper created is empty or not. The GUI class throws an error message if the inputs are invalid.
 *
 *   The GUI class also throws an error message if the order could not be completed due to insufficient items in stock.
 */

public class GUI extends JFrame implements ActionListener, MouseListener {

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
    private JTextField mCInput; // Male count input
    private JTextField fCInput; // Female count input
    private JTextField cUEInput; // Child under eight input
    private JTextField cOEInput; // child over eight input

    // The constructor sets up the GUI box.

    public GUI() {
        super("Create an order!");
        setupGUI();
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Main method so that the GUI could run when the program starts.

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }

    /* The setupGUI method sets up the GUI with the appropriate boxes and buttons. It also contains custom functionality
    *  for each of the buttons (submitHamper and finishOrder) so that they work as intended. */

    public void setupGUI() {
        instructions = new JLabel("Please enter your information to generate an order");
        mCLabel = new JLabel("Male:");
        fCLabel = new JLabel("Female:");
        cUELabel = new JLabel("Child Under Eight:");
        cOELabel = new JLabel("Child Over Eight:");

        mCInput = new JTextField("e.g. 1", 3);
        fCInput = new JTextField("e.g. 2", 3);
        cUEInput = new JTextField("e.g. 3", 3);
        cOEInput = new JTextField("e.g. 4", 3);

        mCInput.addMouseListener(this);
        fCInput.addMouseListener(this);
        cUEInput.addMouseListener(this);
        cOEInput.addMouseListener(this);

        /* The submitHamper button adds the client counts into a hashmap and inserts it into the orderList */

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

        /* The finishOrder button finishes the order and creates a new Order object with the orderList as an argument for
        *  the constructor. */

        JButton finishOrder = new JButton("Finish order");
        finishOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!orderList.isEmpty()) {
                    try {
                        String orderID = generateOrderID();
                        JOptionPane.showMessageDialog(finishOrder, "The order is successfully finished! The order ID is " + orderID);
                        finishOrder.setEnabled(false);
                        submitHamper.setEnabled(false);
                        Order order = new Order(orderList);
                        OutputToFile output = new OutputToFile(orderID);

                        if (order.getOrderCompleted()) {
                            JOptionPane.showMessageDialog(finishOrder, "Order form successfully created!");
                            output.createOrderForm(order);
                            JOptionPane.showMessageDialog(finishOrder, createBestHamperString(order));
                        } else JOptionPane.showMessageDialog(finishOrder, "There isn't enough stock to complete the order!");

                    } catch (HamperAlreadyFoundException ex) {
                        JOptionPane.showMessageDialog(finishOrder, "The program encountered an error!");
                    } catch (StockNotAvailableException ignore) {} catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else JOptionPane.showMessageDialog(finishOrder, "the order list is empty!");

                finishOrder.setEnabled(true);
                submitHamper.setEnabled(true);
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

    public void mouseClicked(MouseEvent event) {
        if (event.getSource().equals(mCInput)) mCInput.setText("");
        if (event.getSource().equals(fCInput)) fCInput.setText("");
        if (event.getSource().equals(cUEInput)) cUEInput.setText("");
        if (event.getSource().equals(cOEInput)) cOEInput.setText("");
    }

    /* The generateOrderID generates a random 8-digit number that is used for as the Order ID.
    *  The Order ID is used to differentiate and identify each order */

    private String generateOrderID() {
        Random random = new Random();
        int upperbound = 99_999_999;
        int lowerbound = 10_000_000;
        return String.valueOf(random.nextInt(upperbound - lowerbound) + lowerbound);
    }

    /* The validateInput returns true if all GUI inputs are valid and returns false if either one of the
    *  inputs is not a number or less than zero. */

    private boolean validateInput() {
        boolean allInputValid = true;

        if (validateString(mCInput.getText())) {
            maleCount = Integer.parseInt(mCInput.getText());
        } else allInputValid = false;

        if (validateString(fCInput.getText())) {
            femaleCount = Integer.parseInt(fCInput.getText());
        } else allInputValid = false;

        if (validateString(cUEInput.getText())) {
            childUECount = Integer.parseInt(cUEInput.getText());
        } else allInputValid = false;

        if (validateString(cOEInput.getText())) {
            childOECount = Integer.parseInt(cOEInput.getText());
        } else allInputValid = false;

        if (allInputValid) {
            if (maleCount < 0) allInputValid = false;
            if (femaleCount < 0) allInputValid = false;
            if (childUECount < 0) allInputValid = false;
            if (childOECount < 0) allInputValid = false;
            if (maleCount == 0 && femaleCount == 0 && childOECount == 0 && childUECount == 0) allInputValid = false;
        }
        return allInputValid;
    }

    /* The validateString method returns true if the input string is a number and returns false if the input string
    *  is not a number. This is used to make sure that the user inputs are all numbers. */

    private boolean validateString(String textBoxInput) {
        try {
            Integer.parseInt(textBoxInput);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // The createBestHamperString generates a formatted string of all the best hampers

    private String createBestHamperString(Order order) {
        StringBuilder bestHamperString = new StringBuilder();
        for (int i = 0; i < order.getFamilies().length; i++) {
            if (i > 0) bestHamperString.append("\n\n");
            bestHamperString.append("Hamper " + (i + 1) + " items:\n");
            ArrayList<Map<String,String>> bestHamper = order.getFamilies()[i].getHamper();
            for (Map<String, String> foodItem : bestHamper) {
                String name = foodItem.get("Name");
                String itemID = foodItem.get("ItemID");
                bestHamperString.append(itemID).append("\t\t").append(name).append("\n");
            }
        }
        return bestHamperString.toString();
    }

    // The getMaleCount method returns the male count

    public int getMaleCount() {
        return maleCount;
    }

    // The getFemaleCount method returns the female count

    public int getFemaleCount() {
        return femaleCount;
    }

    // The getChildUECount method returns the childUE count

    public int getChildUECount() {
        return childUECount;
    }

    // The getChildUECount method returns the childOE count

    public int getChildOECount() {
        return childOECount;
    }

    // The getOrderList method returns the orderList

    public ArrayList<Map<String,String>> getOrderList() {
        return this.orderList;
    }

    // These methods are required for the GUI to work properly but do not require custom code as they are not used.

    public void mouseEntered(MouseEvent event) { }

    public void mouseExited(MouseEvent event) { }

    public void mousePressed(MouseEvent event) { }

    public void mouseReleased(MouseEvent event) { }

    public void actionPerformed(ActionEvent event) { }
}
