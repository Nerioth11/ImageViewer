package imageviewer.view;

import imageviewer.model.Image;

public interface ImageDisplay {
    public Image image();
    public void show(Image image);

}