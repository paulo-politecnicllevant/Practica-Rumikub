import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class JuegoBase implements Serializable{
    protected ArrayList<Jugador> jugadores;
    protected Mesa mesa;
    protected transient Scanner scanner;
    protected Mazo mazo;
    protected ArrayList<Carta> descarte;
    protected int turnoActual;

    public JuegoBase(int n) {
        jugadores = new ArrayList<>();
        mesa = new Mesa();
        scanner = new Scanner(System.in);

        for (int i = 1; i <= n; i++) {
            jugadores.add(new Jugador("Jugador " + i));
        }
    }

    public abstract void iniciar();

    public void guardarPartida(String archivo) {

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(archivo))) {

            oos.writeObject(this);

            System.out.println("Partida guardada correctamente");

        } catch (IOException e) {
            System.out.println("Error al guardar");
            e.printStackTrace();
        }
    }

    public static JuegoBase cargarPartida(String archivo) {

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(archivo))) {

            JuegoBase juego = (JuegoBase) ois.readObject();

            // reconstruir scanner porque es transient
            juego.scanner = new Scanner(System.in);

            return juego;

        } catch (Exception e) {

            System.out.println("Error al cargar");
            e.printStackTrace();

            return null;
        }
    }
}
