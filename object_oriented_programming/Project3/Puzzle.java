import java.util.ArrayList;
public interface Puzzle {
    int getMoveCount();

    void updateGameState(String move);

    boolean checkTerminalState();

    ArrayList<String> getAvailableMoves();

    String gameStateToString();
}

