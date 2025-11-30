package app.main;

import app.ControlMenuPrincipal;
import app.InputsApp;
import app.SerBDJeep;
import app.SerXmlJeep;
import app.interfaces.ISerBDJeep;
import app.interfaces.ISerXmlJeep;
import dominio.factorias.FacPedidosVenta;
import dominio.factorias.FacProducto;
import dominio.factorias.FacProductosPedido;
import dominio.factorias.interfaces.IFacPedidosVenta;
import dominio.factorias.interfaces.IFacProducto;
import dominio.factorias.interfaces.IFacProductosPedido;
import infrastrutura.RepoPedidosVenta;
import infrastrutura.RepoProducto;
import infrastrutura.RepoProductosPedido;
import infrastrutura.SerDom;
import infrastrutura.SerMySql;
import infrastrutura.interfaces.IRepoPedidosVenta;
import infrastrutura.interfaces.IRepoProducto;
import infrastrutura.interfaces.IRepoProductosPedido;
import infrastrutura.interfaces.ISerBD;
import infrastrutura.interfaces.ISerDom;
import util.DBUtils;

public class Main {

	public static void main(String[] args) {

		System.out.println("\n---- Bienvenido a JeeP! ----");
		try {
			// ---------- Carga de dependencias ---------
			
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
			
			// Servicio de DOM de creacion de XML
			ISerDom dom = new SerDom();
			ISerXmlJeep serXML = new SerXmlJeep(dom, serBD);
			
			// Inputs de consola
			InputsApp input = InputsApp.getInstance();
			
			// ---------- Fin carga de dependencias ---------
			while(true) {
				// Este bucle sirve para cargar el menu en bucle hasta que el menu cierre el programa.
				new ControlMenuPrincipal(serXML, serBD, input).render();				
			}

		} catch (Exception e) {
			System.out.println("Ha ocurrido un error inesperado en la conexion con la base de datos, por favor intentelo de nuevo, en caso de no solucionarse contacte con el equipo tecnico.");
			e.printStackTrace();
		} 
		
	}
	
}