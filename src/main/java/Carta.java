public class Carta {
    private String palo;
    private int valor;
    private boolean joker;

    public Carta(String palo, int valor, boolean joker){
        this.palo = palo;
        this.valor = valor;
        this.joker = joker;
    }

    public String getPalo(){
        return palo;
    }

    public int getValor(){
        return valor;
    }

    public boolean esJoker(){
        return true;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getPuntos() {
        if (joker){
            return 20;
        }

        if (valor <= 7){
            return 5;
        }

        return 10;
    }

    @Override
    public String toString() {
        if (joker) return "JOKER";

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
