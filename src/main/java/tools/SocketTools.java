/*
    SocketTools类:封装了socket的发送和接收的功能
*/
package tools;

import controller.userClass;

import java.io.*;
import java.net.Socket;

public class SocketTools {

    //setSocket:生成一个socket,如果有错误则抛出异常并且返回的socket为空!!!!!!
    public static Socket setSocket(String ipAddress, int port, String usetName, String userNumber) throws IOException,ConnectWrongException {
        Socket socket = null;
        int LoginFlag = 1;
        socket = new Socket(ipAddress, port);  //创建一个socket
        String loginMessage = ProcessTools.loginProcessMessage(usetName, userNumber);
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF(loginMessage);
        String successFlag = dataInputStream.readUTF();
        //socketSendMessage(socket, loginMessage);
            // String successFlag = socketReceiveMessage(socket);
            //String successFlag = bufferedReader.readLine();
            System.out.println(successFlag);
            String[] arrayString = successFlag.split("-");
            if (!arrayString[0].equals("accepted")) {
                socket.close();
                throw new ConnectWrongException();
            }
            userClass.student.setCourseName(arrayString[1]);


        return socket;
    }

    //socketSendMessage:向服务端发送消息，发送成功返回值为1，发送失败返回值为0
    public static void socketSendMessage(Socket socket, String message) throws IOException {

            //将socket的InputStream转换成PrintWriter
            /*OutputStream socketOutputStream = socket.getOutputStream();//获取一个输出流，向服务端发送信息
            PrintWriter socketPrintWriter = new PrintWriter(socketOutputStream);//将输出流包装成打印流
            socketPrintWriter.flush();
            socketPrintWriter.print(message);
            socketPrintWriter.flush();
            System.out.println("完成");*/
            OutputStream os = socket.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            System.out.println(message);
            bw.write(message);
            bw.flush();
            System.out.println(message);
            //关闭输出流
            //socket.shutdownOutput();
    }

    //socketReceiveMessage:接收服务端的消息，返回服务端的字符串
    public static String socketReceiveMessage(Socket socket) throws IOException{
        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String message = bufferedReader.readLine();
        /*
        int flag = 0;
        while((flag = bufferedReader.read()) != -1) {
            char temp = (char)flag;
            System.out.print(temp);
            messageBuffer.append(temp);
        }
        message = messageBuffer.toString();
         */
        return message;
    }

}
