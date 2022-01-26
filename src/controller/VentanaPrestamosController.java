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

import DAO.PrestamoDAO;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Alumno;
import model.Prestamo;
import javafx.scene.control.TableColumn;

public class VentanaPrestamosController implements Initializable{
	@FXML
	private TableView tablaPrestamos;
	@FXML
	private TableColumn colId;
	@FXML
	private TableColumn colDni;
	@FXML
	private TableColumn colCodigo;
	@FXML
	private TableColumn colFecha;
	@FXML
	private Button btnAgregar;
	@FXML
	private Button btnDevolver;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		colId.setCellValueFactory(new PropertyValueFactory<Alumno, String>("id_prestamo"));
		colDni.setCellValueFactory(new PropertyValueFactory<Alumno, String>("dni_alumno"));
		colCodigo.setCellValueFactory(new PropertyValueFactory<Alumno, String>("codigo_libro"));
		colFecha.setCellValueFactory(new PropertyValueFactory<Alumno, String>("fecha_prestamo"));
		PrestamoDAO prestamo = new PrestamoDAO();
		try {
			ArrayList<Prestamo> a = prestamo.read();
			for (int i = 0; i < a.size(); i++) {
				tablaPrestamos.getItems().add(a.get(i));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

	}

	// Event Listener on Button[#btnAgregar].onAction
	@FXML
	public void agregar(ActionEvent event) {
		try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/VentanaPrestamosAgregar.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Agregar Prestamos");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
	}
	public void devolver(ActionEvent event) {
		try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/VentanaPrestamosDevolver.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Devolver Prestamos");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
	}
	public void volver(ActionEvent event) {
		Stage stage = (Stage) tablaPrestamos.getScene().getWindow();
 	    stage.close();
	}
}
