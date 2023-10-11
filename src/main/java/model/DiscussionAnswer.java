package model;

import java.io.Serializable;

public class DiscussionAnswer implements Serializable {
    private String answer = "";
    public DiscussionAnswer() {}
    public DiscussionAnswer(String answer) {
        this.answer = answer;
    }
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
