import enums.TipoJugada;

import java.io.Serializable;
import java.util.ArrayList;

public class Jugador implements Serializable{
    private String nombre;
    private ArrayList<Carta> mano;

    public Jugador(String nombre){
        this.nombre = nombre;
        this.mano = new ArrayList<>();
    }

    //GETTERS / SETTERS
    public String getNombre() {
        return nombre;
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Añade una carta a la mano del jugador
    public void setMano(ArrayList<Carta> mano) {
        this.mano = mano;
    }

    public void anyadirCarta(Carta carta){
        mano.add(carta);
    }

    //Elimina una carta de la mano del jugador
    public void eliminarCarta(Carta carta){
        mano.remove(carta);
    }

    public Combinacion crearCombinacion(TipoJugada tipo, ArrayList<Integer> indices){
        Combinacion combinacion = new Combinacion(tipo);

        for(Integer indice : indices){
            combinacion.agregarCarta(mano.get(indice));
        }

        return combinacion;
    }

    public void quitarCombinacion(Combinacion combinacion){
        for(Carta carta : combinacion.getCartas()){
            eliminarCarta(carta);
        }
    }

    //Muestra todas las cartas que tiene el jugador
    public void mostrarMano(){
        System.out.println("Mano de " + nombre);
        for (int i = 0; i < mano.size(); i++){
            System.out.println("[Carta " + i + "] " + mano.get(i));
        }
    }

    public boolean gano(){
        return mano.isEmpty();
    }

    //Calcula los puntos muertos del jugador (Gin Rummi)
    public int puntosMuertos() {
        int suma = 0;
        for (Carta c : mano) suma += c.getPuntos();
        return suma;
    }

    //Indica si el jugador tiene "Gin"
    public boolean tieneGin() {
        return puntosMuertos() == 0;
    }
}
