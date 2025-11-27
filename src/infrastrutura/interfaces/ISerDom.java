package infrastrutura.interfaces;

import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public interface ISerDom {
	public void print();
	public String raiz();
	public String nodos(Node nodo, int nivel, boolean esUltimo);
	public void crearDocumento();
	public Element insertar(String nombre);
	public Element insertar(Element raiz, String nombre);	
	public Element insertar(Element raiz, String nombre, String contenido);
	public Element insertar(Element raiz, String nombre, String contenido, HashMap<String, String> atributoValor);
	public Element insertar(Element raiz, String nombre, HashMap<String, String> atributoValor);
}
