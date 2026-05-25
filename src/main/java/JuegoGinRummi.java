import java.io.Serializable;
import java.util.ArrayList;

public class JuegoGinRummi extends JuegoBase implements Serializable {

    public JuegoGinRummi(int n) {
        super(n);
        this.mazo = new Mazo();
        this.descarte = new ArrayList<>();
        repartir();
    }

    public void repartir(){
        for (int i = 0; i < 10; i++){
            for (Jugador j: jugadores){
                j.anyadirCarta(mazo.robar());
            }
        }
        descarte.add(mazo.robar());
    }

    private void descartar(Jugador jugador) {
        jugador.mostrarMano();
        System.out.print("Elige la carta que quieres descartar: ");

        int index = scanner.nextInt();
        scanner.nextLine();

        Carta carta = jugador.getMano().get(index);
        jugador.eliminarCarta(carta);
        descarte.add(carta);

        System.out.println(jugador.getNombre() + " ha descartado: " + carta);
    }

    @Override
    public void iniciar() {

        turnoActual = turnoActual;
        boolean fin = false;

        while (!fin) {

            Jugador jugador = jugadores.get(turnoActual);

            System.out.println("==============================");
            System.out.println("TURNO DE " + jugador.getNombre());
            System.out.println("==============================");

            System.out.println("Carta en descarte: " + descarte.getLast());

            System.out.println("1. Robar del mazo");
            System.out.println("2. Robar del descarte");
            System.out.print("Opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            Carta robada;
            if (opcion == 1) {
                robada = mazo.robar();
                System.out.println("Robas del mazo: " + robada);
            } else {
                robada = descarte.removeLast();
                System.out.println("Robas del descarte: " + robada);
            }

            jugador.anyadirCarta(robada);

            boolean turnoTerminado = false;

            while (!turnoTerminado) {

                System.out.println("1. Ver mano");
                System.out.println("2. Hacer KNOCK");
                System.out.println("3. Hacer GIN");
                System.out.println("4. Descartar y terminar turno");
                System.out.println("5. Guardar partida");
                System.out.print("Opcion: ");

                int accion = scanner.nextInt();
                scanner.nextLine();

                switch (accion) {

                    case 1:
                        jugador.mostrarMano();
                        break;

                    case 2:
                        if (jugador.puntosMuertos() <= 10) {
                            System.out.println(jugador.getNombre() + " hace kncok");
                            finDeRonda(jugador);
                            return;
                        } else {
                            System.out.println("No puedes hacer knock (puntos muertos > 10)");
                        }
                        break;

                    case 3:
                        if (jugador.tieneGin()) {
                            System.out.println(jugador.getNombre() + " hace gin");
                            finDeRonda(jugador);
                            return;
                        } else {
                            System.out.println("No tienes gin");
                        }
                        break;

                    case 4:
                        descartar(jugador);
                        turnoTerminado = true;
                        break;

                    case 5:
                        System.out.print("Nombre del archivo: ");
                        String nombre = scanner.nextLine();
                        guardarPartida(nombre);
                        break;

                    default:
                        System.out.println("Opcion invalida");
                }
            }

            turnoActual = (turnoActual + 1) % jugadores.size();
        }
    }

    private void finDeRonda(Jugador ganador) {
        System.out.println("===== FIN DE LA RONDA =====");
        for (Jugador j : jugadores) {
            j.mostrarMano();
            System.out.println("Puntos muertos: " + j.puntosMuertos());
        }
        System.out.println("Ganador: " + ganador.getNombre());
    }
}
