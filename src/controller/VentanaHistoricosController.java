package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.HistoricoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Historico;
import javafx.scene.control.TableColumn;

public class VentanaHistoricosController implements Initializable{
	@FXML
	private TableView tablaHistoricos;
	@FXML
	private TableColumn colId;
	@FXML
	private TableColumn colDni;
	@FXML
	private TableColumn colCodigo;
	@FXML
	private TableColumn colPrestamo;
	@FXML
	private TableColumn colDevolucion;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		colId.setCellValueFactory(new PropertyValueFactory<Historico, String>("id_prestamo"));
		colDni.setCellValueFactory(new PropertyValueFactory<Historico, String>("dni_alumno"));
		colCodigo.setCellValueFactory(new PropertyValueFactory<Historico, String>("codigo_libro"));
		colPrestamo.setCellValueFactory(new PropertyValueFactory<Historico, String>("fecha_prestamo"));
		colDevolucion.setCellValueFactory(new PropertyValueFactory<Historico, String>("fecha_devolucion"));
		HistoricoDAO historico = new HistoricoDAO();
		try {
			ArrayList<Historico> a = historico.read();
			for (int i = 0; i < a.size(); i++) {
				tablaHistoricos.getItems().add(a.get(i));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

	}
	public void volver(ActionEvent event) {
		Stage stage = (Stage) tablaHistoricos.getScene().getWindow();
 	    stage.close();
	}

}
