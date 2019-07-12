public class TicTacToeCompHum {

    // Initialize variables
    private static char[][] board;
    private String currentPlayer;
    private char currentKey;

    public TicTacToeCompHum() {
        currentPlayer = "Human";
        currentKey = 'X';
        board = new char[3][3];
        initializeBoard();
        printBoard();
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public static void initializeBoard() {
        //loop through rows
        for (int i = 0; i < 3; i++) {
            //loop through colums
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public static void printBoard() {
        System.out.println("------------------");
        System.out.println("    A "+"|"+" B "+"|"+" C ");
        for (int i = 0; i < 3; i++) {
            System.out.print(i+1+" | ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("------------------");
        }
    }
    public boolean isBoardFull(){
        boolean isFull=true;

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i][j]=='-') {
                    isFull=false;
                }
            }
        }
        return isFull;
    }
    public boolean checkForWin(){
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());

    }
    private boolean checkRowsForWin(){
        for(int i=0;i<3;i++){
            if(checkRowCol(board[i][0],board[i][1],board[i][2]) == true){
                return true;
            }
        }
        return false;

    }
    private boolean checkColumnsForWin(){
        for(int i=0;i<3;i++){
            if(checkRowCol(board[0][i],board[1][i],board[2][i]) == true){
                return true;
            }
        }
        return false;

    }
    private boolean checkDiagonalsForWin(){
        return ((checkRowCol(board[0][0],board[1][1],board[2][2])==true|| checkRowCol(board[0][2], board[1][1],board[2][0])==true));

    }
    private boolean checkRowCol(char c1,char c2, char c3){
        return ((c1 !='-') && (c1==c2) && (c2==c3));

    }
    public void changePlayer(){
        if(currentPlayer == "Human"){
            currentPlayer = "Computer";
            currentKey = 'O';
        } else {
            currentPlayer = "Human";
            currentKey = 'X';
        }
    }
    public boolean splitAndPlaceMark(String val){
        //Splitting
        String[] parts = val.split("([a-z]+)([0-9]+)");
        int row=0, col=0;
        if(parts[0].equals('A')) row = 0;
        if(parts[0].equals('B')) row = 1;
        if(parts[0].equals('C')) row = 2;
        col=Integer.parseInt(parts[1])-1;

        //Placing
        if((row >= 0) && row < 3){
            if((col >= 0 )&& col < 3){
                if(board[row][col] == '-'){
                    board[row][col] = currentKey;
                    return true;
                }
            }
        }
        return false;
    }
    public void computerGame(){

    }
}