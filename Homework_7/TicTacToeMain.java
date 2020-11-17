package Homework_7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeMain extends JFrame {
    private static final int WINDOW_WIDTH = 640;
    private static final int WINDOW_HEIGHT = 480;

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int WINDOW_POSX = (screenSize.width - WINDOW_WIDTH) / 2;
    private static final int WINDOW_POSY = (screenSize.height - WINDOW_HEIGHT) / 2;;

    private Settings settings;
    private PlayingField playingField;

    public TicTacToeMain(){
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");

        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        settings = new Settings(this);
        playingField = new PlayingField();
        add(playingField);

        JButton btnPlay = new JButton("Start a New Game");
        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.defaultSettings();
            }
        });

        JButton btnSettings = new JButton("Settings");
        btnSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setVisible(true);
            }
        });

        JPanel bottomMenu = new JPanel();
        bottomMenu.setLayout(new GridLayout(2,2));
        JPanel startGame = new JPanel();
        startGame.setLayout(new GridLayout(1,1));
        startGame.add(btnPlay);
        bottomMenu.add(startGame);
        JPanel settingsExit = new JPanel();
        settingsExit.setLayout(new GridLayout(1,2));
        settingsExit.add(btnSettings);
        settingsExit.add(btnExit);
        bottomMenu.add(settingsExit);
        add(bottomMenu, BorderLayout.SOUTH);

        setResizable(false);
        setVisible(true);
    }

    void newGame(int gameMode, int fieldSizeX, int fieldSizeY, int victoryCondition){
        playingField.newGame(gameMode, fieldSizeX, fieldSizeY, victoryCondition);
        //playingField.repaint();
        //repaint();
        //playingField.setVisible(true);
    }
}
