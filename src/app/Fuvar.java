package app;

import java.util.Objects;

public class Fuvar {
    private String rsz;
    private int ido;
    private double osszeg;
    private String fizmod;

    public Fuvar(String sor) {
        String[] s = sor.split(";");
        this.rsz = s[0];
        this.ido = Integer.parseInt(s[1]);
        this.osszeg = Double.parseDouble(s[2]);
        this.fizmod = s[3];
    }
    
    public Fuvar(String rsz, int ido, double osszeg, String fizmod) {
        this(rsz+";"+ido+";"+osszeg+";"+fizmod);
    }

    public String getRsz() {
        return rsz;
    }

    public int getIdo() {
        return ido;
    }

    public double getOsszeg() {
        return osszeg;
    }

    public String getFizmod() {
        return fizmod;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.rsz);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fuvar other = (Fuvar) obj;
        return Objects.equals(this.rsz, other.rsz);
    }

    @Override
    public String toString() {
        return "Fuvar{" + "rsz=" + rsz + ", ido=" + ido + ", osszeg=" + osszeg + ", fizmod=" + fizmod + '}';
    }
    
    
}
