package model;

import java.io.Serializable;

public class ProgrammingQuestion implements Serializable {
    private String question;


    public ProgrammingQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
