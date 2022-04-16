package edu.ucalgary.ensf409;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.*;

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
    private JTextField mCInput;
    private JTextField fCInput;
    private JTextField cUEInput;
    private JTextField cOEInput;

    public GUI() {
        super("Create an order!");
        setupGUI();
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }

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

    public void actionPerformed(ActionEvent event) {
    }

    public int getMaleCount() {
        return maleCount;
    }

    public int getFemaleCount() {
        return femaleCount;
    }

    public int getChildUECount() {
        return childUECount;
    }

    public int getChildOECount() {
        return childOECount;
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

    private String generateOrderID() {
        Random random = new Random();
        int upperbound = 999_999_999;
        int lowerbound = 100_00_000;
        return String.valueOf(random.nextInt(upperbound - lowerbound) + lowerbound);
    }

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

    private boolean validateString(String textBoxInput) {
        try {
            Integer.parseInt(textBoxInput);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

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
        System.out.println(bestHamperString.toString());
        return bestHamperString.toString();
    }

    public ArrayList<Map<String,String>> getOrderList() {
        return this.orderList;
    }
}
