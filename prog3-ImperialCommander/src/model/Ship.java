/**
 * 
 */
package model;

import model.exceptions.NoFighterAvailableException;

import java.util.*;
/**
 * The mothership of ImperialCommander that stores a fleet of fighters of the same side
 *  and counts wins and losses of those fighters. 
 * @author Eduard Andrei Duta | NIE: X9281056G
 *
 */
public class Ship {
	/**
	 * The name of the ship.
	 */
	private String name;
	/**
	 * The side of the ship (imperial or rebel).
	 */
	private Side side;
	/**
	 * Number of won battles.
	 */
	protected int wins;
	/**
	 * Number of lost battles.
	 */
	protected int losses;
	/**
	 * List of fighters assigned to the ship.
	 */
	protected ArrayList<Fighter> fleet;
	/**
	 * Constructor of Ship. Creates a ship with the specified name and side with wins and losses
	 * initialized at 0 and an empty fleet.
	 * @param name of the ship
	 * @param side of the ship
	 */
	public Ship(String name,Side side) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(side);
		this.name = name;
		this.side = side;
		this.wins = 0;
		this.losses = 0;
		this.fleet = new ArrayList<>();
	}
	/**
	 * Gets the name of the ship.
	 * @return the name (String)
	 */
	public String getName() {
		return name;
	}
	/**
	 * Gets the side of the ship.
	 * @return the side (Side object)
	 */
	public Side getSide() {
		return side;
	}
	/**
	 * Gets the number of won battles of the ship.
	 * @return the wins (int)
	 */
	public int getWins() {
		return wins;
	}
	/**
	 * Gets the number of lost battles of the ship.
	 * @return the losses (int)
	 */
	public int getLosses() {
		return losses;
	}
	/**
	 * Gets the fleet list. For test purposes only.
	 * @return the fleet (List of Fighter)
	 */
	public List<Fighter> getFleetTest() {
		return fleet;
	}
	/**
	 * Adds a list of fighters to the ship specified as a string with the next format:
	 * "numberOfFighters/typeOfFighter". Multiple types of fighter can be added divided by ":" as it follows:
	 * "numberOfFighters1/typeOfFighter1:numberOfFighters2/typeOfFighter2". Example: "5/XWing:12/AWing:3/YWing:2/XWing"
	 * @param fd string of fighters to add
	 */
	public void addFighters(String fd) {
		Objects.requireNonNull(fd);
		String[] fighters = fd.split(":");
		for(String s : fighters) {
			String[] type = s.split("\\/");
			int quant = Integer.parseInt(type[0]);
			for(int i=0; i<quant; i++) {
				Fighter f = FighterFactory.createFighter(type[1],this);
				if (!Objects.isNull(f)) this.fleet.add(f);
			}
		}
	}
	/**
	 * Updates the number of won or lost battles of the ship. If the parameter specified is 
	 * positive it will add that many won battles and if it's negative it will add that many 
	 * lost battles.
	 * @param r number of battles to add
	 * @param f fighter destroyed in the fight that triggered the update
	 */
	public void updateResults(int r, Fighter f) {
		if(r==1) {
			this.wins++;
		}
		if(r==-1) {
			this.losses++;
		}
	}
	/**
	 * Gets the first available fighter of the fleet of the specified type
	 * @param type of the fighter
	 * @return first available fighter of the specified type or null if there is none.
	 * @throws NoFighterAvailableException if there is no fighter of the specified type available
	 */
	public Fighter getFirstAvailableFighter(String type) throws NoFighterAvailableException {
		Objects.requireNonNull(type);
		if(type.isEmpty()) {
			for(Fighter f : fleet) {
				if(!(f.isDestroyed()) && Objects.isNull(f.getPosition())) return f;
			}
		}
		else {
			for(Fighter f: fleet) {
				if(f.getType().equals(type) && !(f.isDestroyed()) && Objects.isNull(f.getPosition())) {
					return f;
				}
			}
		}
		throw new NoFighterAvailableException(type);
	}
	/**
	 * Removes destroyed fighters from the fleet.
	 */
	public void purgeFleet() {
		fleet.removeIf(Fighter::isDestroyed);
	}
	/**
	 * Returns a string in which each line shows a fighter of the fleet, and it's values as well
	 * as if it's
	 * destroyed or not. If the fighter it's destroyed it will have "(X)" string appended.
	 * If the fleet is empty returns empty string. The order is the same in which they are inserted
	 * in the fleet.
	 * @return string list of fighters in the fleet (both destroyed and not destroyed).
	 */
	public String showFleet() {
		if (this.fleet.isEmpty()) {
			return "";
			}
		else {
			StringBuilder sb = new StringBuilder();
			for(Fighter f: fleet) {
				if (!Objects.equals(f,fleet.get(0))) sb.append("\n");
				sb.append(f.toString());
				if (f.isDestroyed()) sb.append(" (X)");

			}
			return sb.toString();
		}
	}
	/**
	 * Returns a string of all the fighters of the fleet with the format used to add fighters: 
	 * "numberOfFighters1/typeOfFighter1:numberOfFighters2/typeOfFighter2". Example: "7/XWing:12/AWing:3/YWing".
	 * The order is the order in which they are inserted in the fleet but grouped by types.
	 * @return string of fighters grouped by types.
	 */
	public String myFleet() {
		if (this.fleet.isEmpty()) {
			return "";
			}
		else {
			
			StringBuilder sb = new StringBuilder();
			Map<String, Integer> fighterMap = new LinkedHashMap<>();
			for (Fighter f : this.fleet) {
				if (!f.isDestroyed()) {
					Integer n = fighterMap.get(f.getType());
					if (n==null) {
						n=0;
					}
					fighterMap.put(f.getType(), n+1);
				}	
			}
			if (fighterMap.isEmpty()) return ("");
			fighterMap.forEach((f,n) -> { 	sb.append(n);
											sb.append("/");
											sb.append(f);
											sb.append(":");
										});
			sb.deleteCharAt(sb.length() - 1);
			return sb.toString();
		}
	}
	/**
	 * Returns a string representation of the object. The string contains the name, the won and lost
	 * battles of the ship as well as the fleet.
	 */
	@Override
	public String toString() {
		return "Ship [" +
				this.name +
				" " +
				this.wins +
				"/" +
				this.losses +
				"] " +
				this.myFleet();
	}
}
