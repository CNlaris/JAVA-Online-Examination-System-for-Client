package model;

import java.io.Serializable;

public class SingleChoiceAnswer implements Serializable {
    private int selectiveAnswer = -1;
    public SingleChoiceAnswer() {}
    public SingleChoiceAnswer(int selectiveAnswer) {
        this.selectiveAnswer = selectiveAnswer;
    }

    public int getSelectiveAnswer() {
        return selectiveAnswer;
    }

    public void setSelectiveAnswer(int selectiveAnswer) {
        this.selectiveAnswer = selectiveAnswer;
    }
}
