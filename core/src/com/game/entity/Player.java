package com.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.game.data.DefaultData;
import com.game.main.titles.GameScreen;

public class Player extends Entity {

    private final GameScreen game;

    public Player(float width, float height, GameScreen game) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.game = game;

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

    public boolean isVisible(float x, float y, int minusWidth, int minusHeight){
        return x > (game.touchHandler.getX() + getWidth() / 2f) - DefaultData.cameraWidth - width + minusWidth
                && x < (game.touchHandler.getX() + getWidth() / 2f) + DefaultData.cameraWidth - minusWidth
                && y > (game.touchHandler.getY() + getHeight() / 2f) - DefaultData.cameraHeight - height + minusHeight
                && y < (game.touchHandler.getY() + getHeight() / 2f) + DefaultData.cameraHeight - minusHeight;
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
