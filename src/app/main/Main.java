package app.main;

import app.ControlMenuPrincipal;
import app.ISerBDJeep;
import app.ISerXmlJeep;
import app.InputsApp;
import app.SerBDJeep;
import app.SerXmlJeep;
import dominio.factorias.FacPedidosVenta;
import dominio.factorias.FacProducto;
import dominio.factorias.FacProductosPedido;
import dominio.factorias.IFacPedidosVenta;
import dominio.factorias.IFacProducto;
import dominio.factorias.IFacProductosPedido;
import infrastrutura.RepoPedidosVenta;
import infrastrutura.RepoProducto;
import infrastrutura.RepoProductosPedido;
import infrastrutura.SerMySql;
import infrastrutura.interfaces.IRepoPedidosVenta;
import infrastrutura.interfaces.IRepoProducto;
import infrastrutura.interfaces.IRepoProductosPedido;
import infrastrutura.interfaces.ISerBD;
import util.DBUtils;

public class Main {

	public static void main(String[] args) {

		System.out.println("\n---- Bienvenido a JeeP! ----");
		try {
			// ---------- Carga de dependencias ---------
			
			// Servicio de DOM de creacion de XML
			ISerXmlJeep serXML = new SerXmlJeep();
			
			// Servicio de conexion a la base de datos
			ISerBD bd = new SerMySql(DBUtils.DRIVER, DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			// Factorias de creacion de modelos de dominio
			// y repositorios de objetos
			
			// Producto
			IFacProducto fProducto = new FacProducto();
			IRepoProducto rProducto = new RepoProducto(bd, fProducto);
			
			// ProductosPedidos
			IFacProductosPedido fProductosPedidos = new FacProductosPedido(rProducto);
			IRepoProductosPedido rProductosPedido = new RepoProductosPedido(bd, fProductosPedidos);
			
			// PedidosVenta
			IFacPedidosVenta fPedidosVenta = new FacPedidosVenta(rProductosPedido);
			IRepoPedidosVenta rPedidosVenta = new RepoPedidosVenta(bd, fPedidosVenta);
			
			// Servicio de bd de aplicacion
			ISerBDJeep serBD = new SerBDJeep(rPedidosVenta, rProducto, rProductosPedido);
			
			// Inputs de consola
			InputsApp input = InputsApp.getInstance();
			
			// ---------- Fin carga de dependencias ---------
			while(true) {
				new ControlMenuPrincipal(serXML, serBD, input).render();				
			}

		} catch (Exception e) {
			System.out.println("Ha ocurrido un error inesperado en la conexion con la base de datos, por favor intentelo de nuevo, en caso de no solucionarse contacte con el equipo tecnico.");
			e.printStackTrace();
		} 
		
	}

	/*
	public static void leerXML() {

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
	}*/
}