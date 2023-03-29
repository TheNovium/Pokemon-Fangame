package space.novium.nebula.world.entity;

import space.novium.nebula.KeyInput;
import space.novium.nebula.core.GameObject;
import space.novium.nebula.core.components.SpriteRenderer;
import space.novium.nebula.core.resources.registration.GameResourceLocations;
import space.novium.nebula.graphics.gui.Window;
import space.novium.nebula.graphics.renderer.Renderer;
import space.novium.nebula.graphics.renderer.Transform;
import space.novium.nebula.graphics.texture.TextureAtlasHandler;
import space.novium.nebula.graphics.texture.TextureAtlasType;
import space.novium.nebula.world.enums.Direction;
import space.novium.nebula.world.level.Level;
import space.novium.utils.math.Vector2f;

public class Player extends Entity {
    private final SpriteRenderer spr;
    private final GameObject obj;

    public Player(){
        super(new Entity.Properties());
        TextureAtlasHandler handler = Renderer.get().getHandler();
        spr = new SpriteRenderer(TextureAtlasType.ENTITY);
        spr.addDrawLocation(handler.getDrawLocationForResourceLocation(GameResourceLocations.PLAYER_UP));
        spr.addDrawLocation(handler.getDrawLocationForResourceLocation(GameResourceLocations.PLAYER_RIGHT));
        spr.addDrawLocation(handler.getDrawLocationForResourceLocation(GameResourceLocations.PLAYER_DOWN));
        spr.addDrawLocation(handler.getDrawLocationForResourceLocation(GameResourceLocations.PLAYER_LEFT));
        obj = new GameObject("Player", new Transform(new Vector2f(0, 0), new Vector2f(0.1f, 0.1f * Window.get().getAspectRatio()), Renderer.TEXT_Z));
        obj.addComponent(spr);
        Renderer.get().add(obj);
    }

    @Override
    public void tick(Level level) {
        if(KeyInput.isHeld(KeyInput.MOVE_UP)){
            setFacing(Direction.NORTH);
            spr.setFrame(0);
            move(0.0f, getSpeed() * getFacing().getDirY());
        }
        if(KeyInput.isHeld(KeyInput.MOVE_RIGHT)){
            setFacing(Direction.EAST);
            spr.setFrame(1);
            move(getSpeed() * getFacing().getDirX(), 0.0f);
        }
        if(KeyInput.isHeld(KeyInput.MOVE_DOWN)){
            setFacing(Direction.SOUTH);
            spr.setFrame(2);
            move(0.0f, getSpeed() * getFacing().getDirY());
        }
        if(KeyInput.isHeld(KeyInput.MOVE_LEFT)){
            setFacing(Direction.WEST);
            spr.setFrame(3);
            move(getSpeed() * getFacing().getDirX(), 0.0f);
        }
    }
}
