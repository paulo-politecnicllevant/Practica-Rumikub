import enums.Palo;
import interfaces.PiezaJuego;

import java.io.Serializable;

public class Carta implements Serializable, PiezaJuego {
    private Palo palo;
    private int valor;
    private boolean joker;

    public Carta(Palo palo, int valor, boolean joker){
        this.palo = palo;
        this.valor = valor;
        this.joker = joker;
    }


    //GETTERS / SETTERS
    public Palo getPalo(){
        return palo;
    }

    @Override
    public int getValor(){
        return valor;
    }

    public boolean esJoker(){
        return joker;
    }

    public void setPalo(Palo palo) {
        this.palo = palo;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    //Devuelve los puntos de la carta según las reglas del Rummi
    public int getPuntos() {
        if (joker){
            return 20;
        }

        if (valor <= 7){
            return 5;
        }

        return 10;
    }

    public int getPuntosArgentino() {

        //JOKER
        if (joker){
            return 50;
        }

        //Mono (el 2)
        if (valor == 2){
            return 20;
        }

        //As
        if (valor == 1){
            return 15;
        }

        if (valor >= 3 && valor <= 7){
            return 5;
        }

        if (valor >= 8 && valor <= 13){
            return 10;
        }

        return 0;
    }

    @Override
    public boolean esComodin(){
        return joker;
    }

    @Override
    public String toString() {
        if (joker){
            return "JOKER";
        }

        String nombre;
        switch (valor) {
            case 1: nombre = "A"; break;
            case 11: nombre = "J"; break;
            case 12: nombre = "Q"; break;
            case 13: nombre = "K"; break;
            default: nombre = String.valueOf(valor);
        }

        return nombre + "-" + palo;
    }

}
