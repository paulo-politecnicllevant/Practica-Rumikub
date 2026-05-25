import java.io.Serializable;
import java.util.ArrayList;

public class PartidaGuardada implements Serializable {
    public ArrayList<Jugador> jugadores;
    public ArrayList<ArrayList<Carta>> mesa;
    public ArrayList<Carta> descarte;
    public ArrayList<Carta> mazo;
    public int turno;
    public String modo;

    public PartidaGuardada(ArrayList<Jugador> jugadores,
                           ArrayList<ArrayList<Carta>> mesa,
                           ArrayList<Carta> descarte,
                           ArrayList<Carta> mazo,
                           int turno,
                           String modo) {

        this.jugadores = jugadores;
        this.mesa = mesa;
        this.descarte = descarte;
        this.mazo = mazo;
        this.turno = turno;
        this.modo = modo;
    }
}
