import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {
    private Random random;
    private int targetNumber;
    private int shift;

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public int getTargetNumber() {
        return targetNumber;
    }

    public void setTargetNumber(int targetNumber) {
        this.targetNumber = targetNumber;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public GuessTheNumberGame(){
        setRandom(new Random());
        setTargetNumber(random.nextInt(100));
        setShift(1);
    }

    public boolean checkGuess(Player player){
        if(player instanceof HumanPlayer){
            System.out.println("--- Round 1: Player 1 --- " + player.getName());
            System.out.println("Player 1 Enter you guess: ");
            Scanner scanner = new Scanner(System.in);
            int number = scanner.nextInt();
            player.setGuesses(number);
            if(player.makeGuess() > getTargetNumber()){
                System.out.println("too high!");
            }
            else{
                System.out.println("too low!");
            }
            return player.makeGuess() != getTargetNumber();
        }
        else{
            System.out.println("--- Round 2: Computer Player --- ");
            System.out.println("Computer Player Enter you guess: ");
            int number = player.makeGuess();
            System.out.println(number);
            if(player.makeGuess() > getTargetNumber()){
                System.out.println("too high!");
            }
            else{
                System.out.println("too low!");
            }
            return number != getTargetNumber();
        }
    }

    public static void main(String[] args) {
        GuessTheNumberGame game = new GuessTheNumberGame();
        HumanPlayer playerOne;
        playerOne = new HumanPlayer();
        ComputerPlayer computerPlayer = new ComputerPlayer();
        System.out.println(game.getTargetNumber());
        System.out.println("--- Ingresa tu nombre Player 1:");
        Scanner scanner = new Scanner(System.in);
        playerOne.setName(scanner.next());
        boolean validation = true;

        while(validation){
            if(game.getShift()%2==0){
                validation = game.checkGuess(computerPlayer);
                if(!validation){
                    System.out.println("Congratulation "+ computerPlayer.getName() +" You guessed the number.");
                    System.out.print("Attempts: [");
                    for (int i = 0; i < computerPlayer.getGuesses().length; i++) {
                        System.out.print(computerPlayer.getGuesses()[i]);
                        if (i < computerPlayer.getGuesses().length - 1) {
                            System.out.print(", ");
                        }
                    }
                    System.out.println("]");
                    System.out.println("Total Attempts: " + computerPlayer.getGuesses().length);
                }
                else {
                    game.setShift(game.getShift()+1);
                }

            }
            else {
               validation = game.checkGuess(playerOne);
                if(!validation){
                    System.out.println("Congratulation "+ playerOne.getName() +" You guessed the number.");
                    System.out.print("Attempts: [");
                    for (int i = 0; i < playerOne.getGuesses().length; i++) {
                        System.out.print(playerOne.getGuesses()[i]);
                        if (i < playerOne.getGuesses().length - 1) {
                            System.out.print(", ");
                        }
                    }
                    System.out.println("]");
                    System.out.println("Total Attempts: " + playerOne.getGuesses().length);
                }
                else {
                    game.setShift(game.getShift()+1);
                }
            }
        }

    }
}
