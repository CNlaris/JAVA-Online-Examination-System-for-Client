package model;

import java.io.Serializable;

public class CompletionQuestion implements Serializable {
    private String question;
    private int answerNumber;



    public CompletionQuestion(String question, int answerNumber) {
        this.question = question;
        this.answerNumber = answerNumber;
    }



    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(int answerNumber) {
        this.answerNumber = answerNumber;
    }
}
