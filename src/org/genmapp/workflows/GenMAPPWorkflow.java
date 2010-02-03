package org.genmapp.workflows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import cytoscape.Cytoscape;
import cytoscape.plugin.CytoscapePlugin;

public class GenMAPPWorkflow extends CytoscapePlugin {
	public GenMAPPWorkflow() {
		JMenuItem item = new JMenuItem("GenMAPP Workflow");
		JMenu pluginMenu = Cytoscape.getDesktop().getCyMenus().getMenuBar()
				.getMenu("Plugins");
		item.addActionListener(new WorkflowsCommandListener(this));
		pluginMenu.add(item);
	}

	// Handles the top-level menu selection event from Cytoscape
	class WorkflowsCommandListener implements ActionListener {
		GenMAPPWorkflow plugin = null;

		public WorkflowsCommandListener(GenMAPPWorkflow p) {
			plugin = p;
		}

		public void actionPerformed(ActionEvent evt_) {
			// pop up dialog
			JDialog d = new JDialog();
			d.add(new FlowTree());
			d.pack();
			d.setVisible(true);
		}
	}
}