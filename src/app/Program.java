package app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Program {
    private static List<String> sorok;
    private static List<Fuvar> fuvarok;
    
    static void ini() throws IOException{
        sorok = Files.readAllLines(Path.of("fuvarok.csv"));
        fuvarok = new ArrayList<>();
        for (String sor : sorok) {
            fuvarok.add(new Fuvar(sor));
        }
    }
    
    public static void main(String[] args) throws IOException {
       ini();
       osszesFuvarErteke();
       legdragabbFuvarRendszama();
       legolcsobbFuvarForintban();
       kartyasFizetesekSzama();
       mindenFizmodMeghatarozott();
       autokSzama();
       fizetesiModokSzama();
       fuvarokSzamaAutokra();
    }//main

    private static double osszesFuvarErteke() throws IOException {
        ini();
        double osszErtek = 0;
        for (Fuvar fuvar : fuvarok) {
            osszErtek += fuvar.getOsszeg();
        }
        return osszErtek;
    }

    private static String legdragabbFuvarRendszama() throws IOException {
        ini();
        String legdragabbRendszam = fuvarok.get(0).getRsz();
        double legNagyobbOsszeg = fuvarok.get(0).getOsszeg();
        for (Fuvar fuvar : fuvarok) {
            if (fuvar.getOsszeg()>legNagyobbOsszeg) {
                legdragabbRendszam = fuvar.getRsz();
            }
        }
        return legdragabbRendszam;
    }

    private static double legolcsobbFuvarForintban()  {
        double legOlcsobbEuroban = fuvarok.get(0).getOsszeg();

        for (Fuvar fuvar : fuvarok) {
            if (fuvar.getOsszeg()<legOlcsobbEuroban) {
                legOlcsobbEuroban = fuvar.getOsszeg();
            }
        }
        return legOlcsobbEuroban*360;
    }

    private static int kartyasFizetesekSzama() {
        int kartyasFizetesSzamlalo = 0;
        
        for (Fuvar fuvar : fuvarok) {
            if (fuvar.getFizmod().equals("kártya")) {
                kartyasFizetesSzamlalo++;
            }
        }
        return kartyasFizetesSzamlalo;
    }

    private static boolean mindenFizmodMeghatarozott() {
        int i = 0;
        while (i<fuvarok.size() && !(fuvarok.get(i).getFizmod().equals("-"))) {
            i++;
        }
        if (i>=fuvarok.size()) {
            return true;
        }
        return false;
    }

    private static int autokSzama() {
        HashSet<Fuvar> f = new HashSet<>();
        for (Fuvar fuvar : fuvarok) {
            f.add(fuvar);
        }
        return f.size();
    }

    private static int fizetesiModokSzama() {
        HashSet<String> f = new HashSet<>();
        for (Fuvar fuvar : fuvarok) {
            f.add(fuvar.getFizmod());
        }
        return f.size();
    }

    private static void fuvarokSzamaAutokra() {
        HashSet<String> f = new HashSet<>();
        for (Fuvar fuvar : fuvarok) {
            f.add(fuvar.getRsz());
        }
        
        for (String rsz : f) {
            int szamlalo = 0;
            System.out.println("");
            System.out.print(rsz+" Rendszámú autóval történt fuvarok száma: ");
            for (Fuvar fuvar : fuvarok) {
                if (fuvar.getRsz().equals(rsz)) {
                    szamlalo++;
                }
            }
            System.out.println(szamlalo);
        }
         
    }
    
    
}//class
