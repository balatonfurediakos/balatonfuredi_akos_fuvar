package teszt;

import app.Fuvar;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProgramTeszt {
    
    private static List<String> sorok;
    private static List<Fuvar> fuvarok;
    
    public static void main(String[] args) throws IOException {
        tesztesetek();
    }

    /* valójábaN NEM HÍVOM, DE LEHETNE */
//    public ProgramTeszt() throws IOException {
//        ini();
//    }

    static void ini() throws IOException{
        sorok = Files.readAllLines(Path.of("fuvarok.csv"));
        fuvarok = new ArrayList<>();
        for (String sor : sorok) {
            fuvarok.add(new Fuvar(sor));
        }
    }
    
    private static void tesztesetek() throws IOException {
        tesztFajlMegvan();
        tesztSorokSzamPont100();
        tesztSoronkent_4_adat();
        tesztRendszam3betu_3szamFormatumu();
        tesztVanLegalabb20AzonosRendszam();
        tesztVanNemMeghatarozottFizMod();
        tesztMindenFizetesiModbolVanLegalabb1();
                
        /* HIÁNYZÓ tesztek:
         * tesztIdoEsKoltsegKapcsolataFix();
         * további elemek értéke/formátuma
        */
    }

    private static void tesztFajlMegvan() throws IOException {
        ini();
        //Files.readAllLines(Path.of("fuvarok.csv"));
        /* assert ide is kellene: NoSuchFileException --> de kivételt még nem tesztelünk */
    }

    private static void tesztSorokSzamPont100() throws IOException {
        ini();
        /*     állítás             : false esetén kiírja */
        assert sorok.size() == 100 : "HIBA: nem 100 soros a fájl";
        
        /* ha az assert true, akkor végrehajtja: */
        System.out.println("sorok száma 100: OK");
    }

    private static void tesztSoronkent_4_adat() throws IOException {
        ini();
        boolean jo = true;
        int i = 0, N = sorok.size();
        while(i<N && jo){
            String[] s = sorok.get(i).split(";");
            jo = s.length == 4;
            i++;
        }
        assert jo : "Nem 4 adat van az egyik sorban (vagy nincs ;)!";
        System.out.println("Soronként 4 adat: OK");
    }

    /* ez tipikus reges --> reguláris kifejezéssel lenne korrekt */
    private static void tesztRendszam3betu_3szamFormatumu() throws IOException {
        ini();
        for (String sor : sorok) {
            String[] elemek = sor.split(";");
            String[] s = elemek[0].split("-");
            assert s.length == 2 : "nincs, vagy több - van a rendszámban";
            String betuk = s[0];
            assert betuk.length() == 3 : "nem 3 betűs a rendszám";
            /* assert hogy csak betűket tartalmaz e */
            String sSzam = s[1];
            /* assert kellene NumberFormatException kivétrelre is */
            assert sSzam.length() == 3 : "nem 3 számból áll a rendszám";
            assert Integer.parseInt(sSzam) >= 0 : "nem csak számokból áll a rendszám";
        }
        System.out.println("Rendszám forma --> 3betű-3szám: OK");
    }
    
    private static void tesztVanLegalabb20AzonosRendszam() throws IOException {
        int minElteres = 20;
        System.err.printf("A TESZT %d eltérő rendszámot néz!!!\n", minElteres);
        ini();
        Set<Fuvar> f = new HashSet<>(fuvarok);
        assert fuvarok.size()-f.size() >= minElteres : "kevés az eltérő rendszám";
        System.out.println(">= 20 azonos rendszám: OK");
    }

    private static void tesztVanNemMeghatarozottFizMod() throws IOException {
        ini();
        /*1. eldöntés tétele */
        int i = 0;
        while(i<fuvarok.size() && !(fuvarok.get(i).getFizmod().equals("-"))){
            i++;
        }
        assert i<fuvarok.size() : "nincs - a fiz. módban";
        
        /*2. halmazzal */
        Set<String> fizModok = new HashSet<>();
        for (Fuvar fuvar : fuvarok) {
            fizModok.add(fuvar.getFizmod());
        }
        assert fizModok.contains("-") : "nincs - a fiz. módban";
        
        System.out.println("Van - a fiz módban: OK");
    }

    private static void tesztMindenFizetesiModbolVanLegalabb1() {
        Set<String> fizModok = new HashSet<>();
        for (Fuvar fuvar : fuvarok) {
            fizModok.add(fuvar.getFizmod());
        }
        /* az 5. a -, de azt már ellenőriztük! */
        assert fizModok.size() == 5 : "fizetési mód száma != 5";
        assert fizModok.contains("kártya") : "nincs \"kártya\" fizetési mód";
        assert fizModok.contains("kártya") : "nincs \"kártya\" fizetési mód";
        assert fizModok.contains("készpénz") : "nincs \"készpénz\" fizetési mód";
        assert fizModok.contains("utalás") : "nincs \"utalás\" fizetési mód";
        assert fizModok.contains("csekk") : "nincs \"csekk\" fizetési mód";
        
        System.out.println("Minden fiz. mód db >= 1: OK");
    }
}
