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
		FlowTreeNode step2 =  new FlowTreeNode(root, "Build Criteria", NodeType.ALWAYS);
		new FlowTreeNode(step2, "Create default criteria", NodeType.AFTER_PREV, "criteria mapper", "build default");
		new FlowTreeNode(step2, "Open existing criteria", NodeType.WITH_PREV, "criteria mapper", "open");
		// flowTree.put(3.0, new FlowTreeNode("Cluster", NodeType.NONE));
		// flowTree.put(3.1, new FlowTreeNode("Select cluster parameters",
		// NodeType.NONE));
		// flowTree.put(3.2, new FlowTreeNode("Visualize", NodeType.NONE));
		// flowTree.put(4.0, new FlowTreeNode("Pathway Analysis",
		// NodeType.NONE));
		// flowTree.put(4.1, new FlowTreeNode("Load results", NodeType.NONE));
		// flowTree.put(5.0, new FlowTreeNode("Load Additional Pathways",
		// NodeType.NONE));
		// flowTree.put(6.0, new FlowTreeNode("Summary Report", NodeType.NONE));
		// flowTree.put(7.0, new FlowTreeNode("Export Options", NodeType.NONE));

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
