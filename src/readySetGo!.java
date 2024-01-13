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
        instructions.setBounds(85,555,400,20);
        pane.add(instructions);

        JButton masterpiece = new JButton("Mendeleev's<BR>Masterpiece!");
        masterpiece.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                PeriodicTable pt = new PeriodicTable();
                pt.theElementsOfThePeriodicTable();
            }
        });
        Design.formatButton(masterpiece,30);
        masterpiece.setBounds(740,265,240,120);
        pane.add(masterpiece);

        Design.QuickMenu2(pane,frame);

        JButton check = new JButton("Check out<BR>the options!");
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Choices takeAPick = new Choices();
                takeAPick.chooseYourAdventure();
            }
        });

        Design.formatButton(check,30);
        check.setBounds(745,445,240,120);
        pane.add(check);

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

        JButton back = new JButton("Back?");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                HomePage home = new HomePage();
                home.start();
            }
        });
        Design.formatButton(back,30);
        back.setBounds(930,495,155,120);
        pane.add(back);

        JButton bce = new JButton("Balancing<BR>Chemical<BR>Equations!");
        bce.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BalancingChemicalEquations_V1 bce = new BalancingChemicalEquations_V1();
                bce.page();
            }
        });
        Design.formatButton(bce,27);
        bce.setBounds(90,285,155,120);
        pane.add(bce);

        JButton moleModels = new JButton("Create<BR>Molecular<BR>Models!");
        moleModels.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                //MoleculeInput cool = new MoleculeInput();
                //cool.page();
            }
        });
        Design.formatButton(moleModels,27);
        moleModels.setBounds(350,390,155,120);
        pane.add(moleModels);

        JButton moleRatios = new JButton("Play with<BR>Molar Ratios!");
        moleRatios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Stoichiometry ratios = new Stoichiometry();
                ratios.questionPage();
            }
        });
        Design.formatButton(moleRatios,27);
        moleRatios.setBounds(615,290,155,120);
        pane.add(moleRatios);

        JButton pTable = new JButton("The<BR>Periodic<BR>Table!");
        pTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                PeriodicTable pt = new PeriodicTable();
                pt.theElementsOfThePeriodicTable();
            }
        });
        Design.formatButton(pTable,27);
        pTable.setBounds(350,90,155,120);
        pane.add(pTable);

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

        Design.QuickMenu2(pane,frame);

        JButton back = new JButton("Back?");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                HomePage home = new HomePage();
                home.start();
            }
        });
        Design.formatButton(back,30);
        back.setBounds(280,315,155,120);
        pane.add(back);

        JLabel background = Design.setBackgroundImage("ChemistryGalore!/ChemistryGalore_howToPlay.png");
        pane.add(background,JLayeredPane.DEFAULT_LAYER);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.setSize(1152,678);
        frame.setVisible(true);
    }
}