
package edu.ucalgary.ensf409;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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
	
	private HashMap<String, Integer> hMap = new HashMap<String, Integer>();
    
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
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				EventQueue.invokeLater(() -> {
                new GUIOrderID().setVisible(true);        
                });
			}
		});
        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        
        JPanel clientPanel = new JPanel();
		clientPanel.setLayout(new FlowLayout());

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
		clientPanel.add(addButton);
		
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
        
        if(!validateInput()){
            JOptionPane.showMessageDialog(this, "Please input valid numbers");
        }
		else{
			String orderID = idProcessing();
			JOptionPane.showMessageDialog(this, "Your order ID is " + orderID);
			hMap.put("maleCount", maleCount);
			hMap.put("femaleCount", femaleCount);
			hMap.put("childUECount", childUECount);
			hMap.put("childOECount", childOECount);
		}
		
		/*if(event.getSource() == addButton){
			GUIOrderID myGUI = new GUIOrderID().setVisible(true);
		}*/
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
	
	public HashMap<String, Integer> getHashMap(){
		/*for(String name : this.hMap.keySet()){
			String key = name.toString();
			String value = this.hMap.get(name).toString();
			System.out.println(key + " " + value);
		}*/
		
		for(Map.Entry entry : this.hMap.entrySet()){
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		return this.hMap;
		//System.out.println(Arrays.asList(this.hMap));
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
		
		/*if(event.getSource().equals(addButton)){
			EventQueue.invokeLater(() -> {
            new GUIOrderID().setVisible(true);        
            });
		}*/
                
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
            GUIOrderID work = new GUIOrderID();
			work.setVisible(true);
            work.getHashMap();			
        });
		
    }
        
}
