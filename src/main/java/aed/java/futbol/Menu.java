package aed.java.futbol;

import java.util.Scanner;

public class Menu {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		
		do
		{
			System.out.println("1. MySQL");
			System.out.println("2. SQLServer");
			System.out.println("3. Access");
			System.out.println("0. Salir");
			
		}while(sc.nextInt() != 0);
	}

}
