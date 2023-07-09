package model.trainingMode;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.* ;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

public class multiChoice {
    
    private final String question;
    private final String op1;
    private final String op2;
    private final String op3;
    private final String op4;
    private final String correct_answer;
    
    multiChoice (String question , String op1 , String op2 ,String op3, String op4 , String corrAns) {
        this.question = question ;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3 ;
        this.op4 = op4 ;
        this.correct_answer = corrAns ;
    }

    public String getQ() {return question;}
    public String getOp1() {return op1;}

    public String getOp2() {return op2;}
    public String getOp3() {return op3;}
    public String getOp4() {return op4;}
    public String getCorrect_answer() {return correct_answer;}
}