package it.albertus.cycles.gui;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SaveSelectionListener extends SelectionAdapter {

	private final CyclesModGui gui;

	public SaveSelectionListener(final CyclesModGui gui) {
		this.gui = gui;
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		gui.save(true);
	}

}
