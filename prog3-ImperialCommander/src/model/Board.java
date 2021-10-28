/**
 * 
 */
package model;
import java.util.*;
/**
 * Board used in the game ImperialCommander.
 * @author Eduard Andrei Duta | NIE: X9281056G
 *
 */
public class Board {
	/**
	 * Size of the board (NxN).
	 */
	private int size;
	/**
	 * Board. It stores values for the Coordinates and the Fighter that occupies that coordinate.
	 */
	private Map<Coordinate, Fighter> board;
	/**
	 * Constructor for the Board. Creates an empty board of the specified size.
	 * @param size of the board
	 */
	public Board(int size) {
		this.size = size;
		this.board = new HashMap<>();
	}
	/**
	 * Gets the size of the board.
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * Gets the Fighter that occupies specified Coordinate.
	 * @param c coordinate to check
	 * @return fighter or null if the coordinate is empty.
	 */
	public Fighter getFighter(Coordinate c) {
		Objects.requireNonNull(c);
		if (!(this.board.containsKey(c))) {
			return null;
		}
		else {
			return new Fighter(this.board.get(c));
		}
	}
	/**
	 * Removes the specified fighter from the board.
	 * @param f fighter to remove
	 * @return true or false if the fighter has been removed or not
	 */
	public boolean removeFighter(Fighter f) {
		Objects.requireNonNull(f);
		if (f.getPosition() == null) return false;
		if (!this.board.containsKey(f.getPosition())) return false;	
		if ((this.board.get(f.getPosition())).equals(f)) {
			this.board.remove(f.getPosition());
			f.setPosition(null);
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Checks if a coordinate is inside the board.
	 * @param c coordinate to check
	 * @return true or false if the coordinate is inside the board or not
	 */
	public boolean inside(Coordinate c) {
		if (c.getX()>= 0 && c.getX()<=(this.size - 1) && c.getX()>= 0 && c.getX()<=(this.size - 1) && Objects.nonNull(c)) {
			return c.getY() >= 0 && c.getY() <= (this.size - 1);
		}
		return false;
	}
	/**
	 * Gets the surrounding coordinates of the specified coordinate inside the board.
	 * @param c coordinate whose surroundings to return
	 * @return TreeSet with the surrounding coordinates inside the board.
	 */
	public Set<Coordinate> getNeighborhood(Coordinate c) {
		Objects.requireNonNull(c);
		Set<Coordinate> ts = new TreeSet<>(c.getNeighborhood());
		
		ts.removeIf(n -> !(this.inside(n)));
		
		return ts;
	}
	/**
	 * Puts specified fighter from a ships fleet on the board on the specified coordinate.
	 * If the coordinate is occupied by an enemy the fighter fights the enemy before. If it's occupied
	 * by an ally it does nothing.
	 * @param c coordinate to put the fighter
	 * @param f fighter to launch
	 * @return 0 if the fighter occupied an unoccupied coordinate, the result of the fight if it was occupied
	 * by an enemy fighter.
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
					if(this.removeFighter(this.board.get(c))) {
						this.board.put(c, f);
						f.setPosition(c);
					}
				}
				return result;
			
			}
		}
		else if (this.inside(c)) {
			this.board.put(c, f);
			f.setPosition(c);
		}
		return 0;
	}
	/**
	 * The specified fighter that occupies a coordinate checks surrounding coordinates and fights all
	 * enemy fighters.
	 * @param f fighter to patrol
	 */
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
