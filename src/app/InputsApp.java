package app;

import java.util.Scanner;

import util.InputUtil;

public class InputsApp extends InputUtil {

	public static InputsApp getInstance() {
		if (instance == null) {
			return new InputsApp();
		}
		return (InputsApp) instance;
	}
	
	protected InputsApp() {
		this.scn = new Scanner(System.in);
	}
	
	public int opc() { return leerInt("Elige tu opcion: "); }
		
}
