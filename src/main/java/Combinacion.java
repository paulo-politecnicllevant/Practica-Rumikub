import enums.Palo;
import enums.TipoJugada;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Combinacion implements Serializable {

    private TipoJugada tipo;

    private ArrayList<Carta> cartas;

    public Combinacion(TipoJugada tipo){
        this.tipo = tipo;
        cartas = new ArrayList<>();
    }

    public TipoJugada getTipo() {
        return tipo;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public void agregarCarta(Carta carta){
        cartas.add(carta);
    }

    public boolean esValida(){
        if (tipo == TipoJugada.GRUPO){
            return esGrupo();
        }else{
            return esEscalera();
        }
    }

    //Compruebo si una lista de cartas forma un GRUPO
    public boolean esGrupo() {
        if (cartas.size() < 3 || cartas.size() > 4){
            return false;
        }

        int valor = -1;

        ArrayList<Enum<?>> usados = new ArrayList<>();

        for (Carta c : cartas) {
            if (c.esJoker()) {
                continue;
            }

            if (valor == -1) {
                // Primera carta no comodín define el valor del grupo
                valor = c.getValor();
            } else if (valor != c.getValor()) {
                // Si alguna carta no coincide, no es un grupo
                return false;
                // No repetir palo
            } else if(usados.contains(c.getTipo())){
                return false;
            }
            usados.add(c.getTipo());
        }
        return true;
    }

    // Compruebo si una lista de cartas forma un GRUPO
    private boolean esEscalera(){
        if (cartas.size() < 4){
            return false;
        }

        ArrayList<Carta> copia = new ArrayList<>(cartas);

        copia.sort(Comparator.comparingInt(Carta::getValor));

        Enum<?> tipo = null;

        for (Carta c : copia){
            if (c.esJoker()){
                continue;
            }

            if (tipo == null){
                tipo = c.getTipo();

            } else if (tipo != c.getTipo()){
                return false;
            }
        }

        int jokers = (int) copia.stream().filter(Carta::esJoker).count();

        for (int i = 1; i < copia.size(); i++){
            Carta previa = copia.get(i - 1);

            Carta actual = copia.get(i);

            if (previa.esJoker() || actual.esJoker()){
                continue;
            }

            int diferencia = actual.getValor() - previa.getValor();

            if (diferencia == 0){
                return false;
            }

            int faltan = diferencia - 1;

            if (faltan > jokers){
                return false;
            }
            jokers -= faltan;
        }

        return true;
    }

    //Calcula los puntos totales de una combinación
    public int puntos() {
        int suma = 0;

        for (Carta c : cartas) {
            suma += c.getPuntos();
        }

        return suma;
    }

    @Override
    public String toString() {
        return tipo + " --> " + cartas;
    }
}
