import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


class HomePage {

    //INSTANCE VARIABLES
    private final JFrame frame = new JFrame("Chemistry Galore!");


    //HOME PAGE METHOD :)
    public void start() {

        JLayeredPane pane = new JLayeredPane();

        JButton instructions = new JButton("Not sure how to use this app? Click HERE!");
        instructions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                HowToPlay playTheGame = new HowToPlay();
                playTheGame.howToPlayPage();
            }
        });

        Design.formatButton(instructions,20);
        instructions.setBounds(85,525,400,80);
        pane.add(instructions);

        JLabel background = Design.setBackgroundImage("ChemistryGalore!/ChemistryGalore_homePage.png");
        pane.add(background,JLayeredPane.DEFAULT_LAYER);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.setSize(1152,678);
        frame.setVisible(true);
    }
}



class Choices {

    //INSTANCE VARIABLES
    private final JFrame frame = new JFrame("Chemistry Galore!~ Choose your adventure!");


    //CHOICES PAGE METHOD
    public void chooseYourAdventure() {
        JLayeredPane pane = new JLayeredPane();
        JLabel background = Design.setBackgroundImage("ChemistryGalore!/ChemistryGalore_optionsPage.png");
        pane.add(background,JLayeredPane.DEFAULT_LAYER);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.setSize(1152,678);
        frame.setVisible(true);
    }
}



class HowToPlay {

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