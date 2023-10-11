package model;

import java.io.Serializable;

public class DiscussionQuestion implements Serializable {
    private String question;

    public DiscussionQuestion(String question) {
        this.question = question;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
