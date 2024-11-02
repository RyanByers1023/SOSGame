module sos.ryanbyers.sosjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens sos.ryanbyers.sosjavafx to javafx.fxml;
    exports sos.ryanbyers.input;
    opens sos.ryanbyers.input to javafx.fxml;
    exports sos.ryanbyers.gameLogic;
    opens sos.ryanbyers.gameLogic to javafx.fxml;
    exports sos.ryanbyers.gui;
    opens sos.ryanbyers.gui to javafx.fxml;
    opens sos.ryanbyers.driver to javafx.fxml;
    exports sos.ryanbyers.driver;
}