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
                bce.bcePage();
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
                MoleculeInput cool = new MoleculeInput();
                cool.page();
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
        back.setBounds(280,295,155,120);
        pane.add(back);

        String theInstructions = "WELCOME, ONE AND ALL!" +
                "<BR>" +
                "<BR>Have you ever had to deal with thousand upon thousands of chemical balancing questions? What about" +
                "<BR>molar masses? Forget the elements of the Periodic Table? Well then, welcome to the world of Chemistry" +
                "<BR>Galore! There are three main features of this app. The first is balancing chemical equations..." +
                "<BR>" +
                "<BR>" +
                "<BR>BALANCING CHEMICAL EQUATIONS!!" +
                "<BR>" +
                "<BR>In this page, you'll find you can take one of two paths. Firstly, you can insert the unbalanced chemical" +
                "<BR>equation, as well as enter the type of reaction. When you click 'Next! >' the program will give you the" +
                "<BR>balanced chemical equation. If you don't know what the full balanced chemical equation is, then not to" +
                "<BR>worry! In the bottom left corner, there's an option to switch over to the second version of the page. The" +
                "<BR>second version is far simpler: you enter the reactants (and type of reaction), then we do the rest Not" +
                "<BR>too shabby, right?" +
                "<BR>" +
                "<BR>" +
                "<BR>MOLAR MASSES!!" +
                "<BR>" +
                "<BR>Have you ever casually wondered what the mass of products (stuff produced after a reaction) is, but you're" +
                "<BR>only given the mass of one of the reactants (things that react)? Well, it happens to the best of us. On" +
                "<BR>the Stoichiometry page (also called the Molar Masses page), after entering the chemical formula and the" +
                "<BR>mass of ONE of the reactants (and after specifying which reactant), we'll give you the masses of all the" +
                "<BR>products produced! Wouldn't you say that's neat?" +
                "<BR>" +
                "<BR>";

        JLabel instructions = new JLabel(theInstructions);
        Design.formatLabel(instructions,17);
        instructions.setBounds(150,300,900,3050);
        pane.add(instructions);

        JScrollPane scroll = new JScrollPane(pane);
        scroll.getVerticalScrollBar().setUnitIncrement(5);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setOpaque(false);

        JLabel background = Design.setScrollBackgroundImage("ChemistryGalore!/ChemistryGalore_howToPlay.png");
        pane.add(background,JLayeredPane.DEFAULT_LAYER);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.setSize(1152,678);
        frame.setVisible(true);
    }
}