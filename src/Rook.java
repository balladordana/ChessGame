import java.awt.image.BufferedImage;

public class Rook extends ChessPiece{
    public Rook(String color, int col, int line) {
        super(color, col, line);
        this.sprite = sheet.getSubimage(4 * sheetScale, this.color.equals("White") ? 0 : sheetScale, sheetScale, sheetScale)
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

        if (this.line == toLine || this.col == toColumn){
            return !chessBoard.isOccupiedByOwnPiece(toLine, toColumn);
        }

        return false;
    }

    @Override
    public boolean moveCollidesWithPieces(ChessBoard chessBoard, int col, int line){
        //вправо
        if (this.col < col)
            for (int c = this.col + 1; c < col; c++) {
                if (chessBoard.getPiece(c, this.line) != null) return true;
            }
        //влево
        else
            for (int c = this.col - 1; c > col; c--) {
                if (chessBoard.getPiece(c, this.line) != null) return true;
            }
        //вверх
        if (this.line < line)
            for (int l = this.line + 1; l < line; l++) {
                if (chessBoard.getPiece(this.col, l) != null) return true;
            }
        //вниз
        else
            for (int l = this.line - 1; l > line; l--) {
                if (chessBoard.getPiece(this.col, l) != null) return true;
            }
        return false; // Движение свободно
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
