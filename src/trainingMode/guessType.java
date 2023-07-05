package trainingMode;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.* ;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

public class guessType {
    
    String question , correct_answer;
    
    guessType (String question, String corrAns) {
        this.question = question ;
        this.correct_answer = corrAns ;
    }
}