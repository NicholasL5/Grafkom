package Nicholas;

import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL30.*;




public class Grizz {
    boolean checkKaki = true;
    boolean angkatKiri= false;
    boolean hadapDepan = true;
    boolean checkKepala = true;
    boolean modeDuduk = false;
    boolean checkKepalaY = true;
    boolean checkTanganKanan = true;
    boolean checkTanganKiri = true;
    boolean hadapKiri = false;

    boolean checkKaki2 = true;
//    private Window window = new Window(1600, 1600, "hello world");
    boolean overlap = false;
    boolean grab = false;
    private int counter = 0;
    int countBezier = 0;
    int counterLean = 0;

    int tes;
    int tes2;
    public static float[][] controlBerzier1 = {
            {-0.44375f, 0.24625f, 0.55f},
            {0f, -0.3f, 0.55f},
            {0.44375f, 0.24625f, 0.55f}
    };



    public ArrayList<Object3d> clouds = new ArrayList<>();
    public ArrayList<Sphere> listSphere = new ArrayList<>();
    public ArrayList<Object3d> listBezier = new ArrayList<>();
    public ArrayList<Object3d> listBezier2 = new ArrayList<>();
    public ArrayList<Object3d> objectPointsControl = new ArrayList<>();
    float deg = 0;
    float degKepalaY = 0;
    float degKaki = 0;
    float degTanganKanan = 0;
    float degTanganKiri = 0;
    float degKaki2 = 0;
    float turn = 0.5f;
    private boolean hadapKanan = false;
    private boolean angkatTangan = false;
    private boolean clicked = false;
    private boolean sekali = false;
    private int counterJatuh = 0;

    public void init() {
        // memanggil function untuk dipakai
        GL.createCapabilities();
//        camera.setPosition(2, 0.2f, 3f);
//        camera.setRotation((float) Math.toRadians(0.0f), (float) Math.toRadians(0.0f));
//        glEnable(GL_DEPTH_TEST);
        // coding mulai dari sini

//        objectPointsControl.add(new Object3d(Arrays.asList(
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//        ), new ArrayList<>(
//
//            ),
//                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f) // ini untuk warna garisnya
//        ));


        listBezier.add(new Object3d(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f)
        ));

        listBezier2.add(new Object3d(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f)
        ));




        //badan
        listSphere.add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f(0.4823f, 0.247f, 0.0f, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 7
        ));
//        listSphere.get(0).scaleObject(1.65f, 1.65f, 0.3f);

        listSphere.get(0).scaleObject(2.2f, 2.2f, 0.3f);
        listSphere.get(0).rotateObject((float) Math.toRadians(90f), 0.0f, 1.0f, 0.0f);
        listSphere.get(0).rotateObject((float) Math.toRadians(-90f), 0.0f, 0.0f, 1.0f);
        listSphere.get(0).translateObject(0f, 0.6f, 0f);

