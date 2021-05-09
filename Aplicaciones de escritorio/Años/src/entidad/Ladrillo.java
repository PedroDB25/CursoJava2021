package entidad;

public class Ladrillo {
	Integer largo, posicionx,posiciony,tipo;
	



	public Ladrillo(Integer largo, Integer posicionx, Integer posiciony) {
		setLargo(largo);
		setPosicionx(posicionx);
		setPosiciony(posiciony);
		
	}
	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public Integer getLargo() {
		return largo;
	}

	public void setLargo(Integer largo) {
		this.largo = largo;
	}

	public Integer getPosicionx() {
		return posicionx;
	}

	public void setPosicionx(Integer posicionx) {
		this.posicionx = posicionx;
	}

	public Integer getPosiciony() {
		return posiciony;
	}

	public void setPosiciony(Integer posiciony) {
		this.posiciony = posiciony;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((largo == null) ? 0 : largo.hashCode());
		result = prime * result + ((posicionx == null) ? 0 : posicionx.hashCode());
		result = prime * result + ((posiciony == null) ? 0 : posiciony.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Ladrillo other = (Ladrillo) obj;
		if (largo == null) {
			if (other.largo != null)
				return false;
		} else if (!largo.equals(other.largo))
			return false;
		if (posicionx == null) {
			if (other.posicionx != null)
				return false;
		} else if (!posicionx.equals(other.posicionx))
			return false;
		if (posiciony == null) {
			if (other.posiciony != null)
				return false;
		} else if (!posiciony.equals(other.posiciony))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

}
