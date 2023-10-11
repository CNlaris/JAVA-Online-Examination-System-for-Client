package model;

import java.io.Serializable;
import java.util.ArrayList;

public class CompletionAnswer implements Serializable {
    private ArrayList<String> answers = new ArrayList<>();
    private int answerNumber = 0;
    public CompletionAnswer(int answerNumber) {
        this.answerNumber = answerNumber;
        answers = new ArrayList<>();
        for(int i = 0;i < answerNumber;i ++ ) {
            answers.add("");
        }
    }
    public int getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(int answerNumber) {
        this.answerNumber = answerNumber;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }
}
