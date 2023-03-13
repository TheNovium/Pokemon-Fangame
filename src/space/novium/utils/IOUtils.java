package space.novium.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import space.novium.nebula.graphics.texture.TextureAtlas;
import space.novium.nebula.core.resources.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class IOUtils {
    private static final String ROOT = "engine/src/";

    private IOUtils(){}

    public static String loadAsString(ResourceLocation loc){
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(loadFileReader(loc));
            String buffer = "";
            while((buffer = reader.readLine()) != null){
                result.append(buffer).append('\n');
            }
            reader.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }

    public static JsonObject loadJson(ResourceLocation loc){
        try {
            JsonReader reader = new JsonReader(loadFileReader(loc));
            return new Gson().fromJson(reader, JsonObject.class);
        } catch(Exception e){
            System.err.println("Unable to load json object from " + loc + ", replacing with empty json object");
            return new JsonObject();
        }
    }

    public static BufferedImage loadImage(ResourceLocation loc){
        try {
            BufferedImage img = ImageIO.read(loadFileInputStream(loc, "resources/textures", "png"));
            return img;
        } catch(Exception e){
            System.err.println("Unable to load image from " + loc + ", replacing with empty image.");
            return loadImage(TextureAtlas.NO_TEXTURE);
        }
    }

    public static Font loadFont(ResourceLocation loc){
        try {
            InputStream stream = loadFileInputStream(loc, "data/fonts", "ttf");
            return Font.createFont(Font.TRUETYPE_FONT, stream);
        } catch(Exception e){
            return new Font("Monospaced", Font.BOLD, 48);
        }
    }

    private static FileReader loadFileReader(ResourceLocation loc) throws FileNotFoundException {
        return loadFileReader(loc, "data");
    }

    private static FileReader loadFileReader(ResourceLocation loc, String sub) throws FileNotFoundException {
        return new FileReader(ROOT + loc.getNamespace() + "/" + sub + "/" + loc.getPath());
    }

    private static FileInputStream loadFileInputStream(ResourceLocation loc, String filetype) throws FileNotFoundException {
        return loadFileInputStream(loc, "data", filetype);
    }

    private static FileInputStream loadFileInputStream(ResourceLocation loc, String sub, String filetype) throws FileNotFoundException {
        return new FileInputStream(ROOT + loc.getNamespace() + "/" + sub + "/" + loc.getPath() + "." + filetype);
    }
}
