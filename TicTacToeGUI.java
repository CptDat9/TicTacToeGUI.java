import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private static final int BOARD_SIZE = 3;
    private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
    private char currentPlayerSymbol = 'X';

    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        buttonClicked.setText(String.valueOf(currentPlayerSymbol));
        buttonClicked.setEnabled(false);
        if (isGameOver()) {
            JOptionPane.showMessageDialog(this, "Game Over! Player " + currentPlayerSymbol + " wins!");
            resetBoard();
        } else {
            currentPlayerSymbol = (currentPlayerSymbol == 'X') ? 'O' : 'X';
        }
    }

    private boolean isGameOver() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayerSymbol)) &&
                buttons[i][1].getText().equals(String.valueOf(currentPlayerSymbol)) &&
                buttons[i][2].getText().equals(String.valueOf(currentPlayerSymbol))) {
                return true;
            }
        }
        for (int j = 0; j < BOARD_SIZE; j++) {
            if (buttons[0][j].getText().equals(String.valueOf(currentPlayerSymbol)) &&
                buttons[1][j].getText().equals(String.valueOf(currentPlayerSymbol)) &&
                buttons[2][j].getText().equals(String.valueOf(currentPlayerSymbol))) {
                return true;
            }
        }
        if (buttons[0][0].getText().equals(String.valueOf(currentPlayerSymbol)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayerSymbol)) &&
            buttons[2][2].getText().equals(String.valueOf(currentPlayerSymbol))) {
            return true;
        }
        if (buttons[0][2].getText().equals(String.valueOf(currentPlayerSymbol)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayerSymbol)) &&
            buttons[2][0].getText().equals(String.valueOf(currentPlayerSymbol))) {
            return true;
        }
        return false;
    }

    private void resetBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        currentPlayerSymbol = 'X';
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToeGUI game = new TicTacToeGUI();
            game.setVisible(true);
        });
    }
}
