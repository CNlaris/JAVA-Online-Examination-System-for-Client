package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Question implements Serializable {
    private static final int SerialVersionUID = 1;
    public ArrayList<SingleChoiceQuestion> singleChoiceProblemSet;
    public ArrayList<MultipleChoiceQuestion> multipleChoiceProblemSet;
    public ArrayList<CompletionQuestion> completionQuestionProblemSet;
    public ArrayList<DiscussionQuestion> discussionQuestionProblemSet;
    public ArrayList<ProgrammingQuestion> programmingQuestionProblemSet;
    public LocalDateTime startExamLocalDateTime;
    public LocalDateTime endExamLocalDateTime;
    public Question() {
        singleChoiceProblemSet = new ArrayList<>();
        multipleChoiceProblemSet = new ArrayList<>();
        completionQuestionProblemSet = new ArrayList<>();
        discussionQuestionProblemSet = new ArrayList<>();
        programmingQuestionProblemSet = new ArrayList<>();
    }
}
