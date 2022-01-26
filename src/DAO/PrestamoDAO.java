package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ConexionBD;
import model.Prestamo;

public class PrestamoDAO {
	private static ConexionBD conexion;
	public static ArrayList<Prestamo> read() throws ClassNotFoundException, SQLException {
		conexion = new ConexionBD();
		Connection conn = conexion.getConexion();
		PreparedStatement ps = conn.prepareStatement("select id_prestamo,dni_alumno,codigo_libro,fecha_prestamo from Prestamo");
	    ResultSet rs = ps.executeQuery();
	    ArrayList<Prestamo> prestamoList = new ArrayList<>();
	    while (rs.next()) {
	    	Prestamo prestamo = new Prestamo(rs.getInt("id_prestamo"), rs.getString("dni_alumno"), rs.getInt("codigo_libro"), rs.getDate("fecha_prestamo"));
	    	prestamoList.add(prestamo);
	    }
	    rs.close();
		ps.close();
		conn.close();
	    return prestamoList;
	}
	
	public void insert(Prestamo prestamo)throws SQLException {
		conexion = new ConexionBD();
		Connection conn = conexion.getConexion();
		PreparedStatement ps = conn.prepareStatement("insert into Prestamo (id_prestamo,dni_alumno,codigo_libro,fecha_prestamo) values (" + prestamo.getId_prestamo() + ",'"+ prestamo.getDni_alumno() + "',"+ prestamo.getCodigo_libro() + ",'"+ prestamo.getFecha_prestamo() + "')");
		ps.executeUpdate();
		ps.close();
		conn.close();
			
		
	}
	public void delete(int id)throws SQLException {
		conexion = new ConexionBD();
		Connection conn = conexion.getConexion();
		PreparedStatement ps = conn.prepareStatement("delete from Prestamo WHERE id_prestamo = ?");
		ps.setInt(1, id);
		ps.executeUpdate();
		ps.close();
		conn.close();
		
	}
}
