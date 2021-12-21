package com.example.ap_willhero;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private VBox menu = new VBox();
    private EventHandler<MouseEvent> onGeneralClick;
    @FXML
    private AnchorPane root = new AnchorPane();
    @FXML
    private Button PlayGame = new Button();

    @FXML
    private ImageView hero = new ImageView();

    @FXML
    private ImageView greenOrc = new ImageView();

    @FXML
    private ImageView redOrc = new ImageView();

    @FXML
    private Label currrentLocation = new Label();


    @FXML
    private ImageView pauseMenu = new ImageView();

    @FXML
    private Button pauseMenuButton = new Button();

    @FXML
    private TranslateTransition heroMoving = new TranslateTransition();

//    @FXML
//    private TranslateTransition orcMoving = new TranslateTransition();

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

    @FXML
    private AnchorPane gameRoot = new AnchorPane();

    @FXML
    private Button loadGameButton = new Button();

    @FXML
    private Pane pauseGameMenu = new Pane();

    @FXML
    private Pane MainMenu = new Pane();

    @FXML
    private TranslateTransition coinAnimationTranslate = new TranslateTransition();

    @FXML
    private ImageView coin1 = new ImageView();

    @FXML
    private ImageView coin2 = new ImageView();

    @FXML
    private ImageView coin3 = new ImageView();

    @FXML
    private HBox threeCoinsBox= new HBox();

    @FXML
    private ImageView obstacle1 = new ImageView();
    @FXML
    private ImageView obstacle2 = new ImageView();
    @FXML
    private ImageView obstacle3 = new ImageView();
    @FXML
    private ImageView obstacle4 = new ImageView();

    @FXML
    private Pane staticPane = new Pane();

    @FXML
    private ImageView settingsButton = new ImageView();

    private int currLocation;


    public void removeFallingPlatformNode(ImageView node){
        gameRoot.getChildren().remove(node);
    }

    public void nodeFalling(ImageView node){
        TranslateTransition fallNode = new TranslateTransition();
        fallNode.setByY(node.getY() + 500);
        fallNode.setDuration(Duration.millis(2500));
        fallNode.setNode(node);
        fallNode.setOnFinished(e -> removeFallingPlatformNode(node));

        fallNode.play();


    }

    public void startFallingPlatformAnimation(){
        PauseTransition pause1 = new PauseTransition();
        PauseTransition pause2 = new PauseTransition();
        PauseTransition pause3 = new PauseTransition();

        pause1.setDuration(Duration.millis(500));
        pause2.setDuration(Duration.millis(1000));
        pause3.setDuration(Duration.millis(1500));


        nodeFalling(obstacle1);

        pause1.play();
        pause1.setOnFinished(e -> nodeFalling(obstacle2));
        pause2.play();
        pause2.setOnFinished(e -> nodeFalling(obstacle3));
        pause3.play();
        pause3.setOnFinished(e -> nodeFalling(obstacle4));




    }
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

    public void coinAnimation(){
        coinAnimationTranslate.setByY(coin1.getY() - 10);
        coinAnimationTranslate.setDuration(Duration.millis(800));
        coinAnimationTranslate.setCycleCount(500);
        coinAnimationTranslate.setAutoReverse(true);
        coinAnimationTranslate.setNode(threeCoinsBox);
        coinAnimationTranslate.play();
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
        root.getChildren().remove(MainMenu);
        startFallingPlatformAnimation();


    }
    public void moveHero(){
        heroMoving.setByY(hero.getY() - 100);
        heroMoving.setDuration(Duration.millis(850));
        heroMoving.setCycleCount(500);
        heroMoving.setAutoReverse(true);
        heroMoving.setNode(hero);
        heroMoving.play();

    }
    public void moveOrc(ImageView orc, double time){
        TranslateTransition orcMoving = new TranslateTransition();
        orcMoving.setByY(orc.getY() - 100);
        orcMoving.setDuration(Duration.millis(time));
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

        heroMoving.pause();
        platformMoving.pause();
        root.getChildren().add(pauseGameMenu);


    }

    public void displayLoadMenu(){
        root.getChildren().add(loadGameMenu);
    }

    public void onScreenClick(){
        shootShurikenBullet();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        root.getChildren().remove(loadGameMenu);
        root.getChildren().remove(pauseGameMenu);

        moveHero();
        moveOrc(redOrc, 750);
        moveOrc(greenOrc, 800);
        movePlatform();
        coinAnimation();
        currLocation = 0;
        onGeneralClick = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                if(settingsButton.isPressed()){
//                    System.out.println("HELLO ");
//                }
                if (hero.getTranslateX() <= 300) {
                    TranslateTransition forwardStep = new TranslateTransition();
                    forwardStep.setByX(hero.getLayoutX() + 50);
                    forwardStep.setDuration(Duration.millis(300));
                    forwardStep.setNode(hero);
                    forwardStep.play();
                } else {
//                    hero.setLayoutX(hero.getLayoutX() -200);
                    for (Node x : gameRoot.getChildren()) {
                        TranslateTransition forwardStep = new TranslateTransition();
                        if (x != hero && x != staticPane) {
                            forwardStep.setByX(x.getLayoutX() - 100);
                            forwardStep.setDuration(Duration.millis(200));
                            forwardStep.setNode(x);
                            forwardStep.play();

                        }
                    }
                }
            }
        };
        staticPane.setOnMouseClicked(onGeneralClick);
    }
}