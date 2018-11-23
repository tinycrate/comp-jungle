package hk.edu.polyu.comp.comp2021.jungle.views;

import hk.edu.polyu.comp.comp2021.jungle.models.Board;
import hk.edu.polyu.comp.comp2021.jungle.models.Coordinates;
import hk.edu.polyu.comp.comp2021.jungle.models.pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This class draws the game board
 */
class GameBoardPanel extends JPanel {
    /**
     * The width of the panel
     */
    public static final int BOARD_WIDTH_PX = 463;
    /**
     * The height of the panel
     */
    public static final int BOARD_HEIGHT_PX = 598;

    private static final int PIECE_WIDTH_PX = 64;
    private static final int PIECE_HEIGHT_PX = 64;

    private Board board;
    private final Timer updateTimer;

    private final Map<Piece, Image> imageMap = new HashMap<>();
    private final Map<Rectangle, Coordinates> hitboxMap = new HashMap<>();

    private final Image boardImage = ImageLoader.loadImageFromAssets("board.png");


    /**
     * Constructs the game screen
     */
    GameBoardPanel() {
        super();
        updateTimer = new Timer(16, this::onUpdate);
        Dimension size = new Dimension(BOARD_WIDTH_PX, BOARD_HEIGHT_PX);
        setPreferredSize(size);
        setBackground(Color.WHITE);
    }

    /**
     * Binds the board to this panel
     * The Board reference is held just for drawing the required output
     * Nothing about the Board will ever be changed in this class
     *
     * @param board The board
     */
    void bindBoard(Board board) {
        this.board = board;
        imageMap.clear();
        hitboxMap.clear();
        for (int x = 0; x < Board.BOARD_WIDTH; x++) {
            for (int y = 0; y < Board.BOARD_HEIGHT; y++) {
                Coordinates coords = new Coordinates(x, y);
                Rectangle hitbox = new Rectangle(toScreenSpacePoint(coords), new Dimension(PIECE_WIDTH_PX, PIECE_HEIGHT_PX));
                hitboxMap.put(hitbox, coords);
                Piece piece = board.getTile(coords).getOccupiedPiece();
                if (piece != null) {
                    String playerColor = (piece.getOwner() == board.getPlayerOne()) ? "red" : "blue";
                    String pieceType = piece.getPieceType().name().toLowerCase();
                    Image image = ImageLoader.loadImageFromAssets(String.format("%s_%s.png", playerColor, pieceType));
                    imageMap.put(piece, image);
                }
            }
        }
        updateTimer.start();
    }

    /**
     * Checks if a board is being bound to this panel
     *
     * @return True if it is already bound to a Board
     */
    public boolean isBoardBound() {
        return board != null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(boardImage, 0, 0, null);
        if (board == null) return;
        for (Piece piece : imageMap.keySet()) {
            Coordinates coords = board.getCoordinates(piece);
            if (coords != null) {
                Point point = toScreenSpacePoint(coords);
                g2.drawImage(imageMap.get(piece), point.x, point.y, null);
            }
        }
    }

    private void onUpdate(ActionEvent event) {
        repaint();
    }

    private Point toScreenSpacePoint(Coordinates coordinates) {
        return new Point(coordinates.getX() * (PIECE_WIDTH_PX + 2) + 2, coordinates.getY() * (PIECE_HEIGHT_PX + 2) + 2);
    }

}
