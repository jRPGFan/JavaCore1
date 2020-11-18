package Homework_7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class PlayingField extends JPanel {
    private static final Random RANDOM = new Random();

    public static final int HUMAN_VS_AI = 0;
    public static final int HUMAN_VS_HUMAN = 1;

    private static final int EMPTY_CELL = 0;
    private static final int HUMAN_CELL = 1;
    private static final int AI_CELL = 2;
    private static final int CELL_PADDING = 5;

    private int gameOverStatus;
    private static final int DRAW = 0;
    private static final int HUMAN_VICTORY = 1;
    private static final int AI_VICTORY = 2;

    private static final String HUMAN_VICTORY_MSG = "Player Wins!";
    private static final String AI_VICTORY_MSG = "AI Wins!";
    private static final String DRAW_MSG = "It's a draw!";

    private boolean isGameOver;
    private boolean initMap;

    private int[][] field;
    private int fieldSizeX;
    private int fieldSizeY;
    private int victoryCondition;

    private int cellWidth;
    private int cellHeight;

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    PlayingField(){
        setBackground(Color.DARK_GRAY);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                update(e);
            }
        });
        initMap = false;
    }

    void newGame(int gameMode, int fieldSizeX, int fieldSizeY, int victoryCondition) {
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.victoryCondition = victoryCondition;
        field = new int[fieldSizeY][fieldSizeX];
        isGameOver = false;
        initMap = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g){
        if (!initMap) return;

        int width = getWidth();
        int height = getHeight();
        cellWidth = width/fieldSizeX;
        cellHeight = height/fieldSizeY;
        g.setColor(Color.BLACK);

        for (int i = 0; i < fieldSizeY; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, width, y);
        }

        for (int i = 0; i < fieldSizeX; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, height);
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke((new BasicStroke(10)));

        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isEmptyCell(x, y)) continue;
                if (field[y][x] == HUMAN_CELL){
                    g2.setColor(Color.BLUE);
                    g2.drawLine(x * cellWidth + CELL_PADDING * 2, y * cellHeight + CELL_PADDING * 2,
                            (x + 1) * cellWidth - CELL_PADDING * 2, (y +1 ) * cellHeight - CELL_PADDING * 2);
                    g2.drawLine((x + 1) * cellWidth - CELL_PADDING * 2, y * cellHeight + CELL_PADDING * 2,
                            x * cellWidth + CELL_PADDING * 2, (y + 1) * cellHeight - CELL_PADDING * 2);
//                    g.fillOval(x * cellWidth + CELL_PADDING, y * cellHeight + CELL_PADDING,
//                            cellWidth - CELL_PADDING * 2, cellHeight - CELL_PADDING * 2);
                } else if (field[y][x] == AI_CELL){
                    g2.setColor(Color.RED);
                    g2.drawOval(x * cellWidth + CELL_PADDING * 2, y * cellHeight + CELL_PADDING * 2,
                            cellWidth - CELL_PADDING * 4, cellHeight - CELL_PADDING * 4);
//                    g.fillOval(x * cellWidth + CELL_PADDING, y * cellHeight + CELL_PADDING,
//                            cellWidth - CELL_PADDING * 2, cellHeight - CELL_PADDING * 2);
                } else {
                    throw new RuntimeException(String.format("Can't repaint cell field[%d][%d]: %d", y, x, field[y][x]));
                }
            }
        }

        if (isGameOver){
            showGameOverMessage(g);
        }
    }

    private void showGameOverMessage(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(0, getHeight() / 2 - 50, getWidth(), 70);
        g.setColor(Color.ORANGE);
        g.setFont(new Font("Times New Roman", Font.BOLD, 48));
        switch (gameOverStatus){
            case DRAW:
                g.drawString(DRAW_MSG, 180, getHeight() / 2); break;
            case HUMAN_VICTORY:
                g.drawString(HUMAN_VICTORY_MSG, 70, getHeight() / 2); break;
            case AI_VICTORY:
                g.drawString(AI_VICTORY_MSG, 20, getHeight() / 2); break;
            default:
                throw new RuntimeException("Unexpected Game Over Status: " + gameOverStatus);
        }
    }

    private void update(MouseEvent e){
        if (!initMap) return;

        if (isGameOver) return;

        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;

        if (!isValidCell(cellX, cellY) || !isEmptyCell(cellX, cellY)) return;

        field[cellY][cellX] = HUMAN_CELL;
        if (checkWin(HUMAN_CELL)){
            setGameOver(HUMAN_VICTORY);
            return;
        }
        if (isFullMap()){
            setGameOver(DRAW);
            return;
        }

        aiTurn();
        repaint();
        if (checkWin(AI_CELL)){
            setGameOver(AI_VICTORY);
            return;
        }
        if (isFullMap()){
            setGameOver(DRAW);
            return;
        }
    }

    private void setGameOver(int gameOverStatus){
        isGameOver = true;
        this.gameOverStatus = gameOverStatus;
        repaint();
    }

    public void aiTurn() {
        if (turnAIWinCell()) {
            return;
        }

        if (turnHumanWinCell()) {
            return;
        }

        int x;
        int y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x, y));
        field[y][x] = AI_CELL;
    }

    private boolean turnAIWinCell() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (isEmptyCell(j, i)) {
                    field[i][j] = AI_CELL;               // поставим нолик в каждую клетку поля по очереди
                    if (checkWin(AI_CELL)) {
                        return true;    // если мы выиграли, вернём истину, оставив нолик в выигрышной позиции
                    }
                    field[i][j] = EMPTY_CELL;            // если нет - вернём обратно пустоту в клетку и пойдём дальше
                }
            }
        }
        return false;
    }

    // Проверка, выиграет-ли игрок своим следующим ходом
    private boolean turnHumanWinCell() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (isEmptyCell(j, i)) {
                    field[i][j] = HUMAN_CELL;            // поставим крестик в каждую клетку по очереди
                    if (checkWin(HUMAN_CELL)) {            // если игрок победит
                        field[i][j] = AI_CELL;            // поставить на то место нолик
                        return true;
                    }
                    field[i][j] = EMPTY_CELL;            // в противном случае вернуть на место пустоту
                }
            }
        }
        return false;
    }

    // проверка на победу
    private boolean checkWin(int player) {
        for (int i = 0; i < fieldSizeX; i++) {            // ползём по всему полю
            for (int j = 0; j < fieldSizeY; j++) {
                if (checkLine(i, j, 1, 0, victoryCondition, player)) {
                    return true;    // проверим линию по х
                }
                if (checkLine(i, j, 1, 1, victoryCondition, player)) {
                    return true;    // проверим по диагонали х у
                }
                if (checkLine(i, j, 0, 1, victoryCondition, player)) {
                    return true;    // проверим линию по у
                }
                if (checkLine(i, j, 1, -1, victoryCondition, player)) {
                    return true;    // проверим по диагонали х -у
                }
            }
        }
        return false;
    }

    // проверка линии
    private boolean checkLine(int x, int y, int vx, int vy, int victoryCondition, int player) {
        final int farX = x + (victoryCondition - 1) * vx;            // посчитаем конец проверяемой линии
        final int farY = y + (victoryCondition - 1) * vy;
        if (!isValidCell(farX, farY)) {
            return false;    // проверим не выйдет-ли проверяемая линия за пределы поля
        }
        for (int i = 0; i < victoryCondition; i++) {                    // ползём по проверяемой линии
            if (field[y + i * vy][x + i * vx] != player) {
                return false;    // проверим одинаковые-ли символы в ячейках
            }
        }
        return true;
    }

    public boolean isFullMap() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == EMPTY_CELL) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    public boolean isEmptyCell(int x, int y) {
        return field[y][x] == EMPTY_CELL;
    }
}
