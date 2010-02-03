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

import org.genmapp.workflows.commands.ImportDataCommand;

public class FlowTree extends JPanel
implements TreeSelectionListener {
	
    private JTree tree;
    private static String lineStyle = "Horizontal";

    private final static HashMap<Integer, String> FLOWTREE = new HashMap<Integer, String>();
    	static {
    		FLOWTREE.put(0,"GenMAPP-CS");
    		FLOWTREE.put(1, "Load Data");
    	}
    	


	public FlowTree () {

		// build the tree
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(FLOWTREE.get(0));
		
		DefaultMutableTreeNode one = new DefaultMutableTreeNode(new ImportDataCommand(FLOWTREE.get(1)));
		root.add(one);
		
		tree = new JTree(root);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(this);
		tree.putClientProperty("JTree.lineStyle", lineStyle);
		
		JScrollPane treeView = new JScrollPane(tree);
        treeView.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        treeView.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        treeView.setBorder
        (new javax.swing.border.MatteBorder(15,15,15,15, java.awt.Color.white));

        
		// set scrollpane size:
		// width - a little bigger than longest string in tree
		// height - we are really only concerned with width, set height to anything
		// (note: longest string should be found programatically)
        Collection<String> treeItems = FLOWTREE.values();
        int maxWidth = 0;
        for (String i : treeItems){
        	if (i.length() > maxWidth){
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
		
	}

}
