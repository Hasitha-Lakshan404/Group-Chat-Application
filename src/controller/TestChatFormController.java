package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author : Hasitha Lakshan
 * Project :Group Chat Application
 * Date :8/10/2022
 * Time :11:11 AM
 */

public class TestChatFormController extends Thread {
//    public TextArea txtTextArea;
    public TextField txtTextMsg;
    public ImageView imgSendMsg;
    public AnchorPane apnChatForm;
    public Rectangle rectangle;
    public Label txtUserName;
    public Pane pnePopUp;
    public TextField txtNicName;
    public VBox vboxChat;

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
                /*if (fullMsg.toString().equalsIgnoreCase("bye")) {
                    break;
                }*/
//======================================================================
                Text text=new Text(msg);

                text.setFill(Color.WHITE);
                text.getStyleClass().add("message");
                TextFlow tempFlow=new TextFlow();

                if(!cmd.equalsIgnoreCase(txtUserName.getText() + ":")){
                    Text txtName=new Text(msg + "\n");
                    txtName.getStyleClass().add("txtName");
                    tempFlow.getChildren().add(txtName);
                }

                tempFlow.getChildren().add(text);
                tempFlow.setMaxWidth(200);

                TextFlow flow=new TextFlow(tempFlow);

                HBox hBox=new HBox(12);

                //=================================================


                System.out.println("cmd=" + cmd + "-----" + "UserName" + txtUserName.getText());
                if (!cmd.equalsIgnoreCase(txtUserName.getText() + ":")) {
                    /*HBox hBox=new HBox(12);
                    hBox.setAlignment(Pos.CENTER_LEFT);


                    Text text = new Text(msg);*/


//                    hBox.getChildren().add(text);
//                    txtTextArea.appendText(msg + "\n");

                    tempFlow.getStyleClass().add("tempFlowFlipped");
                    flow.getStyleClass().add("textFlowFlipped");
                    vboxChat.setAlignment(Pos.TOP_LEFT);
                    hBox.setAlignment(Pos.CENTER_LEFT);
                    hBox.getChildren().add(flow);

                }else{
                    text.setFill(Color.WHITE);
                    tempFlow.getStyleClass().add("tempFlow");
                    flow.getStyleClass().add("textFlow");
                    hBox.setAlignment(Pos.BOTTOM_RIGHT);
                    hBox.getChildren().add(flow);
                }
                hBox.getStyleClass().add("hbox");
                Platform.runLater(() -> vboxChat.getChildren().addAll(hBox));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void imgSendMsgOnAction(MouseEvent mouseEvent) throws IOException {

        String msg = txtTextMsg.getText();
        writer.println(txtUserName.getText() + ": " + txtTextMsg.getText());

       /* HBox hBox=new HBox(12);
        hBox.setAlignment(Pos.CENTER_LEFT);


        Text text = new Text("Me: "+msg);


        hBox.getChildren().add(text);*/

//        txtTextArea.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
//        txtTextArea.appendText("Me: " + msg + "\n");
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
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/TestChatForm.fxml"))));
        stage.setResizable(false);
        //primaryStage.getIcons().add(new Image("location"));
        stage.setTitle("sample title");
        stage.centerOnScreen();
        stage.show();

    }
}
