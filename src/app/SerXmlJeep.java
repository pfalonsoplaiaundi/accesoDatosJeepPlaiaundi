package app;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Element;

import dominio.model.PedidosVenta;
import dominio.model.Producto;
import dominio.model.ProductosPedido;
import infrastrutura.interfaces.ISerDom;

public class SerXmlJeep implements ISerXmlJeep {

	private ISerDom xml;
	private ISerBDJeep bd;
	
	public SerXmlJeep(ISerDom serXml, ISerBDJeep bd) {
		this.xml = serXml;
		this.bd = bd;
	}
	
	
	@Override
	public boolean crearBackup() {
		
		if (!xml.crearDocumento("jeep_backup.xml")) return false;
		
		Element raiz = xml.insertar("pedidosVenta");
		
		// Sacamos la info fe la base de datos
		ArrayList<PedidosVenta> pedidosVentas = bd.getBackUp();
		
		// Creamos los nodos de pedidos
		//HashMap<Integer, Element> pedidos = crearNodosPedidosVentas(pedidosVentas);
		for (PedidosVenta pedido: pedidosVentas) {
			
			// Nodo de pedido venta
			HashMap<String, String> atributos = new HashMap<>();
			atributos.put("id", pedido.getId()+"");
			Element pedidoVenta = xml.insertar(raiz, "pedidoVenta", atributos);
			
			// Nodos hijos de pedido venta
			xml.insertar(pedidoVenta, "fecha", 		pedido.getFecha().toString());
			xml.insertar(pedidoVenta, "bRecoger",	pedido.isbRecoger()+"");
			xml.insertar(pedidoVenta, "fecEntrega", pedido.getFecEntrega().toString());
			xml.insertar(pedidoVenta, "direccion", 	pedido.getDireccion());
			xml.insertar(pedidoVenta, "idFactura", 	pedido.getIdFactura()+"");
			xml.insertar(pedidoVenta, "idCliente", 	pedido.getIdCliente()+"");
				
			// Array de productos pedidos
			Element productosPedidos = xml.insertar(pedidoVenta, "productosPedidos");	
			
			for (ProductosPedido productoPedido : pedido.getProductos()) {
				
				// Nodo de productosPedido
				HashMap<String, String> attr = new HashMap<>();
				atributos.put("id", productoPedido.getId()+"");			
				Element productosPedido = xml.insertar(productosPedidos, "productosPedido", attr);
				
					xml.insertar(productosPedido, "precioFinal"		, productoPedido.getPrecioFinal()+"");
					xml.insertar(productosPedido, "dctoPorcen"		, productoPedido.getPrecioFinal()+"");
					xml.insertar(productosPedido, "cantidad"		, productoPedido.getPrecioFinal()+"");
				
					// Insertamos el producto
					Producto p = productoPedido.getProducto();
					
					HashMap<String, String> attrProducto = new HashMap<>();
						atributos.put("id", p.getId()+"");
						atributos.put("categoria", p.getCategoria().toString());
					
					Element producto = xml.insertar(productosPedido, "producto", attrProducto);
						xml.insertar(producto, "alias"			, p.getAlias());
						xml.insertar(producto, "descripcion"	, p.getDescripcion());
						xml.insertar(producto, "precioVenta"	, p.getPrecioVenta()+"");
						xml.insertar(producto, "precioCompra"	, p.getPrecioCompra()+"");
						xml.insertar(producto, "stock"			, p.getStock()+"");
						xml.insertar(producto, "tipoIva"		, p.getTipoIva()+"");				
			}
			
			
		}
		
		return xml.finalizarDocumento();
	}
	
}
