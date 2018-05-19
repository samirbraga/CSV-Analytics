import javax.swing.JPanel;
import java.awt.*;
import java.io.*;
import java.awt.Graphics;

class CustomBGPanel extends JPanel {
    private static final long serialVersionUID = 2L; // unique id
    Image bg = null;

    public void setImageBG(Image img) {
        bg = img;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, null);
        g.drawImage(bg, 0, 0, null);
    }
}