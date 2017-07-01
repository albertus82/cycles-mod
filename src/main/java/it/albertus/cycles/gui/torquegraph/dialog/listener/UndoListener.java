package it.albertus.cycles.gui.torquegraph.dialog.listener;

import org.eclipse.nebula.visualization.internal.xygraph.undo.OperationsManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import it.albertus.jface.SwtUtils;

public class UndoListener implements KeyListener, SelectionListener {

	private final OperationsManager manager;

	public UndoListener(final OperationsManager manager) {
		this.manager = manager;
	}

	@Override
	public void keyPressed(final KeyEvent e) {
		if (SWT.MOD1 == e.stateMask && SwtUtils.KEY_UNDO == e.keyCode) {
			execute();
		}
	}

	@Override
	public void keyReleased(final KeyEvent e) {/* Ignore */}

	@Override
	public void widgetSelected(final SelectionEvent e) {
		execute();
	}

	@Override
	public void widgetDefaultSelected(final SelectionEvent e) {/* Ignore */}

	private void execute() {
		manager.undo();
	}

}
