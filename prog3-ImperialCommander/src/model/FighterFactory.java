/**
 * 
 */
package model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Class used to create fighters for Imperial Commander game.
 * @author Eduard Andrei Duta | NIE: X9281056G
 *
 */
public class FighterFactory {
	/**
	 * Method that calls specific constructors depending on the type of fighter specified.
	 * @param type of fighter to create.
	 * @param mother mothership of the fighter to create.
	 * @return fighter created.
	 */

	public static Object createFighter(String type, Ship mother) {
		try {
			String s = "model.fighters.";
			s += type;
			Class f = Class.forName(s);
			Method m[] = f.getDeclaredMethods();
			return f.getDeclaredConstructor().newInstance(mother);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {}
		return null;
	}
}

/*
	public static Fighter createFighter(String type, Ship mother) {
		Objects.requireNonNull(mother);
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
*/