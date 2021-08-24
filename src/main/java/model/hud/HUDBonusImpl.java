package model.hud;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class HUDBonusImpl implements IHUDBonus {

    /*
     * HUD parameters
     */
    private final static int DIMENSIONS = 30;
    private final static int SPACING = 30;
    private static final double X_LAYOUT = 450;
    private static final double X_TRANSLATION = 645;

    /*
     * Control fields
     */
    private AnchorPane pane;
    private ImageView[] bonus = new ImageView[HUDParameters.TOTAL_BONUS];
    
    /*
     * Constructor
     */
    public HUDBonusImpl(final AnchorPane gamePane) {
        this.pane = gamePane;
        
        this.addBonuses();
    }

    @Override
    public ImageView[] getBonusTaken() {
        return this.bonus;
    }

    @Override
    public void addBonuses() {
        int index = HUDParameters.ZERO;
        
        while (index < 5) {
            this.bonus[index] = new ImageView(new Image(new File(HUDParameters.PNG_FOLDER + "BonusSpeed.png").toURI().toString(), DIMENSIONS, 
                    DIMENSIONS, HUDParameters.RATIO, HUDParameters.SMOOTH));
            this.bonus[index].setLayoutX(X_LAYOUT);
            this.bonus[index].setLayoutY(index * -SPACING);
            this.bonus[index].setTranslateY(X_TRANSLATION);
            this.bonus[index].setViewOrder(HUDParameters.VIEW_ORDER);
            
            this.pane.getChildren().add(this.bonus[index]);
            
            index++;
        }
    }

    @Override
    public void showBonus(final int index) {
        this.pane.getChildren().add(this.bonus[index]);
    }

    @Override
    public void hideBonus(final int index) {
        this.pane.getChildren().remove(this.bonus[index]);
    }   
}
