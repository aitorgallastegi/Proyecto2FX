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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Alumno;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

public class VentanaAlumnosController implements Initializable{
	@FXML
	private TableView<Alumno> tablaAlumnos;
	@FXML
	private TableColumn<Alumno,String> colDni;
	@FXML
	private TableColumn<Alumno,String> colNombre,colApellido1,colApellido2;
	@FXML
	private Button btnModificar,btnAñadir,btnVolver;
	
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		colDni.setCellValueFactory(new PropertyValueFactory<Alumno, String>("dni"));
		colNombre.setCellValueFactory(new PropertyValueFactory<Alumno, String>("nombre"));
		colApellido1.setCellValueFactory(new PropertyValueFactory<Alumno, String>("apellido1"));
		colApellido2.setCellValueFactory(new PropertyValueFactory<Alumno, String>("apellido2"));
		AlumnoDAO alumno = new AlumnoDAO();
		try {
			ArrayList<Alumno> a = alumno.read();
			for (int i = 0; i < a.size(); i++) {
				tablaAlumnos.getItems().add(a.get(i));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

	}
	public void agregarAlumnos(ActionEvent event) {
		try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/VentanaAlumnosAgregar.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Agregar Alumnos");
            stage.setScene(scene);
            stage.show();
            
            Stage stage1 = (Stage) btnAñadir.getScene().getWindow();
    	    stage1.close();
        } catch (IOException e) {
            System.out.println(e);
        }
	}
	public void modificarAlumnos(ActionEvent event) {
		try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/VentanaAlumnosModificar.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Modificar Alumnos");
            stage.setScene(scene);
            stage.show();
            
            Stage stage1 = (Stage) btnModificar.getScene().getWindow();
    	    stage1.close();
        } catch (IOException e) {
            System.out.println(e);
        }
	}
	public void volver(ActionEvent event) {
		Stage stage2 = (Stage) btnVolver.getScene().getWindow();
    	stage2.close();
	}

}
