package entidad;
public class Personaje {
	//Constates iniciales
	private static final Integer EDAD_INICIA=0, DINERO_INICIAL=0, BIENESTAR=100;
	
	//variables
	private String nombre;
	private Integer edad, dinero, bienestar;
	
	public Personaje(String nombre, Integer edad, Integer dinero, Integer bienestar) {
		setBienestar(bienestar);
		setDinero(dinero);
		setEdad(edad);
		setNombre(nombre);	
	}
	
	public Personaje(String nombre) {
		this(nombre, EDAD_INICIA,DINERO_INICIAL,BIENESTAR);
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public Integer getDinero() {
		return dinero;
	}
	public void setDinero(Integer dinero) {
		this.dinero = dinero;
	}
	public Integer getBienestar() {
		return bienestar;
	}
	public void setBienestar(Integer bienestar) {
		this.bienestar = bienestar;
	}

	@Override
	public String toString() {
		return "Caracteristicas:\n"
				+ nombre + "(" + edad + ")\n" 
				+ dinero + "\nbienestar=" + bienestar;
	}
	
	

}
