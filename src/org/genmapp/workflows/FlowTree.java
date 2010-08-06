/*
 Copyright 2010 Alexander Pico
 Licensed under the Apache License, Version 2.0 (the "License"); 
 you may not use this file except in compliance with the License. 
 You may obtain a copy of the License at 
 	
 	http://www.apache.org/licenses/LICENSE-2.0 
 	
 Unless required by applicable law or agreed to in writing, software 
 distributed under the License is distributed on an "AS IS" BASIS, 
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 See the License for the specific language governing permissions and 
 limitations under the License. 
 */

package org.genmapp.workflows;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.genmapp.workflows.FlowTreeNode.NodeType;

public class FlowTree extends JPanel implements TreeSelectionListener,
		ActionListener, MouseListener {

	public JTree tree;
	private static String lineStyle = "Horizontal";

	public FlowTree() {

		FlowTreeNode root = new FlowTreeNode(null, "GenMAPP-CS",
				NodeType.ALWAYS);
		FlowTreeNode step1 = new FlowTreeNode(root, "Load Data",
				NodeType.ALWAYS, "genmapp import", "open");
		new FlowTreeNode(step1, "Select file", NodeType.AFTER_PREV);
		new FlowTreeNode(step1, "Visualize", NodeType.AFTER_PREV);
		FlowTreeNode step2 = new FlowTreeNode(root, "Build Criteria", NodeType.ALWAYS);
		new FlowTreeNode(step2, "Create default criteria", NodeType.AFTER_PREV, "criteria mapper", "build default");
		new FlowTreeNode(step2, "Open existing criteria", NodeType.WITH_PREV, "criteria mapper", "open");
		FlowTreeNode step3 = new FlowTreeNode(root, "Cluster", NodeType.ALWAYS);
		new FlowTreeNode(step3, "Select cluster parameters", NodeType.AFTER_PREV, "cluster maker", "open");
		new FlowTreeNode(step3, "Visualize", NodeType.WITH_PREV, "cluster maker", "visualize");
		FlowTreeNode step4 = new FlowTreeNode(root, "Pathway Analysis", NodeType.ALWAYS);
		new FlowTreeNode(step4, "Load results", NodeType.AFTER_PREV, "gpml", "open");
		FlowTreeNode step5 = new FlowTreeNode(root, "Load Additional Pathways", NodeType.ALWAYS);
		FlowTreeNode step6 = new FlowTreeNode(root, "Summary Report", NodeType.ALWAYS);
		FlowTreeNode step7 = new FlowTreeNode(root, "Export Options", NodeType.ALWAYS);

		// build the tree

		buildTreeView(root);

		MouseListener ml = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int selRow = tree.getRowForLocation(e.getX(), e.getY());
				TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
				if (selRow != -1) {
					if (e.getClickCount() == 1) {
						tree.setSelectionRow(selRow);
						tree.setSelectionPath(selPath);
						treeSelection();
					} else if (e.getClickCount() == 2) {
						// myDoubleClick(selRow, selPath);
					}
				}
			}
		};
		tree.addMouseListener(ml);

	}

	public void buildTreeView(DefaultMutableTreeNode root) {
		tree = new JTree(root);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(this);
		tree.putClientProperty("JTree.lineStyle", lineStyle);

		JScrollPane treeView = new JScrollPane(tree);
		treeView
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		treeView
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		treeView.setBorder(new javax.swing.border.MatteBorder(15, 15, 15, 15,
				java.awt.Color.white));

		// set scrollpane size:
		// Collection<String> treeItems = flowTree.values();
		// int maxWidth = 0;
		// for (String i : treeItems) {
		// if (i.length() > maxWidth) {
		// maxWidth = i.length();
		// }
		// }
		// Dimension size = new Dimension(maxWidth + maxWidth / 2, 1);
		// treeView.setMinimumSize(size);

		// add tree to this pane
		setLayout(new BorderLayout());
		add(treeView, BorderLayout.CENTER);

	}

	public void treeSelection() {
		// TODO Auto-generated method stub
		FlowTreeNode ftn = (FlowTreeNode) tree.getLastSelectedPathComponent();

		if (ftn == null)
			return;
		else

		{

			ftn.execute();
			this.revalidate();
			// buildTreeView();

		}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Clicked!!!");
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Pressed!!!");

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		// handled by mouselistener
	}

}
