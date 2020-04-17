package klondikePasianssi.logics;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import klondikePasianssi.gui.Card;

public class Movement {

    private final Card card;

    public Movement(Card card) {
        this.card = card;
        
        
        this.card.setOnDragDetected(e -> {
            dragDetected(e);
        });
        
        this.card.setOnDragOver(e -> {
            dragOver(e);
        });
        
        this.card.setOnDragDropped(e -> {
            dragDropped(e);
        });
        
        this.card.setOnDragDone(e -> {
            dragDone(e);
        });
    }

    private void dragDetected(MouseEvent event) {

        if (card == null) {
            event.consume();
            return;
        }
        System.out.println("detected");
        Dragboard dragboard = card.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putImage(card.getImage());
        content.putString(card.toString());
        dragboard.setContent(content);  
        event.consume();
            
    }
    
    private void dragOver(DragEvent event){
        
        Dragboard dragboard = event.getDragboard();
        
        if(dragboard.hasImage() && dragboard.hasString()){
            event.acceptTransferModes(TransferMode.MOVE);
            System.out.println("over");
        }
        
        
        event.consume();
    }
    
    private void dragDropped(DragEvent event){
        
        Dragboard db = event.getDragboard();
        
        if(db.hasString() && db.hasImage()){
            System.out.println("Hienoa");
            event.setDropCompleted(true);
        }
        else{
            event.setDropCompleted(false);
            System.out.println("voivoi");
        }
        
        event.consume();    
    }
    
    private void dragDone(DragEvent e){
        TransferMode modeUsed = e.getTransferMode();
        
        if(modeUsed == TransferMode.MOVE){
            System.out.println("jee taas");
        }
        e.consume();
    }
    
    

}
