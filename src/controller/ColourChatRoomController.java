package controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.Collection;

/**
 * @author : Hasitha Lakshan
 * Project :Group Chat Application
 * Date :8/10/2022
 * Time :11:11 AM
 */

public class ColourChatRoomController extends Thread {
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

    private FileChooser fileChooser;
    private File filePath;

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

                String o = reader.getClass().getName();
                HBox hBox=null;

                Platform.runLater(() -> vboxChat.getChildren().addAll(hBox));

                System.out.println("=========oooooo=========="+o);
                /*if(reader.equals(HBox)){

                }

                if (o instanceof JFXButton) {
                    JFXButton btn = (JFXButton) o;

                }*/


                /*String msg = reader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];
                System.out.println(cmd);
//                txtTextArea.appendText(cmd+"\n");
                StringBuilder fullMsg = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fullMsg.append(tokens[i]);
                }

                System.out.println(fullMsg);
                String[] msgToAr = msg.split(" ");
                String st="";
                for (int i = 0; i < msgToAr.length-1; i++) {
                    st+=msgToAr[i+1]+" ";
                }
//======================================================================
                System.out.println("msg-"+msg);
                System.out.println("Fullmsg-"+fullMsg);
                System.out.println("st :"+st);


                Text text=new Text(st);

                text.setFill(Color.WHITE);
                text.getStyleClass().add("message");
                TextFlow tempFlow=new TextFlow();

                if(!cmd.equalsIgnoreCase(txtUserName.getText() + ":")){
                    Text txtName=new Text(cmd + "\n");
                    txtName.getStyleClass().add("txtName");
                    tempFlow.getChildren().add(txtName);
                }

                tempFlow.getChildren().add(text);
                tempFlow.setMaxWidth(200); //200

                TextFlow flow=new TextFlow(tempFlow);

                HBox hBox=new HBox(12); //12

                //=================================================

                System.out.println("cmd=" + cmd + "-----" + "UserName" + txtUserName.getText());
                if (!cmd.equalsIgnoreCase(txtUserName.getText() + ":")) {

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
                Platform.runLater(() -> vboxChat.getChildren().addAll(hBox));*/

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
        stage.getIcons().add(new Image("assets/messenger.gif"));
        stage.setTitle("Messenger");
        stage.centerOnScreen();
        stage.show();

    }


    public void chooseImageButton(MouseEvent mouseEvent) {

        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        this.filePath = fileChooser.showOpenDialog(stage);
//        txtTextMsg.setText(filePath.getPath());
//        saveControl = true;

        try {
            BufferedImage bufferedImage = ImageIO.read(filePath);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            ImageView imageView=new ImageView(image);
//            imageView.fitHeightProperty();
//            imageView.fitWidthProperty();

            imageView.setFitHeight(200);
            imageView.setFitWidth(300);

            double height = image.getHeight();
            double width = image.getWidth();

            HBox hBox=new HBox(12);
            hBox.setAlignment(Pos.BOTTOM_RIGHT);

            hBox.getChildren().addAll(imageView);



            Platform.runLater(() -> vboxChat.getChildren().addAll(hBox));

            writer.println(hBox);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
