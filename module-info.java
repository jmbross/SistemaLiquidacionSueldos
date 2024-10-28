module SistemaLiquidacionSueldos {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires mysql.connector.j;
	
	opens com.colegio to javafx.fxml;
	opens com.colegio.controllers to javafxxml;
	exports com.colegio;
	exports com.colegio.controllers;
}