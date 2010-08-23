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
