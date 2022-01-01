package com.example.ap_willhero;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
    private Label currrentLocationLabel = new Label();

    @FXML
    private Label coinsCollectedLabel = new Label();


    @FXML
    private ImageView pauseMenu = new ImageView();

    @FXML
    private Button pauseMenuButton = new Button();


    @FXML
    private FadeTransition menuDisappearing = new FadeTransition();

    @FXML
    private FadeTransition pauseMenuAppearing = new FadeTransition();


    @FXML
    private RotateTransition rotatingShuriken = new RotateTransition();

    @FXML
    private ImageView shurikenBullet;

    @FXML
    private TranslateTransition movingShuriken = new TranslateTransition();


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
    private Stage stage;
    private Scene currScene;
    private KeyFrame master;
    private KeyFrame heroStep;


    private ArrayList<String> savedGameSlots = new ArrayList<>();
    private int overWrite;

    public GameController() {
        savedGameSlots.add("StoringGame1");
        savedGameSlots.add("StoringGame2");
        savedGameSlots.add("StoringGame3");
        overWrite = 1;
    }

    public void createPlatformList() {
        for (Node platforms : platformPane.getChildren()) {

            game.addPlatform(new Platform((Rectangle) platforms));
        }

        for(Node fallingPlatforms : gameObjectsPane.getChildren()){
            game.addFallingPlatform(new FallingPlatform((Rectangle) fallingPlatforms));
        }

    }


    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCurrScene(Scene currScene) {
        this.currScene = currScene;
    }

    public Scene getCurrScene() {
        return currScene;
    }

    public void setUpGame() {

        this.game = new Game(this, heroImage);

        game.getHero().setHeroImage(heroImage);
        root.getChildren().remove(loadGameMenu);
        root.getChildren().remove(pauseGameMenu);
        createPlatformList();
        game.generateGameObjects();
        this.currLocation = 0;

    }

    public void serialize() throws IOException {

        String filename = savedGameSlots.get(overWrite - 1);

        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(game); //Storing game
            overWrite++;
            if (overWrite == 4) overWrite = 1;

        } finally {
            out.close();
        }
        System.out.println("Done serialising");
    }

    public Label getCoinsCollectedLabel() {
        return this.coinsCollectedLabel;
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

    public void setUpHeroStepKeyFrame(int refreshTime, int animationTime){
        heroStep = new KeyFrame(Duration.millis(refreshTime), e -> {

            game.getHero().setyVelocity(0);
            double savedGravity = game.getGravity();
            game.setGravity(0);

            for (Solid x : game.getSolidList()) {
                if(! (x instanceof Shurikens)){
                    x.translateSolidX(-game.getHero().getStepSize() * refreshTime / animationTime);

                }

            }
            for (Node x : gameObjectsPane.getChildren()) {
                x.setLayoutX(x.getLayoutX() - game.getHero().getStepSize() * refreshTime / animationTime);
            }
            game.setGravity(savedGravity);
//                    game.getHero().setxVelocity(0);
        });

    }
    public void setUpGeneralClick() {

        onGeneralClick = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(game.getHero().getEquippedWeapon() != null){
                    Weapon currWeapon = game.getHero().getEquippedWeapon();
                    currWeapon.useWeapon(game);

                }
                int refreshTime = 5;
                int animationTime = 100;
                setUpHeroStepKeyFrame(5, 100);
                Timeline movingHero = new Timeline();
                movingHero.setCycleCount(animationTime / refreshTime);
                movingHero.getKeyFrames().add(heroStep);
                movingHero.play();
                currLocation++;
                currrentLocationLabel.setText(Integer.toString(currLocation));
            }
        };
    }

    public void checkHeroLife() {
        if (game.getHero().isDead(game.getAbyssLevel())) {
            game.killHero();
        }

    }

    public void garbageArea(Solid gameObject) {
        //Removing Old Objects
        if (gameObject instanceof Platform) {
            game.getPlatformList().remove(gameObject);
        } else if (gameObject instanceof Orc) {
            game.getOrcList().remove(gameObject);
        }
//                  Try iterating over this
//                    game.getSolidList().remove(gameObject);

    }

    public void stagingArea(Solid gameObject) {
        //Staging Area

        if (!gameObject.isStaged()) {


            if (gameObject instanceof GreenOrc) {
                GreenOrc g = (GreenOrc) gameObject;
                ImageView img = new ImageView(new Image("greenOrc.png"));
                g.setOrcImage(img);
                gameObjectsPane.getChildren().add(img);
                g.setyVelocity(-0.3);

            } else if (gameObject instanceof RedOrc) {
                RedOrc g = (RedOrc) gameObject;
                ImageView img = new ImageView(new Image("redOrc.jpeg"));
                g.setOrcImage(img);
                gameObjectsPane.getChildren().add(img);
                g.setyVelocity(-0.3);

            } else if (gameObject instanceof TreasureChest) {

                TreasureChest t = (TreasureChest) gameObject;
                ImageView img = new ImageView(new Image("chestClosed.png"));
                t.setChestImage(img);
                gameObjectsPane.getChildren().add(img);


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

            else if(gameObject instanceof FallingPlatform && !(((FallingPlatform)gameObject).isBrickPhotosSet())){
                FallingPlatform p = (FallingPlatform) gameObject;
                p.getRectangleForPlatform().setOpacity(0);
                Image img = new Image("FallingPlatformNode.png");
                int brickNumber = 0;
                int numberOfBricks = p.getBricks().size();
                for(int i = 0; i < numberOfBricks; i++){
                    System.out.println("Adding brick number " + i);
                    ImageView imgview = new ImageView(img);
                    p.getBricks().get(i).setBrickImage(imgview, brickNumber);
                    gameObjectsPane.getChildren().add(imgview);
                    brickNumber++;
                }
                p.setBrickPhotosSet(true);
            }


            gameObject.setStaged(true);
        }


    }

    public void collisionArea(Solid gameObject) {
        //Collision Here
        //Only Hero and Orc are collidable, rest check with Solids
        int collideVal = game.getHero().hasCollided(gameObject);
        if (collideVal > 0) {
            game.getHero().collidesWith(gameObject, collideVal);
        }
        for (Orc x : game.getOrcList()) {
            collideVal = x.hasCollided(gameObject);
            if (collideVal > 0) {
                x.collidesWith(gameObject, collideVal);
            }
        }
    }

    public void physicsHero(int frameTimeInMillis) {
        double s = game.getHero().getyVelocity() * frameTimeInMillis + frameTimeInMillis * frameTimeInMillis * game.getGravity() / 2;
        game.getHero().translateSolidY(s);
        double v = game.getHero().getyVelocity() + game.getGravity() * frameTimeInMillis;
        game.getHero().setyVelocity(v);
    }

    public void physicsOrc(Orc x, int frameTimeInMillis) {
        double so = x.getyVelocity() * frameTimeInMillis + frameTimeInMillis * frameTimeInMillis * game.getGravity() / 2;
        x.translateSolidY(so);
        x.translateSolidX(x.getxVelocity() * frameTimeInMillis);
        double vo = x.getyVelocity() + game.getGravity() * frameTimeInMillis;
        x.setyVelocity(vo);
    }

    public void physicsEngine(int frameTimeInMillis) {

        physicsHero(frameTimeInMillis);
        for (Orc x : game.getOrcList()) {
            if (x.isStaged()) {
                physicsOrc(x, frameTimeInMillis);
            }


        }

    }

    public void setUpMasterKeyFrame() {
        int frameTimeInMillis = 40;
        master = new KeyFrame(Duration.millis(frameTimeInMillis), e -> {
            checkHeroLife();
            removeDeadThings();



            for (Solid gameObject : game.getSolidList()) {
                if (gameObject.getPos().getxPos() + gameObject.getWidth() < 0) {
                    garbageArea(gameObject);
                }
                if (gameObject.getPos().getxPos() <= 2048 && gameObject.getPos().getxPos() > 0) {
                    stagingArea(gameObject);
                }
                if (gameObject.getPos().getxPos() <= 2048) {
                    collisionArea(gameObject);

                }
            }
            physicsEngine(frameTimeInMillis);


        });
    }

    public void removeDeadThings() {
        for(Orc x : game.getOrcList()){
//            if(x.getPos().getxPos() > game.getAbyssLevel()){
//                x.setAlive(false);
//                if(x.isStaged()){
//                    x.getOrcImage().setImage(null);
//
//                }
//
//            }
            if(!x.isAlive()){

                game.getSolidList().remove(x);
            }
        }
        for(Solid x : game.getWeaponList()){
            if(x instanceof Shurikens){
                if(!(((Shurikens) x).isLive)){
                    game.getSolidList().remove(x);
                }

            }
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setUpGame();
        setUpGeneralClick();
        setUpMasterKeyFrame();
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(master);
        timeline.play();
        staticPane.setOnMouseClicked(onGeneralClick);
    }
}
