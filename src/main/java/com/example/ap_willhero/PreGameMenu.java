package com.example.ap_willhero;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.action.Action;

import java.net.URL;
import java.security.Key;
import java.util.ResourceBundle;

public class PreGameMenu implements EventHandler<KeyEvent>, Initializable {

    @FXML
    VBox menu = new VBox();
    @FXML
    AnchorPane root = new AnchorPane();
    @FXML
    Button PlayGame = new Button();

    @FXML
    ImageView hero = new ImageView();

    @FXML
    ImageView orc = new ImageView();

    @FXML
    private ImageView pauseMenu = new ImageView();

    @FXML
    private Button pauseMenuButton = new Button();

    @FXML
    TranslateTransition heroMoving = new TranslateTransition();

    @FXML
    TranslateTransition orcMoving = new TranslateTransition();

    @FXML
    FadeTransition menuDisappearing = new FadeTransition();

    @FXML
    FadeTransition pauseMenuAppearing = new FadeTransition();



    Hero h =  new Hero();


    public void onPlayGameClick(){
        menuDisappearing.setDuration(Duration.millis(1000));

        menuDisappearing.setNode(menu);
        menuDisappearing.setFromValue(10);
        menuDisappearing.setToValue(0);
        menuDisappearing.setAutoReverse(false);
        menuDisappearing.play();
        menuDisappearing.setOnFinished(e -> removeMenu());


//        root.getChildren().remove(menu);

    }
    public void removeMenu(){
        root.getChildren().remove(menu);
    }
    public void moveHero(){
        heroMoving.setByY(hero.getY() - 100);
        heroMoving.setDuration(Duration.millis(1000));
        heroMoving.setCycleCount(500);
        heroMoving.setAutoReverse(true);
        heroMoving.setNode(hero);
        heroMoving.play();

    }
    public void moveOrc(){
        orcMoving.setByY(orc.getY() - 100);
        orcMoving.setDuration(Duration.millis(1000));
        orcMoving.setCycleCount(200);
        orcMoving.setAutoReverse(true);
        orcMoving.setNode(orc);
        orcMoving.play();

    }

    public void displayPauseMenu(){
        onPlayGameClick();
        orcMoving.stop();
        heroMoving.stop();
        pauseMenuAppearing.setDuration(Duration.millis(1000));

        pauseMenuAppearing.setNode(pauseMenu);
        pauseMenuAppearing.setFromValue(10);
        pauseMenuAppearing.setToValue(0);
        pauseMenuAppearing.setAutoReverse(false);

        pauseMenuAppearing.play();
        //root.getChildren().add(pauseMenu);

    }

    @Override
    public void handle(KeyEvent keyEvent) {
        System.out.println("REACHED HERE");

        if(KeyCode.W.equals(keyEvent.getCode())){
            System.out.println("W Pressed");
            moveHero();

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moveHero();
        moveOrc();
    }
}