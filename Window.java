//package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for GUI
 * @author Darin Georgiev
 */
public class Window {
    /**
     * Here we create instances of the Hashmaps we need and objects for GUI
     */
    private LinkedHashMap<String, LinkedHashMap<String, List<Integer>>> map;
    private WordCount wordCounter = new WordCount();
    private ArrayList<String> fileNames = new ArrayList<>();

    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JLabel textName;
    private JTextField insertName;
    private JButton enterButton,clearButton;

    /**
     *This constructor creates the frame, panel and add the label, text field and the two buttons to it.
     */
    public Window(){

        //panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new FlowLayout());

        frame.setSize(1200,200);
        frame.add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
        frame.setVisible(true);

        textName = new JLabel("Enter file name:");
        panel.add(textName);

        insertName = new JTextField(12);
        panel.add(insertName);

        enterButton = new JButton("Enter");
        panel.add(enterButton);
        enterButton.addActionListener(this::actionPerformed);
        enterButton.setSize(50,20);

        clearButton = new JButton("Clear");
        panel.add(clearButton);
        clearButton.addActionListener(this::actionPerformed);
        clearButton.setSize(50,20);

        frame.pack();
        //mapCount();
        fileNames.clear();
    }

    /**
     * This Action method gets the text from the text field when the Enter button is pressed.
     * Checks if it's valid.
     * If so adds it to the arraylist "fileNames".
     * Calls method print();
     * Removes the element.
     *
     * If the "Clear" button is clicked then it will clear the console.
     * @param e
     */
    public void actionPerformed(ActionEvent e){
        String str = "";
        if(e.getSource() == enterButton) {

            str = insertName.getText();
            fileNames.add(0, str);
            if (str.equals("")) {
                System.out.println("Enter valid name");
            }
            else {
                try {
                    mapCount();


                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                //System.out.println(map.toString());
                insertName.setText("");
                print();
                fileNames.remove(0);

            }
        }
        if(e.getSource() == clearButton){
            System.out.print("\033[H\033[2J");
            System.out.flush();

        }
    }

    /**
     * The Arraylist "fileNames" is sent to the WordCount as a parameter.
     * @throws IOException
     */
    public void mapCount() throws IOException {
        map = wordCounter.count(fileNames);
    }

    /**
     * This method prints the Hashmap
     */
    public void print(){
        for(Map.Entry <String, LinkedHashMap<String, List<Integer>>> entry: map.entrySet()){
            System.out.println(entry.getKey() + entry.getValue().entrySet());
        }
    }

}
