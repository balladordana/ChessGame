import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class ChessPiece {
    String color;
    boolean check = true;
    Image sprite;
    BufferedImage sheet;
    protected int sheetScale;
    public int yPos, xPos;
    public int col, line;

    public ChessPiece(String color, int col, int line) {
        this.color = color;
        try{
            sheet = ImageIO.read(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("res/pieces.png")));
        } catch (IOException e){
            e.printStackTrace();
        }
        this.sheetScale = sheet.getWidth()/6;
        this.col = col;
        this.line = line;
        this.xPos = col * 80;
        this.yPos = line * 80;
    }
    public void setCheck(boolean check) {
        this.check = check;
    }
    public String getColor(){
        return this.color;
    }
    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int toLine, int toColumn);
    public abstract boolean moveCollidesWithPieces(ChessBoard chessBoard, int col, int line);
    public abstract String getSymbol();
    public void paint (Graphics2D g2d, int xPos, int yPos){
        g2d.drawImage(sprite, xPos, yPos, null);
    }
}
