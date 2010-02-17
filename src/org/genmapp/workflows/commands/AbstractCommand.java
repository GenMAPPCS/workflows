package org.genmapp.workflows.commands;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import cytoscape.Cytoscape;
import cytoscape.util.CytoscapeAction;

public class AbstractCommand  {
	private String name;
	
	public AbstractCommand(String n)
	{
		super();
		name = n;
	}
	
		
	
	/**
	 * ActionPerformed should be overridden by all extending classes
	 * @param e
	 */
	public void execute(String com)
	{
		JOptionPane.showMessageDialog(Cytoscape.getDesktop(), 
				new String("Sorry, " + com + " is not yet implemented."));

	}

}
