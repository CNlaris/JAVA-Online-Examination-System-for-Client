package controller;

import model.AnswerDatagram;
import model.ProblemDatagram;
import model.Question;
import model.Student;

import java.net.Socket;

public class userClass {
    public static Student student;
    public static ProblemDatagram problemDatagram;
    public static Question question;
    public static AnswerDatagram answerDatagram;
    public static Socket socket;
    public static boolean isExamed = false;
    public static boolean isExamEnded = false;
}
