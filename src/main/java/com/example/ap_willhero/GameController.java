package com.example.ap_willhero;

import javafx.animation.*;
import javafx.event.ActionEvent;
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
import org.controlsfx.control.action.Action;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Scanner;

public class GameController implements Initializable {


    @FXML
    transient private VBox menu = new VBox();
    transient private EventHandler<MouseEvent> onGeneralClick;
    transient private EventHandler<KeyEvent> onNumberKey;


    public AnchorPane getRoot() {
        return root;
    }

    public void setRoot(AnchorPane root) {
        this.root = root;
    }

    @FXML
    transient private AnchorPane root = new AnchorPane();
    @FXML
    transient private Button PlayGame = new Button();

    public ImageView getHeroImage() {
        return heroImage;
    }

    @FXML
    transient private ImageView heroImage = new ImageView();


    @FXML
    transient private Label currrentLocationLabel = new Label();

    @FXML
    transient private Label coinsCollectedLabel = new Label();

    @FXML
    transient private Label shurikenLabel = new Label();

    @FXML
    transient private Label knifeLabel = new Label();
    @FXML
    transient private Label SavedGameLocation1 = new Label();
    @FXML
    transient private Label SavedGameLocation2 = new Label();
    @FXML
    transient private Label SavedGameLocation3 = new Label();
    @FXML
    transient private Label SavedGameCoin1 = new Label();
    @FXML
    transient private Label SavedGameCoin2 = new Label();
    @FXML
    transient private Label SavedGameCoin3 = new Label();


    @FXML
    transient private Button pauseMenuButton = new Button();


    @FXML
    transient private Pane loadGameMenu = new Pane();


    @FXML
    transient private AnchorPane gameRoot = new AnchorPane();

    @FXML
    transient private Button loadGameButton = new Button();

    @FXML
    transient private Pane pauseGameMenu = new Pane();

    @FXML
    transient private Pane endGameMenu = new Pane();

    public Pane getMainMenu() {
        return MainMenu;
    }

    @FXML
    transient private Pane MainMenu = new Pane();

    @FXML
    transient private Pane staticPane = new Pane();


    @FXML
    transient private ImageView settingsButton = new ImageView();

    @FXML
    transient private Button resurrectButton = new Button();

    @FXML
    transient private Pane platformPane = new Pane();

    @FXML
    transient private Pane cloudPane = new Pane();

    @FXML
    transient private Pane gameObjectsPane = new Pane();


    transient private int currLocation;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    transient private Game game;
    transient private Stage stage;
    transient private Scene currScene;
    transient private KeyFrame master;
    transient private KeyFrame heroStep;


    transient private ArrayList<String> savedGameSlots = new ArrayList<>();

    transient private Timeline timeline;

    public Pane getEndGameMenu() {
        return endGameMenu;
    }

    public Button getResurrectButton() {
        return resurrectButton;
    }

    public GameController() {
        savedGameSlots.add("StoringGame1");
        savedGameSlots.add("StoringGame2");
        savedGameSlots.add("StoringGame3");
    }

    public void createPlatformList() {
        for (Node platforms : platformPane.getChildren()) {

            game.addPlatform(new Platform((Rectangle) platforms));
        }

        for (Node fallingPlatforms : gameObjectsPane.getChildren()) {
            if (fallingPlatforms instanceof Rectangle) {
                game.addFallingPlatform(new FallingPlatform((Rectangle) fallingPlatforms));

            }
        }
        int numberOfFallingPlatforms = game.getFallingPlatformList().size();
        for (int i = 0; i < numberOfFallingPlatforms; i++) {
            gameObjectsPane.getChildren().remove(gameObjectsPane.getChildren().size() - 1);
        }

        gameRoot.getChildren().remove(platformPane);

    }

    public EventHandler<KeyEvent> getOnNumberKey() {
        return onNumberKey;
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

    public Label getShurikenLabel() {
        return shurikenLabel;
    }

    public Label getKnifeLabel() {
        return knifeLabel;
    }

    public void setUpGame() {

        this.game = new Game(this, heroImage);

        game.getHero().setHeroImage(heroImage);
        root.getChildren().remove(loadGameMenu);
        root.getChildren().remove(pauseGameMenu);
        root.getChildren().remove(endGameMenu);
        createPlatformList();
        game.generateGameObjects();
        this.currLocation = 0;

    }

    public void serialize() throws IOException {

        String filename = savedGameSlots.get(Main.overWrite - 1);

        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(game); //Storing game
            setLabelsForLoadGames(Main.overWrite, this.game);
            Main.overWrite++;
            System.out.println("File created" + filename);
            if (Main.overWrite == 4) Main.overWrite = 1;

        } finally {
            out.close();
        }
    }

