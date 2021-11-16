package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Kviz {
    private String naziv;
    private Map<Pitanje, ArrayList<String>> pitanja;

    public Object getNaziv() {
        return naziv;
    }
//popraaviti ENUM
    public enum SistemBodovanja {BINARNO, PARCIJALNO, PARCIJALNO_SA_NEGATIVNIM};
    private SistemBodovanja sistemBodovanja;

    public Kviz(String naziv, SistemBodovanja sistem) {
        this.naziv=naziv;
        this.pitanja = new HashMap<>();
        this.sistemBodovanja =sistem;
    }

    public Map<Pitanje, ArrayList<String>> getKolekicija() {
        return pitanja;
    }

    public void setKolekicija(Map<Pitanje, ArrayList<String>> kolekicija) {
        this.pitanja = kolekicija;
    }

    public SistemBodovanja getSistemBodovanja() {
        return sistemBodovanja;
    }

    public void setSistemBodovanja(SistemBodovanja sistemBodovanja) {
        this.sistemBodovanja = sistemBodovanja;
    }

    void dodajPitanje(Pitanje pitanje) throws IllegalArgumentException {
        for(Map.Entry<Pitanje, ArrayList<String>> entry : pitanja.entrySet()) {
            if (entry.getKey().getTekst().equalsIgnoreCase(pitanje.getTekst()))
                throw new IllegalArgumentException("Ne možete dodati pitanje sa tekstom koji već postoji");
        }
        pitanja.put(pitanje, null);

    }

    @Override
    public String toString() {
        return "Kviz" + naziv + "boduje se" + sistemBodovanja +".\n"+
                "Pitanja:";
    }
}
