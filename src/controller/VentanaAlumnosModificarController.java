package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


import DAO.AlumnoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Alumno;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

public class VentanaAlumnosModificarController implements Initializable{
	@FXML
	private ComboBox cbAlumnos;
	@FXML
	private TextField txtDni;
	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtApellido1;
	@FXML
	private TextField txtApellido2;
	@FXML
	private Button btnCancelar;
	
	
	private String dni;
	private AlumnoDAO alumno = new AlumnoDAO();
	
	
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			ArrayList<Alumno> a = alumno.read();
			for (int i = 0; i < a.size(); i++) {
				cbAlumnos.getItems().add(a.get(i));//.getNombre()+" "+a.get(i).getApellido1()+" "+a.get(i).getApellido2()
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		cbAlumnos.setOnAction((event) -> {
		    int selectedIndex = cbAlumnos.getSelectionModel().getSelectedIndex();
		    Object selectedItem = cbAlumnos.getSelectionModel().getSelectedItem();

		    System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
		    System.out.println("   ComboBox.getValue(): " + cbAlumnos.getValue());
		    dni = ((Alumno) selectedItem).getDni();
		    
		    txtDni.setText(((Alumno) selectedItem).getDni());
		    txtNombre.setText(((Alumno) selectedItem).getNombre());
		    txtApellido1.setText(((Alumno) selectedItem).getApellido1());
		    txtApellido2.setText(((Alumno) selectedItem).getApellido2());
		    
		    
		});

	}
	public void modificar(ActionEvent event) throws SQLException{
		
		Alumno a = new Alumno(txtDni.getText().toString(),txtNombre.getText().toString(),txtApellido1.getText().toString(),txtApellido2.getText().toString());
		alumno.update(a,dni);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Notificacion");
		alert.setHeaderText("EXITO!!!");
		alert.setContentText("El alumno ha sido Modificado correctamente");

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
	public void cancelar(ActionEvent event) {
	    Stage stage = (Stage) btnCancelar.getScene().getWindow();
	    stage.close();
	    
	    try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/VentanaAlumnos.fxml"));
            Scene scene1 = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage1 = new Stage();
            stage1.setTitle("Alumnos");
            stage1.setScene(scene1);
            stage1.show();
        } catch (IOException e) {
            System.out.println(e);
        }
	}
	
		
	
	

}
