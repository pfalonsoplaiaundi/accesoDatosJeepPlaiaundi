package app;

import java.util.HashMap;

import view.Menu;

public class ControlMenuPrincipal extends ControlMenu {

	private ISerXmlJeep xml;
	private ISerBDJeep bd;
	private InputsApp in;
	
	public ControlMenuPrincipal(ISerXmlJeep xml, ISerBDJeep bd, InputsApp in) {
		super(null);
		this.xml = xml;
		this.bd = bd;
		this.in = in;
		HashMap<Object, String> opciones = new HashMap<>();
		opciones.put(1, "Mostrar datos");
		opciones.put(2, "Hacer backup");
		opciones.put(0, "Salir");
		this.setView(
			new Menu(
				"Menu principal",
				opciones				
			)
		);
	}

	@Override
	public void selector(Object opc) {
		if (opc.equals(0)) System.exit(0);
		if (opc.equals(1)) mostrarDatos();
		if (opc.equals(2)) hacerBackUp();
	
	}

	private void hacerBackUp() {
		System.out.println(
				"-".repeat(5) + 
				" Creando XML para hacer backup " + 
				"-".repeat(5) 
		);
		
		System.out.println(
				"-".repeat(5) + 
				((this.xml.crearBackup()) ? " Finalizando proceso " : " HA OCURRIDO UN ERROR ") + 
				"-".repeat(5) 
		);
	}

	private void mostrarDatos() {
		System.out.println(
				"-".repeat(5) + 
				" Mostrando datos " + 
				"-".repeat(5) 
		);
		
		this.bd.verDatosDeBackUp();
		
		System.out.println(
				"-".repeat(5) + 
				" Finalizando proceso " + 
				"-".repeat(5) 
		);
	}

	@Override
	public void render() {
		view.render();
		selector(in.opc());
	}
	
}
