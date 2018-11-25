package hk.edu.polyu.comp.comp2021.jungle.view.gui;

import hk.edu.polyu.comp.comp2021.jungle.model.Board;
import hk.edu.polyu.comp.comp2021.jungle.model.Coordinates;
import hk.edu.polyu.comp.comp2021.jungle.model.pieces.Piece;
import hk.edu.polyu.comp.comp2021.jungle.controller.command.Command;
import hk.edu.polyu.comp.comp2021.jungle.controller.command.CommandListener;
import hk.edu.polyu.comp.comp2021.jungle.controller.command.CommandType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class draws the game board
 */
public class GameBoardPanel extends JPanel {

    private static final int BOARD_WIDTH_PX = 463;
    private static final int BOARD_HEIGHT_PX = 598;

    private static final int PIECE_WIDTH_PX = 64;
    private static final int PIECE_HEIGHT_PX = 64;

    private static final float RECT_HOVER_TRANSPARENCY = 0.3f;
    private static final float RECT_HINT_TRANSPARENCY = 0.2f;

    private Board board;
    private final Timer updateTimer;

    private CommandListener commandListener;

    private final Map<Piece, Image> imageMap = new HashMap<>();
    private final Map<Rectangle, Coordinates> hitboxToCoords = new HashMap<>();
    private final Map<Coordinates, Rectangle> coordsToHitbox = new HashMap<>();

    private final Image boardImage = ImageLoader.loadImageFromAssets("board.png");

    private Rectangle hoveredRect;
    private Piece selectedPiece;

    /**
     * Constructs the game screen
     */
    public GameBoardPanel() {
        super();
        updateTimer = new Timer(16, this::onUpdate);
        Dimension size = new Dimension(BOARD_WIDTH_PX, BOARD_HEIGHT_PX);
        setPreferredSize(size);
        setBackground(Color.WHITE);
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                onMouseMoved(e);
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseClicked(e);
                onMouseReleased(e);
            }
        });
    }

    /**
     * Binds the board to this panel
     * The Board reference is held just for drawing the required output
     * Nothing about the Board will ever be changed in this class
     *
     * @param board The board
     */
    public void bindBoard(Board board) {
        setSelectedPiece(null);
        imageMap.clear();
        hitboxToCoords.clear();
        coordsToHitbox.clear();
        this.board = board;
        for (int x = 0; x < Board.BOARD_WIDTH; x++) {
            for (int y = 0; y < Board.BOARD_HEIGHT; y++) {
                Coordinates coords = new Coordinates(x, y);
                Rectangle hitbox = new Rectangle(toScreenSpacePoint(coords), new Dimension(PIECE_WIDTH_PX, PIECE_HEIGHT_PX));
                hitboxToCoords.put(hitbox, coords);
                coordsToHitbox.put(coords, hitbox);
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
     * Called when the board is being updated
     */
    public void onBoardUpdated() {
        setSelectedPiece(null);
    }

    /**
     * Checks if a board is being bound to this panel
     *
     * @param board The board to be checked
     * @return True if it is already bound to a Board
     */
    public boolean isBoardBound(Board board) {
        return board != null && this.board == board;
    }

    /**
     * Sets a command listener for this game panel
     * When a user interacts with this panel and a command is issued, the command listener will be triggered
     *
     * @param listener The listener
     */
    public void setUserCommandListener(CommandListener listener) {
        this.commandListener = listener;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(boardImage, 0, 0, null);
        if (board == null) return;
        if (hoveredRect != null) {
            Piece piece = board.getTile(hitboxToCoords.get(hoveredRect)).getOccupiedPiece();
            if (piece != null && piece.getOwner() == board.getCurrentPlayer()) {
                g2.setColor(Color.DARK_GRAY);
                g2.setComposite(AlphaComposite.SrcOver.derive(RECT_HOVER_TRANSPARENCY));
                g2.fillRect(hoveredRect.x, hoveredRect.y, hoveredRect.width, hoveredRect.height);
                g2.setComposite(AlphaComposite.SrcOver);
            }
        }
        java.util.List<Coordinates> availableMoves = new ArrayList<>();
        if (selectedPiece != null) {
            Rectangle selectedRect = coordsToHitbox.get(board.getCoordinates(selectedPiece));
            g2.setColor(Color.BLUE);
            g2.setStroke(new BasicStroke(3));
            g2.draw(selectedRect);
            g2.setColor((selectedPiece.getOwner() == board.getPlayerOne()) ? Color.RED : Color.BLUE);
            availableMoves.addAll(board.getAvailableMoves(board.getCoordinates(selectedPiece)));
        }
        for (Coordinates coords : availableMoves) {
            Rectangle rect = coordsToHitbox.get(coords);
            g2.setComposite(AlphaComposite.SrcOver.derive(RECT_HINT_TRANSPARENCY));
            g2.fillRect(rect.x, rect.y, rect.width, rect.height);
            g2.setComposite(AlphaComposite.SrcOver);
        }
        for (Piece piece : imageMap.keySet()) {
            Coordinates coords = board.getCoordinates(piece);
            if (coords != null) {
                Point point = toScreenSpacePoint(coords);
                g2.drawImage(imageMap.get(piece), point.x, point.y, null);
            }
        }
    }

    private void onMouseMoved(MouseEvent e) {
        setHoveredRect(getCollidedHitbox(e.getPoint()));
    }

    private void onMouseReleased(MouseEvent e) {
        Rectangle hitbox = getCollidedHitbox(e.getPoint());
        if (hitbox == null) return;
        if (hitbox != hoveredRect) return;
        Coordinates coords = hitboxToCoords.get(hitbox);
        Piece piece = board.getTile(coords).getOccupiedPiece();
        if (piece != null && piece.getOwner() == board.getCurrentPlayer()) {
            setSelectedPiece(piece);
        } else if (selectedPiece != null) {
            Coordinates origin = board.getCoordinates(selectedPiece);
            java.util.List<Coordinates> availableMoves = board.getAvailableMoves(origin);
            if (availableMoves.contains(coords) && commandListener != null) {
                commandListener.onCommand(new Command(CommandType.MOVE, new String[]{origin.toString(), coords.toString()}));
                setSelectedPiece(null);
            }
        }
    }

    private void onUpdate(ActionEvent event) {
        repaint();
    }

    private Point toScreenSpacePoint(Coordinates coordinates) {
        return new Point(coordinates.getX() * (PIECE_WIDTH_PX + 2) + 2, coordinates.getY() * (PIECE_HEIGHT_PX + 2) + 2);
    }

    private Rectangle getCollidedHitbox(Point point) {
        for (Rectangle rect : hitboxToCoords.keySet()) {
            if (rect.contains(point)) {
                return rect;
            }
        }
        return null;
    }

    private void setHoveredRect(Rectangle hoveredRect) {
        this.hoveredRect = hoveredRect;
    }

    private void setSelectedPiece(Piece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }
}
