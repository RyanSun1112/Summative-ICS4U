import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Error {
    private final JFrame frame = new JFrame("Chemistry Galore! ~ ERRORS GALORE!!!");


    //END PAGE METHOD :0
    public void endIt() {

        JLayeredPane pane = new JLayeredPane();

        Design.QuickMenu2(pane,frame);

        JButton tryAgain = new JButton("It seems something went wrong! The app has suffered complete combustion! Perhaps an" +
                "<BR>incorrect value, or too much data was inputted? Give it another whirl and START AGAIN!");
        tryAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                HomePage homePage = new HomePage();
                homePage.start();
            }
        });

        Design.formatButton(tryAgain,20);
        tryAgain.setBounds(105,475,950,80);
        pane.add(tryAgain);

        JLabel background = Design.setBackgroundImage("ChemistryGalore!/ChemistryGalore_yikesPage.png");
        pane.add(background,JLayeredPane.DEFAULT_LAYER);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.setSize(1152,678);
        frame.setVisible(true);
    }
}
