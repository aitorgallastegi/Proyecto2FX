package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.LibroDAO;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Alumno;
import model.Libro;
import javafx.scene.control.TableColumn;

public class VentanaLibrosController implements Initializable{
	@FXML
	private TableView tablaLibros;
	
	@FXML
	private TableColumn colTitulo,colAutor,colEditorial,colEstado;
	@FXML
	private Button btnAñadir;
	@FXML
	private Button btnModificar;
	@FXML
	private Button btnVolver;

	public void initialize(URL arg0, ResourceBundle arg1) {
		colTitulo.setCellValueFactory(new PropertyValueFactory<Alumno, String>("titulo"));
		colAutor.setCellValueFactory(new PropertyValueFactory<Alumno, String>("autor"));
		colEditorial.setCellValueFactory(new PropertyValueFactory<Alumno, String>("editorial"));
		colEstado.setCellValueFactory(new PropertyValueFactory<Alumno, String>("estado"));
		LibroDAO libro = new LibroDAO();
		try {
			ArrayList<Libro> a = libro.read();
			for (int i = 0; i < a.size(); i++) {
				tablaLibros.getItems().add(a.get(i));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

	}
	// Event Listener on Button[#btnAñadir].onAction
	@FXML
	public void agregarLibros(ActionEvent event) {
		try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/VentanaLibrosAgregar.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Agregar Libros");
            stage.setScene(scene);
            stage.show();
            
            Stage stage1 = (Stage) btnAñadir.getScene().getWindow();
    	    stage1.close();
        } catch (IOException e) {
            System.out.println(e);
        }
	}
	// Event Listener on Button[#btnModificar].onAction
	@FXML
	public void modificarLibros(ActionEvent event) {
		try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/VentanaLibrosModificar.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 700);
            Stage stage = new Stage();
            stage.setTitle("Modificar Libros");
            stage.setScene(scene);
            stage.show();
            
            Stage stage1 = (Stage) btnModificar.getScene().getWindow();
    	    stage1.close();
        } catch (IOException e) {
            System.out.println(e);
        }
	}
	// Event Listener on Button[#btnVolver].onAction
	@FXML
	public void volver(ActionEvent event) {
		Stage stage1 = (Stage) btnModificar.getScene().getWindow();
	    stage1.close();
	}
}
