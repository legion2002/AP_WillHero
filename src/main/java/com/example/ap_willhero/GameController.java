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
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
    private ImageView heroImage = new ImageView();

    @FXML
    private ImageView greenOrc = new ImageView();

    @FXML
    private ImageView redOrc = new ImageView();

    @FXML
    private Label currrentLocationLabel = new Label();

    @FXML
    private Label coinsCollectedLabel = new Label();


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
    private HBox threeCoinsBox = new HBox();

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

    @FXML
    private Pane platformPane = new Pane();

    @FXML
    private Pane cloudPane = new Pane();

    @FXML
    private Pane gameObjectsPane = new Pane();


    private int currLocation;

    private Game game;

    private int reachHeight;

    private boolean goingUp;
    private boolean pauseMotion;

    public GameController() {

    }

    public void createPlatformList() {
        for (Node platforms : platformPane.getChildren()) {

            game.addPlatform(new Platform((Rectangle) platforms));
        }
    }

    public void setUpGame() {
        createPlatformList();
        game.generateGameObjects();

    }

    public void serialize() throws IOException {

        ObjectOutputStream out = null;
        TestingClass test = new TestingClass(1);
        TestingClass test2 = new TestingClass(3);
        try {
            out = new ObjectOutputStream(new FileOutputStream("StoringGame.txt"));
            out.writeObject(test); //Storing game
            out.writeObject(test2);
        } finally {
            out.close();
        }
        System.out.println("Done serialising");
    }

    public Label getCoinsCollectedLabel() {
        return this.coinsCollectedLabel;
    }

    public Game getGame() {
        return this.game;
    }


    public void removeFallingPlatformNode(ImageView node) {
        gameRoot.getChildren().remove(node);
    }

    public void nodeFalling(ImageView node) {
        TranslateTransition fallNode = new TranslateTransition();
        fallNode.setByY(node.getY() + 500);
        fallNode.setDuration(Duration.millis(2500));
        fallNode.setNode(node);
        fallNode.setOnFinished(e -> removeFallingPlatformNode(node));

        fallNode.play();


    }

    public void startFallingPlatformAnimation() {
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

    public void onPlayGameClick() {
        menuDisappearing.setDuration(Duration.millis(1000));

        menuDisappearing.setNode(menu);
        menuDisappearing.setFromValue(10);
        menuDisappearing.setToValue(0);
        menuDisappearing.setAutoReverse(false);
        menuDisappearing.play();
        menuDisappearing.setOnFinished(e -> removeMenu());


//        root.getChildren().remove(menu);

    }

    public void coinAnimation() {
        coinAnimationTranslate.setByY(coin1.getY() - 10);
        coinAnimationTranslate.setDuration(Duration.millis(800));
        coinAnimationTranslate.setCycleCount(500);
        coinAnimationTranslate.setAutoReverse(true);
        coinAnimationTranslate.setNode(threeCoinsBox);
        coinAnimationTranslate.play();
    }

    public void shootShurikenBullet() {
        Image bullet = new Image("shurikenBullet.png");
        shurikenBullet = new ImageView(bullet);
        shurikenBullet.setFitWidth(28);
        shurikenBullet.setFitHeight(28);
        shurikenBullet.setLayoutX(game.getHero().getHeroImage().getLayoutX() + game.getHero().getHeroImage().getTranslateX() + 5);
        shurikenBullet.setLayoutY(game.getHero().getHeroImage().getLayoutY() + game.getHero().getHeroImage().getTranslateY());
        root.getChildren().add(shurikenBullet);
        shootWeaponAnimation();

    }

    public void removeMenu() {
        root.getChildren().remove(MainMenu);
        startFallingPlatformAnimation();


    }

    public AnchorPane getGameRoot() {
        return this.gameRoot;
    }

    public void moveOrc(ImageView orc, double time) {
        TranslateTransition orcMoving = new TranslateTransition();
        orcMoving.setByY(orc.getY() - 100);
        orcMoving.setDuration(Duration.millis(time));
        orcMoving.setCycleCount(200);
        orcMoving.setAutoReverse(true);
        orcMoving.setNode(orc);
        orcMoving.play();

    }

    public void movePlatform() {
        platformMoving.setByY(floatingPlatform.getY() - 20);
        platformMoving.setDuration(Duration.millis(3000));
        platformMoving.setCycleCount(200);
        platformMoving.setAutoReverse(true);
        platformMoving.setNode(floatingPlatform);
        platformMoving.play();


    }

    public void removeWeapon() {
        root.getChildren().remove(shurikenBullet);

    }

    public void shootWeaponAnimation() {
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

    public void displayPauseMenu() {

        heroMoving.pause();
        platformMoving.pause();
        root.getChildren().add(pauseGameMenu);


    }

    public void displayLoadMenu() {
        root.getChildren().add(loadGameMenu);
    }

    public Pane getGameObjectsPane() {
        return this.gameObjectsPane;
    }

    public void savingGame() {
        System.out.println("Saving this game");
        try {
            serialize();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.game = new Game(this, heroImage);
        game.getHero().setHeroImage(heroImage);
        root.getChildren().remove(loadGameMenu);
        root.getChildren().remove(pauseGameMenu);
        setUpGame();
//        game.getHero().checkCollisionWithPlatform();
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        goingUp = false;
        int countOrc = 0;
        int frameTimeInMillis = 40;
        KeyFrame collision = new KeyFrame(Duration.millis(frameTimeInMillis), e -> {
            if (game.getHero().isDead(game.getAbyssLevel())) {
                // Start Endgame Menu Here #DIKSHA
                System.out.println("Hero is Dead");
                System.exit(0);

            }
            int offset = 5;
            for (Solid gameObject : game.getSolidList()) {
                if (gameObject.getPos().getxPos() + gameObject.getWidth() < 0) {
                    //Removing Old Objects
                    if (gameObject instanceof Platform) {
                        game.getPlatformList().remove(gameObject);
                    } else if (gameObject instanceof Orc) {
                        game.getOrcList().remove(gameObject);
                    }
//                  Try iterating over this
//                    game.getSolidList().remove(gameObject);
                }
                if (gameObject.getPos().getxPos() <= 2048 && gameObject.getPos().getxPos() > 0) {
                    //Staging Area

                    if (!gameObject.isStaged()) {


                        if (gameObject instanceof GreenOrc) {
                            GreenOrc g = (GreenOrc) gameObject;
                            ImageView img = new ImageView(new Image("greenOrc.png"));
                            g.setOrcImage(img);
                            gameObjectsPane.getChildren().add(img);
                            g.setyVelocity(-0.5);
                            //System.out.println("making greenOrc");
                        } else if (gameObject instanceof RedOrc) {
                            RedOrc g = (RedOrc) gameObject;
                            ImageView img = new ImageView(new Image("redOrc.jpeg"));
                            g.setOrcImage(img);
                            gameObjectsPane.getChildren().add(img);
                            g.setyVelocity(-0.5);


                            //System.out.println("making redOrc");

                        } else if (gameObject instanceof TreasureChest) {

                            TreasureChest t = (TreasureChest) gameObject;
                            ImageView img = new ImageView(new Image("chestClosed.png"));
                            t.setChestImage(img);
                            gameObjectsPane.getChildren().add(img);
                            //System.out.println("making treasure chest");

                        } else if (gameObject instanceof Platform) {
                            Platform p = (Platform) gameObject;
                            p.getBasePlatform().setOpacity(0);
                            ImageView img;
                            if (p.getWidth() >= 350) {
                                img = new ImageView(new Image("longPlatform.png"));

                            } else if (p.getWidth() > 200 && p.getWidth() < 350) {
                                img = new ImageView(new Image("fatPlatform.png"));
                            } else {
                                img = new ImageView(new Image("normalPlatform.png"));
                            }
                            p.setPlatformImage(img);
                            gameObjectsPane.getChildren().add(img);


                        }


                        gameObject.setStaged(true);
                    }
                }
                if (gameObject.getPos().getxPos() <= 2048) {
                    //Collision Here
                    //Only Hero and Orc are collidable, rest check with Solids
                    int collideVal = game.getHero().hasCollided(gameObject);
                    if (collideVal > 0) {
                        game.getHero().collidesWith(gameObject, collideVal);
                    }
                    for(Orc x : game.getOrcList()){
                        collideVal = x.hasCollided(gameObject);
                        if(collideVal > 0){
                            x.collidesWith(gameObject,collideVal);
                        }
                    }
                }
            }

            double s = game.getHero().getyVelocity() * frameTimeInMillis + frameTimeInMillis * frameTimeInMillis * game.getGravity() / 2;
            game.getHero().translateSolidY(s);
            double v = game.getHero().getyVelocity() + game.getGravity() * frameTimeInMillis;
            game.getHero().setyVelocity(v);
            for(Orc x : game.getOrcList()){
                double so = x.getyVelocity() * frameTimeInMillis + frameTimeInMillis * frameTimeInMillis * game.getGravity() / 2;
                x.translateSolidY(so);
                x.translateSolidX(x.getxVelocity() * frameTimeInMillis);
                double vo = x.getyVelocity() + game.getGravity() * frameTimeInMillis;
                x.setyVelocity(vo);

            }
        });
        timeline.getKeyFrames().add(collision);
        timeline.play();
        currLocation = 0;

        onGeneralClick = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int animationTime = 100;
                int refreshTime = 5;
                Timeline movingHero = new Timeline();
                movingHero.setCycleCount(animationTime / refreshTime);

                KeyFrame moveHero = new KeyFrame(Duration.millis(refreshTime), e -> {

                    game.getHero().setyVelocity(0);
                    double savedGravity = game.getGravity();
                    game.setGravity(0);
                    for (Solid x : game.getSolidList()) {
                        x.translateSolidX(-game.getHero().getStepSize() * refreshTime / animationTime);
                    }
                    for (Node x : gameObjectsPane.getChildren()) {
                        x.setLayoutX(x.getLayoutX() - game.getHero().getStepSize() * refreshTime / animationTime);
                    }
                    game.setGravity(savedGravity);
                });

                movingHero.getKeyFrames().add(moveHero);
                movingHero.play();
                currLocation++;
                currrentLocationLabel.setText(Integer.toString(currLocation));
            }
        };
        staticPane.setOnMouseClicked(onGeneralClick);
    }
}
