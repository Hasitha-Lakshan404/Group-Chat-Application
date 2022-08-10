package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author : Hasitha Lakshan
 * Project :Group Chat Application
 * Date :8/8/2022
 * Time :12:10 AM
 */

public class ChatFormController extends Thread {

    public TextArea txtTextArea;
    public TextField txtTextMsg;
    public ImageView imgSendMsg;
    public AnchorPane apnChatForm;
    public Rectangle rectangle;
    public Label txtUserName;
    public Pane pnePopUp;
    public TextField txtNicName;

    BufferedReader reader;
    PrintWriter writer;
    Socket socket;


    public void initialize() {
        try {
            socket = new Socket("localhost", 5000);
            System.out.println("Socket is connected with server!");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {

                String msg = reader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];
                System.out.println(cmd);
//                txtTextArea.appendText(cmd+"\n");
                StringBuilder fullMsg = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fullMsg.append(tokens[i]);
                }
                System.out.println(fullMsg);
//                txtTextArea.appendText(cmd+" "+fullMsg+"\n");
                /*if (cmd.equalsIgnoreCase("Client" + ":")) {
                    continue;
                }*/
                if (fullMsg.toString().equalsIgnoreCase("bye")) {
                    break;
                }

                System.out.println("cmd=" + cmd + "-----" + "UserName" + txtUserName.getText());
                if (!cmd.equalsIgnoreCase(txtUserName.getText() + ":")) {
                    txtTextArea.appendText(msg + "\n");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void imgSendMsgOnAction(MouseEvent mouseEvent) throws IOException {

        String msg = txtTextMsg.getText();
        writer.println(txtUserName.getText() + ": " + txtTextMsg.getText());
        txtTextArea.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        txtTextArea.appendText("Me: " + msg + "\n");
        txtTextMsg.clear();
        if (msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            Stage stage = (Stage) txtTextMsg.getScene().getWindow();
            stage.close();
        }
    }

    public void btnGoOnAction(ActionEvent actionEvent) {
        txtUserName.setText(txtNicName.getText().trim());
        pnePopUp.setVisible(false);
        apnChatForm.setVisible(true);
    }

    public void AddClientOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ChatForm.fxml"))));
        stage.setResizable(false);
        //primaryStage.getIcons().add(new Image("location"));
        stage.setTitle("sample title");
        stage.centerOnScreen();
        stage.show();


    }

}
