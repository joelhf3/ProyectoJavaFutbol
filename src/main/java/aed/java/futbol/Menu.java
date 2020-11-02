package aed.java.futbol;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

	static String tipoConexion;
	
	public static void MenuPrincipal()
	{
		Scanner sc = new Scanner(System.in);
		boolean salir = false;
		int opcion;
		
		do
		{
			System.out.println("\n");
			System.out.println("Elija una opción");
			System.out.println("1. MySQL");
			System.out.println("2. SQLServer");
			System.out.println("3. Access");
			System.out.println("0. Salir");
			System.out.println("\n");
			
			try
			{
				opcion = sc.nextInt();
				
				switch(opcion)
				{
					case 1:
						MenuOpciones("mysql");
						salir = true;
						break;
					case 2:
						MenuOpciones("sqlserver");
						salir = true;
						break;
					case 3:
						MenuOpciones("access");
						salir = true;
						break;
					case 0:
						System.out.println("Adiós");
						salir = true;
						break;
					default:
						System.out.println("Elija una opción válida");
						break;
				}
			}
			catch(InputMismatchException e)
			{
				System.out.println("Debe escribir un número");
				sc.next();
			}
			
		}while(!salir);
	}
	
	public static void MenuOpciones(String servidor)
	{
		tipoConexion = servidor;
		
		Scanner sc = new Scanner(System.in);
		boolean salir = false;
		int opcion;
		
		do
		{
			System.out.println("\n");
			System.out.println("Elija una opción:");
			System.out.println("1. Mostrar Equipos");
			System.out.println("2. Añadir Equipos");
			System.out.println("3. Modificar Equipos");
			System.out.println("4. Borrar Equipos");
			System.out.println("0. Menú Anterior");
			System.out.println("\n");
			
			try
			{
				opcion = sc.nextInt();
				
				switch(opcion)
				{
					case 1:
						Sentencia.MostrarEquipos();
						salir = true;
						break;
					case 2:
						Sentencia.InsertarEquipos();
						salir = true;
						break;
					case 3:
						Sentencia.ModificarEquipos();
						salir = true;
						break;
					case 4:
						Sentencia.BorrarEquipos();
						salir = true;
						break;
					case 0:
						salir = true;
						MenuPrincipal();
						break;
					default:
						System.out.println("Elija una opción válida");
						break;
				}
			}
			catch(InputMismatchException e)
			{
				System.out.println("Debe escribir un número");
				sc.next();
			}
			
		}while(!salir);
	}
	
}
