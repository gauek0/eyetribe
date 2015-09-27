
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author roy
 */
public class ChangingButton extends JButton {

    private final int[][] fModel;
    private final int fX;
    private final int fY;

    public ChangingButton(final int x, final int y, final int[][] model) {
        fX = x;
        fY = y;
        fModel = model;

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fModel[fX][fY] = fModel[fX][fY] == 2 ? 0 : 2;
                updateNameFromModel();
            }
        });
        updateNameFromModel();
    }

    private void updateNameFromModel() {
        setText(String.valueOf(fModel[fX][fY]));
    }

    public static void main(String[] args) {

        int dim = 3;
        int matrix[][] = new int[3][3];

        JFrame f = new JFrame("Window containing a matrix");
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(dim, dim));

        for (int r = 0; r < dim; r++) {
            for (int c = 0; c < dim; c++) {
                ChangingButton button = new ChangingButton(r, c, matrix);
                p.add(button);
            }
        }
        f.add(p);
        f.pack();
        f.setVisible(true);

    }

}
