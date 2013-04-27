package com.arun.image.utils;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class ImageUtils {
	
	public final static int INTERCHANGERG=1; 
	public final static int INTERCHANGEGB=2;
	public final static int INTERCHANGERB=3;
	
	
	public BufferedImage SmoothMeanImage(BufferedImage image){		
		
		BufferedImage transformedImage=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
		int red,green,blue,alpha;
		for(int i=0;i<image.getWidth();i++){
			Color color=obtainColor(image,i,0);
			red=color.red;
			blue=color.blue;
			green=color.green;
			alpha=color.alpha;
			transformedImage.setRGB(i, 0, ((alpha<<24)|(red<<16)|(green<<8)|blue));
			
			color=obtainColor(image,i,image.getHeight()-1);
			red=color.red;
			blue=color.blue;
			green=color.green;
			alpha=color.alpha;
			transformedImage.setRGB(i, image.getHeight()-1, ((alpha<<24)|(red<<16)|(green<<8)|blue));
		}
		
		
		for(int i=0;i<image.getHeight();i++){
			Color color=obtainColor(image,0,i);
			red=color.red;
			blue=color.blue;
			green=color.green;
			alpha=color.alpha;
			transformedImage.setRGB(0, i, ((alpha<<24)|(red<<16)|(green<<8)|blue));
			
			color=obtainColor(image,image.getWidth()-1,i);
			red=color.red;
			blue=color.blue;
			green=color.green;
			alpha=color.alpha;
			transformedImage.setRGB(image.getWidth()-1,i, ((alpha<<24)|(red<<16)|(green<<8)|blue));
		}
		
		
		for(int i=1;i<image.getWidth()-1;i++){
            for(int j=1;j<image.getHeight()-1;j++){
            	red=green=blue=alpha=0;
            	
    			Color color=obtainColor(image,i-1,j-1);
            	red+=color.red;
    			blue+=color.blue;
    			green+=color.green;
    			color=obtainColor(image,i,j-1);
            	red+=color.red;
    			blue+=color.blue;
    			green+=color.green;
    			color=obtainColor(image,i+1,j-1);
            	red+=color.red;
    			blue+=color.blue;
    			green+=color.green;
    			color=obtainColor(image,i-1,j);
            	red+=color.red;
    			blue+=color.blue;
    			green+=color.green;
    			color=obtainColor(image,i,j);
            	red+=color.red;
    			blue+=color.blue;
    			green+=color.green;
    			color=obtainColor(image,i+1,j);
            	red+=color.red;
    			blue+=color.blue;
    			green+=color.green;
    			
    			
    			color=obtainColor(image,i-1,j+1);
            	red+=color.red;
    			blue+=color.blue;
    			green+=color.green;
    			color=obtainColor(image,i,j+1);
            	red+=color.red;
    			blue+=color.blue;
    			green+=color.green;
    			color=obtainColor(image,i+1,j+1);
            	red+=color.red;
    			blue+=color.blue;
    			green+=color.green;
    			
    			red=(int)(red/9.0);
    			green=(int)(green/9.0);
    			blue=(int)(blue/9.0);
    			alpha=0xff;
               // System.out.println("red="+red+" green="+green+" blue="+blue);                

    			transformedImage.setRGB(i, j, ((alpha<<24)|(red<<16)|(green<<8)|blue));
            }
        }
		return transformedImage;

	}
	
	
	
public BufferedImage SmoothMedianImage(BufferedImage image){
		
		BufferedImage transformedImage=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
		int red,green,blue,alpha;
		for(int i=0;i<image.getWidth();i++){
			Color color=obtainColor(image,i,0);
			red=color.red;
			blue=color.blue;
			green=color.green;
			alpha=color.alpha;
			transformedImage.setRGB(i, 0, ((alpha<<24)|(red<<16)|(green<<8)|blue));
			
			color=obtainColor(image,i,image.getHeight()-1);
			red=color.red;
			blue=color.blue;
			green=color.green;
			alpha=color.alpha;
			transformedImage.setRGB(i, image.getHeight()-1, ((alpha<<24)|(red<<16)|(green<<8)|blue));
		}
		
		
		for(int i=0;i<image.getHeight();i++){
			Color color=obtainColor(image,0,i);
			red=color.red;
			blue=color.blue;
			green=color.green;
			alpha=color.alpha;
			transformedImage.setRGB(0, i, ((alpha<<24)|(red<<16)|(green<<8)|blue));
			
			color=obtainColor(image,image.getWidth()-1,i);
			red=color.red;
			blue=color.blue;
			green=color.green;
			alpha=color.alpha;
			transformedImage.setRGB(image.getWidth()-1,i, ((alpha<<24)|(red<<16)|(green<<8)|blue));
		}
		int reds[]=new int[9];
		int blues[]=new int[9];
		int greens[]=new int[9];
		
		for(int i=1;i<image.getWidth()-1;i++){
            for(int j=1;j<image.getHeight()-1;j++){
            	red=green=blue=alpha=0;
            	
    			Color color=obtainColor(image,i-1,j-1);
            	reds[0]=color.red;
    			blues[0]=color.blue;
    			greens[0]=color.green;
    			
    			color=obtainColor(image,i,j-1);
    			reds[1]=color.red;
    			blues[1]=color.blue;
    			greens[1]=color.green;
    			
    			color=obtainColor(image,i+1,j-1);
    			reds[2]=color.red;
    			blues[2]=color.blue;
    			greens[2]=color.green;
    			
    			color=obtainColor(image,i-1,j);
    			reds[3]=color.red;
    			blues[3]=color.blue;
    			greens[3]=color.green;
    			
    			color=obtainColor(image,i,j);
    			reds[4]=color.red;
    			blues[4]=color.blue;
    			greens[4]=color.green;
    			
    			color=obtainColor(image,i+1,j);
    			reds[5]=color.red;
    			blues[5]=color.blue;
    			greens[5]=color.green;
    			
    			
    			color=obtainColor(image,i-1,j+1);
    			reds[6]=color.red;
    			blues[6]=color.blue;
    			greens[6]=color.green;
    			
    			color=obtainColor(image,i,j+1);
    			reds[7]=color.red;
    			blues[7]=color.blue;
    			greens[7]=color.green;
    			
    			color=obtainColor(image,i+1,j+1);
    			reds[8]=color.red;
    			blues[8]=color.blue;
    			greens[8]=color.green;
    			Arrays.sort(reds);
    			Arrays.sort(greens);
    			Arrays.sort(blues);
    			red=reds[5];
    			green=greens[5];
    			blue=blues[5];
    			alpha=0xff;
               // System.out.println("red="+red+" green="+green+" blue="+blue);                

    			transformedImage.setRGB(i, j, ((alpha<<24)|(red<<16)|(green<<8)|blue));
            }
        }
		return transformedImage;

	}
	


public BufferedImage GrayScale(BufferedImage image){
	
	BufferedImage transformedImage=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
	int red,green,blue,alpha;
	int colortoset;
	for(int i=0;i<image.getWidth();i++){
        for(int j=0;j<image.getHeight();j++){
        	red=green=blue=alpha=0;
        	
			Color color=obtainColor(image,i,j);        	
			if(color.red>color.green){
                colortoset=color.red;
            }
            else if(color.green>color.blue){
                colortoset=color.green;
            }
            else colortoset=color.blue;
			
			red=green=blue=colortoset;
			alpha=0xff;
           // System.out.println("red="+red+" green="+green+" blue="+blue);                

			transformedImage.setRGB(i, j, ((alpha<<24)|(red<<16)|(green<<8)|blue));
        }
    }
	return transformedImage;

}


public BufferedImage Negative(BufferedImage image){
	
	BufferedImage transformedImage=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
	int red,green,blue,alpha;
	for(int i=0;i<image.getWidth();i++){
        for(int j=0;j<image.getHeight();j++){
        	red=green=blue=alpha=0;
        	
			Color color=obtainColor(image,i,j);        				
			red=255-color.red;
			green=255-color.green;
			blue=255-color.blue;
			alpha=0xff;
           // System.out.println("red="+red+" green="+green+" blue="+blue);                

			transformedImage.setRGB(i, j, ((alpha<<24)|(red<<16)|(green<<8)|blue));
        }
    }
	return transformedImage;

}

public BufferedImage SharpImage(BufferedImage image){
	
	BufferedImage transformedImage=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
	int red,green,blue,alpha;
	for(int i=0;i<image.getWidth();i++){
		Color color=obtainColor(image,i,0);
		red=color.red;
		blue=color.blue;
		green=color.green;
		alpha=color.alpha;
		transformedImage.setRGB(i, 0, ((alpha<<24)|(red<<16)|(green<<8)|blue));
		
		color=obtainColor(image,i,image.getHeight()-1);
		red=color.red;
		blue=color.blue;
		green=color.green;
		alpha=color.alpha;
		transformedImage.setRGB(i, image.getHeight()-1, ((alpha<<24)|(red<<16)|(green<<8)|blue));
	}
	
	
	for(int i=0;i<image.getHeight();i++){
		Color color=obtainColor(image,0,i);
		red=color.red;
		blue=color.blue;
		green=color.green;
		alpha=color.alpha;
		transformedImage.setRGB(0, i, ((alpha<<24)|(red<<16)|(green<<8)|blue));
		
		color=obtainColor(image,image.getWidth()-1,i);
		red=color.red;
		blue=color.blue;
		green=color.green;
		alpha=color.alpha;
		transformedImage.setRGB(image.getWidth()-1,i, ((alpha<<24)|(red<<16)|(green<<8)|blue));
	}
	Color colortosetifnegative;
	for(int i=1;i<image.getWidth()-1;i++){
        for(int j=1;j<image.getHeight()-1;j++){
        	red=green=blue=alpha=0;
        	
			Color color=obtainColor(image,i-1,j-1);
        	red-=color.red;
			blue-=color.blue;
			green-=color.green;
			
			color=obtainColor(image,i,j-1);
			red-=color.red;
			blue-=color.blue;
			green-=color.green;
			
			color=obtainColor(image,i+1,j-1);
			red-=color.red;
			blue-=color.blue;
			green-=color.green;
			
			color=obtainColor(image,i-1,j);
			red-=color.red;
			blue-=color.blue;
			green-=color.green;
			
			colortosetifnegative=color=obtainColor(image,i,j);
			red+=9*color.red;
			blue+=9*color.blue;
			green+=9*color.green;
			
			color=obtainColor(image,i+1,j);
			red-=color.red;
			blue-=color.blue;
			green-=color.green;
			
			
			color=obtainColor(image,i-1,j+1);
			red-=color.red;
			blue-=color.blue;
			green-=color.green;
			
			color=obtainColor(image,i,j+1);
			red-=color.red;
			blue-=color.blue;
			green-=color.green;
			
			color=obtainColor(image,i+1,j+1);
			red-=color.red;
			blue-=color.blue;
			green-=color.green;
			
			//System.out.print(red+" "+green+" "+blue );
			
			//System.out.print(colortosetifnegative.red+" "+colortosetifnegative.green+" "+colortosetifnegative.blue );
			
			if(red<0||blue<0||green<0){
				red=colortosetifnegative.red;
				blue=colortosetifnegative.blue;
				green=colortosetifnegative.green;
			}
			
			//System.out.println(red+" "+green+" "+blue );
			alpha=0xff;
           // System.out.println("red="+red+" green="+green+" blue="+blue);                

			transformedImage.setRGB(i, j, ((alpha<<24)|(red<<16)|(green<<8)|blue));
        }
    }
	return transformedImage;

}

public BufferedImage EdgeDetect(BufferedImage image){
	
	BufferedImage transformedImage=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
	int red,green,blue,alpha,color;
    int colortoset;
    byte pixel[][]=new byte[image.getWidth()][image.getHeight()];
    byte sober[][]={{-1,0,1},{-2,0,2},{-1,0,1}};
    byte sober1[][]={{1,2,1},{0,0,0},{-1,-2,-1}};
    byte sober2[][]={{1,1,0},{-1,0,2},{0,-1,1}};
    for(int i=0;i<image.getWidth();i++){
        for(int j=0;j<image.getHeight();j++){
            color=image.getRGB(i, j);
            alpha=((color>>24)&255);
            red=((color>>16)&255);
            green=((color>>8)&255);
            blue=(color&255);
           // System.out.println("red="+red+" green="+green+" blue="+blue);
            if(red>green){
                colortoset=red;
            }
            else if(green>blue){
                colortoset=green;
            }
            else colortoset=blue;
            //System.out.println("color="+colortoset);
            pixel[i][j]=(byte)colortoset;
            colortoset=(colortoset<<16)|(colortoset<<8)|colortoset;
            //System.out.println("color1 = "+colortoset);

            transformedImage.setRGB(i, j, colortoset);
        }
    }
    int gx,gy,gz;
    int textchar;
    for(int j=1;j<image.getHeight()-1;j++){
        for(int i=1;i<image.getWidth()-1;i++){
            gx=pixel[i-1][j-1]*sober[0][0]+pixel[i][j-1]*sober[0][1]+pixel[i+1][j-1]*sober[0][2]+
                    pixel[i-1][j]*sober[1][0]+pixel[i][j]*sober[1][1]+pixel[i+1][j]*sober[1][2]+
                    pixel[i-1][j+1]*sober[2][0]+pixel[i][j+1]*sober[2][1]+pixel[i+1][j+1]*sober[2][2];

            gy=pixel[i-1][j-1]*sober1[0][0]+pixel[i][j-1]*sober1[0][1]+pixel[i+1][j-1]*sober1[0][2]+
                    pixel[i-1][j]*sober1[1][0]+pixel[i][j]*sober1[1][1]+pixel[i+1][j]*sober1[1][2]+
                    pixel[i-1][j+1]*sober1[2][0]+pixel[i][j+1]*sober1[2][1]+pixel[i+1][j+1]*sober1[2][2];

            /*gz=pixel[i-1][j-1]*sober2[0][0]+pixel[i][j-1]*sober2[0][1]+pixel[i+1][j-1]*sober2[0][2]+
                    pixel[i-1][j]*sober2[1][0]+pixel[i][j]*sober2[1][1]+pixel[i+1][j]*sober2[1][2]+
                    pixel[i-1][j+1]*sober2[2][0]+pixel[i][j+1]*sober2[2][1]+pixel[i+1][j+1]*sober2[2][2];
            //textchar=colortoset=(int)Math.sqrt(gx*gx+gy*gy+gz*gz);*/
            colortoset=(int)Math.sqrt(gx*gx+gy*gy);
            //System.out.println("texttochar"+textchar);
            colortoset=(0xff000000)|(colortoset<<16)|(colortoset<<8)|colortoset;
            transformedImage.setRGB(i, j,colortoset);
        }
    }

	return transformedImage;

}
public BufferedImage GlassImage(BufferedImage image){
	
	BufferedImage transformedImage=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
	int red,green,blue,alpha,color;
	int width=image.getWidth();
	int height=image.getHeight();
	for(int i=1;i<width-1;i++){
		for(int j=1;j<height-1;j++){
			//glass effect
			int xx = (width  + i + random(-5, 5)) % width;
            int yy = (height + j + random(-5, 5)) % height;
            color=image.getRGB(xx, yy);
            transformedImage.setRGB(i,j, color);
		}
	}
	return transformedImage;
}
		public static int random(int a, int b) {
	        return a + (int) (Math.random() * (b-a+1));
	    }
	public BufferedImage Warp1Image(BufferedImage image){
		BufferedImage transformedImage=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
		int r;
		int g;
		int b;
		int a=0;
		int color=0;
		int x;
		int y;
		double rval;
		int width=image.getWidth();
		int height=image.getHeight();
		int xmin=0,xmax=width;
		int ymin=0;int ymax=height;
		double transformi;
		double transformj;
		double intermediatex;
		double intermediatey;

		for(int i=1;i<width-1;i++){
			for(int j=1;j<height-1;j++){
				//glass effect
				
				
				transformi=(i-width/2.);
				transformj=(j-height/2.);				
				rval=Math.sqrt(transformi*transformi+transformj*transformj);
				//rval=5*Math.exp(-10*Math.sqrt(transformi*transformi+transformj*transformj));
				//System.out.println(rval);
				intermediatex=((transformi)*Math.cos(Math.PI*rval/180)+(transformj)*Math.sin(Math.PI*rval/180));
				intermediatey=((-transformi)*Math.sin(Math.PI*rval/180)+(transformj)*Math.cos(Math.PI*rval/180));//idhu
				//System.out.println("X="+intermediatex+" Y="+intermediatey+" rval="+rval);
				x=(int)(intermediatex+width/2);
				y=(int)(intermediatey+height/2);
				if(x<width&&x>0&&y>0&&y<height){
					color=image.getRGB(x, y);
					transformedImage.setRGB(i,j, color);
				}				
				/*transformi=(i-width/2.)/(width/2.)*0.5;
				transformj=(j-height/2.)/(height/2.)*0.5;				
				rval=Math.exp(-Math.sqrt(transformi*transformi+transformj*transformj));
				//rval=2*Math.sqrt(transformi*transformi+transformj*transformj);
				x=(int)((i-(width/2))*rval)+width/2;
				y=(int)((j-height/2)*rval)+height/2;
				//System.out.println("X="+x+" Y="+y+" rval="+rval);
				//System.out.println("i="+i+" j="+j);
				if(x>=width)x=width-2;
				if(y>=height)y=height-2;
				if(x<=0)x=0;
				if(y<=0)y=0;
				color=image.getRGB(x, y);
				img.setRGB(i,j, color);*/
			}
		}
			return transformedImage;
		}
	
	
	public BufferedImage Warp2Image(BufferedImage image){
		BufferedImage transformedImage=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
		int r;
		int g;
		int b;
		int a=0;
		int color=0;
		int x;
		int y;
		double rval;
		int width=image.getWidth();
		int height=image.getHeight();
		int xmin=0,xmax=width;
		int ymin=0;int ymax=height;
		double transformi;
		double transformj;
		double intermediatex;
		double intermediatey;

		for(int i=1;i<width-1;i++){
			for(int j=1;j<height-1;j++){
				transformi=(i-width/2.)/(width/2.)*0.5;
				transformj=(j-height/2.)/(height/2.)*0.5;				
				rval=Math.exp(-Math.sqrt(transformi*transformi+transformj*transformj));
				//rval=2*Math.sqrt(transformi*transformi+transformj*transformj);
				x=(int)((i-(width/2))*rval)+width/2;
				y=(int)((j-height/2)*rval)+height/2;
				//System.out.println("X="+x+" Y="+y+" rval="+rval);
				//System.out.println("i="+i+" j="+j);
				if(x>=width)x=width-2;
				if(y>=height)y=height-2;
				if(x<=0)x=0;
				if(y<=0)y=0;
				color=image.getRGB(x, y);
				transformedImage.setRGB(i,j, color);
			}
		}
			return transformedImage;
		}
	
	public BufferedImage InterchangeColorImage(BufferedImage image,int colorchangeType){
		BufferedImage transformedImage=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
		int red=0,green=0,blue=0,alpha=0;
		
		Color color;
		for(int i=0;i<image.getWidth();i++){
			for(int j=0;j<image.getHeight();j++){
				color=obtainColor(image,i, j);
				if(colorchangeType==ImageUtils.INTERCHANGERG){
					red=color.green;
					green=color.red;
					blue=color.blue;
				}
				else if(colorchangeType==ImageUtils.INTERCHANGEGB){
					red=color.red;
					green=color.blue;
					blue=color.green;
				}
				else if(colorchangeType==ImageUtils.INTERCHANGERB){
					red=color.blue;
					green=color.green;
					blue=color.red;
				}
				alpha=0xff;
				transformedImage.setRGB(i,j, ((alpha<<24)|(red<<16)|(green<<8)|blue));
			}
		}
			return transformedImage;
		}
	public BufferedImage AlphaImage(BufferedImage image,int alphaValue){
		BufferedImage transformedImage=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
		int red=0,green=0,blue=0,alpha=0;
		
		Color color;
		for(int i=0;i<image.getWidth();i++){
			for(int j=0;j<image.getHeight();j++){
				color=obtainColor(image,i, j);
				red=color.red;
				green=color.green;
				blue=color.blue;
				alpha=alphaValue;
				transformedImage.setRGB(i,j, ((alpha<<24)|(red<<16)|(green<<8)|blue));
			}
		}
			return transformedImage;
	}
	public BufferedImage RotateImage(BufferedImage image){
		BufferedImage transformedImage=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
		int red=0,green=0,blue=0,alpha=0;
		
		Color color;
		for(int i=0;i<image.getWidth();i++){
			for(int j=0;j<image.getHeight();j++){
				color=obtainColor(image,i, j);
				//System.out.print(color.red+" "+color.green+" "+color.blue+" ");
				red=rotateRight( color.red,1)&64;
				green= rotateRight( color.green,1)&64;;
				blue= rotateRight( color.blue,1)&64;;
				alpha= color.alpha;
				//System.out.println(red+" "+green+" "+blue);
				transformedImage.setRGB(i,j, ((alpha<<24)|(red<<16)|(green<<8)|blue));
			}
		}
			return transformedImage;
	}
	public static int rotateRight(int value,int number){
		byte numbits=1;
		for(int i=0;i<number-1;i++){
			numbits=(byte)((numbits<<1)|1);
		}
		//System.out.println(numbits);
		return (value >>> number) | ((value& numbits) << (8 - number));
	}
	
	public Color obtainColor(BufferedImage image,int i,int j){
		Color colr=new Color();
		int color=image.getRGB(i, j);
	    colr.alpha=((color>>24)&255);
	    colr.red=((color>>16)&255);
	    colr.green=((color>>8)&255);
	    colr.blue=(color&255);
	    return colr;
	}
}


