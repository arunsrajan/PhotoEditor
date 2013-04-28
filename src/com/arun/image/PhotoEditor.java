package com.arun.image;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.arun.image.utils.ImageUtils;

public class PhotoEditor {
	public static void main(String[] args){
		 ArrayList photoFrameUndoList=new ArrayList();
		ArrayList photoFrameRedoList=new ArrayList();
		JFrame photoFrame=new JFrame();
		Container contentPane=photoFrame.getContentPane();
		PhotoImagePanel photoImagePanel=new PhotoImagePanel();
		contentPane.add(photoImagePanel);
		JMenuBar menubar=new JMenuBar();
		photoFrame.setJMenuBar(menubar);
		JMenu fileMenu=new JMenu("File");
		JMenuItem openItem=new JMenuItem("Open");
		JMenuItem saveAsItem=new JMenuItem("Save As");
		FileMenuItemListener filemenutItemListener=new FileMenuItemListener(photoFrame,photoImagePanel,photoFrameUndoList,photoFrameRedoList);
		openItem.addActionListener(filemenutItemListener);
		saveAsItem.addActionListener(filemenutItemListener);	
		fileMenu.add(openItem);
		fileMenu.add(saveAsItem);
		
		EditMenuItemListener editmenutItemListener=new EditMenuItemListener(photoFrame,photoImagePanel,photoFrameUndoList,photoFrameRedoList);
		JMenu editMenu=new JMenu("Edit");
		JMenuItem undoItem=new JMenuItem("Undo");
		JMenuItem redoItem=new JMenuItem("Redo");
		undoItem.addActionListener(editmenutItemListener);
		redoItem.addActionListener(editmenutItemListener);
		editMenu.add(undoItem);
		editMenu.add(redoItem);
		

		ImageEffectMenuItemListener imageEffectItemListener=new ImageEffectMenuItemListener(photoFrame,photoImagePanel,photoFrameUndoList,photoFrameRedoList);
		JMenu effectsMenu=new JMenu("Effects");
		JMenu smoothMenu=new JMenu("Smooth");
		JMenuItem meansmoothItem=new JMenuItem("MeanSmooth");
		JMenuItem mediansmoothItem=new JMenuItem("MedianSmooth");
		meansmoothItem.addActionListener(imageEffectItemListener);
		mediansmoothItem.addActionListener(imageEffectItemListener);
		smoothMenu.add(meansmoothItem);
		smoothMenu.add(mediansmoothItem);
		effectsMenu.add(smoothMenu);
		
		
		JMenuItem grayscaleItem=new JMenuItem("GrayScale");
		effectsMenu.add(grayscaleItem);
		grayscaleItem.addActionListener(imageEffectItemListener);
		
		
		
		JMenuItem negativeItem=new JMenuItem("Negative");
		effectsMenu.add(negativeItem);
		negativeItem.addActionListener(imageEffectItemListener);
		
		
		
		JMenuItem sharpItem=new JMenuItem("Sharp");
		effectsMenu.add(sharpItem);
		sharpItem.addActionListener(imageEffectItemListener);
		
		
		JMenuItem edgeItem=new JMenuItem("EdgeDetect");
		effectsMenu.add(edgeItem);
		edgeItem.addActionListener(imageEffectItemListener);
		
		
		JMenuItem glassItem=new JMenuItem("Glass");
		effectsMenu.add(glassItem);
		glassItem.addActionListener(imageEffectItemListener);

		JMenu warpMenu=new JMenu("Warp");
		effectsMenu.add(warpMenu);		
		JMenuItem warpMenuItem=new JMenuItem("Warp1");
		warpMenu.add(warpMenuItem);
		warpMenuItem.addActionListener(imageEffectItemListener);
		JMenuItem warpMenu1Item=new JMenuItem("Warp2");
		warpMenu.add(warpMenu1Item);
		warpMenu1Item.addActionListener(imageEffectItemListener);
		
		JMenu colorInterchangeMenu=new JMenu("Interchange");
		effectsMenu.add(colorInterchangeMenu);
		JMenuItem rgItem=new JMenuItem("RG");
		colorInterchangeMenu.add(rgItem);
		rgItem.addActionListener(imageEffectItemListener);
		JMenuItem gbItem=new JMenuItem("GB");
		colorInterchangeMenu.add(gbItem);
		gbItem.addActionListener(imageEffectItemListener);
		JMenuItem rbItem=new JMenuItem("RB");
		colorInterchangeMenu.add(rbItem);
		rbItem.addActionListener(imageEffectItemListener);
		
		JMenuItem alphaMenuItem=new JMenuItem("Alpha");
		alphaMenuItem.addActionListener(imageEffectItemListener);
		effectsMenu.add(alphaMenuItem);
		
		JMenuItem rotateItem=new JMenuItem("RotateImage");
		rotateItem.addActionListener(imageEffectItemListener);
		effectsMenu.add(rotateItem);
		
		
		menubar.add(fileMenu);
		menubar.add(editMenu);
		menubar.add(effectsMenu);
		photoFrame.setBounds(10,10,200,200);
		photoFrame.setVisible(true);
	}
	
	
}
class FileMenuItemListener implements ActionListener{

