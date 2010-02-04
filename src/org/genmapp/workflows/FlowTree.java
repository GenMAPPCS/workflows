package org.genmapp.workflows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import org.genmapp.workflows.commands.AbstractCommand;
import org.genmapp.workflows.commands.BuildCriteriaCommand;
import org.genmapp.workflows.commands.ImportDataCommand;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;

public class FlowTree extends JPanel implements TreeSelectionListener, ActionListener {

	public JTree tree;
	private static String lineStyle = "Horizontal";

	public static HashMap<Double, String> flowTree = new HashMap<Double, String>();
	static {
		flowTree.put(0.0, "GenMAPP-CS");
		flowTree.put(1.0, "Load Data");
		flowTree.put(2.0, "Build Criteria");
		flowTree.put(3.0, "Open Criteria");
	}
	

	// make root available to command actions
	public static DefaultMutableTreeNode root = new DefaultMutableTreeNode(flowTree
			.get(0.0));
	

	public FlowTree() {

		// build the tree
		DefaultMutableTreeNode step1 = new DefaultMutableTreeNode(
				new ImportDataCommand(flowTree.get(1.0)));
		DefaultMutableTreeNode step2 = new DefaultMutableTreeNode(
				new BuildCriteriaCommand(flowTree.get(2.0)));
		DefaultMutableTreeNode step3 = new DefaultMutableTreeNode(
				new BuildCriteriaCommand(flowTree.get(3.0)));

		root.add(step1);
		root.add(step2);
		root.add(step3);

		buildTreeView();
	}
	
	public void buildTreeView() {
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
		Collection<String> treeItems = flowTree.values();
		int maxWidth = 0;
		for (String i : treeItems) {
			if (i.length() > maxWidth) {
				maxWidth = i.length();
			}
		}
		Dimension size = new Dimension(maxWidth + maxWidth / 2, 1);
		treeView.setMinimumSize(size);

		// add tree to this pane
		setLayout(new BorderLayout());
		add(treeView, BorderLayout.CENTER);

	}

	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
				.getLastSelectedPathComponent();

		CyNetwork currentNetwork = Cytoscape.getCurrentNetwork();

		if (node == null)
			return;
		else

		{
			Object userObject = node.getUserObject();
			if (userObject == null)
				return;

			if (userObject instanceof AbstractCommand) {
				((AbstractCommand) userObject).actionPerformed(null);
				this.revalidate();
				//buildTreeView();
			} else {
				// user may have selected an internal (Non-leaf) node. Do
				// nothing
			}
		}

	}


	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
