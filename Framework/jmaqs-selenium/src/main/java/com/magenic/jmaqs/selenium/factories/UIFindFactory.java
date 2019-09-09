package com.magenic.jmaqs.selenium.factories;

import org.openqa.selenium.SearchContext;

/**
 * Factory class for creating UIFind objects
 * for the test.
 */
public class UIFindFactory {
	
	// private constructor so class can't be instantiated
	private UIFindFactory() { }
	
	/**
	 * Initializes a new instance of {@link UIFind}
	 * @param searchItem the search item that is used for
	 *        finding elements
	 * @return The UIFind instance
	 */
	public UIFind getFind(SearchContext searchItem) {
		return new UIFind(searchItem);
	}
}
