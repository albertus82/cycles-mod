package it.albertus.cycles.gui.torquegraph.dialog.listener;

import org.eclipse.nebula.visualization.xygraph.figures.IXYGraph;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import it.albertus.cycles.resources.Messages;
import it.albertus.jface.SwtUtils;

public class SaveSnapshotListener implements KeyListener, SelectionListener {

	private final Shell shell;
	private final IXYGraph xyGraph;

	public SaveSnapshotListener(final Shell shell, final IXYGraph xyGraph) {
		this.shell = shell;
		this.xyGraph = xyGraph;
	}

	@Override
	public void keyPressed(final KeyEvent e) {
		if (SWT.MOD1 == e.stateMask && SwtUtils.KEY_SAVE == e.keyCode) {
			execute();
		}
	}

	@Override
	public void widgetSelected(final SelectionEvent e) {
		execute();
	}

	private void execute() {
		final ImageLoader loader = new ImageLoader();
		loader.data = new ImageData[] { xyGraph.getImage().getImageData() };
		final FileDialog dialog = new FileDialog(shell, SWT.SAVE);
		dialog.setFilterNames(new String[] { "Portable Network Graphics (*.png)", Messages.get("lbl.graph.save.allFiles", "(*.*)") });
		dialog.setFilterExtensions(new String[] { "*.PNG;*.png", "*.*" }); // Windows
		final String path = dialog.open();
		if ((path != null) && !path.isEmpty()) {
			loader.save(path, SWT.IMAGE_PNG);
		}
	}

	@Override
	public void keyReleased(final KeyEvent e) {/* Ignore */}

	@Override
	public void widgetDefaultSelected(final SelectionEvent e) {/* Ignore */}

}