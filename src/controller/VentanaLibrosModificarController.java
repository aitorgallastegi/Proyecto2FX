package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Alumno;
import model.Libro;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.LibroDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

import javafx.scene.control.CheckBox;

public class VentanaLibrosModificarController implements Initializable{
	@FXML
	private TextField txtCodigo;
	@FXML
	private TextField txtTitulo;
	@FXML
	private TextField txtAutor;
	@FXML
	private TextField txtEditorial;
	@FXML
	private ComboBox comboEstado;
	@FXML
	private CheckBox checkBaja;
	@FXML
	private Button btnModificar;
	@FXML
	private Button btnCancelar;
	@FXML
	private ComboBox<Libro> comboLibros;
	
	private LibroDAO libro = new LibroDAO();
	private int codigo,baja;
	private String estado;
	
public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			ArrayList<Libro> a = libro.read();
			for (int i = 0; i < a.size(); i++) {
				comboLibros.getItems().add(a.get(i));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		ObservableList<String> items = FXCollections.observableArrayList();
		items.addAll("Nuevo", "Usado nuevo", "Usado seminuevo", "Usado estropeado", "Restaurado");
		for (int i=0;i<items.size();i++) {
			comboEstado.getItems().add(items.get(i));
		}
		
		
		
		comboLibros.setOnAction((event) -> {
		    int selectedIndex = comboLibros.getSelectionModel().getSelectedIndex();
		    Object selectedItem = comboLibros.getSelectionModel().getSelectedItem();

		    System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
		    System.out.println("   ComboBox.getValue(): " + comboLibros.getValue());
		    
		    
		    codigo = ((Libro) selectedItem).getCodigo();
		    txtCodigo.setText(     Integer.toString(((Libro) selectedItem).getCodigo())           );
		    txtTitulo.setText(((Libro) selectedItem).getTitulo());
		    txtAutor.setText(((Libro) selectedItem).getAutor());
		    txtEditorial.setText(((Libro) selectedItem).getEditorial());
		    comboEstado.getSelectionModel().select(selectedIndex);
		    
		    if (((Libro) selectedItem).getBaja() == 0) {
		    	checkBaja.setSelected(false);
		    }else {
		    	checkBaja.setSelected(true);
		    }
		    
		    
		    
		    
		});
	}

	// Event Listener on Button[#btnModificar].onAction
	@FXML
	public void modificar(ActionEvent event) throws SQLException {
		int codigoNuevo = Integer.parseInt(txtCodigo.getText().toString());
		String titulo = txtTitulo.getText().toString();
		String autor = txtAutor.getText().toString();
		String editorial = txtEditorial.getText().toString();
		String estado = comboEstado.getValue().toString();
		
		int baja = 0;
		if (checkBaja.isSelected()){
			baja = 1;
		}
		
		Libro a = new Libro(codigoNuevo,titulo,autor,editorial,estado,baja);
		libro.update(a,codigo);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Notificacion");
		alert.setHeaderText("EXITO!!!");
		alert.setContentText("El LIBRO ha sido Modificado correctamente");

		alert.showAndWait().ifPresent(response -> {
		     if (response == ButtonType.OK) {
		    	 try {
			            FXMLLoader fxmlLoader = new FXMLLoader();
			            fxmlLoader.setLocation(getClass().getResource("/fxml/VentanaLibros.fxml"));
			            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
			            Stage stage1 = new Stage();
			            stage1.setTitle("Libros");
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
