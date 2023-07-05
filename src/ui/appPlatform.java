package ui;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class appPlatform {
    JPanel appCardLayout;
    JPanel homePanel;
    private JPanel mainPage;
    private JLabel welcome;
    private JButton wordCollectionsButton;
    private JButton trainingModeButton;
    private JButton exitButton;
    private JPanel collectionPage;
    private JPanel buttonPanel1;
    private JButton previousButton;
    private JButton backToHomeButton;
    private JPanel parentPanel;
    private JPanel frame2;
    private JButton createButtonFrame2;
    private JButton viewEditButtonFrame2;
    private JPanel frame4;
    private JPanel buttonPanel2;
    private JPanel parentPanel2;
    private JPanel trainingPage;
    private JButton backToHomeButton2;
    private JButton previousButton2;
    private JButton newCollectionButtonFrame4;
    private JList collectionSelectionlistFrame4;
    private JButton proceedWithTheSelectedCollectionButtonFrame4;
    private JPanel frame5;
    private JTextField textFieldNewCollectionFrame5;
    private JButton proceedButtonFrame5;
    private JPanel frame6;
    private JTextField textFieldFrontCreation;
    private JTextField textFieldBackCreation;
    private JButton proceedButtonFrame6;
    private JPanel successCreateFrame;
    private JPanel frame7;
    private JButton proceedWithTheSelectedCollectionButtonFrame7;
    private JList existentCollections;
    private JPanel frame8;
    private JPanel viewPanel;
    private JLabel collectionNameGUI;
    private JPanel editPanel;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JButton editButtonViewPanel;
    private JList collectionSpecific;
    private JButton proceedWithTheSelectedWordButtonFrame8;
    private JButton editFinishButtonEditPanel;
    private JTextField textFieldFrontEdit;
    private JTextField textFieldBackEdit;
    private JPanel successEditFrame;
    private JPanel frame3;
    private JPanel proceedColTrainingButton;
    private JList trainingList;
    private JButton editcollectionNameButtonframe8;
    private JPanel editColNamePanel;
    private JTextField textFieldEditColNamePanel;
    private JButton proceedEditColNamePanel;
    private JButton deleteWordButtonViewPanel;
    private JButton deleteCollectionFrame8;
    private JButton proceedWithTheSelectedCollectionButtonFrame3;
    private JButton guessAndTypeButton;
    private JButton multiChoiceButton;
    private JButton remembernCheckButton;
    private JButton remembernCheckReversedButton;
    private JButton multiChoiceReversedButton;
    private JButton guessAndTypeReversedButton;
    private JPanel frame10;

    DefaultListModel dlm = new DefaultListModel();

    static collectionData database = new collectionData();
    ArrayList <newCollection> collectionListdb = database.listCollections();

    ArrayList <JPanel> frameStack = new ArrayList<>();

    public int selectedColInd = -1;
    public int selectedWordInd = -1;
    public trainingPlatform selectedGameTraining;

    public static appPlatform appPlat;

    public static void main(String[] args) {
        database.collectionCollect();
        JFrame window = new JFrame("LangLearn");
        window.setSize(800,500);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        appPlat =  new appPlatform(window);

        window.setContentPane(appPlat.appCardLayout);

        window.pack();
        window.setVisible(true);

    }

    public appPlatform(JFrame frame) {
            //================================Creation Mode===========================================//
            //--------------------------------Home Panel----------------------------------------//
            wordCollectionsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) { // if the "Word collection" button gets clicked on
                    getFrame(appCardLayout,collectionPage);
                    nextFrameStack(parentPanel,frame2);

                }
            });
            trainingModeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) { // if the "Training mode" button gets clicked on
                    getFrame(appCardLayout,trainingPage);
                    nextFrameStack(parentPanel2,frame3);
                    dlm.clear();
                    trainingList.setModel(dlm);
                    for (int i = 0; i < collectionListdb.size(); i++) {
                        dlm.addElement(collectionListdb.get(i).collectionName);
                    }
                }
            });
            exitButton.addActionListener(new ActionListener() { // if the "exit" button gets clicked on
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.exit(0);
                }
            });
            //--------------------------------Home Panel----------------------------------------//

            //--------------------------------Button Panel----------------------------------------//
            previousButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (frameStack.size() == 1) { // the only panel existent is panel2 -> functions as a home button
                        getFrame(appCardLayout, homePanel);
                        frameStack.clear();
                        selectedColInd = -1;
                        selectedWordInd = -1;
                        selectedGameTraining = null;

                    } else if (frameStack.get(frameStack.size()-1).equals(frame8)) { // if the current panel panel8(needs to reset the list)
                        frameStack.remove(frameStack.size() - 1);
                        getFrame(parentPanel,frame7);
                        dlm.clear();
                        existentCollections.setModel(dlm);
                        for (int i = 0; i < collectionListdb.size(); i++) {
                            dlm.addElement(collectionListdb.get(i).collectionName);
                        }
                        selectedColInd = -1;
                        selectedWordInd = -1;
                        selectedGameTraining = null;

                    } else if (frameStack.get(frameStack.size()-1).equals(viewPanel)) { // if the current panel viewPanel(needs to reset the list)
                    frameStack.remove(frameStack.size() - 1);
                    getFrame(parentPanel,frame8);
                    textArea1.setText(null);
                    textArea2.setText(null);
                    selectedWordInd = -1;

                    } else if (frameStack.get(frameStack.size()-1).equals(editPanel)) { // if the current panel editPanel(no change should occur)
                        frameStack.remove(frameStack.size() - 1);
                        getFrame(parentPanel,viewPanel);

                    } else {
                        getFrame(parentPanel, frameStack.get(frameStack.size() - 2));
                        frameStack.remove(frameStack.size() - 1);
                        selectedColInd = -1;
                        selectedWordInd = -1;
                        selectedGameTraining = null;
                    }
                }
            });
            previousButton2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (frameStack.size() == 1) { // the only panel existent is panel3 -> functions as a home button
                        getFrame(appCardLayout, homePanel);
                        frameStack.clear();
                        selectedColInd = -1;
                        selectedWordInd = -1;

                    } else if (frameStack.size() == 2) {
                        getFrame(parentPanel2, frame3);
                        frameStack.remove(frameStack.size() - 1);
                        dlm.clear();
                        trainingList.setModel(dlm);
                        for (int i = 0; i < collectionListdb.size(); i++) {
                            dlm.addElement(collectionListdb.get(i).collectionName);
                        }
                        selectedColInd = -1;
                        selectedWordInd = -1;

                    } else {
                        getFrame(parentPanel2, frameStack.get(frameStack.size() - 2));
                        frameStack.remove(frameStack.size() - 1);
                        selectedColInd = -1;
                        selectedWordInd = -1;
                    }
                }
            });
            backToHomeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    getFrame(appCardLayout, homePanel);
                    frameStack.clear();
                    selectedColInd = -1;
                    selectedWordInd = -1;
                }
            });
            backToHomeButton2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    getFrame(appCardLayout, homePanel);
                    frameStack.clear();
                    selectedColInd = -1;
                    selectedWordInd = -1;
                }
            });
            //--------------------------------Button Panel----------------------------------------//

            //--------------------------------Panel 2----------------------------------------//
            createButtonFrame2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nextFrameStack(parentPanel,frame4);
                    dlm.clear();
                    collectionSelectionlistFrame4.setModel(dlm);
                    for (int i = 0; i < collectionListdb.size(); i++) {
                        dlm.addElement(collectionListdb.get(i).collectionName);
                    }

                }
            });
            viewEditButtonFrame2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nextFrameStack(parentPanel,frame7);
                    dlm.clear();
                    existentCollections.setModel(dlm);
                    for (int i = 0; i < collectionListdb.size(); i++) {
                        dlm.addElement(collectionListdb.get(i).collectionName);
                    }

                }
            });
            proceedWithTheSelectedCollectionButtonFrame4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!collectionSelectionlistFrame4.isSelectionEmpty()) {
                        nextFrameStack(parentPanel, frame6);
                        selectedColInd = collectionSelectionlistFrame4.getSelectedIndex();
                    } else {
                        JOptionPane.showMessageDialog(null, "No collection was selected");
                    }
                }
            });
            //--------------------------------Panel 2----------------------------------------//

            //--------------------------------Panel 4----------------------------------------//
            newCollectionButtonFrame4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nextFrameStack(parentPanel, frame5);
                }
            });
            //--------------------------------Panel 4----------------------------------------//

            //--------------------------------Panel 5----------------------------------------//
            proceedButtonFrame5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (textFieldNewCollectionFrame5.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "The field cannot be left blank. You must enter in a name");
                    } else {
                        nextFrameStack(parentPanel, frame6);
                    }
                }
            });
            //--------------------------------Panel 5----------------------------------------//

            //--------------------------------Panel 6----------------------------------------//
            proceedButtonFrame6.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (textFieldFrontCreation.getText().isEmpty() || textFieldBackCreation.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Both text fields must be filled to proceed.");
                    } else {
                        if (selectedColInd >= 0) { // creating a word inside an existing collection
                            collectionListdb.get(selectedColInd).addCollectionWord(textFieldFrontCreation.getText(), textFieldBackCreation.getText());
                            showSuccess("Word was created with success", parentPanel, frame2);
                            refreshDatabase();
                        } else { // creating a word inside a new collection (create collection and put the word inside)
                            database.addCollection(textFieldNewCollectionFrame5.getText(),textFieldFrontCreation.getText(),textFieldBackCreation.getText());
                            showSuccess("Word was created with success", parentPanel, frame2);
                            refreshDatabase();
                        }
                        textFieldFrontCreation.setText(null);
                        textFieldBackCreation.setText(null);
                        textFieldNewCollectionFrame5.setText(null);
                    }
                }
            });
            //--------------------------------Panel 6----------------------------------------//

            //--------------------------------Panel 7----------------------------------------//
            proceedWithTheSelectedCollectionButtonFrame7.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!existentCollections.isSelectionEmpty()) {
                        nextFrameStack(parentPanel, frame8);
                        selectedColInd = existentCollections.getSelectedIndex();
                        collectionNameGUI.setText(collectionListdb.get(selectedColInd).collectionName);

                        newCollection collectionSelected = collectionListdb.get(selectedColInd);
                        dlm.clear();
                        collectionSpecific.setModel(dlm);
                        for (int i = 0; i < collectionSelected.front.size(); i++) {
                            dlm.addElement(collectionSelected.front.get(i));
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "No collection was selected");
                    }
                }
            });
            //--------------------------------Panel 7----------------------------------------//


            //--------------------------------Panel 8----------------------------------------//
            proceedWithTheSelectedWordButtonFrame8.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!collectionSpecific.isSelectionEmpty()) {
                        nextFrameStack(parentPanel, viewPanel);
                        selectedWordInd = collectionSpecific.getSelectedIndex();
                        textArea1.setText(collectionListdb.get(selectedColInd).front.get(selectedWordInd));
                        textArea2.setText(collectionListdb.get(selectedColInd).back.get(selectedWordInd));

                    } else {
                        JOptionPane.showMessageDialog(null, "No word was selected");
                    }
                }
            });

            editcollectionNameButtonframe8.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nextFrameStack(parentPanel,editColNamePanel);
                }
            });

            deleteCollectionFrame8.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (JOptionPane.showConfirmDialog(null,
                            "Do you want to delete this collection?", "",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        database.deleteCollection(selectedColInd);
                        showSuccess("Collection was deleted with success", parentPanel, frame2);
                        refreshDatabase();
                    }
                }
            });
            //--------------------------------Panel 8----------------------------------------//


            //--------------------------------View Panel----------------------------------------//
            editButtonViewPanel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nextFrameStack(parentPanel, editPanel);
                }
            });

            deleteWordButtonViewPanel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (JOptionPane.showConfirmDialog(null, "Do you want to delete this word?", "",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        try {
                            collectionListdb.get(selectedColInd).deleteCollectionWord(selectedWordInd);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        showSuccess("Word was deleted with success", parentPanel, frame2);
                        refreshDatabase();
                    }
                }
            });
            //--------------------------------View Panel----------------------------------------//

            //--------------------------------Edit Panel----------------------------------------//
            editFinishButtonEditPanel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (textFieldFrontEdit.getText().isEmpty() && textFieldBackEdit.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Either text fields must be filled to proceed.");
                    } else {
                        if (selectedWordInd >= 0) {
                            if (!textFieldFrontEdit.getText().isEmpty() && !textFieldBackEdit.getText().isEmpty()) {
                                try {
                                    collectionListdb.get(selectedColInd).editCollectionWord(selectedWordInd,
                                            textFieldFrontEdit.getText(), textFieldBackEdit.getText());
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                            } else if (!textFieldFrontEdit.getText().isEmpty() && textFieldBackEdit.getText().isEmpty()){
                                try {
                                    collectionListdb.get(selectedColInd).editCollectionWord(selectedWordInd,
                                            textFieldFrontEdit.getText(), collectionListdb.get(selectedColInd).back.get(selectedWordInd));
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                            } else if (textFieldFrontEdit.getText().isEmpty() && !textFieldBackEdit.getText().isEmpty()){
                                try {
                                    collectionListdb.get(selectedColInd).editCollectionWord(selectedWordInd,
                                            collectionListdb.get(selectedColInd).front.get(selectedWordInd), textFieldBackEdit.getText());
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                            }
                            showSuccess("Word was edited with success",parentPanel, frame2);
                        }
                        refreshDatabase();
                        textFieldFrontEdit.setText(null);
                        textFieldBackEdit.setText(null);
                    }
                }
            });
            //--------------------------------Edit Panel----------------------------------------//

            //--------------------------------Edit CollectionName Frame----------------------------------------//
            proceedEditColNamePanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldEditColNamePanel.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "The field cannot be left blank. You must enter in a name");
                } else {
                    boolean nameChanged = false;
                    while (!nameChanged) {
                        nameChanged = collectionListdb.get(selectedColInd).editCollectionName(textFieldEditColNamePanel.getText());
                    }
                    showSuccess("Collection's name was edited with success",parentPanel, frame2);
                }
                refreshDatabase();
            }
             });
            //--------------------------------Edit CollectionName Frame----------------------------------------//


            //================================Creation Mode===========================================//


            //================================Training Mode===========================================//


            //--------------------------------Panel 3----------------------------------------//
            proceedWithTheSelectedCollectionButtonFrame3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!trainingList.isSelectionEmpty()) {
                        nextFrameStack(parentPanel2, frame10);
                        selectedColInd = trainingList.getSelectedIndex();
                        selectedGameTraining = new trainingPlatform(collectionListdb.get(selectedColInd),frame);

                    } else {
                        JOptionPane.showMessageDialog(null, "No collection was selected");
                    }
                }
             });
            //--------------------------------Panel 3----------------------------------------//

            //--------------------------------Panel 10----------------------------------------//

            remembernCheckButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        selectedGameTraining.remembernCheckGame(frame,false);
                }
            });
            guessAndTypeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedGameTraining.guessnTypeGame(frame,false);
                }
            });
            multiChoiceButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(collectionListdb.get(selectedColInd).front.size() >= 4) {
                        selectedGameTraining.multiChoiceGame(frame,false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Collection not big enough for this training type (need 4 words minimum)");
                    }
                }
            });

            remembernCheckReversedButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedGameTraining.remembernCheckGame(frame,true);
                }
            });
            guessAndTypeReversedButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedGameTraining.guessnTypeGame(frame,true);
                }
            });
            multiChoiceReversedButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(collectionListdb.get(selectedColInd).front.size() >= 4) {
                        selectedGameTraining.multiChoiceGame(frame,true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Collection not big enough for this training type (need 4 words minimum)");
                    }
                }
            });

            //--------------------------------Panel 10----------------------------------------//


        //================================Training Mode===========================================//

    }


public void nextFrameStack (JPanel parent, JPanel frame){
    parent.removeAll();
    parent.add(frame);
    parent.repaint();
    parent.revalidate();
    frameStack.add(frame);

}

public void getFrame (JPanel parent, JPanel frame){
        parent.removeAll();
        parent.add(frame);
        parent.repaint();
        parent.revalidate();
    }

    public void refreshDatabase(){ // insert the new database into the program
        database.collectionCollect();
        collectionListdb.clear();
        collectionListdb = database.listCollections();
    }

    public void showSuccess (String congrat, JPanel parent, JPanel frame) {
        JOptionPane.showMessageDialog(null, congrat);
        frameStack.clear();
        nextFrameStack(parent, frame);
        refreshDatabase();
    }
}

