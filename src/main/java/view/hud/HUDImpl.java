package view.hud;

import controller.collisionEngine.PhysicsEngine;
import controller.collisionEngine.PhysicsEngineImpl;
import model.hud.HUDBonusImpl;
import model.hud.HUDLifeImpl;
import model.hud.HUDPointsImpl;
import utilities.HUDParameters;
import view.gameField.GameField;

public class HUDImpl implements IHUD {

     /*
     * Game Container reference and HUD elements.
     */
    private HUDPointsImpl hud;
    private HUDLifeImpl playerLives;
    private PhysicsEngineImpl collisionEngine;
    private HUDBonusImpl bonusHUD;
    private GameField gameField;

    public HUDImpl(final GameField gameField) {

    	this.gameField = gameField;

    	this.createHUD();
    }

    @Override
    public final void createHUD() {
        /*
         * Points HUD, setting position on screen due to his definition:
         * it is a label part so it needs a position on screen and it needs
         * to be added on the nodes system, same for other nodes.
         */
        this.hud = new HUDPointsImpl();
        this.gameField.getGameContainer().getChildren().add(this.hud);
        this.hud.setViewOrder(HUDParameters.VIEW_ORDER);

        /*
         * Lives HUD
         */
        this.playerLives = new HUDLifeImpl(this.gameField.getGameContainer());

        /*
         * PowerUP HUD
         */
        this.bonusHUD = new HUDBonusImpl(this.gameField);

        /*
         * Collision engine comes with HUD creation!
         */
        this.collisionEngine = new PhysicsEngineImpl(this.gameField, this.hud, this.playerLives, this.bonusHUD);
    }

    @Override
    public final boolean checkGameStatus() {
        return this.playerLives.getStatus();
    }

    @Override
    public final int checkPoints() {
        return this.hud.getPoints();
    }

    @Override
    public final int checkLives() {
        return this.playerLives.getLifePoints();
    }

    @Override
    public final HUDBonusImpl getBonusImpl() {
        return this.bonusHUD;
    }

    @Override
    public final PhysicsEngine getCollisionEngine() {
        return this.collisionEngine;
    }
}