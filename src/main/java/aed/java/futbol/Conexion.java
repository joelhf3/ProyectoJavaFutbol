package aed.java.futbol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	public Connection conexion;
	
	public Connection conectar()
	{
		try
		{
			String url = "jdbc:mysql://localhost:3306/bdfutbol";
			String usuario = "root";
			String contrasenia = "";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conexion = DriverManager.getConnection(url, usuario, contrasenia);
			
			return conexion;
		
		}
		catch(SQLException | ClassNotFoundException e)
		{
			e.getMessage();
		}
		
		return null;
	}
	
	public void desconectar()
	{
		try
		{
			conexion.close();
		}
		catch(SQLException e)
		{
			e.getMessage();
		}
	}
	
}
