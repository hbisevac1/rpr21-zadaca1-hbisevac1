package ba.unsa.etf.rpr;

import java.util.Map;

public class RezultatKviza {
    private Kviz kviz;
    private double total;
    private Map<Pitanje, Double> bodovi;

    public RezultatKviza(Kviz kviz) {
        this.kviz = kviz;
        this.total = 0;
    }

    public Kviz getKviz() {
        return kviz;
    }

    public void setKviz(Kviz kviz) {
        this.kviz = kviz;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Map<Pitanje, Double> getBodovi() {
        return bodovi;
    }

    public void setBodovi(Map<Pitanje, Double> bodovi) {
        this.bodovi = bodovi;
    }

    @Override
    public String toString() {
        String pom = "";
        for(Map.Entry<Pitanje, Double> entry : bodovi.entrySet()){
            pom += "\n\t" + entry.getKey().getTekst() + " - " + entry.getValue();
        }
        return "Na kvizu" + kviz.getNaziv() +
                "ostvarili ste ukupno " + total + pom;
    }
}
