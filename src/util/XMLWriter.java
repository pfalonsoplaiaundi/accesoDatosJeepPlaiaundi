package util;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import accesoDatosJeep.model.PedidosVenta;
import accesoDatosJeep.model.Producto;

import java.util.List;
/**
 * Clase encargada de generar un archivo XML a partir de una lista de objetos PedidosVenta.
 * 
 * Utiliza la API DOM (Document Object Model) para construir la estructura jerárquica del XML en memoria
 * y luego lo guarda en un fichero utilizando Transformer.
 */
public class XMLWriter {

    /**
     * Crea un archivo XML con la información de los pedidos de venta.
     * 
     * @param pedidos Lista de objetos PedidosVenta a escribir en el XML.
     * @param rutaArchivo Ruta del archivo XML donde se guardará la información.
     */
    public static void escribirPedidosXML(List<PedidosVenta> pedidos, String rutaArchivo) {
        try {
            // 1️⃣ Crear el documento XML en memoria
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Documento base donde se construirá el árbol XML
            Document doc = builder.newDocument();

            // 2️⃣ Crear el elemento raíz <pedidosVenta>
            Element root = doc.createElement("pedidosVenta");
            doc.appendChild(root);

            // 3️⃣ Recorrer la lista de pedidos para convertirlos a XML
            for (PedidosVenta pedido : pedidos) {
                // Crear nodo <pedidoVenta>
                Element pedidoElem = doc.createElement("pedidoVenta");

                // Añadir subelementos básicos
                Element id = doc.createElement("id");
                id.appendChild(doc.createTextNode(String.valueOf(pedido.getId())));
                pedidoElem.appendChild(id);

                Element idPedido = doc.createElement("idPedido");
                idPedido.appendChild(doc.createTextNode(String.valueOf(pedido.getIdPedido())));
                pedidoElem.appendChild(idPedido);

                Element precioFinal = doc.createElement("precioFinal");
                precioFinal.appendChild(doc.createTextNode(String.valueOf(pedido.getPrecioFinal())));
                pedidoElem.appendChild(precioFinal);

                Element dctoPorcen = doc.createElement("dctoPorcen");
                dctoPorcen.appendChild(doc.createTextNode(String.valueOf(pedido.getDctoPorcen())));
                pedidoElem.appendChild(dctoPorcen);

                Element cantidad = doc.createElement("cantidad");
                cantidad.appendChild(doc.createTextNode(String.valueOf(pedido.getCantidad())));
                pedidoElem.appendChild(cantidad);

                // 4️⃣ Crear el subelemento <producto> con la información del producto
                Producto p = pedido.getProducto();
                Element prodElem = doc.createElement("producto");

                Element idProd = doc.createElement("id");
                idProd.appendChild(doc.createTextNode(String.valueOf(p.getId())));
                prodElem.appendChild(idProd);

                Element alias = doc.createElement("alias");
                alias.appendChild(doc.createTextNode(p.getAlias()));
                prodElem.appendChild(alias);

                Element categoria = doc.createElement("categoria");
                categoria.appendChild(doc.createTextNode(p.getCategoria().toString()));
                prodElem.appendChild(categoria);

                // Asociar el producto con el pedido
                pedidoElem.appendChild(prodElem);

                // Añadir el pedido completo al nodo raíz
                root.appendChild(pedidoElem);
            }

            // 5️⃣ Guardar el XML en disco con formato legible
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();

            // Configurar la salida para que tenga indentación (sangría)
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            // Escribir el documento XML en el archivo especificado
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(rutaArchivo));

            transformer.transform(source, result);

            System.out.println("✅ Archivo XML creado correctamente: " + rutaArchivo);

        } catch (Exception e) {
            // Captura cualquier error durante la creación o escritura del XML
            System.err.println("❌ Error al crear el archivo XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}