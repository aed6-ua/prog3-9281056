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
	/**
	 * 
	 * @param type
	 * @param mother
	 */
	Fighter(String type,Ship mother) {
		this.attack = 80;
		this.shield = 80;
		this.velocity = 100;
		this.type = new String(type);
		this.motherShip = new Ship(mother);
		this.position = null;
		
	}
	/**
	 * 
	 * @param f
	 */
	public Fighter(Fighter f) {
		this.attack = f.getAttack();
		this.shield = f.getShield();
		this.velocity = f.getVelocity();
		this.type = f.getType();
		this.motherShip = f.getMotherShip();
		this.position = f.getPosition();
	}
	/**
	 * 
	 */
	public static void resetNextId() {
		nextId = 1;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Fighter other = (Fighter) obj;
		if (id != other.id)
			return false;
		return true;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isDestroyed() {
		if(this.shield<=0) return true;
		else return false;
	}
	/**
	 * 
	 * @return
	 */
	public Side getSide() {
		return this.motherShip.side;
	}
	/**
	 * 
	 * @param n
	 * @param enemy
	 * @return
	 */
	public int getDamage(int n,Fighter enemy) {
		return (n*this.attack)/300;
	}
	/**
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(this.getType());
		sb.append(" ");
		sb.append(this.id);
		sb.append(" ");
		sb.append(this.getSide());
		sb.append(" ");
		sb.append(this.getPosition());
		sb.append(" {");
		sb.append(this.getVelocity());
		sb.append(",");
		sb.append(this.getAttack());
		sb.append(",");
		sb.append(this.getShield());
		sb.append("})");
		String s = sb.toString();
		return s;
	}
	
	public int fight(Fighter enemy) {
		if(this.isDestroyed() || enemy.isDestroyed()) return 0;
		else {
			do {
				int n = RandomNumber.newRandomNumber(99);
				if(((this.velocity*100)/(this.velocity+enemy.velocity))<=n) {
					enemy.shield = enemy.shield - this.getDamage(n, enemy);
				}
				else {
					this.shield = this.shield - enemy.getDamage(100-n, enemy);
				}
			}
			while(!(this.isDestroyed() || enemy.isDestroyed()));
		}
	}
}
