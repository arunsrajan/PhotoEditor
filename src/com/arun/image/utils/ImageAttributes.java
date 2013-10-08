package com.arun.image.utils;

import java.awt.image.BufferedImage;
import java.io.File;

public class ImageAttributes implements Cloneable{
	private File file;
	private BufferedImage image;
	public File getFile() {
		return file;
	}
	public BufferedImage getImage() {
		return image; 
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public void setFile(File file) {
		this.file = file;
	}
	private double mean;
	private double standardDeviation;
	private double energy;
	private double entropy;
	private double contrast;
	private double homogeneity;
	public double getHomogeneity() {
		return homogeneity;
	}
	public void setHomogeneity(double homogeneity) {
		this.homogeneity = homogeneity;
	}
	public double getMean() {
		return mean;
	}
	public void setMean(double mean) {
		this.mean = mean;
	}
	public double getStandardDeviation() {
		return standardDeviation;
	}
	public void setStandardDeviation(double standardDeviation) {
		this.standardDeviation = standardDeviation;
	}
	public double getEnergy() {
		return energy;
	}
	public void setEnergy(double energy) {
		this.energy = energy;
	}
	public double getEntropy() {
		return entropy;
	}
	public void setEntropy(double entropy) {
		this.entropy = entropy;
	}
	public double getContrast() {
		return contrast;
	}
	public void setContrast(double contrast) {
		this.contrast = contrast;
	}
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	public String toString(){
		return "Energy: "+getEnergy()+" Mean:"+getMean()+" StandardDeviation"+getStandardDeviation()+" Entropy"+getEntropy()+" Contrast"+getContrast()+" Homogeneity:"+getHomogeneity();
		
	}
}
