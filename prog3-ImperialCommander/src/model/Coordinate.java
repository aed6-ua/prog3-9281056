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
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			sb.append(x);
			sb.append(",");
			sb.append(y);
			sb.append("]");
			String s = sb.toString();
			return s;
	}
	/**
	 * public Coordinate add(Coordinate c): Returns a Coordinate object with coordinates X and Y
	 * resulting form addition of c and current object corresponding coordinates.
	 * @param c : Object of class Coordinate whose X and Y coordinates are goingo to be added
	 * to current object's coordinates.
	 * @return Object of class Coordinate with resulting X and Y.
	 */
	public Coordinate add(Coordinate c) {
		Coordinate d = new Coordinate(c.x+this.x, c.y+this.y);
		return d;
	}
	/**
	 * public Coordinate add(int x, int y): Returns a Coordinate object with coordinates X and Y
	 * resulting form addition of x and y primitive integers and current object corresponding coordinates.
	 * @param x : int to be added to current object's X coordinate.
	 * @param y :int to be added to current object's Y coordinate.
	 * @return Object of class Coordinate with resulting X and Y.
	 */
	public Coordinate add(int x, int y) {
		Coordinate c = new Coordinate(x+this.x, y+this.y);
		return c;
	}
	/**
	 * 
	 * @return
	 */
	@Override
	public int CompareTo(Coordinate otra) {
		if (this.x>otra.x) {
			return -1;
		}
		if (this.x<otra.x) {
			return 1;
		}
		else {
			if (this.y>otra.y) {
				return -1;
			}
			if (this.y<otra.y) {
				return 1;
			}
			else return 0;
		}
	}
	
	public Set<Coordinate> getNeighborhood() {
		Set<Coordinate> ts = new TreeSet<>();
		Coordinate up = new Coordinate(this.x, this.y+1);
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
		return ts;
	}
}
