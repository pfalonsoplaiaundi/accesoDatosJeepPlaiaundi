package infrastrutura.interfaces;

import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public interface ISerDom {
	public boolean crearDocumento(String nombreDocumento);
	public boolean finalizarDocumento();
	public Element insertar(String nombre);
	public Element insertar(String nombre, HashMap<String, String> atributoValor);
	public Element insertar(Element raiz, String nombre);	
	public Element insertar(Element raiz, String nombre, String contenido);
	public Element insertar(Element raiz, String nombre, String contenido, HashMap<String, String> atributoValor);
	public Element insertar(Element raiz, String nombre, HashMap<String, String> atributoValor);
}
