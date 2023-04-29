package Gwan;


import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;

public class Sphere extends Circle{

    float centerz;

    float zr;

    List<Integer> index;

    int ibo;

    public Sphere(List<ShaderModuleData> shaderModuleDataList,
                  List<Vector3f> vertices,
                  Vector4f color,
                  float cx, float cy, float cz, float xr, float yr, float zr, int choice) {
        super(shaderModuleDataList, vertices, color, cx, cy,  xr, yr);
        this.centerPoint.set(2, cz);
        this.zr = zr;
        this.centerz = cz;

        if (choice == 1){
            Tube();
//            createBox();
        } else if (choice == 2) {
            createSphere();
        } else if (choice == 3) {
            createHalfElipsoid();
        } else if (choice == 4) {
            createHyperboloid1();
        } else if (choice == 5) {
            createHyperboloid2();
        } else if (choice == 6) {
            elipticCone();
        } else if (choice == 7) {
            elipticParaboloid();
        }else if (choice == 8){
            hyperboloidParaboloid();
        }else if (choice == 9){
            createElipsoid();
        }
        setupVAOVBO();

    }

    public float getCenterz() {
        return centerz;
    }

    public void createBox(){
        vertices.clear();
        Vector3f temp = new Vector3f();
        ArrayList<Vector3f> tempVertices = new ArrayList<>();
        // Tititk 1 kiri atas belakang
        temp.x = x - xr/2;

        temp.y = y + yr/2;
        temp.z = centerz - zr / 2;
        tempVertices.add(temp);
        temp = new Vector3f();

        // Titik 2 kiri bawah belakang
        temp.x = x - xr/2;
        temp.y = y - yr/2;
        temp.z = centerz - zr / 2;
        tempVertices.add(temp);
        temp = new Vector3f();

        // Titik 3 kanan bawah belakang
        temp.x = x + xr/2;
        temp.y = y - yr/2;
        temp.z = centerz - zr / 2;
        tempVertices.add(temp);
        temp = new Vector3f();

        // Titik 4 kanan atas belakang
        temp.x = x + xr/2;
        temp.y = y + yr/2;
        temp.z = centerz - zr / 2;
        tempVertices.add(temp);
        temp = new Vector3f();

        // titik 5 kiri atas depan
        temp.x = x - xr/2;
        temp.y = y + yr/2;
        temp.z = centerz + zr / 2;
        tempVertices.add(temp);
        temp = new Vector3f();

        // titik 6 kiri bawah depan
        temp.x = x - xr / 2;
        temp.y = y - yr / 2;
        temp.z = centerz + zr / 2;
        tempVertices.add(temp);
        temp = new Vector3f();

        // titik 7 kanan bawah depan
        temp.x = x + xr/2;
        temp.y = y - yr/2;
        temp.z = centerz + zr / 2;
        tempVertices.add(temp);
        temp = new Vector3f();

        // titik 8 kanan atas depan
        temp.x = x + xr/2;
        temp.y = y + yr/2;
        temp.z = centerz + zr / 2;
        tempVertices.add(temp);
        temp = new Vector3f();

        // kotak belakang
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(2));

        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(0));



        // kotak samping kiri
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(4));

        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(4));

        // kotak depan
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(4));


        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));



        // kotak samping kanan
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(2));

        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(7));

        // kotak atas
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(7));

        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(0));

        // kotak bawah
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));

        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(1));
    }

    public void createHalfElipsoid(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI/80; u+=Math.PI/60){
                float x = 0.5f * (float)(Math.cos(v) * Math.cos(u));
                float y = 0.5f * (float)(Math.cos(v) * Math.sin(u));
                float z = 0.5f * (float)(Math.sin(v));
                temp.add(new Vector3f(x,y,z));
            }
        }

        vertices = temp;
    }
    public void createElipsoid(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = 0.5f * (float)(Math.cos(v) * Math.cos(u));
                float y = 0.5f * (float)(Math.cos(v) * Math.sin(u));
                float z = 0.5f * (float)(Math.sin(v));
                temp.add(new Vector3f(x,y,z));
            }
        }

        vertices = temp;
    }

    public void createHyperboloid1(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI/2; u<= Math.PI/2; u+=Math.PI/60){
                float x = 0.1f * (float)((1/Math.cos(v)) * Math.cos(u));
                float y = 0.1f * (float)((1/Math.cos(v)) * Math.sin(u));
                float z = 0.1f * (float)(Math.tan(v));
                temp.add(new Vector3f(x,y,z));
            }
        }

        vertices = temp;
    }

    public void createHyperboloid2(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI/2; u<= Math.PI/2; u+=Math.PI/60){
                float x = 0.5f * (float)(Math.tan(v) * Math.cos(u));
                float z = 0.5f * (float)(Math.tan(v) * Math.sin(u));
                float y = 0.5f * (float)((1/Math.cos(v)));
                temp.add(new Vector3f(x,y,z));
            }

            for(double u = Math.PI/2; u<= 3*Math.PI/2; u+=Math.PI/60){
                float x = -0.5f * (float)(Math.tan(v) * Math.cos(u));
                float z = -0.5f * (float)(Math.tan(v) * Math.sin(u));
                float y = -0.5f * (float)((1/Math.cos(v)));
                temp.add(new Vector3f(x,y,z));
            }
        }

        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){

        }

        vertices = temp;
    }


    public void elipticCone(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = 0.1f * (float) v * (float) Math.cos(u);
                float y = 0.1f * (float) v * (float) Math.sin(u);
                float z = 0.1f * (float) v ;
                temp.add(new Vector3f(x,y,z));
            }
        }
        vertices = temp;
    }

    public void hyperboloidParaboloid(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = 0; v<= Math.PI; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = 0.1f * (float) v * (float) Math.tan(u);
                float y = 0.1f * (float) v * (1/(float) Math.cos(u));
                float z = (float) v * (float) v ;
                temp.add(new Vector3f(x,y,z));
            }
        }
        vertices = temp;
    }

    public void Tube(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = -Math.PI/2; v<= Math.PI/20; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = 0.5f * (float) Math.cos(u);
                float y = 0.5f * (float) Math.sin(u);
                float z = (float) v;
                temp.add(new Vector3f(x,y,z));
            }
        }


        vertices = temp;
    }

    public void elipticParaboloid(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = 1; v<= Math.PI/2.05; v+=Math.PI/720){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/720){
                float x = 0.1f * (float) v * (float) Math.cos(u);
                float y = 0.1f * (float) v * (float) Math.sin(u);
                float z = (float) v * (float) v ;
                temp.add(new Vector3f(x,y,z));
            }
        }
        vertices = temp;
    }

    public void createSphere(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();
        int stackCount = 18, sectorCount = 36;
        float x,y,z,xy,nx,ny,nz;
        float sectorStep = (float) (2* Math.PI )/ sectorCount; //sector count
        float stackStep = (float) Math.PI / stackCount; // stack count
        float sectorAngle, stackAngle;


        for(int i=0;i<=stackCount;i++){
            stackAngle = (float)Math.PI/2 - i * stackStep;
            xy = (float) (0.5f * Math.tan(stackAngle));
            z = (float) (0.5f * Math.cos(stackAngle));

            for(int j=0;j<sectorCount;j++){
                sectorAngle = j * sectorStep;
                x = (float) (xy * Math.cos(sectorAngle));
                y = (float) (xy * Math.sin(sectorAngle));
                temp.add(new Vector3f(x,y,z));
            }
        }
        vertices = temp;

        int k1, k2;
        ArrayList<Integer> temp_indices = new ArrayList<>();
        for(int i=0;i<stackCount;i++){
            k1 = i * (sectorCount + 1);
            k2 = k1 + sectorCount + 1;

            for(int j=0;j<sectorCount;++j, ++k1, ++k2){
                if(i != 0){
                    temp_indices.add(k1);
                    temp_indices.add(k2);
                    temp_indices.add(k1+1);
                }
                if(i!=(18-1)){
                    temp_indices.add(k1+1);
                    temp_indices.add(k2);
                    temp_indices.add(k2+1);
                }
            }
        }
        this.index = temp_indices;
        ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, Utils.listoInt(index), GL_STATIC_DRAW);

    }


}
