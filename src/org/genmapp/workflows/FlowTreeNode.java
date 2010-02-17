package org.genmapp.workflows;

import java.util.HashMap;

import javax.swing.tree.DefaultMutableTreeNode;

import cytoscape.command.CyCommandException;
import cytoscape.command.CyCommandManager;

public class FlowTreeNode extends DefaultMutableTreeNode {

	public enum NodeType {
		AFTER_PREV, WITH_PREV, ALWAYS
	};

	String label;
	NodeType nt;
	FlowTreeNode parent;
	String ns;
	String com;

	public FlowTreeNode(FlowTreeNode ftn, String item, NodeType nt) {
		this(ftn, item, nt, null, null);

	}

	public FlowTreeNode(FlowTreeNode ftn, String item, NodeType nt,
			String ns, String com) {
		super(item);
		this.label = item;
		this.nt = nt;
		this.parent = ftn;
		this.ns = ns;
		this.com = com;
		if (parent != null) {
			parent.add(this);
		}

	}

	public String toString() {
		return label;
	}
	
	public void execute(){
		if (ns != null){
			try {
				CyCommandManager.execute(ns, com, new HashMap());
			} catch (CyCommandException e) {
				//logger!
			} catch (RuntimeException e) {

			}			
			
		}
	}
}