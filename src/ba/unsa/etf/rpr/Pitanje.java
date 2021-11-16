package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pitanje {
    private String tekst;
    private double brojPoena;
    private Map<String, Odgovor> odgovori;

    public Pitanje(String tekst, double brojPoena) {
        this.tekst = tekst;
        this.brojPoena = brojPoena;
        odgovori = new HashMap<>();
    }

    public void dodajOdgovor(String id, String zuta, boolean b) {
        (this.odgovori).put(id, new Odgovor(zuta, b));
    }

    public void obrisiOdgovor(String id) throws IllegalArgumentException {
        if(!(odgovori.containsKey(id))) throw new IllegalArgumentException("Id odgovora mora biti jedinstven");
        odgovori.remove(id);
    }

    public List<Odgovor> dajListuOdgovora(){
        return new ArrayList<Odgovor>(odgovori.values());
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public double getBrojPoena() {
        return brojPoena;
    }

    public void setBrojPoena(double brojPoena) {
        this.brojPoena = brojPoena;
    }

    public Map<String, Odgovor> getOdgovori() {
        return odgovori;
    }

    public void setOdgovori(Map<String, Odgovor> odgovori) {
        this.odgovori = odgovori;
    }

    @Override
    public String toString() {
        String pom="";
        for(Map.Entry<String, Odgovor> entry : odgovori.entrySet()){
            pom+= entry.getKey() + ":" + entry.getValue().getTekstOdgovora()+"\n";
        }
        return "" + tekst + "("+brojPoena+")\n"+ pom;
    }

    public double izracunajPoene(ArrayList<String> id_odgovora){
        int br=0;
        double poeni=0;

    }

}
