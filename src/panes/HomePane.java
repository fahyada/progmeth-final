package panes;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static utils.GetData.getFont;
import static utils.GetData.getImage;

public class HomePane extends VBox {
    // fields
    private GamePanel gamePanel;

    // constructors
    public HomePane() {
        setGamePanel(new GamePanel(768, 526));

        Image homeImage = getImage("Background/Title.JPG");
        BackgroundSize backgroundSize = new BackgroundSize(768,526,false,false,true,true);
        BackgroundImage backgroundImage = new BackgroundImage(homeImage,BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.DEFAULT,backgroundSize);
        setBackground(new Background(backgroundImage));

        getChildren().add(startGameButton());
        getChildren().add(quitButton());
    }

    // methods
    public Button startGameButton() {
        Button startGameButton = new Button("Start Game");
        startGameButton.setPrefWidth(200);
        startGameButton.setPrefHeight(50);
        startGameButton.setBackground(new Background(new BackgroundFill(Color.PINK,new CornerRadii(10), Insets.EMPTY)));
        startGameButton.setFont(getFont("Font/Retro Gaming.ttf", 20));
        startGameButton.setTextFill(Color.WHITE);
        startGameButton.setTranslateX(384 - startGameButton.getPrefWidth() / 2);
        startGameButton.setTranslateY(320);
        startGameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                getChildren().clear();
                getChildren().add(gamePanel);
                gamePanel.startGameThread();
            }
        });

        return startGameButton;
    }

    public Button quitButton() {
        Button quitButton = new Button("Quit Game");
        quitButton.setPrefWidth(200);
        quitButton.setPrefHeight(50);
        quitButton.setBackground(new Background(new BackgroundFill(Color.PINK,new CornerRadii(10), Insets.EMPTY)));
        quitButton.setFont(getFont("Font/Retro Gaming.ttf", 20));
        quitButton.setTextFill(Color.WHITE);
        quitButton.setTranslateX(384 - startGameButton().getPrefWidth() / 2);
        quitButton.setTranslateY(340);
        quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Platform.exit();
            }
        });
        return quitButton;
    }


    // getters setters
    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
