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
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.HistoricoDAO;
import DAO.LibroDAO;
import DAO.PrestamoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Alumno;
import model.Historico;
import model.Libro;
import model.Prestamo;

public class VentanaPrestamosDevolverController implements Initializable{
	@FXML
	private ComboBox comboPrestamos,comboEstado;
	@FXML
	private Button btnDevolver;
	@FXML
	private Button btnCancelar;
	
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		PrestamoDAO prestamo = new PrestamoDAO();
		try {
			ArrayList<Prestamo> a = prestamo.read();
			for (int i = 0; i < a.size(); i++) {
				comboPrestamos.getItems().add(a.get(i));
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
		comboEstado.getSelectionModel().selectFirst();

	}

	// Event Listener on Button[#btnDevolver].onAction
	@FXML
	public void devolver(ActionEvent event) throws SQLException {
		Object selectedItem = comboPrestamos.getSelectionModel().getSelectedItem();
		int id = ((Prestamo) selectedItem).getId_prestamo();
		int codigo = ((Prestamo) selectedItem).getCodigo_libro();
		Date datePrestado =  ((Prestamo) selectedItem).getFecha_prestamo();
		Date dateDevuelto = new Date(System.currentTimeMillis());
		String dni = ((Prestamo) selectedItem).getDni_alumno();
		
		String estado = comboEstado.getValue().toString();
		
		PrestamoDAO prestamo = new PrestamoDAO();
		LibroDAO libro = new LibroDAO();
		HistoricoDAO historico = new HistoricoDAO();
		prestamo.delete(id);
		libro.updateEstado(estado, codigo);
		Historico h = new Historico(id,dni,codigo,datePrestado,dateDevuelto);
		historico.insert(h);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Notificacion");
		alert.setHeaderText("EXITO!!!");
		alert.setContentText("El Prestamo ha sido devuelto correctamente");

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

				Stage stage = (Stage) comboEstado.getScene().getWindow();
				stage.close();
			}
		});
		
		
		
	}
	// Event Listener on Button[#btnCancelar].onAction
	@FXML
	public void cancelar(ActionEvent event) {
		Stage stage = (Stage) comboEstado.getScene().getWindow();
 	    stage.close();
	}
}