//        //bagian bawah perut
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
        ), new Vector4f(0.4823f, 0.247f, 0.0f, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 3
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
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f(0.4823f, 0.247f, 0.0f, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
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
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f(0.4823f, 0.247f, 0.0f, 1.0f),
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 3
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
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f(0.4823f, 0.247f, 0.0f, 1.0f),
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 3
        ));
        listSphere.get(0).getChildObject().get(1).getChildObject().get(1).scaleObject(0.1f, 0.1f, 0.1f);
        listSphere.get(0).getChildObject().get(1).getChildObject().get(1).rotateObject(
                (float) Math.toRadians(-145f), 0f, 0f, 1f);
        listSphere.get(0).getChildObject().get(1).getChildObject().get(1).translateObject(-0.1f, 0.43f, 0.0f);

        //muka mulut
        listSphere.get(0).getChildObject().get(1).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f(0.4823f, 0.247f, 0.0f, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 10
        ));
        listSphere.get(0).getChildObject().get(1).getChildObject().get(2).scaleObject(0.2f, 0.13f, 0.15f);
        listSphere.get(0).getChildObject().get(1).getChildObject().get(2).translateObject(0f, 0.29f, 0.159f);

        //hidung 2
        listSphere.get(0).getChildObject().get(1).getChildObject().get(2).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f(0.0f, 0.0f, 0.0f, 0.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        listSphere.get(0).getChildObject().get(1)
                .getChildObject().get(2)
                .getChildObject().get(0).scaleObject(0.05f, 0.05f, 0.05f);
        listSphere.get(0).getChildObject().get(1)
                .getChildObject().get(2)
                .getChildObject().get(0).translateObject(0f, 0.34f, 0.2f);

        //mata kanan
        listSphere.get(0).getChildObject().get(1).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        listSphere.get(0).getChildObject().get(1).getChildObject().get(3).scaleObject(0.03f, 0.03f, 0.03f);
        listSphere.get(0).getChildObject().get(1).getChildObject().get(3).translateObject(0.05f, 0.37f, 0.15f);

        //mata kiri
        listSphere.get(0).getChildObject().get(1).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        listSphere.get(0).getChildObject().get(1).getChildObject().get(4).scaleObject(0.03f, 0.03f, 0.03f);
        listSphere.get(0).getChildObject().get(1).getChildObject().get(4).translateObject(-0.05f, 0.37f, 0.15f);


        //tangan kanan
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
        ), new Vector4f(0.4823f, 0.247f, 0.0f, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        listSphere.get(0).getChildObject().get(2).scaleObject(0.17f, 0.2f, 0.45f);
        listSphere.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(90), 1f, 0.0f, 0.0f);
        listSphere.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(17), 0f, 0.0f, 1.0f);
        // z ketukar dengan y
        listSphere.get(0).getChildObject().get(2).translateObject(0.25f, 0.03f, 0f);


        //tangan kiri
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
        ), new Vector4f(0.4823f, 0.247f, 0.0f, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        listSphere.get(0).getChildObject().get(3).scaleObject(0.17f, 0.2f, 0.45f);
        listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(90), 1f, 0.0f, 0.0f);
        listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(-17), 0f, 0.0f, 1.0f);

        listSphere.get(0).getChildObject().get(3).translateObject(-0.25f, 0.03f, 0f);

        //ekor
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
        ), new Vector4f(0.4823f, 0.247f, 0.0f, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        listSphere.get(0).getChildObject().get(4).scaleObject(0.075f, 0.075f, 0.075f);
        listSphere.get(0).getChildObject().get(4).translateObject(0f, -0.15f, -0.27f);

        //kaki kiri
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
        ), new Vector4f(0.4823f, 0.247f, 0.0f, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 1
        ));
        listSphere.get(0).getChildObject().get(5).scaleObject(0.15f, 0.15f, 0.15f);
        listSphere.get(0).getChildObject().get(5).translateObject(0f, 0.14f, -0.23f);
        listSphere.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(90f), 0.0f, 1.0f, 0.0f);
        listSphere.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(90f), 0.0f, 0.0f, 1.0f);

        //kaki
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
        ), new Vector4f(0.4823f, 0.247f, 0.0f, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 1
        ));
        listSphere.get(0).getChildObject().get(6).scaleObject(0.15f, 0.15f, 0.15f);
        listSphere.get(0).getChildObject().get(6).translateObject(0f, -0.14f, -0.23f);
        listSphere.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(90f), 0.0f, 1.0f, 0.0f);
        listSphere.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(90f), 0.0f, 0.0f, 1.0f);

        //telapak kiri
        listSphere.get(0).getChildObject().get(5).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f(0.4823f, 0.247f, 0.0f, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 3
        ));
        listSphere.get(0).getChildObject().get(5).getChildObject().get(0).scaleObject(0.15f, 0.15f, 0.15f);

        listSphere.get(0).getChildObject().get(5).getChildObject().get(0).rotateObject((float) Math.toRadians(180f), 1f, 0f, 0f);
        listSphere.get(0).getChildObject().get(5).getChildObject().get(0).translateObject(-0.141f, -0.47f, 0.05f);

        //telapak kanan
        listSphere.get(0).getChildObject().get(6).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f(0.4823f, 0.247f, 0.0f, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 3
        ));
        listSphere.get(0).getChildObject().get(6).getChildObject().get(0).scaleObject(0.15f, 0.15f, 0.15f);

        listSphere.get(0).getChildObject().get(6).getChildObject().get(0).rotateObject((float) Math.toRadians(180f), 1f, 0f, 0f);
        listSphere.get(0).getChildObject().get(6).getChildObject().get(0).translateObject(0.141f, -0.47f, 0.05f);
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
        ), new Vector4f(0.4823f, 0.247f, 0.0f, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        listSphere.get(0).getChildObject().get(7).scaleObject(0.05f, 0.05f,0.05f);
        listSphere.get(0).getChildObject().get(7).translateObject(0.2f,0.2f, 0.0f);

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
        ), new Vector4f(0.4823f, 0.247f, 0.0f, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        listSphere.get(0).getChildObject().get(8).scaleObject(0.05f, 0.05f,0.05f);
        listSphere.get(0).getChildObject().get(8).translateObject(-0.2f,0.2f, 0.0f);


        bezier(controlBerzier1, 0);
        listBezier.get(0).setFlag();
        listSphere.get(0).getChildObject().get(1).getChildObject().add(listBezier.get(0));
        listSphere.get(0).getChildObject().get(1).getChildObject().get(5).scaleObject(0.1f,0.1f,0.1f);
        listSphere.get(0).getChildObject().get(1).getChildObject().get(5).translateObject(0f, 0.28f, 0.18f);

//        listSphere.get(0).translateObject(0.8f, 0f,-1.5f);
//        listSphere.get(0).translateObject(-12f, 0f,4.5f);
//        listSphere.get(0).rotateObject((float) Math.toRadians(-90f), 0f, 1f, 0f);
//        listSphere.get(0).translateObject(4.2f, 0f,-1f);
//        this.hadapKiri = true;
//        this.hadapDepan = false;
        listSphere.get(0).translateObject(1f, 0f,-0.6f);


    }

    public void bezier(float[][] floats, int urutan) {
        int n = floats.length - 1;
        int indexBerzier = 0;
        int indexBerzier2 = 0;
        for (float t = 0; t <= 1; t += 0.001f) {
            float x = 0;
            float y = 0;
            float z = 0;
            for (int i = 0; i <= n; i++) {
                double koefisien = combination(n, i);
                float hasil = (float) (koefisien * (float) Math.pow(1 - t, n - i) * (float) Math.pow(t, i));
//                System.out.println("cek: " + objects.get(i).getCenterx() + " " + objects.get(i).getCentery());
                x += hasil * floats[i][0];
                y += hasil * floats[i][1];
                z += hasil * floats[i][2];
            }
            if (urutan == 0){
                if(tes == 0){
                    listBezier.get(0).addVertices(new Vector3f(x, y, z));
                } else if (tes == 1) {
                    listBezier.get(0).setVertices(indexBerzier, new Vector3f(x, y, z));
                    indexBerzier += 1;
                }
            } else if (urutan == 1) {
                if(tes2 == 0){

                    listBezier2.get(0).addVertices(new Vector3f(x, y, z));
                } else if (tes2 == 1) {
                    listBezier2.get(0).setVertices(indexBerzier2, new Vector3f(x, y, z));
                    indexBerzier2 += 1;
                }
            }

        }

        if (tes == 0) {
            tes = 1;
        }else if(tes2 == 0){
            tes2 = 1;
        }

    }

    public Vector3f getPosition(){
        return listSphere.get(0).updateCenterPoint();
    }

    public double combination(int n, int k) {
        if (k < 0 || k > n) {
            return 0;
        }

        double result = 1;

        for (int i = 1; i <= k; i++) {
            result *= n - k + i;
            result /= i;
        }
        return result;
    }


    public void gelengX(){
        Vector3f tmp = listSphere.get(0).getChildObject().get(1).updateCenterPoint();

        if (this.deg > -30 && !checkKepala) {
            listSphere.get(0).getChildObject().get(1).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
            listSphere.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(-0.5), 1.0f, 0.0f, 0.0f);
            listSphere.get(0).getChildObject().get(1).translateObject(tmp.x, tmp.y, tmp.z);
            this.deg--;
        }
        if (this.deg < 30 && checkKepala) {
            listSphere.get(0).getChildObject().get(1).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
            listSphere.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(0.5), 1.0f, 0.0f, 0.0f);
            listSphere.get(0).getChildObject().get(1).translateObject(tmp.x, tmp.y, tmp.z);
            this.deg++;
        }


        if (this.deg >= 30) {
            this.deg = 30;
            this.checkKepala = false;
        }
        if (this.deg <= -30) {
            this.deg = -30;
            this.checkKepala = true;
        }
    }

    public void gerakTangan(){
        Vector3f tmp = listSphere.get(0).getChildObject().get(7).updateCenterPoint();
        if (this.degTanganKanan < 180 && checkTanganKanan) {
            listSphere.get(0).getChildObject().get(2).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
            listSphere.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(0.5), 0.0f, 1.0f, 1.0f);
            listSphere.get(0).getChildObject().get(2).translateObject(tmp.x, tmp.y, tmp.z);
            this.degTanganKanan++;
        }
        if (this.degTanganKanan > -180 && !checkTanganKanan) {
            listSphere.get(0).getChildObject().get(2).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
            listSphere.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(-0.5), 0.0f, 1.0f, 1.0f);
            listSphere.get(0).getChildObject().get(2).translateObject(tmp.x, tmp.y, tmp.z);
            this.degTanganKanan--;
        }
        if (this.degTanganKanan >= 180) {
            this.degTanganKanan = 180;
            this.checkTanganKanan = false;
        }
        if (this.degTanganKanan <= -180) {
            this.degTanganKanan = -180;
            this.checkTanganKanan = true;
        }
    }

    public void TurunKepala(){
        Vector3f tmp = listSphere.get(0).getChildObject().get(1).updateCenterPoint();

        if (this.deg > -40) {
            listSphere.get(0).getChildObject().get(1).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
            listSphere.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(-0.5), 1.0f, 0.0f, 0.0f);
            listSphere.get(0).getChildObject().get(1).translateObject(tmp.x, tmp.y, tmp.z);
            this.deg--;
        }



        if (this.deg <= -40) {
            this.deg = -40;
            this.checkKepala = true;
        }
    }

    public void jalan(){
        if(hadapDepan) {

            Vector3f tmp = listSphere.get(0).getChildObject().get(6).updateCenterPoint();
            Vector3f tmp2 = listSphere.get(0).getChildObject().get(5).updateCenterPoint();
            if (this.degKaki < 90 && checkKaki) {
                listSphere.get(0).getChildObject().get(5).translateObject(tmp2.x * -1, tmp2.y * -1, tmp2.z * -1);
                listSphere.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(-0.5), 1.0f, 0.0f, 0.0f);
                listSphere.get(0).getChildObject().get(5).translateObject(tmp2.x, tmp2.y, tmp2.z);
                this.degKaki++;
            }
            if (this.degKaki > -90 && !checkKaki) {
                listSphere.get(0).getChildObject().get(5).translateObject(tmp2.x * -1, tmp2.y * -1, tmp2.z * -1);
                listSphere.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(0.5), 1.0f, 0.0f, 0.0f);
                listSphere.get(0).getChildObject().get(5).translateObject(tmp2.x, tmp2.y, tmp2.z);
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

            if (this.degKaki2 < 90 && checkKaki2) {
                listSphere.get(0).getChildObject().get(6).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
                listSphere.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(0.5), 1.0f, 0.0f, 0.0f);
                listSphere.get(0).getChildObject().get(6).translateObject(tmp.x, tmp.y, tmp.z);
                this.degKaki2++;
            }
            if (this.degKaki2 > -90 && !checkKaki2) {
                listSphere.get(0).getChildObject().get(6).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
                listSphere.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(-0.5), 1.0f, 0.0f, 0.0f);
                listSphere.get(0).getChildObject().get(6).translateObject(tmp.x, tmp.y, tmp.z);
                this.degKaki2--;
            }
            if (this.degKaki2 >= 90) {
                this.degKaki2 = 90;
                this.checkKaki2 = false;
            }
            if (this.degKaki2 <= -90) {
                this.degKaki2 = -90;
                this.checkKaki2 = true;
            }
            if (modeDuduk) {
                Vector3f tmpTanganKiri = listSphere.get(0).getChildObject().get(8).updateCenterPoint();
                if (this.degTanganKiri < 240 && checkTanganKiri) {
                    listSphere.get(0).getChildObject().get(3).translateObject(tmpTanganKiri.x * -1, tmpTanganKiri.y * -1, tmpTanganKiri.z * -1);
                    listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(-0.5), 1.0f, 0.0f, 0.0f);
                    listSphere.get(0).getChildObject().get(3).translateObject(tmpTanganKiri.x, tmpTanganKiri.y, tmpTanganKiri.z);
                    this.degTanganKiri++;
                }
                if (this.degTanganKiri > 60 && !checkTanganKiri) {
                    listSphere.get(0).getChildObject().get(3).translateObject(tmpTanganKiri.x * -1, tmpTanganKiri.y * -1, tmpTanganKiri.z * -1);
                    listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(0.5), 1.0f, 0.0f, 0.0f);
                    listSphere.get(0).getChildObject().get(3).translateObject(tmpTanganKiri.x, tmpTanganKiri.y, tmpTanganKiri.z);
                    this.degTanganKiri--;
                }
                if (this.degTanganKiri >= 240) {
                    this.degTanganKiri = 240;
                    this.checkTanganKiri = false;
                }
                if (this.degTanganKiri <= 60) {
                    this.degTanganKiri = 60;
                    this.checkTanganKiri = true;
                }

                Vector3f tmpTanganKananTanganKanan = listSphere.get(0).getChildObject().get(7).updateCenterPoint();
                if (this.degTanganKanan < 240 && checkTanganKanan) {
                    listSphere.get(0).getChildObject().get(2).translateObject(tmpTanganKananTanganKanan.x * -1, tmpTanganKananTanganKanan.y * -1, tmpTanganKananTanganKanan.z * -1);
                    listSphere.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(0.5), 1.0f, 0.0f, 0.0f);
                    listSphere.get(0).getChildObject().get(2).translateObject(tmpTanganKananTanganKanan.x, tmpTanganKananTanganKanan.y, tmpTanganKananTanganKanan.z);
                    this.degTanganKanan++;
                }
                if (this.degTanganKanan > 60 && !checkTanganKanan) {
                    listSphere.get(0).getChildObject().get(2).translateObject(tmpTanganKananTanganKanan.x * -1, tmpTanganKananTanganKanan.y * -1, tmpTanganKananTanganKanan.z * -1);
                    listSphere.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(-0.5), 1.0f, 0.0f, 0.0f);
                    listSphere.get(0).getChildObject().get(2).translateObject(tmpTanganKananTanganKanan.x, tmpTanganKananTanganKanan.y, tmpTanganKananTanganKanan.z);
                    this.degTanganKanan--;
                }
                if (this.degTanganKanan >= 240) {
                    this.degTanganKanan = 240;
                    this.checkTanganKanan = false;
                }
                if (this.degTanganKanan <= 60) {
                    this.degTanganKanan = 60;
                    this.checkTanganKanan = true;
                }
            }

            listSphere.get(0).translateObject(0f, 0f, 0.01f);
        }else if(hadapKiri){
            if(!Peter.Ice.getBerhenti()) {
                Vector3f tmp = listSphere.get(0).getChildObject().get(6).updateCenterPoint();
                Vector3f tmp2 = listSphere.get(0).getChildObject().get(5).updateCenterPoint();
                if (this.degKaki < 90 && checkKaki) {
                    listSphere.get(0).getChildObject().get(5).translateObject(tmp2.x * -1, tmp2.y * -1, tmp2.z * -1);
                    listSphere.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(-0.5), 0.0f, 0.0f, 1.0f);
                    listSphere.get(0).getChildObject().get(5).translateObject(tmp2.x, tmp2.y, tmp2.z);
                    this.degKaki++;
                }
                if (this.degKaki > -90 && !checkKaki) {
                    listSphere.get(0).getChildObject().get(5).translateObject(tmp2.x * -1, tmp2.y * -1, tmp2.z * -1);
                    listSphere.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(0.5), 0.0f, 0.0f, 1.0f);
                    listSphere.get(0).getChildObject().get(5).translateObject(tmp2.x, tmp2.y, tmp2.z);
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

                if (this.degKaki2 < 90 && checkKaki2) {
                    listSphere.get(0).getChildObject().get(6).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
                    listSphere.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(0.5), 0.0f, 0.0f, 1.0f);
                    listSphere.get(0).getChildObject().get(6).translateObject(tmp.x, tmp.y, tmp.z);
                    this.degKaki2++;
                }
                if (this.degKaki2 > -90 && !checkKaki2) {
                    listSphere.get(0).getChildObject().get(6).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
                    listSphere.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(-0.5), 0.0f, 0.0f, 1.0f);
                    listSphere.get(0).getChildObject().get(6).translateObject(tmp.x, tmp.y, tmp.z);
                    this.degKaki2--;
                }
                if (this.degKaki2 >= 90) {
                    this.degKaki2 = 90;
                    this.checkKaki2 = false;
                }
                if (this.degKaki2 <= -90) {
                    this.degKaki2 = -90;
                    this.checkKaki2 = true;
                }

                if (modeDuduk) {
                    Vector3f tmpTanganKiri = listSphere.get(0).getChildObject().get(8).updateCenterPoint();
                    if (this.degTanganKiri < 240 && checkTanganKiri) {
                        listSphere.get(0).getChildObject().get(3).translateObject(tmpTanganKiri.x * -1, tmpTanganKiri.y * -1, tmpTanganKiri.z * -1);
                        listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(-0.5), .0f, 0.0f, 1.0f);
                        listSphere.get(0).getChildObject().get(3).translateObject(tmpTanganKiri.x, tmpTanganKiri.y, tmpTanganKiri.z);
                        this.degTanganKiri++;
                    }
                    if (this.degTanganKiri > 60 && !checkTanganKiri) {
                        listSphere.get(0).getChildObject().get(3).translateObject(tmpTanganKiri.x * -1, tmpTanganKiri.y * -1, tmpTanganKiri.z * -1);
                        listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(0.5), 0.0f, 0.0f, 1.0f);
                        listSphere.get(0).getChildObject().get(3).translateObject(tmpTanganKiri.x, tmpTanganKiri.y, tmpTanganKiri.z);
                        this.degTanganKiri--;
                    }
                    if (this.degTanganKiri >= 240) {
                        this.degTanganKiri = 240;
                        this.checkTanganKiri = false;
                    }
                    if (this.degTanganKiri <= 60) {
                        this.degTanganKiri = 60;
                        this.checkTanganKiri = true;
                    }

                    Vector3f tmpTanganKananTanganKanan = listSphere.get(0).getChildObject().get(7).updateCenterPoint();
                    if (this.degTanganKanan < 240 && checkTanganKanan) {
                        listSphere.get(0).getChildObject().get(2).translateObject(tmpTanganKananTanganKanan.x * -1, tmpTanganKananTanganKanan.y * -1, tmpTanganKananTanganKanan.z * -1);
                        listSphere.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(0.5), 0.0f, 0.0f, 1.0f);
                        listSphere.get(0).getChildObject().get(2).translateObject(tmpTanganKananTanganKanan.x, tmpTanganKananTanganKanan.y, tmpTanganKananTanganKanan.z);
                        this.degTanganKanan++;
                    }
                    if (this.degTanganKanan > 60 && !checkTanganKanan) {
                        listSphere.get(0).getChildObject().get(2).translateObject(tmpTanganKananTanganKanan.x * -1, tmpTanganKananTanganKanan.y * -1, tmpTanganKananTanganKanan.z * -1);
                        listSphere.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(-0.5), 0.0f, 0.0f, 1.0f);
                        listSphere.get(0).getChildObject().get(2).translateObject(tmpTanganKananTanganKanan.x, tmpTanganKananTanganKanan.y, tmpTanganKananTanganKanan.z);
                        this.degTanganKanan--;
                    }
                    if (this.degTanganKanan >= 240) {
                        this.degTanganKanan = 240;
                        this.checkTanganKanan = false;
                    }
                    if (this.degTanganKanan <= 60) {
                        this.degTanganKanan = 60;
                        this.checkTanganKanan = true;
                    }

                }
                listSphere.get(0).translateObject(-0.0025f, 0f, 0f);
            }else{
                this.AngkatTangan();
                Vector3f tmp = listSphere.get(0).getChildObject().get(6).updateCenterPoint();
                Vector3f tmp2 = listSphere.get(0).getChildObject().get(5).updateCenterPoint();
                if(!sekali) {
                    listSphere.get(0).getChildObject().get(5).translateObject(tmp2.x * -1, tmp2.y * -1, tmp2.z * -1);
                    listSphere.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(-60), 0.0f, 0.0f, 1.0f);
                    listSphere.get(0).getChildObject().get(5).translateObject(tmp2.x, tmp2.y, tmp2.z);

                    listSphere.get(0).getChildObject().get(6).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
                    listSphere.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(-60), 0.0f, 0.0f, 1.0f);
                    listSphere.get(0).getChildObject().get(6).translateObject(tmp.x, tmp.y, tmp.z);
                    sekali = true;
                }

                if (counterJatuh < 860) {
                    Vector3f body = listSphere.get(0).updateCenterPoint();
                    listSphere.get(0).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
                    listSphere.get(0).rotateObject((float) Math.toRadians(-0.1f), 0f, 0f, 1f);
                    listSphere.get(0).translateObject(0.001f, -0.0001f, 0f);
                    listSphere.get(0).translateObject(tmp.x, tmp.y, tmp.z);
                    counterJatuh++;
                }else {
                    listSphere.get(0).getChildObject().get(1).getChildObject().get(3).setFlag();
                    listSphere.get(0).getChildObject().get(1).getChildObject().get(4).setFlag();
                    listSphere.get(0).getChildObject().get(1).getChildObject().get(3).setFlag2();
                    listSphere.get(0).getChildObject().get(1).getChildObject().get(4).setFlag2();
                }



                
            }



        }else if(hadapKanan){
            Vector3f tmp = listSphere.get(0).getChildObject().get(6).updateCenterPoint();
            Vector3f tmp2 = listSphere.get(0).getChildObject().get(5).updateCenterPoint();
            Vector3f tmpTanganKiri = listSphere.get(0).getChildObject().get(8).updateCenterPoint();
            Vector3f tmpTanganKanan = listSphere.get(0).getChildObject().get(7).updateCenterPoint();

            if (this.degKaki < 45 && checkKaki) {
                listSphere.get(0).getChildObject().get(5).translateObject(tmp2.x * -1, tmp2.y * -1, tmp2.z * -1);
                listSphere.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(-1), 0.0f, 0.0f, 1.0f);
                listSphere.get(0).getChildObject().get(5).translateObject(tmp2.x, tmp2.y, tmp2.z);
                this.degKaki++;
            }
            if (this.degKaki > -45 && !checkKaki) {
                listSphere.get(0).getChildObject().get(5).translateObject(tmp2.x * -1, tmp2.y * -1, tmp2.z * -1);
                listSphere.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(1), 0.0f, 0.0f, 1.0f);
                listSphere.get(0).getChildObject().get(5).translateObject(tmp2.x, tmp2.y, tmp2.z);
                this.degKaki--;
            }
            if (this.degKaki >= 45) {
                this.degKaki = 45;
                this.checkKaki = false;
            }
            if (this.degKaki <= -45) {
                this.degKaki = -45;
                this.checkKaki = true;
            }

            if (this.degKaki2 < 45 && checkKaki2) {
                listSphere.get(0).getChildObject().get(6).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
                listSphere.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(1), 0.0f, 0.0f, 1.0f);
                listSphere.get(0).getChildObject().get(6).translateObject(tmp.x, tmp.y, tmp.z);
                this.degKaki2++;
            }
            if (this.degKaki2 > -45 && !checkKaki2) {
                listSphere.get(0).getChildObject().get(6).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
                listSphere.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(-1), 0.0f, 0.0f, 1.0f);
                listSphere.get(0).getChildObject().get(6).translateObject(tmp.x, tmp.y, tmp.z);
                this.degKaki2--;
            }
            if (this.degKaki2 >= 45) {
                this.degKaki2 = 45;
                this.checkKaki2 = false;
            }
            if (this.degKaki2 <= -45) {
                this.degKaki2 = -45;
                this.checkKaki2 = true;
            }


