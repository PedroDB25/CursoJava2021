package entidad;

public class Punto {
	private final static Integer INICIALX=215,INICIALY=0;
	private Integer PosicionX,PosicionY;
	
	public Punto () {
		this(INICIALX, INICIALY);
	}
	
	public Punto(Integer posicionX, Integer posicionY) {
		setPosicionX(posicionX);
		setPosicionY(posicionY);
	}

	public Integer getPosicionX() {
		return PosicionX;
	}

	public void setPosicionX(Integer posicionX) {
		PosicionX = posicionX;
	}

	public Integer getPosicionY() {
		return PosicionY;
	}

	public void setPosicionY(Integer posicionY) {
		PosicionY = posicionY;
	}
	
	

}
