import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Klass mis on mõeldud edetabeli faili sisuga majandamiseks
 */
public class Content implements Comparable<Content> {
    private final String name;          //mängija nimi
    private final int steps;            //Sammude arv
    private final String dateTime;      //Mängu kuupäev ja kellaeg
    private final long gameTimeMillis;  //Mängu aeg millisekundites

    /**
     * Objekti loomise konstruktor
     * @param name mängija nimi
     * @param steps mängija sammude arv
     * @param dateTime Mängimise kuupäev ja kellaeg
     * @param gameTimeMillis Mängu kestvus millisekundites
     */
    public Content(String name, int steps, String dateTime, long gameTimeMillis) {
        this.name = name;
        this.steps = steps;
        this.dateTime = dateTime;
        this.gameTimeMillis = gameTimeMillis;
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
     * Tagastab mängu kuupäeva ja kellaaja
     * @return kuupäev ja kellaeg
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Tagastab mängu kestvuse millisekundites
     * @return kestvus millisekundites
     */
    public long getGameTimeMillis() {
        return gameTimeMillis;
    }

    /**
     * Sorteerimine sammude ja kuupäeva järgi kahanevalt
     * @param o objekt mida võrrelda
     * @return täisarv
     */
    @Override
    public int compareTo(Content o) {
        int stepsCompare = Integer.compare(steps, o.steps); // Kasvavalt .sort() .reversed()
        if (stepsCompare != 0) return stepsCompare; //Kui sammude arvu erinevus ei ole 0, siis tagastatakse väärtus
        return dateTime.compareTo(o.dateTime); //Kui sammude arvu erinvus on 0 sorteeritakse kuupäeva järgi
    }

    /**
     * Vormindatud edetabel konsooli näitamiseks
     * @return vormindatud rida
     */
    public String formattedData() {
        String displayName =  name.length() > 15 ? name.substring(0, 15) : String.format("%-15s", name); //kui nimi pikem kui 15 märki, siis võetakse esimesed 15 kui on lühem, siis esimesed 15
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  //kuupäeva kuju nagu on ta failis
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"); //kuupäeva kuju nagu soovime näidata
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, inputFormatter);            // Kuupäeva kuju muutmiseks muudame ta LocalDateTimeks
        String displayDatetime = localDateTime.format(outputFormatter);                         // Vormindame ta soovitud kuupäeva kujuks ja salvestame Stringina
        String n = String.format("%-15s", displayName); //- märk teeb tühikuid (ilus vaadata)
        String s = String.format("%-3d", steps);
        String d = String.format("%-20s", displayDatetime);
        String m = String.format("%-4d", gameTimeMillis);
        return d + n + s + m;
    }
}
