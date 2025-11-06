package accesoDatosJeep.model;

public class Producto {

	public enum Categoria{
		juguetes,
		alimentos,
		maquinaria,
		herramientas
	}
	
	private int id;
	private String alias;
	private String descripcion;
	private double precioVenta;
	private double precioCompra;
	private int stock;
	private Producto.Categoria categoria;
	private int tipoIva;
	
	public Producto(int id, String alias, String descripcion, double precioVenta, double precioCompra, int stock,
			Categoria categoria, int tipoIva) {
		super();
		this.id = id;
		this.alias = alias;
		this.descripcion = descripcion;
		this.precioVenta = precioVenta;
		this.precioCompra = precioCompra;
		this.stock = stock;
		this.categoria = categoria;
		this.tipoIva = tipoIva;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}
	public double getPrecioCompra() {
		return precioCompra;
	}
	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Producto.Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Producto.Categoria categoria) {
		this.categoria = categoria;
	}
	public int getTipoIva() {
		return tipoIva;
	}
	public void setTipoIva(int tipoIva) {
		this.tipoIva = tipoIva;
	}	
}