import java.util.ArrayList;

public class Mesa {
    private ArrayList<ArrayList<Carta>> jugadas;

    public Mesa() {
        jugadas = new ArrayList<>();
    }

    public void agregar(ArrayList<Carta> jugada) {
        jugadas.add(jugada);
    }

    public void mostrar() {
        System.out.println("============ MESA ============");
        for (int i = 0; i < jugadas.size(); i++) {
            System.out.println(i + ": " + jugadas.get(i));
        }
    }
}