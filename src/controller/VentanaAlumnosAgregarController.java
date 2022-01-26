package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.AlumnoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import model.Alumno;

public class VentanaAlumnosAgregarController implements Initializable{
	@FXML
	private TextField txtDni;
	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtApellido1;
	@FXML
	private TextField txtApellido2;
	
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		

	}
	public void agregar(ActionEvent event) throws SQLException{
		String dni = txtDni.getText().toString();
		String nombre = txtNombre.getText().toString();
		String apellido1 = txtApellido1.getText().toString();
		String apellido2 = txtApellido2.getText().toString();
		
		AlumnoDAO alumno = new AlumnoDAO();
		Alumno a = new Alumno(dni,nombre, apellido1,apellido2);
		alumno.insert(a);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Notificacion");
		alert.setHeaderText("EXITO!!!");
		alert.setContentText("El alumno ha sido agregado correctamente");

		alert.showAndWait().ifPresent(response -> {
		     if (response == ButtonType.OK) {
		    	 try {
			            FXMLLoader fxmlLoader = new FXMLLoader();
			            fxmlLoader.setLocation(getClass().getResource("/fxml/VentanaAlumnos.fxml"));
			            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
			            Stage stage1 = new Stage();
			            stage1.setTitle("Alumnos");
			            stage1.setScene(scene);
			            stage1.show();
			        } catch (IOException e) {
			            System.out.println(e);
			        }
		    	 
		    	Stage stage = (Stage) txtDni.getScene().getWindow();
		 	    stage.close();
		 	    
		     }
		 });
		
	}
	public void volver(ActionEvent event) {
		Stage stage2 = (Stage) txtDni.getScene().getWindow();
    	stage2.close();
	}
}
