public class Main {
    public static ChessBoard buildBoard() {
        ChessBoard board = new ChessBoard("White");

        board.board[0][0] = new Rook("White", 0, 0);
        board.board[0][1] = new Horse("White", 1, 0);
        board.board[0][2] = new Bishop("White", 2, 0);
        board.board[0][3] = new Queen("White", 3, 0);
        board.board[0][4] = new King("White", 4, 0);
        board.board[0][5] = new Bishop("White", 5, 0);
        board.board[0][6] = new Horse("White", 6, 0);
        board.board[0][7] = new Rook("White", 7, 0);
        board.board[1][0] = new Pawn("White", 0, 1);
        board.board[1][1] = new Pawn("White", 1, 1);
        board.board[1][2] = new Pawn("White", 2, 1);
        board.board[1][3] = new Pawn("White", 3, 1);
        board.board[1][4] = new Pawn("White", 4, 1);
        board.board[1][5] = new Pawn("White", 5, 1);
        board.board[1][6] = new Pawn("White", 6, 1);
        board.board[1][7] = new Pawn("White", 7, 1);

        board.board[7][0] = new Rook("Black", 0, 7);
        board.board[7][1] = new Horse("Black", 1, 7);
        board.board[7][2] = new Bishop("Black", 2, 7);
        board.board[7][3] = new Queen("Black", 3, 7);
        board.board[7][4] = new King("Black", 4, 7);
        board.board[7][5] = new Bishop("Black", 5, 7);
        board.board[7][6] = new Horse("Black", 6, 7);
        board.board[7][7] = new Rook("Black", 7, 7);
        board.board[6][0] = new Pawn("Black", 0, 6);
        board.board[6][1] = new Pawn("Black", 1, 6);
        board.board[6][2] = new Pawn("Black", 2, 6);
        board.board[6][3] = new Pawn("Black", 3, 6);
        board.board[6][4] = new Pawn("Black", 4, 6);
        board.board[6][5] = new Pawn("Black", 5, 6);
        board.board[6][6] = new Pawn("Black", 6, 6);
        board.board[6][7] = new Pawn("Black", 7, 6);
        return board;
    }

    public static void main(String[] args) {

        ChessBoard board = buildBoard();
        board.drawBoard();
    }
}