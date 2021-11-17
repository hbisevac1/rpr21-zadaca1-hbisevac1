package ba.unsa.etf.rpr;

import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void igrajKviz(Kviz kviz){
        List<Pitanje> listaPitanja = kviz.getPitanja();

        Map<Pitanje, ArrayList<String>> mojiOdgovori = new HashMap<>();

        System.out.println("Pitanja glase:\n");
        double ukupno=0;
        for(int i=0; i<listaPitanja.size(); i++){
            System.out.println(""+listaPitanja.get(i).toString());
            System.out.println("Unesite Vas odgovor (u formatu a, b, c itd.): ");
            String odg="";
            Scanner ulaz = new Scanner(System.in);
            odg  = ulaz.nextLine();
            ArrayList<String> odgovor = new ArrayList<String>();
            if(odg.isBlank() || odg.isEmpty()) odgovor.add(null);
            else {
                String[] razdvojeniOdgovori = odg.split(", ");
                for (String o : razdvojeniOdgovori) {
                    odgovor.add(o);
                }
            }
            try{
                double poeni = listaPitanja.get(i).izracunajPoene(odgovor, kviz.getSistemBodovanja());
            }catch(IllegalArgumentException e){
                System.out.println("Izuzetak: " + e.getMessage());
                System.out.println("Izaberite odgovor ponovno.");
                i--;
                continue;
            }
            mojiOdgovori.put(listaPitanja.get(i), odgovor);
            ukupno+=listaPitanja.get(i).getBrojPoena();
        }
        RezultatKviza rezultat = kviz.predajKviz(mojiOdgovori);
        System.out.println(""+rezultat.toString());
        System.out.println("Zelite li pogledati tacne odgovore po pitanjima (d za da, n za ne)?");
        String izbor="";
        Scanner ulaz = new Scanner(System.in);
        izbor = ulaz.nextLine();
        if(izbor.equalsIgnoreCase("d")) System.out.println(""+kviz.toString());
        double ostvareni = rezultat.getTotal();
        System.out.println("Vas rezultat na ovom kvizu je: "+ostvareni + " od mogucih " + ukupno);
        if(ostvareni == ukupno) System.out.println("MAJSTOR! MAY THE FORCE BE WITH YOU!");
        else if(ostvareni >= ukupno*0.9) System.out.println("We will watch your career with great interest.");
        else if(ostvareni >= ukupno*0.5 && ostvareni<=ukupno*0.8) System.out.println("Almost there... RUN FORREST, RUN!");
        else System.out.println("Muggle");


    }
    public static void main(String[] args) {
        Kviz kviz = new Kviz("Filmski kviz", SistemBodovanja.PARCIJALNO);
        Pitanje pitanje1 = new Pitanje("Glavni lik u crtanom filmu \"Lion King\" je: ", 2.0);
        pitanje1.dodajOdgovor("a", "Mufasa", false);
        pitanje1.dodajOdgovor("b", "Nala", false);
        pitanje1.dodajOdgovor("c", "Simba", true);
        Pitanje pitanje2 = new Pitanje("Koji je najbolji film svih vremena?", 4.0);
        pitanje2.dodajOdgovor("a", "Green Mile", true);
        pitanje2.dodajOdgovor("b", "Forrest Gump", true);
        pitanje2.dodajOdgovor("c", "Tall girl", false);
        Pitanje pitanje3 = new Pitanje("Koji filmovi imaju najmanje 3 dijela?", 5);
        pitanje3.dodajOdgovor("a", "Star Wars", true);
        pitanje3.dodajOdgovor("b", "Harry Potter", true);
        pitanje3.dodajOdgovor("c", "Fantastic Beasts and where to find them", false);
        pitanje3.dodajOdgovor("d", "Spiderman", true);
        Pitanje pitanje4 = new Pitanje("Spoiler alert! Ko je bio otac Luke Skywalker-a?", 10);
        pitanje4.dodajOdgovor("a", "Han Solo", false);
        pitanje4.dodajOdgovor("b", "Obi-Wan Kenobi", false);
        pitanje4.dodajOdgovor("c", "Darth Vader", true);
        pitanje4.dodajOdgovor("d", "Yoda", false);
        Pitanje pitanje5 = new Pitanje("Ko je bio najbolji prijatelj Han Sol-a?", 6);
        pitanje5.dodajOdgovor("a", "Hana Bisevac",true);
        pitanje5.dodajOdgovor("b", "Darth Vader", false);
        pitanje5.dodajOdgovor("c", "Chewbacca", true);

        kviz.dodajPitanje(pitanje1);
        kviz.dodajPitanje(pitanje2);
        kviz.dodajPitanje(pitanje3);
        kviz.dodajPitanje(pitanje4);
        kviz.dodajPitanje(pitanje5);
        Pitanje pitanje6 = new Pitanje("Ko je bio najbolji prijatelj Han Sol-a?", 6);
        try{
            pitanje5.dodajOdgovor("a", "Hana Bisevac", false);
            pitanje5.dodajOdgovor("b", "Darth Vader", false);
            pitanje5.dodajOdgovor("c", "Chewbacca", true);
        }catch(IllegalArgumentException e){
            System.out.println(""+e.getMessage());
        }
        try{
            kviz.dodajPitanje(pitanje6);
        }
        catch(IllegalArgumentException e){
            System.out.println("Izuzetak " + e.getMessage());
        }
        System.out.println("Dobrodosli na kviz \"" + kviz.getNaziv() + "\".\n");
        igrajKviz(kviz);
        //System.out.println("Ugodno rješavanje zadaće 1 :)");
    }
}
