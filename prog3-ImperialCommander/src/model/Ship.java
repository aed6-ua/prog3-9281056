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
	private ArrayList<Fighter> fleet;
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
		this.fleet = new ArrayList<>();
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
	/**
	 * 
	 * @param r
	 */
	public void updateResults(int r) {
		if(r==1) {
			this.wins++;
		}
		if(r==-1) {
			this.losses++;
		}
	}
	/**
	 * 
	 * @param type
	 * @return
	 */
	public Fighter getFirstAvailableFighter(String type) {
		if(type.isEmpty()) {
			for(Fighter f : fleet) {
				if(!(f.isDestroyed())) return f;
			}
			return null;
		}
		else {
			for(Fighter f: fleet) {
				if(f.getType().equals(type) && !(f.isDestroyed())) {
					return f;
				}
			}
			return null;
		}
	}
	/**
	 * 
	 */
	public void purgeFleet() {
		fleet.removeIf(n -> n.isDestroyed());
	}
	/**
	 * 
	 * @return
	 */
	public String showFleet() {
		if (this.fleet.isEmpty()) {
			String r = new String("");
			return r;
			}
		else {
			StringBuilder sb = new StringBuilder();
			for(Fighter f: fleet) {
				sb.append(f.toString());
				if (f.isDestroyed()) sb.append("(X)");
				sb.append("\n");
			}
			String s = sb.toString();
			return s;
		}
	}
	/**
	 * 
	 * @return
	 */
	public String myFleet() {
		if (this.fleet.isEmpty()) {
			String r = new String("");
			return r;
			}
		else {
			StringBuilder sb = new StringBuilder();
			Map<String, Integer> fighterMap = new HashMap<>();
			for (Fighter f : this.fleet) {
				Integer n = fighterMap.get(f.getType());
				if (n==null) {
					n=0;
				}
				
				fighterMap.put(f.getType(), n+1);
			}
			fighterMap.forEach((f,n) -> { 	sb.append(n);
											sb.append("/");
											sb.append(f);
											sb.append(":");
										});
			sb.deleteCharAt(sb.length() - 1);
			String s = sb.toString();
			return s;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Ship [");
		sb.append(this.name);
		sb.append(" ");
		sb.append(this.wins);
		sb.append("/");
		sb.append(this.losses);
		sb.append("] ");
		sb.append(this.myFleet());
		String s = sb.toString();
		return s;
	}
}
