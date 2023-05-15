package com.game.entity;

import com.badlogic.gdx.math.Rectangle;
import com.game.main.TouchHandler;
import com.game.world.Map;
import com.game.world.TileSolidBox;

public class SolidArea {
    private final TouchHandler touchH;
    private final Player player;
    private final Map map;
    private int tilesInCollisionArea;

    public SolidArea(Map map, TouchHandler touchH, Player player) {
        this.map = map;
        this.touchH = touchH;
        this.player = player;
    }

    public void render() {
        for (TileSolidBox solidBox : map.getTileManager().getSolidBoxes()) {
            if (Player.isVisible((int) solidBox.x, (int) solidBox.y, 0, 0)) {
                tilesInCollisionArea++;
                playerVisible(solidBox);
            }
        }
        if (tilesInCollisionArea == 0) {
            touchH.collisionDeltaPlusX = 1;
            touchH.collisionDeltaMinusX = 1;
            touchH.collisionDeltaPlusY = 1;
            touchH.collisionDeltaMinusY = 1;
        }
        tilesInCollisionArea = 0;
    }

    private void playerVisible(Rectangle solidBox) {
        if (player.getHitBox().overlaps(solidBox)) {
            if (collisionChecker(player.getHitBox(), solidBox).equals("left")) {
                touchH.collisionDeltaMinusX = 0;
            }
            if (collisionChecker(player.getHitBox(), solidBox).equals("right")) {
                touchH.collisionDeltaPlusX = 0;
            }
            if (collisionChecker(player.getHitBox(), solidBox).equals("top")) {
                touchH.collisionDeltaPlusY = 0;
            }
            if (collisionChecker(player.getHitBox(), solidBox).equals("bottom")) {
                touchH.collisionDeltaMinusY = 0;
            }
        } else {
            tilesInCollisionArea--;
        }
    }

    private String collisionChecker(Rectangle solidBox1, Rectangle solidBox2) {
        String direction = "nothing";
        if (solidBox1.getY() <= solidBox2.y + solidBox2.height - 10 && solidBox1.getY() + solidBox1.getHeight() > solidBox2.y + 10) {
            if (solidBox1.getX() <= solidBox2.x + solidBox2.width && solidBox1.getX() > solidBox2.x) {
                direction = "left";
            }
            if (solidBox1.getX() + solidBox1.getWidth() >= solidBox2.x && solidBox1.getX() + solidBox1.getWidth() < solidBox2.x + solidBox2.width) {
                direction = "right";
            }
        }
        if (solidBox1.getX() <= solidBox2.x + solidBox2.width - 10 && solidBox1.getX() + solidBox1.getWidth() > solidBox2.x + 10) {
            if (solidBox1.getY() <= solidBox2.y + solidBox2.height && solidBox1.getY() > solidBox2.y) {
                direction = "bottom";
            }
            if (solidBox1.getY() + solidBox1.getHeight() >= solidBox2.y && solidBox1.getY() + solidBox1.getHeight() < solidBox2.y + solidBox2.height) {
                direction = "top";
            }
        }
        return direction;
    }
}
