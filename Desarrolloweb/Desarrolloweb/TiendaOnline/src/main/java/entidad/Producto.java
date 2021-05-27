package entidad;

public class Producto {
	//Variables
	private Integer id, descuento, existencia, caducidad, precio;
	private String nombreProducto, marca, proveedor, categoria, tipoDeMascota, edadDeMascota;
	
	//Constructores
	
	public Producto(Integer id, Integer descuento, Integer existencia, Integer caducidad, Integer precio,
			String nombreProducto, String marca, String proveedor, String categoria, String tipoDeMascota,
			String edadDeMascota) {
		setId(id);
		setDescuento(descuento);
		setExistencia(existencia);
		setCaducidad(caducidad);
		setPrecio(precio);
		setNombreProducto(nombreProducto);
		setMarca(marca);
		setProveedor(proveedor);
		setCategoria(categoria);
		setTipoDeMascota(tipoDeMascota);
		setEdadDeMascota(edadDeMascota);
		}

	//Getters y setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDescuento() {
		return descuento;
	}
	public void setDescuento(Integer descuento) {
		this.descuento = descuento;
	}
	public Integer getExistencia() {
		return existencia;
	}
	public void setExistencia(Integer existencia) {
		this.existencia = existencia;
	}
	public Integer getCaducidad() {
		return caducidad;
	}
	public void setCaducidad(Integer caducidad) {
		this.caducidad = caducidad;
	}
	public Integer getPrecio() {
		return precio;
	}
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getTipoDeMascota() {
		return tipoDeMascota;
	}
	public void setTipoDeMascota(String tipoMascota) {
		this.tipoDeMascota = tipoMascota;
	}
	public String getEdadDeMascota() {
		return edadDeMascota;
	}
	public void setEdadDeMascota(String edadDeMascota) {
		this.edadDeMascota = edadDeMascota;
	}
	//metodos sobreescritos
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caducidad == null) ? 0 : caducidad.hashCode());
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((descuento == null) ? 0 : descuento.hashCode());
		result = prime * result + ((edadDeMascota == null) ? 0 : edadDeMascota.hashCode());
		result = prime * result + ((existencia == null) ? 0 : existencia.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((nombreProducto == null) ? 0 : nombreProducto.hashCode());
		result = prime * result + ((precio == null) ? 0 : precio.hashCode());
		result = prime * result + ((proveedor == null) ? 0 : proveedor.hashCode());
		result = prime * result + ((tipoDeMascota == null) ? 0 : tipoDeMascota.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (caducidad == null) {
			if (other.caducidad != null)
				return false;
		} else if (!caducidad.equals(other.caducidad))
			return false;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (descuento == null) {
			if (other.descuento != null)
				return false;
		} else if (!descuento.equals(other.descuento))
			return false;
		if (edadDeMascota == null) {
			if (other.edadDeMascota != null)
				return false;
		} else if (!edadDeMascota.equals(other.edadDeMascota))
			return false;
		if (existencia == null) {
			if (other.existencia != null)
				return false;
		} else if (!existencia.equals(other.existencia))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (nombreProducto == null) {
			if (other.nombreProducto != null)
				return false;
		} else if (!nombreProducto.equals(other.nombreProducto))
			return false;
		if (precio == null) {
			if (other.precio != null)
				return false;
		} else if (!precio.equals(other.precio))
			return false;
		if (proveedor == null) {
			if (other.proveedor != null)
				return false;
		} else if (!proveedor.equals(other.proveedor))
			return false;
		if (tipoDeMascota == null) {
			if (other.tipoDeMascota != null)
				return false;
		} else if (!tipoDeMascota.equals(other.tipoDeMascota))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Producto [id=" + id + ", descuento=" + descuento + ", existencia=" + existencia + ", caducidad="
				+ caducidad + ", precio=" + precio + ", nombreProducto=" + nombreProducto + ", marca=" + marca
				+ ", proveedor=" + proveedor + ", categoria=" + categoria + ", tipoMascota=" + tipoDeMascota
				+ ", edadDeMascota=" + edadDeMascota + "]";
	}
	
	
}
