//Class contains methods for formatting/designing various aspects of the program so that these
//formatting methods don't need to be replicated for each individual class created

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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
}