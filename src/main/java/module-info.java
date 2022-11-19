module com.asgn.algorithmasgn1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.asgn.algorithmasgn1 to javafx.fxml;
    exports com.asgn.algorithmasgn1;
}