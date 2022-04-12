package edu.ucalgary.ensf409;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.SpringLayout;

public class GUIOrderID extends JFrame implements ActionListener, MouseListener{

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
    
    public GUIOrderID(){
        super("Create an order!");
        setupGUI();
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        
    }
    
    public void setupGUI(){
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
        
        JButton submitInfo = new JButton("Submit");
        submitInfo.addActionListener(this);
        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        
        JPanel clientPanel = new JPanel();
		SpringLayout cPanel = new SpringLayout();
        //clientPanel.setLayout(new SpringLayout());

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
		/*clientPanel.add(mCLabel);
		clientPanel.add(mCInput);
		cPanel.putConstraint(SpringLayout.WEST, mCLabel, 20, SpringLayout.WEST, clientPanel);
		cPanel.putConstraint(SpringLayout.NORTH, mCLabel, 20, SpringLayout.NORTH, clientPanel);
		cPanel.putConstraint(SpringLayout.WEST, mCInput, 20, SpringLayout.WEST, mCLabel);
		cPanel.putConstraint(SpringLayout.NORTH, mCInput, 20, SpringLayout.NORTH, clientPanel);
		
		clientPanel.add(fCLabel);
		clientPanel.add(fCInput);
		cPanel.putConstraint(SpringLayout.WEST, fCLabel, 20, SpringLayout.WEST, clientPanel);
		cPanel.putConstraint(SpringLayout.NORTH, fCLabel, 20, SpringLayout.NORTH, clientPanel);
		cPanel.putConstraint(SpringLayout.WEST, fCInput, 20, SpringLayout.WEST, fCLabel);
		cPanel.putConstraint(SpringLayout.NORTH, fCInput, 20, SpringLayout.NORTH, clientPanel);*/
		
        submitPanel.add(submitInfo);
        
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.PAGE_END);
    }
    
    public void actionPerformed(ActionEvent event){
        maleCount = Integer.parseInt(mCInput.getText());
        femaleCount = Integer.parseInt(fCInput.getText());
        childUECount = Integer.parseInt(cUEInput.getText());
        childOECount = Integer.parseInt(cOEInput.getText());
        
        if(validateInput()){
            String orderID = idProcessing();
            JOptionPane.showMessageDialog(this, "Your order ID is " + orderID);
        }
    }
	
    public int getMaleCount(){
		return Integer.parseInt(mCInput.getText());
	}
	
	public int getFemaleCount(){
		return Integer.parseInt(fCInput.getText());
	}
	
	public int getChildUECount(){
		return Integer.parseInt(cUEInput.getText());
	}
	
	public int getChildOECount(){
		return Integer.parseInt(cOEInput.getText());
	}
	
    public void mouseClicked(MouseEvent event){
        
        if(event.getSource().equals(mCInput))
            mCInput.setText("");

        if(event.getSource().equals(fCInput))
            fCInput.setText("");

        if(event.getSource().equals(cUEInput))
            cUEInput.setText("");

        if(event.getSource().equals(cOEInput))
            cOEInput.setText("");
                
    }
    
    public void mouseEntered(MouseEvent event){
        
    }

    public void mouseExited(MouseEvent event){
        
    }

    public void mousePressed(MouseEvent event){
        
    }

    public void mouseReleased(MouseEvent event){
        
    }
    
    private String idProcessing(){

        //String orderID = new String("maleCount: " + String.valueOf(maleCount) + " femaleCount: " + String.valueOf(femaleCount) + " childUECount: " + String.valueOf(childUECount) + " childOECount: " + String.valueOf(childOECount));
        String orderID = new String(String.valueOf(maleCount) + String.valueOf(femaleCount) + String.valueOf(childUECount) + String.valueOf(childOECount));
        return orderID;
    }    
    
    private boolean validateInput()throws IllegalArgumentException{
        
        boolean allInputValid = true;
        
        if(maleCount < 0){
            allInputValid = false;
			throw new IllegalArgumentException();
            //JOptionPane.showMessageDialog(this, maleCount + " is an invalid input.");
        }
        
        if(femaleCount < 0){
            allInputValid = false;
			throw new IllegalArgumentException();
            //JOptionPane.showMessageDialog(this, femaleCount + " is an invalid input.");
        }

        if(childUECount < 0){
            allInputValid = false;
			throw new IllegalArgumentException();
            //JOptionPane.showMessageDialog(this, childUECount + " is an invalid input.");
        }

        if(childOECount < 0){
            allInputValid = false;
			throw new IllegalArgumentException();
            //JOptionPane.showMessageDialog(this, childOECount + " is an invalid input.");
        }
        
        return allInputValid;
    }

    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            new GUIOrderID().setVisible(true);
        });
    }
        
}
