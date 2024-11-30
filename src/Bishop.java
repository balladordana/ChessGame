import java.awt.image.BufferedImage;

public class Bishop extends ChessPiece{


    public Bishop(String color, int col, int line) {
        super(color, col, line);
        this.sprite = sheet.getSubimage(2 * sheetScale, this.color.equals("White") ? 0 : sheetScale, sheetScale, sheetScale)
                .getScaledInstance(80, 80, BufferedImage.SCALE_SMOOTH);
    }

    public String getColor() {
        return super.getColor();
    }
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard,  int toLine, int toColumn){
        // Проверка, что конечные координаты находятся в пределах доски
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false; //вернуть инфо что неправильно выбраны точки
        }

        // Проверка, что конь не пытается остаться на месте
        if (this.line == toLine && this.col == toColumn) {
            return false; //вернуть инфо что конь должен двигаться
        }

        if (Math.abs(toLine - this.line) == Math.abs(toColumn - this.col)){
            return !chessBoard.isOccupiedByOwnPiece(toLine, toColumn); // Конь может сделать этот ход
        }

        return false; // Конь не может сделать этот ход
    }
    @Override
    public boolean moveCollidesWithPieces(ChessBoard chessBoard, int col, int line) {
        //нижний правый угол
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
        return false;
    }
    @Override
    public String getSymbol() {
        return "B";
    }
}
