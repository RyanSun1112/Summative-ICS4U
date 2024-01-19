import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


class Stoichiometry {

    //INSTANCE VARIABLES
    private final JFrame frame = new JFrame("Chemistry Galore! ~ Stoichiometry!");
    private final JComboBox chemicals = new JComboBox();
    private final JTextField inputArea = new JTextField();
    String minus = "";

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
        pane.add(back,JLayeredPane.PALETTE_LAYER);

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
        pane.add(next,JLayeredPane.PALETTE_LAYER);


        chemicals.setBounds(635,325,440,50);
        for (int i = 0; i < chemicals.getComponentCount(); i++)
        {
            if (chemicals.getComponent(i) instanceof JComponent) {
                ((JComponent) chemicals.getComponent(i)).setBorder(new EmptyBorder(0, 0,0,0));
            }
            if (chemicals.getComponent(i) instanceof AbstractButton) {
                ((AbstractButton) chemicals.getComponent(i)).setBorderPainted(false);
            }
        }
        chemicals.setBackground(Color.white);
        chemicals.setForeground(Color.BLACK);
        chemicals.setFont(Design.theNormalFont(12));
        inputArea.setOpaque(false);
        inputArea.setBorder(new EmptyBorder(0, 0,0,0));
        inputArea.setBounds(635,300,420,100);
        inputArea.setFont(Design.theNormalFont(20));
        pane.add(inputArea,JLayeredPane.POPUP_LAYER);
        JLabel background = Design.setBackgroundImage("ChemistryGalore!/ChemistryGalore_stoichiometry.png");
        pane.add(background,JLayeredPane.DEFAULT_LAYER);
        pane.add(chemicals, JLayeredPane.PALETTE_LAYER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.setSize(1152,678);

        frame.setVisible(true);
        inputArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                System.out.print(inputArea.getText());
                chemicals.removeAllItems();
                if(inputArea.getText().isEmpty()){
                    chemicals.setPopupVisible(false);
                }else{

                    chemicals.setPopupVisible(true);
                    String url = null;
                    boolean yes = false;
                    String ll = inputArea.getText();
                    if(inputArea.getText().length() == 1|| (Character.isDigit(ll.charAt(1)) && ll.length()==2)){
                        url =  "https://webbook.nist.gov/cgi/cbook.cgi?Formula="+inputArea.getText()+"&AllowExtra=on&NoIon=on&Units=SI";
                    }else{

                        url =  "https://webbook.nist.gov/cgi/cbook.cgi?Formula="+inputArea.getText()+"&NoIon=on&Units=SI";

                    }
                    String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";
                    Document doc = null;
                    try {
                        doc = Jsoup.connect(url).get();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    int counter = 0;
                    Elements img = doc.getElementsByTag("li");
                    for (Element el : img) {
                        if(counter == 0){
                            chemicals.addItem(" ");
                            counter++;
                            continue;
                        }
                        if(chemicals.getItemAt(chemicals.getItemCount()-1) != " "){
                            Object item = chemicals.getItemAt(chemicals.getItemCount()-1);
                            String item2 = String.valueOf(item);
                            String[] parts = item2.split(" ");
                            if(el.text().contains(parts[0])){
                                continue;
                            }
                        }
                        if(counter < 18){
                            counter++;
                            continue;
                        }

                        if(el.text().equals("Disclaimer (Note: This site is covered by copyright.)")||el.text().contains("IUPAC")){
                            chemicals.addItem("Nothing found");
                            break;
                        }
                        if(counter == 22|| el.text().equals("Privacy Statement")){
                            break;
                        }
                        chemicals.addItem(el.text());
                        counter++;

                    }
                }

            }
        });
        chemicals.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                if(String.valueOf(e.getSource()).equals("javax.swing.JComboBox[,635,325,440x50,layout=javax.swing.plaf.metal.MetalComboBoxUI$MetalComboBoxLayoutManager,alignmentX=0.0,alignmentY=0.0,border=,flags=328,maximumSize=,minimumSize=,preferredSize=,isEditable=false,lightWeightPopupEnabled=true,maximumRowCount=8,selectedItemReminder="+chemicals.getSelectedItem()+"]")){
                    String cool = String.valueOf(chemicals.getSelectedItem());
                    String[] arrOfStr = cool.split("\\(");
                    String[] arrOfStr2 = arrOfStr[1].split("\\)");

                    String output = arrOfStr2[0];
                    if(output.length()==1) {
                        inputArea.setText(output + "+ ");
                    }else{
                        String actual = "<html>";
                        for(int i = 0; i < output.length(); i++){
                            if(Character.isDigit(output.charAt(i))){
                                actual += "<sub>" + output.charAt(i) + "</sub>";
                            }else{
                                actual += output.charAt(i);
                            }
                        }

                        actual +=" + </html>";
                        System.out.print(actual);
                        inputArea.setText(actual);
                    }


                    chemicals.removeAllItems();

                }
            }
        });
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