module com.asgn.algorithmasgn1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;


    opens com.asgn.algorithmasgn1 to javafx.fxml;
    exports com.asgn.algorithmasgn1;
    opens Resources to xstream;
    exports models to xstream;
    opens models to xstream;
}