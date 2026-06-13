import enums.TipoJugada;
import enums.TipoMazo;

import java.io.Serializable;
import java.util.ArrayList;

public class JuegoRummikub extends JuegoBase implements Serializable {
    private boolean[] jugadorHaHecho30; //Para comprobar la primera jugada

    public JuegoRummikub(int n) {
        super(n);
        mazo = new Mazo(TipoMazo.FICHAS);
        jugadorHaHecho30 = new boolean[n];
        repartir();
    }

    //Método para repartir cartas
    private void repartir() {
        for (int i = 0; i < 14; i++) {
            for (Jugador j : jugadores) {
                j.anyadirCarta(mazo.robar());
            }
        }
    }

    //Método para bajar una combinación nueva
    private void jugarCombinacion(Jugador jugador, int turno) {
        System.out.println("1. Grupo");
        System.out.println("2. Escalera");

        int opcion = scanner.nextInt();scanner.nextLine();

        TipoJugada tipo = opcion == 1 ? TipoJugada.GRUPO : TipoJugada.ESCALERA;

        Combinacion jugada = new Combinacion(tipo);

        System.out.println("Cuantas fichas:");

        int n = scanner.nextInt();
        scanner.nextLine();

        for(int i = 0; i < n;i++) {
            jugador.mostrarMano();

            int indice = scanner.nextInt();
            scanner.nextLine();

            jugada.agregarCarta(jugador.getMano().get(indice));
        }

        int puntos = jugada.puntos();

        if (!jugadorHaHecho30[turno]) {
            if(puntos <30) {
                System.out.println("Necesitas 30 puntos");
                return;
            }
            jugadorHaHecho30[turno] = true;
        }

        if(jugada.esValida()){
            mesa.agregar(jugada);

            for(Carta c: jugada.getCartas()){
                jugador.eliminarCarta(c);
            }

            System.out.println("Combinacion valida");
        } else {
            System.out.println("Combinacion invalida");
        }
    }

    //Método para añadir una carta a una combinación ya existente
    private void anadirAMesa(Jugador jugador, int turno) {
        if (!jugadorHaHecho30[turno]) {
            System.out.println("Primero debes bajar 30 puntos");
            return;
        }

        mesa.mostrar();
        if (mesa.getCombinaciones().isEmpty()) {
            System.out.println("No hay combinaciones");
            return;
        }

        System.out.print("Elige jugada: ");

        int jugadaIndex = scanner.nextInt();
        scanner.nextLine();

        jugador.mostrarMano();

        System.out.print("Elige ficha: ");

        int cartaIndex = scanner.nextInt();
        scanner.nextLine();

        Carta ficha = jugador.getMano().get(cartaIndex);

        Combinacion jugada = mesa.getCombinaciones().get(jugadaIndex);

        jugada.agregarCarta(ficha);

        if(jugada.esValida()){
            jugador.eliminarCarta(ficha);

            System.out.println("Ficha añadida");
        }else{
            jugada.getCartas().remove(ficha);

            System.out.println("Movimiento invalido");
        }
    }

    //Método para iniciar una partida
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
            if (!mazo.estaVacio()) {
                Carta robada = mazo.robar();
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
