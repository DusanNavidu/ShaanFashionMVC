module lk.ijse.gdse72.shaanfashion {
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires java.desktop;
    requires com.jfoenix;
    requires javafx.controls;
    requires java.mail;
    requires net.sf.jasperreports.core;
    //requires com.google.zxing;

    opens lk.ijse.gdse72.shaanfashion.dto.tm to javafx.base;
    opens lk.ijse.gdse72.shaanfashion.controller to javafx.fxml;
    opens lk.ijse.gdse72.shaanfashion to javafx.fxml;
    exports lk.ijse.gdse72.shaanfashion.controller to javafx.fxml;
    exports lk.ijse.gdse72.shaanfashion;
}