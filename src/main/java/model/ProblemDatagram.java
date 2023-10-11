package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ProblemDatagram implements Serializable {
    private LocalDateTime examStartTime;
    private LocalDateTime examEndTime;
    private int singleChoiceQuestionNumber;
    private int multipleChoiceQuestionNumber;
    private int discussionQuestionNumber;
    private int completionQuestionNumber;
    private int programmingQuestionNumber;

    public ProblemDatagram() {}
    public ProblemDatagram(int singleChoiceQuestionNumber, int multipleChoiceQuestionNumber, int completionQuestionNumber
            , int discussionQuestionNumber, int programmingQuestionNumber, LocalDateTime examStartTime
            ,LocalDateTime examEndTime) {
        this.singleChoiceQuestionNumber = singleChoiceQuestionNumber;
        this.multipleChoiceQuestionNumber = multipleChoiceQuestionNumber;
        this.completionQuestionNumber = completionQuestionNumber;
        this.discussionQuestionNumber = discussionQuestionNumber;
        this.programmingQuestionNumber = programmingQuestionNumber;
        this.examStartTime = examStartTime;
        this.examEndTime = examEndTime;
    }

    public int getCompletionQuestionNumber() {
        return completionQuestionNumber;
    }

    public void setCompletionQuestionNumber(int completionQuestionNumber) {
        this.completionQuestionNumber = completionQuestionNumber;
    }

    public int getDiscussionQuestionNumber() {
        return discussionQuestionNumber;
    }

    public void setDiscussionQuestionNumber(int discussionQuestionNumber) {
        this.discussionQuestionNumber = discussionQuestionNumber;
    }

    public LocalDateTime getExamEndTime() {
        return examEndTime;
    }

    public void setExamEndTime(LocalDateTime examEndTime) {
        this.examEndTime = examEndTime;
    }

    public LocalDateTime getExamStartTime() {
        return examStartTime;
    }

    public void setExamStartTime(LocalDateTime examStartTime) {
        this.examStartTime = examStartTime;
    }

    public int getMultipleChoiceQuestionNumber() {
        return multipleChoiceQuestionNumber;
    }

    public void setMultipleChoiceQuestionNumber(int multipleChoiceQuestionNumber) {
        this.multipleChoiceQuestionNumber = multipleChoiceQuestionNumber;
    }

    public int getProgrammingQuestionNumber() {
        return programmingQuestionNumber;
    }

    public void setProgrammingQuestionNumber(int programmingQuestionNumber) {
        this.programmingQuestionNumber = programmingQuestionNumber;
    }

    public int getSingleChoiceQuestionNumber() {
        return singleChoiceQuestionNumber;
    }

    public void setSingleChoiceQuestionNumber(int singleChoiceQuestionNumber) {
        this.singleChoiceQuestionNumber = singleChoiceQuestionNumber;
    }

    public int getTotalNumber() {
        int totalNumber = singleChoiceQuestionNumber + multipleChoiceQuestionNumber + discussionQuestionNumber
                +completionQuestionNumber + programmingQuestionNumber;
        return totalNumber;
    }
}
