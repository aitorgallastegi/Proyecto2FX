package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ConexionBD;
import model.Libro;

public class LibroDAO {

	private static ConexionBD conexion;
	public static ArrayList<Libro> read() throws ClassNotFoundException, SQLException {
		conexion = new ConexionBD();
		Connection conn = conexion.getConexion();
		PreparedStatement ps = conn.prepareStatement("select codigo,titulo,autor,editorial,estado,baja from Libro where baja=0");
	    ResultSet rs = ps.executeQuery();
	    ArrayList<Libro> libroList = new ArrayList<>();
	    while (rs.next()) {
	    	Libro libro = new Libro(rs.getInt("codigo"), rs.getString("titulo"), rs.getString("autor"), rs.getString("editorial"), rs.getString("estado"), rs.getInt("baja"));
	    	libroList.add(libro);
	    }
	    rs.close();
		ps.close();
		conn.close();
	    return libroList;
	}
	
	public void insert(Libro libro)throws SQLException {
		conexion = new ConexionBD();
		Connection conn = conexion.getConexion();
		PreparedStatement ps = conn.prepareStatement("insert into Libro (codigo,titulo,autor,editorial,estado,baja) values (" + libro.getCodigo() + ",'"+ libro.getTitulo() + "','"+ libro.getAutor() + "','"+ libro.getEditorial() + "','"+ libro.getEstado() + "',"+ libro.getBaja() + ")");
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
	
	public void update(Libro libro, int codigo) throws SQLException{
		conexion = new ConexionBD();
		Connection conn = conexion.getConexion();
		PreparedStatement ps = conn.prepareStatement("UPDATE Libro SET codigo=" + libro.getCodigo()+", titulo=" + "'" + libro.getTitulo()+ "'" + ", autor=" + "'" + libro.getAutor()+ "'" + ", editorial=" + "'" + libro.getEditorial() +  "'" + ", estado=" + "'" + libro.getEstado()+ "'" + ", baja=" + libro.getBaja()+" WHERE codigo=?");
		ps.setInt(1, codigo);
		ps.executeUpdate();
		ps.close();
		conn.close();
	}
	public void updateEstado(String estado, int codigo) throws SQLException{
		conexion = new ConexionBD();
		Connection conn = conexion.getConexion();
		PreparedStatement ps = conn.prepareStatement("UPDATE Libro SET estado=? WHERE codigo=?");
		ps.setString(1, estado);
		ps.setInt(2, codigo);
		ps.executeUpdate();
		ps.close();
		conn.close();
	}
	
	
}

