/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author ismai
 */
public class MenuBorder extends BorderPane{
    
    private MenuBar menuBar = new MenuBar();
    private Menu menuItems = new Menu("Scene");
    private MenuItem itemForce = new MenuItem("Electric Force");
    private MenuItem itemMotion = new MenuItem("Charge Motion");
    private MenuItem itemField = new MenuItem("Electric Field");
    private MenuItem itemMenu = new MenuItem("Main Menu");
    private MenuItem itemExit = new MenuItem("Exit");

    public MenuBorder() {
        this.setPrefSize(1920, 1010);
        menuItems.getItems().addAll(itemField, itemForce, itemMotion, itemMenu, itemExit);
        menuBar.getMenus().add(menuItems);
        this.setTop(menuBar);
    }

    public MenuItem getItemExit() {
        return itemExit;
    }

    public MenuItem getItemField() {
        return itemField;
    }

    public MenuItem getItemForce() {
        return itemForce;
    }

    public MenuItem getItemMotion() {
        return itemMotion;
    }

    public MenuItem getItemMenu() {
        return itemMenu;
    }
    
    
    
}