    public void setLabelsForLoadGames(int gameNumber, Game loadedGame){
        if(gameNumber == 1){
            SavedGameCoin1.setText("Coins : " +Integer.toString(loadedGame.getHero().getCurrCoins()));
            SavedGameLocation1.setText("Location : " +Integer.toString(loadedGame.getHero().getCurrentLocation()));
        }
        else if(gameNumber == 2){
            SavedGameCoin2.setText("Coins : " +Integer.toString(loadedGame.getHero().getCurrCoins()));
            SavedGameLocation2.setText("Location : " + Integer.toString(loadedGame.getHero().getCurrentLocation()));
        }
        else{
            SavedGameCoin3.setText("Coins : " +Integer.toString(loadedGame.getHero().getCurrCoins()));
            SavedGameLocation3.setText("Location : " +Integer.toString(loadedGame.getHero().getCurrentLocation()));
        }
    }

    public Label getCoinsCollectedLabel() {
        return this.coinsCollectedLabel;
    }

    public void onPlayGameClick() {
        setUpGame();
        setUpGeneralClick();
        setUpMasterKeyFrame();
        setUpNumberKeyPressed();
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(master);
        timeline.play();
        staticPane.setOnMouseClicked(onGeneralClick);
        staticPane.setOnKeyPressed(onNumberKey);
        removeMenu();


    }


