import java.awt.image.BufferedImage;

public class Queen extends ChessPiece{
    public Queen(String color, int col, int line) {
        super(color, col, line);
        this.sprite = sheet.getSubimage(sheetScale, this.color.equals("White") ? 0 : sheetScale, sheetScale, sheetScale)
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

        // Проверка, что конь не пытается остаться на месте
        if (this.line == toLine && this.col == toColumn) {
            return false; //вернуть инфо что конь должен двигаться
        }

        if (Math.abs(toLine - this.line) == Math.abs(toColumn - this.col) || toColumn == this.col || toLine == this.line){
            return !chessBoard.isOccupiedByOwnPiece(toLine, toColumn); // Конь может сделать этот ход
        }

        return false; // Конь не может сделать этот ход
    }

    @Override
    public boolean moveCollidesWithPieces(ChessBoard chessBoard, int col, int line) {
        if (this.col == col || this.line == line) {
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
        } else {
            if (this.col < col && this.line < line) {
                for (int i = 1; i < Math.abs(col - this.col); i++)
                    if (chessBoard.getPiece(this.col + i, this.line + i) != null)
                        return true;
            }
            //верхний правый угол
            else if (this.col < col && this.line > line) {
                for (int i = 1; i < Math.abs(col - this.col); i++)
                    if (chessBoard.getPiece(this.col + i, this.line - i) != null) return true;
            }
            //верхний левый угол
            else if (this.col > col && this.line > line) {
                for (int i = 1; i < Math.abs(this.col - col); i++)
                    if (chessBoard.getPiece(this.col - i, this.line - i) != null) return true;
            }
            //нижний левый угол
            else {
                for (int i = 1; i < Math.abs(col - this.col); i++)
                    if (chessBoard.getPiece(this.col - i, this.line + i) != null) return true;
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
