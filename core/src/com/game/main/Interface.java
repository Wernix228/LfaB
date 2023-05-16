package com.game.main;

import com.badlogic.gdx.graphics.Texture;

public class Interface {

    public Texture[] textures = new Texture[2];

    public Interface(){
        textures[0] = new Texture("textures/interface/controller.png");
        textures[1] = new Texture("textures/tiles/003.png");
    }

}