//            if (this.degTanganKiri < 60 && checkTanganKiri) {
//                listSphere.get(0).getChildObject().get(3).translateObject(tmpTanganKiri.x * -1, tmpTanganKiri.y * -1, tmpTanganKiri.z * -1);
//                listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(-0.5), 0.0f, 0.0f, 1.0f);
//                listSphere.get(0).getChildObject().get(3).translateObject(tmpTanganKiri.x, tmpTanganKiri.y, tmpTanganKiri.z);
//                this.degTanganKiri++;
//            }
//            if (this.degTanganKiri > -60 && !checkTanganKiri) {
//                listSphere.get(0).getChildObject().get(3).translateObject(tmpTanganKiri.x * -1, tmpTanganKiri.y * -1, tmpTanganKiri.z * -1);
//                listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(0.5), 0.0f, 0.0f, 1.0f);
//                listSphere.get(0).getChildObject().get(3).translateObject(tmpTanganKiri.x, tmpTanganKiri.y, tmpTanganKiri.z);
//                this.degTanganKiri--;
//            }
//            if (this.degTanganKiri >= 60) {
//                this.degTanganKiri = 60;
//                this.checkTanganKiri = false;
//            }
//            if (this.degTanganKiri <= -60) {
//                this.degTanganKiri = -60;
//                this.checkTanganKiri = true;
//            }

