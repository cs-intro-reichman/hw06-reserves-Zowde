import java.awt.Color;
public class Editor4 {

    public static void main (String[] args) {
		String source = args[0];
	       int n =Integer.parseInt(args[2]);
		Color[][] sourceImage = Runigram.read(source);
		Color[][] sourceImage2 = Runigram.grayScaled(sourceImage);//gray form of the image
		Runigram.setCanvas(sourceImage);
		Runigram.morph(sourceImage,sourceImage2, n);

	    
		}
}
