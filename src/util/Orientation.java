package util;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public enum Orientation {
	
	UP, DOWN, LEFT, RIGHT, NA;
	
	public static BufferedImage rotateUp(BufferedImage bi) {
	    int width = bi.getWidth();
	    int height = bi.getHeight();
	    BufferedImage biFlip = new BufferedImage(height, width, bi.getType());
	    for(int i=0; i<width; i++)
	        for(int j=0; j<height; j++)
	            biFlip.setRGB(height-j-1, width-i-1, bi.getRGB(i, j));
	    return biFlip;
	}
	
	public static BufferedImage rotateDown(BufferedImage bi) {
	    int width = bi.getWidth();
	    int height = bi.getHeight();
	    BufferedImage biFlip = new BufferedImage(height, width, bi.getType());
	    for(int i=0; i<width; i++)
	        for(int j=0; j<height; j++)
	            biFlip.setRGB(j, i, bi.getRGB(i, j));
	    return biFlip;
	}
	
	public static BufferedImage rotateLeft(BufferedImage bi) {
		BufferedImage bufferedImage = bi;

	    AffineTransform tx = AffineTransform.getScaleInstance(-1, -1);
	    tx.translate(-bufferedImage.getWidth(null), -bufferedImage.getHeight(null));
	    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
	    bufferedImage = op.filter(bufferedImage, null);
	    return bufferedImage;
	}

}
