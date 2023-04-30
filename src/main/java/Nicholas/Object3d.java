package Nicholas;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Object3d extends ShaderProgram{
    List<Vector3f> vertices;
    static int counter = 0;
    List<Vector3f> verticesColor;

    int vao;
    int vbo;


    int vboColor;

    Vector4f color;

    //untuk bezier
    boolean flag = false;
    UniformsMap uniformsMap;

    Matrix4f model;
    Vector3f centerPoint;

    List<Object3d> childObject;
    private boolean flag2 = true;

    public Vector3f updateCenterPoint(){
        Vector3f centerTemp = new Vector3f();
        model.transformPosition(0.0f, 0.0f, 0.0f, centerTemp);
        this.centerPoint = centerTemp;
        return centerTemp;
    }

    public List<Object3d> getChildObject() {
        return childObject;
    }

    public void setChildObject(List<Object3d> childObject) {
        this.childObject = childObject;
    }

    public void setFlag() {
        this.flag = true;
    }
    public boolean getFlag(){
        return this.flag;
    }

    public boolean getFlag2(){
        return this.flag2;
    }

    public void setFlag2() {
        this.flag2 = !this.flag2;
    }

    public Object3d(List<ShaderModuleData> shaderModuleDataList,
                    List<Vector3f> vertices,
                    Vector4f color) {
        super(shaderModuleDataList);
        //setiap update vertice harus setup vaovbo
        this.vertices = vertices;
        setupVAOVBO();
        this.color = color;
        uniformsMap = new UniformsMap(getProgramId());
        //uniformName harus sama dengan yang ada di scene.frag
        uniformsMap.createUniform("uni_color");
        uniformsMap.createUniform("model");
        uniformsMap.createUniform(
                "projection");
        uniformsMap.createUniform(
                "view");
        // default matrix4f = identitas
        model = new Matrix4f();
        childObject = new ArrayList<>();
    }


    /**
     * pakai vertice color
     */

    public Object3d(List<ShaderModuleData> shaderModuleDataList,
                    List<Vector3f> vertices,
                    List<Vector3f> verticesColor ) {
        super(shaderModuleDataList);
        this.vertices = vertices;
        this.verticesColor = verticesColor;
        setupVAOVBOWithVerticeColor();

    }

    public void setupVAOVBO(){
        // setupvaovbo dipanggil setiap vertices di update
        // set vao
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        // set vbo
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        // mengirim vertices ke shadder
        glBufferData(GL_ARRAY_BUFFER, Utils.listoFloat(vertices), GL_STATIC_DRAW);

    }

    public void setupVAOVBOWithVerticeColor(){
        // setupvaovbo dipanggil setiap vertices di update
        // set vao
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        // set vbo
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        // mengirim vertices ke shadder
        glBufferData(GL_ARRAY_BUFFER, Utils.listoFloat(vertices), GL_STATIC_DRAW);

        vboColor = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboColor);
        // mengirim vertices ke shadder
        glBufferData(GL_ARRAY_BUFFER, Utils.listoFloat(verticesColor), GL_STATIC_DRAW);

    }

    public void drawSetup(Camera camera, Projection projection){
        bind();
        uniformsMap.setUniform("uni_color", color);
        uniformsMap.setUniform("model", model);
        uniformsMap.setUniform("view", camera.getViewMatrix());
        uniformsMap.setUniform("projection", projection.getProjMatrix());
        // Bind vbo
        glEnableVertexAttribArray(0);


        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
    }

    public void drawSetupWithVerticeColor(){
        bind();
        // Bind vbo
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        // Bind vboColor
        glEnableVertexAttribArray(1);
        glBindBuffer(GL_ARRAY_BUFFER, vboColor);
        //size sama dengan vector, kalau 3f size = 3
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);
    }

    public void draw(Camera camera, Projection projection){

        drawSetup(camera, projection);
        // untuk atur besar line
        glLineWidth(1);
        // untuk atur besar titik
        glPointSize(1);
        // GL_TRIANGLES
        // GL_LINE_LOOP
        // GL_LINE_STRIP
        // GL_LINES
        // GL_POINTS
        // GL_TRIANGLE_FAN

        glDrawArrays(GL_POLYGON, 0, vertices.size() );
        for (Object3d child:childObject){
            if (child.flag){
                child.drawLine(camera, projection);

            }else {
                child.draw(camera, projection);
//                counter+=1;
            }
        }
    }

    public void drawLine(Camera camera, Projection projection){
        drawSetup(camera, projection);
        // untuk atur besar line
        glLineWidth(5);
        // untuk atur besar titik
        glPointSize(10);
        // GL_TRIANGLES
        // GL_LINE_LOOP
        // GL_LINE_STRIP
        // GL_LINES
        // GL_POINTS
        // GL_TRIANGLE_FAN
        if(this.flag2){
            glDrawArrays(GL_LINE_STRIP, 0, vertices.size() );
        }

    }



    public void addVertices(Vector3f newVector){
        vertices.add(newVector);
        setupVAOVBO();
    }

    public void addVertices(ArrayList<Vector3f> arr){
        vertices.addAll(arr);
        setupVAOVBO();
    }

    public void setVertices(int index, Vector3f newVector){
        vertices.set(index, newVector);
        setupVAOVBO();
    }

    public Matrix4f getModel() {
        return model;
    }

    public void drawWithVerticeColor(){
        drawSetupWithVerticeColor();
        // untuk atur besar line
        glLineWidth(10);
        // untuk atur besar titik
        glPointSize(0);
        // GL_TRIANGLES
        // GL_LINE_LOOP
        // GL_LINE_STRIP
        // GL_LINES
        // GL_POINTS
        // GL_TRIANGLE_FAN

        glDrawArrays(GL_TRIANGLES, 0, vertices.size() );
    }

    public void translateObject(Float offsetX, Float offsetY, Float offsetZ){

        model = new Matrix4f().translate(offsetX, offsetY, offsetZ).mul(new Matrix4f(model));
        updateCenterPoint();
        for(Object3d child:childObject){
            child.translateObject(offsetX, offsetY, offsetZ);
        }
    }

    public void translateExcept(
            Float offsetX, Float offsetY, Float offsetZ){

        model = new Matrix4f().translate(offsetX, offsetY, offsetZ).mul(new Matrix4f(model));
        updateCenterPoint();
        for (int i = 0; i < childObject.size(); i++) {
            if (i == 5 || i ==6){
                continue;
            }else{
                childObject.get(i).translateObject(offsetX, offsetY, offsetZ);
            }

        }

    }

    /**
     * degree dijadikan radians dulu
     */
    public void rotateObject(
            Float degree, Float offsetX, Float offsetY, Float offsetZ){
        Matrix4f rotationMatrix = new Matrix4f().rotate(degree, offsetX, offsetY, offsetZ);
        model = new Matrix4f(rotationMatrix).mul(model);

        //model = new Matrix4f().rotate(degree, offsetX, offsetY, offsetZ).mul(new Matrix4f(model));
        updateCenterPoint();
        for(Object3d child:childObject){
            child.rotateObject(degree, offsetX, offsetY, offsetZ);
        }

    }
    public void rotateChild(
            Float degree, Float offsetX, Float offsetY, Float offsetZ, Object3d obj){

        Vector3f tmp = obj.updateCenterPoint();
        model = new Matrix4f().rotate(degree, offsetX, offsetY, offsetZ).mul(new Matrix4f(model));
        updateCenterPoint();
        for(Object3d child:childObject){
            child.translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
            child.rotateChild(degree, offsetX, offsetY, offsetZ, obj);
            child.translateObject(tmp.x, tmp.y, tmp.z);

        }

    }

    public void rotateExcept(
            Float degree, Float offsetX, Float offsetY, Float offsetZ){

        model = new Matrix4f().rotate(degree, offsetX, offsetY, offsetZ).mul(new Matrix4f(model));
        updateCenterPoint();
        for (int i = 0; i < childObject.size(); i++) {
            if (i == 5 || i ==6){
                continue;
            }else{
                childObject.get(i).rotateObject(degree, offsetX, offsetY, offsetZ);
            }

        }


    }



    public void rotateOnly(
            Float degree, Float offsetX, Float offsetY, Float offsetZ){

        model = new Matrix4f().rotate(degree, offsetX, offsetY, offsetZ).mul(new Matrix4f(model));

    }

    public void scaleObject(Float x, Float y, Float z){
        model = new Matrix4f().scale(x, y, z).mul(new Matrix4f(model));
        updateCenterPoint();
    }



}
