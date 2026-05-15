import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private ArrayList<Carta> mano;

    public Jugador(String nombre){
        this.nombre = nombre;
        this.mano = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMano(ArrayList<Carta> mano) {
        this.mano = mano;
    }

    public void anyadirCarta(Carta carta){
        mano.add(carta);
    }

    public void eliminarCarta(Carta carta){
        mano.remove(carta);
    }

    public void mostrarMano(){
        System.out.println("Mano de " + nombre);
        for (int i = 0; i < mano.size(); i++){
            System.out.println("Carta " + i + ": " + mano.get(i));
        }
    }

    public boolean gano(){
        return mano.isEmpty();
    }
}
