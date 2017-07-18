import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageResize {


    public static BufferedImage zoomInImage(BufferedImage  originalImage, Integer times){

        int width = originalImage.getWidth()*times;

        int height = originalImage.getHeight()*times;

        BufferedImage newImage = new BufferedImage(width,height,originalImage.getType());

        return newImage;

    }


    public static BufferedImage zoomOutImage(BufferedImage  originalImage, Integer times){

        int width = originalImage.getWidth()/times;

        int height = originalImage.getHeight()/times;

        BufferedImage newImage = new BufferedImage(width,height,originalImage.getType());

        return newImage;

    }

}