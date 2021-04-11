package chargemodel;

import chargeview.ChargeGraph;
import javafx.scene.layout.Pane;

/**
 *
 * @author Chris
 */
public class Model {

    public ChargeGraph graph = new ChargeGraph();

    public Pane getGraph() {
        return graph;
    }
    
}
