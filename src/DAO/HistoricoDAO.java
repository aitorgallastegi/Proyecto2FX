package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Historico;
import model.Alumno;
import model.ConexionBD;

public class HistoricoDAO {

	private static ConexionBD conexion;
	
	public static ArrayList<Historico> read() throws ClassNotFoundException, SQLException {
		conexion = new ConexionBD();
		Connection conn = conexion.getConexion();
		PreparedStatement ps = conn.prepareStatement("select id_prestamo, dni_alumno, codigo_libro, fecha_prestamo, fecha_devolucion from Historico_prestamo");
	    ResultSet rs = ps.executeQuery();
	    ArrayList<Historico> historicoList = new ArrayList<>();
	    while (rs.next()) {
	    	Historico historico = new Historico(rs.getInt("id_prestamo"), rs.getString("dni_alumno"), rs.getInt("codigo_libro"), rs.getDate("fecha_prestamo"), rs.getDate("fecha_devolucion"));
	    	historicoList.add(historico);
	    }
	    rs.close();
		ps.close();
		conn.close();
	    return historicoList;
	}
	
	public void insert(Historico historico)throws SQLException {
		conexion = new ConexionBD();
		Connection conn = conexion.getConexion();
		PreparedStatement ps = conn.prepareStatement("insert into Historico_prestamo (id_prestamo, dni_alumno, codigo_libro, fecha_prestamo, fecha_devolucion) values (" + historico.getId_prestamo() + ",'"+ historico.getDni_alumno() + "',"+ historico.getCodigo_libro() + ",'"+ historico.getFecha_prestamo() + "','"+ historico.getFecha_devolucion()+"')");
		ps.executeUpdate();
		ps.close();
		conn.close();
			
		
	}
	
	
	
	
}
