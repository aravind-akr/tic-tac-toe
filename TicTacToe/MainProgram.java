import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        TicTacToe ticTacToe=new TicTacToe();
        ticTacToe.initializeBoard();
        System.out.println("Tic Tac Toe!!!");
        do{
            System.out.println("Current board layout:");
            ticTacToe.printBoard();
            int row;
            int col;
            do{
                System.out.println("Player "+ticTacToe.getCurrentPlayer()+", enter an empty row[1-3] and column[1-3] to place your mark!:");
                row = s.nextInt()-1;
                col = s.nextInt()-1;
            }
            while(!ticTacToe.placeMark(row,col));
            ticTacToe.changePlayer();
        }
        while (!ticTacToe.checkForWin() && !ticTacToe.isBoardFull());
        if(ticTacToe.isBoardFull() && !ticTacToe.checkForWin()){
            System.out.println("The game was a tie");
        }
        else{
            System.out.println("Current board layout:");
            ticTacToe.printBoard();
            ticTacToe.changePlayer();
            System.out.println(Character.toUpperCase(ticTacToe.getCurrentPlayer())+" Wins!");
        }
    }
}
