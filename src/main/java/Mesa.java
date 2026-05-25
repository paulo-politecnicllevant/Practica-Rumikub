import java.io.Serializable;
import java.util.ArrayList;

public class Mesa implements Serializable {
    private ArrayList<ArrayList<Carta>> jugadas;

    public Mesa() {
        jugadas = new ArrayList<>();
    }

    public void agregar(ArrayList<Carta> jugada) {
        if (Combinacion.esGrupo(jugada) || Combinacion.esEscalera(jugada)) {
            jugadas.add(jugada);
        }else{
            System.out.println("COMBINACION INVALIDA");
        }
    }

    public void mostrar() {
        System.out.println("============ MESA ============");
        for (int i = 0; i < jugadas.size(); i++) {
            System.out.println(i + ": " + jugadas.get(i));
        }
    }

    public ArrayList<ArrayList<Carta>> getJugadas() {
        return jugadas;
    }

}