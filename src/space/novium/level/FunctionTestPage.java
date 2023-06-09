package space.novium.level;

import com.google.gson.JsonObject;
import space.novium.level.registration.GameFonts;
import space.novium.nebula.core.TilePos;
import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.nebula.graphics.Camera;
import space.novium.nebula.graphics.gui.parts.ImagePart;
import space.novium.nebula.graphics.gui.parts.hud.PlayerInGameMenu;
import space.novium.nebula.graphics.gui.parts.TextPart;
import space.novium.nebula.graphics.gui.parts.enums.TextAlign;
import space.novium.nebula.graphics.renderer.Renderer;
import space.novium.nebula.world.Chunk;
import space.novium.nebula.world.entity.Player;
import space.novium.nebula.world.level.ILevelScene;
import space.novium.nebula.world.level.Level;
import space.novium.nebula.world.tiles.Tile;
import space.novium.utils.IOUtils;
import space.novium.utils.math.Vector2f;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class FunctionTestPage extends Scene implements ILevelScene {
    private final String saveLocation = "test";
    private Level level;
    private JsonObject chunk;
    private TextPart region;
    private float textTimer = 0.0f;
    private Timer timer;
    private Player player;
    private PlayerInGameMenu playerMenu;
    private List<ImagePart> images;

    public FunctionTestPage(){
        camera = new Camera();
    }

    @Override
    public void init() {
        images = new ArrayList<>();
        chunk = IOUtils.loadJson(new ResourceLocation("chunks/test"));
        region = new TextPart("", new Vector2f(-0.98f, 0.96f), 1.96f, TextAlign.LEFT, GameFonts.BASE_SMALL);
        timer = new Timer();
        level = new Level(this);
        player = level.getPlayer();
        playerMenu = new PlayerInGameMenu(player);
        setRegion(level.getRegion());
        if(chunk.has("region")){
            level.setRegion(chunk.get("region").getAsString());
        }
    }

    @Override
    public void update(float dt) {
        level.tick();
        playerMenu.tick();
        textTimer += dt;
    }

    @Override
    public void render() {
        if(textTimer > 4.0f && textTimer < 5.0f){
            region.setColor(1.0f, 1.0f, 1.0f, 5.0f - textTimer);
        }
    }

    @Override
    public void setRegion(String string) {
        region.setMessage(string);
        region.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        textTimer = 0;
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        region.setColor(0.0f, 0.0f, 0.0f, 0.0f);
                    }
                }, 5000
        );
    }

    @Override
    public void addTile(Tile tile, int z) {
        TilePos pos = tile.getPosition();
        ImagePart part = new ImagePart(new ResourceLocation(tile.getRegistryName()),
                Chunk.getNormalizedDrawLocation((float) pos.getX(), (float) pos.getY())
                , Renderer.DEFAULT_TILE_SIZE, Renderer.WORLD_START_Z + z);
        images.add(part);
    }
    
    @Override
    public Camera getCamera() {
        return camera;
    }

    @Override
    public void cleanup() {
        level.cleanup();
    }
}
