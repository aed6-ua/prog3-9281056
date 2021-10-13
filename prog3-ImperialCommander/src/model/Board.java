/**
 * 
 */
package model;
import java.util.*;
/**
 * @author edu
 *
 */
public class Board {
	/**
	 * 
	 */
	private int size;
	/**
	 * 
	 */
	private Map<Coordinate, Fighter> board;
	/**
	 * 
	 * @param size
	 */
	public Board(int size) {
		Objects.requireNonNull(size);
		this.size = size;
		this.board = new HashMap<>();
	}
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * 
	 * @param c
	 * @return
	 */
	public Fighter getFighter(Coordinate c) {
		Objects.requireNonNull(c);
		if (!(this.board.containsKey(c))) {
			return null;
		}
		else {
			Fighter f = new Fighter(this.board.get(c));
			return f;
		}
	}
	/**
	 * 
	 * @param f
	 * @return
	 */
	public boolean removeFighter(Fighter f) {
		Objects.requireNonNull(f);
		if (f.getPosition() == null) return false;
		if (!this.board.containsKey(f.getPosition())) return false;	
		if ((this.board.get(f.getPosition())).equals(f)) {
			this.board.remove(f.getPosition());
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * 
	 * @param c
	 * @return
	 */
	public boolean inside(Coordinate c) {
		if (c.getX()>= 0 && c.getX()<=(this.size - 1) && c.getX()>= 0 && c.getX()<=(this.size - 1) && Objects.nonNull(c)) {
			if (c.getY()>= 0 && c.getY()<=(this.size - 1) && c.getY()>= 0 && c.getY()<=(this.size - 1)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @param c
	 * @return
	 */
	public Set<Coordinate> getNeighborhood(Coordinate c) {
		Objects.requireNonNull(c);
		Set<Coordinate> ts = new TreeSet<>(c.getNeighborhood());
		
		ts.removeIf(n -> !(this.inside(n)));
		
		return ts;
	}
	/**
	 * 
	 * @param c
	 * @param f
	 * @return
	 */
	public int launch(Coordinate c, Fighter f) {
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		
		if (this.inside(c) && (this.board.containsKey(c))) {
			if (!(this.board.get(c).getSide().equals(f.getSide()))) {
				int result = f.fight(this.board.get(c));
				f.getMotherShip().updateResults(result);
				this.getFighter(c).getMotherShip().updateResults(-result);
				if (!f.isDestroyed()) {
					if(this.removeFighter(this.board.get(c)))
						this.board.put(c, f);
						f.setPosition(c);
						return result;
			}
			
			}
		}
		else if (this.inside(c)) {
			this.board.put(c, f);
			f.setPosition(c);
		}
		return 0;
	}
	
	public void patrol(Fighter f) {
		Objects.requireNonNull(f);
		if (this.board.containsKey(f.getPosition())) {
			for (Coordinate i : this.getNeighborhood(f.getPosition())) {
				if (this.board.containsKey(i)) {
					if (!(this.board.get(i).getSide().equals(f.getSide()))) {
						int result =f.fight(this.board.get(i));
						f.getMotherShip().updateResults(result);
						this.board.get(i).getMotherShip().updateResults(-result);
						if (f.isDestroyed()) {
							this.removeFighter(f);
							break;
						}
						else {
							this.removeFighter(this.board.get(i));
						}
					}
				}
				
			}
		}
	}
}
