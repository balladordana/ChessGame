import java.awt.image.BufferedImage;

public class King extends ChessPiece {
    public King(String color, int col, int line) {
        super(color, col, line);
        this.sprite = sheet.getSubimage(0, this.color.equals("White") ? 0 : sheetScale, sheetScale, sheetScale)
                .getScaledInstance(80, 80, BufferedImage.SCALE_SMOOTH);
    }

    @Override
    public boolean moveCollidesWithPieces(ChessBoard chessBoard, int col, int line) {
        return false;
    }

    public String getColor() {
        return super.getColor();
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int toLine, int toColumn) {
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false;
        }

        if (this.line == toLine && this.col == toColumn) {
            return false;
        }

        if (Math.abs((toColumn - this.col) * (toLine - this.line)) == 1
                || (Math.abs(toColumn - this.col) + Math.abs(toLine - this.line)) == 1) {
            return !chessBoard.isOccupiedByOwnPiece(toLine, toColumn);
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    public boolean isUnderAttack(ChessBoard board, int column, int line) {
        if (board.getPiece(column, line) != null
                && board.getPiece(column, line).canMoveToPosition(board, this.line, this.col)
                && !(board.getPiece(column, line).moveCollidesWithPieces(board, this.col, this.line))) {
            return true;
        }
        return false;
    }
}
