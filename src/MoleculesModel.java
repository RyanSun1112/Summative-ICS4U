import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

class MoleculeInput {

    private final JFrame frame = new JFrame("Chemistry Galore! ~ Modeling 3D Molecules!");
    private static JTextArea inputArea = new JTextArea(1,1);
    public void page() {
        inputArea.setText("");
        JLayeredPane pane = new JLayeredPane();

        Design.QuickMenu1(pane,frame);
        JButton d = new JButton("Submit");
        Design.formatButton(d,23);
        d.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                MoleculeOutput cool = new MoleculeOutput();
                cool.page(inputArea.getText());
            }
        });
        d.setBounds(800,470,155,120);
        pane.add(d);

        JLabel background = Design.setBackgroundImage("ChemistryGalore!/ChemistryGalore_moleculeModels.png");
        pane.add(background,JLayeredPane.DEFAULT_LAYER);

        inputArea.setBounds(600,370,350,100);
        inputArea.setFont(Design.theNormalFont(50));
        pane.add(inputArea);




        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.setSize(1152,678);
        frame.setVisible(true);
    }
}
class MoleculeOutput{
    private static final String IMAGE_DESTINATION_FOLDER = "C:\\Users\\megar\\IdeaProjects\\Summative-ICS4\\src";

    private final JFrame frame = new JFrame("Chemistry Galore! ~ Modeling 3D Molecules!");
    public void page(String line) {
        JLayeredPane pane = new JLayeredPane();

        Design.QuickMenu1(pane,frame);



        String molecule = line;
        String molecule2 = molecule;
        boolean found = false;

        String url = "https://www.google.com/search?q=3d+molecule+model+of+"+molecule+"+molinstincts&tbm=isch&ved=2ahUKEwjOs8_GkdmDAxVPGVkFHUWWDKMQ2-cCegQIABAA&oq=3d+molecule+model+of+methane+molinstincts&gs_lcp=CgNpbWcQAzoECCMQJ1CLA1iLA2DOCGgAcAB4AIABSogBkgGSAQEymAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=KN2hZc5_z7Lk2g_FrLKYCg&bih=945&biw=1199&rlz=1C1RXQR_enCA986CA986&hl=en";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";
        molecule2.toLowerCase();
        try{
            String s1 = molecule2.substring(0, 1).toUpperCase();
            String nameCapitalized = s1 + molecule2.substring(1);
            molecule.toLowerCase();
            String s2 = molecule.substring(0, 1).toUpperCase();
            String nameCapitalized2 = s2 + molecule.substring(1);
            Document doc = Jsoup.connect(url).get();
            Elements img = doc.getElementsByTag("a");
            for (Element el : img) {
                // If alt is empty or null, add one to counter

                System.out.println( el.attr("alt"));
                System.out.println( el.attr("jsname"));
                if(el.attr("aria-label").contains("Mol-Instincts"+nameCapitalized2)){
                    url = el.attr("abs:href");
                    doc = Jsoup.connect(url).get();
                    img = doc.getElementsByTag("img");
                    for (Element i : img) {
                        // If alt is empty or null, add one to counter

                        System.out.println( i.attr("alt"));
                        System.out.println( i.attr("jsname"));
                        if(i.attr("alt").contains("3D")){
                            url = i.attr("abs:src");
                            break;
                        }

                    }
                    found = true;
                    pane.add(downloadImage(url), JLayeredPane.PALETTE_LAYER);
                    break;
                }

            }
            if(found == false){

                JLabel notFound = new JLabel("Not Found");
                notFound.setFont(Design.theNormalFont(40));
                notFound.setBounds(450,300,320,320);
                pane.add(notFound,JLayeredPane.PALETTE_LAYER);
            }
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.add(pane);
            frame.setSize(1152,678);
            frame.setVisible(true);
            found = false;
            // Loop through img tags
        }catch(IOException e){
            JLabel notFound = new JLabel("Not Found");
            notFound.setFont(Design.theNormalFont(40));
            notFound.setBounds(450,300,320,320);
            pane.add(notFound,JLayeredPane.PALETTE_LAYER);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.add(pane);
            frame.setSize(1152,678);
            frame.setVisible(true);
            found = false;
        }catch(StringIndexOutOfBoundsException e){

            JLabel notFound = new JLabel("Not Found");
            notFound.setFont(Design.theNormalFont(40));
            notFound.setBounds(450,300,320,320);
            pane.add(notFound,JLayeredPane.PALETTE_LAYER);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.add(pane);
            frame.setSize(1152,678);
            frame.setVisible(true);
            found = false;
        }
        JLabel background = Design.setBackgroundImage("ChemistryGalore!/ChemistryGalore_moleculeActualModel.png");
        pane.add(background,JLayeredPane.DEFAULT_LAYER);
    }
    private JLabel downloadImage(String strImageURL){

        //get file name from image path
        String strImageName =
                strImageURL.substring( strImageURL.lastIndexOf("/") + 1 );

        System.out.println("Saving: " + strImageName + ", from: " + strImageURL);

        try {

            //open the stream from URL
            URL urlImage = new URL(strImageURL);
            InputStream in = urlImage.openStream();

            byte[] buffer = new byte[4096];
            int n = -1;

            OutputStream os = new FileOutputStream( IMAGE_DESTINATION_FOLDER + "/" + strImageName );

            //write bytes to the output stream
            while ( (n = in.read(buffer)) != -1 ){
                os.write(buffer, 0, n);
            }

            //close the stream
            os.close();
            ImageIcon cool = new ImageIcon(IMAGE_DESTINATION_FOLDER + "/" + strImageName);
            System.out.println("Image saved");
            JLabel imgLabel = new JLabel(cool);
            imgLabel.setBounds(450,300,320,320);
            return imgLabel;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
