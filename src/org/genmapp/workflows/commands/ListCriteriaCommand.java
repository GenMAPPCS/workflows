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
