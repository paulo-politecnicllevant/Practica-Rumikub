import enums.TipoJugada;

import java.io.Serializable;
import java.util.ArrayList;

public class JuegoRummyArgentino extends JuegoBase implements Serializable {

    public JuegoRummyArgentino(int n) {
        super(n);
        repartirCartas(9);
        descarte.add(mazo.robar());
    }

    //Método para bajar una combinación nueva
    private void bajarCombinacion(Jugador jugador){
        System.out.println("1. Grupo");
        System.out.println("2. Escalera");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        TipoJugada tipo;

        if(opcion == 1){
            tipo = TipoJugada.GRUPO;
        }else{
            tipo = TipoJugada.ESCALERA;
        }

        Combinacion combinacion = new Combinacion(tipo);

        System.out.println("Cantidad de cartas:");

        int n = scanner.nextInt();
        scanner.nextLine();

        for(int i = 0; i < n; i++){
            jugador.mostrarMano();

            int indice = scanner.nextInt();
            scanner.nextLine();

            combinacion.agregarCarta(jugador.getMano().get(indice));
        }

        int comodines = 0;

        for(Carta carta : combinacion.getCartas()){
            if(carta.esComodin()){
                comodines++;
            }
        }

        if(comodines > 1){
            System.out.println("Solo un comodin");
            return;
        }

        if(combinacion.esValida()){
            mesa.agregar(combinacion);

            jugador.quitarCombinacion(combinacion);

            System.out.println("Combinacion colocada");
        }else{
            System.out.println("Combinacion invalida");
        }
    }

    //Método para descartar una carta
    private void descartar(Jugador jugador) {
        jugador.mostrarMano();
        System.out.print("Elige una carta para descartar: ");

        int indice = scanner.nextInt();
        scanner.nextLine();

        Carta carta = jugador.getMano().get(indice);
        jugador.eliminarCarta(carta);
        descarte.add(carta);

        System.out.println("Has descartado: " + carta);
    }

    //Método para añadir una carta a una combinación ya existente
    private void anadirAMesa(Jugador jugador) {
        mesa.mostrar();
        System.out.print("Numero:");

        int indice = scanner.nextInt();
        scanner.nextLine();

        jugador.mostrarMano();
        System.out.print("Carta:");

        int carta = scanner.nextInt();
        scanner.nextLine();

        Carta seleccionada = jugador.getMano().get(carta);

        Combinacion combinacion = mesa.getCombinaciones().get(indice);

        combinacion.agregarCarta(seleccionada);

        int comodines = 0;

        for(Carta c : combinacion.getCartas()){
            if(c.esComodin()){
                comodines++;
            }
        }

        if(comodines > 1){
            combinacion.getCartas().remove(seleccionada);
            return;
        }

        if(combinacion.esValida()){
            jugador.eliminarCarta(seleccionada);
        }else{
            combinacion.getCartas().remove(seleccionada);
        }
    }

    //Método para iniciar una partida
    @Override
    public void iniciar() {
        boolean fin = false;

        while (!fin) {

            Jugador jugador = obtenerJugadorActual();

            System.out.println("==============================");
            System.out.println("TURNO DE " + jugador.getNombre());
            System.out.println("==============================");

            jugador.mostrarMano();
            System.out.println("Carta en descarte: " + descarte.get(descarte.size() - 1));

            System.out.println("1. Robar del mazo");
            System.out.println("2. Robar del descarte");
            System.out.print("Elige una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            Carta robada;

            if (opcion == 1) {
                robada = mazo.robar();
                System.out.println("Has robado del mazo: " + robada);
            } else {
                robada = descarte.remove(descarte.size() - 1);
                System.out.println("Has robado del descarte: " + robada);
            }

            jugador.anyadirCarta(robada);

            boolean turnoTerminado = false;

            while (!turnoTerminado) {

                System.out.println("============ ACCIONES ==============");
                System.out.println("1. Ver mano");
                System.out.println("2. Bajar combinacion");
                System.out.println("3. Añadir a la mesa");
                System.out.println("4. Descartar y terminar turno");
                System.out.println("5. Guardar partida");
                System.out.print("Elige una opción: ");

                int accion = scanner.nextInt();
                scanner.nextLine();

                switch (accion) {
                    case 1:
                        jugador.mostrarMano();
                        break;

                    case 2:
                        bajarCombinacion(jugador);
                        break;

                    case 3:
                        anadirAMesa(jugador);
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

            //Si el jugador se quedó sin cartas, gana
            if (hayGanador()) {
                System.out.println(jugador.getNombre() + " ha ganado la ronda");
                fin = true;
            }
            siguienteTurno();
        }
    }
}