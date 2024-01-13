import javax.swing.*;


class BalancingChemicalEquations_V1 {

    //INSTANCE VARIABLES
    private final JFrame frame = new JFrame("Chemistry Galore! ~ Balancing Chemical Equations!");


    //BALANCING CHEMICAL EQUATIONS METHOD (1)
    public void bcePage() {

        JLayeredPane pane = new JLayeredPane();

        Design.QuickMenu1(pane,frame);

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
    public void bcePage() {

        JLayeredPane pane = new JLayeredPane();

        Design.QuickMenu1(pane,frame);

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

        JLabel background = Design.setBackgroundImage("ChemistryGalore!/ChemistryGalore_BCEexplained.png");
        pane.add(background,JLayeredPane.DEFAULT_LAYER);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.setSize(1152,678);
        frame.setVisible(true);
    }
}
