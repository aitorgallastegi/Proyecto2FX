package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Alumno;
import model.ConexionBD;

public class AlumnoDAO {

	private static ConexionBD conexion;
	public static ArrayList<Alumno> read() throws ClassNotFoundException, SQLException {
		conexion = new ConexionBD();
		Connection conn = conexion.getConexion();
		PreparedStatement ps = conn.prepareStatement("select dni,nombre,apellido1,apellido2 from Alumno");
	    ResultSet rs = ps.executeQuery();
	    ArrayList<Alumno> alumnoList = new ArrayList<>();
	    while (rs.next()) {
	    	Alumno alumno = new Alumno(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"));
	    	alumnoList.add(alumno);
	    }
	    rs.close();
		ps.close();
		conn.close();
	    return alumnoList;
	}
	
	public void insert(Alumno alumno)throws SQLException {
		conexion = new ConexionBD();
		Connection conn = conexion.getConexion();
		PreparedStatement ps = conn.prepareStatement("insert into Alumno (dni,nombre,apellido1,apellido2) values ('" + alumno.getDni() + "','"+ alumno.getNombre() + "','"+ alumno.getApellido1() + "','"+ alumno.getApellido2() + "')");
		ps.executeUpdate();
		ps.close();
		conn.close();
			
		
	}
	
	public void delete(String dni) throws SQLException{
		conexion = new ConexionBD();
		Connection conn = conexion.getConexion();
		PreparedStatement ps = conn.prepareStatement("delete from Alumno where dni = ?");
		ps.setString(1, dni);
		ps.executeUpdate();
		ps.close();
		conn.close();
	}
	
	public void update(Alumno alumno, String dni) throws SQLException{
		conexion = new ConexionBD();
		Connection conn = conexion.getConexion();
		PreparedStatement ps = conn.prepareStatement("UPDATE Alumno SET dni=" + "'" + alumno.getDni()+ "'" + ", nombre=" + "'" + alumno.getNombre()+ "'" + ", apellido1=" + "'" + alumno.getApellido1()+ "'" + ", apellido2=" + "'" + alumno.getApellido2() + "' WHERE dni=?");
		ps.setString(1, dni);
		ps.executeUpdate();
		ps.close();
		conn.close();
	}
	
	
}
