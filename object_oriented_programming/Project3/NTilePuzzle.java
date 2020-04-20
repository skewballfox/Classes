import java.util.HashMap;
public class NTilePuzzle.java {
    private int moveCount;
    private int boardLength;
    private int[][] Board;
    
    private int emptyTileRow,emptyTileColumn;
    private boolean terminalState;

    private Set<String> AvailableMoves;
    NTilePuzzle(String configName){
        
    }

    public Playermove(char direction) throws IllegalArgumentException {
        /*
        swaps the values at two locations
        expected input
        w or k: up
        a or h: left
        d or l: right
        s or j: down
        */
        if (!AvailableMoves.contains(direction.toLower())){
            throw new IllegalArgumentException("%s is not a valid option for a move");
        }
        if (direction == "up") {
           swapEmptyTile(emptyTileRow-1,emptyTileColumn);
        }
        if (direction == "left") {
           swapEmptyTile(emptyTileRow,emptyTileColumn-1); 
        }
        if (direction == "right") {
            swapEmptyTile(emptyTileRow,emptyTileColumn+1)
        }
        if (direction == "down") {
            swapEmptyTile(emptyTileRow+1,emptyTileColumn)
        }
        moveCount+=1;
        checkState();
        if (terminalState==false){
            updateAvailableMoves();
        }
        
    }
    private updateState(){
        if ((emptyTileRow==0 && emptyTileColumn==0) ||
            (emptyTileRow==boardLength && emptyTileColumn==boardLength) {

            boolean sequentialCheck=true;
            int prev=Board[0][0];
            for (int row=0;row<boardLength;row++){
                for (int column=0;column<boardLength;column++){
                    if ((row==0 && column==0)||
                        (row==boardLength&&column==boardLength))
                        continue;
                    if (Board[row][column]!=prev++){
                        sequentialCheck=false;
                        break;
                    }
                }
            }
            if (sequentialCheck==true){
                terminalState=true;
            }
        }
    }
    private swapEmptyTile(row,column){
        Board[emptyTileRow][emptyTileColumn]=Board[row][Column];
        Board[row][column]=0;
        emptyTileRow=row;
        emptyTileColumn=column;
    }
    private updateAvailableMoves() {
    /*
    This mainly exist because game logic shouldn't
    be controlled in the interface
    */
        if (emptyTileRow!=1)
            AvailableMoves.add("up");
        else
            AvailableMoves.remove("up");

        if (emptyTileColumn!=1)
            AvailableMoves.add("left");
        else
            AvailableMoves.remove("left");

        if (emptyTileColumn!=n)
            AvailableMoves.add("right");
        else
            AvailableMoves.remove("right");

        if (emptyTileRow!=n)
            AvailableMoves.add("down")
        else
            AvailableMoves.remove("down")
    }
}
