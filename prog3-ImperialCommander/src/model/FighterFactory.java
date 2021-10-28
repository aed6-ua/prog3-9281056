/**
 * 
 */
package model;

/**
 * @author aed6
 *
 */
public class FighterFactory {
	
	public static Fighter createFighter(String type, Ship mother) {
		Fighter f = null;
		switch(type) {
			case "AWing": //f = new Fighter(type, mother);
							break;
			case "XWing": //f = new Fighter(type, mother);
							break;
			case "YWing": //f = new Fighter(type, mother);
							break;
			case "TIEInterceptor": //f = new Fighter(type, mother);
							break;
			case "TIEFighter": //f = new Fighter(type, mother);
							break;
			case "TIEBomber": //f = new Fighter(type, mother);
							break;
			default: return null;
		}
		return f;
	}
}
