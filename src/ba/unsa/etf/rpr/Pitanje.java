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

    public void dodajOdgovor(String id, String zuta, boolean b) throws IllegalArgumentException {
        for(Map.Entry<String, Odgovor> entry : odgovori.entrySet()){
            if(id.equals(entry.getKey())) throw new IllegalArgumentException("Id odgovora mora biti jedinstven");
        }
        (this.odgovori).put(id, new Odgovor(zuta, b));
    }

    public void obrisiOdgovor(String id) throws IllegalArgumentException {
        if(!(odgovori.containsKey(id))) throw new IllegalArgumentException("Odgovor s ovim id-em ne postoji");
        odgovori.remove(id);
    }

    public ArrayList<Odgovor> dajListuOdgovora(){
        return new ArrayList<>(odgovori.values());
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
            pom += "\n\t" + entry.getKey() + ": " + entry.getValue().getTekstOdgovora();
        }
        return "" + tekst + "("+brojPoena+"b)"+ pom;
    }

    public double izracunajPoene(List<String> id_odgovora, SistemBodovanja bodovanjae){
        //izuzeci
        for(int i=0; i<id_odgovora.size(); i++){
            if(!odgovori.containsKey(id_odgovora.get(i))) throw new IllegalArgumentException("Odabran je nepostojeći odgovor");
            for(int j=i+1; j < id_odgovora.size(); j++){
                if(id_odgovora.get(i).equals(id_odgovora.get(j))) throw new IllegalArgumentException("Postoje duplikati među odabranim odgovorima");
            }
        }
    //racunanje tacnih odgovora
        int broj_tacnih=0;
        ArrayList<String> tacni = new ArrayList<>();
        ArrayList<String> netacni = new ArrayList<>();
        for(Map.Entry<String, Odgovor> entry : odgovori.entrySet()){
            if(entry.getValue().isTacno()) {
                broj_tacnih++;
                tacni.add(entry.getKey());
            }
            else netacni.add(entry.getKey());
        }
        double poeni = 0;
        int brojac_tacnih = 0;
        int brojac_netacnih = 0;
        for(int i = 0; i < tacni.size(); i++){
            if(id_odgovora.contains(tacni.get(i))) brojac_tacnih++;
        }
        for(int i = 0; i < netacni.size(); i++){
            if(id_odgovora.contains(netacni.get(i))) brojac_netacnih++;
        }
        if(brojac_tacnih==broj_tacnih && id_odgovora.size() == broj_tacnih) return getBrojPoena();

    //BINARNO
        //sve tacno -> svi poeni
        //barem jedan netacan -> 0 poena
        //nisu svi tacni zaokurzeni -> 0 poena
        if(bodovanjae.equals(SistemBodovanja.BINARNO)) poeni=0;
    //PARCIJALNO
            //sve tacno -> svi poeni
            //jedan netacan -> 0 poena
            //nisu svi tacni zaokruzeni -> (ukupno/broj odg * broj tacnih)
        else if(bodovanjae.equals(SistemBodovanja.PARCIJALNO)){
            if(brojac_netacnih > 0) poeni = 0;
            else if(broj_tacnih > id_odgovora.size()) poeni = (getBrojPoena()/ odgovori.size())*id_odgovora.size();
        }

    //NEGATIVNI
        //sve tacno ->svi poeni
        //jedan netacan -> -poeni (polovina od ukupnog broja bodova)
        //nisu svi tacni zaokruzeni -> (ukupno/broj_odg*brojtacnih)
        else if(bodovanjae.equals(SistemBodovanja.PARCIJALNO_SA_NEGATIVNIM)){
            if(brojac_netacnih > 0) poeni-=brojac_netacnih*(getBrojPoena()/2);
            else if(broj_tacnih > id_odgovora.size()) poeni = (getBrojPoena()/ odgovori.size())*id_odgovora.size();
        }
        return poeni;
    }

}
