package space.novium.gui;


import space.novium.level.registration.GameFonts;
import space.novium.nebula.core.resources.RegistryObject;
import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.nebula.core.resources.registration.GameResourceLocations;
import space.novium.nebula.graphics.renderer.FontRenderer;
import space.novium.utils.math.Vector4f;

import java.awt.*;

public class Theme {
    private Vector4f primaryColor;
    private Vector4f secondaryColor;
    private Vector4f highlightColor;
    private Vector4f fontColor;
    private RegistryObject<FontRenderer> titleFont;
    private RegistryObject<FontRenderer> primaryFont;
    private RegistryObject<FontRenderer> secondaryFont;
    //It's recommended that dialog boxes are 70 x 14 pixels
    private ResourceLocation dialogBox;

    private static Theme theme;

    private Theme(){
        this.primaryColor = new Vector4f(0.0f, 0.0f, 0.0f, 1.0f);
        this.secondaryColor = new Vector4f(0.4f, 0.0f, 0.42f, 1.0f);
        this.highlightColor = new Vector4f(0.8f, 0.1f, 0.75f, 1.0f);
        this.fontColor = new Vector4f(1);
        this.titleFont = GameFonts.BASE_LARGE;
        this.primaryFont = GameFonts.BASE_NORMAL;
        this.secondaryFont = GameFonts.BASE_SMALL;
        this.dialogBox = GameResourceLocations.DEFAULT_DIALOG;
    }

    public static Theme get(){
        if(theme == null){
            theme = new Theme();
        }
        return theme;
    }

    public Vector4f getPrimaryColor() {
        return primaryColor;
    }

    public Theme setPrimaryColor(Vector4f primaryColor) {
        this.primaryColor = primaryColor;
        return this;
    }

    public Vector4f getSecondaryColor() {
        return secondaryColor;
    }

    public Theme setSecondaryColor(Vector4f secondaryColor) {
        this.secondaryColor = secondaryColor;
        return this;
    }

    public Vector4f getHighlightColor() {
        return highlightColor;
    }

    public Theme setHighlightColor(Vector4f highlightColor) {
        this.highlightColor = highlightColor;
        return this;
    }

    public Vector4f getFontColor() {
        return fontColor;
    }

    public Theme setFontColor(Vector4f fontColor) {
        this.fontColor = fontColor;
        return this;
    }

    public RegistryObject<FontRenderer> getTitleFont() {
        return titleFont;
    }

    public Theme setTitleFont(RegistryObject<FontRenderer> titleFont) {
        this.titleFont = titleFont;
        return this;
    }

    public RegistryObject<FontRenderer> getPrimaryFont() {
        return primaryFont;
    }

    public Theme setPrimaryFont(RegistryObject<FontRenderer> primaryFont) {
        this.primaryFont = primaryFont;
        return this;
    }

    public RegistryObject<FontRenderer> getSecondaryFont() {
        return secondaryFont;
    }

    public Theme setSecondaryFont(RegistryObject<FontRenderer> secondaryFont) {
        this.secondaryFont = secondaryFont;
        return this;
    }

    public ResourceLocation getDialogBox() {
        return dialogBox;
    }

    public Theme setDialogBox(ResourceLocation dialogBox) {
        this.dialogBox = dialogBox;
        return this;
    }
}
