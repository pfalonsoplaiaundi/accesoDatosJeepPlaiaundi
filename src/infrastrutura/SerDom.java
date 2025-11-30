package infrastrutura;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import infrastrutura.interfaces.ISerDom;

/**
 * Servicio de creacion de un xml
 */
public class SerDom implements ISerDom {

	private Document doc;
	private File a;
	
	/**
	 * Funcion para crear un documento xml, cada vez que se llama
	 * cambia de documento. Si el documento ya existiera lo recupera.
	 */
	@Override
	public boolean crearDocumento(String nombreDocumento) {
		try {
			a = new File(nombreDocumento);
			DocumentBuilderFactory facBuildDoc = DocumentBuilderFactory.newInstance();
			DocumentBuilder buildDoc = facBuildDoc.newDocumentBuilder();
			
			if (a.exists()) {
				doc = buildDoc.parse(a);
				doc.getDocumentElement().normalize();
			} else {
				doc = buildDoc.newDocument();
			}
			return true;
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println("Ha ocurrido un error inesperado: " + e.getMessage());
			return false;
		}
	}


	// Metodos de insercion de nodos en el xml
	/** Metodo para insertar un nodo raiz sin atributos */
	@Override public Element insertar(String nombre) { return insertar(null, nombre, null, null);}	
	/** Metodo para insertar un nodo raiz con atributos */ 
	@Override public Element insertar(String nombre, HashMap<String, String> atributoValor) { return insertar(null, nombre, null, atributoValor); }
	/** Metodo para insertar un nodo sin atributos, ni value */
	@Override public Element insertar(Element raiz, String nombre) {return insertar(raiz, nombre, null, null);}	
	/** Metodo para insertar un nodo con atributos sin value */
	@Override public Element insertar(Element raiz, String nombre, HashMap<String, String> atributoValor) {return insertar(raiz, nombre, null, atributoValor);}
	/** Metodo para insertar un nodo sin atributos con value */
	@Override public Element insertar(Element raiz, String nombre, String contenido) {return insertar(raiz, nombre, contenido, null);}	
	/** Metodo para insertar un nodo con atributos y con value */
	@Override public Element insertar(Element raiz, String nombre, String contenido, HashMap<String, String> atributoValor) {
		// Crear elemento
		Element result = doc.createElement(nombre);
		
		// Si hay atributos insertarselos
		if (atributoValor != null) {
			for (Map.Entry<String, String> attr : atributoValor.entrySet()) {
				result.setAttribute(attr.getKey(), attr.getValue());
			}
		}
		
		// Si hay contenido/value insertarselo
		if (contenido != null) {
			result.setTextContent(contenido);
		}
		
		// Asociarlo a un nodo anterior, o en caso de no haber unir al doc como raiz
		if (raiz != null) {
			raiz.appendChild(result);
		} else if (a.exists()) {
			raiz = doc.getDocumentElement();
			raiz.appendChild(result);
		} else {
			doc.appendChild(result);
		}
		return result;
	}
	
	/** Exporta el xml virual y crea el documento */
	@Override
	public boolean finalizarDocumento() {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer;
			transformer = transformerFactory.newTransformer();
	
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	
	        DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(a);
	
	        transformer.transform(source, result);
	
	        System.out.println("XML generado correctamente como "+a.getName()+".");
	        return true;
         
		} catch (TransformerException e) {
			e.printStackTrace();
			return false;
		}

	}

}
