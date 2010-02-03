package org.genmapp.workflows.commands;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import cytoscape.command.CyCommandException;
import cytoscape.command.CyCommandManager;
import cytoscape.command.CyCommandResult;

public class ImportDataCommand extends AbstractCommand {

	public ImportDataCommand(String n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	public void actionPerformed(ActionEvent e) {

		Map<String, Object> args = new HashMap();

		try {
			CyCommandResult result = CyCommandManager.execute("genmapp import",
					"open", args);
			// Visualize data from result
		} catch (CyCommandException ex) {
			// Handle exception
		}

	}

}
