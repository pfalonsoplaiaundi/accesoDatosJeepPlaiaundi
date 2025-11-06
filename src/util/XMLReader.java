package util;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import accesoDatosJeep.model.PedidosVenta;
import accesoDatosJeep.model.Producto;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de leer un archivo XML de pedidos de venta utilizando SAX.
 * 
 * SAX es un parser basado en eventos: en lugar de cargar todo el XML en memoria,
 * va leyendo elemento por elemento, lo que lo hace muy eficiente para archivos grandes.
 */
public class XMLReader extends DefaultHandler {

    // Lista donde se almacenarán los pedidos leídos
    private List<PedidosVenta> pedidos = new ArrayList<>();

    // Variables auxiliares para construir los objetos durante la lectura
    private PedidosVenta pedidoActual;
    private Producto productoActual;
    private StringBuilder valorActual; // Acumula el texto dentro de las etiquetas

    /**
     * Devuelve la lista final de pedidos leídos del XML.
     */
    public List<PedidosVenta> getPedidos() {
        return pedidos;
    }

    /**
     * Se ejecuta cada vez que SAX encuentra el inicio de una etiqueta (ej: <pedidoVenta>)
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        valorActual = new StringBuilder(); // Reiniciar el valor acumulado

        if (qName.equalsIgnoreCase("pedidoVenta")) {
            // Se ha detectado un nuevo pedido
            pedidoActual = new PedidosVenta(0, 0, 0, 0, 0, null);
        } else if (qName.equalsIgnoreCase("producto")) {
            // Se ha detectado un nuevo producto dentro de un pedido
            productoActual = new Producto(0, "", "", 0, 0, 0, Producto.Categoria.herramientas, 0);
        }
    }

    /**
     * Se ejecuta cada vez que SAX encuentra texto dentro de una etiqueta.
     */
    @Override
    public void characters(char[] ch, int start, int length) {
        valorActual.append(ch, start, length);
    }

    /**
     * Se ejecuta cuando SAX encuentra el cierre de una etiqueta (ej: </id>).
     */
    @Override
    public void endElement(String uri, String localName, String qName) {

        // Según la etiqueta, asignamos el valor al objeto correspondiente
        switch (qName) {
            case "id":
                // Si hay un producto abierto, asignamos su ID
                if (productoActual != null && pedidoActual != null && pedidoActual.getProducto() == null)
                    productoActual.setId(Integer.parseInt(valorActual.toString()));
                // Si no, asignamos el ID del pedido
                else if (pedidoActual != null)
                    pedidoActual.setId(Integer.parseInt(valorActual.toString()));
                break;

            case "idPedido":
                pedidoActual.setIdPedido(Integer.parseInt(valorActual.toString()));
                break;

            case "precioFinal":
                pedidoActual.setPrecioFinal(Double.parseDouble(valorActual.toString()));
                break;

            case "dctoPorcen":
                pedidoActual.setDctoPorcen(Double.parseDouble(valorActual.toString()));
                break;

            case "cantidad":
                pedidoActual.setCantidad(Integer.parseInt(valorActual.toString()));
                break;

            case "alias":
                productoActual.setAlias(valorActual.toString());
                break;

            case "categoria":
                productoActual.setCategoria(Producto.Categoria.valueOf(valorActual.toString()));
                break;

            case "producto":
                // Fin de producto → lo asociamos al pedido actual
                pedidoActual.setProducto(productoActual);
                break;

            case "pedidoVenta":
                // Fin del pedido → lo añadimos a la lista
                pedidos.add(pedidoActual);
                break;
        }
    }

    /**
     * Método estático que se encarga de abrir el archivo XML y lanzar el parser SAX.
     * 
     * @param ruta Ruta del archivo XML a leer.
     * @return Lista de objetos PedidosVenta leídos del XML.
     */
    public static List<PedidosVenta> leerXML(String ruta) {
        try {
            // Crear un parser SAX
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            // Crear un manejador (esta misma clase)
            XMLReader handler = new XMLReader();

            // Ejecutar el parseo del XML
            parser.parse(new File(ruta), handler);

            System.out.println("✅ Lectura de XML completada correctamente: " + ruta);
            return handler.getPedidos();

        } catch (Exception e) {
            System.err.println("❌ Error al leer el XML: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}