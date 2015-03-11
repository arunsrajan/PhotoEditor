package com.arun.image.utils;
/*Copyright 2013 - 2015, Arun_Soundararajan (arun_srajan_2007@yahoo.com).and/or its affiliates.

All files in this repository or distribution are licensed under the
Apache License, Version 2.0 (the "License");
you may not use any files in this repository or distribution except
in compliance with the License.

You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/
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
