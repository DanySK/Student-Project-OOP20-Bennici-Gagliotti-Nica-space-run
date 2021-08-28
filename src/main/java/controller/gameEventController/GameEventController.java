package controller.gameEventController;

import Utilities.HUDParameters;
import controller.collisionEngine.PhysicsEngine;
import controller.collisionEngine.PhysicsEngineImpl;
import model.hud.EndGameGUI;
import model.hud.HUDBonusImpl;
import model.hud.HUDLifeImpl;
import model.hud.HUDPointsImpl;
import view.gameField.GameField;

public class GameEventController implements IGameEventController {

    /*
     * HUD values
     */


    /*
     * Game Container reference and HUD elements
     */
    private HUDPointsImpl hud;
    private HUDLifeImpl playerLives;
    private PhysicsEngineImpl collisionEngine;
    private HUDBonusImpl bonusHUD;
    private GameField gameField;

    /**
     * Constructor.
     * @param game field.
     */
    public GameEventController(final GameField gameField) {
        this.gameField = gameField;

        this.createHUD();
    }

    @Override
    public void endGame() {
        new EndGameGUI().end(this.checkPoints());
    }

    @Override
    public void createHUD() {
        /*
         * Points HUD, setting poisition on screen due to his definition:
         * it is a label part so it needs a position on screen and it needs
         * to be added on the nodes system.
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
    public boolean checkGameStatus() {
        return this.playerLives.getStatus();
    }

    @Override
    public int checkPoints() {
        return this.hud.getPoints();
    }

    @Override
    public int checkLives() {
        return this.playerLives.getLifePoints();
    }
//
//    @Override
//    public HUDBonusImpl getBonusImpl() {
//        return this.bonusHUD;
//    }

    @Override
    public PhysicsEngine getCollisionEngine() {
        return this.collisionEngine;
    }
}