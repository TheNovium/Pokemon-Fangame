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
        super(new Entity.Properties()
                .setHitBox(0.0625f, 0.0625f, 0.875f, 0.875f)
                .setSpeed(0.05f));
        TextureAtlasHandler handler = Renderer.get().getHandler();
        spr = new SpriteRenderer(TextureAtlasType.ENTITY);
        spr.addDrawLocation(handler.getDrawLocationForResourceLocation(GameResourceLocations.PLAYER_UP));
        spr.addDrawLocation(handler.getDrawLocationForResourceLocation(GameResourceLocations.PLAYER_RIGHT));
        spr.addDrawLocation(handler.getDrawLocationForResourceLocation(GameResourceLocations.PLAYER_DOWN));
        spr.addDrawLocation(handler.getDrawLocationForResourceLocation(GameResourceLocations.PLAYER_LEFT));
        obj = new GameObject("Player", new Transform(new Vector2f(getHitBox().getX() * Renderer.DEFAULT_TILE_SIZE, getHitBox().getY() * Renderer.DEFAULT_TILE_SIZE), new Vector2f(getHitBox().getW() * Renderer.DEFAULT_TILE_SIZE, getHitBox().getH() * Renderer.DEFAULT_TILE_SIZE * Window.get().getAspectRatio()), Renderer.TEXT_Z));
        obj.addComponent(spr);
        Renderer.get().add(obj);
    }

    @Override
    public void tick(Level level) {
        if(KeyInput.isHeld(KeyInput.MOVE_UP)){
            if(getFacing() != Direction.NORTH){
                setFacing(Direction.NORTH);
                spr.setFrame(0);
            }
            level.move(this, Direction.NORTH);
        }
        if(KeyInput.isHeld(KeyInput.MOVE_RIGHT)){
            if(getFacing() != Direction.EAST){
                setFacing(Direction.EAST);
                spr.setFrame(1);
            }
            level.move(this, Direction.EAST);
        }
        if(KeyInput.isHeld(KeyInput.MOVE_DOWN)){
            if(getFacing() != Direction.SOUTH){
                setFacing(Direction.SOUTH);
                spr.setFrame(2);
            }
            level.move(this, Direction.SOUTH);
        }
        if(KeyInput.isHeld(KeyInput.MOVE_LEFT)){
            if(getFacing() != Direction.WEST){
                setFacing(Direction.WEST);
                spr.setFrame(3);
            }
            level.move(this, Direction.WEST);
        }
    }


}
