import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static java.lang.Character.isLetter;
import static java.lang.Integer.parseInt;


class BalancingChemicalEquations {

    //INSTANCE VARIABLES
    private final JFrame frame = new JFrame("Chemistry Galore! ~ Balancing Chemical Equations!");
    private final JComboBox chemicals = new JComboBox();
    private final JTextPane inputArea = new JTextPane();
    boolean minus = false;
    boolean yes = false;
    String temp = "";
    boolean transfer = false;
    public static ArrayList<String> reactants = new ArrayList<String>();
    public static ArrayList<String> products = new ArrayList<String>();
    public static boolean canContinue=true;
    public static String theEquation = "";



    //BALANCING CHEMICAL EQUATIONS METHOD
    public void bcePage() {
        reactants.clear();
        products.clear();

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
                theEquation = "";
                theEquation = ChemicalEquationBalancer2.Balancing(BalancingChemicalEquations.reactants,BalancingChemicalEquations.products);
                System.out.print(theEquation);

                if(theEquation.contains("ERROR")||theEquation.contains("error")
                        || theEquation.equals("1 ---> 1")) { //if there's an error...
                    frame.setVisible(false);
                    Error error = new Error();
                    error.endIt();
                    canContinue = false;

                }

                if(canContinue) {
                    frame.setVisible(false);
                    BalancingChemicalEquations_Answer answer = new BalancingChemicalEquations_Answer();
                    answer.bceAnswerPage();
                }
            }
        });

        Design.formatButton(next,30);
        next.setBounds(880,405,155,120);
        pane.add(next);

        chemicals.setBounds(635,325,440,50);

        Design.formatComboBox(chemicals);
        Design.formatTextPane(inputArea);

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
                try{

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

                }catch(Exception ee){
                    frame.setVisible(false);

                    Error cddd = new Error();
                    cddd.endIt();
                }
            }
        });
        chemicals.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                try{

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
                }catch(Exception ee){
                    frame.setVisible(false);

                    Error cddd = new Error();
                    cddd.endIt();
                }
            }
        });
    }


    //HELPER METHODS
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



class BalancingChemicalEquations_Answer {

    //INSTANCE VARIABLES
    private final JFrame frame = new JFrame("Chemistry Galore! ~ Balancing Chemical Equations!");
    private boolean canContinue = true;


    //BALANCING CHEMICAL EQUATIONS ANSWER METHOD
    public void bceAnswerPage() {

        JLayeredPane pane = new JLayeredPane();
        try{
        String balancedEquation = BalancingChemicalEquations.theEquation;
        List<String> list = new ArrayList<String>(Arrays.asList(balancedEquation.split("")));
        ArrayList<String> arr = new ArrayList<>(list);

        for(int i=0; i<arr.size();i++) {
            list = new ArrayList<String>(Arrays.asList(balancedEquation.split("")));
            arr = new ArrayList<>(list);
            System.out.println("Array of split thingies!: "+arr);

            System.out.println("Size of array: "+arr.size());
            String character = arr.get(i);
            System.out.println("Character (i): "+character);
            if(isDigit(character)) {
                try {
                    if (isLetter(arr.get(i - 1))) {
                        String startString = balancedEquation.substring(0,i);
                        String endString = balancedEquation.substring((i+1));
                        balancedEquation = startString+"<sub>"+character+"</sub>"+endString;
                        System.out.println("Messy Thus Far: "+balancedEquation);
                        i+=10;
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\nFingers crossed this works :))\n");

                }
            }
        }

        System.out.println("\n\n\nTO-BE-OUTPUTTED TEXT!!!\nmessy text: " + balancedEquation);

        balancedEquation=balancedEquation.replace("--->","→");
        JLabel displayedChemEquation = new JLabel(balancedEquation);
        Design.formatLabel(displayedChemEquation,35);
        displayedChemEquation.setBounds(320,365,752,75);
        pane.add(displayedChemEquation);
        }catch(Exception eee){
            frame.setVisible(false);

            Error cddd = new Error();
            cddd.endIt();
        }

        Design.QuickMenu1(pane,frame);

        JButton back = new JButton("Back?");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BalancingChemicalEquations bce = new BalancingChemicalEquations();
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

    //HELPER METHODS
    public static boolean isUpper(String str) {
        ArrayList<String> arr = new ArrayList<String>();
        arr.add("A");
        arr.add("B");
        arr.add("C");
        arr.add("D");
        arr.add("E");
        arr.add("F");
        arr.add("G");
        arr.add("H");
        arr.add("I");
        arr.add("J");
        arr.add("K");
        arr.add("L");
        arr.add("M");
        arr.add("N");
        arr.add("O");
        arr.add("P");
        arr.add("Q");
        arr.add("R");
        arr.add("S");
        arr.add("T");
        arr.add("U");
        arr.add("V");
        arr.add("W");
        arr.add("X");
        arr.add("Y");
        arr.add("Z");
        return arr.contains(str);
    }

    public static boolean isLower(String str) {
        ArrayList<String> arr = new ArrayList<String>();
        arr.add("a");
        arr.add("b");
        arr.add("c");
        arr.add("d");
        arr.add("e");
        arr.add("f");
        arr.add("g");
        arr.add("h");
        arr.add("i");
        arr.add("j");
        arr.add("k");
        arr.add("l");
        arr.add("m");
        arr.add("n");
        arr.add("o");
        arr.add("p");
        arr.add("q");
        arr.add("r");
        arr.add("s");
        arr.add("t");
        arr.add("u");
        arr.add("v");
        arr.add("w");
        arr.add("x");
        arr.add("y");
        arr.add("z");
        return arr.contains(str);
    }

    public static boolean isLetter(String str) {
        return isUpper(str) || isLower(str);
    }

    public static boolean isDigit(String str) {
        ArrayList<String> digits = new ArrayList<String>();
        digits.add("1");
        digits.add("2");
        digits.add("3");
        digits.add("4");
        digits.add("5");
        digits.add("6");
        digits.add("7");
        digits.add("8");
        digits.add("9");
        digits.add("0");
        return digits.contains(str);
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
                BalancingChemicalEquations bce = new BalancingChemicalEquations();
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
