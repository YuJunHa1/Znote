
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class CAPTCHAGenerator {
    private JLabel login_lbl_captcha_image;
    
    public CAPTCHAGenerator(JLabel login_lbl_captcha_image){
        this.login_lbl_captcha_image = login_lbl_captcha_image;
    }

    
    public String Generate() throws IOException {
        // Get random string from method getString below
        String text = getString();

        // Create image showing the string generated, to prevent the ability to copy/paste the string
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        Font font = new Font("Arial", Font.PLAIN, 38);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        g2d.dispose();

        img = new BufferedImage(155, 50, BufferedImage.TYPE_INT_ARGB);
        g2d = img.createGraphics();
        g2d.setFont(font);
        fm = g2d.getFontMetrics();
        g2d.setColor(Color.BLACK);
        g2d.drawString(text, 0, fm.getAscent());
        g2d.dispose();
        try {
            ImageIO.write(img, "png", new File("Text.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            popup(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    // Generates random string from the characters in CHARS
    public String getString() {
        String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder str = new StringBuilder();
        Random rnd = new Random();
        while (str.length() < 6) {
            int index = (int) (rnd.nextFloat() * CHARS.length());
            str.append(CHARS.charAt(index));
        }
        String generatedCaptcha = str.toString();
        return generatedCaptcha;
    }

    public void popup(String text) throws IOException {
        GUI graphicInterface = new GUI(login_lbl_captcha_image);
        graphicInterface.display(text);
    }
}
