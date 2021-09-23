package model;
/**
 * Coordinate: representa una coordenada en el tablero del juego ImperialCommander
 * @author Eduard Andrei Duta | NIE: X9281056G
 *
 */

public class Coordinate {
	private int x;
	private int y;
	/**
	 * Constructor: crea una instancia de la clase Coordinate con valores x e y
	 * @param x
	 * @param y
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * Constructor de copia: crea una instancia de la clase Coordinate con los 
	 * mismos valores que el objeto de la misma clase que se le pasa.
	 * @param c
	 */
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
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

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
