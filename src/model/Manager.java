package model;

import java.sql.SQLException;

import DAO.AlumnoDAO;
import model.*;

public class Manager {
    public static void main(String[] args) throws SQLException {
        AlumnoDAO alumno = new AlumnoDAO();
        
        ///////////////////
        //Alumno p = alumno.read("55555555A");
        //System.out.println(p);
        
        //Alumno a = new Alumno("12345a","qqq","zzz","xxx");
        //alumno.insert(a);
        
        //alumno.delete("55555555A");
        
        //Alumno upd = new Alumno("666","Skate","skate","skate");
        //alumno.update(upd, "777");
        
      //DeportistaDAO deportista = new DeportistaDAO();
      
      
      //Deportista r = deportista.read(2);
      //System.out.println(r);
      
      ////////////////////////Deportista i = new Deportista(3,"Aitor","M",80,180);
      ////////////////////////deportista.insert(i);
      
      //deportista.delete(3);
      
      //Deportista u = new Deportista(3,"Yefri","F",90,190);
      //deportista.update(u, 3);
      
      //EquipoDAO equipo = new EquipoDAO();
      
      //Equipo r = equipo.read(2);
      //System.out.println(r);
      
      //////////////////////Equipo i = new Equipo(7,"Portugal","POR");
      //////////////////////equipo.insert(i);
      
      //equipo.delete(5);
      
      //Equipo u = new Equipo(5,"fdfdfdfd","FyA");
      //equipo.update(u, 5);
      
      //OlimpiadaDAO olimpiada = new OlimpiadaDAO();
      
      //Olimpiada r = olimpiada.read(2);
      //System.out.println(r);
      
      //Olimpiada i = new Olimpiada(4,"a",1,"Summer","c");
      //olimpiada.insert(i);
      
      //d
      
      //Olimpiada u = new Olimpiada(4,"x",99,"Summer","españa");
      //olimpiada.update(u,4);
        
    }
}