package Peter;

import Nicholas.Grizz;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;


public class Ice {
    private int counter = 0;
    int tes;
    boolean hadapKanan = false;
    boolean hadapKiri = false;
    int counterLompat = 0;
    int counterLean = 0;
    boolean modeDuduk = false;
    static boolean berhenti = false;

    public static float[][] controlBerzier1 = {
            {-0.3f, 0.15f, 0.4f},
            {0f, -0.17f, 0.4f},
            {0.3f, 0.15f, 0.4f}
    };

    ArrayList<Circle> objects = new ArrayList<>();
    ArrayList<Vector3f> points = new ArrayList<>();
    public ArrayList<Sphere> listSphere = new ArrayList<>();
    public ArrayList<Object3d> listBezier = new ArrayList<>();
    ArrayList<Object3d> objectPointsControl = new ArrayList<>();
    ArrayList<Object3d> objectsRectangle = new ArrayList<>();
    ArrayList<Float> xyNow = new ArrayList<>();
    private float degKaki;
    private boolean checkKaki;
    float degKepalaY = 0;
    boolean checkKepalaY = true;
    boolean hadapDepan = true;
    private boolean checkKepala = true;
    private int deg = 0;
    private boolean dekat = false;
    
    float degPergelanganKanan = 0;
    float degPergelanganKiri = 0;
    float degTanganKanan = 0;
    float degTanganKiri = 0;
    private boolean clicked = false;
    private boolean angkatTangan = false;


//    public void run(){
//        init();
//        loop();
//        glfwTerminate();
//        glfwSetErrorCallback(null).free();
//    }

