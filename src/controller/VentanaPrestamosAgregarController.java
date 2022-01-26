package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import DAO.AlumnoDAO;
import DAO.LibroDAO;
import DAO.PrestamoDAO;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Alumno;
import model.Libro;
import model.Prestamo;

public class VentanaPrestamosAgregarController implements Initializable {
	@FXML
	private ComboBox comboAlumno;
	@FXML
	private ComboBox comboLibro;
	@FXML
	private DatePicker datepicker;
	@FXML
	private Button btnAgregar;
	@FXML
	private Button btnCancelar;
	@FXML
	private TextField txtId;

	private AlumnoDAO alumno = new AlumnoDAO();
	private LibroDAO libro = new LibroDAO();
	private String dni;
	private int codigo;

	public void initialize(URL arg0, ResourceBundle arg1) {

		try {
			ArrayList<Alumno> a = alumno.read();
			for (int i = 0; i < a.size(); i++) {
				comboAlumno.getItems().add(a.get(i));// .getNombre()+" "+a.get(i).getApellido1()+"
														// "+a.get(i).getApellido2()
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			ArrayList<Libro> a = libro.read();
			for (int i = 0; i < a.size(); i++) {
				comboLibro.getItems().add(a.get(i));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		comboAlumno.setOnAction((event) -> {
			int selectedIndex = comboAlumno.getSelectionModel().getSelectedIndex();
			Object selectedItem = comboAlumno.getSelectionModel().getSelectedItem();
			dni = ((Alumno) selectedItem).getDni();
		});
		comboLibro.setOnAction((event) -> {
			int selectedIndex = comboLibro.getSelectionModel().getSelectedIndex();
			Object selectedItem = comboLibro.getSelectionModel().getSelectedItem();
			codigo = ((Libro) selectedItem).getCodigo();
		});

	}

	// Event Listener on Button[#btnAgregar].onAction
	@FXML
	public void agregar(ActionEvent event) throws SQLException {

		java.util.Date date = java.util.Date
				.from(datepicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		int id = Integer.parseInt(txtId.getText().toString());

		PrestamoDAO prestamo = new PrestamoDAO();
		Prestamo a = new Prestamo(id, dni, codigo, sqlDate);
		
		
		
		
		
		
		
		
		prestamo.insert(a);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Notificacion");
		alert.setHeaderText("EXITO!!!");
		alert.setContentText("El Prestamo ha sido agregado correctamente");

		alert.showAndWait().ifPresent(response -> {
			if (response == ButtonType.OK) {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/fxml/VentanaPrestamos.fxml"));
					Scene scene = new Scene(fxmlLoader.load(), 600, 400);
					Stage stage1 = new Stage();
					stage1.setTitle("Prestamos");
					stage1.setScene(scene);
					stage1.show();
				} catch (IOException e) {
					System.out.println(e);
				}

				Stage stage = (Stage) txtId.getScene().getWindow();
				stage.close();
			}
		});

	}

	// Event Listener on Button[#btnCancelar].onAction
	@FXML
	public void cancelar(ActionEvent event) {
		Stage stage = (Stage) datepicker.getScene().getWindow();
		stage.close();
	}
}