	JFrame photoFrame;
	PhotoImagePanel panel;
	ArrayList photoFrameUndoList;
	ArrayList photoFrameRedoList;
	
	public FileMenuItemListener(JFrame photoFrame,PhotoImagePanel panel,ArrayList photoFrameUndoList,ArrayList photoFrameRedoList){
		this.photoFrame=photoFrame;
		this.panel=panel;
		this.photoFrameUndoList=photoFrameUndoList;
		this.photoFrameRedoList=photoFrameRedoList;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Open")){
			JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "JPG & GIF Images", "jpg", "gif", "png");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showOpenDialog(panel);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	PhotoImagePanel imagepanel=new PhotoImagePanel();
		    	try{
		    		
		    		BufferedImage img=ImageIO.read(chooser.getSelectedFile().getAbsoluteFile());
		    		this.panel.setBufferedImage(img);
		    		this.photoFrame.setBounds(this.photoFrame.getX(), this.photoFrame.getX(), img.getWidth(), img.getHeight());
		    		this.photoFrame.repaint();
		    		this.panel.repaint();
		    		photoFrameUndoList.add(img);
		    		//photoFrameRedoList.clear();
		    	}
		    	catch(Exception ex){
		    		System.out.println(ex);
		    	}
		    }
		}
		else if(e.getActionCommand().equals("Save As")){

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Specify a file to save");    
			
			int userSelection = fileChooser.showSaveDialog(this.photoFrame);
			
			if (userSelection == JFileChooser.APPROVE_OPTION) {
			    File fileToSave = fileChooser.getSelectedFile();
			    try {
					ImageIO.write((BufferedImage)photoFrameUndoList.get(photoFrameUndoList.size()-1), "png", new File(fileToSave.getAbsolutePath()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
	}
}

class ImageEffectMenuItemListener implements ActionListener{

	JFrame photoFrame;
	PhotoImagePanel panel;
	ArrayList photoFrameUndoList;
	ArrayList photoFrameRedoList;
	
	public ImageEffectMenuItemListener(JFrame photoFrame,PhotoImagePanel panel,ArrayList photoFrameUndoList,ArrayList photoFrameRedoList){
		this.photoFrame=photoFrame;
		this.panel=panel;
		this.photoFrameUndoList=photoFrameUndoList;
		this.photoFrameRedoList=photoFrameRedoList;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("MeanSmooth")){
			if(photoFrameUndoList.size()==0){
		    	Object[] options = { "OK", "CANCEL" };
		    	JOptionPane.showOptionDialog(null, "Please select an image to apply effects", "Warning",

		    	        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,

		    	        null, options, options[0]);
		    	return;
		    }
				//photoFrameRedoList.add(photoFrameUndoList.remove(photoFrameUndoList.size()-1));
	    	BufferedImage transimg=(BufferedImage) new ImageUtils().SmoothMeanImage((BufferedImage)photoFrameUndoList.get(photoFrameUndoList.size()-1));
	    	photoFrameUndoList.add(transimg);
	    	this.panel.setBufferedImage(transimg);
	    	this.photoFrame.setBounds(this.photoFrame.getX(), this.photoFrame.getX(), transimg.getWidth(), transimg.getHeight());
	    	this.photoFrame.repaint();
	    	this.panel.repaint();
	    		
		}
		else if(e.getActionCommand().equals("MedianSmooth")){
			if(photoFrameUndoList.size()==0){
		    	Object[] options = { "OK", "CANCEL" };
		    	JOptionPane.showOptionDialog(null, "Please select an image to apply effects", "Warning",

		    	        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,

		    	        null, options, options[0]);
		    	return;
		    }
			BufferedImage transimg=(BufferedImage) new ImageUtils().SmoothMedianImage((BufferedImage)photoFrameUndoList.get(photoFrameUndoList.size()-1));
    		photoFrameUndoList.add(transimg);
    		this.panel.setBufferedImage(transimg);
    		this.photoFrame.setBounds(this.photoFrame.getX(), this.photoFrame.getX(), transimg.getWidth(), transimg.getHeight());
    		this.photoFrame.repaint();
    		this.panel.repaint();
		}
		else if(e.getActionCommand().equals("GrayScale")){
			if(photoFrameUndoList.size()==0){
		    	Object[] options = { "OK", "CANCEL" };
		    	JOptionPane.showOptionDialog(null, "Please select an image to apply effects", "Warning",

		    	        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,

		    	        null, options, options[0]);
		    	return;
		    }
			BufferedImage transimg=(BufferedImage) new ImageUtils().GrayScale((BufferedImage)photoFrameUndoList.get(photoFrameUndoList.size()-1));
    		photoFrameUndoList.add(transimg);
    		this.panel.setBufferedImage(transimg);
    		this.photoFrame.setBounds(this.photoFrame.getX(), this.photoFrame.getX(), transimg.getWidth(), transimg.getHeight());
    		this.photoFrame.repaint();
    		this.panel.repaint();
		}
		else if(e.getActionCommand().equals("Negative")){
			if(photoFrameUndoList.size()==0){
		    	Object[] options = { "OK", "CANCEL" };
		    	JOptionPane.showOptionDialog(null, "Please select an image to apply effects", "Warning",

		    	        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,

		    	        null, options, options[0]);
		    	return;
		    }
			BufferedImage transimg=(BufferedImage) new ImageUtils().Negative((BufferedImage)photoFrameUndoList.get(photoFrameUndoList.size()-1));
    		photoFrameUndoList.add(transimg);
    		this.panel.setBufferedImage(transimg);
    		this.photoFrame.setBounds(this.photoFrame.getX(), this.photoFrame.getX(), transimg.getWidth(), transimg.getHeight());
    		this.photoFrame.repaint();
    		this.panel.repaint();
		}
		
		else if(e.getActionCommand().equals("Sharp")){
			if(photoFrameUndoList.size()==0){
		    	Object[] options = { "OK", "CANCEL" };
		    	JOptionPane.showOptionDialog(null, "Please select an image to apply effects", "Warning",

		    	        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,

		    	        null, options, options[0]);
		    	return;
		    }
			BufferedImage transimg=(BufferedImage) new ImageUtils().SharpImage((BufferedImage)photoFrameUndoList.get(photoFrameUndoList.size()-1));
    		photoFrameUndoList.add(transimg);
    		this.panel.setBufferedImage(transimg);
    		this.photoFrame.setBounds(this.photoFrame.getX(), this.photoFrame.getX(), transimg.getWidth(), transimg.getHeight());
    		this.photoFrame.repaint();
    		this.panel.repaint();
		}
		
		else if(e.getActionCommand().equals("EdgeDetect")){
			if(photoFrameUndoList.size()==0){
		    	Object[] options = { "OK", "CANCEL" };
		    	JOptionPane.showOptionDialog(null, "Please select an image to apply effects", "Warning",

		    	        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,

		    	        null, options, options[0]);
		    	return;
		    }
			BufferedImage transimg=(BufferedImage) new ImageUtils().EdgeDetect((BufferedImage)photoFrameUndoList.get(photoFrameUndoList.size()-1));
    		photoFrameUndoList.add(transimg);
    		this.panel.setBufferedImage(transimg);
    		this.photoFrame.setBounds(this.photoFrame.getX(), this.photoFrame.getX(), transimg.getWidth(), transimg.getHeight());
    		this.photoFrame.repaint();
    		this.panel.repaint();
		}
		else if(e.getActionCommand().equals("Glass")){
			if(photoFrameUndoList.size()==0){
		    	Object[] options = { "OK", "CANCEL" };
		    	JOptionPane.showOptionDialog(null, "Please select an image to apply effects", "Warning",

		    	        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,

		    	        null, options, options[0]);
		    	return;
		    }
			BufferedImage transimg=(BufferedImage) new ImageUtils().GlassImage((BufferedImage)photoFrameUndoList.get(photoFrameUndoList.size()-1));
    		photoFrameUndoList.add(transimg);
    		this.panel.setBufferedImage(transimg);
    		this.photoFrame.setBounds(this.photoFrame.getX(), this.photoFrame.getX(), transimg.getWidth(), transimg.getHeight());
    		this.photoFrame.repaint();
    		this.panel.repaint();
		}
		else if(e.getActionCommand().equals("Warp1")){
			if(photoFrameUndoList.size()==0){
		    	Object[] options = { "OK", "CANCEL" };
		    	JOptionPane.showOptionDialog(null, "Please select an image to apply effects", "Warning",

		    	        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,

		    	        null, options, options[0]);
		    	return;
		    }
			BufferedImage transimg=(BufferedImage) new ImageUtils().Warp1Image((BufferedImage)photoFrameUndoList.get(photoFrameUndoList.size()-1));
    		photoFrameUndoList.add(transimg);
    		this.panel.setBufferedImage(transimg);
    		this.photoFrame.setBounds(this.photoFrame.getX(), this.photoFrame.getX(), transimg.getWidth(), transimg.getHeight());
    		this.photoFrame.repaint();
    		this.panel.repaint();
		}
		else if(e.getActionCommand().equals("Warp2")){
			if(photoFrameUndoList.size()==0){
		    	Object[] options = { "OK", "CANCEL" };
		    	JOptionPane.showOptionDialog(null, "Please select an image to apply effects", "Warning",

		    	        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,

		    	        null, options, options[0]);
		    	return;
		    }
			BufferedImage transimg=(BufferedImage) new ImageUtils().Warp2Image((BufferedImage)photoFrameUndoList.get(photoFrameUndoList.size()-1));
    		photoFrameUndoList.add(transimg);
    		this.panel.setBufferedImage(transimg);
    		this.photoFrame.setBounds(this.photoFrame.getX(), this.photoFrame.getX(), transimg.getWidth(), transimg.getHeight());
    		this.photoFrame.repaint();
    		this.panel.repaint();
		}
		else if(e.getActionCommand().equals("RG")){
			if(photoFrameUndoList.size()==0){
		    	Object[] options = { "OK", "CANCEL" };
		    	JOptionPane.showOptionDialog(null, "Please select an image to apply effects", "Warning",

		    	        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,

		    	        null, options, options[0]);
		    	return;
		    }
			BufferedImage transimg=(BufferedImage) new ImageUtils().InterchangeColorImage((BufferedImage)photoFrameUndoList.get(photoFrameUndoList.size()-1),ImageUtils.INTERCHANGERG);
    		photoFrameUndoList.add(transimg);
    		this.panel.setBufferedImage(transimg);
    		this.photoFrame.setBounds(this.photoFrame.getX(), this.photoFrame.getX(), transimg.getWidth(), transimg.getHeight());
    		this.photoFrame.repaint();
    		this.panel.repaint();
		}
		else if(e.getActionCommand().equals("GB")){
			if(photoFrameUndoList.size()==0){
		    	Object[] options = { "OK", "CANCEL" };
		    	JOptionPane.showOptionDialog(null, "Please select an image to apply effects", "Warning",

		    	        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,

		    	        null, options, options[0]);
		    	return;
		    }
			BufferedImage transimg=(BufferedImage) new ImageUtils().InterchangeColorImage((BufferedImage)photoFrameUndoList.get(photoFrameUndoList.size()-1),ImageUtils.INTERCHANGEGB);
    		photoFrameUndoList.add(transimg);
    		this.panel.setBufferedImage(transimg);
    		this.photoFrame.setBounds(this.photoFrame.getX(), this.photoFrame.getX(), transimg.getWidth(), transimg.getHeight());
    		this.photoFrame.repaint();
    		this.panel.repaint();
		}
		else if(e.getActionCommand().equals("RB")){
			if(photoFrameUndoList.size()==0){
		    	Object[] options = { "OK", "CANCEL" };
		    	JOptionPane.showOptionDialog(null, "Please select an image to apply effects", "Warning",

		    	        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,

		    	        null, options, options[0]);
		    	return;
		    }
			BufferedImage transimg=(BufferedImage) new ImageUtils().InterchangeColorImage((BufferedImage)photoFrameUndoList.get(photoFrameUndoList.size()-1),ImageUtils.INTERCHANGERB);
    		photoFrameUndoList.add(transimg);
    		this.panel.setBufferedImage(transimg);
    		this.photoFrame.setBounds(this.photoFrame.getX(), this.photoFrame.getX(), transimg.getWidth(), transimg.getHeight());
    		this.photoFrame.repaint();
    		this.panel.repaint();
		}
		else if(e.getActionCommand().equals("Alpha")){
			if(photoFrameUndoList.size()==0){
		    	Object[] options = { "OK", "CANCEL" };
		    	JOptionPane.showOptionDialog(null, "Please select an image to apply effects", "Warning",

		    	        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,

		    	        null, options, options[0]);
		    	return;
		    }
			JOptionPane optionPane = new JOptionPane();
			JSlider slider = getSlider(optionPane);
		    optionPane.setMessage(new Object[] { "Select a value: ", slider });
		    optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
		    optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
		    JDialog dialog = optionPane.createDialog(this.photoFrame, "Alpha");
		    dialog.setVisible(true);
		    int alpha=(int) optionPane.getInputValue();
			BufferedImage transimg=(BufferedImage) new ImageUtils().AlphaImage((BufferedImage)photoFrameUndoList.get(photoFrameUndoList.size()-1),alpha);
    		photoFrameUndoList.add(transimg);
    		this.panel.setBufferedImage(transimg);
    		this.photoFrame.setBounds(this.photoFrame.getX(), this.photoFrame.getX(), transimg.getWidth(), transimg.getHeight());
    		this.photoFrame.repaint();
    		this.panel.repaint();
		}
		
		else if(e.getActionCommand().equals("RotateImage")){
			if(photoFrameUndoList.size()==0){
		    	Object[] options = { "OK", "CANCEL" };
		    	JOptionPane.showOptionDialog(null, "Please select an image to apply effects", "Warning",

		    	        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,

		    	        null, options, options[0]);
		    	return;
		    }
			BufferedImage transimg=(BufferedImage) new ImageUtils().RotateImage((BufferedImage)photoFrameUndoList.get(photoFrameUndoList.size()-1));
    		photoFrameUndoList.add(transimg);
    		this.panel.setBufferedImage(transimg);
    		this.photoFrame.setBounds(this.photoFrame.getX(), this.photoFrame.getX(), transimg.getWidth(), transimg.getHeight());
    		this.photoFrame.repaint();
    		this.panel.repaint();
		}
		
	}
	
	static JSlider getSlider(final JOptionPane optionPane) {
	    JSlider slider = new JSlider(0,255);
	    slider.setMajorTickSpacing(1);
	    slider.setPaintTicks(true);
	    slider.setPaintLabels(true);
	    slider.setValue(255);
	    ChangeListener changeListener = new ChangeListener() {
	      public void stateChanged(ChangeEvent changeEvent) {
	        JSlider theSlider = (JSlider) changeEvent.getSource();
	        if (!theSlider.getValueIsAdjusting()) {
	          optionPane.setInputValue(new Integer(theSlider.getValue()));
	        }
	      }
	    };
	    slider.addChangeListener(changeListener);
	    return slider;
	  }
}


class EditMenuItemListener implements ActionListener{

	JFrame photoFrame;
	PhotoImagePanel panel;
	ArrayList photoFrameUndoList;
	ArrayList photoFrameRedoList;
	
	public EditMenuItemListener(JFrame photoFrame,PhotoImagePanel panel,ArrayList photoFrameUndoList,ArrayList photoFrameRedoList){
		this.photoFrame=photoFrame;
		this.panel=panel;
		this.photoFrameUndoList=photoFrameUndoList;
		this.photoFrameRedoList=photoFrameRedoList;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Undo")){
			if(photoFrameUndoList.size()>1){
	    		photoFrameRedoList.add(photoFrameUndoList.remove(photoFrameUndoList.size()-1));
	    		BufferedImage img=(BufferedImage) photoFrameUndoList.get(photoFrameUndoList.size()-1);
	    		this.panel.setBufferedImage(img);
	    		this.photoFrame.setBounds(this.photoFrame.getX(), this.photoFrame.getX(), img.getWidth(), img.getHeight());
	    		this.photoFrame.repaint();
	    		this.panel.repaint();
	    		
			}
		}
		else if(e.getActionCommand().equals("Redo")){
			if(photoFrameRedoList.size()>0){
	    		photoFrameUndoList.add(photoFrameRedoList.remove(photoFrameRedoList.size()-1));
	    		BufferedImage img=(BufferedImage) photoFrameUndoList.get(photoFrameUndoList.size()-1);
	    		this.panel.setBufferedImage(img);
	    		this.photoFrame.setBounds(this.photoFrame.getX(), this.photoFrame.getX(), img.getWidth(), img.getHeight());
	    		this.photoFrame.repaint();
	    		this.panel.repaint();
	    		
			}
		}
	}
}


class PhotoImagePanel extends JPanel{
	BufferedImage buffImage; 
	public PhotoImagePanel(){
		
	}
	public void setBufferedImage(BufferedImage buffImage){
		this.buffImage=buffImage;
	}
	public void paint(Graphics g){
		if(buffImage!=null)
		 g.drawImage(buffImage, 0,0,buffImage.getWidth(),buffImage.getHeight(), this);
	}
}
