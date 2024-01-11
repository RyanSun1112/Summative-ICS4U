import javax.swing.*;

public class PeriodicTable {

    //INSTANCE VARIABLES
    private final JFrame frame = new JFrame("Chemistry Galore!~ Mendeleev's Masterpiece!");


    //STOICHIOMETRY METHOD
    public void theElementsOfThePeriodicTable() {
        JLayeredPane pane = new JLayeredPane();
        JLabel background = Design.setBackgroundImage("ChemistryGalore!/ChemistryGalore_mendeleevsMasterpiece.png");
        pane.add(background,JLayeredPane.DEFAULT_LAYER);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.setSize(1152,678);
        frame.setVisible(true);
    }
}
