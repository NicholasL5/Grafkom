package Peter;

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

//    private Window window = new Window(1600, 1600, "hello world");
    boolean overlap = false;
    boolean grab = false;
    private int counter = 0;
    int tes;
    boolean hadapKanan = false;
    boolean hadapKiri = false;
    int counterLean = 0;
    boolean modeDuduk = false;


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
                0.5f,0.55f,0.5f, 3
        ));
        listSphere.get(0).getChildObject().get(1).scaleObject(0.33f, 0.33f, 0.33f);
        listSphere.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(180f), 0f, 0f, 1f);
        listSphere.get(0).getChildObject().get(1).translateObject(0f, 0.3f, 0.0f);

//        listSphere.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(90f), 1f, 0f, 0f);
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

        //kapak
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
        ), new Vector4f(0.25f,0.5f,0.20f,1.0f), // ini untuk warna object segitiganya
                0.0f,0.0f,0.0f,
                0.5f,0.55f,0.5f, 1
        ));
        listSphere.get(0).getChildObject().get(6).getChildObject().get(1).scaleObject(0.05f,0.05f,0.25f);
        listSphere.get(0).getChildObject().get(6).getChildObject().get(1).rotateObject((float) Math.toRadians(180f), 0f, 1f, 0f);
        listSphere.get(0).getChildObject().get(6).getChildObject().get(1).translateObject(0.385f, -0.035f, 0f);
        listSphere.get(0).getChildObject().get(6).getChildObject().get(1).setFlag2();

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
        ), new Vector4f(0.5f,0.0f,0.0f,1.0f), // ini untuk warna object segitiganya
                0.0f,0.0f,0.0f,
                0.5f,0.55f,0.5f, 11
        ));
        listSphere.get(0).getChildObject().get(6).getChildObject().get(2).scaleObject(0.15f,0.15f,0.15f);
        listSphere.get(0).getChildObject().get(6).getChildObject().get(2).rotateObject((float) Math.toRadians(180f), 0f, 0f, 1f);
        listSphere.get(0).getChildObject().get(6).getChildObject().get(2).translateObject(0.385f, 0.0f, 0.25f);
        listSphere.get(0).getChildObject().get(6).getChildObject().get(2).setFlag2();


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

        bezier(controlBerzier1, 0);
        listBezier.get(0).setFlag();
        listSphere.get(0).getChildObject().get(1).getChildObject().add(listBezier.get(0));
        listSphere.get(0).getChildObject().get(1).getChildObject().get(5).scaleObject(0.1f, 0.1f, 0.1f);
        listSphere.get(0).getChildObject().get(1).getChildObject().get(5).translateObject(0.0f, 0.31f, 0.11f);

        listSphere.get(0).translateObject(-0.8f, 0f, -2f);

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
            listSphere.get(0).translateObject(0.004f, 0f, 0f);
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

    public void modeJalan(){
        Vector3f tmp = listSphere.get(0).getChildObject().get(3).updateCenterPoint();
        if(hadapKanan){
           
            if (counterLean < 180) {

                listSphere.get(0).translateExcept(-0.001f, 0.0005f,0f);
                listSphere.get(0).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
                listSphere.get(0).rotateExcept((float) Math.toRadians(-0.5f), 0f, 0f, 1f);
                counterLean += 1;
                listSphere.get(0).translateObject(tmp.x, tmp.y, tmp.z);
            }
        }
    }

    public void kapak(){
        Vector3f tmp2 = listSphere.get(0).getChildObject().get(6).updateCenterPoint();
        if (this.degKaki < 90 && checkKaki) {
            //kapak
            listSphere.get(0).getChildObject().get(6).translateObject(tmp2.x * -1, tmp2.y * -1, tmp2.z * -1);
            listSphere.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(0.5), 1.0f, 0.0f, 0.0f);
            listSphere.get(0).getChildObject().get(6).translateObject(tmp2.x, tmp2.y, tmp2.z);
            this.degKaki++;
        }
        if (this.degKaki > -90 && !checkKaki) {
            //tangan kanan
            listSphere.get(0).getChildObject().get(6).translateObject(tmp2.x * -1, tmp2.y * -1, tmp2.z * -1);
            listSphere.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(-0.5), 1.0f, 0.0f, 0.0f);
            listSphere.get(0).getChildObject().get(6).translateObject(tmp2.x, tmp2.y, tmp2.z);
            this.degKaki--;
        }
        if (this.degKaki >= 90) {
            this.degKaki = 90;
            this.checkKaki = false;
        }
        if (this.degKaki <= -90) {
            this.degKaki = -90;
            this.checkKaki = true;
        }
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

