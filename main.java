/**
 * Ryan Sandan
 * v1 Sept 1, 2019
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class GPACalculator extends JFrame
{
    private JLabel gradeL, unitsL, emptyL;
    
    private JTextField gradeTF, unitsTF;
  
    private JButton addButton, gpaButton, resetButton, exitButton;
    
    private AddButtonHandler abHandler;
    private GPAButtonHandler cbHandler;
    private ResetButtonHandler rbHandler;
    private ExitButtonHandler ebHandler;
    
    private static final int WIDTH = 400;
    private static final int HEIGHT = 200;
    
    private static double totalUnits = 0.0;
    private static double gradePoints = 0.0;
    private static double totalGPA = 0.0;
    
    //Constructor
    public GPACalculator()
    {
       //Create Labels
       gradeL = new JLabel("Enter your grade: ", SwingConstants.RIGHT);
       unitsL = new JLabel("Enter number of units: ", SwingConstants.RIGHT);
       emptyL = new JLabel("", SwingConstants.RIGHT);
        
       //Create text fields
       gradeTF = new JTextField(20);
       unitsTF = new JTextField(20);
        
       //Create Add Button
       addButton = new JButton("Add Class");
       abHandler = new AddButtonHandler();
       addButton.addActionListener(abHandler);
        
       //Create GPA Button
       gpaButton = new JButton("Click to find out GPA");
       cbHandler = new GPAButtonHandler();
       gpaButton.addActionListener(cbHandler);
        
       //Create Reset Button
       resetButton = new JButton("Reset");
       rbHandler = new ResetButtonHandler();
       resetButton.addActionListener(rbHandler);
        
       //Create Exit Button
       exitButton = new JButton("Exit");
       ebHandler = new ExitButtonHandler();
       exitButton.addActionListener(ebHandler);
       
       //Set the title of the window
       setTitle("GPA CALCULATOR");
       
       //Get the container
       Container pane = getContentPane();
       
       //Set the layout. 
       //3 rows, 3 columns
       pane.setLayout(new GridLayout(3, 3));
       
       //Place the components in the pane
       pane.add(gradeL);
       pane.add(gradeTF);
       pane.add(addButton);
       pane.add(unitsL);
       pane.add(unitsTF);
       pane.add(gpaButton);
       pane.add(emptyL);
       pane.add(resetButton);
       pane.add(exitButton);
       
       //Set the size of the window and display it
       setSize(WIDTH, HEIGHT);
       setVisible(true);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private class AddButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //If there are no entries, display message
            if (gradeTF.getText().equals("") && unitsTF.getText().equals(""))
            {
                noEntryMsg();
            }
            else
            {
                //Read the score and the units
                char grade = gradeTF.getText().charAt(0);
                double units = Double.parseDouble(unitsTF.getText());
                double points = 0.0;
                
                //Calculating points according to the entries (grade & units)
                //For example: 
                //When grade is 'a' or 'A', the points = units * 4.0
                //When grade is 'b' or 'B', the points = units * 3.0
                //When grade is 'c' or 'C', the points = units * 2.0
                //When grade is 'd' or 'D', the points = units * 1.0
                //When grade is 'f' or 'F', the points = units * 0
                
                if (grade == 'a' || grade == 'A')
                {
                    points = units * 4.0;
                }
                else if(grade == 'b' || grade == 'B')
                {
                    points = units * 3.0;
                }
                else if(grade == 'c' || grade == 'C')
                {
                    points = units * 2.0;
                }
                else if(grade == 'd' || grade == 'D')
                {
                    points = units * 1.0;
                }
                else
                {
                    points = 0.0;
                }
                
                //Add units to total Units
                totalUnits += units;
                gradePoints += points;
                
                //Reset Text Fields
                gradeTF.setText("");
                unitsTF.setText("");
            }
        }
    }
    private class ResetButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String msg = "Resetting will delete all entries.\n" +
                "Are you sure you want to continue?";

            int answer = JOptionPane.showConfirmDialog (null, 
                    msg, "Click Yes of No",JOptionPane.YES_NO_OPTION);

            //Display window to try again
            if (answer == JOptionPane.YES_OPTION)
            {
                //Reset text fields
                gradeTF.setText("");
                unitsTF.setText("");

                // Reset total units and total grade points
                // Your code here (3 of 5)....
                
                totalUnits = 0.0;
                gradePoints = 0.0;
            }

        }
    }
    private class GPAButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (gradePoints == 0.0)   
            { 
                if (gradeTF.getText().equals("") && unitsTF.getText().equals(""))  
                {
                    noEntryMsg();         
                }
                else
                {           
                    String msg = "Click the ADD button\n and then click GPA again.";

                    //Show results on message box
                    JOptionPane.showMessageDialog(null, msg, "Result", 
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else
            {
                // Compute totalGPA by dividing the total grade points by the total units
                // Your code here (4 of 5)....
                
                totalGPA = gradePoints/totalUnits;

                DecimalFormat df = new DecimalFormat("0.000");

                String outputStr = "GPA: " + df.format(totalGPA) + "\n";

                //Show results on message box
                JOptionPane.showMessageDialog(null, outputStr, "Result", 
                    JOptionPane.INFORMATION_MESSAGE); 

                int answer = JOptionPane.showConfirmDialog (null, 
                        "Try again?", "Click Yes of No",
                        JOptionPane.YES_NO_OPTION);

                //Display window to try again
                if (answer == JOptionPane.YES_OPTION)
                {
                    //Reset text fields
                    gradeTF.setText("");
                    unitsTF.setText("");

                    // Reset total units and total grade points
                    // Your code here (5 of 5)....
                    
                    totalUnits = 0.0;
                    gradePoints = 0.0;
                    
                }
                else if (answer == JOptionPane.NO_OPTION)
                {
                    System.exit(0);
                }
            }
        }   
    }

    private class ExitButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }

    public static void noEntryMsg()
    {
        String msg = "Enter grade and number of units.";
        //Show results on message box
        JOptionPane.showMessageDialog(null, msg, "Result", 
            JOptionPane.INFORMATION_MESSAGE);   
    }

    public static void main(String[] args)
    {
        GPACalculator gpaCalculator = new GPACalculator();
    } 
}
