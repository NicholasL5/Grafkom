package Peter;


import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.*;

public class Star extends Object3d {
    List<Integer> index;
    int ibo;

    public Star(List<ShaderModuleData> shaderModuleDataList,
                List<Vector3f> vertices,
                Vector4f color,
                List<Integer> index,
                float x, float y, float r) {
        super(shaderModuleDataList, vertices, color);
        this.index = index;
        ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, Utils.listoInt(index), GL_STATIC_DRAW);
        createStar(x, y, r);
    }

    public void createStar(float x, float y, float r){
        this.vertices.clear();
        for (double i = 0; i < 360;i+=72f){
            double calX = x + r * Math.cos(Math.toRadians(i));
            double calY = y + r * Math.sin(Math.toRadians(i));

            this.vertices.add(new Vector3f((float) calX, (float)calY, 0.0f));
        }
        setupVAOVBO();
    }

//    public void draw(Engine.Camera camera, Engine.Projection projection){
//        drawSetup(camera,projection);
//        glLineWidth(1);
//        // bind ibo & draw
//        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
//        glDrawElements(GL_LINE_LOOP,
//                index.size(),
//                GL_UNSIGNED_INT, 0);
//    }
}
