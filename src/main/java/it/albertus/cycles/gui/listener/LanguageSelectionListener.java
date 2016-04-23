package it.albertus.cycles.gui.listener;

import it.albertus.cycles.gui.CyclesModGui;
import it.albertus.cycles.resources.Resources;
import it.albertus.cycles.resources.Resources.Language;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MenuItem;

public class LanguageSelectionListener extends SelectionAdapter {

	private final CyclesModGui gui;

	public LanguageSelectionListener(final CyclesModGui gui) {
		this.gui = gui;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		final MenuItem languageMenuItem = (MenuItem) e.widget;
		if (languageMenuItem.getSelection() && !Resources.getLanguage().equals(languageMenuItem.getData())) {
			gui.setLanguage((Language) languageMenuItem.getData());
		}
	}

}