package imageviewer.model;

public interface Image {
    public <T> T bitMap();
    public Image prev();
    public Image next();

}