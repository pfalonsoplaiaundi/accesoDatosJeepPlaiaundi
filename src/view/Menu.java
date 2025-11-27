package view;

import java.util.HashMap;
import java.util.Map;

import dominio.ERenderizable;

public class Menu implements ERenderizable {

	private String mensajeMenu;
	private String titulo;
	private HashMap<Object, String> opciones;
	
	public Menu(String titulo, HashMap<Object, String> opciones) {
		this.titulo = titulo;
		this.opciones = opciones;
		crearMensajeMenu();
	}
	
	private void crearMensajeMenu() {
		StringBuilder builder = new StringBuilder();
		// Creamos el mensaje con un builder para ir paso a paso
		
		// 1. Decoración y Título
        builder.append("\n-----------------------------------\n");
        builder.append("   ").append(this.titulo.toUpperCase()).append("\n");
        builder.append("-----------------------------------\n");
        
        // 2. Iterar sobre las opciones
        // Se recorre el mapa para mostrar: "Clave - Descripción"
        if (opciones != null && !opciones.isEmpty()) {
            for (Map.Entry<Object, String> entrada : opciones.entrySet()) {
                builder.append(" [")
                       .append(entrada.getKey())
                       .append("]  ")
                       .append(entrada.getValue())
                       .append("\n");
            }
        } else {
            builder.append("  (No hay opciones disponibles)\n");
        }
        
        // 3. Cierre del menú
        builder.append("-----------------------------------");
				
		// Lo precargamos en memoria para cuando lo pidan
		this.mensajeMenu = builder.toString();
	}

	@Override
	public void render() {
		
		System.out.println(this.mensajeMenu);
		
	}
	
	
}
