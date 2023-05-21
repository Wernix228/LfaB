package com.game.entity;

import com.badlogic.gdx.math.Rectangle;
import com.game.data.DefaultData;
import com.game.main.TouchHandler;
import com.game.world.Map;
import com.game.world.TileSolidBox;

public class SolidArea {
    private final TouchHandler touchH;
    private final Player player;
    private final Map map;
    private NPCManager npcManager;
    private int tilesInCollisionArea;

    public SolidArea(Map map, TouchHandler touchH, Player player,NPCManager npcManager) {
        this.map = map;
        this.touchH = touchH;
        this.player = player;
        this.npcManager = npcManager;
    }

    public void render() {
        for (TileSolidBox solidBox : map.getTileManager().getSolidBoxes()) {
            if (Player.isVisible((int) solidBox.x, (int) solidBox.y, 0,0)) {
                tilesInCollisionArea++;
                playerVisible(solidBox);
                for (NPC n : npcManager.npcs) {
                    npcVisible(solidBox,n);
                }
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

            if (touchH.collisionDeltaPlusX == 0 && touchH.collisionDeltaMinusX == 0) {
                if (player.getHitBox().x + player.getHitBox().width / 2 > solidBox.x) {
                    touchH.collisionDeltaPlusX=1;
                } else if (player.getHitBox().x + player.getHitBox().width / 2 < solidBox.x) {
                    touchH.collisionDeltaMinusX=1;
                }
            }

            if (touchH.collisionDeltaPlusY == 0 && touchH.collisionDeltaMinusY == 0) {
                if (player.getHitBox().y + player.getHitBox().height / 2 > solidBox.y) {
                    touchH.collisionDeltaPlusY=1;
                } else if (player.getHitBox().y + player.getHitBox().height / 2 < solidBox.y) {
                    touchH.collisionDeltaMinusY=1;
                }
            }

        } else {
            tilesInCollisionArea--;
        }
    }
    private void npcVisible(Rectangle rectangle,NPC npc){
        if (!collisionChecker(npc.getHitBox(), rectangle).equals("nothing")) {
            npc.setCollSide(collisionChecker(npc.getHitBox(), rectangle));
            if(collisionChecker(npc.getHitBox(), rectangle).equals("top")) npc.setY(npc.getY()-npc.getSpeed());
            if(collisionChecker(npc.getHitBox(), rectangle).equals("bottom")) npc.setY(npc.getY()+npc.getSpeed());
            if(collisionChecker(npc.getHitBox(), rectangle).equals("left")) npc.setX(npc.getX()+npc.getSpeed());
            if(collisionChecker(npc.getHitBox(), rectangle).equals("right")) npc.setX(npc.getX()-npc.getSpeed());
        }
//        if (!collisionChecker(rectangle,npc.getHitBox()).equals("nothing")) System.out.println(collisionChecker(rectangle,npc.getHitBox()));
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
