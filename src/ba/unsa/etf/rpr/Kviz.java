package ba.unsa.etf.rpr;

import java.util.*;



enum SistemBodovanja {BINARNO("binarno"), PARCIJALNO("parcijalno"), PARCIJALNO_SA_NEGATIVNIM("parcijalno sa negativnim bodovima");
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
    private List<Pitanje> pitanja;
    private SistemBodovanja sistemBodovanja;

    public Object getNaziv() {
        return naziv;
    }

    public Kviz(String naziv, SistemBodovanja sistem) {
        this.naziv=naziv;
        this.pitanja = new ArrayList<>();
        this.sistemBodovanja = sistem;
    }


    public SistemBodovanja getSistemBodovanja() {
        return sistemBodovanja;
    }

    public void setSistemBodovanja(SistemBodovanja sistemBodovanja) {
        this.sistemBodovanja = sistemBodovanja;
    }

    void dodajPitanje(Pitanje pitanje) throws IllegalArgumentException {
        for(int i = 0; i < pitanja.size(); i++){
            if(pitanja.get(i).getTekst().equalsIgnoreCase(pitanje.getTekst())) throw new IllegalArgumentException("Ne možete dodati pitanje sa tekstom koji već postoji");
        }
        pitanja.add(pitanje);

    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Pitanje> getPitanja() {
        return pitanja;
    }

    public void setPitanja(List<Pitanje> pitanja) {
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
        for(int i = 0; i < pitanja.size(); i++){
            if(!rezultati.getBodovi().containsKey(pitanja.get(i))) rezultati.getBodovi().put(pitanja.get(i), (double) 0);
        }
        return rezultati;
    }


    @Override
    public String toString() {
        String pom="";
        for(int i = 0; i < pitanja.size(); i++){
            int broj=i+1;
            pom += "\n" + broj + ". " + pitanja.get(i).getTekst() + "(" + pitanja.get(i).getBrojPoena() + "b)";
            Map <String, Odgovor> odgovori = pitanja.get(i).getOdgovori();
            for(Map.Entry<String, Odgovor> entry1 : odgovori.entrySet()){
                pom+= "\n\t" + entry1.getKey() + ": " + entry1.getValue().getTekstOdgovora();
                if(entry1.getValue().isTacno()) pom+="(T)";
            }
            if(i!=pitanja.size()-1) pom+="\n";
        }
        return "Kviz \"" + naziv + "\" boduje se " + sistemBodovanja.getIme() +". Pitanja:" +pom;
    }
}
