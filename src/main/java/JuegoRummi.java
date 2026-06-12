import enums.TipoJugada;

import java.io.Serializable;
import java.util.ArrayList;

public class JuegoRummi extends JuegoBase implements Serializable {
    public JuegoRummi(int n) {
        super(n);
        repartirCartas(14);
        descarte.add(mazo.robar());
    }

    //Método para bajar una combinación nueva
    private void jugarCombinacion(Jugador jugador) {
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

        System.out.println("¿Cuantas cartas bajas?");

        int cantidad = scanner.nextInt();
        scanner.nextLine();

        for(int i = 0; i < cantidad; i++){
            jugador.mostrarMano();

            System.out.print("Indice: ");

            int indice = scanner.nextInt();
            scanner.nextLine();

            combinacion.agregarCarta(jugador.getMano().get(indice));
        }

        if(combinacion.esValida()){
            mesa.agregar(combinacion);

            jugador.quitarCombinacion(combinacion);
            System.out.println("Combinacion colocada");
        } else {
            System.out.println("Combinacion invalida");
        }
    }

    //Método para añadir una carta a una combinación ya existente
    private void anadirAMesa(Jugador jugador) {
        mesa.mostrar();

        System.out.print("Numero combinacion: ");

        int indice = scanner.nextInt();
        scanner.nextLine();

        jugador.mostrarMano();

        System.out.print("Carta: ");

        int carta = scanner.nextInt();
        scanner.nextLine();

        Carta seleccionada = jugador.getMano().get(carta);

        Combinacion combinacion = mesa.getCombinaciones().get(indice);

        combinacion.agregarCarta(seleccionada);

        if(combinacion.esValida()) {
            jugador.eliminarCarta(seleccionada);

            System.out.println("Carta añadida");

        }else{
            combinacion.getCartas().remove(seleccionada);

            System.out.println("Movimiento invalido");
        }
    }

    //Método para descartar una carta
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

    //Método para iniciar una partida
    @Override
    public void iniciar() {
        turnoActual = turnoActual;
        boolean fin = false;

        while (!fin) {

            Jugador jugador = obtenerJugadorActual();

            System.out.println("==============================");
            System.out.println("TURNO DE " + jugador.getNombre());
            System.out.println("==============================");

            jugador.mostrarMano();
            mesa.mostrar();
            System.out.println("Carta en descarte: " + descarte.get(descarte.size() - 1));

            //Robar carta
            System.out.println("1. Robar del mazo");
            System.out.println("2. Robar del descarte");
            System.out.print("Elige opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            Carta robada;
            if (opcion == 1) {
                robada = mazo.robar();
                System.out.println(jugador.getNombre() + " roba del mazo: " + robada);
            } else {
                robada = descarte.remove(descarte.size() - 1);
                System.out.println(jugador.getNombre() + " roba del descarte: " + robada);
            }

            jugador.anyadirCarta(robada);

            boolean turnoTerminado = false;

            while (!turnoTerminado) {
                System.out.println("============= ACCIONES =============");
                System.out.println("1. Ver mano");
                System.out.println("2. Jugar combinación");
                System.out.println("3. Añadir carta a combinación existente");
                System.out.println("4. Descartar y terminar turno");
                System.out.println("5. Guardar partida");
                System.out.print("Elige opción: ");

                int accion = scanner.nextInt();
                scanner.nextLine();

                switch (accion) {
                    case 1:
                        jugador.mostrarMano();
                        break;

                    case 2:
                        jugarCombinacion(jugador);
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

            if (hayGanador()) {
                System.out.println(jugador.getNombre() + " ha ganado la partida");
                fin = true;
            }

            siguienteTurno();
        }
    }

}