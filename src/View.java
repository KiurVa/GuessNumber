import java.util.List;
import java.util.Scanner;

/**
 * Kõik mida kasutaja näeb konsoolis
 */
public class View {
    private final Scanner scanner = new Scanner(System.in);
    private final int MAX_ATTEMPTS = 5;

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
     * küsib kasutajalt numbrit ja kontrollib, et see oleks täisarv ja vahemikus 1-100
     * peab arvestust, mitu mitte täisarvulist sisestust on tehtud
     * @param cheatCode petmise number
     * @return number
     */
    public int askGuess(int cheatCode) {
        int attempts = 0;
        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Sisesta number vahemikus 1-100: ");
            try {
                int guess = Integer.parseInt(scanner.nextLine());
                if ((guess >= 1 && guess <= 100) || guess == cheatCode) {
                    return guess;
                } else {
                    System.out.println("Arv peab olemas vahemikus 1-100.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Sisestus peab olema täisarv.");
            }
            attempts++;
            System.out.println("Jäänud katseid: " + (MAX_ATTEMPTS - attempts));
        }
        System.out.println("Liiga palju vigaseid sisestusi. Mäng on katkestatud.");
        return -1; //Tagastab -1, et mäng katkestada
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
