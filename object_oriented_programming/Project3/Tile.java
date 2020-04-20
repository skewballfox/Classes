public class Tile
{
    private String content;
    private final int row;
    private final int column;
    private Tile[] Neighbors;

    Tile(int row, int column,String content) {
        this.row=row;
        this.column=column;
    }
    int getContent(){
        return content;
    }
    
    Tile[] getNeighbors(){
        return Neighbors;
    }

    void setContent(int newContent){
        this.content=newContent;
    }
}
