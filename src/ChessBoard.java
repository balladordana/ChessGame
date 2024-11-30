import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessBoard extends JPanel {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;
    int tileSize = 80;
    ChessPiece selectedPiece;
    Input input = new Input(this);
    private JLabel label;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
    }
    public ChessPiece getPiece(int column, int line){
        return board[line][column];
    }
    private boolean castling0(){
        if (nowPlayer.equals("White")){
            if (board[0][0] == null || board[0][4] == null) return false;
            if (board[0][0].getSymbol().equals("R") && board[0][4].getSymbol().equals("K")
                && board[0][1] == null && board[0][2] == null && board[0][3] == null) {
                if (board[0][0].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                        board[0][0].check && board[0][4].check &&
                        !new King("White", 0, 2).isUnderAttack( this, 0, 2)){
                    board[0][4] = null;
                    board[0][2] = new King ("White", 2, 0);
                    board[0][2].check = false;
                    board[0][0] = null;
                    board[0][3] = new Rook ( "White", 3, 0);
                    board[0][3].check = false;
                    nowPlayer = "Black";
                    label.setText("Ход игрока: " + nowPlayer);
                    this.repaint();
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][0] == null || board[7][4] == null) return false;
            if (board[7][0].getSymbol().equals("R") && board[7][4].getSymbol().equals("K")
                    && board[7][1] == null && board[7][2] == null && board[7][3] == null) {
                if (board[7][0].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                        board[7][0].check && board[7][4].check &&
                        !new King("Black", 0, 2).isUnderAttack( this, 7, 2)){
                    board[7][4] = null;
                    board[7][2] = new King ("Black", 2, 7);
                    board[7][2].check = false;
                    board[7][0] = null;
                    board[7][3] = new Rook ( "Black", 3, 7);
                    board[7][3].check = false;
                    nowPlayer = "White";
                    label.setText("Ход игрока: " + nowPlayer);
                    this.repaint();
                    return true;
                } else return false;
            } else return false;
        }
    }
    private boolean castling7(){
        if (nowPlayer.equals("White")){
            if (board[0][7] == null || board[0][4] == null) return false;
            if (board[0][7].getSymbol().equals("R") && board[0][4].getSymbol().equals("K")
                    && board[0][6] == null && board[0][5] == null) {
                if (board[0][7].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                        board[0][7].check && board[0][4].check &&
                        !new King("White", 0, 6).isUnderAttack( this, 0, 6)){
                    board[0][4] = null;
                    board[0][6] = new King ("White", 6, 0);
                    board[0][6].check = false;
                    board[0][7] = null;
                    board[0][5] = new Rook ( "White", 5, 0);
                    board[0][5].check = false;
                    nowPlayer = "Black";
                    label.setText("Ход игрока: " + nowPlayer);
                    this.repaint();
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][7] == null || board[7][4] == null) return false;
            if (board[7][7].getSymbol().equals("R") && board[7][4].getSymbol().equals("K")
                    && board[7][6] == null && board[7][5] == null) {
                if (board[7][7].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                        board[7][7].check && board[7][4].check &&
                        !new King("Black", 6, 7).isUnderAttack( this, 7, 6)){
                    board[7][4] = null;
                    board[7][6] = new King ("Black", 6, 7);
                    board[7][6].check = false;
                    board[7][7] = null;
                    board[7][5] = new Rook ( "Black", 5, 7);
                    board[7][5].check = false;
                    nowPlayer = "White";
                    label.setText("Ход игрока: " + nowPlayer);
                    this.repaint();
                    return true;
                } else return false;
            } else return false;
        }
    }
    public void makeMove(int toColumn, int toLine) {
        board[toLine][toColumn] = selectedPiece;
        board[selectedPiece.line][selectedPiece.col] = null;

        selectedPiece.col = toColumn;
        selectedPiece.line = toLine;
        selectedPiece.xPos = toColumn * tileSize;
        selectedPiece.yPos = toLine * tileSize;

        selectedPiece.setCheck(false);
        nowPlayer = nowPlayer.equals("White") ? "Black" : "White";
        label.setText("Ход игрока: " + nowPlayer);
        for (int c = 0; c < 8; c++) {
            for (int r = 0; r < 8; r++) {
                if (this.getPiece(c, r) != null
                        && this.getPiece(c, r).getColor().equals(nowPlayer)
                        && this.getPiece(c, r).getSymbol().equals("K")) {
                    if (new King(nowPlayer, c, r).isUnderAttack( this, selectedPiece.col, selectedPiece.line))
                        JOptionPane.showMessageDialog(null, "Игроку " + nowPlayer + " поставлен Шах!");
                }
            }
        }
    }
    public boolean isOccupiedByOwnPiece(int toLine, int toColumn){
        if (this.selectedPiece != null && board[toLine][toColumn] != null) {
            return this.selectedPiece.getColor().equals(board[toLine][toColumn].getColor());
        }
        else return false;
    }
    public void drawOptions(){
        label = new JLabel("Ход игрока:" + this.nowPlayer);
        JButton button0 = new JButton("<html>Выполнить<br>рокировку по 0 столбцу</html>");
        JButton button7 = new JButton("<html>Выполнить<br>рокировку по 7 столбцу</html>");
        button0.setPreferredSize(new Dimension(120, 100));
        button0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (castling0()) {
                    JOptionPane.showMessageDialog(null, "Рокировка по 0 столбцу выполнена!");
                } else {
                    JOptionPane.showMessageDialog(null, "Рокировку выполнить невозможно!");
                }
            }
        });
        button7.setPreferredSize(new Dimension(120, 100));
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (castling7()) {
                    JOptionPane.showMessageDialog(null, "Рокировка по 7 столбцу выполнена!");
                } else {
                    JOptionPane.showMessageDialog(null, "Рокировку выполнить невозможно!");
                }
            }
        });

        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 10));
        buttonPanel.add(label);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(button0);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(button7);
        add(buttonPanel, BorderLayout.EAST);
    }
    public  void drawBoard(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawOptions();
        frame.add(this);
        frame.setSize(800, 677);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        for (int r = 0; r < 8; r++){
            for (int c = 0; c < 8; c++){
                g2d.setColor((c+r) % 2 == 0 ? new Color(227, 198, 181) : new Color(157, 105, 53));
                g2d.fillRect(c * 80, r * 80, 80, 80);
            }
        }

        if (selectedPiece != null)
            for (int c = 0; c < 8; c++) {
                for (int r = 0; r < 8; r++) {
                    if (selectedPiece.canMoveToPosition(this, r, c)
                            && !(selectedPiece.moveCollidesWithPieces(this, c, r))){
                        g2d.setColor(new Color(68, 180, 57, 190));
                        g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
                    }
                }
            }

        for (int r = 0; r < 8; r++){
            for (int c = 0; c < 8; c++){
                if (board[r][c] != null) { board[r][c].paint(g2d, board[r][c].xPos, board[r][c].yPos);}
            }
        }
    }
}
