package src.Entidades;

public class Ropa {
    //Variables
    String Polera, Pantalones;
    Integer PosicionX, PosicionY, Id;

    //Constructores

    public Ropa(Integer id,String polera, String pantalones, Integer posicionX, Integer posicionY) {
        setId(id);;
        setPolera(polera);
        setPantalones(pantalones);
        setPosicionX(posicionX);
        setPosicionY(posicionY);
    }


    //Setter y Getters
    public String getPolera() {
        return Polera;
    }

    public void setPolera(String polera) {
        Polera = polera;
    }
    public String getPantalones() {
        return Pantalones;
    }
    public void setPantalones(String pantalones) {
        Pantalones = pantalones;
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
    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) {
        Id = id;
    }


    @Override
    public String toString() {
        return String.format(
            "La persona en la posicion [%s , %s] lleva una polera de color %s y pantalon %s ",
            PosicionX, PosicionY, Polera, Pantalones);
    }




    
    
}