//    public void input(){
//
//        if(window.getMouseInput().isLeftButtonPressed()){
////            System.out.println("grab" + this.grab);
////            System.out.println();
////            System.out.println("press2:" + window.getMouseInput().isLeftButtonPressed());
////            System.out.println("release2:" + window.getMouseInput().isLeftButtonReleased());
////            System.out.println();
//            this.overlap = false;
//            Vector2f pos  = window.getMouseInput().getCurrentPos();
////            System.out.println("x: " + pos.x + "y: " + pos.y);
//
//
//            // buat normalisasi supaya titik 0,0 ditengah, kalau tidak pakai ini, titik 0,0 di kiri atas
//            pos.x = (pos.x - (window.getWidth())/2.0f) / (window.getWidth()/2.0f);
//            pos.y = (pos.y - (window.getWidth())/2.0f) / (-window.getWidth()/2.0f);
////            System.out.println("x: " + pos.x + "y: " + pos.y);
//
//
//
//                //karena kecepatan
//                if ((!(pos.x > 1 || pos.x < -0.97) && !(pos.y > 0.97 || pos.y < -1))) {
////                    System.out.println("x: " + pos.x + "y: " + pos.y);
//                    if (objects.isEmpty()) {
//                        this.overlap = false;
//                    } else {
//                        if (this.grab){
//                            this.overlap = true;
//                            objects.get(this.counter).reCreate(pos.x, pos.y);
//                            objectPointsControl.get(0).setVertices(this.counter, new Vector3f(objects.get(this.counter).getX(), objects.get(this.counter).getY(), 0));
////                            bezier(objects);
//                        }else {
//                            this.counter = 0;
//                            for (Circle object : objects) {
//
//                                double res = calcDistance(object.getX(), object.getY(), pos.x, pos.y);
//                                if (res < 0.1) {
//                                    this.grab = true;
//                                }
//                                if (res < 0.3) {
//                                    this.overlap = true;
//
//                                    break;
//                                }
//
//                                this.counter += 1;
//                            }
//                        }
//                    }
//
//
//                    if (!(this.overlap)) {
//                        objectPointsControl.get(0).addVertices(new Vector3f(pos.x, pos.y, 0));
//                        objects.add(new Circle(
//                                Arrays.asList(
//                                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                                ),
//                                new ArrayList<>(),
//                                new Vector4f(1f, 0f, 0f, 1.0f),
//                                (float) pos.x, (float) pos.y, 0.1f
//
//                        ));
////                        untuk bezier
////                        bezier(objects);
//                    }
//
//
//                }
//
//
//
//
//        }
//        if (window.getMouseInput().isLeftButtonReleased()){
////            System.out.println("\nreleased");
//            this.grab = false;
//        }
////        if (window.getMouseInput().isLeftButtonPressed() && !window.getMouseInput().isLeftButtonReleased()){
////
////        }
//
//
//    }

//    public void loop(){
//        while (window.isOpen()){
//            window.update();
//            // untuk bersihin window (jadi hitam)
//            glClearColor(0.5f, 1f, 0.5f, 0.0f);
//            GL.createCapabilities();
//            // code . .
//            input();
//
//
//
//
//
////            for (Object3d object:objectPointsControl){
////                object.drawLine();
////            }
//            for (Object3d object:listBezier){
//                object.drawLine();
//            }
//
//            for (Sphere object:listSphere){
//                object.draw();
//            }
//
////            for (Object3d object:objectsRectangle){
////                object.draw();
////            }
//            //Restore state
//            glDisableVertexAttribArray(0);
//            // Poll for window events
//            // the key callback above will only be
//            // invoked during this call
//            glfwPollEvents();
//
//        }
//    }

//    public static void main(String[] args) {
//
//        new Ice().run();
//    }
}