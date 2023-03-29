package space.novium.level;

import com.google.gson.JsonObject;
import space.novium.level.registration.GameFonts;
import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.nebula.core.resources.registration.GameResourceLocations;
import space.novium.nebula.graphics.Camera;
import space.novium.nebula.graphics.gui.Window;
import space.novium.nebula.graphics.gui.parts.ImagePart;
import space.novium.nebula.graphics.gui.parts.PlayerInGameMenu;
import space.novium.nebula.graphics.gui.parts.TextPart;
import space.novium.nebula.graphics.gui.parts.enums.TextAlign;
import space.novium.nebula.world.entity.Player;
import space.novium.nebula.world.level.ILevelScene;
import space.novium.nebula.world.level.Level;
import space.novium.utils.IOUtils;
import space.novium.utils.math.Vector2f;

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

    public FunctionTestPage(){
        camera = new Camera();
    }

    @Override
    public void init() {
        level = new Level(this);
        chunk = IOUtils.loadJson(new ResourceLocation("chunks/test"));
        if(chunk.has("region")){
            level.setRegion(chunk.get("region").getAsString());
        }
        region = new TextPart("", new Vector2f(-0.98f, 0.96f), 1.96f, TextAlign.LEFT, GameFonts.BASE_SMALL);
        timer = new Timer();
        player = new Player();
        playerMenu = new PlayerInGameMenu(player);
        setRegion(level.getRegion());
        new ImagePart(GameResourceLocations.PLANET);
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
    public void cleanup() {
        level.cleanup();
    }
}
