package space.novium.nebula.graphics.gui.parts.hud;

import space.novium.nebula.KeyInput;
import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.nebula.core.resources.registration.GameResourceLocations;
import space.novium.nebula.graphics.gui.Window;
import space.novium.nebula.graphics.gui.parts.ImagePart;
import space.novium.nebula.graphics.gui.parts.RectPart;
import space.novium.nebula.graphics.gui.parts.hud.tabs.PokemonTab;
import space.novium.nebula.graphics.gui.parts.hud.tabs.QuestTab;
import space.novium.nebula.graphics.renderer.Renderer;
import space.novium.nebula.world.entity.Player;
import space.novium.utils.ColorUtils;
import space.novium.utils.math.Vector2f;
import space.novium.utils.math.Vector4f;

import java.util.ArrayList;
import java.util.List;

public class PlayerInGameMenu {
    private List<Tab> tabs;
    private int activeIndex;

    public PlayerInGameMenu(Player player){
        tabs = new ArrayList<>();
        tabs.add(new Tab("Party", new Vector4f(0.5f, 0.1f, 0.35f, 1.0f), GameResourceLocations.POKEBALL_SYMBOL, new PokemonTab(player)));
        tabs.add(new Tab("Quest", new Vector4f(0.3f, 0.3f, 0.4f, 1.0f), GameResourceLocations.BOOK_SYMBOL, new QuestTab(player)));
        activeIndex = 0;
        for (Tab tab : tabs) {
            tab.disable();
        }
        tabs.get(activeIndex).enable();
    }

    public void tick(){
        if(KeyInput.isPressed(KeyInput.HUD_RIGHT)){
            tabs.get(activeIndex).disable();
            activeIndex = (activeIndex + 1) % tabs.size();
            tabs.get(activeIndex).enable();
        }
        if(KeyInput.isPressed(KeyInput.HUD_LEFT)){
            tabs.get(activeIndex).disable();
            activeIndex--;
            if(activeIndex < 0) activeIndex = tabs.size() - 1;
            tabs.get(activeIndex).enable();
        }
        if(KeyInput.isPressed(KeyInput.HUD_ACTION_LEFT)){
            tabs.get(activeIndex).leftAction();
        }
        if(KeyInput.isPressed(KeyInput.HUD_ACTION_RIGHT)){
            tabs.get(activeIndex).rightAction();
        }
        if(KeyInput.isPressed(KeyInput.HUD_SELECT)){
            tabs.get(activeIndex).select();
        }
    }

    private class Tab {
        private static int tabs = 0;

        private String description;
        private final Vector4f color;
        private final Vector4f iconColor;
        private final Vector4f disableColor;
        private float position;
        private ImagePart icon;
        private boolean enabled;
        private List<RectPart> hideParts;
        private List<RectPart> dimParts;
        private List<RectPart> alwaysVisible;
        private ITabAction tabAction;

