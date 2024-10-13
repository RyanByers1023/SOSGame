module sos.ryanbyers.sosjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens sos.ryanbyers.sosjavafx to javafx.fxml;
    exports sos.ryanbyers.sosjavafx;
}