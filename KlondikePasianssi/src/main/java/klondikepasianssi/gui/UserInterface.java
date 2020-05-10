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
        MainMenu mainPane = new MainMenu();
        Scene gameScene = new Scene(gameScreen, 800, 600);

        mainPane.getPlayButton().setOnMouseClicked(event -> {
            primaryStage.setScene(gameScene);
            gameScreen.createTimer();

        });

        mainPane.getExitButton().setOnMouseClicked(event -> {
            primaryStage.close();
        });

        Scene mainScene = new Scene(mainPane, 800, 800);

        gameScreen.getMenuButton().setOnMouseClicked(event -> {
            primaryStage.setScene(mainScene);
            //gameScreen.getTimer().cancel();

        });

        primaryStage.setScene(mainScene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(UserInterface.class
        );
    }

}
