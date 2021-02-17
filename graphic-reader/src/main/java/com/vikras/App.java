package com.vikras;

import com.vikras.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Locale;

/**
 * main class which run javafx application
 */
public class App extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Locale.setDefault(Locale.ENGLISH);
        FXMLLoader loader = new FXMLLoader(App.class.getResource("main.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Color converter");
        primaryStage.setScene(new Scene(root, 1000, 750));
        MainController controller = loader.getController();


        Image icon = new Image(App.class.getResourceAsStream("main_icon.jpg"));
        primaryStage.getIcons().add(icon);

        primaryStage.show();

        controller.init(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}


