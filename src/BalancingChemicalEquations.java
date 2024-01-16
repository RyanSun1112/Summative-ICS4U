import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class BalancingChemicalEquations_V1 {

    //INSTANCE VARIABLES
    private final JFrame frame = new JFrame("Chemistry Galore! ~ Balancing Chemical Equations!");


    //BALANCING CHEMICAL EQUATIONS METHOD (1)
    public void bcePage() {

        JLayeredPane pane = new JLayeredPane();

        Design.QuickMenu1(pane,frame);

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
        back.setBounds(620,465,155,120);
        pane.add(back);

        JButton next = new JButton("Next!");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BalancingChemicalEquations_Answer answer = new BalancingChemicalEquations_Answer();
                answer.bceAnswerPage();
            }
        });
        Design.formatButton(next,30);
        next.setBounds(800,465,155,120);
        pane.add(next);

        JButton noFormula = new JButton("Don't know what the formula is?" +
                "<BR>That's fine, click HERE to enter just<BR>the reactants and type of reaction," +
                "<BR>then we'll do the rest!");
        noFormula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BalancingChemicalEquations_V2 answer = new BalancingChemicalEquations_V2();
                answer.bcePage();
            }
        });
        Design.formatButton(noFormula,20);
        noFormula.setBounds(60,345,455,320);
        pane.add(noFormula);

        JTextField unbalanced = new JTextField();

        JLabel background = Design.setBackgroundImage("ChemistryGalore!/ChemistryGalore_BCE1.png");
        pane.add(background,JLayeredPane.DEFAULT_LAYER);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.setSize(1152,678);
        frame.setVisible(true);
    }
}



class BalancingChemicalEquations_V2 {

    //INSTANCE VARIABLES
    private final JFrame frame = new JFrame("Chemistry Galore! ~ Balancing Chemical Equations!");


    //BALANCING CHEMICAL EQUATIONS METHOD (2)
    public void bcePage() {

        JLayeredPane pane = new JLayeredPane();

        Design.QuickMenu1(pane,frame);


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
        back.setBounds(620,465,155,120);
        pane.add(back);

        JButton next = new JButton("Next!");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BalancingChemicalEquations_Answer answer = new BalancingChemicalEquations_Answer();
                answer.bceAnswerPage();
            }
        });
        Design.formatButton(next,30);
        next.setBounds(800,465,155,120);
        pane.add(next);

        JButton withFormula = new JButton("Want to enter the unbalanced" +
                "<BR>formula instead? Click HERE to enter<BR>the entire chemical equation and," +
                "<BR>type of reaction!");
        withFormula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BalancingChemicalEquations_V1 answer = new BalancingChemicalEquations_V1();
                answer.bcePage();
            }
        });
        Design.formatButton(withFormula,20);
        withFormula.setBounds(60,345,455,320);
        pane.add(withFormula);

        JLabel background = Design.setBackgroundImage("ChemistryGalore!/ChemistryGalore_BCE2.png");
        pane.add(background,JLayeredPane.DEFAULT_LAYER);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.setSize(1152,678);
        frame.setVisible(true);
    }
}



class BalancingChemicalEquations_Answer {

    //INSTANCE VARIABLES
    private final JFrame frame = new JFrame("Chemistry Galore! ~ Balancing Chemical Equations!");


    //BALANCING CHEMICAL EQUATIONS ANSWER METHOD
    public void bceAnswerPage() {

        JLayeredPane pane = new JLayeredPane();

        Design.QuickMenu1(pane,frame);

        JButton back = new JButton("Back?");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BalancingChemicalEquations_V1 bce = new BalancingChemicalEquations_V1();
                bce.bcePage();
            }
        });
        Design.formatButton(back,30);
        back.setBounds(510,425,155,120);
        pane.add(back);

        JButton understand = new JButton("Don't quite understand why? We've got your back! Click HERE for an explanation!");
        understand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BalancingChemicalEquations_Explained see = new BalancingChemicalEquations_Explained();
                see.bcePage();
            }
        });
        Design.formatButton(understand,20);
        understand.setBounds(140,510,900,120);
        pane.add(understand);

        JLabel background = Design.setBackgroundImage("ChemistryGalore!/ChemistryGalore_BCEdrumroll.png");
        pane.add(background,JLayeredPane.DEFAULT_LAYER);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.setSize(1152,678);
        frame.setVisible(true);
    }
}



class BalancingChemicalEquations_Explained {

    //INSTANCE VARIABLES
    private final JFrame frame = new JFrame("Chemistry Galore! ~ Balancing Chemical Equations!");


    //BALANCING CHEMICAL EXPLANATION METHOD
    public void bcePage() {

        JLayeredPane pane = new JLayeredPane();

        Design.QuickMenu1(pane,frame);

        JButton back = new JButton("Back?");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BalancingChemicalEquations_V1 bce = new BalancingChemicalEquations_V1();
                bce.bcePage();
            }
        });
        Design.formatButton(back,30);
        back.setBounds(535,190,155,120);
        pane.add(back);

        JLabel background = Design.setBackgroundImage("ChemistryGalore!/ChemistryGalore_BCEexplained.png");
        pane.add(background,JLayeredPane.DEFAULT_LAYER);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.setSize(1152,678);
        frame.setVisible(true);
    }
}
