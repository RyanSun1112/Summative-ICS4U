import javax.swing.*;
import java.awt.*;
import java.io.File;

class homePage {
    //INSTANCE VARIABLES
    private JFrame frame = new JFrame("Chemistry Galore!");
    private Color white = new Color(255,255,255);

    //HOME PAGE METHOD :)
    public void start() {
        JLayeredPane pane = new JLayeredPane();
        JLabel background = Design.setBackgroundImage("ChemistryGalore!/ChemistryGalore_homePage.png");
        pane.add(background,JLayeredPane.DEFAULT_LAYER);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.setSize(1152,678);
        frame.setVisible(true);
    }
}

class choices {

}

class howToPlay {

}