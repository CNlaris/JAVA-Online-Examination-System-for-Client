package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.*;
import tools.ProcessTools;

import java.io.IOException;
import java.util.ArrayList;

public class StaticPagainationFactory {
    public static ScrollPane createSingleChoiceQuestion(int point, SingleChoiceQuestion singleChoiceQuestion) {
        userClass.answerDatagram = AnswerDatagram.readFromFile();
        ScrollPane scrollPane = new ScrollPane();
        VBox vBox = new VBox(50);
        String tempQuestion = singleChoiceQuestion.getQuestion();
        String question = ProcessTools.separateString(tempQuestion, 30);
        Label questionLabel = new Label(question);
        questionLabel.setFont(new Font(20));
        vBox.getChildren().add(questionLabel);
        ArrayList<String> possibleAnswers = singleChoiceQuestion.getPossibleAnswers();
        ToggleGroup possibleAnswersToggleGroup = new ToggleGroup();
        ArrayList<RadioButton> answerRadioButton = new ArrayList<>();
        for(int i = 0;i < possibleAnswers.size();i ++ ) {
            char option = (char)(65 + i);
            String tempPossibleAnswers = possibleAnswers.get(i);
            String answer = ProcessTools.separateString(tempPossibleAnswers,30);
            RadioButton radioButton = new RadioButton(option + " : " + answer);
            radioButton.setToggleGroup(possibleAnswersToggleGroup);
            answerRadioButton.add(radioButton);
            vBox.getChildren().add(radioButton);
        }
        int temp = userClass.answerDatagram.singleChoiceAnswerSet.get(point).getSelectiveAnswer();
        if(temp != -1) {
            answerRadioButton.get(temp).setSelected(true);
        }
        Button saveButton = new Button("保存");
        vBox.getChildren().add(saveButton);
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int x = -1;
                for(int i = 0;i < answerRadioButton.size();i ++ ) {
                    if(answerRadioButton.get(i).isSelected() == true) {
                        x = i;
                    }
                }
                userClass.answerDatagram.singleChoiceAnswerSet.get(point).setSelectiveAnswer(x);
                LoginController.mainController.stateLabel.setText("第 "+ (point+1) + " 道单选题保存成功！");
                try {
                    AnswerDatagram.writeToFile(userClass.answerDatagram);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        scrollPane.setContent(vBox);
        return scrollPane;
    }

    public static ScrollPane createMultipleChoiceQuestion(int point, MultipleChoiceQuestion multipleChoiceQuestion) {
        userClass.answerDatagram = AnswerDatagram.readFromFile();
        ScrollPane scrollPane = new ScrollPane();
        VBox vBox = new VBox(50);
        String tempQuestion = multipleChoiceQuestion.getQuestion();
        String question = ProcessTools.separateString(tempQuestion, 30);
        Label questionLabel = new Label(question);
        questionLabel.setFont(new Font(20));
        vBox.getChildren().add(questionLabel);
        ArrayList<String> possibleAnswers = multipleChoiceQuestion.getPossibleAnswers();
        ArrayList<CheckBox> answerCheckBox = new ArrayList<>();
        for(int i = 0;i < possibleAnswers.size();i ++ ) {
            char option = (char)(65 + i);
            String tempPossibleAnswers = possibleAnswers.get(i);
            String answer = ProcessTools.separateString(tempPossibleAnswers,30);
            CheckBox checkBox = new CheckBox(option + " : " + answer);
            answerCheckBox.add(checkBox);
            vBox.getChildren().add(checkBox);
        }
        ArrayList<Integer> arrayList = multipleChoiceQuestion.getCorrectAnswer();
        for(int i = 0;i < answerCheckBox.size();i ++ ) {
            boolean flag = false;
            for(int j = 0;j < arrayList.size();j ++ ) {
                if(arrayList.get(j) == i) {
                    flag = true;
                }
            }
            if(flag) {
                answerCheckBox.get(i).setSelected(true);
            }
        }
        Button saveButton = new Button("保存");
        vBox.getChildren().add(saveButton);
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<Integer> arrayList = new ArrayList<>();
                for(int i = 0;i < answerCheckBox.size();i ++ ) {
                    if(answerCheckBox.get(i).isSelected() == true) {
                        arrayList.add(i);
                    }
                }
                userClass.answerDatagram.multipleChoiceAnswerSet.get(point).setSelectiveAnswer(arrayList);
                LoginController.mainController.stateLabel.setText("第 "+ (point+1) + " 道多选题保存成功！");
                try {
                    AnswerDatagram.writeToFile(userClass.answerDatagram);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        scrollPane.setContent(vBox);
        return scrollPane;
    }

    public static ScrollPane createCompletionQuestion(int point, CompletionQuestion completionQuestion) {
        userClass.answerDatagram = AnswerDatagram.readFromFile();
        ScrollPane scrollPane = new ScrollPane();
        VBox vBox = new VBox(50);
        String tempQuestion = completionQuestion.getQuestion();
        String question = ProcessTools.separateString(tempQuestion, 30);
        Label questionLabel = new Label(question);
        questionLabel.setFont(new Font(20));
        vBox.getChildren().add(questionLabel);
        int answerNumber = completionQuestion.getAnswerNumber();
        ArrayList<TextField> answerTextField = new ArrayList<>();
        for(int i = 0;i < answerNumber;i ++ ) {
            HBox hBox = new HBox(5);
            Label answerLabel = new Label("第 "+(i + 1)+"个空 : ");
            hBox.getChildren().add(answerLabel);
            TextField textField = new TextField();
            answerTextField.add(textField);
            hBox.getChildren().add(textField);
            vBox.getChildren().add(hBox);
        }
        if(userClass.answerDatagram.completionAnswerSet.get(point).getAnswers() != null) {
            for(int i = 0;i < userClass.answerDatagram.completionAnswerSet.get(point).getAnswerNumber();i ++ ) {
                answerTextField.get(i).setText(userClass.answerDatagram.completionAnswerSet.get(point).getAnswers().get(i));
            }
        }
        Button saveButton = new Button("保存");
        vBox.getChildren().add(saveButton);
        /*
        for(int i = 0;i < answerTextField.size();i ++ ) {
            answerTextField.get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    for(int i = 0;i < answerNumber;i ++ ) {
                        arrayList.add(answerTextField.get(i).getText());
                    }
                    userClass.answerDatagram.completionAnswerSet.get(point).setAnswers(arrayList);
                }
            });
        }
        scrollPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ArrayList<String> arrayList = new ArrayList<>();
                for(int i = 0;i < answerNumber;i ++ ) {
                    arrayList.add(answerTextField.get(i).getText());
                }
                userClass.answerDatagram.completionAnswerSet.get(point).setAnswers(arrayList);
    }
        });
        scrollPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                ArrayList<String> arrayList = new ArrayList<>();
                for(int i = 0;i < answerNumber;i ++ ) {
                    arrayList.add(answerTextField.get(i).getText());
                }
                userClass.answerDatagram.completionAnswerSet.get(point).setAnswers(arrayList);
            }
        });
         */
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<String> answer = new ArrayList<>();
                for(int i = 0;i < answerNumber; i++) {
                    answer.add(answerTextField.get(i).getText());
                }
                userClass.answerDatagram.completionAnswerSet.get(point).setAnswers(answer);
                LoginController.mainController.stateLabel.setText("第 "+ (point+1) + " 道填空题保存成功！");
                try {
                    AnswerDatagram.writeToFile(userClass.answerDatagram);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        scrollPane.setContent(vBox);
        return scrollPane;
    }

    public static ScrollPane createDiscussionQuestion(int point, DiscussionQuestion discussionQuestion) {
        userClass.answerDatagram = AnswerDatagram.readFromFile();
        ScrollPane scrollPane = new ScrollPane();
        VBox vBox = new VBox(50);
        String tempQuestion = discussionQuestion.getQuestion();
        String question = ProcessTools.separateString(tempQuestion, 30);
        Label questionLabel = new Label(question);
        questionLabel.setFont(new Font(20));
        vBox.getChildren().add(questionLabel);
        TextArea answerTextArea = new TextArea();
        vBox.getChildren().add(answerTextArea);
        Button saveButton = new Button("保存");
        vBox.getChildren().add(saveButton);
        System.out.println(point);
        answerTextArea.setText(userClass.answerDatagram.discussionAnswerSet.get(point).getAnswer());
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                userClass.answerDatagram.discussionAnswerSet.get(point).setAnswer(answerTextArea.getText());
                LoginController.mainController.stateLabel.setText("第 "+ (point+1) + " 道辩论题保存成功！");
                try {
                    AnswerDatagram.writeToFile(userClass.answerDatagram);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        scrollPane.setContent(vBox);
        return scrollPane;
    }
    public static ScrollPane createPage(int pageIndex, Question question,ProblemDatagram problemDatagram) {
        int x1 = 0;
        int x2 = problemDatagram.getSingleChoiceQuestionNumber();
        int x3 = x2 + problemDatagram.getMultipleChoiceQuestionNumber();
        int x4 = x3 + problemDatagram.getCompletionQuestionNumber();
        int x5 = x4 + problemDatagram.getDiscussionQuestionNumber();
        System.out.println(x1+" "+x2+" "+x3+" "+x4+" "+x5);
        int x6 = x5 + problemDatagram.getProgrammingQuestionNumber();
        if(x1 <= pageIndex && pageIndex < x2) {
            return createSingleChoiceQuestion(pageIndex - x1, question.singleChoiceProblemSet.get(pageIndex));
        } else if(x2 <= pageIndex && pageIndex < x3) {
            return createMultipleChoiceQuestion(pageIndex - x2, question.multipleChoiceProblemSet.get(pageIndex - x2));
        } else if(x3 <= pageIndex && pageIndex < x4) {
            return createCompletionQuestion(pageIndex - x3, question.completionQuestionProblemSet.get(pageIndex - x3));
        } else if(x4 <= pageIndex && pageIndex < x5) {
            return createDiscussionQuestion(pageIndex - x4, question.discussionQuestionProblemSet.get(pageIndex - x4));
        }
        else {
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(new Label("null"));
            return scrollPane;
        }
    }
}
