package com.example.ap_willhero;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements EventHandler<KeyEvent>, Initializable {

    @FXML
    private VBox menu = new VBox();
    @FXML
    private AnchorPane root = new AnchorPane();
    @FXML
    private Button PlayGame = new Button();

    @FXML
    private ImageView hero = new ImageView();



    @FXML
    private ImageView orc = new ImageView();



    @FXML
    private ImageView pauseMenu = new ImageView();

    @FXML
    private Button pauseMenuButton = new Button();

    @FXML
    private TranslateTransition heroMoving = new TranslateTransition();

    @FXML
    private TranslateTransition orcMoving = new TranslateTransition();

    @FXML
    private TranslateTransition platformMoving = new TranslateTransition();


    @FXML
    private FadeTransition menuDisappearing = new FadeTransition();

    @FXML
    private FadeTransition pauseMenuAppearing = new FadeTransition();

    @FXML
    private ImageView floatingPlatform = new ImageView();

    @FXML
    private RotateTransition rotatingShuriken = new RotateTransition();

    @FXML
    private ImageView shurikenBullet;

    @FXML
    private TranslateTransition movingShuriken = new TranslateTransition();

    @FXML
    private Button fullScreenButton = new Button();

    @FXML
    private Pane loadGameMenu = new Pane();



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

    public void shootShurikenBullet(){
        Image bullet = new Image("shurikenBullet.png");
        shurikenBullet = new ImageView(bullet);
        shurikenBullet.setFitWidth(28);
        shurikenBullet.setFitHeight(28);
        shurikenBullet.setLayoutX(hero.getLayoutX() + hero.getTranslateX() + 5);
        shurikenBullet.setLayoutY(hero.getLayoutY() + hero.getTranslateY());
        root.getChildren().add(shurikenBullet);
        shootWeaponAnimation();

    }
    public void removeMenu(){
        root.getChildren().remove(menu);

    }
    public void moveHero(){
        heroMoving.setByY(hero.getY() - 100);
        heroMoving.setDuration(Duration.millis(850));
        heroMoving.setCycleCount(500);
        heroMoving.setAutoReverse(true);
        heroMoving.setNode(hero);
        heroMoving.play();

    }
    public void moveOrc(){
        orcMoving.setByY(orc.getY() - 100);
        orcMoving.setDuration(Duration.millis(900));
        orcMoving.setCycleCount(200);
        orcMoving.setAutoReverse(true);
        orcMoving.setNode(orc);
        orcMoving.play();

    }
    public void movePlatform(){
        platformMoving.setByY(floatingPlatform.getY() - 20);
        platformMoving.setDuration(Duration.millis(3000));
        platformMoving.setCycleCount(200);
        platformMoving.setAutoReverse(true);
        platformMoving.setNode(floatingPlatform);
        platformMoving.play();


    }
    public void removeWeapon(){
        root.getChildren().remove(shurikenBullet);

    }

    public void shootWeaponAnimation(){
        rotatingShuriken.setAxis(Rotate.Z_AXIS);
        rotatingShuriken.setByAngle(360);
        rotatingShuriken.setCycleCount(100);
        rotatingShuriken.setDuration(Duration.millis(300));

        rotatingShuriken.setNode(shurikenBullet);
        System.out.println("Reached Rotating Animation");
        rotatingShuriken.play();
        movingShuriken.setByX(shurikenBullet.getX() + 300);
        movingShuriken.setDuration(Duration.millis(1000));
        movingShuriken.setAutoReverse(true);
        movingShuriken.setNode(shurikenBullet);
        movingShuriken.play();
        movingShuriken.setOnFinished(e -> removeWeapon());

    }
    public void displayPauseMenu(){
        onPlayGameClick();
        orcMoving.pause();
        heroMoving.pause();
        platformMoving.pause();
        //FXMLLoader pauseGameLoader = new FXMLLoader(getClass().getResource("PauseGameMenu.fxml"));

        //pauseMenuAppearing.play();
        //root.getChildren().add(pauseMenu);

    }

    public void onScreenClick(){
        shootShurikenBullet();
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        System.out.println("REACHED HERE");

        if(KeyCode.W.equals(keyEvent.getCode())){
            System.out.println("W Pressed");
            shootShurikenBullet();

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moveHero();
        moveOrc();
        movePlatform();

    }
}