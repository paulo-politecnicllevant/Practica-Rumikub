import java.io.Serializable;
import java.util.ArrayList;

public class Mesa implements Serializable {
    private ArrayList<Combinacion> combinaciones;

    public Mesa() {
        combinaciones = new ArrayList<>();
    }

    //Agrega una combinacion a la mesa
    public void agregar(Combinacion combinacion) {
        if (combinacion.esValida()) {
            combinaciones.add(combinacion);
        }else{
            System.out.println("COMBINACION INVALIDA");
        }
    }

    //Muestra todas las combinaciones que hay actualmente en la mesa
    public void mostrar() {
        System.out.println("============ MESA ============");
        for (int i = 0; i < combinaciones.size(); i++) {
            System.out.println(i + ": " + combinaciones.get(i));
        }
    }

    //Devuelve la lista completa de combinaciones de la mesa
    public ArrayList<Combinacion> getCombinaciones() {
        return combinaciones;
    }

}