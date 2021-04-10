package fieldcontroller;

import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import fieldmodel.Charge;
import fieldmodel.ChargeImage;
import fieldmodel.EFVector;
import fieldmodel.ElectricField;
import fieldmodel.Prefix;
import fieldmodel.PrefixValue;
import view.ChargeImagesBox;
import view.GraphPane;
import view.RightBorder;
import view.ElectricFieldPane;

/**
 *
 * @author ismail
 */

public class EFController {
    private ElectricFieldPane fieldPane;
    private int mouseX;
    private int mouseY;
    private double size;
    private double ratio;
    private Circle vectorCircle;
    private ChargeImage draggedImage;
    private ArrayList<Charge> chargeList;
    private ArrayList<ElectricField> electricFieldList;
    private ArrayList<EFVector> vectorList;
    private Charge selectedCharge;
    private DecimalFormat df = new DecimalFormat("###.##");
    private Prefix gridPrefix;

    public EFController() throws FileNotFoundException {
        this.fieldPane = new ElectricFieldPane(120.00);
        this.chargeList        = new ArrayList<>();
        this.electricFieldList = new ArrayList<>();
        this.vectorList        = new ArrayList<>();
        vectorCircle = new Circle(26, Color.TRANSPARENT);
        vectorCircle.setStrokeWidth(2);
        vectorCircle.setStroke(Paint.valueOf("red"));
        vectorCircle.setStrokeType(StrokeType.OUTSIDE);
        
        this.size = getBorder().getGridProperty().getSize().getValue();
        this.ratio = size/672;
        this.gridPrefix = getBorder().getGridProperty().getSize().getPrefix();
        
        setHandlers();
    }
    
    private void setHandlers(){
        setChargeImageHandler(getImageBox().getPositive());
        setChargeImageHandler(getImageBox().getNegative());
        setGraphHandler();
        setGridHandlers();
    }
    
    public void createVectors(){
        if(getGraph().getGrid().getChildren().contains(vectorCircle)){
            getGraph().getGrid().removeElement(vectorCircle);
            getGraph().getStatusLabel().setText("");
        }
        if(chargeList.isEmpty()){
           if(!vectorList.isEmpty()){
                for(EFVector v : vectorList){
                    getGraph().getGrid().removeElement(v.getImage());
                }
                vectorList.clear();
            }
        }
        if(!chargeList.isEmpty()){
            //Emptying existing vectors 
            if(!vectorList.isEmpty()){
                for(EFVector v : vectorList){
                    getGraph().getGrid().removeElement(v.getImage());
                }
                vectorList.clear();
            }
            
            int columns = getGraph().getGrid().getColumns();
            int rows = getGraph().getGrid().getRows();
            //Looping
            for(int j = 0;j<rows;j++){
                int y = j*56;
                for(int i = 0;i<columns;i++){
                    int x = i*56;
                    electricFieldList.clear();
                    for(Charge q : chargeList){
                        ElectricField ef = new ElectricField(x, y, q, ratio, PrefixValue.getPrefixValue(gridPrefix));
                        electricFieldList.add(ef);
                    }
                    ElectricField netElectricField = ElectricField.sumOfElectricFields(electricFieldList);
                    EFVector v = new EFVector(netElectricField, x, y);
                    setVectorHandler(v, x, y);
                    vectorList.add(v);
                    getGraph().getGrid().insertElement(v.getImage());
                }
                //Removing an re-inserting elements to refresh the graph's content
                getGraph().removeElement(getGraph().getGrid());
                getGraph().insertElement(getGraph().getGrid());
                for(Charge c : chargeList){
                    if(getGraph().getChildren().contains(c.getSelectedImage())){
                        getGraph().getChildren().remove(c.getSelectedImage());
                        getGraph().getChildren().add(c.getSelectedImage());
                    }if(getGraph().getChildren().contains(c.getImage())){
                        getGraph().getChildren().remove(c.getImage());
                        getGraph().getChildren().add(c.getImage());
                    }
                }
            }
        }
    }
    
