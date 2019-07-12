import java.util.Scanner;

public class TTTCompHum {

    public static void main(String[] args) {

        // Ask human to enter his input
        // Computer to analyze and insert its next move
        Scanner sc = new Scanner(System.in);
        System.out.println(" -------------- Hey, Welcome to Legendary Tic-Tac-Toe game ------------ ");
        System.out.print("Before beginning, Tell me your name: ");
        String you = sc.next();
        System.out.println("That's a good name " + you.toUpperCase());
        System.out.println("By the way, I am a bot going to play with you.... ");
        System.out.println(you + " will be playing as X and I'll be playing as O");
        System.out.println("Let's play " + you);
        TicTacToeCompHum ticTacToe = new TicTacToeCompHum();
        do {


            if (ticTacToe.getCurrentPlayer().equals("Human")) {
                String val;
                do {
                    System.out.println(you + ", please enter a desired location in row[A-C] and column[1-3] like A1/B2: ");
                    val = sc.next();
                } while (!ticTacToe.splitAndPlaceMark(val));
                ticTacToe.changePlayer();
            }
            else{
                ticTacToe.computerGame();
                ticTacToe.changePlayer();
            }
        } while (!ticTacToe.checkForWin() && !ticTacToe.isBoardFull());
        if(ticTacToe.isBoardFull() && !ticTacToe.checkForWin()){
            System.out.println("The game was a tie");
        }
        else{
            System.out.println("Current board layout:");
            ticTacToe.printBoard();
            ticTacToe.changePlayer();
            if(ticTacToe.getCurrentPlayer().equals("Human")){
                System.out.println(you + "Win, Congratulations");
            }
            if(ticTacToe.getCurrentPlayer().equals("Computer")){
                System.out.println("I win, Better luck next time");

            }
        }


    }
}
