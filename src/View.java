import java.util.List;
import java.util.Scanner;

/**
 * Kõik mida kasutaja näeb konsoolis
 */
public class View {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Mängu menüü näitamine
     */
    public void showMenu() {
        System.out.println("1. Mängima");
        System.out.println("2. Edetabel");
        System.out.println("3. Välju");
        System.out.print("Tee valik: ");
    }

    /**
     * Tagastab kasutaja menüüs tehtud sisestuse
     * @return kasutaja sisestus
     */
    public String getMenuChoice () {
        return scanner.nextLine();
    }

    /**
     * Väljastab ette antud teate konsooli
     * @param message teade mida väljastada
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * küsib kasutajalt numbrit
     * @return sisestus
     */
    public String askGuess() {
        System.out.println("Sisesta number 1-100: ");
        return scanner.nextLine();
    }

    /**
     * Küsib kasutajalt mängija nime
     * @return sisestatud nimi
     */
    public String askName() {
        System.out.print("Sisesta nimi: ");
        return scanner.nextLine();
    }

    /**
     * Näitab konsoolis edetabelit
     * @param scores faili sisu listina
     */
    public void showScoreboard(List<Content> scores) {
        System.out.println("EDETABEL");
        System.out.println("--------");
        for(Content sc : scores) {
            System.out.println(sc.formattedData());
        }
        System.out.println(); //Tühi rida
    }

    public void showRemainingAttempts(int remainingAttempts) {
        System.out.println("Jäänud katseid: " + remainingAttempts);
    }
}
