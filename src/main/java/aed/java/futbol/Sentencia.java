package aed.java.futbol;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Sentencia {
	
	public static void MostrarEquipos()
	{
		Conexion conexion = new Conexion();
		
		try
		{
			String sentencia = "select codEquipo, nomEquipo, nomLiga, localidad, internacional from equipos join ligas on equipos.codLiga = ligas.codLiga order by codEquipo";
			PreparedStatement select = conexion.Conectar(Menu.tipoConexion).prepareStatement(sentencia);
			ResultSet resultado = select.executeQuery();
			
			while(resultado.next())
			{
				System.out.println(resultado.getInt(1) + " | " + resultado.getString(2) + " | " + resultado.getString(3) + " | " + resultado.getString(4) + " | " + resultado.getInt(5));
			}
			
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		finally
		{
			conexion.Desconectar();
			Menu.MenuOpciones(Menu.tipoConexion);
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
		Conexion conexion = new Conexion();
		Scanner sc = new Scanner(System.in);
		
		try
		{
			String sentencia = "select * from equipos order by codEquipo";
			PreparedStatement select = conexion.Conectar(Menu.tipoConexion).prepareStatement(sentencia);
			ResultSet resultado = select.executeQuery();
			
			List<Integer>codigos = new ArrayList<Integer>();
			boolean existe = false;
			
			while(resultado.next())
			{
				System.out.println(resultado.getInt(1) + " | " + resultado.getString(2) + " | " + resultado.getString(3) + " | " + resultado.getString(4) + " | " + resultado.getInt(5));
				codigos.add(resultado.getInt(1));
			}
			
			System.out.println("\n");
			System.out.println("Escriba el número del equipo a borrar:");
			
			int equipo = sc.nextInt();
			
			for(int i = 0; i < codigos.size(); i++)
			{
				if(equipo == codigos.get(i))
				{
					existe = true;
					PreparedStatement delete = conexion.Conectar(Menu.tipoConexion).prepareStatement("delete from equipos where codEquipo = ?");
					delete.setInt(1, equipo);
					delete.execute();
					System.out.println("Equipo eliminado.");
				}
			}
			
			if(!existe)
			{
				System.out.println("El número de equipo introducido no existe.");
			}
			
		}
		catch(SQLException e)
		{
			System.out.println("No se pueden eliminar equipos que contengan jugadores.");
		}
		catch(InputMismatchException e)
		{
			System.out.println("Debe escribir un número");
		}
		finally
		{
			conexion.Desconectar();
			Menu.MenuOpciones(Menu.tipoConexion);
		}
		
	}
}
