package aed.java.futbol;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Sentencia {
	
	public static void MostrarEquipos()
	{
		Conexion conexion = new Conexion();
		
		try
		{
			String sentencia = "select codEquipo, nomEquipo, nomLiga, localidad, internacional from equipos join ligas on equipos.codLiga = ligas.codLiga";
			PreparedStatement select = conexion.Conectar(Menu.tipoConexion).prepareStatement(sentencia);
			ResultSet resultado = select.executeQuery();
			
			while(resultado.next())
			{
				System.out.println(resultado.getInt(1) + " | " + resultado.getString(2) + " | " + resultado.getString(3) + " | " + resultado.getString(4) + " | " + resultado.getInt(5));
			}
			
			Menu.MenuOpciones(Menu.tipoConexion);
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		finally
		{
			conexion.Desconectar();
		}
	}
	
	public static void InsertarEquipos()
	{
		
	}
	
	public static void ModificarEquipos()
	{
		
	}
	
	public static void BorrarEquipos()
	{
		
	}
}
