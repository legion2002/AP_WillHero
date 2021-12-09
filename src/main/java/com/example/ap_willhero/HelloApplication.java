package com.example.ap_willhero;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PreGameMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 600);
        Image icon = new Image("icon.png");
        scene.setOnKeyPressed(new PreGameMenu());
        stage.getIcons().add(icon);
        stage.setTitle("Will Hero by Tanishk Goyal and Diksha Sethi");


        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}