package dominio.model;

import java.sql.Date;
import java.util.ArrayList;

import dominio.EModel;

public class PedidosVenta implements EModel {

	private int id;
	private Date fecha;
	private boolean bRecoger;
	private Date fecEntrega;
	private String direccion;
	private int idFactura;
	private int idCliente;
	private ArrayList<ProductosPedido> productos;
	
	public PedidosVenta(int id, Date fecha, boolean bRecoger, Date fecEntrega, String direccion, int idFactura,
			int idCliente, ArrayList<ProductosPedido> productos) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.bRecoger = bRecoger;
		this.fecEntrega = fecEntrega;
		this.direccion = direccion;
		this.idFactura = idFactura;
		this.idCliente = idCliente;
		this.productos = productos;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public boolean isbRecoger() {
		return bRecoger;
	}
	public void setbRecoger(boolean bRecoger) {
		this.bRecoger = bRecoger;
	}
	public Date getFecEntrega() {
		return fecEntrega;
	}
	public void setFecEntrega(Date fecEntrega) {
		this.fecEntrega = fecEntrega;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public ArrayList<ProductosPedido> getProductos() {
		return productos;
	}
	public void setProductos(ArrayList<ProductosPedido> productos) {
		this.productos = productos;
	}
	
	
}