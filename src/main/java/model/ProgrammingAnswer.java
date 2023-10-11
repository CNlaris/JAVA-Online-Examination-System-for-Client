package model;

import java.io.Serializable;

public class ProgrammingAnswer implements Serializable {
    private String answer = "";

    public ProgrammingAnswer() {}

    public ProgrammingAnswer(String answer) {
        this.answer = answer;
    }
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
