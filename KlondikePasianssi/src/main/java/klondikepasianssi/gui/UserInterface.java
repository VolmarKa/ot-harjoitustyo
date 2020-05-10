package klondikepasianssi.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UserInterface extends Application {

    private boolean playClickedFirstTime = true;

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameScreen gameScreen = new GameScreen();
        //gamePane.setGridLinesVisible(true);
        MainMenu mainMenu = new MainMenu();
        Scene gameScene = new Scene(gameScreen, 800, 600);

        mainMenu.getPlayButton().setOnMouseClicked(event -> {
            primaryStage.setScene(gameScene);
            gameScreen.createTimer();

        });

        mainMenu.getExitButton().setOnMouseClicked(event -> {
            primaryStage.close();
        });

        Scene mainScene = new Scene(mainMenu, 800, 800);

        gameScreen.getMenuButton().setOnMouseClicked(event -> {
            primaryStage.setScene(mainScene);
            gameScreen.getTimer().cancel();

        });

        primaryStage.setScene(mainScene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(UserInterface.class
        );
    }

}
