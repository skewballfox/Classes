import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.util.ArrayList;
public class NTilePuzzle implements Puzzle{
    private int moveCount;
    private int boardLength;
    private int[][] Board;

    private ArrayList<String> availableMoves=new ArrayList<String>();
    private int emptyTileRow,emptyTileColumn;
    private String spacer;
    
    public NTilePuzzle(String configName) throws IllegalArgumentException {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        try {
            for (String value : Files.readString((new File(configName)).toPath()).strip().split(",")) {
                try {
                    temp.add(Integer.valueOf(value));
                } catch (NumberFormatException e) {
                    System.out.println("the input file contains values which aren't numbers or commas");
                    System.out.println(value);
                }
            }
         } catch (IOException e) {
             System.out.println("An I/O exception has occured, please try again");
        }         
        double squareRoot= Math.sqrt(temp.size());
        boardLength =(int) Math.floor(squareRoot);
        if ((squareRoot - boardLength)>.0001)
            throw new IllegalArgumentException("the number of tiles need to be a perfect square");
        Board=new int[boardLength][boardLength];
        for (int row=0; row < boardLength; row++){
            for (int column=0; column < boardLength; column++) {
                Board[row][column] = temp.get(column+(boardLength*row));
                if (Board[row][column] == 0){
                    emptyTileRow=row;
                    emptyTileColumn=column;
                }
                    
            }
        }
        updateAvailableMoves();
    }

    public int getMoveCount(){
        return moveCount;
    }
    public void updateGameState(String move) {

        if (!availableMoves.contains(move.toLowerCase())){
            throw new IllegalArgumentException("%s is not a valid option for a move");
        }

        if (move == "up") {
           swapEmptyTile(emptyTileRow-1,emptyTileColumn);
        }
        if (move == "left") {
           swapEmptyTile(emptyTileRow,emptyTileColumn-1); 
        }
        if (move == "right") {
            swapEmptyTile(emptyTileRow,emptyTileColumn+1);
        }
        if (move == "down") {
            swapEmptyTile(emptyTileRow+1,emptyTileColumn);
        }
        moveCount+=1;
        updateAvailableMoves();
        
    }
    public boolean checkTerminalState(){
        if ((emptyTileRow==0 && emptyTileColumn==0) ||
            (emptyTileRow==boardLength-1 && emptyTileColumn==boardLength-1)) {

                int prev=Board[0][0];
                for (int row=0;row<boardLength;row++){
                    for (int column=0;column<boardLength;column++){
                        if ((row==0 && column==0)||
                            (row==boardLength-1&&column==boardLength-1))
                            continue;//if all previous elements checked last element is in order
                        else if (Board[row][column]!=++prev){//only works if sequence
                            return false;
                    }
                }
            }
            return true;
        } else
            return false;
            
    }
    private void swapEmptyTile(int row,int column){
        Board[emptyTileRow][emptyTileColumn]=Board[row][column];
        Board[row][column]=0;
        emptyTileRow=row;
        emptyTileColumn=column;
    }
    public ArrayList<String> getAvailableMoves(){
        return availableMoves;
    }

    private void updateAvailableMoves() {
        
        availableMoves.clear();
        if (emptyTileRow!=0)
            availableMoves.add("up");

        if (emptyTileColumn!=0)
            availableMoves.add("left");

        if (emptyTileColumn!=boardLength-1)
            availableMoves.add("right");
        

        if (emptyTileRow!=boardLength-1)
            availableMoves.add("down");
    }

    public String gameStateToString(){
        String GameState="";
        int rowLength;
        for (int row=0;row<boardLength; row++) {
            GameState+="\n| ";
            for (int column=0;column<boardLength; column++){
                if (boardLength>3 && Board[row][column]<10)
                    GameState+=" ";
                if (Board[row][column]!=0)
                    GameState+=String.valueOf(Board[row][column])+" | ";
                else
                    GameState+="  | ";
            }
            if (String.valueOf(spacer)=="null"){
                spacer="\n"+"-".repeat(GameState.length()-2);
            }
            GameState+=spacer;
        }
    return spacer+GameState;
    }
    public static void main(String [] args){
        NTilePuzzle testPuzzle=new NTilePuzzle("eightpuzzle_1.txt");
        //test toString
        System.out.println(testPuzzle.gameStateToString());
        //test moves
        for (String move: testPuzzle.getAvailableMoves())
            System.out.println(move);
        if ((new NTilePuzzle("test_puzzle.txt")).checkTerminalState()==true)
            System.out.println("it works");

    }
}
