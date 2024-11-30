import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Pawn extends ChessPiece{
    public Pawn(String color, int col, int line) {
        super(color, col, line);
        this.sprite = sheet.getSubimage(5 * sheetScale, this.color.equals("White") ? 0 : sheetScale, sheetScale, sheetScale)
                .getScaledInstance(80, 80, BufferedImage.SCALE_SMOOTH);
    }

    public String getColor() {
        return super.getColor();
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int toLine, int toColumn){
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false;
        }

        if (this.line == toLine && this.col == toColumn) {
            return false;
        }

        int[] move = {toLine - this.line, toColumn - this.col};
        int index = this.color.equals("White") ? 1 : -1;
        if (Arrays.equals(move, new int[]{index, 0}) && chessBoard.getPiece(toColumn, toLine) == null) {
            return !chessBoard.isOccupiedByOwnPiece(toLine, toColumn);
        } else if ((Arrays.equals(move, new int[]{index, 1}) || Arrays.equals(move, new int[]{index, -1}))
                    && chessBoard.getPiece(toColumn, toLine) != null){
            return !chessBoard.isOccupiedByOwnPiece(toLine, toColumn);
        } else {
            return (Arrays.equals(move, new int[]{index * 2, 0}) && this.check
                    && chessBoard.getPiece(toColumn, toLine) == null);
        }
    }

    @Override
    public boolean moveCollidesWithPieces(ChessBoard chessBoard, int col, int line) {
        if (this.line < line)
            for (int l = this.line + 1; l < line; l++) {
                if (chessBoard.getPiece(this.col, l) != null) return true;
            }
        else
            for (int l = this.line - 1; l > line; l--) {
                if (chessBoard.getPiece(this.col, l) != null) return true;
            }
        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
