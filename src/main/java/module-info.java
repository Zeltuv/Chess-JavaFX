module tr.zeltuv.chessjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens tr.zeltuv.chessjavafx to javafx.fxml;
    exports tr.zeltuv.chessjavafx;

    opens tr.zeltuv.chessjavafx.node to javafx.fxml;
    exports tr.zeltuv.chessjavafx.node;

    opens tr.zeltuv.chessjavafx.scene to javafx.fxml;
    exports tr.zeltuv.chessjavafx.scene;

    opens tr.zeltuv.chessjavafx.utils to javafx.fxml;
    exports tr.zeltuv.chessjavafx.utils;

    opens tr.zeltuv.chessjavafx.chess to javafx.fxml;
    exports tr.zeltuv.chessjavafx.chess;

    opens tr.zeltuv.chessjavafx.chess.piece to javafx.fxml;
    exports tr.zeltuv.chessjavafx.chess.piece;

    opens tr.zeltuv.chessjavafx.chess.team to javafx.fxml;
    exports tr.zeltuv.chessjavafx.chess.team;
}