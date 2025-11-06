package accesoDatosJeep.main;

import accesoDatosJeep.model.*;
import util.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
    	
    	// Se guardan dentro de la lista los pedidos leidos del XML
    	List<PedidosVenta> pedidosLeidos = XMLReader.leerXML("pedidos_venta.xml");
    	
    	// Si hay pedidos dentro del XML, se ejecuta lo siguiente
        if (pedidosLeidos != null && !pedidosLeidos.isEmpty()) {
            System.out.println("📦 Pedidos leídos: " + pedidosLeidos.size());
            System.out.println("-----------------------------------------");
            
            for (PedidosVenta pedido : pedidosLeidos) {
                System.out.println("🧾 Pedido ID: " + pedido.getId());
                System.out.println("➡ ID Pedido: " + pedido.getIdPedido());
                System.out.println("💰 Precio Final: " + pedido.getPrecioFinal());
                System.out.println("🔢 Cantidad: " + pedido.getCantidad());
                System.out.println("📉 Descuento: " + pedido.getDctoPorcen());
                if (pedido.getProducto() != null) {
                    Producto p = pedido.getProducto();
                    System.out.println("🧱 Producto: " + p.getAlias() + " (" + p.getCategoria() + ")");
                    System.out.println("💲 Precio Venta: " + p.getPrecioVenta());
                }
                System.out.println("-----------------------------------------");
            }
        } else {
            System.out.println("⚠ No se encontraron pedidos en el XML o hubo un error al leerlo.");
        }
    
    	// Datos ya creados y añadidos al XML
        
   /*   // 1. Crear algunos productos
        Producto p1 = new Producto(1, "Taladro", "Taladro eléctrico", 50.0, 30.0, 15, Producto.Categoria.herramientas, 21);
        Producto p2 = new Producto(2, "Excavadora", "Excavadora compacta", 15000.0, 12000.0, 3, Producto.Categoria.maquinaria, 21);
        Producto p3 = new Producto(3, "Osito", "Osito de peluche", 15.0, 7.0, 50, Producto.Categoria.juguetes, 10);

        // 2. Crear algunos pedidos
        PedidosVenta pedido1 = new PedidosVenta(1, 1001, 135.0, 10.0, 3, p1);
        PedidosVenta pedido2 = new PedidosVenta(2, 1002, 30000.0, 5.0, 2, p2);
        PedidosVenta pedido3 = new PedidosVenta(3, 1003, 45.0, 0.0, 3, p3);

        // 3. Crear lista con los pedidos
        List<PedidosVenta> listaPedidos = new ArrayList<>();
        listaPedidos.add(pedido1);
        listaPedidos.add(pedido2);
        listaPedidos.add(pedido3);  */
   
    }
}