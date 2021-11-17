package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// i metodu toString


enum SistemBodovanja {BINARNO("binarno"), PARCIJALNO("parcijalno"), PARCIJALNO_SA_NEGATIVNIM("parcijalno sa negativnim");
    private String ime;
    SistemBodovanja(String s) {
        this.ime=s;
    }
    public String getIme(){
        return ime;
    }
};

public class Kviz {
    private String naziv;
    private Map<Pitanje, ArrayList<String>> pitanja;
    private SistemBodovanja sistemBodovanja;

    public Object getNaziv() {
        return naziv;
    }

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

    public RezultatKviza predajKviz(Map<Pitanje, ArrayList<String>> zaokruzeniOdgovori) {
        double poeni = 0;

        RezultatKviza rezultati = new RezultatKviza(this);
        Map<Pitanje, Double> p = new HashMap<>();
        for (Map.Entry<Pitanje, ArrayList<String>> entry : zaokruzeniOdgovori.entrySet()){
            poeni+=entry.getKey().izracunajPoene(entry.getValue(), sistemBodovanja);
            p.put(entry.getKey(), entry.getKey().izracunajPoene(entry.getValue(), sistemBodovanja));
        }
        rezultati.setBodovi(p);
        rezultati.setTotal(poeni);
        for(Map.Entry<Pitanje, ArrayList<String>> entry : pitanja.entrySet()){
            if(!rezultati.getBodovi().containsKey(entry.getKey())) rezultati.getBodovi().put(entry.getKey(), (double) 0);
        }
        return rezultati;
    }

    /*
    @Override
    public String toString() {
        String pom="";
        int br=0;
        for(Map.Entry<Pitanje, ArrayList<String>> entry : pitanja.entrySet()){
            pom += br + 1 + "." + entry.getKey().getTekst() + "?(" + entry.getKey().getBrojPoena() + "b)\n";
            for(Map.Entry<Pitanje, ArrayList<String>> pitanje : pitanja.entrySet()){
                if(pitanje.getKey().getOdgovori().containsKey(entry.getValue()))
            }
        }
        return "Kviz" + naziv + "boduje se" + sistemBodovanja.getIme() +".\n"+
                "Pitanja:\n";
    }*/
}
