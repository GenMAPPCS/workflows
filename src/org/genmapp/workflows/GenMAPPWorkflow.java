/*******************************************************************************
 * Copyright 2010 Alexander Pico
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
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
		JDialog d = new JDialog();
		d.add(new FlowTree());
		d.pack();
		d.setVisible(true);
		d.setAlwaysOnTop(true);
//		
//		JMenuItem item = new JMenuItem("GenMAPP Workflow");
//		JMenu pluginMenu = Cytoscape.getDesktop().getCyMenus().getMenuBar()
//				.getMenu("Plugins");
//		item.addActionListener(new WorkflowsCommandListener(this));
//		pluginMenu.add(item);
	}

	// Handles the top-level menu selection event from Cytoscape
//	class WorkflowsCommandListener implements ActionListener {
//		GenMAPPWorkflow plugin = null;
//
//		public WorkflowsCommandListener(GenMAPPWorkflow p) {
//			plugin = p;
//		}
//
//		public void actionPerformed(ActionEvent evt_) {
//			// pop up dialog
//			JDialog d = new JDialog();
//			d.add(new FlowTree());
//			d.pack();
//			d.setVisible(true);
//		}
//	}
}
