package space.novium.nebula.graphics.renderer;

import space.novium.gui.Window;
import space.novium.nebula.core.components.SpriteRenderer;
import space.novium.nebula.graphics.shader.Shader;
import space.novium.nebula.graphics.texture.TextureAtlasHandler;
import space.novium.nebula.graphics.texture.TextureAtlasType;
import space.novium.utils.TextureUtils;
import space.novium.utils.math.Vector2f;
import space.novium.utils.math.Vector4f;

import static org.lwjgl.opengl.GL30.*;

public class RenderBatch implements Comparable<RenderBatch> {
    /**
     * Vertex array information
     * ----------
     * Position         Color                           texture coordinates     texture id
     * float, float     float, float, float, float      float, float            float
     * **/
    private final int POSITION_SIZE = 2;
    private final int COLOR_SIZE = 4;
    private final int TEX_COORD_SIZE = 2;
    private final int ID_SIZE = 1;

    private final int POSITION_OFFSET = 0;
    private final int COLOR_OFFSET = POSITION_OFFSET + POSITION_SIZE * Float.BYTES;
    private final int TEX_COORD_OFFSET = COLOR_OFFSET + COLOR_SIZE * Float.BYTES;
    private final int ID_OFFSET = TEX_COORD_OFFSET + TEX_COORD_SIZE * Float.BYTES;
    private final int VERTEX_SIZE = POSITION_SIZE + COLOR_SIZE + TEX_COORD_SIZE + ID_SIZE;
    private final int VERTEX_SIZE_BYTES = VERTEX_SIZE * Float.BYTES;


    private SpriteRenderer[] sprites;
    private int numSprites;
    private boolean hasRoom;
    private float[] vertices;

    private int vao, vbo;
    private int maxBatchSize;
    private int zIndex;

    public RenderBatch(int maxBatchSize, int zIndex){
        this.maxBatchSize = maxBatchSize;
        this.sprites = new SpriteRenderer[maxBatchSize];

        vertices = new float[maxBatchSize * 4 * VERTEX_SIZE];

        this.numSprites = 0;
        this.hasRoom = true;
        this.zIndex = zIndex;
    }

    public void start(){
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, (long) vertices.length * Float.BYTES, GL_DYNAMIC_DRAW);

        int ebo = glGenBuffers();
        int[] indices = generateIndices();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);

        glVertexAttribPointer(0, POSITION_SIZE, GL_FLOAT, false, VERTEX_SIZE_BYTES, POSITION_OFFSET);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, COLOR_SIZE, GL_FLOAT, false, VERTEX_SIZE_BYTES, COLOR_OFFSET);
        glEnableVertexAttribArray(1);

        glVertexAttribPointer(2, TEX_COORD_SIZE, GL_FLOAT, false, VERTEX_SIZE_BYTES, TEX_COORD_OFFSET);
        glEnableVertexAttribArray(2);

        glVertexAttribPointer(3, ID_SIZE, GL_FLOAT, false, VERTEX_SIZE_BYTES, ID_OFFSET);
        glEnableVertexAttribArray(3);
    }

    public void render(TextureAtlasHandler handler){
        boolean rebuffData = false;
        for(int i = 0; i < numSprites; i++){
            SpriteRenderer spr = sprites[i];
            if(spr.isDirty()){
                loadVertexProperties(i);
                spr.setClean();
                rebuffData = true;
            }

            if(spr.getGameObject().getTransform().getZ() != this.zIndex){
                remove(spr);
                Renderer.get().add(spr);
                i--;
            }
        }

        if(rebuffData){
            glBindBuffer(GL_ARRAY_BUFFER, vbo);
            glBufferSubData(GL_ARRAY_BUFFER, 0, vertices);
        }

        for(TextureAtlasType type : TextureAtlasType.values()){
            glActiveTexture(type.getGlTexture());
            handler.getAtlas(type).getTexture().bind();
        }

        Shader shader = Renderer.getBoundShader();

        shader.enable();
        shader.setUniformMat4("ortho_matrix", TextureUtils.ORTHO_MATRIX);
        shader.setUniformMat4("view_matrix", TextureUtils.ORTHO_MATRIX);
        shader.setUniformIntArr("textures", new int[]{0, 1, 2, 3, 4, 5});

        glBindVertexArray(vao);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);
        glEnableVertexAttribArray(3);

        glDrawElements(GL_TRIANGLES, numSprites * 6, GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glDisableVertexAttribArray(3);
        glBindVertexArray(0);

        shader.disable();
    }

    public void addSprite(SpriteRenderer spr){
        sprites[numSprites] = spr;
        loadVertexProperties(numSprites);
        numSprites++;

        if(numSprites >= maxBatchSize){
            hasRoom = false;
        }
    }

    private void loadVertexProperties(int index){
        SpriteRenderer spr = sprites[index];
        int offset = index * 4 * VERTEX_SIZE;
        Vector4f color = spr.getColor();
        float[] texCoords = TextureUtils.getTextureDrawCoordinates(spr.getFrame());
        float xAdd = 0;
        float yAdd = 0;
        for(int i = 0; i < 4; i++){
            if(i == 1){
                yAdd = 1.0f;
            } else if(i == 2){
                xAdd = 1.0f;
            } else if(i == 3){
                yAdd = 0;
            }
            Vector2f pos = spr.getGameObject().getTransform().getPosition();
            Vector2f scale = spr.getGameObject().getTransform().getScale();
            vertices[offset] = pos.x + (scale.x * xAdd);
            vertices[offset + 1] = pos.y + (scale.y * yAdd);

            vertices[offset + 2] = color.getX();
            vertices[offset + 3] = color.getY();
            vertices[offset + 4] = color.getW();
            vertices[offset + 5] = color.getH();

            vertices[offset + 6] = texCoords[i * 2];
            vertices[offset + 7] = texCoords[(i * 2) + 1];

            vertices[offset + 8] = spr.getGlId();

            offset += VERTEX_SIZE;
        }
    }

    public boolean remove(SpriteRenderer spr){
        for(int i = 0; i < numSprites; i++){
            if(sprites[i].equals(spr)){
                for(int j = i; j < numSprites; j++){
                    sprites[j] = sprites[j + 1];
                    sprites[j].setDirty();
                }
                numSprites--;
                return true;
            }
        }
        return false;
    }

    public boolean hasRoom(){
        return numSprites < maxBatchSize;
    }

    private int[] generateIndices(){
        int[] elements = new int[6 * maxBatchSize];
        for(int i = 0; i < maxBatchSize; i++){
            //0 1 2  2 3 0    4 5 6  6 7 4
            int offset = 6 * i;
            int add = 4 * i;
            elements[offset] = add;
            elements[offset + 1] = add + 1;
            elements[offset + 2] = add + 2;
            elements[offset + 3] = add + 2;
            elements[offset + 4] = add + 3;
            elements[offset + 5] = add;
        }
        return elements;
    }

    public int getZIndex(){
        return zIndex;
    }

    public void clear(){}

    @Override
    public int compareTo(RenderBatch o){
        return o.getZIndex() - getZIndex();
    }
}
