package com.lee;

import com.lee.entity.NetworkServer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sun.net.NetworkClient;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Created by lipengchun on 2017/11/2.
 */
public class MainController {
    @FXML
    private TextField txtIP;

    @FXML
    private TextField txtPort;

    @FXML
    private TextArea txtInput;

    @FXML
    private TextArea txtOutput;

    @FXML
    private Button btnSend;

    @FXML
    private Button btnConnect;

    @FXML
    private Button btnClose;

//    final Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

    private NetworkServer server;

    public MainController() throws IOException {

    }

    @FXML
    private void listener() {
        int port = Integer.valueOf(txtPort.getText());
        try {
            server = new NetworkServer(port);
            server.open();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void close() {
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clearInputBuffer() {

    }

    @FXML
    private void send() {

    }

    private void setUIEnable(boolean isConnected) {
        btnConnect.setDisable(isConnected);
        btnClose.setDisable(!isConnected);
        btnSend.setDisable(!isConnected);
    }
}
