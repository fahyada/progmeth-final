package utils;

import entity.Entity;
import panes.GamePanel;

public class CollisionChecker {
    private GamePanel gp;
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }
    public void checkTile(Entity entity) {
        double entityLeftx = entity.getX() + entity.getSolidArea().getX();
        double entityRightx = entity.getX() + entity.getSolidArea().getX() + entity.getSolidArea().getWidth();
        double entityTopy = entity.getY() + entity.getSolidArea().getY();
        double entityBottomy = entity.getY() + entity.getSolidArea().getY() + entity.getSolidArea().getHeight();

        double entityLeftcol = Math.max(0,entityLeftx/ GamePanel.tileSize);
        double entityRightcol = Math.min(15, entityRightx/ GamePanel.tileSize);
        double entityToprow = entityTopy/ GamePanel.tileSize;
        double entityBottomrow = entityBottomy/ GamePanel.tileSize;

        int tileNum1, tileNum2;

        switch (entity.getDirection()) {
            case "up":
                entityToprow = (entityTopy - entity.getSpeed())/GamePanel.tileSize;
                tileNum1 = gp.getTileManager().getMapTileNum()[(int) entityToprow][(int) entityLeftcol];
                tileNum2 = gp.getTileManager().getMapTileNum()[(int) entityToprow][(int) entityRightcol];
                if (gp.getTileManager().getTile()[tileNum1].isCollision() || gp.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomrow = (entityBottomy + entity.getSpeed())/GamePanel.tileSize;
                tileNum1 = gp.getTileManager().getMapTileNum()[(int) entityBottomrow][(int) entityLeftcol];
                tileNum2 = gp.getTileManager().getMapTileNum()[(int) entityBottomrow][(int) entityRightcol];
                if (gp.getTileManager().getTile()[tileNum1].isCollision() || gp.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftcol = (entityLeftx - entity.getSpeed())/ GamePanel.tileSize;
                tileNum1 = gp.getTileManager().getMapTileNum()[(int) entityToprow][(int) entityLeftcol];
                tileNum2 = gp.getTileManager().getMapTileNum()[(int) entityBottomrow][(int) entityLeftcol];
                if (gp.getTileManager().getTile()[tileNum1].isCollision() || gp.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityToprow = (entityTopy + entity.getSpeed())/ GamePanel.tileSize;
                tileNum1 = gp.getTileManager().getMapTileNum()[(int) entityToprow][(int) entityLeftcol];
                tileNum2 = gp.getTileManager().getMapTileNum()[(int) entityBottomrow][(int) entityRightcol];
                if (gp.getTileManager().getTile()[tileNum1].isCollision() || gp.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
        }
    }
}