    public void init(){
//        window.init();
        // memanggil function untuk dipakai
//        GL.createCapabilities();

        //badan
        listSphere.add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f,-0.5f,0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f,0.5f, 0.0f)
                )
        ), new Vector4f(1.0f,1.0f,1.0f,1.0f), // ini untuk warna object segitiganya
                0.0f,0.0f,0.0f,
                0.5f,0.55f,0.5f, 7
        ));

        listSphere.get(0).scaleObject(1.65f, 1.65f, 0.3f);
        listSphere.get(0).rotateObject((float) Math.toRadians(90f), 0.0f, 1.0f,0.0f);
        listSphere.get(0).rotateObject((float) Math.toRadians(-90f), 0.0f, 0.0f,1.0f);
        listSphere.get(0).translateObject(0f,0.6f,0f);

        //bagian bawah perut
        listSphere.get(0).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f,-0.5f,0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f,0.5f, 0.0f)
                )
        ), new Vector4f(1.0f,1.0f,1.0f,1.0f), // ini untuk warna object segitiganya
                0.0f,0.0f,0.0f,
                0.5f,0.55f,0.5f, 3
        ));
        listSphere.get(0).getChildObject().get(0).scaleObject(0.5068f, 0.5068f, 0.5068f);
        listSphere.get(0).getChildObject().get(0).translateObject(0f, -0.1f, 0.0f);

        //kepala
        listSphere.get(0).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f,-0.5f,0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f,0.5f, 0.0f)
                )
        ), new Vector4f(1.0f,1.0f,1.0f,1.0f), // ini untuk warna object segitiganya
                0.0f,0.0f,0.0f,
                0.5f,0.55f,0.5f, 9
        ));
        listSphere.get(0).getChildObject().get(1).scaleObject(0.33f, 0.33f, 0.33f);
        listSphere.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(180f), 0f, 0f, 1f);
        listSphere.get(0).getChildObject().get(1).translateObject(0f, 0.3f, 0.0f);

        //telinga kanan
        // dianggap child oleh kepala
        listSphere.get(0).getChildObject().get(1).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f,-0.5f,0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f,0.5f, 0.0f)
                )
        ), new Vector4f(1.0f,1.0f,1.0f,1.0f),
                0.0f,0.0f,0.0f,
                0.5f,0.55f,0.5f, 3
        ));
        listSphere.get(0).getChildObject().get(1).getChildObject().get(0).scaleObject(0.1f, 0.1f, 0.1f);
        listSphere.get(0).getChildObject().get(1).getChildObject().get(0).rotateObject(
                (float) Math.toRadians(145f), 0f, 0f, 1f);
        listSphere.get(0).getChildObject().get(1).getChildObject().get(0).translateObject(0.1f, 0.43f, 0.0f);

        //telinga kiri
        listSphere.get(0).getChildObject().get(1).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f,-0.5f,0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f,0.5f, 0.0f)
                )
        ), new Vector4f(1.0f,1.0f,1.0f,1.0f),
                0.0f,0.0f,0.0f,
                0.5f,0.55f,0.5f, 3
        ));
        listSphere.get(0).getChildObject().get(1).getChildObject().get(1).scaleObject(0.1f, 0.1f, 0.1f);
        listSphere.get(0).getChildObject().get(1).getChildObject().get(1).rotateObject(
                (float) Math.toRadians(-145f), 0f, 0f, 1f);
        listSphere.get(0).getChildObject().get(1).getChildObject().get(1).translateObject(-0.1f, 0.43f, 0.0f);

        //mata
        listSphere.get(0).getChildObject().get(1).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f,-0.5f,0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f,0.5f, 0.0f)
                )
        ), new Vector4f(0f,0f,0f,1.0f), // ini untuk warna object segitiganya
                0.0f,0.0f,0.0f,
                0.5f,0.55f,0.5f, 9
        ));
        listSphere.get(0).getChildObject().get(1).getChildObject().get(2).scaleObject(0.025f,0.025f,0.025f);
        listSphere.get(0).getChildObject().get(1).getChildObject().get(2).translateObject(0.05f, 0.4f, 0.12f);

        listSphere.get(0).getChildObject().get(1).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f,-0.5f,0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f,0.5f, 0.0f)
                )
        ), new Vector4f(0f,0f,0f,1.0f), // ini untuk warna object segitiganya
                0.0f,0.0f,0.0f,
                0.5f,0.55f,0.5f, 9
        ));
        listSphere.get(0).getChildObject().get(1).getChildObject().get(3).scaleObject(0.025f,0.025f,0.025f);
        listSphere.get(0).getChildObject().get(1).getChildObject().get(3).translateObject(-0.05f, 0.4f, 0.12f);

        //hidung
        listSphere.get(0).getChildObject().get(1).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f,-0.5f,0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f,0.5f, 0.0f)
                )
        ), new Vector4f(0f,0f,0f,1.0f), // ini untuk warna object segitiganya
                0f,0f,0f,
                0.5f,0.55f,0.5f, 10
        ));
        listSphere.get(0).getChildObject().get(1).getChildObject().get(4).scaleObject(0.025f,0.025f,0.025f);
        listSphere.get(0).getChildObject().get(1).getChildObject().get(4).translateObject(0f, 0.36f, 0.15f);

        //mulut
        listBezier.add(new Object3d(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f)
        ));

        //ekor
        listSphere.get(0).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f,-0.5f,0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f,0.5f, 0.0f)
                )
        ), new Vector4f(1.0f,1.0f,1.0f,1.0f), // ini untuk warna object segitiganya
                0.0f,0.0f,0.0f,
                0.5f,0.55f,0.5f, 9
        ));
        listSphere.get(0).getChildObject().get(2).scaleObject(0.075f,0.075f,0.075f);
        listSphere.get(0).getChildObject().get(2).translateObject(0f, -0.15f, -0.27f);

        //kaki
        listSphere.get(0).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f,-0.5f,0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f,0.5f, 0.0f)
                )
        ), new Vector4f(1.0f,1.0f,1.0f,1.0f), // ini untuk warna object segitiganya
                0.0f,0.0f,0.0f,
                0.5f,0.5f,0.5f, 1
        ));
        listSphere.get(0).getChildObject().get(3).scaleObject(0.15f, 0.15f, 0.15f);
        listSphere.get(0).getChildObject().get(3).translateObject(0f, -0.14f,-0.23f);
        listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(90f), 0.0f, 1.0f,0.0f);
        listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(90f), 0.0f, 0.0f,1.0f);

        listSphere.get(0).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f,-0.5f,0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f,0.5f, 0.0f)
                )
        ), new Vector4f(1.0f,1.0f,1.0f,1.0f), // ini untuk warna object segitiganya
                0.0f,0.0f,0.0f,
                0.5f,0.5f,0.5f, 1
        ));
        listSphere.get(0).getChildObject().get(4).scaleObject(0.15f, 0.15f, 0.15f);
        listSphere.get(0).getChildObject().get(4).translateObject(0f, 0.14f,-0.23f);
        listSphere.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(90f), 0.0f, 1.0f,0.0f);
        listSphere.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(90f), 0.0f, 0.0f,1.0f);

        //telapak kiri
        listSphere.get(0).getChildObject().get(4).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f,-0.5f,0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f,0.5f, 0.0f)
                )
        ), new Vector4f(1.0f,1.0f,1.0f,1.0f), // ini untuk warna object segitiganya
                0.0f,0.0f,0.0f,
                0.5f,0.55f,0.5f, 3
        ));
        listSphere.get(0).getChildObject().get(4).getChildObject().get(0).scaleObject(0.15f,0.15f,0.15f);

        listSphere.get(0).getChildObject().get(4).getChildObject().get(0).rotateObject((float) Math.toRadians(180f), 1f, 0f, 0f);
        listSphere.get(0).getChildObject().get(4).getChildObject().get(0).translateObject(-0.141f, -0.47f, 0.05f);


        //telapak kanan
        listSphere.get(0).getChildObject().get(3).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f,-0.5f,0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f,0.5f, 0.0f)
                )
        ), new Vector4f(1.0f,1.0f,1.0f,1.0f), // ini untuk warna object segitiganya
                0.0f,0.0f,0.0f,
                0.5f,0.55f,0.5f, 3
        ));
        listSphere.get(0).getChildObject().get(3).getChildObject().get(0).scaleObject(0.15f,0.15f,0.15f);

        listSphere.get(0).getChildObject().get(3).getChildObject().get(0).rotateObject((float) Math.toRadians(180f), 1f, 0f, 0f);
        listSphere.get(0).getChildObject().get(3).getChildObject().get(0).translateObject(0.141f, -0.47f, 0.05f);

        //tangan kanan
        listSphere.get(0).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f,-0.5f,0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f,0.5f, 0.0f)
                )
        ), new Vector4f(1.0f,1.0f,1.0f,1.0f), // ini untuk warna object segitiganya
                0.0f,0.0f,0.0f,
                0.5f,0.5f,0.5f, 1
        ));
        listSphere.get(0).getChildObject().get(5).scaleObject(0.15f, 0.15f, 0.15f);

        listSphere.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(90f), 0.0f, 1.0f,0.0f);
        listSphere.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(-160f), 0.0f, 0.0f,1.0f);
        listSphere.get(0).getChildObject().get(5).translateObject(-0.33f, 0.16f,0f);


        listSphere.get(0).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f,-0.5f,0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f,0.5f, 0.0f)
                )
        ), new Vector4f(1.0f,1.0f,1.0f,1.0f), // ini untuk warna object segitiganya
                0.0f,0.0f,0.0f,
                0.5f,0.5f,0.5f, 1
        ));
        listSphere.get(0).getChildObject().get(6).scaleObject(0.15f, 0.15f, 0.15f);
        listSphere.get(0).getChildObject().get(6).translateObject(0f, -0.35f,0.075f);
        listSphere.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(90f), 0.0f, 1.0f,0.0f);
        listSphere.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(110f), 0.0f, 0.0f,1.0f);

        //telapak tangan kanan
        listSphere.get(0).getChildObject().get(6).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f,-0.5f,0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f,0.5f, 0.0f)
                )
        ), new Vector4f(1.0f,1.0f,1.0f,1.0f), // ini untuk warna object segitiganya
                0.0f,0.0f,0.0f,
                0.5f,0.55f,0.5f, 3
        ));
        listSphere.get(0).getChildObject().get(6).getChildObject().get(0).scaleObject(0.15f,0.15f,0.15f);
        listSphere.get(0).getChildObject().get(6).getChildObject().get(0).rotateObject((float) Math.toRadians(20f), 0f, 0f, 1f);
        listSphere.get(0).getChildObject().get(6).getChildObject().get(0).translateObject(0.385f, -0.035f, 0f);




        //tangan kiri
        listSphere.get(0).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f,-0.5f,0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f,0.5f, 0.0f)
                )
        ), new Vector4f(1.0f,1.0f,1.0f,1.0f), // ini untuk warna object segitiganya
                0.0f,0.0f,0.0f,
                0.5f,0.5f,0.5f, 1
        ));
        listSphere.get(0).getChildObject().get(7).scaleObject(0.15f, 0.15f, 0.15f);
        listSphere.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(90f), 0.0f, 1.0f,0.0f);
        listSphere.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(-20f), 0.0f, 0.0f,1.0f);
        listSphere.get(0).getChildObject().get(7).translateObject(0.33f, 0.16f,0f);

        listSphere.get(0).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f,-0.5f,0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f,0.5f, 0.0f)
                )
        ), new Vector4f(1.0f,1.0f,1.0f,1.0f), // ini untuk warna object segitiganya
                0.0f,0.0f,0.0f,
                0.5f,0.5f,0.5f, 1
        ));
        listSphere.get(0).getChildObject().get(8).scaleObject(0.15f, 0.15f, 0.15f);
        listSphere.get(0).getChildObject().get(8).translateObject(0f, 0.35f,0.075f);
        listSphere.get(0).getChildObject().get(8).rotateObject((float) Math.toRadians(90f), 0.0f, 1.0f,0.0f);
        listSphere.get(0).getChildObject().get(8).rotateObject((float) Math.toRadians(70f), 0.0f, 0.0f,1.0f);

        //telapak tangan kiri
        listSphere.get(0).getChildObject().get(8).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f,-0.5f,0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f,0.5f, 0.0f)
                )
        ), new Vector4f(1.0f,1.0f,1.0f,1.0f), // ini untuk warna object segitiganya
                0.0f,0.0f,0.0f,
                0.5f,0.55f,0.5f, 3
        ));
        listSphere.get(0).getChildObject().get(8).getChildObject().get(0).scaleObject(0.15f,0.15f,0.15f);
        listSphere.get(0).getChildObject().get(8).getChildObject().get(0).rotateObject((float) Math.toRadians(-20f), 0f, 0f, 1f);
        listSphere.get(0).getChildObject().get(8).getChildObject().get(0).translateObject(-0.385f, -0.035f, 0f);

