package aed.java.futbol;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

	public Connection conexion;
	
	public Connection Conectar(String servidor)
	{
		try
		{
			String url = null;
			String usuario = null;
			String contrasenia = null;
			
			if(servidor.equals("mysql"))
			{
				url = "jdbc:mysql://localhost:3306/bdfutbol";
				usuario = "root";
				contrasenia = "";
				
				Class.forName("com.mysql.cj.jdbc.Driver");
			}
			else if(servidor.equals("sqlserver"))
			{
				
			}
			else if(servidor.equals("access"))
			{
				
			}
					
			conexion = DriverManager.getConnection(url, usuario, contrasenia);
			
			return conexion;
		
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		
		return null;
	}
	
	public void Desconectar()
	{
		try
		{
			conexion.close();
		}
		catch(Exception e)
		{
			e.getMessage();
		}
	}
	
}
