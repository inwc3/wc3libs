package net.moonlightflower.wc3libs.misc.image;

import javax.annotation.Nonnull;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class FxImg {
    private BufferedImage _bufImg;

    public double getWidth() {
        return _bufImg.getWidth();
    }

    public double getHeight() {
        return _bufImg.getHeight();
    }

    private static BufferedImage copyBufImg(@Nonnull BufferedImage bufImg) {
        ColorModel colorModel = bufImg.getColorModel();

        WritableRaster raster = bufImg.copyData(null);

        return new BufferedImage(colorModel, raster, colorModel.isAlphaPremultiplied(), null);
    }

    public BufferedImage toBufImg() {
        return copyBufImg(_bufImg);
    }

    public FxImg(@Nonnull BufferedImage bufImg) {
        _bufImg = copyBufImg(bufImg);
    }
}
