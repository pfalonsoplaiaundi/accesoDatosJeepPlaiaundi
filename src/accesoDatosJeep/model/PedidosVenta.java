package accesoDatosJeep.model;

public class PedidosVenta {

	private int id;
	private int idPedido;
	private double precioFinal;
	private double dctoPorcen;
	private int cantidad;
	private Producto producto;
	public PedidosVenta(int id, int idPedido, double precioFinal, double dctoPorcen, int cantidad, Producto producto) {
		super();
		this.id = id;
		this.idPedido = idPedido;
		this.precioFinal = precioFinal;
		this.dctoPorcen = dctoPorcen;
		this.cantidad = cantidad;
		this.producto = producto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public double getPrecioFinal() {
		return precioFinal;
	}
	public void setPrecioFinal(double precioFinal) {
		this.precioFinal = precioFinal;
	}
	public double getDctoPorcen() {
		return dctoPorcen;
	}
	public void setDctoPorcen(double dctoPorcen) {
		this.dctoPorcen = dctoPorcen;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	
	
}
