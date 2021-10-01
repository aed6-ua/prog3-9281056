/**
 * 
 */
package model;

import java.util.*;
/**
 * @author edu
 *
 */
public class Ship {
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private Side side;
	/**
	 * 
	 */
	private int wins;
	/**
	 * 
	 */
	private int losses;
	/**
	 * 
	 */
	private List<Fighter> fleet;
	/**
	 * 
	 * @param name
	 * @param side
	 */
	public Ship(String name,Side side) {
		this.name = new String(name);
		this.side = side;
		this.wins = 0;
		this.losses = 0;
		this.fleet = null;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the side
	 */
	public Side getSide() {
		return side;
	}
	/**
	 * @return the wins
	 */
	public int getWins() {
		return wins;
	}
	/**
	 * @return the losses
	 */
	public int getLosses() {
		return losses;
	}
	/**
	 * 
	 * @return
	 */
	public List<Fighter> getFleetTest() {
		return fleet;
	}
	/**
	 * 
	 * @param fd
	 */
	public void addFighters(String fd) {
		String fighters[] = fd.split(":");
		for(String s : fighters) {
			String type[] = s.split("\\/");
			int quant = Integer.parseInt(type[0]);
			for(int i=0; i<quant; i++) {
				this.fleet.add(new Fighter(type[1],this));
			}
		}
	}
}
