import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;



class BalancingChemicalEquations_V1 {

    //INSTANCE VARIABLES
    private final JFrame frame = new JFrame("Chemistry Galore! ~ Balancing Chemical Equations!");
    private final JComboBox chemicals = new JComboBox();
    private final JTextPane inputArea = new JTextPane();
    boolean minus = false;
    boolean yes = false;
    String temp = "";
    boolean transfer = false;
    ArrayList<String> reactants = new ArrayList<String>();
    ArrayList<String> products = new ArrayList<String>();



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
        back.setBounds(700,405,155,120);
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
        next.setBounds(880,405,155,120);
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
        noFormula.setBounds(100,375,455,320);
        pane.add(noFormula);

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
        chemicals.setFont(Design.theNormalFont(16));
        inputArea.setOpaque(false);
        inputArea.setBorder(new EmptyBorder(0, 0,0,0));
        inputArea.setBounds(635,330,420,100);
        inputArea.setFont(Design.theNormalFont(24));

        pane.add(inputArea,JLayeredPane.POPUP_LAYER);
        JLabel background = Design.setBackgroundImage("ChemistryGalore!/ChemistryGalore_BCE1.png");
        pane.add(background,JLayeredPane.DEFAULT_LAYER);
        pane.add(chemicals, JLayeredPane.PALETTE_LAYER);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.setSize(1152,678);
        frame.setVisible(true);

        inputArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                temp = "";
                int counter1 = 0;

