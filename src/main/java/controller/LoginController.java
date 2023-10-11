package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tools.ConnectWrongException;
import tools.ProcessTools;
import tools.SocketTools;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML TextField userNameField;
    @FXML private TextField userNumberField;
    @FXML private Label userNameLabel;
    @FXML private Label userNumberLabel;
    @FXML private Button loginButton;
    @FXML private Button loginSettingButton;
    @FXML private Button exitButton;
    public static MainController mainController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userNameField.setText("学生");
        userNumberField.setText("123456789");
        userClass.student.setIpAddress("0.0.0.0");
        userClass.student.setPort("9005");
    }
    public void loginButtonAction(ActionEvent event) throws IOException {

        int successLoginFlag = 1;
        String userName = userNameField.getText();
        String userNumber = userNumberField.getText();
        userClass.student.setUserName(userName);
        userClass.student.setUserNumber(userNumber);
        String userIPAddress = userClass.student.getIpAddress();
        String userPort = userClass.student.getPort();
        if(!(userName.equals("admin") && userNumber.equals("123456"))) {
            if (userName.equals("") || userNumber.equals("")
                    || userIPAddress.equals("") || userPort.equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误");
                alert.setHeaderText("信息未填写完全");
                alert.setContentText("您的信息未填写完全，请检查");
                alert.showAndWait();
                return;
            }
            if (!ProcessTools.isIPAddress(userIPAddress)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误");
                alert.setHeaderText("IP地址错误");
                alert.setContentText("您的IP地址有误，请检查后再试");
                alert.showAndWait();
                return;
            }
            int integerUserPort = Integer.parseInt(userPort);
            if (!(integerUserPort >= 1 && integerUserPort <= 65535)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误");
                alert.setHeaderText("端口号错误");
                alert.setContentText("您的端口号有误，请检查后再试");
                alert.showAndWait();
                return;
            }
            try {
                userClass.socket = SocketTools.setSocket(userIPAddress, integerUserPort, userName, userNumber);
                System.out.println((userClass.socket == null));
            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误");
                alert.setHeaderText("登录错误");
                alert.setContentText("无法连接至服务器，请检查IP地址和端口号是否正确或者老师是否开启服务器");
                alert.showAndWait();
                successLoginFlag = 0;
            } catch (ConnectWrongException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误");
                alert.setHeaderText("账号或密码错误");
                alert.setContentText("账号或密码错误！请检查账号或密码是否正确");
                alert.showAndWait();
                successLoginFlag = 0;
            } catch(Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误");
                alert.setHeaderText("登录错误");
                alert.setContentText("无法连接至服务器，请检查IP地址和端口号是否正确或者老师是否开启服务器");
                alert.showAndWait();
                successLoginFlag = 0;
            }
        }
        if( successLoginFlag == 1) {
            //StudentSocket studentSocket = new StudentSocket();
            //studentSocket.socket = userClass.socket;
            //studentSocket.inputStream = userClass.socket.getInputStream();
            //studentSocket.outputStream = userClass.socket.getOutputStream();
            //Thread thread = new Thread(studentSocket);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Mainx.fxml"));
            AnchorPane settings = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("考试系统");
            stage.setScene(new Scene(settings));
            //stage.setFullScreen(true);
            //stage.setResizable(false);
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");
            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            /*stage.setOnCloseRequest(new EventHandler< WindowEvent>(){
                @Override
                public void handle(WindowEvent event){
                    // deque it
                    event.consume();
                }
            });*/
            //Platform.setImplicitExit(false);
            mainController = fxmlLoader.getController();
            stage.show();
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        }
    }
    public void LoginSettingsButtonAction() throws IOException {
        AnchorPane settings = FXMLLoader.load(getClass().getResource("/view/LoginSettings.fxml"));
        Stage stage = new Stage();
        stage.setTitle("配置");
        stage.setScene(new Scene(settings));
        stage.setResizable(false);
        stage.show();
    }
    public void loginKeyAction(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER ) {
            try {
                ActionEvent actionEvent = new ActionEvent();
                loginButtonAction(actionEvent);
            } catch(Exception e) {
                System.out.println("Exception");
            }
        }
    }
}