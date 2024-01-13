import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class Stoichiometry {

    //INSTANCE VARIABLES
    private final JFrame frame = new JFrame("Chemistry Galore! ~ Stoichiometry!");


    //STOICHIOMETRY METHOD
    public void questionPage() {

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
        back.setBounds(165,420,155,120);
        pane.add(back);

        JButton next = new JButton("Next!");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Stoichiometry_Answer answer = new Stoichiometry_Answer();
                answer.stoichiometryAnswer();
            }
        });
        Design.formatButton(next,30);
        next.setBounds(340,420,155,120);
        pane.add(next);

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
    private final JFrame frame = new JFrame("Chemistry Galore! ~ Stoichiometry!");


    //STOICHIOMETRY METHOD
    public void stoichiometryAnswer() {

        JLayeredPane pane = new JLayeredPane();

        Design.QuickMenu1(pane,frame);

        JButton back = new JButton("Back?");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Stoichiometry moles = new Stoichiometry();
                moles.questionPage();
            }
        });
        Design.formatButton(back,30);
        back.setBounds(310,410,155,120);
        pane.add(back);

        JButton understand = new JButton("Don't quite understand why? We've got your back! Click HERE for an explanation!");
        understand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Stoichiometry_Explained see = new Stoichiometry_Explained();
                see.StoichiometryExplanation();
            }
        });
        Design.formatButton(understand,20);
        understand.setBounds(140,510,900,120);
        pane.add(understand);

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
    private final JFrame frame = new JFrame("Chemistry Galore! ~ Stoichiometry!");


    //STOICHIOMETRY METHOD
    public void StoichiometryExplanation() {

        JLayeredPane pane = new JLayeredPane();

        Design.QuickMenu1(pane,frame);

        JButton back = new JButton("Back?");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Stoichiometry moles = new Stoichiometry();
                moles.questionPage();
            }
        });
        Design.formatButton(back,30);
        back.setBounds(535,190,155,120);
        pane.add(back);

        JLabel background = Design.setBackgroundImage("ChemistryGalore!/ChemistryGalore_stoichiometryExplained.png");
        pane.add(background,JLayeredPane.DEFAULT_LAYER);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.setSize(1152,678);
        frame.setVisible(true);
    }
}