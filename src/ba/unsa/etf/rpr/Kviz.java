package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//Zavrsiti predajKviz i klasu RezultatKviza i metodu toString


enum SistemBodovanja {BINARNO, PARCIJALNO, PARCIJALNO_SA_NEGATIVNIM};

public class Kviz {
    private String naziv;
    private Map<Pitanje, ArrayList<String>> pitanja;
    private SistemBodovanja sistemBodovanja;
    public Object getNaziv() {
        return naziv;
    }
    /*public RezultatKviza predajKviz(Map<Pitanje, ArrayList<String>> zaokruzeniOdgovori) {

    }*/

    public Kviz(String naziv, SistemBodovanja sistem) {
        this.naziv=naziv;
        this.pitanja = new HashMap<>();
        this.sistemBodovanja = sistem;
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

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Map<Pitanje, ArrayList<String>> getPitanja() {
        return pitanja;
    }

    public void setPitanja(Map<Pitanje, ArrayList<String>> pitanja) {
        this.pitanja = pitanja;
    }
    /*@Override
    public String toString() {
        return "Kviz" + naziv + "boduje se" + sistemBodovanja +".\n"+
                "Pitanja:";
    }
    RezultatKviza predajKvi(Map<Pitanje, ArrayList<String>> tacni_odgovori){


    }*/
}
