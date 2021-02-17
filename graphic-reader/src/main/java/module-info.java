module com.vikras {
    requires javafx.fxml;
    requires javafx.controls;
    requires metadata.extractor;
    requires org.apache.commons.io;
    requires sanselan;



    opens com.vikras to javafx.fxml;
    opens com.vikras.controller to javafx.fxml;
    opens com.vikras.model.processor to javafx.base;
    exports com.vikras;
}