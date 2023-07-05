package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class trainingPlatform {
    private JPanel trainingPage;
    private JButton previousButton2;
    private JButton backToHomeButton2;
    private JPanel parentPanel2;
    private JPanel multiChoiceFrame;
    private JLabel questionMultiChoice;

    private JButton option1, option2, option3, option4;
    private JPanel buttonPanel2;
    private JPanel guessnTypingFrame;
    private JLabel questionTyping;
    private JTextField textFieldTyping;
    private JButton proceedTyping;
    private JPanel rememberCheckFrame;
    private JLabel questionRemember;
    private JLabel answerCheck;
    private JButton checkButton;

    String correct_answer;

    public boolean firstTime = true;
    public ArrayList<Integer> questionWordInd = new ArrayList<>();

    public newCollection collectionSelected;

    ArrayList<rememberCheck> qRC = new ArrayList<>(); // create an array
    ArrayList<guessType> qGT = new ArrayList<>(); // create an array
    ArrayList<multiChoice> qMC = new ArrayList<>(); // create an array


    trainingPlatform(newCollection collectionChosen, JFrame window) {
        this.collectionSelected = collectionChosen;

        previousButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setContentPane(appPlatform.appPlat.appCardLayout);
                appPlatform.appPlat.selectedGameTraining = null;
                appPlatform.appPlat.selectedGameTraining = new trainingPlatform(appPlatform.appPlat.collectionListdb.get(appPlatform.appPlat.selectedColInd),window);
            }
        });
        backToHomeButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setContentPane(appPlatform.appPlat.appCardLayout);
                appPlatform.appPlat.getFrame(appPlatform.appPlat.appCardLayout, appPlatform.appPlat.homePanel);
                appPlatform.appPlat.frameStack.clear();
                appPlatform.appPlat.selectedColInd = -1;
                appPlatform.appPlat.selectedWordInd = -1;
                appPlatform.appPlat.selectedGameTraining = null;
            }
        });
    }

    public void remembernCheckGame(JFrame window, boolean reverse) {
        createQuestionsR(qRC, reverse); // add a question
        createQuestionsR(qRC, reverse); // add a question
        getTraining (qRC.get(0), window, reverse); // create the quiz panel

    }

    public void guessnTypeGame(JFrame window, boolean reverse) {
        createQuestionsT(qGT, reverse);
        createQuestionsT(qGT, reverse);
        getTraining (qGT.get(0), window, reverse); // create the quiz panel

    }

    public void multiChoiceGame(JFrame window, boolean reverse) {
        createQuestionsM(qMC, reverse); // add a question in the array
        createQuestionsM(qMC, reverse); // add a question in the array
        getTraining (qMC.get(0), window, reverse); // create the quiz panel with the first question added

    }

    public void getTraining(rememberCheck obj, JFrame window, boolean reverse) {
        getFrame(parentPanel2, rememberCheckFrame);
        questionRemember.setText(obj.question);
        answerCheck.setText(obj.correct_answer);
        answerCheck.setVisible(false);

        window.setContentPane(this.trainingPage);
        window.setVisible(true);

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!answerCheck.isVisible()){
                    answerCheck.setVisible(true);
                    checkButton.setText("Next");

                } else if (answerCheck.isVisible()){
                    answerCheck.setVisible(false);
                    checkButton.setText("Check");
                    change(qRC.get(qRC.size()-1));
                    createQuestionsR(qRC, reverse);
                }

            }
        });

    }

    public void getTraining(guessType obj, JFrame window, boolean reverse) {
        getFrame(parentPanel2,guessnTypingFrame);

        questionTyping.setText(obj.question);
        correct_answer = obj.correct_answer;

        window.setContentPane(this.trainingPage);
        window.setVisible(true);

        proceedTyping.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldTyping.getText().toLowerCase().equals(correct_answer.toLowerCase())) {
                    JOptionPane.showMessageDialog(null, "correct answer!");
                    try {
                        changeFamiliarCount(dequeueQuestionWordInd(),firstTime);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    change(qGT.get(qGT.size()-1));
                    createQuestionsT(qGT, reverse); // add a question in the array
                } else {
                    firstTime = false;
                    if (JOptionPane.showConfirmDialog(null, "Incorrect answer, do you wish to see the answer?", "",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "The correct answer was " + correct_answer);
                        try {
                            changeFamiliarCount(dequeueQuestionWordInd(),firstTime);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        change(qGT.get(qGT.size()-1));
                        createQuestionsT(qGT, reverse); // add a question in the array
                    }
                }
            }
        });

    }

    public void getTraining(multiChoice obj, JFrame window, boolean reverse) {

        questionMultiChoice.setText(obj.question);
        option1.setText(obj.op1);
        option2.setText(obj.op2);
        option3.setText(obj.op3);
        option4.setText(obj.op4);
        correct_answer = obj.correct_answer;

        window.setContentPane(this.trainingPage);

        getFrame(parentPanel2,multiChoiceFrame);

        window.setVisible(true);

        option1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (option1.getText().equals(correct_answer)) {
                    try {
                        changeFamiliarCount(dequeueQuestionWordInd(),firstTime);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    change(qMC.get(qMC.size()-1));
                    createQuestionsM(qMC, reverse); // add a question in the array
                } else {
                    option1.setEnabled(false);
                    firstTime = false;
                }
            }
        });
        option2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (option2.getText().equals(correct_answer)) {
                    try {
                        changeFamiliarCount(dequeueQuestionWordInd(),firstTime);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    change(qMC.get(qMC.size()-1));
                    createQuestionsM(qMC, reverse); // add a question in the array
                } else {
                    option2.setEnabled(false);
                    firstTime = false;
                }
            }
        });
        option3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (option3.getText().equals(correct_answer)) {
                    try {
                        changeFamiliarCount(dequeueQuestionWordInd(),firstTime);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    change(qMC.get(qMC.size()-1));
                    createQuestionsM(qMC, reverse); // add a question in the array
                } else {
                    option3.setEnabled(false);
                    firstTime = false;
                }
            }
        });
        option4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (option4.getText().equals(correct_answer)) {
                    try {
                        changeFamiliarCount(dequeueQuestionWordInd(),firstTime);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    change(qMC.get(qMC.size()-1));
                    createQuestionsM(qMC, reverse); // add a question in the array

                } else {
                    option4.setEnabled(false);
                    firstTime = false;
                }
            }
        });

    }

    public void createQuestionsR(ArrayList<rememberCheck> quiz, boolean reverse) {
        // select a random word from the collection

        int indexChosen = rouletteSelect();
        if (!reverse) {
            String question = collectionSelected.front.get(indexChosen);
            String correctAnswer = collectionSelected.back.get(indexChosen);

            rememberCheck q = new rememberCheck(question, correctAnswer);
            quiz.add(q);
        } else {
            String question = collectionSelected.back.get(indexChosen);
            String correctAnswer = collectionSelected.front.get(indexChosen);

            rememberCheck q = new rememberCheck(question, correctAnswer);
            quiz.add(q);
        }
    }

    public void createQuestionsT(ArrayList<guessType> quiz, boolean reverse) {
        // select a random word from the collection

        int indexChosen = rouletteSelect();
        questionWordInd.add(indexChosen); // the word's index will be recorded for editing
        if (!reverse) {
            String question = collectionSelected.front.get(indexChosen);
            String correctAnswer = collectionSelected.back.get(indexChosen);

            guessType q = new guessType(question, correctAnswer);
            quiz.add(q);
        } else {
            String question = collectionSelected.back.get(indexChosen);
            String correctAnswer = collectionSelected.front.get(indexChosen);

            guessType q = new guessType(question, correctAnswer);
            quiz.add(q);
        }
    }

    public void createQuestionsM(ArrayList<multiChoice> quiz, boolean reverse){
        // select a random word from the collection
        // gets its front(served as the question) and back(served as the correct answer)
        // fill the rest of the alternative answers randomly(must not be the same as the correct answer)

        if (!reverse) {
            int[] listGenerated = generateRandom(collectionSelected.front.size(),4);
            questionWordInd.add(listGenerated[0]); // the word's index will be recorded for editing
            String question = collectionSelected.front.get(listGenerated[0]);
            String correctAnswer = collectionSelected.back.get(listGenerated[0]);
            shuffleArray(listGenerated);
            String[] op = new String[4];

            for (int j = 0; j < listGenerated.length; j++) {
                op[j] = collectionSelected.back.get(listGenerated[j]);
            }
            multiChoice q = new multiChoice(question, op[0], op[1], op[2], op[3], correctAnswer);

            quiz.add(q);

        } else {
            int[] listGenerated = generateRandom(collectionSelected.front.size(),4);
            questionWordInd.add(listGenerated[0]); // the word's index will be recorded for editing
            String question = collectionSelected.back.get(listGenerated[0]);
            String correctAnswer = collectionSelected.front.get(listGenerated[0]);
            shuffleArray(listGenerated);
            String[] op = new String[4];

            for (int j = 0; j < listGenerated.length; j++) {
                op[j] = collectionSelected.front.get(listGenerated[j]);
            }

            multiChoice q = new multiChoice(question, op[0], op[1], op[2], op[3], correctAnswer);

            quiz.add(q);
        }
    }

    public void changeFamiliarCount(int wordIndex, boolean firstTime) throws IOException {
        // edit the word's familiarity(lower = more familiar)
        if (firstTime){
            if(collectionSelected.familiarCount.get(wordIndex) > 1) {
                collectionSelected.editFamiliarCount(wordIndex, -1);
            }
        }
        if(!firstTime) {
            collectionSelected.editFamiliarCount(wordIndex, 1);
        }
    }

    public int rouletteSelect() {
        // roulette wheel selection and returns the index of the word chosen
        // (likely return the index of the most unfamiliar word)
        Random rand = new Random();
        int totalSum = 0;

        for (int i = 0; i < collectionSelected.familiarCount.size(); i++) { // total sum of possible values
            totalSum = totalSum + collectionSelected.familiarCount.get(i);
        }

        int index = rand.nextInt(totalSum);
        int sum = 0;
        int i=0;
        while(sum < index) {
            sum = sum + collectionSelected.familiarCount.get(i++);
        }
        return Math.max(0,i-1);
    }

    int[] generateRandom(int max, int questionsNeeded) { // generate a list of random indexes depending on the
        Random rand = new Random(); //instance of random class

        int[] list = new int[max]; // generate a list of ascending numbers with length "max"
        for (int i = 0; i < list.length; i++) { //fill it up
            list[i] = i;
        }
        shuffleArray(list);

        int chosenIndex = rouletteSelect(); // this would be the index of the word most unfamiliar

        int[] finalList = new int[questionsNeeded]; // Generate an array based on the number of questions Needed
        finalList[0] = chosenIndex; // the first one will be the question asked

        int chosenIndexinlist = -1;
        for (int j = 0; j < list.length; j++) {
            if(list[j] == chosenIndex){
                chosenIndexinlist = j;
                break;
            }
        }
        int[] listwithRemoved = new int[list.length-1];

        for (int i = 0, j = 0; i < list.length; i++) {
            if (i != chosenIndexinlist) {
                listwithRemoved[j++] = list[i];
            }
        }

        int indexRand = rand.nextInt(listwithRemoved.length-(questionsNeeded-2));

        for (int k = indexRand; k < indexRand+(questionsNeeded-1); k++) {
            finalList[k-indexRand+1] = listwithRemoved[k]; // fill the rest of the array with filler questions
        }

        return finalList;
    }

    void change (rememberCheck obj){
        this.questionRemember.setText(obj.question);
        this.answerCheck.setText(obj.correct_answer);
    }

    void change (guessType obj){
        firstTime = true;

        this.questionTyping.setText(obj.question);
        this.textFieldTyping.setText(null);
        this.correct_answer = obj.correct_answer;

    }

    void change (multiChoice obj){
        firstTime = true;

        this.questionMultiChoice.setText(obj.question);
        this.option1.setText(obj.op1);
        this.option2.setText(obj.op2);
        this.option3.setText(obj.op3);
        this.option4.setText(obj.op4);
        this.correct_answer = obj.correct_answer;
        this.option1.setEnabled(true);
        this.option2.setEnabled(true);
        this.option3.setEnabled(true);
        this.option4.setEnabled(true);

    }

    int dequeueQuestionWordInd (){
        int hold = questionWordInd.get(0);
        questionWordInd.remove(0);
        return hold;
    }

    public void getFrame (JPanel parent, JPanel frame){
        parent.removeAll();
        parent.add(frame);
        parent.repaint();
        parent.revalidate();
    }

    static void shuffleArray(int[] arr) { // shuffle array
        Random rnd = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1); // Simple swap
            int a = arr[index];
            arr[index] = arr[i];
            arr[i] = a;
        }
    }

}
