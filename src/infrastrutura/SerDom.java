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

public class SerDom implements ISerDom {

	private Document doc;
	private File a;
	
	private HashMap<String, String> map;
	
	public SerDom (String path, HashMap<String, String> esquema) {
		map = esquema;
		try {
			a = new File(path);
			DocumentBuilderFactory facBuildDoc = DocumentBuilderFactory.newInstance();
			DocumentBuilder buildDoc = facBuildDoc.newDocumentBuilder();
			
			if (a.exists()) {
				System.out.println("Documento " + a.getName() + " recuperado con exito.");
				doc = buildDoc.parse(a);
				doc.getDocumentElement().normalize();
			} else {
				doc = buildDoc.newDocument();
			}
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println("Ha ocurrido un error inesperado: " + e.getMessage());
		}
		
	}
	
	public Element insertar(String nombre) {return insertar(null, nombre, null, null);}	
	public Element insertar(Element raiz, String nombre) {return insertar(raiz, nombre, null, null);}	
	public Element insertar(Element raiz, String nombre, HashMap<String, String> atributoValor) {return insertar(raiz, nombre, null, atributoValor);}
	public Element insertar(Element raiz, String nombre, String contenido) {return insertar(raiz, nombre, contenido, null);}	
	public Element insertar(Element raiz, String nombre, String contenido, HashMap<String, String> atributoValor) {
		Element result = doc.createElement(nombre);
		if (atributoValor != null) {
			for (Map.Entry<String, String> attr : atributoValor.entrySet()) {
				result.setAttribute(attr.getKey(), attr.getValue());
			}
		}
		
		if (contenido != null) {
			result.setTextContent(contenido);
		}
		
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
	
	public void crearDocumento() {
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

		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}
	
	public void print() {
		StringBuilder result = new StringBuilder();
		
		result.append(raiz().trim());
		result.append("\n");
		result.append("-".repeat(20));
		result.append("\n");
		result.append(nodos(doc.getDocumentElement(), 0, false).trim());
		
		System.out.print(result.toString());
	}
	
	public String raiz() {
		String result = "Elemento raiz: ";
		result += doc.getDocumentElement().getNodeName();
		return result;
	}
	
	public String nodos(Node nodo, int nivel, boolean esUltimo) {
	    StringBuilder result = new StringBuilder();

	    if (nodo.getNodeType() == Node.ELEMENT_NODE) {
	        // Sangría según nivel
	        for (int i = 0; i < nivel - 1; i++) {
	            result.append("│\t");
	        }

	        if (nivel > 0) {
	            if (esUltimo) {
	                result.append("└─────> ");
	            } else {
	                result.append("├─────> ");
	            }
	        }

	        result.append(map != null ? map.get(nodo.getNodeName()) : nodo.getNodeName());

	        // Atributos
	        NamedNodeMap attrs = nodo.getAttributes();
	        if (attrs != null && attrs.getLength() > 0) {
	            result.append(" (");
	            for (int i = 0; i < attrs.getLength(); i++) {
	                Node attr = attrs.item(i);
	                result.append((map != null ? map.get(attr.getNodeName()) : attr.getNodeName()) + ": " + attr.getNodeValue());
	                if (i < attrs.getLength() - 1) result.append(", ");
	            }
	            result.append(")");
	        }

	        // Texto de los hijos
	        NodeList hijos = nodo.getChildNodes();
	        StringBuilder textContent = new StringBuilder();
	        for (int i = 0; i < hijos.getLength(); i++) {
	            Node hijo = hijos.item(i);
	            if (hijo.getNodeType() == Node.TEXT_NODE) {
	                String text = hijo.getTextContent().trim();
	                if (!text.isEmpty()) textContent.append(text);
	            }
	        }
	        if (textContent.length() > 0) {
	            result.append(": ").append(textContent);
	        }

	        result.append("\n");

	        // Llamada recursiva para los hijos
	        int hijosValidos = 0;
	        for (int i = 0; i < hijos.getLength(); i++) {
	            Node hijo = hijos.item(i);
	            if (hijo.getNodeType() == Node.ELEMENT_NODE) {
	                hijosValidos++;
	            }
	        }

	        int contador = 0;
	        for (int i = 0; i < hijos.getLength(); i++) {
	            Node hijo = hijos.item(i);
	            if (hijo.getNodeType() == Node.ELEMENT_NODE) {
	                contador++;
	                boolean ultimo = contador == hijosValidos;
	                result.append(nodos(hijo, nivel + 1, ultimo));
	            }
	        }
	    }

	    return result.toString();
	}


}
