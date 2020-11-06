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
			System.out.println(e.getMessage());
		}
		finally
		{
			conexion.Desconectar();
			Menu.MenuOpciones(Menu.tipoConexion);
		}
	}
	
	public static void InsertarEquipos()
	{
		Conexion conexion = new Conexion();
		Scanner sc = new Scanner(System.in);
		
		try
		{
			List<String>codigos = new ArrayList<String>();
			boolean existe = false;
			
			String sentencia = "select * from equipos order by codEquipo";
			PreparedStatement select = conexion.Conectar(Menu.tipoConexion).prepareStatement(sentencia);
			ResultSet resultado = select.executeQuery();
	
			while(resultado.next())
			{
				codigos.add(resultado.getString(3));
			}
			
			System.out.println("Nombre del equipo:");
			String nombre = sc.nextLine();
			
			System.out.println("Localidad del equipo:");
			String localidad = sc.nextLine();		
			
			System.out.println("Código de liga:");
			String liga = sc.nextLine();
			
			for(int i = 0; i < codigos.size(); i++)
			{
				if(liga.equals(codigos.get(i)))
				{
					existe = true;
				}
			}
			
			if(!existe)
			{
				System.out.println("El código de liga introducido no existe.");
			}
			else
			{
				System.out.println("Internacional: (1 o 0):");
				int internacional = sc.nextInt();
				
				if(internacional != 1 && internacional != 0)
				{
					System.out.println("Internacional solo puede ser 1 o 0.");
				}
				else
				{
					sentencia = "insert into equipos (nomEquipo, codLiga, localidad, internacional) values (?, ?, ?, ?)";
					PreparedStatement insert = conexion.Conectar(Menu.tipoConexion).prepareStatement(sentencia);
					insert.setString(1, nombre);
					insert.setString(2, liga);
					insert.setString(3, localidad);
					insert.setInt(4, internacional);
					insert.execute();
					System.out.println("Equipo insertado con éxito.");
				}
			}
			
		}
		catch(SQLException e)
		{
			System.out.println("Alguno de los datos introducidos es erróneo.");
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
	
	public static void ModificarEquipos()
	{
		Conexion conexion = new Conexion();
		Scanner sc = new Scanner(System.in);
		
		try
		{
			List<String>codigoLiga = new ArrayList<String>();
			List<Integer>codigoEquipo = new ArrayList<Integer>();
			boolean ligaExiste = false;
			boolean equipoExiste = false;
			String nombre = null;
			String localidad = null;
			String liga = null;
			
			String sentencia = "select * from equipos order by codEquipo";
			PreparedStatement select = conexion.Conectar(Menu.tipoConexion).prepareStatement(sentencia);
			ResultSet resultado = select.executeQuery();
	
			while(resultado.next())
			{
				System.out.println(resultado.getInt(1) + " | " + resultado.getString(2) + " | " + resultado.getString(3) + " | " + resultado.getString(4) + " | " + resultado.getInt(5));
				codigoEquipo.add(resultado.getInt(1));
				codigoLiga.add(resultado.getString(3));
			}
			
			System.out.println("\n");
			System.out.println("Escriba el número del equipo a modificar:");
			int codigo = sc.nextInt();
			
			for(int i = 0; i < codigoEquipo.size(); i++)
			{
				if(codigo == codigoEquipo.get(i))
				{
					equipoExiste = true;
				}
			}
			
			if(equipoExiste)
			{
				System.out.println("Nuevo nombre del equipo:");
				nombre = sc.next();
				
				System.out.println("Nueva localidad del equipo:");
				localidad = sc.next();		
				
				System.out.println("Nuevo código de liga:");
				liga = sc.next();
				
				for(int i = 0; i < codigoLiga.size(); i++)
				{
					if(liga.equals(codigoLiga.get(i)))
					{
						ligaExiste = true;
					}
				}
				
				if(!ligaExiste)
				{
					System.out.println("El código de liga introducido no existe.");
				}
				else
				{
					System.out.println("Internacional: (1 o 0):");
					int internacional = sc.nextInt();
					
					if(internacional != 1 && internacional != 0)
					{
						System.out.println("Internacional solo puede ser 1 o 0.");
					}
					else
					{
						sentencia = "update equipos set nomEquipo = ?, codLiga = ?, localidad = ?, internacional = ? where codEquipo = ?";
						PreparedStatement insert = conexion.Conectar(Menu.tipoConexion).prepareStatement(sentencia);
						insert.setString(1, nombre);
						insert.setString(2, liga);
						insert.setString(3, localidad);
						insert.setInt(4, internacional);
						insert.setInt(5, codigo);
						insert.execute();
						System.out.println("Equipo modificado con éxito.");
					}
				}
			}
			else
			{
				System.out.println("El número de equipo introducido no existe.");
			}
			
		}
		catch(SQLException e)
		{
			System.out.println("Alguno de los datos introducidos es erróneo.");
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