    public void removeMenu() {
        root.getChildren().remove(MainMenu);

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

    public void savingGame() throws IOException, ClassNotFoundException {
        trySerializing();
        exitToMainPage();

    }

    public void trySerializing() {
        try {
            serialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pauseGame() {
        timeline.pause();
        displayPauseMenu();
    }


    public void displayGameObjectsAfterLoading() {

        currrentLocationLabel.setText(Integer.toString(game.getHero().getCurrentLocation()));
        getCoinsCollectedLabel().setText(Integer.toString(game.getHero().getCurrCoins()));
        getShurikenLabel().setText(Integer.toString(game.getHero().getHelmet().getWeapon1().getLevel()));
        getKnifeLabel().setText(Integer.toString(game.getHero().getHelmet().getWeapon2().getLevel()));

        game.getHero().setHeroImage(heroImage);

        gameRoot.getChildren().remove(platformPane);
        root.getChildren().remove(loadGameMenu);
        root.getChildren().remove(endGameMenu);
        root.getChildren().remove(pauseGameMenu);

        int numberOfFallingPlatforms = game.getFallingPlatformList().size();
        for (int i = 0; i < numberOfFallingPlatforms; i++) {
            gameObjectsPane.getChildren().remove(gameObjectsPane.getChildren().size() - 1);
        }


        for (Solid gameObject : game.getSolidList()) {
            if (gameObject instanceof FallingPlatform) {
                ((FallingPlatform) gameObject).setBrickPhotosSet(false);
                for (Brick brick : ((FallingPlatform) gameObject).getBricks()) {
                    brick.setPause(new PauseTransition());
                }
            }
            if (gameObject.isStaged()) {
                if (gameObject instanceof RedOrc) {
                    ImageView img = new ImageView(new Image("redOrc.jpeg"));
                    ((RedOrc) gameObject).setOrcImage(img);
                    gameObjectsPane.getChildren().add(img);
                } else if (gameObject instanceof GreenOrc) {
                    ImageView img = new ImageView(new Image("greenOrc.png"));
                    ((GreenOrc) gameObject).setOrcImage(img);
                    gameObjectsPane.getChildren().add(img);
                } else if (gameObject instanceof Coin) {
                    int maximumCoinWidth = 30;
                    double distanceBetweenCoins = 50;
                    ImageView img = new ImageView();
                    Image image = new Image("coin.png");
                    img.setImage(image);
                    img.setFitHeight(maximumCoinWidth);
                    img.setFitWidth(maximumCoinWidth);
                    ((Coin) gameObject).setCoinImage(img);
                    this.getGameObjectsPane().getChildren().add(img);
                } else if (gameObject instanceof TreasureChest) {
                    ImageView img = new ImageView(new Image("chestClosed.png"));
                    ((TreasureChest) gameObject).setChestImage(img);
                    this.getGameObjectsPane().getChildren().add(img);
                } else if (gameObject instanceof Shurikens) {
                    ImageView img = new ImageView(new Image("shurikenBullet.png"));
                    ((Shurikens) gameObject).setShurikenImage(img);
                    this.getGameObjectsPane().getChildren().add(img);
                } else if (gameObject instanceof Platform) {
                    Platform p = (Platform) gameObject;
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
                } else if (gameObject instanceof FallingPlatform) {
                    ((FallingPlatform) gameObject).setBrickPhotosSet(false);
                    FallingPlatform p = (FallingPlatform) gameObject;
                    //p.getRectangleForPlatform().setOpacity(0);
                    Image img = new Image("FallingPlatformNode.png");
                    int brickNumber = 0;
                    int numberOfBricks = p.getBricks().size();
                    for (int i = 0; i < numberOfBricks; i++) {
                        System.out.println("Adding brick number " + i);
                        ImageView imgview = new ImageView(img);
                        p.getBricks().get(i).setBrickImage(imgview, brickNumber);
                        gameObjectsPane.getChildren().add(imgview);
                        brickNumber++;
                    }
                    p.setBrickPhotosSet(true);
                }
            }
        }

        game.getHero().setGame(game);

        setUpGeneralClick();
        setUpMasterKeyFrame();
        setUpNumberKeyPressed();

        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(master);
        timeline.play();
        staticPane.setOnMouseClicked(onGeneralClick);
        staticPane.setOnKeyPressed(onNumberKey);

    }

    public void loadSavedGame() throws IOException, ClassNotFoundException {

        displayGameObjectsAfterLoading();

    }

    public void setUpHeroStepKeyFrame(int refreshTime, int animationTime) {
        heroStep = new KeyFrame(Duration.millis(refreshTime), e -> {

            game.getHero().setyVelocity(0);
            double savedGravity = game.getGravity();
            game.setGravity(0);

            for (Solid x : game.getSolidList()) {
                if (!(x instanceof Shurikens)) {
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

    public void setUpNumberKeyPressed() {
        onNumberKey = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println("IN EVENT HANDLER FOR KEYSSSSS");
                if (keyEvent.getCode().equals(KeyCode.DIGIT1) || keyEvent.getCode().equals(KeyCode.NUMPAD1)) {
                    game.getHero().setEquippedWeapon(game.getHero().getHelmet().getWeapon1());
                }
                if (keyEvent.getCode().equals(KeyCode.DIGIT2) || keyEvent.getCode().equals(KeyCode.NUMPAD2)) {
                    game.getHero().setEquippedWeapon(game.getHero().getHelmet().getWeapon2());
                }
                if (keyEvent.getCode().equals(KeyCode.SPACE)) {
                    mainClick();
                }

            }
        };
    }

    public void mainClick() {
        if (game.getHero().getEquippedWeapon() != null) {
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
        game.getHero().setCurrentLocation(game.getHero().getCurrentLocation() + 1);
        currrentLocationLabel.setText(Integer.toString(game.getHero().getCurrentLocation()));
    }

    public void setUpGeneralClick() {

        onGeneralClick = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainClick();
            }
        };
    }

    public void resurrectHero() {
        if (game.getTotalCoins() > 50) {
            game.setTotalCoins(game.getTotalCoins() - 50);
            timeline.play();
            root.getChildren().remove(endGameMenu);
            game.getHero().setPos(new Position(game.getHero().getPos().getxPos(), 100));
        }
    }

    public void checkHeroLife() throws IOException, ClassNotFoundException {
        if (game.getHero().isDead(game.getAbyssLevel())) {
            game.killHero();
        }

    }

    public void garbageArea(Solid gameObject) {
        //Removing Old Objects
        if (gameObject instanceof Platform) {
            game.getPlatformList().remove(gameObject);
        } else if (gameObject instanceof Orc) {
            ((Orc) gameObject).setAlive(false);
        } else if (gameObject instanceof TreasureChest) {
            ((TreasureChest) gameObject).setOpened(true);
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
                //p.getBasePlatform().setOpacity(0);
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


            } else if (gameObject instanceof Coin) {
                Coin c = (Coin) gameObject;
                ImageView img = new ImageView(new Image("coin.png"));
                img.setFitHeight(Coin.maximumCoinWidth);
                img.setFitWidth(Coin.maximumCoinWidth);
                c.setCoinImage(img);
                gameObjectsPane.getChildren().add(img);
            } else if (gameObject instanceof FallingPlatform && !(((FallingPlatform) gameObject).isBrickPhotosSet())) {
                FallingPlatform p = (FallingPlatform) gameObject;
                //p.getRectangleForPlatform().setOpacity(0);
                Image img = new Image("FallingPlatformNode.png");
                int brickNumber = 0;
                int numberOfBricks = p.getBricks().size();
                for (int i = 0; i < numberOfBricks; i++) {
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
            try {
                checkHeroLife();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            removeDeadThings();
            if (game.getHero().getCurrentLocation() == 108 && game.getBoss() == null) {
                game.startBossFight();
            }
            if (game.getHero().getCurrentLocation() == 122) {
                game.heroWon();
            }


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
        for (Orc x : game.getOrcList()) {
//            if(x.getPos().getxPos() > game.getAbyssLevel()){
//                x.setAlive(false);
//                if(x.isStaged()){
//                    x.getOrcImage().setImage(null);
//
//                }
//
//            }
            if (!x.isAlive()) {

                game.getSolidList().remove(x);
            }
        }
        for (Solid x : game.getWeaponList()) {
            if (x instanceof Shurikens) {
                if (!(((Shurikens) x).isLive)) {
                    game.getSolidList().remove(x);
                }

            }
        }
        for (TreasureChest x : game.getTreasureChestList()) {
            if (x.isOpened()) {
                game.getSolidList().remove(x);
//                x.setChestImage(null);
                gameObjectsPane.getChildren().remove(x.getChestImage());
            }

        }

    }

    public void loadGame(ActionEvent event, int gameNum) throws IOException, ClassNotFoundException {
        Main.loadGame(event, gameNum);
    }

    public void exitToMainPage() throws IOException, ClassNotFoundException{
        Main.exitToMainMenu();
    }

    public void playFromPause() {
        root.getChildren().remove(pauseGameMenu);
        timeline.play();


    }


    public void restartGame(ActionEvent event) throws IOException, ClassNotFoundException {
        Main.restartGame(event);

    }

    public void exit() {
        System.out.println("Goodbye Fellow Adventurer");
        System.out.println("""
                Credits - 
                Coded and Designed by:
                Tanishk Goyal (2020141) - Amazing Developer
                Diksha Sethi (2020056) - Developer
                Adios!
                """);
        System.exit(0);
    }

    public void loadGameNumber1(ActionEvent event) throws IOException, ClassNotFoundException {
        //System.out.println(SavedGameLocation1.getText() + "Label");
        if(SavedGameLocation1.getText().equals("Location : 0") && SavedGameCoin1.getText().equals("Coins : 0")){
            System.out.println("Reached");
            onPlayGameClick();
        }

        else
            loadGame(event, 1);
    }

    public void loadGameNumber2(ActionEvent event) throws IOException, ClassNotFoundException {
        //System.out.println(SavedGameLocation2.getText() + "Label");
        if(SavedGameLocation2.getText().equals("Location : 0") && SavedGameCoin2.getText().equals("Coins : 0"))
            onPlayGameClick();
        else
            loadGame(event, 2);

    }

    public void loadGameNumber3(ActionEvent event) throws IOException, ClassNotFoundException {
        if(SavedGameLocation3.getText().equals("Location : 0") && SavedGameCoin3.getText().equals("Coins : 0"))
            onPlayGameClick();
        else
            loadGame(event, 3);


    }

    public void setLoadGameDetails(String filename, int gameNumber){
        ObjectInputStream in = null;
        try{
            in = new ObjectInputStream(new FileInputStream(filename));
            Game loadedGame = (Game)in.readObject();
            setLabelsForLoadGames(gameNumber, loadedGame);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadGameButtonClick() {
        root.getChildren().remove(MainMenu);
        File f = new File("StoringGame1");
        if(f.exists()){
            setLoadGameDetails("StoringGame1", 1);
        }
        f = new File("StoringGame2");
        if(f.exists()){
            setLoadGameDetails("StoringGame2", 2);
        }
        f = new File("StoringGame3");
        if(f.exists()){
            setLoadGameDetails("StoringGame3", 3);
        }
        root.getChildren().add(loadGameMenu);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        root.getChildren().remove(pauseGameMenu);
        root.getChildren().remove(loadGameMenu);
        root.getChildren().remove(endGameMenu);


    }
}
