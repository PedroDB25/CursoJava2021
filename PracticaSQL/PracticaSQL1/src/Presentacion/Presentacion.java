package src.Presentacion;

import java.util.ArrayList;

import src.Dal.AccesoDatosException;
import src.Dal.DaoRopa;
import src.Entidades.Ropa;

public class Presentacion {
    public static void main(String[] args) {
        try {
            Ropa ropa = DaoRopa.obtenerPorId(2);

            System.out.println(ropa);
//            System.out.println(String.format("La posicion es [%d,%d]", ropa.getPosicionX(), ropa.getPosicionY()));
            mostrarTodos();

            ropa = DaoRopa.insertar(new Ropa(null, "Roja", "Verde", 6, 6));

            System.out.println("INSERTADO -> " + ropa);

            mostrarTodos();

            DaoRopa.modificar(new Ropa(ropa.getId(), "Modi", "Modi",7 ,7));

            mostrarTodos();

            //DaoRopa.borrar(ropa.getId());

            //mostrarTodos();
        } catch (AccesoDatosException ade) {
            System.out.println("Ha habido un error accediendo a la base de datos");
            System.out.println(ade.getMessage());

            if (ade.getCause() != null) {
                System.out.println(ade.getCause().getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error no esperado");
            System.out.println(e.getMessage());
        }
    }


    private static void mostrarTodos() {
        ArrayList<Ropa> ropas = DaoRopa.obtenerTodos();
        System.out.println("MOSTRANDO TODOS");
        for (Ropa ropa : ropas) {
            System.out.println(ropa);
        }
    }
}