//kapak
        listSphere.get(0).getChildObject().get(8).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f,-0.5f,0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f,0.5f, 0.0f)
                )
        ), new Vector4f(0.25f,0.5f,0.20f,1.0f), // ini untuk warna object segitiganya
                0.0f,0.0f,0.0f,
                0.5f,0.55f,0.5f, 1
        ));
        listSphere.get(0).getChildObject().get(8).getChildObject().get(1).scaleObject(0.05f,0.05f,0.25f);
        listSphere.get(0).getChildObject().get(8).getChildObject().get(1).rotateObject((float) Math.toRadians(180f), 0f, 1f, 0f);
        listSphere.get(0).getChildObject().get(8).getChildObject().get(1).translateObject(-0.385f, -0.035f, 0f);
//        listSphere.get(0).getChildObject().get(6).getChildObject().get(1).setFlag2();

        listSphere.get(0).getChildObject().get(8).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f,-0.5f,0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f,0.5f, 0.0f)
                )
        ), new Vector4f(0.5f,0.0f,0.0f,1.0f), // ini untuk warna object segitiganya
                0.0f,0.0f,0.0f,
                0.5f,0.55f,0.5f, 11
        ));
        listSphere.get(0).getChildObject().get(8).getChildObject().get(2).scaleObject(0.15f,0.15f,0.15f);
        listSphere.get(0).getChildObject().get(8).getChildObject().get(2).rotateObject((float) Math.toRadians(180f), 0f, 0f, 1f);
        listSphere.get(0).getChildObject().get(8).getChildObject().get(2).translateObject(-0.385f, 0.0f, 0.25f);
