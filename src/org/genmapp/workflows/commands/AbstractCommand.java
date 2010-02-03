package org.genmapp.workflows.commands;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import cytoscape.Cytoscape;
import cytoscape.util.CytoscapeAction;

public class AbstractCommand extends CytoscapeAction {
	private String name;
	
	public AbstractCommand(String n)
	{
		super();
		name = n;
	}
	
	public String toString()
	{
		return name;
	}
		
	/**
	 * ActionPerformed should be overridden by all extending classes
	 * @param e
	 */
	public void actionPerformed (ActionEvent e)
	{
		JOptionPane.showMessageDialog(Cytoscape.getDesktop(), 
				new String("Sorry, " + name + " is not yet implemented."));

	}

}
