// This class uses the Color class, which is part of a package called awt,
// which is part of Java's standard class library.
import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);

		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] imageOut;

		// Tests the horizontal flipping of an image:
		imageOut = flippedHorizontally(tinypic);
		System.out.println();
		print(imageOut);
		
		//// Write here whatever code you need in order to test your work.
		//// You can reuse / overide the contents of the imageOut array.
	}

		public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
     
 		Color[][] image = new Color[numRows][numCols];
		for(int i=0;i<image.length;i++)
		{
			for(int j=0;j<image[i].length;j++)
			{
				image[i][j]=new Color(in.readInt(),in.readInt(),in.readInt());
			}
		}
		// Reads the RGB values from the file, into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
		return image;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		for(int i=0;i<image.length;i++)
		{
              for(int j=0;j<image[i].length;j++)
			  {
				print(image[i][j]);
			  }
			 System.out.println();
		}
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		//// Replace the following statement with your code
		int col=image[0].length;
		Color [][] fimage=new Color[image.length][col];
        int colin=0,roin=0;//indexes of the flipped image
		for(int i=0;i<image.length;i++)
		{
			for(int j=col-1;j>-1;j--)
			{
               fimage[roin][colin]=image[i][j];
			   colin++;
			}
			colin=0;
			roin++;
		}
		return fimage;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		//// Replace the following statement with your code
		int col=image[0].length;
		Color [][] fimage=new Color[image.length][col];
        int colin=0,roin=0;//indexes of the flipped image
		for(int i=image.length-1;i>-1;i--)
		{
			for(int j=0;j<col;j++)
			{
               fimage[roin][colin]=image[i][j];
			   colin++;
			}
			colin=0;
			roin++;
		}
		return fimage;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	public static Color luminance(Color pixel) 
	{
		//// Replace the following statement with your code
		 int rgb=(int)(pixel.getRed()*0.299+pixel.getGreen()*0.587+pixel.getBlue()*0.114);//targetimage

		return new  Color(rgb, rgb, rgb);
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		//// Replace the following statement with your code
         Color[][] gsimage=new Color[image.length][image[0].length];
		for(int i=0;i<image.length;i++)
		{
			for(int j=0;j<image[i].length;j++)
			{
				gsimage[i][j]=luminance(image[i][j]);

			}
		}

		return  gsimage;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		//// Replace the following statement with your code
		int h0=image.length,w0=image[0].length;	
		 Color [][] cc=new Color[height][width];
		 for(int i=0;i<height;i++)
		 {
			for(int j=0;j<width;j++)
			{
				cc[i][j]=image[i*h0/height][j*w0/width];
			}
		 }
		return cc;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		//// Replace the following statement with your code
	int r=(int)(alpha*c1.getRed()+(1-alpha)*c2.getRed());
	int g=(int)(alpha*c1.getGreen()+(1-alpha)*c2.getGreen());
	int b=(int)(alpha*c1.getBlue()+(1-alpha)*c2.getBlue());
		
		Color c=new Color(r,g,b);
		return c;
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		//// Replace the following statement with your code
		Color[][] image3=new Color[image1.length][image1[0].length];
		for(int i=0;i<image3.length;i++)
		{
		   for(int j=0;j<image3[i].length;j++)
		   {
			  image3[i][j]=blend(image1[i][j],image2[i][j], alpha);
		   }
		}
		return image3;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		//// Replace this comment with your code
		target=scaled(target,source[0].length,source.length);
		Color[][] cc;
		double alpha;
		for(int i=0;i<=n;i++)
		{ 
            alpha=(double)(n-i)/n;
			cc=blend(source, target,alpha);
			display(cc);
			StdDraw.pause(5000);

		}
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(height, width);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

