package util;

import java.util.Arrays;
import java.util.Scanner;

public class InputUtil {
	
	protected static InputUtil instance;
	
	protected Scanner scn;
	
	public static InputUtil getInstance() {
		if (instance == null) {
			return new InputUtil();
		}
		return instance;
	}

	protected InputUtil() {
		this.scn = new Scanner(System.in);
	}
	
	protected int leerInt(String mensaje) {
		try {
			System.out.print(mensaje);
			String intTemp = scn.nextLine().trim();
			return Integer.parseInt(intTemp);
		} catch (Exception e) {
			System.out.println("ERROR -> Por favor introduzca un numero entero");
			return leerInt(mensaje);
		}
	}
	
	protected String leerString(String mensaje) {
		System.out.print(mensaje);
		return scn.nextLine().trim();
	}
	
	protected Double leerDouble(String mensaje) {
	    try {
	        System.out.print(mensaje);
	        String doubleTemp = scn.nextLine().trim(); 
	        String numeroFormatoEstandar = doubleTemp.replace(',', '.');
	        return Double.parseDouble(numeroFormatoEstandar);
	        
	    } catch (Exception e) {
	        System.out.println("ERROR -> Por favor introduzca un número decimal válido (ej. 10.5 o 10,5)");
	        return leerDouble(mensaje);
	    }
	}	
	
	// Definimos los arrays como atributos de la clase (puedes añadir o quitar palabras aquí)
	protected final String[] INPUTS_POSITIVOS = {"s", "si", "y", "yes", "true", "verdadero", "1"};
	protected final String[] INPUTS_NEGATIVOS = {"n", "no", "not", "false", "falso", "0"};
	protected Boolean leerBoolean(String mensaje) {
	    try {
	        System.out.print(mensaje + " (s/n): ");
	        String entrada = scn.nextLine().trim().toLowerCase();
	        if (Arrays.asList(INPUTS_POSITIVOS).contains(entrada)) {
	            return true;
	        }	        
	        if (Arrays.asList(INPUTS_NEGATIVOS).contains(entrada)) {
	            return false;
	        }
	        throw new Exception("Entrada no reconocida");

	    } catch (Exception e) {
	        System.out.println("ERROR -> Respuesta no válida.");
	        System.out.println("Opciones válidas para SI: " + Arrays.toString(INPUTS_POSITIVOS));
	        System.out.println("Opciones válidas para NO: " + Arrays.toString(INPUTS_NEGATIVOS));
	        return leerBoolean(mensaje);
	    }
	}	
	
}
