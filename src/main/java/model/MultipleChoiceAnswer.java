package model;

import java.io.Serializable;
import java.util.ArrayList;

public class MultipleChoiceAnswer implements Serializable {
    private ArrayList<Integer> selectiveAnswer = new ArrayList<>();
    public MultipleChoiceAnswer() {}
    public MultipleChoiceAnswer(ArrayList<Integer> selectiveAnswer) {
        this.selectiveAnswer = selectiveAnswer;
    }

    public ArrayList<Integer> getSelectiveAnswer() {
        return selectiveAnswer;
    }

    public void setSelectiveAnswer(ArrayList<Integer> selectiveAnswer) {
        this.selectiveAnswer = selectiveAnswer;
    }

}
