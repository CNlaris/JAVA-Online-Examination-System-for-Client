package tools;

import controller.LoginController;
import controller.userClass;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.AnswerDatagram;
import model.ProblemDatagram;
import model.Question;

import java.io.*;
import java.net.Socket;

public class StudentSocket extends Thread {
    public Socket socket;
    public InputStream inputStream;
    public OutputStream outputStream;

    public void run() {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        String flag = "";
        while(true) {
            try {
                try {
                    flag = dataInputStream.readUTF();
                } catch(Exception e) {
                    Platform.runLater(()->{
                        LoginController.mainController.setConnectClosedAlert(new ActionEvent());
                    });
                    break;
                }
                if (flag.equals("examStart")) {
                    try {
                        System.out.println("已成功接收！");
                        userClass.isExamed = true;
                        userClass.isExamEnded = false;
                        ObjectInputStream objectInputStream1 = new ObjectInputStream(inputStream);
                        //ObjectInputStream objectInputStream2 = new ObjectInputStream(inputStream);
                        //ProblemDatagram p = (ProblemDatagram) objectInputStream1.readObject();
                        //userClass.problemDatagram = p;
                        userClass.question = (Question) objectInputStream1.readObject();
                        userClass.problemDatagram = new ProblemDatagram(userClass.question.singleChoiceProblemSet.size()
                        , userClass.question.multipleChoiceProblemSet.size()
                        , userClass.question.completionQuestionProblemSet.size()
                        ,userClass.question.discussionQuestionProblemSet.size()
                        ,userClass.question.programmingQuestionProblemSet.size()
                        ,userClass.question.startExamLocalDateTime
                        ,userClass.question.endExamLocalDateTime);
                        userClass.answerDatagram = new AnswerDatagram(userClass.question);
                        System.out.println(userClass.question == null);
                        System.out.println(LoginController.mainController == null);
                        LoginController.mainController.examinationNoticeOnAction(new ActionEvent());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InvalidClassException e) {
                        e.printStackTrace();
                    }
                }
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void sendAnswerDatagram(AnswerDatagram answerDatagram) throws IOException,ClassNotFoundException {
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF("flim");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(answerDatagram);
            //System.out.println(answerDatagram == null);
            System.out.println(answerDatagram.singleChoiceAnswerSet == null);
            System.out.println(answerDatagram.multipleChoiceAnswerSet == null);
            System.out.println(answerDatagram.completionAnswerSet == null);
            System.out.println(answerDatagram.discussionAnswerSet == null);
    }
}
