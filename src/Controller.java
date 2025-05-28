/**
 * Käivitab mängu
 */
public class Controller {
    private final Model model;
    private final View view;

    /**
     * Kontrolleri konstruktor
     * @param model App failis loodud mudel
     * @param view App failis loodud view
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
            int choice = view.getMenuChoice();
            switch (choice) {
                case 1:
                    model.initGame(); //Loob uue mängu
                    Stopwatch stopwatch = new Stopwatch(); //Loome stopperi
                    stopwatch.start(); //Käivitame stopperi
                    //System.out.println(model.getPc_number());
                    //view.showMessage(String.valueOf(model.getPc_number())); //Näitab arvuti valitud numbrit
                    while (!model.isGame_over()) { //Kui mäng pole läbi
                        int quess = view.askGuess(); //Küsib kasutajalt numbrit
                        view.showMessage(model.checkGuess(quess)); //kontroll ja väljasta tulemus
                    }
                    stopwatch.stop(); //peatab stopperi
                    long gameTimeMillis = stopwatch.getElapsedMillis(); //Teeb muutuja millisekundite jaoks
                    view.showMessage("Mängu aeg: " + stopwatch.getElapsedTime() + " (" +gameTimeMillis + ")");
                    if (!model.isCheater()) {           //Kui ei ole petja rakendub if
                        String name = view.askName();   //Küsib nime
                        model.saveScore(name, gameTimeMillis); //Salvestab nime ja mängu aja millisekundites
                    }
                    break;
                case 2:
                    view.showScoreboard(model.loadScores()); //Näitab edetabelit ja saab kaasa listi
                    break;
                case 3:
                    running = false;
                    view.showMessage("Head aega!");
                    break;
                default:
                    view.showMessage("Vigane valik.");
            }
        }
    }
}
