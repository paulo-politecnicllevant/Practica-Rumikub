import java.io.Serializable;
import java.util.ArrayList;

public class JuegoRummikub extends JuegoBase implements Serializable {
    private MazoRummikub bolsa;
    private boolean[] jugadorHaHecho30; //Para comprobar la primera jugada

    public JuegoRummikub(int n) {
        super(n);
        bolsa = new MazoRummikub();
        jugadorHaHecho30 = new boolean[n];
        repartir();
    }

    private void repartir() {
        for (int i = 0; i < 14; i++) {
            for (Jugador j : jugadores) {
                j.anyadirCarta(bolsa.robar());
            }
        }
    }

    private void jugarCombinacion(Jugador jugador, int turno) {
        System.out.println("¿Cuantas fichas tendra la combinacion?");
        int n = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Carta> jugada = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            jugador.mostrarMano();
            System.out.print("Elige el indice de las ficha: ");

            int indice = scanner.nextInt();
            scanner.nextLine();

            jugada.add(jugador.getMano().get(indice));
        }

        int puntos = Combinacion.puntos(jugada);

        if (!jugadorHaHecho30[turno]) {

            if (puntos < 30) {
                System.out.println("Necesitas al menos 30 puntos en tu primera jugada");
                return;
            } else {
                jugadorHaHecho30[turno] = true;
                System.out.println("Primera jugada valida ya que supera los 30 punos");
            }
        }

        if (Combinacion.esGrupo(jugada) || Combinacion.esEscalera(jugada)) {
            System.out.println("Combinacian valida: " + jugada);
            mesa.agregar(jugada);

            for (Carta c : jugada){
                jugador.eliminarCarta(c);
            }

        } else {
            System.out.println("Combinacion invalida");
        }
    }

    private void anadirAMesa(Jugador jugador, int turno) {
        if (!jugadorHaHecho30[turno]) {
            System.out.println("La jugada inicial ha de ser de como minimo 30 puntos");
            return;
        }

        mesa.mostrar();
        if (mesa.getJugadas().isEmpty()) {
            System.out.println("No hay jugadas en la mesa para añadir fichas");
            return;
        }

        System.out.print("Elige el número de la jugada: ");

        int jugadaIndex = scanner.nextInt();
        scanner.nextLine();

        if (jugadaIndex < 0 || jugadaIndex >= mesa.getJugadas().size()) {
            System.out.println("Ese indice es incorrecto");
            return;
        }

        jugador.mostrarMano();
        System.out.print("Elige la ficha para añadir: ");

        int cartaIndex = scanner.nextInt();
        scanner.nextLine();

        if (cartaIndex < 0 || cartaIndex >= jugador.getMano().size()) {
            System.out.println("No existe el indice de la ficha");
            return;
        }

        Carta ficha = jugador.getMano().get(cartaIndex);
        ArrayList<Carta> jugada = mesa.getJugadas().get(jugadaIndex);

        jugada.add(ficha);

        if (Combinacion.esGrupo(jugada) || Combinacion.esEscalera(jugada)) {
            System.out.println("Ficha añadida correctamente");
            jugador.eliminarCarta(ficha);
        } else {
            System.out.println("Esa ficha no se puede añadir");
            jugada.remove(ficha);
        }
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

            jugador.mostrarMano();
            mesa.mostrar();

            //Robar de la bolsa
            if (!bolsa.estaVacio()) {
                Carta robada = bolsa.robar();
                jugador.anyadirCarta(robada);
                System.out.println("Has robado: " + robada);
            } else {
                System.out.println("No quedan fichas en la bolsa");
            }

            boolean turnoTerminado = false;

            while (!turnoTerminado) {
                System.out.println("============= ACCIONES =============");
                System.out.println("1. Ver mano");
                System.out.println("2. Jugar combinacion");
                System.out.println("3. Añadir ficha a combinacion existente");
                System.out.println("4. Terminar turno");
                System.out.println("5. Guardar partida");
                System.out.print("Elige opcion: ");

                int accion = scanner.nextInt();
                scanner.nextLine();

                switch (accion) {
                    case 1:
                        jugador.mostrarMano();
                        break;

                    case 2:
                        jugarCombinacion(jugador, turnoActual);
                        break;

                    case 3:
                        anadirAMesa(jugador, turnoActual);
                        break;

                    case 4:
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

            if (jugador.gano()) {
                System.out.println(jugador.getNombre() + " ha ganado la partida");
                fin = true;
            }

            turnoActual = (turnoActual + 1) % jugadores.size();
        }
    }
}
