package app;

import dominio.ERenderizable;
import view.Menu;

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
	
	public abstract void selector(Object opc);

}
