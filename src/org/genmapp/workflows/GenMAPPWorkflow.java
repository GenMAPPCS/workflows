
package org.genmapp.workflows;

import cytoscape.plugin.CytoscapePlugin;

public class GenMAPPWorkflow extends CytoscapePlugin {
    public GenMAPPWorkflow {
        // Plugin initialization.  Note: we don't want to look for MyPlugin yet.  That should
        // wait until we actually want to use it.  This avoids errors that result from the
        // arbitrary loading order of plugins.
    }

    public void doWork() {
        Map<String, Object> args = new HashMap();
        args.put("iterations", new Integer(10));
        try {
            CyCommandResult result = CyCommandManager.execute("my algorithm", "analyze", args);
            // Visualize data from result
        } catch (CyCommandException e) {
            // Handle exception
        }
    }
}