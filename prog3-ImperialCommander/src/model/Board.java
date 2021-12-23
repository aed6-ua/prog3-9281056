/**
 * 
 */
package model;
import model.exceptions.*;

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
	protected Map<Coordinate, Fighter> board;
	/**
	 * Constructor for the Board. Creates an empty board of the specified size.
	 * @param size of the board
	 * @throws InvalidSizeException sizes lower than 5 not supported
	 */
	public Board(int size) throws InvalidSizeException {
		if (size<5) throw new InvalidSizeException(size);
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
			Fighter f = this.board.get(c);
			return f.copy();
		}
	}
	/**
	 * Removes the specified fighter from the board.
	 * @param f fighter to remove
	 * @throws FighterNotInBoardException if the specified fighter is not on the board
	 */
	public void removeFighter(Fighter f) throws FighterNotInBoardException {
		Objects.requireNonNull(f);
		if (!this.board.containsKey(f.getPosition())) {
			throw new FighterNotInBoardException(f);
		}
		if ((this.board.get(f.getPosition())).equals(f)) {
			this.board.remove(f.getPosition());
			f.setPosition(null);
		}
		else {
			throw new FighterNotInBoardException(f);
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
	 * @throws OutOfBoundsException if the specified coordinate is outside of the board
	 */
	public Set<Coordinate> getNeighborhood(Coordinate c) throws OutOfBoundsException {
		Objects.requireNonNull(c);
		if (!this.inside(c)) {
			throw new OutOfBoundsException(c);
		}
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
	 * @throws FighterAlreadyInBoardException if the specified fighter is already on the board
	 * @throws OutOfBoundsException if the specified coordinate is outside of the board
	 */
	public int launch(Coordinate c, Fighter f) throws FighterAlreadyInBoardException, OutOfBoundsException {
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		if (f.getPosition() != null) throw new FighterAlreadyInBoardException(f);
		if (!this.inside(c)) throw new OutOfBoundsException(c);
		if ((this.board.containsKey(c))) {
			if (!(this.board.get(c).getSide().equals(f.getSide()))) {
				int result;
				try {
					result = f.fight(this.board.get(c));
				} catch (FighterIsDestroyedException e) {
					e.getMessage();
					throw new RuntimeException();
				}
				if (f.isDestroyed()) {
					f.getMotherShip().updateResults(result,f);
					this.getFighter(c).getMotherShip().updateResults(-result,f);
				}

				if (!f.isDestroyed()) {
					try {
						f.getMotherShip().updateResults(result,this.getFighter(c));
						this.getFighter(c).getMotherShip().updateResults(-result,this.getFighter(c));
						this.removeFighter(this.board.get(c));


					} catch (FighterNotInBoardException e) {
						e.getMessage();
						throw new RuntimeException();
					}
					this.board.put(c, f);
					f.setPosition(c);
				}
				return result;
			
			}
		}
		else {
			this.board.put(c, f);
			f.setPosition(c);
		}
		return 0;
	}
	/**
	 * The specified fighter that occupies a coordinate checks surrounding coordinates and fights all
	 * enemy fighters.
	 * @param f fighter to patrol
	 * @throws FighterNotInBoardException if the specified fighter if not on the board
	 */
	public void patrol(Fighter f) throws FighterNotInBoardException {
		Objects.requireNonNull(f);
		if (this.board.containsKey(f.getPosition())) {
			try {
				for (Coordinate i : this.getNeighborhood(f.getPosition())) {
					if (this.board.containsKey(i)) {
						if (!(this.board.get(i).getSide().equals(f.getSide()))) {
							int result;
							try {
								result = f.fight(this.board.get(i));
							} catch (FighterIsDestroyedException e) {
								e.getMessage();
								throw new RuntimeException();
							}

							if (f.isDestroyed()) {
								try {
									f.getMotherShip().updateResults(result,f);
									this.board.get(i).getMotherShip().updateResults(-result,f);
									this.removeFighter(f);
								} catch (FighterNotInBoardException e) {
									e.getMessage();
									throw new RuntimeException();
								}
								break;
							}
							else {
								try {
									f.getMotherShip().updateResults(result,this.board.get(i));
									this.board.get(i).getMotherShip().updateResults(-result,this.board.get(i));
									this.removeFighter(this.board.get(i));
								} catch (FighterNotInBoardException e) {
									e.getMessage();
									throw new RuntimeException();
								}
							}
						}
					}

				}
			} catch (OutOfBoundsException e) {
				e.getMessage();
				throw new RuntimeException();
			}
		}
		else if (Objects.isNull(f.getPosition())) {
			throw new FighterNotInBoardException(f);
		}
		else {
			throw new RuntimeException();
		}
	}
}
