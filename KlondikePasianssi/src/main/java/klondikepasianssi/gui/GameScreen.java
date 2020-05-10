package klondikepasianssi.gui;

import java.util.Timer;
import java.util.TimerTask;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class GameScreen extends BorderPane {

    private GameView gameView;
    private Text time;
    private Timer timer;
    private Button menuButton;
    private Button reverseButton;
    private int secondsPassed = 0;
    private int minutesPassed = 0;
    private int hoursPassed = 0;

    public GameScreen() {
        this.setBackground(new Background(new BackgroundFill(Color.GREEN,
                CornerRadii.EMPTY, new Insets(5, 5, 5, 5))));

        this.gameView = new GameView();
        init();
    }

    private void init() {

        HBox gameScreenButtons = new HBox();
        this.setBottom(gameScreenButtons);
        menuButton = new Button();
        menuButton.setGraphic(new ImageView(new Image("/lopullinenmenu.png")));
        menuButton.setStyle("-fx-background-color: transparent;");
        menuButton.setCursor(Cursor.HAND);
        this.setRight(menuButton);
        menuButton.setAlignment(Pos.BOTTOM_RIGHT);

        reverseButton = new Button();
        reverseButton.setGraphic(new ImageView(new Image("/undo.png")));
        reverseButton.setStyle("-fx-background-color: transparent;");
        reverseButton.setCursor(Cursor.HAND);
        reverseButton.setOnMouseClicked(event -> {
            this.gameView.getReverseMove().reverseMove();
        });

        gameScreenButtons.getChildren().add(reverseButton);

        time = new Text();

        time.setFont(Font.font("Impact", 25));
        gameScreenButtons.getChildren().add(time);

        this.setTop(this.gameView);

    }

    public void createTimer() {

        timer = new Timer();
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                secondsPassed++;
                if (secondsPassed == 60) {
                    secondsPassed = 0;
                    minutesPassed++;
                    if (minutesPassed == 60) {
                        minutesPassed = 0;
                        hoursPassed++;
                    }
                }

                time.setText(Integer.toString(hoursPassed) + ":" + Integer.toString(minutesPassed) + ":" + Integer.toString(secondsPassed));
                if (gameView.getUpperRightManager().gameEnded()) {
                    timer.cancel();
                    
                }
            }
        };
        timer.schedule(timerTask, 0, 1000);

    }

    public Timer getTimer() {
        return this.timer;
    }

    public Button getMenuButton() {
        return this.menuButton;
    }

    public Button getReverseButton() {
        return this.reverseButton;
    }

}
