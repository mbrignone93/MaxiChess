/*
 *  Desarrollo por Maximiliano Brignone © 2021
 *  https://github.com/mbrignone93
 *
 * */

package com.chess.gui;

import com.chess.engine.Alliance;
import com.chess.engine.player.Player;
import com.chess.gui.Table.PlayerType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GameSetup extends JDialog {

    private PlayerType whitePlayerType;
    private PlayerType blackPlayerType;
    private JSpinner searchDepthSpinner;

    private static final String HUMAN_TEXT = "Humano";
    private static final String COMPUTER_TEXT = "Computadora";

    GameSetup(final JFrame frame,
              final boolean modal) {
        super(frame, modal);
        final JPanel myPanel = new JPanel(new GridLayout(0, 1));
        final JRadioButton whiteHumanButton = new JRadioButton(HUMAN_TEXT);
        final JRadioButton whiteComputerButton = new JRadioButton(COMPUTER_TEXT);
        final JRadioButton blackHumanButton = new JRadioButton(HUMAN_TEXT);
        final JRadioButton blackComputerButton = new JRadioButton(COMPUTER_TEXT);
        whiteHumanButton.setActionCommand(HUMAN_TEXT);
        final ButtonGroup whiteGroup = new ButtonGroup();
        whiteGroup.add(whiteHumanButton);
        whiteGroup.add(whiteComputerButton);
        whiteHumanButton.setSelected(true);

        final ButtonGroup blackGroup = new ButtonGroup();
        blackGroup.add(blackHumanButton);
        blackGroup.add(blackComputerButton);
        blackHumanButton.setSelected(true);

        getContentPane().add(myPanel);
        myPanel.add(new JLabel("Blancas"));
        myPanel.add(whiteHumanButton);
        myPanel.add(whiteComputerButton);
        myPanel.add(new JLabel("Negras"));
        myPanel.add(blackHumanButton);
        myPanel.add(blackComputerButton);

        this.searchDepthSpinner = addLabeledSpinner(myPanel, "Profundidad", new SpinnerNumberModel(6, 0, Integer.MAX_VALUE, 1));

        final JButton cancelButton = new JButton("Cancelar");
        final JButton okButton = new JButton("Guardar");

        okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                
				int depthValue = (Integer) searchDepthSpinner.getValue();
				
            	if (depthValue >= 1) 
            	{
            		whitePlayerType = whiteComputerButton.isSelected() ? PlayerType.COMPUTER : PlayerType.HUMAN;
                    blackPlayerType = blackComputerButton.isSelected() ? PlayerType.COMPUTER : PlayerType.HUMAN;
                    GameSetup.this.setVisible(false);
            	}
            	else
            		JOptionPane.showMessageDialog(null, "La Profundidad debe Ser Mayor o Igual a 1", "Error!", JOptionPane.ERROR_MESSAGE, new ImageIcon("assets/icon/icon.png"));
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cancelar");
                GameSetup.this.setVisible(false);
            }
        });

        myPanel.add(cancelButton);
        myPanel.add(okButton);
        pack();
        setTitle("Configurar Partida");
        Image im = Toolkit.getDefaultToolkit().getImage("assets/icon/icon.png");
		setIconImage(im);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(false);
    }

    void promptUser() {
        setVisible(true);
        repaint();
    }

    boolean isAIPlayer(final Player player) {
        if(player.getAlliance() == Alliance.WHITE) {
            return getWhitePlayerType() == PlayerType.COMPUTER;
        }
        return getBlackPlayerType() == PlayerType.COMPUTER;
    }

    PlayerType getWhitePlayerType() {
        return this.whitePlayerType;
    }

    PlayerType getBlackPlayerType() {
        return this.blackPlayerType;
    }

    private static JSpinner addLabeledSpinner(final Container c,
                                              final String label,
                                              final SpinnerModel model) {
        final JLabel l = new JLabel(label);
        c.add(l);
        final JSpinner spinner = new JSpinner(model);
        l.setLabelFor(spinner);
        c.add(spinner);
        return spinner;
    }

    int getSearchDepth() {
        return (Integer)this.searchDepthSpinner.getValue();
    }
}
