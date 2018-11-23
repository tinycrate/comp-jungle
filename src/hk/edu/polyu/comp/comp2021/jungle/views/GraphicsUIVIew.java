package hk.edu.polyu.comp.comp2021.jungle.views;

import hk.edu.polyu.comp.comp2021.jungle.models.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * This class implements the GUI version of UIView
 */
public class GraphicsUIVIew extends JFrame implements UIView {

    private UserCommandListener commandListener;
    private final GameBoardPanel gameBoardPanel;

    private final Button cliButton;
    private final Button newButton;
    private final Button saveButton;
    private final Button loadButton;

    /**
     * Constructs the GUI View
     */
    public GraphicsUIVIew() {
        super();
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(ImageLoader.loadImageFromAssets("red_tiger.png"));
        setTitle("Jungle Game");
        gameBoardPanel = new GameBoardPanel();
        cliButton = new Button("Run in CLI...");
        newButton = new Button("New");
        saveButton = new Button("Save");
        loadButton = new Button("Load");
        addComponents();
        setListeners();
    }

    /**
     * Displays the View on screen
     */
    @Override
    public void init() {
        setVisible(true);
    }

    @Override
    public void updateBoard(Board board) {
        if (!gameBoardPanel.isBoardBound()) gameBoardPanel.bindBoard(board);
    }

    @Override
    public void setUserCommandListener(UserCommandListener listener) {
        this.commandListener = listener;
    }

    @Override
    public String promptUser(String message) {
        String response = JOptionPane.showInputDialog(null, message, "Jungle Game", JOptionPane.QUESTION_MESSAGE);
        return (response != null) ? response : "";
    }

    @Override
    public boolean promptUserQuestion(String message) {
        int response = JOptionPane.showConfirmDialog(null, message, "Jungle Game", JOptionPane.YES_NO_OPTION);
        return response == JOptionPane.YES_OPTION;
    }

    @Override
    public void notifyUser(String message) {
        JOptionPane.showMessageDialog(null, message, "Jungle Game", JOptionPane.INFORMATION_MESSAGE);
    }

    private void onNewClicked(ActionEvent e) {
        if (commandListener != null) {
            commandListener.OnCommand(new UserCommand(UserCommandType.NEW, null));
        }
    }

    private void onSaveClicked(ActionEvent e) {

    }

    private void onLoadClicked(ActionEvent e) {

    }

    private void onCliClicked(ActionEvent e) {
        notifyUser("To launch this game in command line mode, please pass -cli as an argument\nExample: java -jar junglegame.jar -cli");
    }

    private void addComponents() {
        JPanel optionBarPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        optionBarPanel.setBackground(Color.WHITE);
        optionBarPanel.add(cliButton);
        optionBarPanel.add(newButton);
        optionBarPanel.add(saveButton);
        optionBarPanel.add(loadButton);
        add(optionBarPanel, BorderLayout.PAGE_START);
        add(gameBoardPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        newButton.requestFocus();
    }

    private void setListeners() {
        newButton.addActionListener(this::onNewClicked);
        saveButton.addActionListener(this::onSaveClicked);
        loadButton.addActionListener(this::onLoadClicked);
        cliButton.addActionListener(this::onCliClicked);
    }
}