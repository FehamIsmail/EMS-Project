/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motionview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import properties.DecimalTextFormatter;

/**
 *
 * @author Jeffrey Gan
 */
public class InputPane extends VBox{
    //Control Elements
    public TextField mass = new TextField();
    public TextField posX = new TextField();
    public TextField posY = new TextField();
    public TextField velMag = new TextField();
    public TextField velDir = new TextField();
    public TextField charge = new TextField();
    public TextField fieldMag = new TextField();
    public Button isVfield = new Button("Vertical Field");
    public Button isVbound = new Button("x =");
    public TextField stop = new TextField();
    public Label exMessage = new Label("");
    public Button startButton = new Button("Calculate");
    
    //Input Pane Elements
    private Label paneTitle = new Label("Input");
    private final GridPane chargePane = new GridPane();
    private final GridPane fieldPane = new GridPane();
    private final GridPane stopPane = new GridPane();
    
    
    InputPane(){
        //Style of the InputPane
        super(30);
        this.setPrefWidth(390.0);
        this.setMaxWidth(390.0);
        this.setAlignment(Pos.TOP_CENTER);
        this.getStyleClass().add("motion-vbox");
        this.setPadding(new Insets(20));
        
        double width = 350.0;
        
        //Set text formatters for the TextFields
        mass.setTextFormatter(new DecimalTextFormatter(0, 3, false, 4));
        posX.setTextFormatter(new DecimalTextFormatter(0, 3, true, 3));
        posY.setTextFormatter(new DecimalTextFormatter(0, 3, true, 3));
        velMag.setTextFormatter(new DecimalTextFormatter(0, 3, true, 9));
        velDir.setTextFormatter(new DecimalTextFormatter(0, 3, false, 3));
        charge.setTextFormatter(new DecimalTextFormatter(0, 3, true, 6));
        fieldMag.setTextFormatter(new DecimalTextFormatter(0, 3, true, 6));
        stop.setTextFormatter(new DecimalTextFormatter(0, 3, true, 6));
        
        //Style of the GridPane columns
        ColumnConstraints headerColumn = new ColumnConstraints();
        headerColumn.setPercentWidth(24.0);
        ColumnConstraints magnitudeColumn = new ColumnConstraints();
        magnitudeColumn.setMinWidth(100);
        ColumnConstraints magnitudeValue = new ColumnConstraints();
        magnitudeValue.setMinWidth(124);
        ColumnConstraints otherColumns = new ColumnConstraints();
        otherColumns.setPercentWidth((100.0-24.0)/2.0);
        
        paneTitle.setStyle("-fx-font-size: 30; -fx-font-weight: bold; fx-font-family: Segoe UI;");
        paneTitle.setUnderline(true);
        
        chargePane.setVgap(3);
        chargePane.setMinWidth(width);chargePane.setMaxWidth(width);
        chargePane.getColumnConstraints().addAll(headerColumn, otherColumns, otherColumns);
        Label chargeLabel = new Label("Charge Properties");
        chargeLabel.setStyle("-fx-font-size: 16; fx-font-family: Segoe UI;");
        chargeLabel.setUnderline(true);
        chargePane.add(chargeLabel, 0, 0, 3, 1);
        chargePane.add(new Label("Mass (kg): "), 0, 1, 1, 1);
        chargePane.add(mass, 1, 1, 2, 1);
        chargePane.addRow(2, new Label("Position (m): "), posX, posY);
        chargePane.addRow(3, new Label("Velocity (m/s): "), velMag, velDir);
        chargePane.addRow(4, new Label("Charge (C): "));
        chargePane.add(charge, 1, 4, 2, 1);
        
        fieldPane.setVgap(3);
        fieldPane.setMinWidth(width);fieldPane.setMaxWidth(width);
        fieldPane.getColumnConstraints().addAll(magnitudeColumn, magnitudeValue, magnitudeValue);
        Label fieldLabel = new Label("Field properties");
        fieldLabel.setStyle("-fx-font-size: 16; fx-font-family: Segoe UI;");
        fieldLabel.setUnderline(true);
        fieldPane.add(fieldLabel, 0, 0, 3, 1);
        fieldPane.add(new Label("Magnitude (N/C): "), 0, 1, 1, 1);
        fieldPane.add(fieldMag, 1, 1, 2, 1);
        isVfield.setPrefWidth(350.0);
        fieldPane.add(isVfield, 0, 2, 3, 1);
        
        stopPane.setVgap(3);
        stopPane.setMinWidth(width);stopPane.setMaxWidth(width);
        stopPane.getColumnConstraints().addAll(headerColumn, otherColumns, otherColumns);
        isVbound.setPrefWidth(76.0);
        stopPane.add(new Label("Stop condition (m): "), 0, 0, 3, 1);
        stopPane.add(isVbound, 0, 1, 1, 1);
        stopPane.add(stop, 1, 1, 2, 1);
        
        Region space = new Region();
        VBox.setVgrow(space, Priority.ALWAYS);
        
        exMessage.setStyle("-fx-font-size: 18;");
        exMessage.setWrapText(true);
        
        startButton.setTranslateY(-50);
        startButton.setPrefSize(300.0, 100.0);
        startButton.setStyle("-fx-font-size: 30; -fx-font-weight: bold; -fx-font-family: Segoe UI;");
        
        HBox hbox = new HBox(exMessage);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20, 0, 0, 0));
        this.getChildren().addAll(paneTitle,
                chargePane,
                fieldPane,
                stopPane,
                hbox,
                space,
                startButton
        );
        
    }
}
