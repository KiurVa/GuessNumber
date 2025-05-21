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
        System.out.print("4. Tee valik: ");
    }

    /**
     * Tagastab kasutaja menüüs tehtud sisestuse
     * @return kasutaja sisestus
     */
    public int getMenuChoice () {
        return Integer.parseInt(scanner.nextLine());
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
     * @return kasutaja sisestatud number
     */
    public int askGuess() {
        System.out.print("Sisesta number: ");
        return Integer.parseInt(scanner.nextLine());
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
}
