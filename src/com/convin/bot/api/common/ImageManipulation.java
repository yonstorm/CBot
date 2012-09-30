package com.convin.bot.api.common;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import static java.awt.image.AffineTransformOp.TYPE_NEAREST_NEIGHBOR;

/**
 * User: JuV
 * Date: 21.9.2012
 * Time: 14:57
 */
public class ImageManipulation {

    /**
     * Creates a bufferedImage from a byte pixelBuffer you provide.
     *
     * @param pixelBuffer Image data as bytes
     * @param width       The width of the image you are creating
     * @param height      The height of the image you are creating
     * @param flipIt      Should the image be flipped, in opengl images are mirrored and upside down
     * @return A bufferedImage from the data provided
     */
    public static BufferedImage createImageFromBytes(byte[] pixelBuffer, int width, int height, boolean flipIt) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        img.getRaster().setDataElements(0, 0, width, height, pixelBuffer);

        if (flipIt) {
            // Flip the image
            AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
            tx.translate(0, -img.getHeight(null));
            AffineTransformOp op = new AffineTransformOp(tx, TYPE_NEAREST_NEIGHBOR);
            img = op.filter(img, null);
        }

        return img;
    }

    /**
     * Strips the alpha channel from a bufferedImage.
     *
     * @param image Image to strip the alpha channel from
     * @return TYPE_3BYTE_BGR BufferedImage with the alpha channel stripped
     */

    public static BufferedImage stripAlpha(BufferedImage image) {
        try {
            BufferedImage raw_image = image;
            image = new BufferedImage(raw_image.getWidth(), raw_image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
            ColorConvertOp xformOp = new ColorConvertOp(null);
            xformOp.filter(raw_image, image);
            raw_image.flush();
        } catch (Exception e) {
            Logging.log(Logging.LogLevel.ERROR, "Exception " + e + " removing alpha from image");
        }

        return image;
    }

    public static BufferedImage createImageFromBytes(ByteBuffer data) throws IOException {
        return ImageIO.read(new ByteArrayInputStream(data.array()));
    }
}
