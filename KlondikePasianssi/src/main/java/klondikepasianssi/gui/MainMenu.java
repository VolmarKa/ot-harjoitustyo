package klondikepasianssi.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MainMenu extends BorderPane {

    private Button exitButton;
    private Button playButton;
    private Button infoButton;
    private TextArea infoText;

    public MainMenu() {
        this.setBackground(new Background(new BackgroundImage(new Image("/mainBackground.png"), BackgroundRepeat.SPACE, BackgroundRepeat.SPACE, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        infoButton = infoButton();
        playButton = playButton();
        exitButton = exitButton();
        infoText = infoText();
        this.setTop(playButton);
        playButton.setAlignment(Pos.TOP_LEFT);

        HBox infoPane = new HBox();
        infoPane.getChildren().addAll(infoButton, exitButton);
        this.setBottom(infoPane);
        infoPane.setAlignment(Pos.BOTTOM_RIGHT);

        showInfoText();

    }

    private Button infoButton() {
        Button helpButton = new Button();
        helpButton.setGraphic(new ImageView(new Image("/lopullinen.png")));
        helpButton.setStyle("-fx-background-color: transparent;");
        helpButton.setCursor(Cursor.HAND);

        return helpButton;
    }

    private Button playButton() {
        Button play = new Button();
        play.setGraphic(new ImageView(new Image("/play.png")));
        play.setStyle("-fx-background-color: transparent;");
        play.setCursor(Cursor.HAND);
        return play;
    }

    private Button exitButton() {
        Button exit = new Button();
        exit.setGraphic(new ImageView(new Image("/lopullinenexit.png")));
        exit.setStyle("-fx-background-color: transparent;");
        exit.setCursor(Cursor.HAND);
        return exit;
    }

    private TextArea infoText() {
        TextArea helpText = new TextArea();
        helpText.setText(" Pelipöytä koostuu pakasta, neljästä loppupinosta ja seitsemästä "
                + "pelipinosta, joissa on jokaisessa 1-7 pelikorttia. Kortteja nostetaan \n pakkaa klikkaamalla"
                + "ja niitä voi viedä pelipinoihin hiiren avulla klikkaamalla ja raahaamalla. Kortteja voi myös "
                + "kaksoisklikata, jolloin \n ne menevät suoraan loppupinoihin, jos se on säääntöjen mukaista. Lisäksi liikkeen voi kumota undo painikkeella."
                + "\n"
                + "\n"
                + "Pelipinoissa kortteja voi siirtää päällekkäin vain, jos niiden värit ovat erit, eli punaisia "
                + "kortteja voi siirtää vain mustien korttien päälle \nja mustia kortteja vain punaisien korttien päälle."
                + " Lisäksi siirrettävän kortin numeron tulee olla yhtä pienempi, kuin kohteen numeron. \nKuninkaan voi "
                + "siirtää tyhjälle paikalle ja kortteja voi liikutella nippuina. Pelikortit kerätään niiden maata vastaaviin \n"
                + "loppupinoihin suuruusjärjestyksessä pienimmästä suurimpaan ja pelin voittaa, kun kunkin maan kortit saa "
                + "järjestettyä loppupinoihin.");
        helpText.setMaxHeight(170);
        helpText.setFont(Font.font("Comic Sans MS", USE_PREF_SIZE));
        helpText.setEditable(false);
        helpText.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));
        helpText.setStyle("-fx-inner-color: burlywood;");
        return helpText;
    }

    private void showInfoText() {
        infoButton.setOnMouseClicked((MouseEvent event) -> {
            this.setCenter(infoText);
            hideInfoText();
        });
    }

    private void hideInfoText() {
        infoButton.setOnMouseClicked((MouseEvent event) -> {
            this.getChildren().remove(infoText);
            showInfoText();
        });
    }

    public Button getExitButton() {
        return this.exitButton;
    }

    public Button getPlayButton() {
        return this.playButton;
    }

}
