import java.io.*;
import java.util.*;

/**
 * Kogu mängu loogika asub siin
 */
public class Model {
    private final int MINIMUM = 1; //KONSTANT
    private final int MAXIMUM = 100; //KONSTANT
    private final String filename = "scoreboard.txt";
    private final List<Content> scoreboard = new ArrayList<>(); //Edetabeli faili sisu

    private int pc_number; //Arvuti valitud number
    private int steps;  //Käikude lugeja
    private boolean game_over; //mäng läbi või kestab

    /**
     * Uue mängu loomine
     */
    public void initGame () {
        pc_number = new Random().nextInt(MAXIMUM - MINIMUM + 1) + MINIMUM;
        game_over = false;
        steps = 0;
    }

    /**
     * Kontrollitakse kasutaja sisestust ja tagastab sobiva teksti
     * @param guess number mida kontrollida
     * @return tekst mida näidatakse
     */
    public String checkGuess (int guess) {
        steps++; //Sammude arv kasvab
        if (guess == pc_number) {
            game_over = true;
            return "Sa võitsid " + steps + " sammuga!";
        } else if (guess < pc_number) {
            return "Liiga väike";
        } else {
            return "Liiga suur";
        }
    }

    /**
     * Salvestab listi sisu (edetabel) uuesti faili (kirjutab üle)
     * @param name mängija nimi
     */
    public void saveScore (String name) {
        loadScores();
        scoreboard.add(new Content(name, steps)); //lisa nimi ja sammude arv listi
        Collections.sort(scoreboard); //Sorteerib listi (Content compareTo() omaga)
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            for (Content c : scoreboard) {
                out.println(c.getName() + ";" + c.getSteps());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loeb edetabeli faili sisu ja lisab sisu listi
     * @return edetabeli list
     */
    public List<Content> loadScores() {
        scoreboard.clear(); //Tühjenda list
        File file = new File(filename);
        if(!file.exists()) return scoreboard; //Kui faili ei ole tagastab listi
        try (Scanner sc = new Scanner(file)) {
            while(sc.hasNextLine()) { //Kui failis on järgmine rida
                String[] parts = sc.nextLine().split(";");
                if(parts.length == 2) { //Kui on kaks väärtust reas
                    String name = parts[0];
                    int steps = Integer.parseInt(parts[1]);
                    scoreboard.add(new Content(name, steps)); //Kirjutab tabelisse
                }
            }
            Collections.sort(scoreboard); //Sorteerib listi(Ilmselt on üleliigne)
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return scoreboard;
    }

    //GETTERS

    /**
     * Arvuti "mõeldud" number
     * @return juhuslik number vahemikus 1-100
     */
    public int getPc_number() {
        return pc_number;
    }

    /**
     * mäng läbi või kestab
     * @return true läbi, false kestab
     */
    public boolean isGame_over() {
        return game_over;
    }
}
