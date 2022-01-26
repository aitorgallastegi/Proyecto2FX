package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;

public class VentanaController {
	@FXML
	private Button btnAlumnos;
	@FXML
	private Button btnLibros;
	@FXML
	private Button btnPrestamos;

	// Event Listener on Button[#btnAlumnos].onAction
	@FXML
	public void ventanaAlumnos(ActionEvent event) {
	        try {
	            FXMLLoader fxmlLoader = new FXMLLoader();
	            fxmlLoader.setLocation(getClass().getResource("/fxml/VentanaAlumnos.fxml"));
	            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
	            Stage stage = new Stage();
	            stage.setTitle("Alumnos");
	            stage.setScene(scene);
	            stage.show();
	        } catch (IOException e) {
	            System.out.println(e);
	        }
	    }
	
	// Event Listener on Button[#btnLibros].onAction
	@FXML
	public void ventanaLibros(ActionEvent event) {
		try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/VentanaLibros.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Libros");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
	}
	// Event Listener on Button[#btnPrestamos].onAction
	@FXML
	public void ventanaPrestamos(ActionEvent event) {
		try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/VentanaPrestamos.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Prestamos");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
	}
	public void historicos(ActionEvent event) {
		try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/VentanaHistoricos.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Prestamos");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
	}
	
}
