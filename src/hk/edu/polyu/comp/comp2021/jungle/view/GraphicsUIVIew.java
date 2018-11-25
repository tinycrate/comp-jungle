package hk.edu.polyu.comp.comp2021.jungle.view;

import hk.edu.polyu.comp.comp2021.jungle.model.Board;
import hk.edu.polyu.comp.comp2021.jungle.view.gui.GameBoardPanel;
import hk.edu.polyu.comp.comp2021.jungle.view.gui.ImageLoader;
import hk.edu.polyu.comp.comp2021.jungle.controller.command.Command;
import hk.edu.polyu.comp.comp2021.jungle.controller.command.CommandListener;
import hk.edu.polyu.comp.comp2021.jungle.controller.command.CommandType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This class implements the GUI version of UIView
 */
public class GraphicsUIVIew extends JFrame implements UIView {

    private CommandListener commandListener;
    private final GameBoardPanel gameBoardPanel;

    private final Button cliButton;
    private final Button newButton;
    private final Button saveButton;
    private final Button openButton;

    private final Label statusLabel;

    /**
     * Constructs the GUI View
     */
    public GraphicsUIVIew() {
        super();
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconImage(ImageLoader.loadImageFromAssets("red_tiger.png"));
        setTitle("Jungle Game");
        gameBoardPanel = new GameBoardPanel();
        cliButton = new Button("Run in CLI...");
        newButton = new Button("New");
        saveButton = new Button("Save");
        openButton = new Button("Open");
        statusLabel = new Label("Welcome!", Label.LEFT);
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
        if (!gameBoardPanel.isBoardBound(board)) gameBoardPanel.bindBoard(board);
        statusLabel.setText(String.format("It's %s's turn!", board.getCurrentPlayer().getName()));
        statusLabel.setForeground((board.getCurrentPlayer() == board.getPlayerOne()) ? Color.RED : Color.BLUE);
        gameBoardPanel.onBoardUpdated();
    }

    @Override
    public void setUserCommandListener(CommandListener listener) {
        this.commandListener = listener;
    }

    @Override
    public String promptUser(String message) {
        return JOptionPane.showInputDialog(null, message, "Jungle Game", JOptionPane.QUESTION_MESSAGE);
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
            commandListener.onCommand(new Command(CommandType.NEW, null));
        }
    }

    private void onSaveClicked(ActionEvent e) {
        FileDialog fd = new FileDialog(this, "Save Game To...", FileDialog.SAVE);
        fd.setVisible(true);
        String filename = fd.getFile();
        if (filename != null) {
            commandListener.onCommand(new Command(CommandType.SAVE, new String[]{fd.getDirectory() + filename}));
        }
    }

    private void onOpenClicked(ActionEvent e) {
        FileDialog fd = new FileDialog(this, "Load Game From...", FileDialog.LOAD);
        fd.setVisible(true);
        String filename = fd.getFile();
        if (filename != null) {
            commandListener.onCommand(new Command(CommandType.OPEN, new String[]{fd.getDirectory() + filename}));
        }
    }

    private void onCliClicked(ActionEvent e) {
        notifyUser("To launch this game in command line mode, please pass -cli as an argument\nExample: java -jar JungleGame.jar -cli");
    }

    private void onGameBoardCommand(Command command) {
        commandListener.onCommand(command);
    }

    private void onClose(WindowEvent e) {
        commandListener.onCommand(new Command(CommandType.EXIT, null));
    }

    private void addComponents() {
        JPanel toolBarPanel = new JPanel(new BorderLayout());
        JPanel optionBarPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        toolBarPanel.setBorder(new EmptyBorder(0, 5, 0, 0));
        toolBarPanel.setBackground(Color.WHITE);
        optionBarPanel.setBackground(Color.WHITE);
        optionBarPanel.add(cliButton);
        optionBarPanel.add(newButton);
        optionBarPanel.add(saveButton);
        optionBarPanel.add(openButton);
        toolBarPanel.add(optionBarPanel, BorderLayout.EAST);
        statusLabel.setForeground(Color.DARK_GRAY);
        toolBarPanel.add(statusLabel, BorderLayout.CENTER);
        add(toolBarPanel, BorderLayout.PAGE_START);
        add(gameBoardPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        newButton.requestFocus();
    }

    private void setListeners() {
        newButton.addActionListener(this::onNewClicked);
        saveButton.addActionListener(this::onSaveClicked);
        openButton.addActionListener(this::onOpenClicked);
        cliButton.addActionListener(this::onCliClicked);
        gameBoardPanel.setUserCommandListener(this::onGameBoardCommand);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onClose(e);
            }
        });
    }
}