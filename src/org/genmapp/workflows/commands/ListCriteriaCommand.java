package org.genmapp.workflows.commands;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;

import org.genmapp.workflows.FlowTree;

import cytoscape.command.CyCommandException;
import cytoscape.command.CyCommandManager;
import cytoscape.command.CyCommandResult;

public class ListCriteriaCommand extends AbstractCommand {

	public ListCriteriaCommand(String n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	public void actionPerformed(ActionEvent e) {

		Map<String, Object> args = new HashMap();

		try {
			CyCommandResult result = CyCommandManager.execute("criteria mapper",
					"list sets", args);
			// Visualize data from result
			FlowTree.flowTree.put(3.1, "setname_1");
			
			DefaultMutableTreeNode step3_1 = new DefaultMutableTreeNode(
					new BuildCriteriaCommand(FlowTree.flowTree.get(3.1)));
			
			FlowTree.root.add(step3_1);
			

			
		} catch (CyCommandException ex) {
			// Handle exception
		}

	}

}
