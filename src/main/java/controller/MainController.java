package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.ProblemDatagram;
import model.Question;
import tools.StudentSocket;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Label userNameAndNumberLabel;

    @FXML
    private Pagination problemSetPagination;

    @FXML
    private Button exitButton;

    @FXML
    public Label stateLabel;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Label timeLabel;

    @FXML
    private Label courseNameLabel;

    @FXML
    private Button testButton;

    @FXML
    private Label remindLabel;

    @FXML
    private Label examinationTimeLabel;

    @FXML
    private Button test2;
    @FXML
    private Label examEndTimeRemindLabel;

    @FXML
    public Button fileButton;

    public Question question;

    public StudentSocket studentSocket;

    @FXML
    void exitAction(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String courseName = userClass.student.getCourseName();
        courseNameLabel.setText(courseName);
        stateLabel.setText("就绪");
        remindLabel.setText("老师现在没有组织考试，请等待");
        studentSocket = new StudentSocket();
        studentSocket.socket = userClass.socket;
        try {
            studentSocket.inputStream = userClass.socket.getInputStream();
            studentSocket.outputStream = userClass.socket.getOutputStream();
        } catch(IOException e) {
            e.printStackTrace();
        }
        studentSocket.start();
        StringBuffer userNameAndNumberBuffer = new StringBuffer(userClass.student.getUserName());
        userNameAndNumberBuffer.append("-");
        userNameAndNumberBuffer.append(userClass.student.getUserNumber());
        String userNameAndNumber = userNameAndNumberBuffer.toString();
        userNameAndNumberLabel.setText(userNameAndNumber);
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            int tempSecond = LocalDateTime.now().getSecond();
            int tempMinute = LocalDateTime.now().getMinute();
            int tempHour = LocalDateTime.now().getHour();
            int year = LocalDateTime.now().getYear();
            int month = LocalDateTime.now().getMonthValue();
            int day = LocalDateTime.now().getDayOfMonth();
            String second = (tempSecond < 10 ? "0":"") + String.valueOf(tempSecond);
            String minute = (tempMinute < 10 ? "0":"") + String.valueOf(tempMinute);
            String hour = (tempHour < 10 ? "0":"") + String.valueOf(tempHour);
            timeLabel.setText((year) + "年" + month + "月" +(day) + "日 " + hour + ":" + minute + ":" + second);
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }
    public void testButtonOnAction(ActionEvent event) {
        if (problemSetPagination.isVisible() == true) {
            remindLabel.setVisible(true);
            examinationTimeLabel.setVisible(true);
            problemSetPagination.setVisible(false);
        }
        else {
            remindLabel.setVisible(false);
            examinationTimeLabel.setVisible(false);
            problemSetPagination.setVisible(true);
        }
    }
    public void examinationNoticeOnAction(ActionEvent event) {
        problemDatagramReceivedOnAction(userClass.problemDatagram);
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            if(userClass.isExamEnded == false)
            remindLabel.setText("老师已发布考试");
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }
    public void problemDatagramReceivedOnAction(ProblemDatagram problemDatagram) {
        LocalDateTime examStartTime = problemDatagram.getExamStartTime();
        LocalDateTime examEndTime = problemDatagram.getExamEndTime();
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            java.time.Duration duration = java.time.Duration.between(LocalDateTime.now(), examStartTime);
            long tempSecond = duration.getSeconds();
            long s = tempSecond % 60;
            //获取分钟数
            long m = tempSecond / 60 % 60;
            //获取小时数
            long h = tempSecond / 60 / 60 % 24;
            //获取天数
            long d = tempSecond / 60 / 60 / 24;
            String second = (s < 10 ? "0":"") + String.valueOf(s);
            String minute = (m < 10 ? "0":"") + String.valueOf(m);
            examinationTimeLabel.setText((d) + " 天 "+(h) + " 小时 " + (m) + " 分钟 " + (s) + " 秒");
        }),
                new KeyFrame(Duration.seconds(1))
        );
        java.time.Duration duration = java.time.Duration.between(LocalDateTime.now(), examStartTime);
        long tempSecond = duration.getSeconds();
        int temp = (int)tempSecond;
        clock.setCycleCount(temp);
        clock.play();
        clock.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                testButtonOnAction(new ActionEvent());
                problemSetPaginationOnAction(userClass.problemDatagram.getTotalNumber(), userClass.question);
                examEndTimeRemindLabelOnAction(userClass.problemDatagram);
            }
        });
    }
    public void exitButtonOnAction(ActionEvent event) throws IOException {
        System.exit(0);

    }

    public void examEndTimeRemindLabelOnAction(ProblemDatagram problemDatagram) {
        LocalDateTime examEndTime = problemDatagram.getExamEndTime();
        examEndTimeRemindLabel.setVisible(true);
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            java.time.Duration duration = java.time.Duration.between(LocalDateTime.now(), examEndTime);
            long tempSecond = duration.getSeconds();
            long s = tempSecond % 60;
            //获取分钟数
            long m = tempSecond / 60 % 60;
            //获取小时数
            long h = tempSecond / 60 / 60 % 24;
            //获取天数
            String second = (s < 10 ? "0":"") + String.valueOf(s);
            String minute = (m < 10 ? "0":"") + String.valueOf(m);
            examEndTimeRemindLabel.setText("距离考试结束还有" + (h) + " 小时 " + (m) + " 分钟 " + (s) + " 秒");
        }),
                new KeyFrame(Duration.seconds(1))
        );
        java.time.Duration duration = java.time.Duration.between(LocalDateTime.now(), examEndTime);
        long tempSecond = duration.getSeconds();
        int temp = (int)tempSecond;
        clock.setCycleCount(temp);
        clock.play();
        clock.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                examEndTimeRemindLabel.setVisible(false);
                problemSetPagination.setVisible(false);
                remindLabel.setVisible(true);
                remindLabel.setText("考试已结束！");
            }
        });
    }
    public void catchKeyBoardPressedAction(KeyEvent event) {

    }
    public void problemSetPaginationOnAction(int numberCount,Question question) {
        problemSetPagination.setPageCount(numberCount);
        problemSetPagination.setPageFactory((Integer pageIndex) -> StaticPagainationFactory.createPage(pageIndex, userClass.question, userClass.problemDatagram));
    }
    public void filmButtonOnAction(ActionEvent event) {
        try {
            //System.out.println(userClass.answerDatagram == null);
            //System.out.println(studentSocket == null);
            if(userClass.isExamed == false) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误");
                alert.setHeaderText("考试未开始");
                alert.setContentText("考试还未开始，无法提交答案！");
                alert.showAndWait();
                return;
            } else {
                studentSocket.sendAnswerDatagram(userClass.answerDatagram);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误");
            alert.setHeaderText("服务器异常");
            alert.setContentText("无法提交答案，请检查连接！");
            alert.showAndWait();
            return;
        } catch (ClassNotFoundException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误");
            alert.setHeaderText("未知错误");
            alert.setContentText("出现了未知错误！");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提交");
        alert.setHeaderText("提交成功");
        alert.setContentText("您已提交成功！");
        alert.showAndWait();
        userClass.isExamed = false;
        examEndTimeRemindLabel.setVisible(false);
        problemSetPagination.setVisible(false);
        remindLabel.setVisible(true);
        userClass.isExamEnded = true;
        remindLabel.setText("考试已结束！");
    }
    public void setConnectClosedAlert(ActionEvent event) {
        stateLabel.setText("已断开连接！");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("错误");
        alert.setHeaderText("服务器异常");
        alert.setContentText("服务器已断开，请检查连接！");
        alert.showAndWait();
    }
}