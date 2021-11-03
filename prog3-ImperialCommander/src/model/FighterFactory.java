/**
 * 
 */
package model;

import model.fighters.*;

/**
 * @author aed6
 *
 */
public class FighterFactory {
	
	public static Fighter createFighter(String type, Ship mother) {
		switch(type) {
			case "AWing": return new AWing(mother);
			case "XWing": return new XWing(mother);
			case "YWing": return new YWing(mother);
			case "TIEInterceptor": return new TIEInterceptor(mother);
			case "TIEFighter": return new TIEFighter(mother);
			case "TIEBomber": return new TIEBomber(mother);
			default: return null;
		}
	}
}
