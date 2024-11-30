import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Horse extends ChessPiece{
    public Horse(String color, int col, int line) {
        super(color, col, line);
        this.sprite = sheet.getSubimage(3 * sheetScale, this.color.equals("White") ? 0 : sheetScale, sheetScale, sheetScale)
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

        List<int[]> horseMoves = new ArrayList<>();
        horseMoves.add(new int[]{2, 1});
        horseMoves.add(new int[]{2, -1});
        horseMoves.add(new int[]{-2, 1});
        horseMoves.add(new int[]{-2, -1});
        horseMoves.add(new int[]{1, 2});
        horseMoves.add(new int[]{1, -2});
        horseMoves.add(new int[]{-1, 2});
        horseMoves.add(new int[]{-1, -2});

        int[] move = {toLine - this.line, toColumn - this.col};

        for (int[] horseMove : horseMoves) {
            if (horseMove[0] == move[0] && horseMove[1] == move[1]) {
                return !chessBoard.isOccupiedByOwnPiece(toLine, toColumn);
            }
        }
        return false;
    }

    @Override
    public boolean moveCollidesWithPieces(ChessBoard chessBoard, int col, int line) {
        return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
