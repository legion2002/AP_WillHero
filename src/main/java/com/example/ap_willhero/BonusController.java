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

import javax.crypto.KeyAgreementSpi;
import java.io.*;
import java.net.URL;
import java.util.*;

public class BonusController  {
    @FXML
    private Rectangle leftHealth = new Rectangle();
    @FXML
    private Rectangle rightHealth = new Rectangle();
    @FXML
    private ImageView leftImage = new ImageView();
    @FXML
    private ImageView rightImage = new ImageView();
    @FXML
    private Rectangle platformRectange = new Rectangle();




    private BonusHero leftHero;
    private BonusHero rightHero;
    private ArrayList<Solid> solidList;
    private Platform platform;
    private double gravity;
    private KeyFrame master;

    public BonusController() {
        leftHero = new BonusHero(this, leftImage);
        rightHero = new BonusHero(this, rightImage);
        solidList = new ArrayList<>();
        platform = new Platform(platformRectange);
        gravity = 0.008;

    }

    public void collisionArea(Solid gameObject) {

        int collideVal = leftHero.hasCollided(gameObject);
        if (collideVal > 0) {
            leftHero.collidesWith(gameObject, collideVal);
        }
        collideVal = leftHero.hasCollided(gameObject);
        if (collideVal > 0) {
            leftHero.collidesWith(gameObject, collideVal);
        }
    }

    public void physicsHero(int frameTimeInMillis, BonusHero hero) {
        double s = hero.getyVelocity() * frameTimeInMillis + frameTimeInMillis * frameTimeInMillis * gravity / 2;
        hero.translateSolidY(s);
        double v = hero.getyVelocity() + gravity * frameTimeInMillis;
        hero.setyVelocity(v);



    }


    public void physicsEngine(int frameTimeInMillis) {

        physicsHero(frameTimeInMillis, leftHero);
        physicsHero(frameTimeInMillis, rightHero);


    }

    public void setUpMasterKeyFrame() {
        int frameTimeInMillis = 40;
        master = new KeyFrame(Duration.millis(frameTimeInMillis), e -> {
            leftHero.checkHealth();
            rightHero.checkHealth();
            for (Solid gameObject : solidList) {
                collisionArea(gameObject);
            }
            physicsEngine(frameTimeInMillis);


        });
    }

}
