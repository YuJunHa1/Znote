

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GUI {
    private JLabel login_lbl_captcha_image;
    
    public GUI(JLabel login_lbl_captcha_image){
        this.login_lbl_captcha_image = login_lbl_captcha_image;
    }

    public void display(String captcha) throws IOException {
        BufferedImage image = ImageIO.read(new File("./Text.png"));
        login_lbl_captcha_image.setIcon(new ImageIcon(image));

    }
}
