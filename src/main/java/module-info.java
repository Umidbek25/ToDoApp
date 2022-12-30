module sample.todoapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;
    requires mysql.connector.j;


    opens sample.todoapp to javafx.fxml;
    exports sample.todoapp;
    exports sample.todoapp.controller;
    opens sample.todoapp.controller to javafx.fxml;
}