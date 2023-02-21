package com.co.aguilar;

import java.util.Scanner;

import com.co.aguilar.implementaciones.*;
import com.co.aguilar.implementaciones.Restar;
import com.co.aguilar.implementaciones.Sumar;
import com.co.aguilar.principal.OperacionInterface;

public class Menu {

	Scanner ac = new Scanner(System.in);
	
	public void mostarMenu() {
		
		System.out.println("Digite el numero uno: ");
		double numUno =ac.nextDouble();
		
		System.out.println("Digite el numero dos: ");
		double numDos =ac.nextDouble();
		
		System.out.println("Que operacion desea hacer: ");
		int seleccion = ac.nextInt();
		
		
		
		switch (seleccion) {
		case 1:
			Sumar am = new Sumar();
			am.sumar(numUno, numDos);
			System.out.println(am.sumar(numUno, numDos));
			break;
			
		case 2:
			Restar rs = new Restar();
			rs.restar(numUno, numDos);
			System.out.println(rs.restar(numUno, numDos));
			break;
			
		case 3:
			Multiplicar mu = new Multiplicar();
			mu.multiplicar(numUno, numDos);
			System.out.println(mu.multiplicar(numUno, numDos));
			break;
			
		case 4:
			Dividir di = new Dividir();
			di.dividir(numUno, numDos);
			System.out.println(di.dividir(numUno, numDos));
			break;

		default:
			break;
		}
	}
}
