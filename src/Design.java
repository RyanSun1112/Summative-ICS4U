//Class contains methods for formatting/designing various aspects of the program so that these
//formatting methods don't need to be replicated for each individual class created

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public class Design {

    //INSTANCE VARIABLES
    private static Color white = new Color(255,255,255);


    //FONT FORMATTING METHOD
    public static Font theNormalFont(int size)  {

        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource("CaviarDreams/CaviarDreams.ttf");
        File fontFile = null;
        try {
            fontFile = new File(url.toURI());
        } catch(Exception e) {
            System.out.println("Something");
        }

        Font caviarDreams = null;

        try {
            caviarDreams = Font.createFont(Font.TRUETYPE_FONT,fontFile).deriveFont(Font.BOLD,size);
            graphics.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontFile));
            return caviarDreams;
        } catch (IOException | FontFormatException e) {
            System.out.println("ERROR! Code in 'e.printStackTrace()' to print stack trace: ");
        }

        return caviarDreams;
    }


    //BUTTON FORMATTING METHOD
    public static void formatButton(JButton button, int size) {
        button.setFont(theNormalFont(size));
        button.setFocusable(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        //button.setBackground(Color.black); //to check the boundaries of the button while formatting
        button.setForeground(white);
        String stuff = button.getText();
        button.setText("<HTML><p style='text-align:center;'>"+stuff+"</p></HTML>");
    }


    //LABEL FORMATTING METHOD
    private void formatLabel(JLabel button) {
        button.setFont(theNormalFont(35));
        button.setFocusable(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setForeground(white);

    }


    //BACKGROUND MAKING METHOD
    public static JLabel setBackgroundImage(String theURL) {
        Icon backgroundIcon=null;
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            URL url = classloader.getResource(theURL);
            Image image = ImageIO.read(url);
            image = image.getScaledInstance(1152,678,Image.SCALE_SMOOTH);
            backgroundIcon = new ImageIcon(image);
        } catch(IOException e){
            System.out.println("error...");
        }
        JLabel background = new JLabel(backgroundIcon);
        background.setBounds(0,0,1152,648);
        return background;

    }


    //QUICK-MENU MAKING METHOD
    public static void QuickMenu1(JLayeredPane pane, JFrame frame) {

        JButton bce = new JButton("Chemical<BR>Equations!");
        bce.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BalancingChemicalEquations_V1 bce = new BalancingChemicalEquations_V1();
                bce.bcePage();
            }
        });
        Design.formatButton(bce,18);
        bce.setBounds(400,45,155,120);
        pane.add(bce);

        JButton moleModels = new JButton("Molecular<BR>Models!");
        moleModels.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                MoleculeInput cool = new MoleculeInput();
                cool.page();
            }
        });
        Design.formatButton(moleModels,18);
        moleModels.setBounds(555,45,155,120);
        pane.add(moleModels);

        JButton moleRatios = new JButton("Molar<BR>Ratios!");
        moleRatios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Stoichiometry ratios = new Stoichiometry();
                ratios.questionPage();
            }
        });
        Design.formatButton(moleRatios,18);
        moleRatios.setBounds(705,45,155,120);
        pane.add(moleRatios);

        JButton pTable = new JButton("Periodic<BR>Table!");
        pTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                PeriodicTable pt = new PeriodicTable();
                pt.theElementsOfThePeriodicTable();
            }
        });
        Design.formatButton(pTable,18);
        pTable.setBounds(855,45,155,120);
        pane.add(pTable);
    }


    public static void QuickMenu2(JLayeredPane pane, JFrame frame) {

        JButton bce = new JButton("Balancing<BR>Chemical<BR>Equations!");
        bce.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BalancingChemicalEquations_V1 bce = new BalancingChemicalEquations_V1();
                bce.bcePage();
            }
        });
        Design.formatButton(bce,20);
        bce.setBounds(380,75,155,120);
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
        Design.formatButton(moleModels,20);
        moleModels.setBounds(564,75,155,120);
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
        Design.formatButton(moleRatios,20);
        moleRatios.setBounds(748,75,155,120);
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
        Design.formatButton(pTable,20);
        pTable.setBounds(932,75,155,120);
        pane.add(pTable);
    }

    public static void QuickMenu3(JLayeredPane pane, JFrame frame) {

        JButton bce = new JButton("Chemical<BR>Equations!");
        bce.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BalancingChemicalEquations_V1 bce = new BalancingChemicalEquations_V1();
                bce.bcePage();
            }
        });
        Design.formatButton(bce,18);
        bce.setBounds(400,15,155,120);
        pane.add(bce);

        JButton moleModels = new JButton("Molecular<BR>Models!");
        moleModels.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                MoleculeInput cool = new MoleculeInput();
                cool.page();
            }
        });
        Design.formatButton(moleModels,18);
        moleModels.setBounds(555,15,155,120);
        pane.add(moleModels);

        JButton moleRatios = new JButton("Molar<BR>Ratios!");
        moleRatios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Stoichiometry ratios = new Stoichiometry();
                ratios.questionPage();
            }
        });
        Design.formatButton(moleRatios,18);
        moleRatios.setBounds(705,15,155,120);
        pane.add(moleRatios);

        JButton pTable = new JButton("Periodic<BR>Table!");
        pTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                PeriodicTable pt = new PeriodicTable();
                pt.theElementsOfThePeriodicTable();
            }
        });
        Design.formatButton(pTable,18);
        pTable.setBounds(855,15,155,120);
        pane.add(pTable);
    }
}