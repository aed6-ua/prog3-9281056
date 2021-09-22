package model;

public class Coordinate {
	private int x;
	private int y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coordinate(Coordinate c) {
		this.x = c.x;
		this.y = c.y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean equals(Object obj) {
			if (obj == this) return true;
			if (obj == null) return false;
			if (!(obj instanceof Coordinate)) return false;
			Coordinate elotro = (Coordinate)obj;
			if (elotro.x==this.x && elotro.y==this.y) return true;
			else return false;
	}
	
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
	
	public Coordinate add(Coordinate c) {
		Coordinate d = new Coordinate(c.x+this.x, c.y+this.y);
		return d;
	}
	
	public Coordinate add(int x, int y) {
		Coordinate c = new Coordinate(x+this.x, y+this.y);
		return c;
	}
}
