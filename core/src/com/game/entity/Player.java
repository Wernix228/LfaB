package com.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.game.data.DefaultData;
import com.game.main.titles.GameScreen;

public class Player extends Entity {

    private static GameScreen game = null;

    public Player(float width, float height, GameScreen game) {
        Entity.width = width;
        Entity.height = height;
        Player.game = game;

        img = new Texture("textures/player/step1Right.png");

    }

    public void render(){
        this.x = game.touchHandler.getX();
        this.y = game.touchHandler.getY();

        if(game.touchHandler.isRewritingTouch()){
            if(game.touchHandler.getSide().equals("left")){
                setIMG("textures/player/step1Left.png");
            }else if(game.touchHandler.getSide().equals("right")){
                setIMG("textures/player/step1Right.png");
            }
        }else{
            if(game.touchHandler.getSide().equals("left")){
                animation("textures/player/step1Left.png", "textures/player/step2Left.png", 30);
            }else if(game.touchHandler.getSide().equals("right")){
                animation("textures/player/step1Right.png", "textures/player/step2Right.png", 30);
            }
        }

        setUpHitBox();
    }

    public static boolean isVisible(float x, float y, int minusWidth, int minusHeight){
        return x > (game.touchHandler.getX() + getWidth() / 2f) - DefaultData.width - width + minusWidth
                && x < (game.touchHandler.getX() + getWidth() / 2f) + DefaultData.width - minusWidth
                && y > (game.touchHandler.getY() + getHeight() / 2f) - DefaultData.height - height + minusHeight
                && y < (game.touchHandler.getY() + getHeight() / 2f) + DefaultData.height - minusHeight;
    }
    public static boolean isVisible(Rectangle rectangle){
        return rectangle.x > (game.touchHandler.getX() + getWidth() / 2f) - DefaultData.width - width + 0
                && rectangle.x < (game.touchHandler.getX() + getWidth() / 2f) + DefaultData.width - 0
                && rectangle.y > (game.touchHandler.getY() + getHeight() / 2f) - DefaultData.height - height + 0
                && rectangle.y < (game.touchHandler.getY() + getHeight() / 2f) + DefaultData.height - 0;
    }

    private int animationTime;
    private int animationTextureType = 1;

    private void animation(String texture1, String texture2, int animationTextureCooldown) {
        animationTime++;
        if (animationTime >= animationTextureCooldown) {
            if (animationTextureType == 1) {
                setIMG(texture1);
                animationTextureType = 2;
            } else if (animationTextureType == 2) {
                setIMG(texture2);
                animationTextureType = 1;
            }
            animationTime = 0;
        }
    }

}
