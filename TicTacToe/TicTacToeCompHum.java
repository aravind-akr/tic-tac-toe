import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class TicTacToeCompHum {

    // Initialize variables
    private static char[][] board;
    private String currentPlayer;
    private char currentKey;
    private char otherKey;

    public TicTacToeCompHum() {
        currentPlayer = "Human";
        currentKey = 'X';
        otherKey = 'O';
        board = new char[3][3];
        initializeBoard();
        printBoard();
    }

    // Getting the current player
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    //Getting the current key
    public char getCurrentKey(){
        return currentKey;
    }

    // Initializing board with empty 3 rows and 3 columns
    public static void initializeBoard() {
        //loop through rows
        for (int i = 0; i < 3; i++) {
            //loop through colums
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Printing Board
    public void printBoard() {
        System.out.println("------------------");
        System.out.println("    1 " + "|" + " 2 " + "|" + " 3 ");
        for (int i = 0; i < 3; i++) {
            if(i==0){
                System.out.print("A" + "| ");
            }
            if(i==1){
                System.out.print("B" + "| ");
            }
            if(i==2){
                System.out.print("C" + "| ");
            }

            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("------------------");
        }
    }

    // Check if board it full
    public boolean isBoardFull() {
        boolean isFull = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    isFull = false;
                }
            }
        }
        return isFull;
    }

    // Check for winning
    public boolean checkForWin() {
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());

    }

    // Rows check win
    private boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
                return true;
            }
        }
        return false;

    }

    // Columns check win
    private boolean checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
                return true;
            }
        }
        return false;

    }

    // Diagonals check win
    private boolean checkDiagonalsForWin() {
        return ((checkRowCol(board[0][0], board[1][1], board[2][2]) == true || checkRowCol(board[0][2], board[1][1], board[2][0]) == true));

    }

    // Row col val
    private boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));

    }

    // Change player
    public void changePlayer() {
        if (currentPlayer.equals("Human")) {
            currentPlayer = "Computer";
            currentKey = 'O';
            otherKey='X';
        } else {
            currentPlayer = "Human";
            currentKey = 'X';
            otherKey='O';
        }
    }

    public int[] splitting(String val){
        //Splitting
        char[] parts = val.toCharArray();
        int rowCol[]=new int[2];
        int row=0, col=0;
        Character partsObject = new Character(parts[0]);

        if (partsObject.equals('A') || partsObject.equals('a')) row = 0;
        if (partsObject.equals('B') || partsObject.equals('b')) row = 1;
        if (partsObject.equals('C') || partsObject.equals('c')) row = 2;

        col = Character.getNumericValue(parts[1])-1;
        rowCol[0]=row;
        rowCol[1]=col;
        return rowCol;
    }
    // place mark in the board.
    public boolean placeMark(String val, char key) {

        //System.out.println("Placing mark at:"+val+" for "+key);
        int row=splitting(val)[0];
        int col=splitting(val)[1];
        //Placing
        if ((row >= 0) && row < 3) {
            if ((col >= 0) && col < 3) {
                if (board[row][col] == '-') {
                    board[row][col] = key;
                    return true;
                }
            }
        }
        return false;
    }

    // get Row val
    public String getRowValue(int row){
        String cell="";
        if (row == 0) {
            cell = "A";
        }
        if (row == 1) {
            cell = "B";
        }
        if (row == 2) {
            cell = "C";
        }
        return cell;
    }

    // get col val
    public String getColumnValue(int column){
        String cell="";
        if (column == 0) {
            cell = "1";
        }
        if (column == 1) {
            cell = "2";
        }
        if (column == 2) {
            cell = "3";
        }
        return cell;
    }

    // Row check if two filled with same key
    public String rowCheck(char key){
        String cell="";
        int row = 0;
        while (row < 3) {
            if (board[row][0] == key && board[row][1] == key && board[row][2]=='-') {
                cell = getRowValue(row)+ "3";
                break;
            }
            if (board[row][0] == key && board[row][2] == key && board[row][1]=='-') {
                cell = getRowValue(row)+ "2";
                break;
            }
            if (board[row][1] == key && board[row][2] == key && board[row][0]=='-') {
                cell = getRowValue(row)+ "1";
                break;
            }
            row++;
            cell = "Not found";
        }
        return cell;
    }

    // col check if two filled with same key
    public String columnCheck(char key){
        String cell = "";
        int column = 0;
        while (column < 3) {
            if (board[0][column] == key && board[1][column] == key && board[2][column] == '-') {
                cell = "C"+getColumnValue(column);
                break;
            }
            if (board[0][column] == key && board[2][column] == key && board[1][column] == '-') {
                cell = "B"+getColumnValue(column);
                break;
            }
            if (board[1][column] == key && board[2][column] == key && board[0][column] == '-') {
                cell = "A"+getColumnValue(column);
                break;
            }
            cell = "Not found";
            column++;
        }
        return cell;
    }

    // diag check if two filled with same key
    public String diagonalCheck(char key){
        String cell = "";
        if(board[0][0] == key && board[1][1] == key && board[2][2]=='-'){
            cell = "C3";
            return cell;
        }
        if(board[0][0] == key && board[2][2] == key && board[1][1]=='-'){
            cell = "B2";
            return cell;
        }
        if(board[1][1] == key && board[2][2] == key && board[0][0]=='-'){
            cell = "A1";
           return cell;
        }
        if(board[0][2] == key && board[1][1] == key && board[2][0]=='-'){
            cell = "C1";
           return cell;
        }
        if(board[2][0] == key && board[1][1] == key && board[0][2]=='-'){
            cell = "A3";
            return cell;
        }
        if(board[0][2] == key && board[2][0] == key && board[1][1]=='-'){
            cell = "B2";
            return cell;
        }
        cell = "Not found";
        return cell;
    }

    // Computer checks if two values of computer key is filled in first row then column then diagonal. If yes, it blocks its value in that place, else it checks for Human race.
    public void computerHumanWinCheck(String val, char key){

        String rowCheck = rowCheck(key);
        if(!(rowCheck.equals("Not found"))){
            placeMark(rowCheck,'O');
        }
        else{
            String colCheck = columnCheck(key);
            if(!(colCheck.equals("Not found"))){
                placeMark(colCheck,'O');
            }
            else{
                String diagCheck = diagonalCheck(key);
                if(!(diagCheck.equals("Not found"))){
                    placeMark(diagCheck,'O');
                }
                else{
                    if(key!='O'){
                        randomSet(val);
                    }
                    else{
                        computerHumanWinCheck(val,'X');
                    }
                }
            }
        }
    }

    //Get the neighbors from the user entered value, and puts it into HashMap(key as index of the key and value as key value)
    public HashMap getNeighbors(int rowCol[]){
        int x=rowCol[0];
        int y=rowCol[1];
        int width;

        HashMap<String, Character> hashMap = new HashMap<String, Character>();
        width=1;
        while(width<3) {

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if ((i == (x - width) && ((y + width) >= j && j >= (y - width))) || (i == (x + width) && ((y + width) >= j && j >= (y - width))) || (j == (y - width) && ((x + width) >= i && i >= (x - width))) || (j == (y + width) && ((x + width) >= i && i >= (x - width)))) {
                        {
                            String keyIndex = Integer.toString(i) + Integer.toString(j);
                            hashMap.put(keyIndex, board[i][j]);
                        }
                    }
                }

            }
            width++;
        }
        return hashMap;
    }

    //Get a random from the HashMap and place 'O' at that place
    public void randomSet(String val){

        HashMap<int[],Character> hashMap = getNeighbors(splitting(val));
        hashMap.entrySet().removeIf(entry -> !('-'==entry.getValue()));
        List indexVal = new ArrayList(hashMap.keySet());
        Random random = new Random();
        String keyValue = (String)indexVal.get(random.nextInt(indexVal.size()));
        String[] keyCell = keyValue.split("");
        int[] digits= Stream.of(keyCell).mapToInt(Integer::parseInt).toArray();
        placeMark(getRowValue(digits[0])+getColumnValue(digits[1]),'O');
    }

    //Computer plays its game here
    public void computerGame(String val,int count) {
        System.out.println("Oh! It is my turn.... ");
        if(count == 0) {
            randomSet(val);
        }
        else {
            computerHumanWinCheck(val, 'O');
        }
    }
}
