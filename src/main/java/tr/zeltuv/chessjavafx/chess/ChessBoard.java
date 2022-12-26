package tr.zeltuv.chessjavafx.chess;

import tr.zeltuv.chessjavafx.chess.piece.Pawn;
import tr.zeltuv.chessjavafx.chess.team.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    public static Map<Character,Class<? extends Piece>> PIECES_CHAR = new HashMap<>() {{
        put('p', Pawn.class);
    }};


    private Team whiteTeam;
    private Team blackTeam;

    private Team playingTeam;

    private Piece[][] grid = new Piece[8][8];

    public ChessBoard(List<String> boardTiles) {
        placePieces(boardTiles);
    }

    public void placePieces(List<String> boardTiles){
    }
}
