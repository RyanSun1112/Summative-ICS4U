import javax.swing.*;


class Stoichiometry {
    //INSTANCE VARIABLES
    private final JFrame frame = new JFrame("Chemistry Galore!~ Stoichiometry!");


    //STOICHIOMETRY METHOD
    public void questionPage() {
        JLayeredPane pane = new JLayeredPane();
        JLabel background = Design.setBackgroundImage("ChemistryGalore!/ChemistryGalore_stoichiometry.png");
        pane.add(background,JLayeredPane.DEFAULT_LAYER);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.setSize(1152,678);
        frame.setVisible(true);
    }
}



class Stoichiometry_Answer {
    //INSTANCE VARIABLES
    private final JFrame frame = new JFrame("Chemistry Galore!~ Stoichiometry!");


    //STOICHIOMETRY METHOD
    public void stoichiometryAnswer() {
        JLayeredPane pane = new JLayeredPane();
        JLabel background = Design.setBackgroundImage("ChemistryGalore!/ChemistryGalore_stoichiometryMasses.png");
        pane.add(background,JLayeredPane.DEFAULT_LAYER);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.setSize(1152,678);
        frame.setVisible(true);
    }
}



class Stoichiometry_Explained {
    //INSTANCE VARIABLES
    private final JFrame frame = new JFrame("Chemistry Galore!~ Stoichiometry!");


    //STOICHIOMETRY METHOD
    public void StoichiometryExplanation() {
        JLayeredPane pane = new JLayeredPane();
        JLabel background = Design.setBackgroundImage("ChemistryGalore!/ChemistryGalore_stoichiometryExplained.png");
        pane.add(background,JLayeredPane.DEFAULT_LAYER);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.setSize(1152,678);
        frame.setVisible(true);
    }
}