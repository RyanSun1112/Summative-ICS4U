import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PeriodicTable {

    //INSTANCE VARIABLES
    private final JFrame frame = new JFrame("Chemistry Galore! ~ Mendeleev's Masterpiece!");


    //PERIODIC TABLE METHOD
    public void theElementsOfThePeriodicTable() {

        JLayeredPane pane = new JLayeredPane();

        Design.QuickMenu3(pane,frame);

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
        back.setBounds(150,280,155,120);
        pane.add(back);

        JButton seeAllElements = new JButton("Can't see all the elements?<BR>Click HERE to go fullscreen!");
        seeAllElements.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                theZoomedElementsOfThePeriodicTable elements = new theZoomedElementsOfThePeriodicTable();
                elements.showZoomed();
            }
        });
        Design.formatButton(seeAllElements,25);
        seeAllElements.setBounds(380,175,455,120);
        pane.add(seeAllElements);

        JLabel background = Design.setClearBackgroundImage("ChemistryGalore!/ChemistryGalore_mendeleevMasterpiece.png");
        pane.add(background,JLayeredPane.DEFAULT_LAYER);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.add(pane);
        frame.setSize(1037,610);
        frame.setVisible(true);
    }
}

class theZoomedElementsOfThePeriodicTable {
    //INSTANCE VARIABLES
    private final JFrame frame = new JFrame("Chemistry Galore! ~ Mendeleev's Masterpiece!");


    //PERIODIC TABLE METHOD
    public void showZoomed() {

        JLayeredPane pane = new JLayeredPane();

        JButton previousPage = new JButton("Want to return to the previous<BR>page? Click HERE to exit fullscreen!");
        previousPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                PeriodicTable pt = new PeriodicTable();
                pt.theElementsOfThePeriodicTable();
            }
        });
        Design.formatButton(previousPage,25);
        previousPage.setBounds(200,60,455,120);
        pane.add(previousPage);

        JLabel background = Design.setClearBackgroundImage("ChemistryGalore!/ChemistryGalore_mendeleevMasterpieceBigEdition.png");
        pane.add(background, JLayeredPane.DEFAULT_LAYER);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.add(pane);
        frame.setSize(1037,610);
        frame.setVisible(true);
    }
}
