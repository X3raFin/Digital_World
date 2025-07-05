package world;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import organism.Organism;

public class MainWindow implements ActionListener {

    public MainWindow(int N, World w) {
        this.w = w;
        size = N;
        window = new JFrame();
        window.setTitle("Kacper Jankowski 200795");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 800);
        window.setLocationRelativeTo(null);

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        makeRound = new JButton("Next round");
        makeRound.setFocusable(false);
        makeRound.addActionListener(this);

        saveToFile = new JButton("Save to file");
        saveToFile.setFocusable(false);
        saveToFile.addActionListener(this);

        loadFromFile = new JButton("Load last backup");
        loadFromFile.setFocusable(false);
        loadFromFile.addActionListener(this);

        cells = new JPanel[size][size];
        world = new JPanel(new GridLayout(size, size));
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                JPanel cell = new JPanel();
                cell.setBackground(Color.WHITE);
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cells[row][col] = cell;
                world.add(cell);
            }
        }

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setPreferredSize(new Dimension(1000, 200));

        window.add(scrollPane, BorderLayout.SOUTH);
        window.add(world, BorderLayout.CENTER);
        buttonsPanel.add(makeRound);
        buttonsPanel.add(saveToFile);
        buttonsPanel.add(loadFromFile);
        window.add(buttonsPanel, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == makeRound)
            w.makeRound();
        else if (source == saveToFile) {
            World.saveToFile("backup.txt", w);
            messageAppend("Kopia swiata zapisana w backup.txt. \n");
        } else {
            disable();
            World.readFromFile("backup.txt");
        }
    }

    public void show() {
        window.setVisible(true);
    }

    public void disable() {
        window.setVisible(false);
    }

    public void drawWorld(ArrayList<Organism> organisms) {
        for (Organism org : organisms) {
            cells[org.getPosition().getX()][org.getPosition().getY()].setBackground(org.draw());
        }
    }

    public void cleanWorld() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                cells[row][col].setBackground(Color.white);
            }
        }
    }

    public void messageAppend(String message) {
        messageArea.append(message);
    }

    private JFrame window;
    private JPanel buttonsPanel;
    private JPanel world;
    private JPanel[][] cells;
    private JButton makeRound;
    private JButton saveToFile;
    private JButton loadFromFile;
    private JTextArea messageArea;
    private Integer size;
    private World w;
}
