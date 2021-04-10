/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motionelectricfieldsimulation;

import motionview.SimWindow;

/**
 *
 * @author ismai
 */
public class MotionController {
    private SimWindow simPane;

    public MotionController() {
        simPane = new SimWindow();
        InputHandler inputHandler = new InputHandler(simPane);
        OutputController outputController = new OutputController(simPane, inputHandler.getValueMap());
        GraphController graphController = new GraphController(simPane, inputHandler.getValueMap());
        
        simPane.input.startButton.setOnAction(((event) -> {
            inputHandler.onAction();
            outputController.setLabelValues();
            graphController.plotAll();
        }));
    }

    public SimWindow getSimPane() {
        return simPane;
    }
    
    
    
}
