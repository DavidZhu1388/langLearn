package ui;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.* ;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import model.trainingMode.*;

public class questions extends JPanel {
    
    JLabel Q;

    JButton option1 ,
            option2,
            option3,
            option4 ;
    
    String correct_answer;

    static boolean next = false ;
    static int score = 0 ;
    
    questions (multiChoice obj, JFrame window) {
        // EFFECTS: returns true if the correct answer was chosen,
        //          else the option is erased and the player continues to choose

        while (true) {
            Q = new JLabel(obj.getQ());
            option1 = new JButton(obj.getOp1());
            option2 = new JButton(obj.getOp2());
            option3 = new JButton(obj.getOp3());
            option4 = new JButton(obj.getOp4());
            correct_answer = obj.getCorrect_answer();

            JPanel pan = new JPanel();
            pan.setLayout(null);
            pan.setBorder(BorderFactory.createLineBorder(Color.gray));
            pan.setBackground(Color.DARK_GRAY);
            window.setContentPane(pan);
            setLayout(null);
            setBackground(Color.getHSBColor(154, 254, 25));
            setBounds(90, 170, 600, 200);
            setBorder(BorderFactory.createLineBorder(Color.black));
            pan.add(this);

            add(Q);
            add(option1);
            add(option2);
            add(option3);
            add(option4);

            Q.setBounds(110, 8, 400, 50);
            Q.setBorder(new LineBorder(Color.blue, 2, true));
            Q.setHorizontalAlignment(JLabel.CENTER);
            option1.setBounds(90, 70, 200, 40);
            option1.setBackground(new Color(255, 255, 255));
            option2.setBounds(90, 140, 200, 40);
            option2.setBackground(new Color(255, 255, 255));
            option3.setBounds(330, 70, 200, 40);
            option3.setBackground(new Color(255, 255, 255));
            option4.setBounds(330, 140, 200, 40);
            option4.setBackground(new Color(255, 255, 255));
            option1.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
            option2.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
            option3.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
            option4.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

            window.setVisible(true);
        }

    }

    /*
    questions (multiChoice obj,) {

        Q.setText(obj.question);
        this.Q = Q;
        correct_answer = obj.correct_answer ;

    }

     */


    
    void getAnswer (){

            option1.addActionListener((ActionEvent e) -> {
                if (option1.getText().equals(correct_answer)) {
                    score++;
                    next = true;
                } else {
                    option1.setEnabled(false);
                    option1.setBackground(Color.red);
                }
            });

            option2.addActionListener((ActionEvent e) -> {
                if (option2.getText().equals(correct_answer)) {
                    score++;
                    next = true;
                } else {
                    option2.setEnabled(false);
                    option2.setBackground(Color.red);
                }
            });

            option3.addActionListener((ActionEvent e) -> {
                if (option3.getText().equals(correct_answer)) {
                    score++ ;
                    next = true ;
                } else {
                    option3.setEnabled(false);
                    option3.setBackground(Color.red);
                }
            });

            option4.addActionListener((ActionEvent e) -> {
                if (option4.getText().equals(correct_answer)) {
                    score++ ;
                    next = true ;
                } else {
                    option4.setEnabled(false);
                    option4.setBackground(Color.red);
                }
           });

        if (next){
            setVisible(false);
        }

        next = false;


    }
    
    int getScore() { return score ;}
    
    void Reset () {
            score = 0 ;
    
    }
    
}