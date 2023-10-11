package model;

import controller.userClass;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AnswerDatagram implements Serializable {
    public ArrayList<SingleChoiceAnswer> singleChoiceAnswerSet;
    public ArrayList<MultipleChoiceAnswer> multipleChoiceAnswerSet;
    public ArrayList<CompletionAnswer> completionAnswerSet;
    public ArrayList<DiscussionAnswer> discussionAnswerSet;
    public ArrayList<ProgrammingAnswer> programmingAnswerSet;
    public AnswerDatagram() {
        singleChoiceAnswerSet = new ArrayList<>();
        multipleChoiceAnswerSet = new ArrayList<>();
        completionAnswerSet = new ArrayList<>();
        discussionAnswerSet = new ArrayList<>();
        programmingAnswerSet = new ArrayList<>();
    }
    public AnswerDatagram(Question question) {
        int singleChoiceAnswerSize = question.singleChoiceProblemSet.size();
        int multipleChoiceAnswerSize = question.multipleChoiceProblemSet.size();
        int completionAnswerSize = question.completionQuestionProblemSet.size();
        int discussionAnswerSize = question.discussionQuestionProblemSet.size();
        int programmingAnswerSize = question.programmingQuestionProblemSet.size();
        singleChoiceAnswerSet = new ArrayList<>();
        multipleChoiceAnswerSet = new ArrayList<>();
        completionAnswerSet = new ArrayList<>();
        discussionAnswerSet = new ArrayList<>();
        programmingAnswerSet = new ArrayList<>();
        for(int i = 0;i < singleChoiceAnswerSize;i ++ ) {
            singleChoiceAnswerSet.add(new SingleChoiceAnswer());
        }
        for(int i = 0;i < multipleChoiceAnswerSize ;i ++ ) {
            multipleChoiceAnswerSet.add(new MultipleChoiceAnswer());
        }
        for(int i = 0;i < completionAnswerSize;i ++ ) {
            completionAnswerSet.add(new CompletionAnswer(question.completionQuestionProblemSet.get(i).getAnswerNumber()));
        }
        for(int i = 0;i < discussionAnswerSize;i ++ ) {
            discussionAnswerSet.add(new DiscussionAnswer());
        }
        for(int i = 0;i < programmingAnswerSize;i ++ ) {
            programmingAnswerSet.add(new ProgrammingAnswer());
        }
    }
    public static AnswerDatagram readFromFile() {
        AnswerDatagram answerDatagram = new AnswerDatagram(userClass.question);
        LocalDateTime localDateTime = userClass.question.startExamLocalDateTime;
        String str = localDateTime.toString();
        String strPath = "cache/" + str +"exam.txt";
        File file = new File(strPath);
        if(!file.exists()) {
            return answerDatagram;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            answerDatagram = (AnswerDatagram)objectInputStream.readObject();
        } catch(IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return answerDatagram;
    }
    public static void writeToFile(AnswerDatagram answerDatagram) throws IOException {
        LocalDateTime localDateTime = userClass.question.startExamLocalDateTime;
        String str = localDateTime.toString();
        String strPath = "cache/" + str +"exam.txt";
        File file = new File(strPath);
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        if(!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(answerDatagram);
    }
}
