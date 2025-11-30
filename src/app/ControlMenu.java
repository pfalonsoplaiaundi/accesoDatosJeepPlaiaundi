package app;

import dominio.ERenderizable;
import view.Menu;

/**
 * Clase base abstracta para los menus de la app
 */
public abstract class ControlMenu implements ERenderizable {
	
	protected Menu view;
	
	public ControlMenu(Menu view) {
		this.view = view;
	}

	public Menu getView() {
		return view;
	}

	public void setView(Menu view) {
		this.view = view;
	}
	
	/**
	 * Funcion que alberga la logica del menu.
	 * @param opc
	 */
	public abstract void selector(Object opc);

}
