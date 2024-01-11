import javax.swing.*;
import java.awt.*;
import java.io.File;

class homePage {
    //INSTANCE VARIABLES
    private final JFrame frame = new JFrame("Chemistry Galore!");


    //HOME PAGE METHOD
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
    //INSTANCE VARIABLES
    private final JFrame frame = new JFrame("Chemistry Galore!~ Choose your adventure!");


    //CHOICES PAGE METHOD
    public void choicesPage() {
        JLayeredPane pane = new JLayeredPane();
        JLabel background = Design.setBackgroundImage("ChemistryGalore!/ChemistryGalore_optionsPage.png");
        pane.add(background,JLayeredPane.DEFAULT_LAYER);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.setSize(1152,678);
        frame.setVisible(true);
    }
}

class howToPlay {
    //INSTANCE VARIABLES
    private final JFrame frame = new JFrame("Chemistry Galore! ~ How to wield this scepter!");


    //INSTRUCTIONS PAGE METHOD
    public void howToPlayPage() {
        JLayeredPane pane = new JLayeredPane();
        JLabel background = Design.setBackgroundImage("ChemistryGalore!/ChemistryGalore_howToPlay.png");
        pane.add(background,JLayeredPane.DEFAULT_LAYER);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.setSize(1152,678);
        frame.setVisible(true);
    }
}