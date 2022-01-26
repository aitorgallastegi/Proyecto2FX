package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Alumno;
import model.Libro;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.LibroDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class VentanaLibrosAgregarController implements Initializable{
	@FXML
	private TextField txtCodigo;
	@FXML
	private TextField txtTitulo;
	@FXML
	private TextField txtAutor;
	@FXML
	private TextField txtEditorial;
	@FXML
	private Button btnAgregar;
	@FXML
	private Button btnCancelar;
	@FXML
	private ComboBox comboEstado;
	@FXML
	private CheckBox checkBaja;
	
	
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> items = FXCollections.observableArrayList();
		items.addAll("Nuevo", "Usado nuevo", "Usado seminuevo", "Usado estropeado", "Restaurado");
		for (int i=0;i<items.size();i++) {
			comboEstado.getItems().add(items.get(i));
		}
		comboEstado.getSelectionModel().selectFirst();

	}
	// Event Listener on Button[#btnAgregar].onAction
	@FXML
	public void agregar(ActionEvent event) throws SQLException {
		int codigo = Integer.parseInt(txtCodigo.getText().toString());
		String titulo = txtTitulo.getText().toString();
		String autor = txtAutor.getText().toString();
		String editorial = txtEditorial.getText().toString();
		String estado = comboEstado.getValue().toString();
		
		int baja = 0;
		if (checkBaja.isSelected()){
			baja = 1;
		}

		
		LibroDAO libro = new LibroDAO();
		Libro a = new Libro(codigo,titulo, autor,editorial,estado,baja);
		libro.insert(a);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Notificacion");
		alert.setHeaderText("EXITO!!!");
		alert.setContentText("El Libro ha sido agregado correctamente");

		alert.showAndWait().ifPresent(response -> {
		     if (response == ButtonType.OK) {
		    	 try {
			            FXMLLoader fxmlLoader = new FXMLLoader();
			            fxmlLoader.setLocation(getClass().getResource("/fxml/VentanaLibros.fxml"));
			            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
			            Stage stage1 = new Stage();
			            stage1.setTitle("Alumnos");
			            stage1.setScene(scene);
			            stage1.show();
			        } catch (IOException e) {
			            System.out.println(e);
			        }
		    	 
		    	Stage stage = (Stage) txtCodigo.getScene().getWindow();
		 	    stage.close();
		     }
		 });
	}
	// Event Listener on Button[#btnCancelar].onAction
	@FXML
	public void cancelar(ActionEvent event) {
		Stage stage = (Stage) txtCodigo.getScene().getWindow();
 	    stage.close();
	}
}
