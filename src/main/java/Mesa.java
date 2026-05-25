import java.io.Serializable;
import java.util.ArrayList;

public class Mesa implements Serializable {
    private ArrayList<ArrayList<Carta>> jugadas;

    public Mesa() {
        jugadas = new ArrayList<>();
    }

    //Agrega una jugada a la mesa
    public void agregar(ArrayList<Carta> jugada) {
        if (Combinacion.esGrupo(jugada) || Combinacion.esEscalera(jugada)) {
            jugadas.add(jugada);
        }else{
            System.out.println("COMBINACION INVALIDA");
        }
    }

    //Muestra todas las jugadas que hay actualmente en la mesa
    public void mostrar() {
        System.out.println("============ MESA ============");
        for (int i = 0; i < jugadas.size(); i++) {
            System.out.println(i + ": " + jugadas.get(i));
        }
    }

    //Devuelve la lista completa de jugadas de la mesa
    public ArrayList<ArrayList<Carta>> getJugadas() {
        return jugadas;
    }

}