    public Charge createNewCharge(double charge, Prefix prefix, double x, double y, boolean isPositive){
        Charge q = new Charge(charge, prefix, mouseX-773, mouseY-565, isPositive, ratio);
        q.getImage().setTranslateX(mouseX-773);
        q.getImage().setTranslateY(mouseY-565);
        q.getSelectedImage().setTranslateX(q.getImage().getTranslateX());
        q.getSelectedImage().setTranslateY(q.getImage().getTranslateY());
        //Set handler to the new charge
        q.getImage().setOnMousePressed(e->setSelectChargeHandler(q));
        q.getSelectedImage().setOnMousePressed(e->setSelectChargeHandler(q));
       
        //Set default values in charge properties
        q.getProperty().getCharge().getField().setText(q.getCharge()+"");
        
        String xpos = df.format(q.getExactXpos());
        String ypos = df.format(-1*q.getExactYpos());
        
        q.getProperty().getXpos().getField().setText(xpos);
        q.getProperty().getYpos().getField().setText(ypos);
        q.getProperty().getXpos().getPrefixProperty().setValue(getBorder().getGridProperty().getSize().getPrefix());
        q.getProperty().getYpos().getPrefixProperty().setValue(getBorder().getGridProperty().getSize().getPrefix());
        
        q.getProperty().getApply().setOnAction(e->setApplyButtonHandler(q));
        q.getDelete().getDelete().setOnAction(e->setDeleteButtonHandler(q));
        
        return q;
    }
    
