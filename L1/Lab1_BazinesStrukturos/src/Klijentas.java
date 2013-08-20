/**
 * Tai yra demonstracinė klasė, skirta objektų kūrimui parodyti.
 */
class Klijentas{
    private String kodas;
    private int amžius;
    private double indėlis;
// -----------------------------------------------------------------------------
    /**
     * Konstruktorius sukuria objektą su nurodytomis savybėmis
     */
    public Klijentas(String kodas, int amžius, double indėlis) {
        this.kodas = kodas;
        this.amžius = amžius;
        this.indėlis = indėlis;
    }
// -----------------------------------------------------------------------------
    /**
     * Kliento kodą galima tik sužinoti, bet keisti yra negalima.
     * @return the kodas
     */
    public String getKodas() {
        return kodas;
    }
// -----------------------------------------------------------------------------
    /**
     * Amžių galima tik sužinoti, bet keisti yra negalima.
     * @return the amžius
     */
    public int getAmžius() {
        return amžius;
    }
// -----------------------------------------------------------------------------
    /**
     * Indėlį galima sužinoti bei daryti pokyčius
     * @return the indėlis
     */
    public double getIndėlis() {
        return indėlis;
    }
// -----------------------------------------------------------------------------
    /**
     * Tai pagrindinė operacija su klijento duomenimis.
     * Indėlį galima tik padidinti ar sumažinti nurodytu pokyčiu,
     * todėl metodo set() nėra.
     */
    public void keistiIndėlį(double pokytis){
        indėlis += pokytis;
    }
// -----------------------------------------------------------------------------
    /**
     * Perrašomas Object metodas to String, skirtas savybėms pavaizduoti.
     * @return pilnas klijento aprašas
     */
    @Override
    public String toString() {  // pirmas parametras - vaizdavimo formatas
        return String.format("%-7s %3d %9.2f",
                kodas, amžius, indėlis);
    }
// -----------------------------------------------------------------------------
}