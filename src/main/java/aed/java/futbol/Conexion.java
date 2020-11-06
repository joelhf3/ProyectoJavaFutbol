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
				
				conexion = DriverManager.getConnection(url, usuario, contrasenia);
			}
			else if(servidor.equals("sqlserver"))
			{
				url = "jdbc:sqlserver://JOEL-PC\\SQLEXPRESS;DataBaseName=bdFutbol";
				usuario = "joel";
				contrasenia = "1234";
				
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				
				conexion = DriverManager.getConnection(url, usuario, contrasenia);			
			}
			else if(servidor.equals("access"))
			{
				url = "jdbc:ucanaccess://src/main/resources\\BdFutbolAccess.accdb";
				
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				
				conexion = DriverManager.getConnection(url);
			}
			
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