    public void setGraphHandler(){
        getGraph().setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(DragEvent event) {
 
                Dragboard db = event.getDragboard();
                if(db.hasImage()){
                    event.acceptTransferModes(TransferMode.MOVE);
                    mouseX = (int) event.getSceneX();
                    mouseY = (int) event.getSceneY();
                }
                event.consume();
            }
        });
        getGraph().setOnDragDropped(new EventHandler <DragEvent>(){
            @Override
            public void handle(DragEvent event) {             
                    if(mouseX < 101 || mouseX > 1445 || mouseY > 957 || mouseY < 173){
                        getBorder().getError().setText("Position out of bounds.\nPlease try again.");
                        getBorder().getErrorBox().setVisible(true);
                    }else{
                    clearError();
                    Dragboard db = event.getDragboard();
                    
                    if(db.hasImage()){
                    //Creates new charge
                    Charge q = createNewCharge(1, Prefix.no_prefix, mouseX, mouseY, draggedImage.isPositive());
                    //Add charge to the list
                    chargeList.add(q);
                    //Add its selected image
                    getGraph().insertElement(q.getSelectedImage());
                    //Manage the selected charge
                    selectedCharge = q;
                    changeChargeProperty(q);
                    //Deselect other charges
                    if(chargeList.size()!=1){
                        for(Charge c : chargeList){
                            if(getGraph().getChildren().contains(c.getSelectedImage()) && !c.equals(q)){
                               getGraph().removeElement(c.getSelectedImage());
                               getGraph().insertElement(c.getImage());
                            }
                        }
                    }
                    createVectors();
                    event.setDropCompleted(true);
                    }
                    else{
                    event.setDropCompleted(false);
                    }
                event.consume();
                }
            }
        });
    }
    
    public void changeChargeProperty(Charge q){
        boolean selectedFound = false;
        for(Charge c : chargeList){
            if(getBorder().getChildren().contains(c.getProperty())){
                getBorder().getChildren().remove(3);
                getBorder().getChildren().add(q.getProperty());
                selectedFound = true;
            }
        }if(!selectedFound){
            getBorder().getChildren().add(q.getProperty());
        }
        getBorder().getChargeBox().getSelect().setVisible(false);
    }
    
    public void clearError(){
        if(getBorder().getErrorBox().isVisible()){
            getBorder().getErrorBox().setVisible(false);
        }
    }
    
    public void setGridHandlers(){
        getBorder().getGridProperty().getApply().setOnAction(e -> setGridButtonHandler());
        getBorder().getGridProperty().getSize().getPrefixProperty().setOnAction(e -> setGridButtonHandler());
        
    }
    
    public void setGridButtonHandler(){
        double currentGridPrefix = PrefixValue.getPrefixValue(getBorder().getGridProperty().getSize().getPrefix());
        double previousGridPrefix = PrefixValue.getPrefixValue(gridPrefix);
        if(previousGridPrefix > currentGridPrefix || size > getBorder().getGridProperty().getSize().getValue()){
            Alert alert = new Alert(AlertType.WARNING, "Setting a lower prefix or size will delete all the charges.\n"
                                                + "Are you sure you want to continue ?", ButtonType.YES, ButtonType.NO);
            alert.setOnShown(e-> Toolkit.getDefaultToolkit().beep());
            alert.showAndWait();
            //If the user selects yes => deletes everything
            if(alert.getResult() == ButtonType.YES){
                this.size = getBorder().getGridProperty().getSize().getValue();
                this.ratio = size/672;
                this.fieldPane.setSize(size);
                for(Charge c : chargeList){
                    if(getGraph().getChildren().contains(c.getSelectedImage())){
                        getGraph().removeElement(c.getSelectedImage());
                    }
                    if(getGraph().getChildren().contains(c.getImage())){
                        getGraph().removeElement(c.getImage());
                    }
                    selectedCharge = null;
                }
                if(!chargeList.isEmpty()){
                    getBorder().getChildren().remove(3);
                }
                chargeList.clear();
                gridPrefix = getBorder().getGridProperty().getSize().getPrefix();
                getBorder().getChargeBox().getSelect().setVisible(true);
                clearError();
                createVectors();
            }else{
                //If the user selects no => reverts grid properties changes
                if(alert.getResult() == ButtonType.NO){
                    if(previousGridPrefix > currentGridPrefix){
                        getBorder().getGridProperty().getSize().getPrefixProperty().setOnAction(null);
                        Platform.runLater(() -> getBorder().getGridProperty().getSize().getPrefixProperty().getSelectionModel().select(gridPrefix));
                        getBorder().getGridProperty().getSize().getPrefixProperty().setOnAction(e->setGridButtonHandler());
                    }
                    else{
                        getBorder().getGridProperty().getSize().getField().setText(Double.toString(size));
                    }
                }  
            }
        }else{
            applyGrid();
            createVectors();
        }
        
    }
    
    public void applyGrid(){
        double gPrefix = PrefixValue.getPrefixValue(getBorder().getGridProperty().getSize().getPrefix());
        gridPrefix = getBorder().getGridProperty().getSize().getPrefix();
        this.size = getBorder().getGridProperty().getSize().getValue();
        this.ratio = size/672;
        this.fieldPane.setSize(size);
        if(!chargeList.isEmpty()){
            //Looping and updating every charge's position
            for(Charge c : chargeList){
                c.setRatio(size/672);
                double xPrefix = PrefixValue.getPrefixValue(c.getProperty().getXpos().getPrefix());
                double yPrefix = PrefixValue.getPrefixValue(c.getProperty().getYpos().getPrefix());
                //Update charge's position
                if(getGraph().getChildren().contains(c.getSelectedImage())){
                    getGraph().removeElement(c.getSelectedImage());
                    c.getSelectedImage().setTranslateX((c.getProperty().getXpos().getValue())*(1/ratio)*(xPrefix)*(1/gPrefix));
                    c.getSelectedImage().setTranslateY(-(c.getProperty().getYpos().getValue())*(1/ratio)*(yPrefix)*(1/gPrefix));
                    getGraph().insertElement(c.getSelectedImage());
                }if(getGraph().getChildren().contains(c.getImage())){
                    getGraph().removeElement(c.getImage());
                    c.getSelectedImage().setTranslateX((c.getProperty().getXpos().getValue())*(1/ratio)*(xPrefix)*(1/gPrefix));
                    c.getSelectedImage().setTranslateY(-(c.getProperty().getYpos().getValue())*(1/ratio)*(yPrefix)*(1/gPrefix));
                    getGraph().insertElement(c.getImage());
                }
            }
        }
        //Updating the axis
        this.getGraph().insertLabelAxis(size);
        clearError();
    }
    
    public void setChargeImageHandler(ChargeImage image){
        image.setOnMouseEntered(event->{
            image.setCursor(Cursor.HAND);
        });
        image.setOnDragDetected(new EventHandler <MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clearError();
                //Allow any transfer mode 
                draggedImage = (ChargeImage) event.getSource();
                ((Node) event.getSource()).setCursor(Cursor.CROSSHAIR);
                Dragboard db = image.startDragAndDrop(TransferMode.MOVE);
                
                //Put a image on dragboard
                ClipboardContent content = new ClipboardContent();
                Image image2;
                if(image.isPositive()){
                    image2 = new Image("images/positive_32.png");
                }else{
                    image2 = new Image("images/negative_32.png");
                }
                
                content.putImage(image2);
                db.setContent(content);
                
                mouseX = (int) event.getSceneX();
                mouseY = (int) event.getSceneY();
                
                event.consume();
            }
        });
    }
    
    
    public void setDeleteButtonHandler(Charge q){
        //If it's the last charge, then remove all charges
        if(chargeList.size()==1){
            getGraph().removeElement(chargeList.get(0).getSelectedImage());
            getBorder().getChildren().remove(chargeList.get(0).getProperty());
            chargeList.remove(q);
            selectedCharge = null;
            q = null;
            getBorder().getChargeBox().getSelect().setVisible(true);
        }else{
            if(chargeList.get(0).equals(q)){
                setSelectChargeHandler(chargeList.get(1));
            }else{
                setSelectChargeHandler(chargeList.get(0));
            }
            getGraph().removeElement(q.getImage());
            chargeList.remove(q);
            selectedCharge = chargeList.get(0);
            q = null;
        }
        createVectors();
        clearError();
    }
    
    public void setVectorHandler(EFVector v, double x, double y){
        v.getImage().setOnMouseClicked(e->{
            if(!v.isPressed()){
                for(EFVector vector : vectorList){
                    if(vector.isPressed()){
                        vector.setPressed(false);
                        getGraph().getStatusLabel().setText("");
                        getGraph().getGrid().removeElement(vectorCircle);
                    }
                }
                v.setPressed(true);
                double offsetX = 18*Math.cos(v.getEf().getAngle());
                double offsetY = 18*Math.sin(v.getEf().getAngle());
                this.vectorCircle.setCenterX(x+offsetX);
                this.vectorCircle.setCenterY(y-offsetY);
                getGraph().getGrid().insertElement(vectorCircle);
                getGraph().getStatusLabel().setText(v.getEf().toString());
            }
        });
    }
    
    public void setApplyButtonHandler(Charge q){
        //Check if the user has selected all prefixes
        q.setCharge(q.getProperty().getCharge().getValue());
        double xPrefix = PrefixValue.getPrefixValue(q.getProperty().getXpos().getPrefix());
        double yPrefix = PrefixValue.getPrefixValue(q.getProperty().getYpos().getPrefix());
        double gPrefix = PrefixValue.getPrefixValue(getBorder().getGridProperty().getSize().getPrefix());
        double xpos = q.getProperty().getXpos().getValue()*xPrefix*(1/gPrefix);
        double ypos = q.getProperty().getYpos().getValue()*yPrefix*(1/gPrefix);
        //Check if the positon is in bounds
        if(xpos > size+0.0001        || xpos  < (-1*size-0.0001) ||
           ypos > (size+0.001)*7/12  || ypos  < (-1*(size+0.0001)*7/12)){
            getBorder().getErrorBox().setVisible(true);
            getBorder().getError().setText("Position out of bounds.\nPlease try again.");
        }else{
        q.getImage().setTranslateX(xpos*(1/ratio));
        q.getImage().setTranslateY(-ypos*(1/ratio));
        q.setPrefix(q.getProperty().getCharge().getPrefix());
        q.setCharge(q.getProperty().getCharge().getValue());
        clearError();
        createVectors();
        }
        //Check if the positon is in bounds
        if(xpos > size+0.0001        || xpos  < (-1*size-0.0001) ||
           ypos > (size+0.001)*7/12  || ypos  < (-1*(size+0.0001)*7/12)){
            getBorder().getErrorBox().setVisible(true);
            getBorder().getError().setText("Position out of bounds.\nPlease try again.");
        }else{
        //Updating charge position
        q.getImage().setTranslateX(xpos*(1/ratio));
        q.getImage().setTranslateY(-ypos*(1/ratio));
        q.setPrefix(q.getProperty().getCharge().getPrefix());
        q.setCharge(q.getProperty().getCharge().getValue());
        clearError();
        createVectors();
        }      
    }
    
    public void setSelectChargeHandler(Charge q){
        //If the there are only one charge, then the selected charge 
        //is the same as the first index on the chargeList
        if(chargeList.size()==1){
            selectedCharge = chargeList.get(0);
        }else{
            //Else, remove the previous selected charge's selected image
            for(Charge c : chargeList){
                if(getGraph().getChildren().contains(c.getSelectedImage())){
                    getGraph().removeElement(c.getSelectedImage());
                    getGraph().insertElement(c.getImage());
                }
            }
            //Changing selected charge to current charge
            selectedCharge = q;
            changeChargeProperty(q);
            //Change the selected charge's image
            getGraph().removeElement(q.getImage());
            getGraph().insertElement(q.getSelectedImage());
        }
    }
    
    public void start(Stage stage){
        Scene scene = new Scene(fieldPane, 1920, 1080);
        scene.getStylesheets().add("styles/layoutstyles.css");
        
        stage.setTitle("Electric Field Simulation - EMS");
        stage.setScene(scene);
        stage.show();
    }
    
    public RightBorder getBorder(){
        return fieldPane.getRightBorder();
    }
    
    public GraphPane getGraph(){
        return fieldPane.getGrid();
    }
    
    public ChargeImagesBox getImageBox(){
        return getBorder().getChargeBox().getChargeImages();
    }

    public ElectricFieldPane getFieldPane() {
        return fieldPane;
    }
    
}