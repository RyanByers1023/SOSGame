package sos.ryanbyers.input;

public class InputValidator{
    public void AlertNoGamemodeChosen(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No game mode selected.");
        alert.setHeaderText(null);
        alert.setContentText("Please select a game mode.");
        alert.showAndWait();
    }

    public void AlertPieceNotSelected(){
        
    }
}