//        listSphere.get(0).getChildObject().get(6).getChildObject().get(2).setFlag2();


        //helper tangan kanan
        listSphere.get(0).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f(1f, 1f, 1.0f, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        listSphere.get(0).getChildObject().get(9).scaleObject(0.05f, 0.05f,0.05f);
        listSphere.get(0).getChildObject().get(9).translateObject(0.2f,0.2f, 0.0f);

        //helper tangan kiri
        listSphere.get(0).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f(1f, 1f, 1.0f, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        listSphere.get(0).getChildObject().get(10).scaleObject(0.05f, 0.05f,0.05f);
        listSphere.get(0).getChildObject().get(10).translateObject(-0.2f,0.2f, 0.0f);

        bezier(controlBerzier1, 0);
        listBezier.get(0).setFlag();
        listSphere.get(0).getChildObject().get(1).getChildObject().add(listBezier.get(0));
        listSphere.get(0).getChildObject().get(1).getChildObject().get(5).scaleObject(0.1f, 0.1f, 0.1f);
        listSphere.get(0).getChildObject().get(1).getChildObject().get(5).translateObject(0.0f, 0.31f, 0.11f);

//        listSphere.get(0).rotateObject((float) Math.toRadians(180f), 0f, 1f, 0f);
//        listSphere.get(0).translateObject(-12.5f, 0f, 6f);
//        listSphere.get(0).rotateObject((float) Math.toRadians(-90f), 0f, 1f, 0f);
        listSphere.get(0).translateObject(0.2f, 0f,-0.6f);

        this.hadapKiri = true;

    }


    public void reset(){
        hadapKanan = false;
        hadapKiri = false;
        hadapDepan = true;
    }
    public void hadapKanan() {
        if (!hadapKanan) {
            Vector3f tmp = listSphere.get(0).updateCenterPoint();
            listSphere.get(0).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
            listSphere.get(0).rotateObject((float) Math.toRadians(90f), 0.0f, 1.0f, 0.0f);
            listSphere.get(0).translateObject(tmp.x, tmp.y, tmp.z);
            hadapKanan = true;
            hadapDepan = false;
        }
    }

    public void hadapKiri() {
        if (!hadapKiri) {
            Vector3f tmp = listSphere.get(0).updateCenterPoint();
            listSphere.get(0).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
            listSphere.get(0).rotateObject((float) Math.toRadians(-90f), 0.0f, 1.0f, 0.0f);
            listSphere.get(0).translateObject(tmp.x, tmp.y, tmp.z);
            hadapKiri = true;
            hadapDepan = false;
        }
    }

    public void bezier(float[][] floats, int urutan) {
        int n = floats.length - 1;
        int indexBerzier = 0;
        for (float t = 0; t <= 1; t += 0.001f) {
            float x = 0;
            float y = 0;
            float z = 0;
            for (int i = 0; i <= n; i++) {
                double koefisien = combination(n, i);
                float hasil = (float) (koefisien * (float) Math.pow(1 - t, n - i) * (float) Math.pow(t, i));
                x += hasil * floats[i][0];
                y += hasil * floats[i][1];
                z += hasil * floats[i][2];
            }
            if (tes == 0) {
                if (urutan == 0) {
                    listBezier.get(0).addVertices(new Vector3f(x, y, z));
                }
            }
            if (tes == 1) {
                if (urutan == 0) {
                    listBezier.get(0).setVertices(indexBerzier, new Vector3f(x, y, z));
                }
                indexBerzier += 1;
            }
        }
        if (tes == 0) {
            tes = 1;
        }

    }

    public double factorial(int n){
        if (n == 1){
            return 1;
        }
        return n*factorial(n-1);
    }


    public double combination(int n, int k){
        if (k < 0 || k > n){
            return 0;
        }

        double result = 1;

        for (int i = 1; i <= k; i++) {
            result *= n - k + i;
            result /= i;
        }
        return result;
    }

    public double calcDistance(float x1, float y1, float x2, float y2){
        double hasil = Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2-y1), 2));
        return hasil;
    }

    public void jalan(){
        Vector3f tmp2 = listSphere.get(0).getChildObject().get(3).updateCenterPoint();
        
        
        if(hadapDepan) {
            if (this.degKaki < 90 && checkKaki) {
                //kaki kanan
                listSphere.get(0).getChildObject().get(3).translateObject(tmp2.x * -1, tmp2.y * -1, tmp2.z * -1);
                listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(-1), 1.0f, 0.0f, 0.0f);
                listSphere.get(0).getChildObject().get(3).translateObject(tmp2.x, tmp2.y, tmp2.z);
                //kaki kiri
                listSphere.get(0).getChildObject().get(4).translateObject(tmp2.x * -1, tmp2.y * -1, tmp2.z * -1);
                listSphere.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(1), 1.0f, 0.0f, 0.0f);
                listSphere.get(0).getChildObject().get(4).translateObject(tmp2.x, tmp2.y, tmp2.z);
                this.degKaki+=2f;
            }
            if (this.degKaki > -90 && !checkKaki) {
                //kaki kanan
                listSphere.get(0).getChildObject().get(3).translateObject(tmp2.x * -1, tmp2.y * -1, tmp2.z * -1);
                listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(1), 1.0f, 0.0f, 0.0f);
                listSphere.get(0).getChildObject().get(3).translateObject(tmp2.x, tmp2.y, tmp2.z);
                //kaki kiri
                listSphere.get(0).getChildObject().get(4).translateObject(tmp2.x * -1, tmp2.y * -1, tmp2.z * -1);
                listSphere.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(-1), 1.0f, 0.0f, 0.0f);
                listSphere.get(0).getChildObject().get(4).translateObject(tmp2.x, tmp2.y, tmp2.z);

                this.degKaki-=2f;
            }
        }else if (hadapKiri){
            if (this.degKaki < 90 && checkKaki) {
                //kaki kanan
                listSphere.get(0).getChildObject().get(3).translateObject(tmp2.x * -1, tmp2.y * -1, tmp2.z * -1);
                listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(-1), .0f, 0.0f, 1.0f);
                listSphere.get(0).getChildObject().get(3).translateObject(tmp2.x, tmp2.y, tmp2.z);
                //kaki kiri
                listSphere.get(0).getChildObject().get(4).translateObject(tmp2.x * -1, tmp2.y * -1, tmp2.z * -1);
                listSphere.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(1), 0.0f, 0.0f, 1.0f);
                listSphere.get(0).getChildObject().get(4).translateObject(tmp2.x, tmp2.y, tmp2.z);
                this.degKaki+=2f;
            }
            if (this.degKaki > -90 && !checkKaki) {
                //kaki kanan
                listSphere.get(0).getChildObject().get(3).translateObject(tmp2.x * -1, tmp2.y * -1, tmp2.z * -1);
                listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(1), 0.0f, 0.0f, 1.0f);
                listSphere.get(0).getChildObject().get(3).translateObject(tmp2.x, tmp2.y, tmp2.z);
                //kaki kiri
                listSphere.get(0).getChildObject().get(4).translateObject(tmp2.x * -1, tmp2.y * -1, tmp2.z * -1);
                listSphere.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(-1), 0.0f, 0.0f, 1.0f);
                listSphere.get(0).getChildObject().get(4).translateObject(tmp2.x, tmp2.y, tmp2.z);

                this.degKaki-=2f;
                listSphere.get(0).translateObject(-0.005f, 0f, 0f);
            }
        }else if(hadapKanan){
            if (this.degKaki < 90 && checkKaki) {
                //kaki kanan
                listSphere.get(0).getChildObject().get(3).translateObject(tmp2.x * -1, tmp2.y * -1, tmp2.z * -1);
                listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(-1), 0.0f, 0.0f, 1.0f);
                listSphere.get(0).getChildObject().get(3).translateObject(tmp2.x, tmp2.y, tmp2.z);
                //kaki kiri
                listSphere.get(0).getChildObject().get(4).translateObject(tmp2.x * -1, tmp2.y * -1, tmp2.z * -1);
                listSphere.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(1), 0.0f, 0.0f, 1.0f);
                listSphere.get(0).getChildObject().get(4).translateObject(tmp2.x, tmp2.y, tmp2.z);
                this.degKaki+=2f;
            }
            if (this.degKaki > -90 && !checkKaki) {
                //kaki kanan
                listSphere.get(0).getChildObject().get(3).translateObject(tmp2.x * -1, tmp2.y * -1, tmp2.z * -1);
                listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(1), 0.0f, 0.0f, 1.0f);
                listSphere.get(0).getChildObject().get(3).translateObject(tmp2.x, tmp2.y, tmp2.z);
                //kaki kiri
                listSphere.get(0).getChildObject().get(4).translateObject(tmp2.x * -1, tmp2.y * -1, tmp2.z * -1);
                listSphere.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(-1), 0.0f, 0.0f, 1.0f);
                listSphere.get(0).getChildObject().get(4).translateObject(tmp2.x, tmp2.y, tmp2.z);

                this.degKaki-=2f;

            }
        }
        if (this.degKaki >= 90) {
            this.degKaki = 90;
            this.checkKaki = false;
        }
        if (this.degKaki <= -90) {
            this.degKaki = -90;
            this.checkKaki = true;
        }

        if(hadapDepan){
            listSphere.get(0).translateObject(0f, 0f, 0.005f);
        } else if (hadapKiri) {
            listSphere.get(0).translateObject(-0.005f, 0f, 0f);
        } else if (hadapKanan) {
//            listSphere.get(0).translateObject(0.004f, 0f, 0f);
            listSphere.get(0).translateObject(0.004f, 0f, 0.005f);
        }

    }

    public void GelengKanan(){
        Vector3f tmp = listSphere.get(0).getChildObject().get(1).updateCenterPoint();
        if (this.degKepalaY > -90 && !checkKepalaY) {
            listSphere.get(0).getChildObject().get(1).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
            listSphere.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(0.5), 0.0f, 1.0f, 0.0f);
            listSphere.get(0).getChildObject().get(1).translateObject(tmp.x, tmp.y, tmp.z);
            this.degKepalaY--;
        }
        if (this.degKepalaY >= 90) {
            this.degKepalaY = 90;
            this.checkKepalaY = false;
        }
    }

    public void GelengKiri(){
        Vector3f tmp = listSphere.get(0).getChildObject().get(1).updateCenterPoint();
        if (this.degKepalaY < 90 && checkKepalaY) {
            listSphere.get(0).getChildObject().get(1).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
            listSphere.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(-0.5), 0.0f, 1.0f, 0.0f);
            listSphere.get(0).getChildObject().get(1).translateObject(tmp.x, tmp.y, tmp.z);
            this.degKepalaY++;
        }

        if (this.degKepalaY <= -90) {
            this.degKepalaY = -90;
            this.checkKepalaY = true;
        }
    }


    public void GelengX(){
        Vector3f tmp = listSphere.get(0).getChildObject().get(1).updateCenterPoint();
        if (this.deg < 90 && checkKepala) {
            listSphere.get(0).getChildObject().get(1).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
            listSphere.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(0.5), 1.0f, 0.0f, 0.0f);
            listSphere.get(0).getChildObject().get(1).translateObject(tmp.x, tmp.y, tmp.z);
            this.deg++;
        }

        if (this.deg >= 90) {
            this.deg = 90;
            this.checkKepala = false;
        }
    }

    public void TurunKepala(){
        Vector3f tmp = listSphere.get(0).getChildObject().get(1).updateCenterPoint();
        if (this.deg < 90 && checkKepala) {
            listSphere.get(0).getChildObject().get(1).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
            listSphere.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(-0.5), 1.0f, 0.0f, 0.0f);
            listSphere.get(0).getChildObject().get(1).translateObject(tmp.x, tmp.y, tmp.z);
            this.deg++;
        }

        if (this.deg >= 90) {
            this.deg = 90;
            this.checkKepala = false;
        }
    }

    public void modeJalan(Grizz grizz){
        double jarak = calcDistance(grizz.getPosition().x, grizz.getPosition().y,
                listSphere.get(0).updateCenterPoint().x, listSphere.get(0).updateCenterPoint().y);

        if (!dekat){
            if (jarak > 2.1) {
                this.jalan();
            }
            if (jarak <= 2.1){
                dekat = true;
            }
        }

        if (dekat){
            Vector3f tmp = listSphere.get(0).getChildObject().get(3).updateCenterPoint();
            Vector3f tmpKepala = listSphere.get(0).getChildObject().get(1).updateCenterPoint();
            Vector3f helperkanan = listSphere.get(0).getChildObject().get(9).updateCenterPoint();
            Vector3f helperkiri = listSphere.get(0).getChildObject().get(10).updateCenterPoint();
            Vector3f engselKiri = listSphere.get(0).getChildObject().get(5).updateCenterPoint();
            Vector3f engselKanan = listSphere.get(0).getChildObject().get(7).updateCenterPoint();

            if (this.deg < 120) {
                listSphere.get(0).getChildObject().get(1).translateObject(tmpKepala.x * -1, tmpKepala.y * -1, tmpKepala.z * -1);
                listSphere.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(0.75), 0.0f, 0.0f, 1.0f);
                listSphere.get(0).getChildObject().get(1).translateObject(tmpKepala.x, tmpKepala.y, tmpKepala.z);
                this.deg++;
            }

            if (counterLompat < 234) {
                listSphere.get(0).translateObject(0.005f, 0.005f, 0f);
                counterLompat++;
            }

            if (counterLean < 180) {

                listSphere.get(0).translateExcept(-0.001f, 0.0005f,0f);
                listSphere.get(0).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
                listSphere.get(0).rotateExcept((float) Math.toRadians(-0.5f), 0f, 0f, 1f);
                counterLean += 1;

                listSphere.get(0).translateObject(tmp.x, tmp.y, tmp.z);
            }

            if (this.degTanganKanan < 180) {
                listSphere.get(0).getChildObject().get(7).translateObject(helperkanan.x * -1, helperkanan.y * -1, helperkanan.z * -1);
                listSphere.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(0.5), 0.0f, 0.0f, 1.0f);
                listSphere.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(-0.1), 1.0f, 0.0f, 0.0f);
                listSphere.get(0).getChildObject().get(7).translateObject(helperkanan.x, helperkanan.y, helperkanan.z);

                listSphere.get(0).getChildObject().get(6).translateObject(helperkanan.x * -1, helperkanan.y * -1, helperkanan.z * -1);
                listSphere.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(0.5), 0.0f, 0.0f, 1.0f);
                listSphere.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(-0.1), 1.0f, 0.0f, 0.0f);
                listSphere.get(0).getChildObject().get(6).translateObject(helperkanan.x, helperkanan.y, helperkanan.z);

                this.degTanganKanan++;
            }


            if (this.degTanganKiri < 180) {
                listSphere.get(0).getChildObject().get(5).translateObject(helperkiri.x * -1, helperkiri.y * -1, helperkiri.z * -1);
                listSphere.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(0.5), 0.0f, 0.0f, 1.0f);
                listSphere.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(0.1), 1.0f, 0.0f, 0.0f);
                listSphere.get(0).getChildObject().get(5).translateObject(helperkiri.x, helperkiri.y, helperkiri.z);

                listSphere.get(0).getChildObject().get(8).translateObject(helperkiri.x * -1, helperkiri.y * -1, helperkiri.z * -1);
                listSphere.get(0).getChildObject().get(8).rotateObject((float) Math.toRadians(0.5), 0.0f, 0.0f, 1.0f);
                listSphere.get(0).getChildObject().get(8).rotateObject((float) Math.toRadians(0.1), 1.0f, 0.0f, 0.0f);
                listSphere.get(0).getChildObject().get(8).translateObject(helperkiri.x, helperkiri.y, helperkiri.z);
                this.degTanganKiri++;
            }





        }
    }

    public void kapak(){
        Vector3f tmp2 = listSphere.get(0).getChildObject().get(8).updateCenterPoint();
        if (this.degKaki < 0 && checkKaki) {
            //kapak
            listSphere.get(0).getChildObject().get(8).translateObject(tmp2.x * -1, tmp2.y * -1, tmp2.z * -1);
            listSphere.get(0).getChildObject().get(8).rotateObject((float) Math.toRadians(1), 0.0f, 0.0f, 1.0f);
            listSphere.get(0).getChildObject().get(8).translateObject(tmp2.x, tmp2.y, tmp2.z);
            this.degKaki++;
        }
        if (this.degKaki > -150 && !checkKaki) {
            //tangan kanan
            listSphere.get(0).getChildObject().get(8).translateObject(tmp2.x * -1, tmp2.y * -1, tmp2.z * -1);
            listSphere.get(0).getChildObject().get(8).rotateObject((float) Math.toRadians(-1), 0.0f, 0.0f, 1.0f);
            listSphere.get(0).getChildObject().get(8).translateObject(tmp2.x, tmp2.y, tmp2.z);
            this.degKaki--;
        }
        if (this.degKaki >= 0) {
            this.degKaki = 0;
            this.checkKaki = false;
        }
        if (this.degKaki <= -150) {
            this.degKaki = -150;
            this.checkKaki = true;
        }
    }
    
    public void lepasKapak(Grizz grizz){
        double jarak = calcDistance(grizz.listSphere.get(0).getChildObject().get(1).updateCenterPoint().x,
                grizz.listSphere.get(0).getChildObject().get(1).updateCenterPoint().y,
                listSphere.get(0).getChildObject().get(8).getChildObject().get(2).updateCenterPoint().x,
                listSphere.get(0).getChildObject().get(8).getChildObject().get(2).updateCenterPoint().y);


        if (jarak >= 0.4) {
            Vector3f gagang = listSphere.get(0).getChildObject().get(8).getChildObject().get(1).updateCenterPoint();
            listSphere.get(0).getChildObject().get(8).getChildObject().get(1).translateObject(gagang.x * -1, gagang.y * -1, gagang.z * -1);
            listSphere.get(0).getChildObject().get(8).getChildObject().get(1).rotateObject((float) Math.toRadians(25), 0.0f, 0.0f, 1.0f);
            listSphere.get(0).getChildObject().get(8).getChildObject().get(1).translateObject(gagang.x, gagang.y, gagang.z);


            listSphere.get(0).getChildObject().get(8).getChildObject().get(2).translateObject(gagang.x * -1, gagang.y * -1, gagang.z * -1);
            listSphere.get(0).getChildObject().get(8).getChildObject().get(2).rotateObject((float) Math.toRadians(25), 0.0f, 0.0f, 1.0f);
            listSphere.get(0).getChildObject().get(8).getChildObject().get(2).translateObject(gagang.x, gagang.y, gagang.z);

            listSphere.get(0).getChildObject().get(8).getChildObject().get(1).translateObject(0.02f, 0f, 0f);
            listSphere.get(0).getChildObject().get(8).getChildObject().get(2).translateObject(0.02f, 0f, 0f);
        }else {
            berhenti = true;
            listSphere.get(0).getChildObject().get(8).getChildObject().get(1).translateObject(0f, -0.001f, 0f);
        }

    }

    public void AngkatTangan(){
        Vector3f helperkanan = listSphere.get(0).getChildObject().get(9).updateCenterPoint();
        Vector3f helperkiri = listSphere.get(0).getChildObject().get(10).updateCenterPoint();
        if(!angkatTangan){
            listSphere.get(0).getChildObject().get(7).translateObject(helperkanan.x * -1, helperkanan.y * -1, helperkanan.z * -1);
            listSphere.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(180), 0.0f, 0.0f, 1.0f);
//            listSphere.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(-0.1), 1.0f, 0.0f, 0.0f);
            listSphere.get(0).getChildObject().get(7).translateObject(helperkanan.x, helperkanan.y, helperkanan.z);

            listSphere.get(0).getChildObject().get(6).translateObject(helperkanan.x * -1, helperkanan.y * -1, helperkanan.z * -1);
            listSphere.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(180), 0.0f, 0.0f, 1.0f);
