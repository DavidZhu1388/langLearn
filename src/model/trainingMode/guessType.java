package model.trainingMode;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.* ;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

public class guessType {
    
    private final String question , correct_answer;
    
    guessType (String question, String corrAns) {
        this.question = question ;
        this.correct_answer = corrAns ;
    }

    public String getQ(){ return question;}
    public String getCorrect_answer(){ return correct_answer;}
}