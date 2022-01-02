package com.example.ap_willhero;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application{

    GameController Controller;
    static Game currGame;


    public static void loadGame(ActionEvent event) throws IOException, ClassNotFoundException {
        deserialize(1);
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("Game.fxml"));
        Scene currScene = new Scene(loader.load(), 1024, 600 );
        GameController newGC = loader.getController();
        newGC.setUpNumberKeyPressed();
        currScene.setOnKeyPressed(newGC.getOnNumberKey());

        newGC.setGame(currGame);
        currGame.setGameController(newGC);
        newGC.loadSavedGame();

        newGC.setStage((Stage)((Node)event.getSource()).getScene().getWindow());
        newGC.getStage().setScene(currScene);
        newGC.getRoot().getChildren().remove(newGC.getMainMenu());
//        newGC.setUpGeneralClick();
//        newGC.setUpMasterKeyFrame();

        newGC.getStage().show();

    }

    public static void deserialize(int chosenGame) throws IOException, ClassNotFoundException{
        ObjectInputStream in = null;
        try{
            String filename = "D:\\Second Year\\First Sem\\AP\\AP Project\\AP_WillHero\\" + "StoringGame" + chosenGame;
            in = new ObjectInputStream(new FileInputStream(filename));
            Game loadedGame = (Game)in.readObject();
            currGame = loadedGame;

        }
        catch (EOFException e) {
            return ;
        }catch (ClassCastException e) {
            System.out.println("Invalid Class Cast Exception");
        }
        finally {
            in.close();
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Game.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 600);
        Image icon = new Image("icon.png");


        GameController Controller = fxmlLoader.getController();
        Controller.setUpNumberKeyPressed();
        scene.setOnKeyPressed(Controller.getOnNumberKey());
        stage.getIcons().add(icon);
        stage.setTitle("Will Hero by Tanishk Goyal and Diksha Sethi");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}