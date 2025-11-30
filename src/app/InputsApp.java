package app;

import java.util.Scanner;

import util.InputUtil;

/**
 * Inputs de teclado, con validaciones si es necesario
 */
public class InputsApp extends InputUtil {

	/**
	 * Patron de diseño singleton para que el scanner solo se instancie una vez y no de problemas.
	 * @return
	 */
	public static InputsApp getInstance() {
		if (instance == null) {
			return new InputsApp();
		}
		return (InputsApp) instance;
	}
	
	protected InputsApp() {
		this.scn = new Scanner(System.in);
	}
	
	/**
	 * Funcion de entrada de opcion de menus.
	 * @return
	 */
	public int opc() { return leerInt("Elige tu opcion: "); }
		
}
