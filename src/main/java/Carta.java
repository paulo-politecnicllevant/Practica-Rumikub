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

}
