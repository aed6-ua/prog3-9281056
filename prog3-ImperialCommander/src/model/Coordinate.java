package model;

import java.util.*;

/**
 * Coordinate: Represents a coordinate for tabletop game ImperialCommander
 * @author Eduard Andrei Duta | NIE: X9281056G
 *
 */

public class Coordinate implements Comparable<Coordinate>{
	/**
	 * X coordinate.
	 */
	private int x;
	/**
	 * Y coordinate.
	 */
	private int y;
	/**
	 * public Coordinate(int x, int y): Constructor that creates Coordinate instance with x and y values as coordinates.
	 * @param x : coordinate X.
	 * @param y : coordinate Y.
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * public Coordinate(Coordinate c):
	 * Copy constructor that creates a shallow copy with same X and Y values from
	 * input object.
	 * @param c : Coordinate object to be copied.
	 */
	public Coordinate(Coordinate c) {
		this.x = c.x;
		this.y = c.y;
	}
	/**
	 * public int getX(): Returns value of X coordinate.
	 * @return int value of X coordinate.
	 */
	public int getX() {
		return x;
	}
	/**
	 * public int getY(): Returns value of Y coordinate.
	 * @return int value of Y coordinate.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * 
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[" +
				x +
				"," +
				y +
				"]";
	}
	/**
	 * public Coordinate add(Coordinate c): Returns a Coordinate object with coordinates X and Y
	 * resulting form addition of c and current object corresponding coordinates.
	 * @param c : Object of class Coordinate whose X and Y coordinates are going to be added
	 * to current object's coordinates.
	 * @return Object of class Coordinate with resulting X and Y.
	 */
	public Coordinate add(Coordinate c) {
		return new Coordinate(c.x+ this.x, c.y+ this.y);
	}
	/**
	 * public Coordinate add(int x, int y): Returns a Coordinate object with coordinates X and Y
	 * resulting form addition of x and y primitive integers and current object corresponding coordinates.
	 * @param x : int to be added to current object's X coordinate.
	 * @param y :int to be added to current object's Y coordinate.
	 * @return Object of class Coordinate with resulting X and Y.
	 */
	public Coordinate add(int x, int y) {
		return new Coordinate(x+ this.x, y+ this.y);
	}
	/**
	 * Compares this coordinate with the specified coordinate. 
	 * @param otra coordinate to compare.
	 * @return -1 or 1 if the x coordinate is less than or greater than the specified coordinate x. If the x is equal
	 * checks the y coordinate and returns -1 or 1 if is less than or greater than the specified coordinate y. If both
	 * x and y are equals returns 0.
	 */
	@Override
	public int compareTo(Coordinate otra) {
		int result;
		/*if (this.x<otra.x) {
			return -1;
		}
		if (this.x>otra.x) {
			return 1;
		}*/
		if ((result = Integer.compare(this.x, otra.x)) == 0) {
			return Integer.compare(this.y, otra.y);
		}
		else {
			return result;
		}
	}
	/**
	 * Gets the surrounding coordinates around this coordinate.
	 * @return a TreeSet with the surrounding coordinates.
	 */
	public Set<Coordinate> getNeighborhood() {
		Set<Coordinate> ts = new TreeSet<>();
		
		/*Coordinate up = new Coordinate(this.x, this.y+1);
		Coordinate upl = new Coordinate(this.x-1, this.y+1);
		Coordinate upr = new Coordinate(this.x+1, this.y+1);
		Coordinate right = new Coordinate(this.x+1, this.y);
		Coordinate left = new Coordinate(this.x-1, this.y);
		Coordinate down = new Coordinate(this.x, this.y-1);
		Coordinate downl = new Coordinate(this.x-1, this.y-1);
		Coordinate downr = new Coordinate(this.x+1, this.y-1);
		ts.add(up);
		ts.add(upl);
		ts.add(upr);
		ts.add(right);
		ts.add(left);
		ts.add(down);
		ts.add(downl);
		ts.add(downr);
		return ts;*/
		
		for(int x=this.x-1; x<this.x+2; x++) {
			for(int y=this.y-1; y<this.y+2; y++) {
				Coordinate coor = new Coordinate(x,y);
				ts.add(coor);
			}
		}
		ts.removeIf(n -> n.equals(this));
		return ts;
	}
}
