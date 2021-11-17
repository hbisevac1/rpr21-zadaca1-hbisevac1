package ba.unsa.etf.rpr;

import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void igrajKviz(Kviz kviz){
        List<Pitanje> listaPitanja = kviz.getPitanja();

        Map<Pitanje, ArrayList<String>> mojiOdgovori = new HashMap<>();

        System.out.println("Pitanja glase:\n");

        for(int i=0; i<listaPitanja.size(); i++){
            System.out.println(""+listaPitanja.get(i).toString());
            System.out.println("Unesite Vas odgovor: ");
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
            mojiOdgovori.put(listaPitanja.get(i), odgovor);
        }
        RezultatKviza rezultat = kviz.predajKviz(mojiOdgovori);
        System.out.println(""+rezultat.toString());
        System.out.println(""+kviz.toString());

    }
    public static void main(String[] args) {
        Kviz kviz = new Kviz("Filmski kviz", SistemBodovanja.PARCIJALNO);
        List<Pitanje> listaPitanja= new ArrayList<>();
        Pitanje pitanje1 = new Pitanje("Glavni lik u crtanom filmu \"Lion King\" je: ", 2.0);
        pitanje1.dodajOdgovor("a", "Mufasa", false);
        pitanje1.dodajOdgovor("b", "Nala", false);
        pitanje1.dodajOdgovor("c", "Simba", true);
        Pitanje pitanje2 = new Pitanje("Dobitnik Oscara za film \"Nicija zemlja\" je:", 4.0);
        pitanje2.dodajOdgovor("a", "Jasmina Zbanic", false);
        pitanje2.dodajOdgovor("b", "Danis Tanovic", true);
        pitanje2.dodajOdgovor("c", "Ales Kurt", false);
        Pitanje pitanje3 = new Pitanje("Koji glumci su dobitnici Oscara?", 5);
        pitanje3.dodajOdgovor("a", "Leonardo DiCaprio", true);
        pitanje3.dodajOdgovor("b", "Joaquin Phoenix", true);
        pitanje3.dodajOdgovor("c", "Robert Downey Jr", false);
        pitanje3.dodajOdgovor("d", "Tom Hanks", true);
        kviz.dodajPitanje(pitanje1);
        kviz.dodajPitanje(pitanje2);
        kviz.dodajPitanje(pitanje3);
        System.out.println("Dobrodosli na kviz \"" + kviz.getNaziv() + "\".\n");
        igrajKviz(kviz);
        //System.out.println("Ugodno rješavanje zadaće 1 :)");
    }
}
