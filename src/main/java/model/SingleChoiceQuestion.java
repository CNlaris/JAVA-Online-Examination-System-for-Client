package model;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.ArrayList;

public class SingleChoiceQuestion implements Serializable {

    private String question;
    private ArrayList<String> possibleAnswers;
    private ArrayList<Image> questionImage;

    private int correctAnswer;

    public SingleChoiceQuestion(String question, ArrayList<String> possibleAnswers, int correctAnswer) {
        this.question = question;
        this.possibleAnswers = possibleAnswers;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<Image> getQuestionImage() {
        return questionImage;
    }

    public void setQuestionImage(ArrayList<Image> questionImage) {
        this.questionImage = questionImage;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(ArrayList<String> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public static boolean isCorrected(int selectedAnswer, int correctAnswer) {
        return selectedAnswer == correctAnswer;
    }
}
