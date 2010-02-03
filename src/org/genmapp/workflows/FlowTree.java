package org.genmapp.workflows;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import org.genmapp.workflows.commands.ImportDataCommand;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;

public class FlowTree extends JPanel implements TreeSelectionListener {

	private JTree tree;
	private static String lineStyle = "Horizontal";

	private final static HashMap<Integer, String> FLOWTREE = new HashMap<Integer, String>();
	static {
		FLOWTREE.put(0, "GenMAPP-CS");
		FLOWTREE.put(1, "Load Data");
	}

	public FlowTree() {

		// build the tree
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(FLOWTREE
				.get(0));

		DefaultMutableTreeNode one = new DefaultMutableTreeNode(
				new ImportDataCommand(FLOWTREE.get(1)));
		root.add(one);

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
		Collection<String> treeItems = FLOWTREE.values();
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
			} else {
				// user may have selected an internal (Non-leaf) node. Do
				// nothing
			}
		}

	}

}
