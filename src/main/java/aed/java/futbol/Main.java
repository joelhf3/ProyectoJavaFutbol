package aed.java.futbol;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		
		Conexion conexion = new Conexion();
		
		try
		{
			PreparedStatement select = conexion.conectar().prepareStatement("select * from equipos");
			ResultSet resultado = select.executeQuery();
			
			while(resultado.next())
			{
				System.out.println(resultado.getInt("codEquipo") + " " + resultado.getString("nomEquipo"));
			}
		}
		catch(SQLException e)
		{
			e.getMessage();
		}
		finally
		{
			conexion.desconectar();
		}
	}

}
