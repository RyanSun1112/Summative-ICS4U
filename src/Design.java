//Class contains methods for formatting/designing various aspects of the program so that these
//formatting methods don't need to be replicated for each individual class created

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public class Design {

    //INSTANCE VARIABLES
    private final static Color white = new Color(255,255,255);
    private final static Color darkBlue = new Color(31, 81, 81);
    private final static Color darkerBlue = new Color(122, 138, 153);
    private final static Color lighterBlue = new Color(238,238,238);



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
        button.setForeground(white);
        String stuff = button.getText();
        button.setText("<HTML><p style='text-align:center;'>"+stuff+"</p></HTML>");
    }


    //LABEL FORMATTING METHOD
    public static void formatLabel(JLabel label, int size) {
        label.setFont(theNormalFont(size));
        label.setFocusable(false);
        label.setBorder(BorderFactory.createEmptyBorder());
        label.setForeground(white);
        String stuff = label.getText();
        label.setText("<HTML><p style='text-align:center;'>"+stuff+"</p></HTML>");
    }


    //TEXT FIELD FORMATTING METHOD
    public static void formatTextPane(JTextPane textFieldName) {
        textFieldName.setOpaque(false);
        textFieldName.setBorder(new EmptyBorder(0, 0,0,0));
        textFieldName.setBounds(635,330,420,100);
        textFieldName.setFont(Design.theNormalFont(24));
    }


    //COMBO BOX FORMATTING METHOD
    public static void formatComboBox(JComboBox comboBoxName) {
        comboBoxName.setBackground(Color.white);
        comboBoxName.setForeground(Color.BLACK);
        comboBoxName.setFont(Design.theNormalFont(16));
        comboBoxName.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                return new BasicComboPopup(comboBoxName) {
                    @Override
                    protected JScrollPane createScroller() {
                        JScrollPane scroller = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                        Design.formatScrollBar(scroller);
                        return scroller;
                    }
                };
            }
        });
    }


    //SCROLLBAR FORMATTING METHOD
    public static void formatScrollBar(JScrollPane scrollPane) {
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setBackground(lighterBlue);

        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = darkerBlue;
            }
            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = super.createDecreaseButton(orientation);
                button.setFocusable(false);
                button.setBorder(BorderFactory.createEmptyBorder());
                button.setBackground(lighterBlue);
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);
                button.setBorder(BorderFactory.createEmptyBorder());
                return button;
            }
            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = super.createIncreaseButton(orientation);
                button.setFocusable(false);
                button.setBorder(BorderFactory.createEmptyBorder());
                button.setBackground(lighterBlue);
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);
                button.setBorder(BorderFactory.createEmptyBorder());
                return button;
            }
        });
    }


    //BACKGROUND MAKING METHODS
    public static JLabel setBackgroundImage(String theURL) {
        Icon backgroundIcon=null;
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            URL url = classloader.getResource(theURL);
            Image image = ImageIO.read(url);
            image = image.getScaledInstance(1152,678,Image.SCALE_FAST);
            backgroundIcon = new ImageIcon(image);
        } catch(IOException e){
            System.out.println("error...");
        }
        JLabel background = new JLabel(backgroundIcon);
        background.setBounds(0,0,1152,648);
        return background;

    }

    public static JLabel setClearBackgroundImage(String theURL) {
        Icon backgroundIcon=null;
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            URL url = classloader.getResource(theURL);
            Image image = ImageIO.read(url);
            image = image.getScaledInstance(1037,610,Image.SCALE_SMOOTH);
            //scale smooth takes longer to load, however the produced background is higher quality
            backgroundIcon = new ImageIcon(image);
        } catch(IOException e){
            System.out.println("error...");
        }
        JLabel background = new JLabel(backgroundIcon);
        background.setBounds(-70,-20,1152,648);
        return background;

    }

    public static Image setScrollBackgroundImage(String theURL) {
        Image image=null;
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            URL url = classloader.getResource(theURL);
            image = ImageIO.read(url);
            image = image.getScaledInstance(864,1280,Image.SCALE_FAST);
        } catch(IOException e){
            System.out.println("error...");
        }
        return image;

    }


    //QUICK-MENU MAKING METHOD
    public static void QuickMenu1(JLayeredPane pane, JFrame frame) {

        JButton bce = new JButton("Chemical<BR>Equations!");
        bce.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BalancingChemicalEquations bce = new BalancingChemicalEquations();
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
                BalancingChemicalEquations bce = new BalancingChemicalEquations();
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
                BalancingChemicalEquations bce = new BalancingChemicalEquations();
                bce.bcePage();
            }
        });
        Design.formatButton(bce,18);
        bce.setBounds(345,20,155,120);
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
        moleModels.setBounds(480,20,155,120);
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
        moleRatios.setBounds(615,20,155,120);
        pane.add(moleRatios);

        JButton pTable = new JButton("Periodic<BR>Table!");
        pTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PeriodicTable pt = new PeriodicTable();
                pt.theElementsOfThePeriodicTable();
            }
        });
        Design.formatButton(pTable,18);
        pTable.setBounds(750,20,155,120);
        pane.add(pTable);
    }
}