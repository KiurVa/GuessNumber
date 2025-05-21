/**
 * Klass mis on mõeldud edetabeli faili sisuga majandamiseks
 */
public class Content implements Comparable<Content> {
    private final String name;    //mängija nimi
    private final int steps;      //Sammude arv

    /**
     * Objekti loomise konstruktor
     * @param name mängija nimi
     * @param steps mängija sammude arv
     */
    public Content(String name, int steps) {
        this.name = name;
        this.steps = steps;
    }

    /**
     * tagatsab mängija nime failist
     * @return mängija nimi
     */
    public String getName() {
        return name;
    }

    /**
     * Tagastab mängija sammmude arvu failist
     * @return sammude arv
     */
    public int getSteps() {
        return steps;
    }

    /**
     * Sorteerimine sammude järgi kahanevalt
     * @param o objekt mida võrrelda
     * @return täisarv
     */
    @Override
    public int compareTo(Content o) {
        return Integer.compare(steps, o.steps); // Kasvavalt .sort() .reversed()
    }

    /**
     * Vormindatud edetabel konsooli näitamiseks
     * @return vormindatud rida
     */
    public String formattedData() {
        String displayName =  name.length() > 15 ? name.substring(0, 15) : String.format("%-15s", name); //kui nimi pikem kui 15 märki, siis võetakse esimesed 15 kui on lühem, siis esimesed 15
        String n = String.format("%-15s", displayName); //- märk teeb tühikuid (ilus vaadata)
        String s = String.format("%-3d", steps);
        return n + s;
    }
}
