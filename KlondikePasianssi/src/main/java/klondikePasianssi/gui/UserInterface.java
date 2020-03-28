
package klondikePasianssi.gui;

import javafx.application.Application;
import javafx.stage.Stage;


public class UserInterface extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("moi");
        primaryStage.show();
    }
    
    
    public static void main(String[] args){
        launch(UserInterface.class);
    }
}
