package tr.zeltuv.chessjavafx.chess.team;

public enum Team {
    WHITE, BLACK;

    public int adjustDirection(int i) {
        if (this!=ON_TOP){
            return i*-1;
        }
        return i;
    }

    public static Team ON_TOP = BLACK;
}