//            if (this.degTanganKanan < 60 && checkTanganKanan) {
//                listSphere.get(0).getChildObject().get(2).translateObject(tmpTanganKanan.x * -1, tmpTanganKanan.y * -1, tmpTanganKanan.z * -1);
//                listSphere.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(0.5), .0f, 0.0f, 1.0f);
//                listSphere.get(0).getChildObject().get(2).translateObject(tmpTanganKanan.x, tmpTanganKanan.y, tmpTanganKanan.z);
//                this.degTanganKanan++;
//            }
//            if (this.degTanganKanan > -60 && !checkTanganKanan) {
//                listSphere.get(0).getChildObject().get(2).translateObject(tmpTanganKanan.x * -1, tmpTanganKanan.y * -1, tmpTanganKanan.z * -1);
//                listSphere.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(-0.5), 0.0f, 0.0f, 1.0f);
//                listSphere.get(0).getChildObject().get(2).translateObject(tmpTanganKanan.x, tmpTanganKanan.y, tmpTanganKanan.z);
//                this.degTanganKanan--;
//            }
//            if (this.degTanganKanan >= 60) {
//                this.degTanganKanan = 60;
//                this.checkTanganKanan = false;
//            }
//            if (this.degTanganKanan <= -60) {
//                this.degTanganKanan = -60;
//                this.checkTanganKanan = true;
//            }
            

            if (modeDuduk) {
                System.out.println("deg tangan kiri" + degTanganKiri);
                System.out.println("deg tangan kanan" + degTanganKanan);
                tmpTanganKiri = listSphere.get(0).getChildObject().get(8).updateCenterPoint();
                if (this.degTanganKiri < 240 && checkTanganKiri) {
                    listSphere.get(0).getChildObject().get(3).translateObject(tmpTanganKiri.x * -1, tmpTanganKiri.y * -1, tmpTanganKiri.z * -1);
                    listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(-0.5), .0f, 0.0f, 1.0f);
                    listSphere.get(0).getChildObject().get(3).translateObject(tmpTanganKiri.x, tmpTanganKiri.y, tmpTanganKiri.z);
                    this.degTanganKiri++;
                }
                if (this.degTanganKiri > 60 && !checkTanganKiri) {
                    listSphere.get(0).getChildObject().get(3).translateObject(tmpTanganKiri.x * -1, tmpTanganKiri.y * -1, tmpTanganKiri.z * -1);
                    listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(0.5), 0.0f, 0.0f, 1.0f);
                    listSphere.get(0).getChildObject().get(3).translateObject(tmpTanganKiri.x, tmpTanganKiri.y, tmpTanganKiri.z);
                    this.degTanganKiri--;
                }
                if (this.degTanganKiri >= 240) {
                    this.degTanganKiri = 240;
                    this.checkTanganKiri = false;
                }
                if (this.degTanganKiri <= 60) {
                    this.degTanganKiri = 60;
                    this.checkTanganKiri = true;
                }

                tmpTanganKanan = listSphere.get(0).getChildObject().get(7).updateCenterPoint();
                if (this.degTanganKanan < 240 && checkTanganKanan) {
                    listSphere.get(0).getChildObject().get(2).translateObject(tmpTanganKanan.x * -1, tmpTanganKanan.y * -1, tmpTanganKanan.z * -1);
                    listSphere.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(0.5), 0.0f, 0.0f, 1.0f);
                    listSphere.get(0).getChildObject().get(2).translateObject(tmpTanganKanan.x, tmpTanganKanan.y, tmpTanganKanan.z);
                    this.degTanganKanan++;
                }
                if (this.degTanganKanan > 60 && !checkTanganKanan) {
                    listSphere.get(0).getChildObject().get(2).translateObject(tmpTanganKanan.x * -1, tmpTanganKanan.y * -1, tmpTanganKanan.z * -1);
                    listSphere.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(-0.5), 0.0f, 0.0f, 1.0f);
                    listSphere.get(0).getChildObject().get(2).translateObject(tmpTanganKanan.x, tmpTanganKanan.y, tmpTanganKanan.z);
                    this.degTanganKanan--;
                }
                if (this.degTanganKanan >= 240) {
                    this.degTanganKanan = 240;
                    this.checkTanganKanan = false;
                }
                if (this.degTanganKanan <= 60) {
                    this.degTanganKanan = 60;
                    this.checkTanganKanan = true;
                }

            }
            listSphere.get(0).translateObject(0.01f, 0f, 0f);
        }

    }

    public void reset(){
        hadapKanan = false;
        hadapKiri = false;
        hadapDepan = true;
    }

    public void resetTangan(){
        Vector3f tmp = listSphere.get(0).getChildObject().get(7).updateCenterPoint();
        Vector3f tmp2 = listSphere.get(0).getChildObject().get(8).updateCenterPoint();
        if(angkatTangan){
            listSphere.get(0).getChildObject().get(2).translateObject(tmp2.x * -1, tmp2.y * -1, tmp2.z * -1);
            listSphere.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(-90), 0.0f, 0.0f, 1.0f);
            listSphere.get(0).getChildObject().get(2).translateObject(tmp2.x, tmp2.y, tmp2.z);


            listSphere.get(0).getChildObject().get(3).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
            listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(-90), 0.0f, 0.0f, 1.0f);
            listSphere.get(0).getChildObject().get(3).translateObject(tmp.x, tmp.y, tmp.z);
        }
        angkatTangan = false;
    }

    public void AngkatTangan(){
        Vector3f tmp = listSphere.get(0).getChildObject().get(7).updateCenterPoint();
        Vector3f tmp2 = listSphere.get(0).getChildObject().get(8).updateCenterPoint();
        if(!angkatTangan){
            listSphere.get(0).getChildObject().get(2).translateObject(tmp2.x * -1, tmp2.y * -1, tmp2.z * -1);
            listSphere.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(-90), 0.0f, 0.0f, 1.0f);
            listSphere.get(0).getChildObject().get(2).translateObject(tmp2.x, tmp2.y, tmp2.z);


            listSphere.get(0).getChildObject().get(3).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
            listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(-90), 0.0f, 0.0f, 1.0f);
            listSphere.get(0).getChildObject().get(3).translateObject(tmp.x, tmp.y, tmp.z);
            angkatTangan = true;
        }
    }

    public void gerakTanganKanan(){
        Vector3f tmp = listSphere.get(0).getChildObject().get(7).updateCenterPoint();
        if (this.degTanganKanan < 180 && checkTanganKanan) {
            listSphere.get(0).getChildObject().get(2).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
            listSphere.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(0.5), 1.0f, 0.0f, 0.0f);
            listSphere.get(0).getChildObject().get(2).translateObject(tmp.x, tmp.y, tmp.z);
            this.degTanganKanan++;
        }
        if (this.degTanganKanan > -180 && !checkTanganKanan) {
            listSphere.get(0).getChildObject().get(2).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
            listSphere.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(-0.5), 1.0f, 0.0f, 0.0f);
            listSphere.get(0).getChildObject().get(2).translateObject(tmp.x, tmp.y, tmp.z);
            this.degTanganKanan--;
        }
        if (this.degTanganKanan >= 180) {
            this.degTanganKanan = 180;
            this.checkTanganKanan = false;
        }
        if (this.degTanganKanan <= -180) {
            this.degTanganKanan = -180;
            this.checkTanganKanan = true;
        }
    }

    public void angkatKiri(){
        if (!angkatKiri) {
            Vector3f tmp = listSphere.get(0).getChildObject().get(8).updateCenterPoint();
            listSphere.get(0).getChildObject().get(3).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
            listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(-90), 0.0f, 0.0f, 1.0f);
            listSphere.get(0).getChildObject().get(3).translateObject(tmp.x, tmp.y, tmp.z);
            angkatKiri = true;
        }
    }
    public void turunKiri(){
        if (angkatKiri) {
            Vector3f tmp = listSphere.get(0).getChildObject().get(8).updateCenterPoint();
            listSphere.get(0).getChildObject().get(3).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
            listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(90), 0.0f, 0.0f, 1.0f);
            listSphere.get(0).getChildObject().get(3).translateObject(tmp.x, tmp.y, tmp.z);
            angkatKiri = false;
        }
    }


    public void gerakTanganKiri(){
        Vector3f parent = listSphere.get(0).updateCenterPoint();
        Vector3f tmp = listSphere.get(0).getChildObject().get(8).updateCenterPoint().sub(parent);
        if (this.degTanganKiri < 180 && checkTanganKiri) {
            listSphere.get(0).getChildObject().get(3).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
            listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(-0.5), 1.0f, 0.0f, 0.0f);
            listSphere.get(0).getChildObject().get(3).translateObject(tmp.x, tmp.y, tmp.z);
            this.degTanganKiri++;
        }
        if (this.degTanganKiri > -180 && !checkTanganKiri) {
            listSphere.get(0).getChildObject().get(3).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
            listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(0.5), 1.0f, 0.0f, 0.0f);
            listSphere.get(0).getChildObject().get(3).translateObject(tmp.x, tmp.y, tmp.z);
            this.degTanganKiri--;
        }
        if (this.degTanganKiri >= 180) {
            this.degTanganKiri = 180;
            this.checkTanganKiri = false;
        }
        if (this.degTanganKiri <= -180) {
            this.degTanganKiri = -180;
            this.checkTanganKiri = true;
        }
    }

    public void hadapKanan(){
        if(!hadapKanan) {
            Vector3f tmp = listSphere.get(0).updateCenterPoint();
            listSphere.get(0).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
            listSphere.get(0).rotateObject((float) Math.toRadians(90f), 0.0f, 1.0f, 0.0f);
            listSphere.get(0).translateObject(tmp.x, tmp.y, tmp.z);
            hadapKanan = true;
            hadapDepan = false;
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

    public void hadapKiri(){
        if(!hadapKiri) {
            Vector3f tmp = listSphere.get(0).updateCenterPoint();
            listSphere.get(0).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
            listSphere.get(0).rotateObject((float) Math.toRadians(-90f), 0.0f, 1.0f, 0.0f);
            listSphere.get(0).translateObject(tmp.x, tmp.y, tmp.z);
            hadapKiri = true;
            hadapDepan = false;
        }
    }

    public boolean modeJalan(){
        Vector3f tmp = listSphere.get(0).getChildObject().get(6).updateCenterPoint();
        Vector3f tmpTangan = listSphere.get(0).getChildObject().get(7).updateCenterPoint();
        Vector3f tmpKepala = listSphere.get(0).getChildObject().get(1).updateCenterPoint();
        Vector3f tmpTangan2 = listSphere.get(0).getChildObject().get(8).updateCenterPoint();
        if (!this.hadapKanan){
            if (this.deg < 120) {
                listSphere.get(0).getChildObject().get(1).translateObject(tmpKepala.x * -1, tmpKepala.y * -1, tmpKepala.z * -1);
                listSphere.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(-0.75), 1.0f, 0.0f, 0.0f);
                listSphere.get(0).getChildObject().get(1).translateObject(tmpKepala.x, tmpKepala.y, tmpKepala.z);
                this.deg++;
            }
            if (counterLean < 180) {
                listSphere.get(0).translateExcept(0.0f, 0.0005f, -0.001f);
                listSphere.get(0).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
                listSphere.get(0).rotateExcept((float) Math.toRadians(0.5f), 1f, 0f, 0f);
                counterLean += 1;

                listSphere.get(0).translateObject(tmp.x, tmp.y, tmp.z);
            }

            if (this.degTanganKanan < 180) {
                listSphere.get(0).getChildObject().get(2).translateObject(tmpTangan.x * -1, tmpTangan.y * -1, tmpTangan.z * -1);
                listSphere.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(0.5), -1.0f, 0.0f, 0.0f);
                listSphere.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(-0.1), 0.0f, 0.0f, 1.0f);
                listSphere.get(0).getChildObject().get(2).translateObject(tmpTangan.x, tmpTangan.y, tmpTangan.z);
                this.degTanganKanan++;
            }


            if (this.degTanganKiri < 180) {
                listSphere.get(0).getChildObject().get(3).translateObject(tmpTangan2.x * -1, tmpTangan2.y * -1, tmpTangan2.z * -1);
                listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(-0.5), 1.0f, 0.0f, 0.0f);
        //                listSphere.get(0).getChildObject().get(8).rotateObject((float) Math.toRadians(-0.5), 1.0f, 0.0f, 0.0f);
                listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(0.1), .0f, 0.0f, 1.0f);
                listSphere.get(0).getChildObject().get(3).translateObject(tmpTangan2.x, tmpTangan2.y, tmpTangan2.z);
                this.degTanganKiri++;
            }

            if (this.deg >= 120 && this.counterLean >= 180 && this.degTanganKanan >= 180 && this.degTanganKiri >= 180) {
                this.modeDuduk = true;
                return true;
            }
        }else{
            tmp = listSphere.get(0).getChildObject().get(6).updateCenterPoint();
            tmpTangan = listSphere.get(0).getChildObject().get(7).updateCenterPoint();
            tmpKepala = listSphere.get(0).getChildObject().get(1).updateCenterPoint();
            tmpTangan2 = listSphere.get(0).getChildObject().get(8).updateCenterPoint();
            if (this.deg < 120) {
                listSphere.get(0).getChildObject().get(1).translateObject(tmpKepala.x * -1, tmpKepala.y * -1, tmpKepala.z * -1);
                listSphere.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(0.75), 0.0f, 0.0f, 1.0f);
                listSphere.get(0).getChildObject().get(1).translateObject(tmpKepala.x, tmpKepala.y, tmpKepala.z);
                this.deg++;
            }
            if (counterLean < 180) {

                listSphere.get(0).translateExcept(-0.001f, 0.0005f,0f);
                listSphere.get(0).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
                listSphere.get(0).rotateExcept((float) Math.toRadians(-0.5f), 0f, 0f, 1f);
                counterLean += 1;

                listSphere.get(0).translateObject(tmp.x, tmp.y, tmp.z);
            }

            if (this.degTanganKanan < 180) {
                listSphere.get(0).getChildObject().get(2).translateObject(tmpTangan.x * -1, tmpTangan.y * -1, tmpTangan.z * -1);
                listSphere.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(0.5), 0.0f, 0.0f, 1.0f);
                listSphere.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(-0.1), 1.0f, 0.0f, 0.0f);
                listSphere.get(0).getChildObject().get(2).translateObject(tmpTangan.x, tmpTangan.y, tmpTangan.z);
                this.degTanganKanan++;
            }


            if (this.degTanganKiri < 180) {
                listSphere.get(0).getChildObject().get(3).translateObject(tmpTangan2.x * -1, tmpTangan2.y * -1, tmpTangan2.z * -1);
                listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(0.5), 0.0f, 0.0f, 1.0f);
                listSphere.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(0.1), 1.0f, 0.0f, 0.0f);
                listSphere.get(0).getChildObject().get(3).translateObject(tmpTangan2.x, tmpTangan2.y, tmpTangan2.z);
                this.degTanganKiri++;
            }

            if (this.deg >= 120 && this.counterLean >= 180 && this.degTanganKanan >= 180 && this.degTanganKiri >= 180) {
                this.modeDuduk = true;
                return true;
            }
        }
        return false;
    }

    public void rotateSemua(){
            Vector3f vec = listSphere.get(0).updateCenterPoint();
            for (int x = 0; x < listSphere.size(); x++) {
                listSphere.get(0).translateObject(
                        -vec.x,-vec.y,-vec.z
                );
                listSphere.get(0).rotateObject((float) Math.toRadians(0.5f), 0.0f, 1.0f, 0.0f);
                listSphere.get(0).translateObject(
                        vec.x,vec.y,vec.z
                );
            }

    }

    public void gelengKiriKanan(){
        Vector3f tmp = listSphere.get(0).getChildObject().get(1).updateCenterPoint();
        if (this.degKepalaY < 90 && checkKepalaY) {
            listSphere.get(0).getChildObject().get(1).translateObject(tmp.x * -1, tmp.y * -1, tmp.z * -1);
            listSphere.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(-0.5), 0.0f, 1.0f, 0.0f);
            listSphere.get(0).getChildObject().get(1).translateObject(tmp.x, tmp.y, tmp.z);
            this.degKepalaY++;
        }
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
        if (this.degKepalaY <= -90) {
            this.degKepalaY = -90;
            this.checkKepalaY = true;
        }
    }
}