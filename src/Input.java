import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Input extends MouseAdapter {
    ChessBoard chessBoard;

    public Input(ChessBoard chessBoard){
        this.chessBoard = chessBoard;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int col = e.getX() / chessBoard.tileSize;
        int line = e.getY() / chessBoard.tileSize;

        ChessPiece pieceXY = chessBoard.getPiece(col, line);
        if (pieceXY != null){
            chessBoard.selectedPiece = pieceXY;
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if (chessBoard.selectedPiece != null
                && chessBoard.selectedPiece.getColor().equals(chessBoard.nowPlayer)){
            chessBoard.selectedPiece.xPos = e.getX() - chessBoard.tileSize / 2;
            chessBoard.selectedPiece.yPos = e.getY() - chessBoard.tileSize / 2;

            chessBoard.repaint();
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        int col = e.getX() / chessBoard.tileSize;
        int line = e.getY() / chessBoard.tileSize;
        if (chessBoard.selectedPiece != null
                && chessBoard.selectedPiece.getColor().equals(chessBoard.nowPlayer)){
            if (chessBoard.selectedPiece.canMoveToPosition(chessBoard, line, col)
                    && !(chessBoard.selectedPiece.moveCollidesWithPieces(chessBoard, col, line))){
                chessBoard.makeMove(col, line);
            } else {
                chessBoard.selectedPiece.xPos = chessBoard.selectedPiece.col * chessBoard.tileSize;
                chessBoard.selectedPiece.yPos = chessBoard.selectedPiece.line * chessBoard.tileSize;
            }
        }
        chessBoard.selectedPiece = null;
        chessBoard.repaint();
    }

}
