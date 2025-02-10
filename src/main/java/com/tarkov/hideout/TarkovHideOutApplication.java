package com.tarkov.hideout;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class TarkovHideOutApplication extends Application
{
    private static ConfigurableApplicationContext context;

    public static void main(String[] args)
    {
        context = new SpringApplicationBuilder(TarkovHideOutApplication.class)
                .headless(false)  // Отключаем headless-режим, так как используем GUI
                .run(args);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("/view/main-view.fxml"));
        fxmlLoader.setControllerFactory(context::getBean); // Передаем Spring-контекст в контроллеры

        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        primaryStage.setTitle("Tarkov HideOut");
        primaryStage.setScene(scene);
        System.out.println("FXML file loading");
        primaryStage.show();
    }

    @Override
    public void stop()
    {
        if (context != null) {
            context.close();
        }
    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }
}