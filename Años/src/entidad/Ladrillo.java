package entidad;

public class Ladrillo {
	Integer largo, posicionx,posiciony;
	

	public Ladrillo(Integer largo, Integer posicionx, Integer posiciony) {
		setLargo(largo);
		setPosicionx(posicionx);
		setPosiciony(posiciony);
		
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
	
	

}
