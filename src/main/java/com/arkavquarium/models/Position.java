package com.arkavquarium.models;

import java.lang.Math;

public class Position {
	/**
	 * Absis
	 */
	private double x;
	
	/**
	 * Ordinate
	 */
	private double y;
	
	/**
	 * Constructor initiate the position with random coordinate
	 */
	public Position() {
		this.random();
	}

	/**
	 * Constructor, initiate the position with x and y
	 * @param x is the absis
	 * @param y is the coordinate
	 */
	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the absis of the positon
	 */
	public double getAbsis() {
		return this.x;
	}
	
	/**
	 * @return the ordinate of the position
	 */
	public double getOrdinate() {
		return this.y;
	}
	
	/**
	 * Set the absis of the position
	 * @param x new absis of the position
	 */
	public void setAbsis(double x) {
		this.x = x;
	}

	/** 
	 * Set the ordinate of the position
	 * @param y new ordinate of the positon
	 */
	public void setOrdinate(double y) {
		this.y = y;
	}

	/**
	 * Set the position to random value with within maximal height and maximal width
	 */
	public void random() {
		int pad = Data.getMaxWidth() * 5 / 100;
		this.x = Math.random() * (Data.getMaxWidth()-2*pad) + pad;
		this.y = Math.random() * Data.getMaxHeight();
	}

	/**
	 * Set x and y to the nearest coordinate to destination but no more than pythagoran distance maxVelocity
	 * @param dest destination
	 * @param maxVelocity pythagorean distace
	 */
	public void move(Position dest, double maxVelocity) {
		double lenV;
		double Vx, Vy;
		double Dx, Dy;
		Vx = dest.getAbsis() - this.getAbsis();
		Vy = dest.getOrdinate() - this.getOrdinate();
		lenV = Math.sqrt(Math.pow(Vx,2) + Math.pow(Vy,2));	
    	lenV = Math.sqrt(Math.pow(Vx,2) + Math.pow(Vy,2));
    	Dx = (Math.min(maxVelocity, lenV) / lenV) * Vx;
    	Dy = (Math.min(maxVelocity, lenV) / lenV) * Vy;
    	this.x += Dx;
    	this.y += Dy;
	}

	/**
	 * Set x to the nearest absis to destination but no more than pythagoran distance maxVelocity
	 * @param dest destination
	 * @param maxVelocity pythagorean distace
	 */
	public void moveHorizontal(Position dest, double maxVelocity) {	
    	double dx;
    	dx = dest.getAbsis() - this.x;
    	if (dx < 0)
        	this.x += Math.max(-maxVelocity, dx);
    	else
        	this.x += Math.min(maxVelocity, dx);
	}

	/**
	 * Set y to the nearest coordinate to destination but no more than pythagoran distance maxVelocity
	 * @param dest destination
	 * @param maxVelocity pythagorean distace
	 */
	public void moveVertical(Position dest, double maxVelocity) {
    	double dy;
    	dy = dest.getOrdinate() - this.y;
    	if (dy < 0)
        	this.y += Math.max(-maxVelocity, dy);
    	else
        	this.y += Math.min(maxVelocity, dy);
	}
	
	/**
	 * Compare postion
	 * @param other comp the position to compare with
	 * @return true if both positions have same coordinate
	 */
	public boolean equals(Position other) {
		double deltaX = Math.abs(this.x - other.x);
		double deltaY = Math.abs(this.y - other.y);
		return (deltaX <= 3 && deltaY <= 3);
	}

	/**
	 * @param comp the end position
	 * @return the magnitude between to position comp
	 */
	public double magnitude(Position comp) {
		double lenV;
    	double Vx, Vy;
    	Vx = comp.getAbsis() - this.getAbsis();
    	Vy = comp.getOrdinate() - this.getOrdinate();
    	lenV = Math.sqrt(Math.pow(Vx, 2) + Math.pow(Vy, 2));
    	return lenV;
	}
	
	
	/**
	 * @param temp any position
	 * @return true if this position greater than temp
	 */
	public boolean isGreater(Position temp) {
		return this.getAbsis() > temp.getAbsis() && this.getOrdinate() > temp.getOrdinate();
	}
}