        public Tab(String description, Vector4f color, ResourceLocation icon, ITabAction action){
            Window window = Window.get();
            float pixelHeight = window.getSinglePixelHeight();
            float pixelWidth = window.getSinglePixelWidth();
            float xPosition = ((float) tabs) * 19.0f * pixelWidth - 1.0f + pixelWidth * 10.0f;
            this.description = description;
            this.color = color;
            this.iconColor = Vector4f.subtract(color, new Vector4f(0.05f, 0.05f, 0.05f, 0.0f)).absolute();
            this.disableColor = Vector4f.subtract(iconColor, new Vector4f(0.1f, 0.1f, 0.1f, 0.0f)).absolute();
            this.icon = new ImagePart(icon, new Vector2f(xPosition + pixelWidth, -0.7f + pixelHeight), pixelWidth * 11.0f, Renderer.HUD_Z + 2);
            this.icon.setColor(iconColor);
            this.position = xPosition;
            this.enabled = false;
            this.tabAction = action;
            //These parts are set to invisible when the tab goes out of focus
            this.hideParts = new ArrayList<>();
            hideParts.add(
                    new RectPart(new Vector2f(-1.0f, -1.0f), new Vector2f(2.0f, 0.3f), Renderer.HUD_Z)
                            .setColor(color)
            );
            hideParts.add(
                    new RectPart(new Vector2f(-1.0f, -0.7f - pixelHeight), new Vector2f(xPosition + 1.0f - pixelWidth * 2.0f, pixelHeight), Renderer.HUD_Z + 1)
                            .setColor(ColorUtils.BLACK)
            );
            hideParts.add(
                    new RectPart(new Vector2f(xPosition + pixelWidth * 15.0f, -0.7f - pixelHeight), new Vector2f(2.0f - (xPosition + 1.0f + pixelWidth * 15.0f), pixelHeight), Renderer.HUD_Z + 1)
                            .setColor(ColorUtils.BLACK)
            );

            //Dim parts are set to a slightly darker color when the menu position is out of focus
            this.dimParts = new ArrayList<>();
            dimParts.add(
                    new RectPart(new Vector2f(xPosition, -0.7f), new Vector2f(13.0f * pixelWidth, 13.0f * pixelHeight), Renderer.HUD_Z)
            );
            dimParts.add(
                    new RectPart(new Vector2f(xPosition - pixelWidth, -0.7f), new Vector2f(pixelWidth * 15.0f, pixelHeight), Renderer.HUD_Z)
            );

            //Always visible don't change colors when going in and out of focus
            this.alwaysVisible = new ArrayList<>();
            alwaysVisible.add(
                    new RectPart(new Vector2f(xPosition, -0.7f + pixelHeight * 12.0f), new Vector2f(pixelWidth, pixelHeight), Renderer.HUD_Z + 1)
                            .setColor(ColorUtils.BLACK)
            );
            alwaysVisible.add(
                    new RectPart(new Vector2f(xPosition + pixelWidth * 12.0f, -0.7f + pixelHeight * 12.0f), new Vector2f(pixelWidth, pixelHeight), Renderer.HUD_Z + 1)
                            .setColor(ColorUtils.BLACK)
            );
            alwaysVisible.add(
                    new RectPart(new Vector2f(xPosition + pixelWidth, -0.7f + pixelHeight * 13.0f), new Vector2f(pixelWidth * 11.0f, pixelHeight), Renderer.HUD_Z + 1)
                            .setColor(ColorUtils.BLACK)
            );
            alwaysVisible.add(
                    new RectPart(new Vector2f(xPosition - pixelWidth, -0.7f +pixelHeight), new Vector2f(pixelWidth, pixelHeight * 11.0f), Renderer.HUD_Z + 1)
                            .setColor(ColorUtils.BLACK)
            );
            alwaysVisible.add(
                    new RectPart(new Vector2f(xPosition + pixelWidth * 13.0f, -0.7f + pixelHeight), new Vector2f(pixelWidth, pixelHeight * 11.0f), Renderer.HUD_Z + 1)
                            .setColor(ColorUtils.BLACK)
            );
            alwaysVisible.add(
                    new RectPart(new Vector2f(xPosition - pixelWidth * 2.0f, -0.7f), new Vector2f(pixelWidth, pixelHeight), Renderer.HUD_Z + 1)
                            .setColor(ColorUtils.BLACK)
            );
            alwaysVisible.add(
                    new RectPart(new Vector2f(xPosition + pixelWidth * 14.0f,  -0.7f), new Vector2f(pixelWidth, pixelHeight), Renderer.HUD_Z + 1)
                            .setColor(ColorUtils.BLACK)
            );
            tabs++;
        }

        public void enable(){
            this.enabled = true;
            for(RectPart hidden : hideParts){
                hidden.translate(2.0f, 0.0f);
            }
            for(RectPart dim : dimParts){
                dim.setColor(color);
            }
            icon.setColor(iconColor);
            tabAction.enable();
        }

        public void disable(){
            this.enabled = false;
            for(RectPart hidden : hideParts){
                hidden.translate(-2.0f, 0.0f);
            }
            for(RectPart dim : dimParts){
                dim.setColor(iconColor);
            }
            icon.setColor(disableColor);
            tabAction.disable();
        }

        public void rightAction(){
            tabAction.rightButton();
        }

        public void leftAction(){
            tabAction.leftButton();
        }

        public void select(){
            tabAction.select();
        }
    }
}
