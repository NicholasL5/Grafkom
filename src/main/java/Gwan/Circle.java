package Gwan;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

public class Circle extends Object3d {

    List<Float> centerPoint = new ArrayList<>();
    float x;
    float y;
    float r;

    float xr;

    float yr;


    public Circle(
            List<ShaderModuleData> shaderModuleDataList,
            List<Vector3f> vertices,
            Vector4f color,
            float x, float y, float r) {
        super(shaderModuleDataList, vertices, color);
        createCircle(x, y, r);
        this.x = x;
        this.y = y;
        this.r = r;
        this.centerPoint.add(x);
        this.centerPoint.add(y);
        this.centerPoint.add((float) 0 );
    }

    public Circle(
            List<ShaderModuleData> shaderModuleDataList,
            List<Vector3f> vertices,
            Vector4f color,
            float x, float y, float xr, float yr) {
        super(shaderModuleDataList, vertices, color);
        this.xr = xr;
        this.yr = yr;
        createCircle(x, y, xr, yr);
        this.centerPoint.add(x);
        this.centerPoint.add(y);
        this.centerPoint.add((float) 0);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void reCreate(float x, float y){
        this.x = x;
        this.y = y;
        createCircle(x, y, r);
    }

    public void createCircle(float x, float y, float xr, float yr){
        // buat oval
        this.vertices.clear();
        for (double i = 0; i < 360;i+=0.1f){

            double calX = x + xr * Math.cos(i);
            double calY = y + yr * Math.sin(i);

            this.vertices.add(new Vector3f((float) calX, (float)calY, 0.0f));
        }
        setupVAOVBO();

    }

    public void createCircle(float x, float y, float r){
        // vertices -> clear
        this.vertices.clear();
        for (double i = 45; i < 360;i+=90f){
            //lingkaran
//            double i = 0;i < 360; i+=0.1f
//            double calX = x + r * Math.cos(i);
//            double calY = y + r * Math.sin(i);

            //segitiga
//            double i = 90; i < 361;i+=135f
//            if (i == 360){
//                i -= 45;
//            }
//
//            double calX = x + r * Math.cos(Math.toRadians(i));
//            double calY = y + r * Math.sin(Math.toRadians(i));

            // kotak
//            double i = 45; i < 360;i+=90f
            double calX = x + r * Math.cos(Math.toRadians(i));
            double calY = y + r * Math.sin(Math.toRadians(i));
            this.vertices.add(new Vector3f((float) calX, (float)calY, 0.0f));
        }
        setupVAOVBO();

    }
//    public void draw(){
//        drawSetup();
//
//        glLineWidth(10);
//        // untuk atur besar titik
//        glPointSize(0);
//        // GL_TRIANGLES
//        // GL_LINE_LOOP
//        // GL_LINE_STRIP
//        // GL_LINES
//        // GL_POINTS
//        // GL_TRIANGLE_FAN
//
//        glDrawArrays(GL_POLYGON, 0, vertices.size() );
//    }
}
