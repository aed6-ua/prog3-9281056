/**
 * 
 */
package model;

/**
 * @author edu
 *
 */
public class Fighter {
	/**
	 * 
	 */
	private String type;
	/**
	 * 
	 */
	private int velocity;
	/**
	 * 
	 */
	private int attack;
	/**
	 * 
	 */
	private int shield;
	/**
	 * 
	 */
	private int id;
	/**
	 * 
	 */
	private static int nextId=1;
	/**
	 * 
	 */
	private Coordinate position;
	/**
	 * 
	 */
	private Ship motherShip;
	
	Fighter(String type,Ship mother) {
		this.attack = 80;
		this.shield = 80;
		this.velocity = 100;
		String typec = new String(type);
		this.type = typec;
		Ship motherc = new Ship(mother);
		this.motherShip = motherc;
		this.position = null;
		
	}
	
	
	/**
	 * @return the type
	 */
	public String getType() {
		String type = new String(this.type);
		return type;
	}
	/**
	 * @return the velocity
	 */
	public int getVelocity() {
		return velocity;
	}
	/**
	 * @return the attack
	 */
	public int getAttack() {
		return attack;
	}
	/**
	 * @return the shield
	 */
	public int getShield() {
		return shield;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the position
	 */
	public Coordinate getPosition() {
		Coordinate position = new Coordinate(this.position);
		return position;
	}
	/**
	 * @return the motherShip
	 */
	public Ship getMotherShip() {
		Ship motherShip = new Ship(this.motherShip);
		return motherShip;
	}
	/**
	 * @param velocity the velocity to set
	 */
	public void addVelocity(int velocity) {
		this.velocity += velocity;
	}
	/**
	 * @param attack the attack to set
	 */
	public void addAttack(int attack) {
		this.attack += attack;
	}
	/**
	 * @param shield the shield to set
	 */
	public void addShield(int shield) {
		this.shield += shield;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(Coordinate position) {
		Coordinate copy = new Coordinate(position);
		this.position = copy;
	}
	
}
