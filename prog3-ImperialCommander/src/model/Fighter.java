/**
 * 
 */
package model;

/**
 * Fighter used in the game ImperialCommander.
 * @author Eduard Andrei Duta | NIE: X9281056G
 *
 */
public abstract class Fighter {
	
	/**
	 * Speed of the fighter.
	 */
	private int velocity;
	/**
	 * Attack of the fighter.
	 */
	private int attack;
	/**
	 * Shield of the fighter.
	 */
	private int shield;
	/**
	 * id number of the fighter (unique).
	 */
	private int id;
	/**
	 * Static id used to give the fighters unique id's.
	 */
	private static int nextId=1;
	/**
	 * Position occupied by the fighter. Can be null if it's not launched.
	 */
	private Coordinate position;
	/**
	 * Ship that this fighter is assigned to.
	 */
	private Ship motherShip;
	/**
	 * Constructor of Fighter. Creates a Fighter with the specified type and assigns it to the
	 * specified mothership. The default values for attack and shield are 80 and 100 for velocity.
	 * The fighter starts with null position until is launched to the board.
	 * @param type type of fighter
	 * @param mother mothership of the fighter
	 */
	protected Fighter(String type,Ship mother) {
		this.attack = 80;
		this.shield = 80;
		this.velocity = 100;
		this.motherShip = mother;
		this.position = null;
		this.id = nextId;
		nextId++;
	}
	/**
	 * Copy constructor of Fighter. Creates a Fighter with the same values for attributes as the
	 * specified Fighter.
	 * @param f fighter to copy
	 */
	protected Fighter(Fighter f) {
		this.attack = f.getAttack();
		this.shield = f.getShield();
		this.velocity = f.getVelocity();
		this.motherShip = f.getMotherShip();
		this.position = f.getPosition();
		this.id = f.getId();
	}
	
	public abstract Fighter copy();
	
	public abstract char getSymbol();
	/**
	 * Resets the static nextId number used for generating the id's of the Fighters. For test
	 * purposes only.
	 */
	public static void resetNextId() {
		nextId = 1;
	}
	/**
	 * Gets the type of the Fighter.
	 * @return the type
	 */
	public String getType() {
		return getClass().getSimpleName();
	}
	/**
	 * Gets the speed of the Fighter.
	 * @return the velocity
	 */
	public int getVelocity() {
		return velocity;
	}
	/**
	 * Gets the attack of the Fighter.
	 * @return the attack
	 */
	public int getAttack() {
		return attack;
	}
	/**
	 * Gets the shield of the Fighter.
	 * @return the shield
	 */
	public int getShield() {
		return shield;
	}
	/**
	 * Gets the id of the Fighter.
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * Gets the position of the Fighter. If the Fighter is not launched returns null.
	 * @return the position
	 */
	public Coordinate getPosition() {
		//Coordinate position = new Coordinate(this.position);
		return this.position;
	}
	/**
	 * Gets the Ship that this Fighter is assigned to.
	 * @return the motherShip
	 */
	public Ship getMotherShip() {
		//Ship motherShip = new Ship(this.motherShip.getName(), this.motherShip.getSide());
		return this.motherShip;
	}
	/**
	 * Increases the speed by specified value.
	 * @param velocity the velocity to set
	 */
	public void addVelocity(int velocity) {
		this.velocity += velocity;
		if (this.velocity < 0) this.velocity = 0;
	}
	/**
	 * Increases the attack by specified value.
	 * @param attack the attack to set
	 */
	public void addAttack(int attack) {
		this.attack += attack;
		if (this.attack < 0) this.attack = 0;
	}
	/**
	 * Increases the shield by specified value.
	 * @param shield the shield to set
	 */
	public void addShield(int shield) {
		this.shield += shield;
	}
	/**
	 * Sets the position to the specified Coordinate.
	 * @param position the position to set
	 */
	public void setPosition(Coordinate position) {
		//Coordinate copy = new Coordinate(position);
		this.position = position;
	}
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	/**
	 * 
	 */
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
	 * Returns true or false if the Fighter is destroyed (it's shield is 0 or below) or not.
	 * @return true or false.
	 */
	public boolean isDestroyed() {
		/*
		if(this.shield<=0) return true;
		else return false;
		 */
		return this.shield <= 0;
	}
	/**
	 * Gets the Side of the Fighter (determined by the Side of the mothership).
	 * @return Side of the fighter.
	 */
	public Side getSide() {
		return this.motherShip.getSide();
	}
	/**
	 * Gets the damage that the Fighter does, based on it's attack and a random number.
	 * @param n random number
	 * @param enemy to fight
	 * @return damage value (int).
	 */
	public int getDamage(int n,Fighter enemy) {
		return (n*this.attack)/300;
	}
	/**
	 * Returns a string representation of the object. Shows type, id, side, position, speed,
	 * attack and shield of the Fighter.
	 */
	@Override
	public String toString() {
		return "(" +
				this.getType() +
				" " +
				this.id +
				" " +
				this.getSide() +
				" " +
				this.getPosition() +
				" {" +
				this.getVelocity() +
				"," +
				this.getAttack() +
				"," +
				this.getShield() +
				"})";
	}
	/**
	 * The Fighter fights an enemy Fighter until one or the other is destroyed.
	 * @param enemy Fighter
	 * @return 0, -1 or 1 if one fighter starts the fight destroyed, the Fighter losses or wins the fight.
	 */
	public int fight(Fighter enemy) {
		if(this.isDestroyed() || enemy.isDestroyed()) return 0;
		else {
			do {
				int n = RandomNumber.newRandomNumber(100);
				if(((this.velocity*100)/(this.velocity+enemy.getVelocity()))<=n) {
					enemy.addShield(-this.getDamage(n, enemy));
				}
				else {
					this.shield = this.shield - enemy.getDamage(100-n, this);
				}
			}
			while(!(this.isDestroyed() || enemy.isDestroyed()));
		}
		if (this.isDestroyed()) return -1;
		else return 1;
	}
	
	
}
