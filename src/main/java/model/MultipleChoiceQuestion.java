package model;

import javafx.scene.image.Image;
import tools.ProcessTools;

import java.io.Serializable;
import java.util.ArrayList;

public class MultipleChoiceQuestion implements Serializable {
    private String question;
    private ArrayList<String> possibleAnswers;
    private ArrayList<Image> questionImage;
    private ArrayList<Integer> correctAnswer;


    public MultipleChoiceQuestion(String question, ArrayList<String> possibleAnswers,
                                  ArrayList<Integer> correctAnswer) {
        this.question = question;
        this.possibleAnswers = possibleAnswers;
        this.correctAnswer = correctAnswer;
    }

    public ArrayList<Integer> getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(ArrayList<Integer> correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public ArrayList<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(ArrayList<String> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<Image> getQuestionImage() {
        return questionImage;
    }

    public void setQuestionImage(ArrayList<Image> questionImage) {
        this.questionImage = questionImage;
    }


    //isStrict表示判题时是否严格执行
    public double isCorrect(boolean isStrict, ArrayList<Integer> selectiveAnswer) {
        if(selectiveAnswer == null) {
            return 0.0;
        }
        int totalAnswerSize = this.correctAnswer.size();
        int correctAnswerSize = ProcessTools.sameNumberCount(selectiveAnswer, this.correctAnswer);
        if(isStrict == true ) {
            if(totalAnswerSize != correctAnswerSize) {
                return 0.0;
            }
            else {
                return 1.0;
            }
        }
        double correct = correctAnswerSize / totalAnswerSize;
        return correct;
    }
}
