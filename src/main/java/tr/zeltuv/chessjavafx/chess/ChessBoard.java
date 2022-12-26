package tr.zeltuv.chessjavafx.chess;

import tr.zeltuv.chessjavafx.chess.piece.Pawn;
import tr.zeltuv.chessjavafx.chess.team.Team;
import tr.zeltuv.chessjavafx.chess.team.TeamColor;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    public static Map<Character,Class<? extends Piece>> PIECES_CHAR = new HashMap<>() {{
        put('p', Pawn.class);
    }};


    private Team whiteTeam = new Team(TeamColor.WHITE,true);
    private Team blackTeam = new Team(TeamColor.BLACK,false);

    private Team playingTeam;

    private Piece[][] grid = new Piece[8][8];

    public ChessBoard(List<String> boardTiles) {
        placePieces(boardTiles);
    }

    public void placePieces(List<String> boardTiles){
        //TODO finalize the scanner

        for (int i = 0; i < boardTiles.size(); i++) {
            String row = boardTiles.get(i);

            Piece[] pieces = new Piece[8];

            char[] chars = row.toCharArray();

            for (char c : chars) {
                if(Character.toUpperCase(c) == c){
                    PIECES_CHAR.get(Character.toLowerCase(c)).
                }
                PIECES_CHAR.get()
            }
        }

    }

    public Piece createPiece(Class<? extends Piece> clazz,int x,int y,Team team){
        try {
            Constructor<? extends Piece> constructor =  clazz.getConstructor(
                    ChessBoard.class,
                    int.class,
                    int.class,
                    Team.class
            );

            //TODO complete constructor

            Piece piece = constructor.newInstance(
                    this,


            )
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }


    }
}
