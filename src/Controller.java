/**
 * Käivitab mängu
 */
public class Controller {
    private final Model model;
    private final View view;
    private final int MAX_ATTEMPTS = 5;

    /**
     * Kontrolleri konstruktor
     *
     * @param model App failis loodud mudel
     * @param view  App failis loodud view
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Käivitab mängu loogika
     */
    public void start() {
        boolean running = true;
        while (running) {
            view.showMenu();
            try {
                int choice = Integer.parseInt(view.getMenuChoice());
                switch (choice) {
                    case 1:
                        playGame(); //mängu loomine ja käivitamine
                        break;
                    case 2:
                        view.showScoreboard(model.loadScores()); //Näitab edetabelit ja saab kaasa listi
                        break;
                    case 3:
                        running = false;
                        view.showMessage("Head aega!");
                        break;
                    default:
                        view.showMessage("Vigane valik. Sisesta 1, 2 või 3.");
                }
            } catch (NumberFormatException e) {
                view.showMessage("Vigane sisestus. Sisesta number 1, 2 või 3.");
            }
        }
    }

    /**
     * Mängu loogika
     */
    private void playGame() {
        model.initGame(); //Loome mängu
        Stopwatch stopwatch = new Stopwatch(); //Loome stopperi
        stopwatch.start(); //Käivitame stopperi
        boolean gameAborted = false; //mängu katkestamise muutuja
        //System.out.println(model.getPc_number());
        //view.showMessage(String.valueOf(model.getPc_number())); //Näitab arvuti valitud numbrit
        while (!model.isGame_over()) { //Kui mäng pole läbi
            int quess = getValidGuess();
            if (quess == -1) { //Kui tagastati -1 vigaste sisestuste tõttu
                gameAborted = true;
                break;
            } else {
                view.showMessage(model.checkGuess(quess)); //kontroll ja väljasta tulemus
            }
        }
        stopwatch.stop(); //peatab stopperi
        long gameTimeMillis = stopwatch.getElapsedMillis(); //Teeb muutuja millisekundite jaoks
        view.showMessage("Mängu aeg: " + stopwatch.getElapsedTime() + " (" + gameTimeMillis + ")");
        if (!model.isCheater() && !gameAborted) {           //Kui ei ole petja ja mäng pole katkestatud rakendub if
            String name = view.askName();   //Küsib nime
            model.saveScore(name, gameTimeMillis); //Salvestab nime ja mängu aja millisekundites
        }
    }

    /**
     * Sisestuse kontroll, et oleks vahemikus 1-100 ja oleks täisarv
     * peab arvestust valesti sisestuste üle
     * @return sobiv täisarv või -1 kui mäng katkestati
     */
    private int getValidGuess() {
        int attempts = 0;
        while (attempts < MAX_ATTEMPTS) {
            try {
                String input = view.askGuess();
                int guess = Integer.parseInt(input);

                if (isValidGuess(guess)) {
                    return guess;
                } else {
                    view.showMessage("Arv peab olema vahemikus 1-100.");
                }
            } catch (NumberFormatException e) {
                view.showMessage("Sisestus peab olema täisarv.");
            }
            attempts++;
            if (attempts < MAX_ATTEMPTS) {
                view.showRemainingAttempts(MAX_ATTEMPTS - attempts);
            }
        }
        view.showMessage("Liiga palju vigaseid sisestusi. Mäng on katkestatud.");
        return -1;
    }

    /**
     * Konntrollib, kas arvamine on õiges vahemikus või 1000
     * @param guess kasutaja sisestus
     * @return true kui õige ja false kui mitte
     */
    private boolean isValidGuess(int guess) {
        return (guess >= 1 && guess <= 100) || guess == model.getCheatCode();
    }
}
