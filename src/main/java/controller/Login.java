package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Student;

public class Login extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*userclass.problemdatagram = new problemdatagram(1,1,1,1,0, localdatetime.of(2021,6,27,18,17,0),localdatetime.of(2021,6,29,18,30,0));
        userclass.question = new question();
        arraylist<string> answers = new arraylist<>();
        answers.add("航哥");
        answers.add("a");
        answers.add("c");
        answers.add("d");
        arraylist<integer> arraylist =new arraylist<>();
        arraylist.add(1);
        arraylist.add(2);
        arraylist.add(3);
        arraylist.add(4);
        singlechoicequestion singlechoicequestion = new singlechoicequestion("谁是最帅的男人？", answers, 1);
        multiplechoicequestion multiplechoicequestion = new multiplechoicequestion("谁是世界上最善良的人？", answers, arraylist);
        completionquestion completionquestion = new completionquestion("请写出东北电力大学的校训",4);
        discussionquestion discussionquestion = new discussionquestion("请写出计算机学院漂亮小姑娘的微信号");
        userclass.question.singlechoiceproblemset.add(singlechoicequestion);
        userclass.question.multiplechoiceproblemset.add(multiplechoicequestion);
        userclass.question.completionquestionproblemset.add(completionquestion);
        userclass.question.discussionquestionproblemset.add(discussionquestion);*/
        userClass.student = new Student();
        //userClass.answerDatagram  = new AnswerDatagram(userClass.question);
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        primaryStage.setTitle("登录系统");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
