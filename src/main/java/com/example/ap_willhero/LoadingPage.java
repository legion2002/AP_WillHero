package com.example.ap_willhero;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadingPage {
    @FXML
    private Scene scene;
    private Stage stage;


    private Button button = new Button();

    public LoadingPage() {
    }


    @FXML
    public void onButtonClick() throws IOException {
        System.out.println("HELLOOO");
        Parent root =  FXMLLoader.load(getClass().getResource("PreGameMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void lifeIsGood(){
        System.out.println("HELLOOOOO");
    }
}
