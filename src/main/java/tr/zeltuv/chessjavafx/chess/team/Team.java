package tr.zeltuv.chessjavafx.chess.team;

public enum Team {
    WHITE, BLACK;

    public int adjustDirection(int a,int i) {
        if (this!=ON_TOP){
            return a-i;
        }
        return i+i;
    }

    public static Team ON_TOP = BLACK;
}
