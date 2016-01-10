package imageviewer;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements ImageDisplay {

    private Image image;

    public ImagePanel(Image image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        BufferedImage bitmap = this.image.bitMap();
        int[] coordinates = getCoordinates(bitmap);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2.drawImage(bitmap, coordinates[0], coordinates[1], coordinates[2], coordinates[3], ImagePanel.this);
    }  
    @Override
    public Image image() {
        return image;
    }

    @Override
    public void show(Image image) {
        this.image = image;
        this.repaint();
    }
    
    private int[] getCoordinates(BufferedImage bitmap) {
        double relation = bitmap.getWidth() / (double) bitmap.getHeight();
        int width = this.getWidth();
        int height = this.getHeight();
        int x = 0;
        int y = 0;
        if (this.getHeight() > bitmap.getHeight() && width > bitmap.getWidth()) {
            x = (width - bitmap.getWidth()) / 2;
            y = (height - bitmap.getHeight()) / 2;
            width = bitmap.getWidth();
            height = bitmap.getHeight();
        } else if (height / (double) bitmap.getHeight() > width / (double) bitmap.getWidth()) {
            y = (int) (height - width / relation) / 2;
            height = (int) (width / relation);
        } else {
            x = (int) (width - height * relation) / 2;
            width = (int) (height * relation);
        }

        return new int[]{x, y, width, height};
    }
    
}