                if(transfer == false) {
                    if(minus == false){
                        temp = inputArea.getText();
                    }else if(reactants.isEmpty()){
                        String a = inputArea.getText().split("<body>")[1];
                        String b = a.split("</body>")[0];
                        String c = b.split("\n")[1];
                        temp = c.trim();
                    }else{
                        System.out.print(inputArea.getText());
                        boolean find = false;
                        for(int i = 0; i < inputArea.getText().length(); i++){

                            if(find == true){
                                if(inputArea.getText().charAt(i) == '\n'){
                                    break;
                                }
                                temp += inputArea.getText().charAt(i);
                            }
                            if(inputArea.getText().charAt(i) == '+'){
                                counter1++;
                                if (counter1 == reactants.size()) {
                                    find = true;
                                }
                            }
                        }
                        if(find == false && !reactants.isEmpty()){
                            String more = "";
                            boolean found = false;
                            for(int i = inputArea.getText().length()-1; i >= 0; i--){
                                if(inputArea.getText().charAt(i) == reactants.get(reactants.size()-1).charAt(reactants.get(reactants.size()-1).length()-1)){
                                    found = true;
                                }
                                if(found == true){
                                    more = inputArea.getText().charAt(i) + more ;
                                    if(inputArea.getText().charAt(i) == reactants.get(reactants.size()-1).charAt(0)){
                                        break;
                                    }
                                }
                            }
                            if(Character.isDigit(more.charAt(more.length()-1))){
                                more = " " + more + "</sub>";
                            }else{
                                more = " " + more;

                            }
                            reactants.remove(reactants.size()-1);
                            inputArea.setText(inputArea.getText().replace(more, ""));
                            temp = "";
                        }
                        System.out.print(temp);

                    }
                    chemicals.removeAllItems();
                    if(temp.isEmpty()){
                        if(reactants.isEmpty()){

                            chemicals.setPopupVisible(false);
                        }else{
                            chemicals.addItem(" ");
                            chemicals.addItem("->");
                            chemicals.setPopupVisible(true);

                        }
                    }else{
                        chemicals.setPopupVisible(true);
                        String url = null;
                        boolean yes = false;
                        String ll = temp;
                        if(temp.length() == 1|| (Character.isDigit(ll.charAt(1)) && ll.length()==2)||(Character.isUpperCase(ll.charAt(0)) && Character.isLowerCase(ll.charAt(1))&& ll.length()==2)){
                            url =  "https://webbook.nist.gov/cgi/cbook.cgi?Formula="+ll+"&AllowExtra=on&NoIon=on&Units=SI";
                        }else{

                            url =  "https://webbook.nist.gov/cgi/cbook.cgi?Formula="+ll+"&NoIon=on&Units=SI";

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
                            if(el.text().contains("Formula:")){
                                boolean breaker = false;
                                Elements dodo = doc.getElementsByTag("h1");
                                for (Element ell : dodo) {
                                    System.out.print(ell.id());
                                    if(ell.id().equals("Top")){
                                        String part = "";
                                        for(int i = 9; i < el.text().length(); i++){
                                            part += el.text().charAt(i);
                                        }
                                        chemicals.addItem(ell.text()+ "(" + part + ")");
                                        breaker = true;
                                        break;

                                    }
                                }
                                if(breaker == true){
                                    break;
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
                }else{
                    if(minus == false){
                        temp = inputArea.getText();
                    }else if(products.isEmpty()){
                        String a = inputArea.getText().split("<body>")[1];
                        String b = a.split("</body>")[0];
                        String c = b.split("-&gt;")[1];
                        String d = c.split("\n")[0];
                        temp = d.trim();
                    }else{
                        System.out.print(inputArea.getText());
                        boolean find = false;
                        for(int i = 0; i < inputArea.getText().length(); i++){

                            if(find == true){
                                if(inputArea.getText().charAt(i) == '\n'){
                                    break;
                                }
                                temp += inputArea.getText().charAt(i);
                            }
                            if(inputArea.getText().charAt(i) == '+'){
                                counter1++;
                                if (counter1 == reactants.size()+ products.size()-1) {
                                    find = true;
                                }
                            }
                        }
                        if(find == false && !products.isEmpty()){
                            String more = "";
                            boolean found = false;
                            for(int i = inputArea.getText().length()-1; i >= 0; i--){
                                if(inputArea.getText().charAt(i) == products.get(products.size()-1).charAt(products.get(products.size()-1).length()-1)){
                                    found = true;
                                }
                                if(found == true){
                                    more = inputArea.getText().charAt(i) + more ;
                                    if(inputArea.getText().charAt(i) == products.get(products.size()-1).charAt(0)){
                                        break;
                                    }
                                }
                            }
                            if(Character.isDigit(more.charAt(more.length()-1))){
                                more = " " + more + "</sub>";
                            }else{
                                more = " " + more;

                            }
                            products.remove(products.size()-1);
                            inputArea.setText(inputArea.getText().replaceFirst("(?s)(.*)" + more, "$1" + ""));
                            temp = "";
                        }
                        System.out.print(temp);

                    }
                    chemicals.removeAllItems();
                    if(temp.isEmpty()){
                        chemicals.setPopupVisible(false);

                    }else{
                        chemicals.setPopupVisible(true);
                        String url = null;
                        boolean yes = false;
                        String ll = temp;
                        if(temp.length() == 1|| (Character.isDigit(ll.charAt(1)) && ll.length()==2)||(Character.isUpperCase(ll.charAt(0)) && Character.isLowerCase(ll.charAt(1))&& ll.length()==2)){
                            url =  "https://webbook.nist.gov/cgi/cbook.cgi?Formula="+ll+"&AllowExtra=on&NoIon=on&Units=SI";
                        }else{

                            url =  "https://webbook.nist.gov/cgi/cbook.cgi?Formula="+ll+"&NoIon=on&Units=SI";

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
                            if(el.text().contains("Formula:")){
                                boolean breaker = false;
                                Elements dodo = doc.getElementsByTag("h1");
                                for (Element ell : dodo) {
                                    System.out.print(ell.id());
                                    if(ell.id().equals("Top")){
                                        String part = "";
                                        for(int i = 9; i < el.text().length(); i++){
                                            part += el.text().charAt(i);
                                        }
                                        chemicals.addItem(ell.text()+ "(" + part + ")");
                                        breaker = true;
                                        break;

                                    }
                                }
                                if(breaker == true){
                                    break;
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

            }
        });
        chemicals.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                if(String.valueOf(chemicals.getSelectedItem()).equals("->")){
                    String actual = inputArea.getText();
                    for(int i = actual.length()-1; i > 0; i--){
                        if(actual.charAt(i) == '+'){
                            actual = insertString(actual, "-->", i);
                            break;
                        }
                    }

                    actual = actual.replace("+-","");
                    inputArea.setText(actual);
                    transfer = true;
                    chemicals.removeAllItems();
                }
                else if(String.valueOf(e.getSource()).equals("javax.swing.JComboBox[,635,325,440x50,layout=javax.swing.plaf.metal.MetalComboBoxUI$MetalComboBoxLayoutManager,alignmentX=0.0,alignmentY=0.0,border=,flags=328,maximumSize=,minimumSize=,preferredSize=,isEditable=false,lightWeightPopupEnabled=true,maximumRowCount=8,selectedItemReminder="+chemicals.getSelectedItem()+"]")){
                    String cool = String.valueOf(chemicals.getSelectedItem());
                    String[] arrOfStr = cool.split("\\(");
                    String[] arrOfStr2 = arrOfStr[1].split("\\)");

                    String output = arrOfStr2[0];

                    if(minus == false){
                        String actual = "<HTML>";
                        for(int i = 0; i < output.length(); i++){
                            if(Character.isDigit(output.charAt(i))){
                                actual += "<sub>" + output.charAt(i) + "</sub>";
                            }else{
                                actual += output.charAt(i);
                            }
                        }

                        actual +=" + </HTML>";
                        String all = actual;

                        inputArea.setContentType("text/html");
                        inputArea.setText(all);
                        minus = true;
                    }else if (transfer == false){
                        String newTemp = "";
                        for(int i = 0; i < output.length(); i++){
                            if(Character.isDigit(output.charAt(i))){
                                newTemp += "<sub>" + output.charAt(i)+ "</sub>";
                            }else{
                                newTemp += output.charAt(i);
                            }
                        }

                        newTemp = newTemp.toUpperCase();
                        newTemp += " + ";
                        String actual = inputArea.getText();
                        actual = actual.replace(temp,"");
                        if(reactants.isEmpty()){
                            actual = insertString(actual, newTemp, 39);
                        }else{
                            int counter5 = 0;
                            for(int i = 0; i < actual.length();i++){
                                System.out.print(actual.charAt(i));
                                if(actual.charAt(i) == '+'){
                                    counter5++;
                                    if(counter5 == reactants.size()){
                                        actual = insertString(actual, newTemp, i+1);
                                        break;

                                    }
                                }
                            }
                        }
                        inputArea.setText(actual);
                    }else{
                        String newTemp = "";
                        for(int i = 0; i < output.length(); i++){
                            if(Character.isDigit(output.charAt(i))){
                                newTemp += "<sub>" + output.charAt(i)+ "</sub>";
                            }else{
                                newTemp += output.charAt(i);
                            }
                        }

                        newTemp = newTemp.toUpperCase();
                        newTemp += " + ";
                        String actual = inputArea.getText();
                        actual = actual.replace(temp,"");
                        if(products.isEmpty()){
                            actual = insertString(actual, newTemp, 82);
                        }else{
                            int counter5 = 0;
                            for(int i = 0; i < actual.length();i++){
                                System.out.print(actual.charAt(i));
                                if(actual.charAt(i) == '+'){
                                    counter5++;
                                    if(counter5 == products.size()+reactants.size()-1){
                                        actual = insertString(actual, newTemp, i+1);
                                        break;

                                    }
                                }
                            }
                        }
                        inputArea.setText(actual);
                    }
                    chemicals.removeAllItems();
                    if(transfer == false){
                        reactants.add(output);
                        chemicals.addItem(" ");
                        chemicals.addItem("->");
                    }else{
                        products.add(output);
                    }
                    yes = true;

                }
            }
        });
    }

    public static String insertString(
            String originalString,
            String stringToBeInserted,
            int index)
    {

        String newString = new String();

        for (int i = 0; i < originalString.length(); i++) {
            newString += originalString.charAt(i);

            if (i == index) {
                newString += stringToBeInserted;
            }
        }

        // return the modified String
        return newString;
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
