package Homework_7;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JFrame {

    private static final int WINDOW_WIDTH = 320;
    private static final int WINDOW_HEIGHT = 240;

    private static final int MIN_VICTORY_CONDITION = 3;
    private static final int MIN_PLAYING_FIELD = 3;
    private static final int MAX_PLAYING_FIELD = 10;
    private static final String FIELD_SIZE_PREFIX = "Playing field size: ";
    private static final String VICTORY_PREFIX = "Victory conditions: ";

    private TicTacToeMain ticTacToeMain;

    private JRadioButton humanVSAI;
    private JRadioButton humanVSHuman;
    private JSlider sliderVictory;
    private JSlider sliderField;

    Settings(TicTacToeMain ticTacToeMain){
        this.ticTacToeMain = ticTacToeMain;
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        Rectangle mainWindowBounds = ticTacToeMain.getBounds();
        int positionX = (int) mainWindowBounds.getX() + ((int) mainWindowBounds.getWidth() - WINDOW_WIDTH) / 2;
        int positionY = (int) mainWindowBounds.getY() + ((int) mainWindowBounds.getHeight() - WINDOW_HEIGHT) / 2;
        setLocation(positionX, positionY);
        setResizable(false);
        setTitle("Game Settings");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getRootPane().setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        setLayout(new GridLayout(10, 1));

        //Game mode
        add(new JLabel("Choose a game mode:"));
        humanVSAI = new JRadioButton("Human vs AI", true);
        humanVSHuman = new JRadioButton("Human vs Human");
        ButtonGroup gameMode = new ButtonGroup();
        gameMode.add(humanVSAI);
        gameMode.add(humanVSHuman);
        add(humanVSAI);
        add(humanVSHuman);

        //Playing Field
        JLabel lblFieldSize = new JLabel(FIELD_SIZE_PREFIX + MIN_PLAYING_FIELD);
        JLabel lblVictory = new JLabel(VICTORY_PREFIX + MIN_VICTORY_CONDITION);

        sliderVictory = new JSlider(MIN_VICTORY_CONDITION, MIN_PLAYING_FIELD, MIN_VICTORY_CONDITION);
        sliderVictory.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        sliderField = new JSlider(MIN_PLAYING_FIELD, MAX_PLAYING_FIELD, MIN_PLAYING_FIELD);
        sliderField.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        sliderField.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentValue = sliderField.getValue();
                lblFieldSize.setText(FIELD_SIZE_PREFIX + currentValue);
                sliderVictory.setMaximum(currentValue);
            }
        });

        sliderVictory.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lblVictory.setText(VICTORY_PREFIX + sliderVictory.getValue());
            }
        });

        add(new JLabel("Choose playing field size:"));
        add(lblFieldSize);
        add(sliderField);
        add(new JLabel("Choose Victory Condition:"));
        add(lblVictory);
        add(sliderVictory);

        JButton btnPlay = new JButton("Start New Game");
        btnPlay.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnPlayClick();
            }
        });

        add(btnPlay);
        setVisible(false);
    }

    private void btnPlayClick(){
        int gameMode;

        if (humanVSAI.isSelected()){
            gameMode = PlayingField.HUMAN_VS_AI;
        } else if (humanVSHuman.isSelected()){
            gameMode = PlayingField.HUMAN_VS_HUMAN;
        } else{
            throw new RuntimeException("Unknown game mode!");
        }

        int fieldSize = sliderField.getValue();
        int victoryCondition = sliderVictory.getValue();

        ticTacToeMain.newGame(gameMode, fieldSize, fieldSize, victoryCondition);
        setVisible(false);
    }

    void defaultSettings(){
        ticTacToeMain.newGame(PlayingField.HUMAN_VS_AI, MIN_PLAYING_FIELD, MIN_PLAYING_FIELD, MIN_VICTORY_CONDITION);
    }
}