//            listSphere.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(35), 1.0f, 0.0f, 0.0f);
            listSphere.get(0).getChildObject().get(6).translateObject(helperkanan.x, helperkanan.y, helperkanan.z);

            listSphere.get(0).getChildObject().get(5).translateObject(helperkiri.x * -1, helperkiri.y * -1, helperkiri.z * -1);
            listSphere.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(180), 0.0f, 0.0f, 1.0f);
//            listSphere.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(0.1), 1.0f, 0.0f, 0.0f);
            listSphere.get(0).getChildObject().get(5).translateObject(helperkiri.x, helperkiri.y, helperkiri.z);

            listSphere.get(0).getChildObject().get(8).translateObject(helperkiri.x * -1, helperkiri.y * -1, helperkiri.z * -1);
            listSphere.get(0).getChildObject().get(8).rotateObject((float) Math.toRadians(180), 0.0f, 0.0f, 1.0f);
//            listSphere.get(0).getChildObject().get(8).rotateObject((float) Math.toRadians(-35), 1.0f, 0.0f, 0.0f);
            listSphere.get(0).getChildObject().get(8).translateObject(helperkiri.x, helperkiri.y, helperkiri.z);


            angkatTangan = true;
        }
    }

    public void putar(){
        if(!clicked){
            if(hadapKanan) {
                Vector3f tmp = listSphere.get(0).updateCenterPoint();
                listSphere.get(0).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
                listSphere.get(0).rotateObject((float) Math.toRadians(180f), 0.0f, 1.0f, 0.0f);
                listSphere.get(0).translateObject(tmp.x, tmp.y, tmp.z);
                hadapKiri = true;
                hadapKanan = false;
                hadapDepan = false;
            } else if (hadapKiri) {
                Vector3f tmp = listSphere.get(0).updateCenterPoint();
                listSphere.get(0).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
                listSphere.get(0).rotateObject((float) Math.toRadians(-180f), 0.0f, 1.0f, 0.0f);
                listSphere.get(0).translateObject(tmp.x, tmp.y, tmp.z);
                hadapKiri = false;
                hadapKanan =true;
                hadapDepan = false;
            }
            clicked = true;
        }

    }

    public static boolean getBerhenti() {
        return berhenti;
    }

    public void putarY(){
        for (int x = 0; x < listSphere.size(); x++) {
            listSphere.get(x).translateObject(
                    -listSphere.get(x).getX(),
                    -listSphere.get(x).getY(),
                    -listSphere.get(x).getCenterz()
            );
            listSphere.get(x).rotateObject((float) Math.toRadians(0.5f), 0.0f, 1.0f, 0.0f);
            listSphere.get(x).translateObject(
                    listSphere.get(x).getX(),
                    listSphere.get(x).getY(),
                    listSphere.get(x).getCenterz()
            );
        }
    }
    public void putarZ(){
        listSphere.get(0).rotateObject((float) Math.toRadians(0.5f), 0.0f, 0.0f,1.0f);
    }


}