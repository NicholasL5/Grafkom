import Gwan.Pan;
import Nicholas.*;

import Nicholas.Object3d;
import Nicholas.ShaderProgram;
import Nicholas.Sphere;
import Peter.*;

import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;

public class Main {
    Grizz grizz = new Grizz();
    Ice ice = new Ice();
    Pan pan = new Pan();
    ArrayList<Nicholas.Object3d> listObj = new ArrayList<>();
    ArrayList<Nicholas.Object3d> clouds = new ArrayList<>();

    ArrayList<Nicholas.Object3d> tree = new ArrayList<>();
    private Window window = new Window(1600, 1600, "hello world");
    Nicholas.Projection projection = new Nicholas.Projection(window.getWidth(), window.getHeight());
    Nicholas.Camera camera = new Nicholas.Camera();
    public ArrayList<Nicholas.Object3d>  listBezier = new ArrayList<>();
    private int tes;
    boolean clicked;

    public static float[][] controlBerzier2 = {
            {-0.17f, 1.15f, 0f},
            {0.15f, 1.1f, 0f},
            {-0.15f, 1.05f, 0f},
            {-0.1f, 0.9f, 0f},
    };

    public void run(){
        init();
        loop();
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void init(){
        window.init();
        GL.createCapabilities();
        glEnable(GL_DEPTH_TEST);
        grizz.init();
        ice.init();
        pan.init();

        camera.setPosition(0.5f, 1f, 3f);
        camera.setRotation((float) Math.toRadians(0.0f), (float) Math.toRadians(0.0f));

        this.listBezier.add(new Nicholas.Object3d(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f)
        ));

        //awan
        clouds.add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float) 128/255, (float) 128/255, (float) 128/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        clouds.get(0).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float) 128/255, (float) 128/255, (float) 128/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        clouds.get(0).getChildObject().get(0).scaleObject(0.7f,0.7f,0.7f);
        clouds.get(0).getChildObject().get(0).translateObject(-0.5f, -0.07f, 0f);

        clouds.get(0).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float) 128/255, (float) 128/255, (float) 128/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        clouds.get(0).getChildObject().get(1).scaleObject(0.3f,0.3f,0.3f);
        clouds.get(0).getChildObject().get(1).translateObject(-0.85f, -0.13f, 0f);

        clouds.get(0).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float) 128/255, (float) 128/255, (float) 128/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        clouds.get(0).getChildObject().get(2).scaleObject(0.8f,0.8f,0.8f);
        clouds.get(0).getChildObject().get(2).translateObject(-0.25f, 0.2f, 0f);

        clouds.get(0).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float) 128/255, (float) 128/255, (float) 128/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        clouds.get(0).getChildObject().get(3).scaleObject(0.7f,0.7f,0.7f);
        clouds.get(0).getChildObject().get(3).translateObject(0.5f, 0f, 0f);
        clouds.get(0).translateObject(-3f, 6f, -5f);


        //awan 2
        clouds.add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float) 128/255, (float) 128/255, (float) 128/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));

        clouds.get(1).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float) 128/255, (float) 128/255, (float) 128/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        clouds.get(1).getChildObject().get(0).scaleObject(0.7f,0.7f,0.7f);
        clouds.get(1).getChildObject().get(0).translateObject(-0.5f, -0.07f, 0f);

        clouds.get(1).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float) 128/255, (float) 128/255, (float) 128/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        clouds.get(1).getChildObject().get(1).scaleObject(0.3f,0.3f,0.3f);
        clouds.get(1).getChildObject().get(1).translateObject(-0.85f, -0.13f, 0f);

        clouds.get(1).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float) 128/255, (float) 128/255, (float) 128/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        clouds.get(1).getChildObject().get(2).scaleObject(0.8f,0.8f,0.8f);
        clouds.get(1).getChildObject().get(2).translateObject(-0.25f, 0.2f, 0f);

        clouds.get(1).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float) 128/255, (float) 128/255, (float) 128/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        clouds.get(1).getChildObject().get(3).scaleObject(0.7f,0.7f,0.7f);
        clouds.get(1).getChildObject().get(3).translateObject(0.5f, 0f, 0f);

        clouds.get(1).translateObject(4f, 5f, -5f);

        //awan3
        clouds.add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float) 128/255, (float) 128/255, (float) 128/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));

        clouds.get(2).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float) 128/255, (float) 128/255, (float) 128/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        clouds.get(2).getChildObject().get(0).scaleObject(0.7f,0.7f,0.7f);
        clouds.get(2).getChildObject().get(0).translateObject(-0.5f, -0.07f, 0f);

        clouds.get(2).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float) 128/255, (float) 128/255, (float) 128/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        clouds.get(2).getChildObject().get(1).scaleObject(0.3f,0.3f,0.3f);
        clouds.get(2).getChildObject().get(1).translateObject(-0.85f, -0.13f, 0f);

        clouds.get(2).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float) 128/255, (float) 128/255, (float) 128/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        clouds.get(2).getChildObject().get(2).scaleObject(0.8f,0.8f,0.8f);
        clouds.get(2).getChildObject().get(2).translateObject(-0.25f, 0.2f, 0f);

        clouds.get(2).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float) 128/255, (float) 128/255, (float) 128/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        clouds.get(2).getChildObject().get(3).scaleObject(0.7f,0.7f,0.7f);
        clouds.get(2).getChildObject().get(3).translateObject(0.5f, 0f, 0f);

        clouds.get(2).translateObject(0f, 4f, -5f);

        //awan4
        clouds.add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float) 128/255, (float) 128/255, (float) 128/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));

        clouds.get(3).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float) 128/255, (float) 128/255, (float) 128/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        clouds.get(3).getChildObject().get(0).scaleObject(0.7f,0.7f,0.7f);
        clouds.get(3).getChildObject().get(0).translateObject(-0.5f, -0.07f, 0f);

        clouds.get(3).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float) 128/255, (float) 128/255, (float) 128/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        clouds.get(3).getChildObject().get(1).scaleObject(0.3f,0.3f,0.3f);
        clouds.get(3).getChildObject().get(1).translateObject(-0.85f, -0.13f, 0f);

        clouds.get(3).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float) 128/255, (float) 128/255, (float) 128/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        clouds.get(3).getChildObject().get(2).scaleObject(0.8f,0.8f,0.8f);
        clouds.get(3).getChildObject().get(2).translateObject(-0.25f, 0.2f, 0f);

        clouds.get(3).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float) 128/255, (float) 128/255, (float) 128/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.55f, 0.5f, 9
        ));
        clouds.get(3).getChildObject().get(3).scaleObject(0.7f,0.7f,0.7f);
        clouds.get(3).getChildObject().get(3).translateObject(0.5f, 0f, 0f);

        clouds.get(3).translateObject(-13f, 7f, -10f);

        //lantai
        listObj.add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)16/255, (float)163/255, (float)50/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                100f, 0.05f, 100f, 11
        ));

        listObj.get(0).translateObject(0f, -0.5f, 0f);

        //hutan
        listObj.add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)96/255, (float)22/255, (float)22/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 1
        ));
        listObj.get(1).scaleObject(0.5f, 0.5f,0.5f);
        listObj.get(1).rotateObject((float)Math.toRadians(-90f), 1f, 0f,0f);

        listObj.get(1).getChildObject().add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)24/255, (float)114/255, (float)8/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 9
        ));

        listObj.get(1).translateObject(-2f, 0.4f, -3f);

        //rumah
        //gedung
        listObj.add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)230/255, (float)217/255, (float)185/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 11
        ));

        listObj.get(2).scaleObject(7f, 6f, 5f);
        listObj.get(2).rotateObject((float)Math.toRadians(180f), 0f, 1f,0f);

        //pintu
        listObj.get(2).getChildObject().add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)57/255, (float)160/255, (float)191/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 11
        ));

        listObj.get(2).getChildObject().get(0).scaleObject(1.5f, 2.5f, 0.25f);
        listObj.get(2).getChildObject().get(0).rotateObject((float)Math.toRadians(180f), 0f, 1f,0f);
        listObj.get(2).getChildObject().get(0).translateObject(0f, 0f, 1.2f);

        //knop pintu
        listObj.get(2).getChildObject().add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)0/255, (float)0/255, (float)0/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 9
        ));

        listObj.get(2).getChildObject().get(1).scaleObject(0.05f, 0.05f, 0.05f);
        listObj.get(2).getChildObject().get(1).translateObject(-0.25f, 0f, 1.3f);

        listObj.get(2).translateObject(0f,0f,-5f);

        //store
        //gedung1
        listObj.add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)149/255, (float)93/255, (float)78/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 11
        ));

        listObj.get(3).scaleObject(5f, 14f, 7f);
        listObj.get(3).rotateObject((float)Math.toRadians(180f), 0f, 1f,0f);

        //kaca
        listObj.get(3).getChildObject().add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)145/255, (float)184/255, (float)219/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 11
        ));

        listObj.get(3).getChildObject().get(0).scaleObject(1.5f, 2.5f, 0.1f);
        listObj.get(3).getChildObject().get(0).rotateObject((float)Math.toRadians(270f), 0f, 1f,0f);
        listObj.get(3).getChildObject().get(0).translateObject(1.23f, 0f, 0.4f);

        listObj.get(3).getChildObject().add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)145/255, (float)184/255, (float)219/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 11
        ));

        listObj.get(3).getChildObject().get(1).scaleObject(1.5f, 2.5f, 0.1f);
        listObj.get(3).getChildObject().get(1).rotateObject((float)Math.toRadians(270f), 0f, 1f,0f);
        listObj.get(3).getChildObject().get(1).translateObject(1.23f, 0f, -0.4f);

        listObj.get(3).translateObject(-15f,0f,5f);

        //gedung2
        listObj.add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)244/255, (float)184/255, (float)0/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 11
        ));

        listObj.get(4).scaleObject(5f, 9f, 7f);
        listObj.get(4).rotateObject((float)Math.toRadians(180f), 0f, 1f,0f);

        //kaca
        listObj.get(4).getChildObject().add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)145/255, (float)184/255, (float)219/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 11
        ));

        listObj.get(4).getChildObject().get(0).scaleObject(1.5f, 2.5f, 0.1f);
        listObj.get(4).getChildObject().get(0).rotateObject((float)Math.toRadians(270f), 0f, 1f,0f);
        listObj.get(4).getChildObject().get(0).translateObject(1.23f, 0f, 0.4f);

        listObj.get(4).getChildObject().add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)145/255, (float)184/255, (float)219/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 11
        ));

        listObj.get(4).getChildObject().get(1).scaleObject(1.5f, 2.5f, 0.1f);
        listObj.get(4).getChildObject().get(1).rotateObject((float)Math.toRadians(270f), 0f, 1f,0f);
        listObj.get(4).getChildObject().get(1).translateObject(1.23f, 0f, -0.4f);

        listObj.get(4).translateObject(-15f,0f,1.5f);

        //gedung3
        listObj.add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)106/255, (float)125/255, (float)142/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 11
        ));

        listObj.get(5).scaleObject(5f, 10f, 7f);
        listObj.get(5).rotateObject((float)Math.toRadians(180f), 0f, 1f,0f);

        //kaca
        listObj.get(5).getChildObject().add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)145/255, (float)184/255, (float)219/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 11
        ));

        listObj.get(5).getChildObject().get(0).scaleObject(1.5f, 2.5f, 0.1f);
        listObj.get(5).getChildObject().get(0).rotateObject((float)Math.toRadians(270f), 0f, 1f,0f);
        listObj.get(5).getChildObject().get(0).translateObject(1.23f, 0f, 0.4f);

        listObj.get(5).getChildObject().add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)145/255, (float)184/255, (float)219/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 11
        ));

        listObj.get(5).getChildObject().get(1).scaleObject(1.5f, 2.5f, 0.1f);
        listObj.get(5).getChildObject().get(1).rotateObject((float)Math.toRadians(270f), 0f, 1f,0f);
        listObj.get(5).getChildObject().get(1).translateObject(1.23f, 0f, -0.4f);

        listObj.get(5).translateObject(-15f,0f,8.5f);

        //jalan
        listObj.add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)71/255, (float)72/255, (float)76/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 11
        ));

        listObj.get(6).scaleObject(5f, 0.2f, 21f);
        listObj.get(6).rotateObject((float)Math.toRadians(180f), 0f, 1f,0f);

        listObj.get(6).translateObject(-12.5f,-0.5f,5f);

        //pohon1
        tree.add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)96/255, (float)22/255, (float)22/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 1
        ));
        tree.get(0).scaleObject(0.5f, 0.5f,0.5f);
        tree.get(0).rotateObject((float)Math.toRadians(-90f), 1f, 0f,0f);

        tree.get(0).getChildObject().add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)24/255, (float)114/255, (float)8/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 12
        ));

        tree.get(0).getChildObject().get(0).rotateObject((float)Math.toRadians(-90f), 1f, 0f,0f);

        tree.get(0).getChildObject().add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)24/255, (float)114/255, (float)8/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 12
        ));

        tree.get(0).getChildObject().get(1).rotateObject((float)Math.toRadians(-90f), 1f, 0f,0f);
        tree.get(0).getChildObject().get(1).translateObject(0f, 0.4f, 0f);

        tree.get(0).getChildObject().add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)24/255, (float)114/255, (float)8/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 12
        ));

        tree.get(0).getChildObject().get(2).rotateObject((float)Math.toRadians(-90f), 1f, 0f,0f);
        tree.get(0).getChildObject().get(2).translateObject(0f, 0.8f, 0f);

        tree.get(0).translateObject(-4f, 0.4f, -5f);

        //pohon2
        tree.add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)96/255, (float)22/255, (float)22/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 1
        ));
        tree.get(1).scaleObject(0.5f, 0.5f,0.5f);
        tree.get(1).rotateObject((float)Math.toRadians(-90f), 1f, 0f,0f);

        tree.get(1).getChildObject().add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)24/255, (float)114/255, (float)8/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 12
        ));

        tree.get(1).getChildObject().get(0).rotateObject((float)Math.toRadians(-90f), 1f, 0f,0f);

        tree.get(1).getChildObject().add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)24/255, (float)114/255, (float)8/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 12
        ));

        tree.get(1).getChildObject().get(1).rotateObject((float)Math.toRadians(-90f), 1f, 0f,0f);
        tree.get(1).getChildObject().get(1).translateObject(0f, 0.4f, 0f);

        tree.get(1).getChildObject().add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)24/255, (float)114/255, (float)8/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 12
        ));

        tree.get(1).getChildObject().get(2).rotateObject((float)Math.toRadians(-90f), 1f, 0f,0f);
        tree.get(1).getChildObject().get(2).translateObject(0f, 0.8f, 0f);

        tree.get(1).translateObject(5f, 0.4f, -0.5f);

        //pohon3
        tree.add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)96/255, (float)22/255, (float)22/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 1
        ));
        tree.get(2).scaleObject(0.5f, 0.5f,0.5f);
        tree.get(2).rotateObject((float)Math.toRadians(-90f), 1f, 0f,0f);

        tree.get(2).getChildObject().add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)24/255, (float)114/255, (float)8/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 12
        ));

        tree.get(2).getChildObject().get(0).rotateObject((float)Math.toRadians(-90f), 1f, 0f,0f);

        tree.get(2).getChildObject().add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)24/255, (float)114/255, (float)8/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 12
        ));

        tree.get(2).getChildObject().get(1).rotateObject((float)Math.toRadians(-90f), 1f, 0f,0f);
        tree.get(2).getChildObject().get(1).translateObject(0f, 0.4f, 0f);

        tree.get(2).getChildObject().add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)24/255, (float)114/255, (float)8/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 12
        ));

        tree.get(2).getChildObject().get(2).rotateObject((float)Math.toRadians(-90f), 1f, 0f,0f);
        tree.get(2).getChildObject().get(2).translateObject(0f, 0.8f, 0f);

        tree.get(2).getChildObject().add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)24/255, (float)114/255, (float)8/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 12
        ));

        tree.get(2).getChildObject().get(3).rotateObject((float)Math.toRadians(-90f), 1f, 0f,0f);
        tree.get(2).getChildObject().get(3).translateObject(0f, 1.2f, 0f);

        tree.get(2).translateObject(2.75f, 0.4f, -2f);

        //pohon4
        tree.add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)96/255, (float)22/255, (float)22/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 1
        ));
        tree.get(3).scaleObject(0.5f, 0.5f,0.5f);
        tree.get(3).rotateObject((float)Math.toRadians(-90f), 1f, 0f,0f);

        tree.get(3).getChildObject().add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)24/255, (float)114/255, (float)8/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 12
        ));

        tree.get(3).getChildObject().get(0).rotateObject((float)Math.toRadians(-90f), 1f, 0f,0f);

        tree.get(3).getChildObject().add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)24/255, (float)114/255, (float)8/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 12
        ));

        tree.get(3).getChildObject().get(1).rotateObject((float)Math.toRadians(-90f), 1f, 0f,0f);
        tree.get(3).getChildObject().get(1).translateObject(0f, 0.4f, 0f);

        tree.get(3).getChildObject().add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)24/255, (float)114/255, (float)8/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 12
        ));

        tree.get(3).getChildObject().get(2).rotateObject((float)Math.toRadians(-90f), 1f, 0f,0f);
        tree.get(3).getChildObject().get(2).translateObject(0f, 0.8f, 0f);

        tree.get(3).getChildObject().add(new Nicholas.Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
                List.of(
                        new Vector3f(-0.5f, 0.5f, 0.0f),
                        new Vector3f(-0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, -0.5f, 0.0f),
                        new Vector3f(0.5f, 0.5f, 0.0f)
                )
        ), new Vector4f((float)24/255, (float)114/255, (float)8/255, 1.0f), // ini untuk warna object segitiganya
                0.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 12
        ));

        tree.get(3).getChildObject().get(3).rotateObject((float)Math.toRadians(-90f), 1f, 0f,0f);
        tree.get(3).getChildObject().get(3).translateObject(0f, 1.2f, 0f);

        tree.get(3).translateObject(-5f, 0.4f, 1f);


        bezier(controlBerzier2, 0);
        listBezier.get(0).setFlag2();
//        listBezier.get(0).scaleObject(0.1f,0.1f,0.1f);


//        listBezier.get(0).rotateObject((float)Math.toRadians(90f), 0f, 1f,0f);
//        listBezier.get(0).translateObject(-0.5f, -0.5f,-1.5f);
//
//        listObj.add(new Nicholas.Sphere(Arrays.asList(
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//        ), new ArrayList<>(
//                List.of(
//                        new Vector3f(-0.5f, 0.5f, 0.0f),
//                        new Vector3f(-0.5f, -0.5f, 0.0f),
//                        new Vector3f(0.5f, -0.5f, 0.0f),
//                        new Vector3f(0.5f, 0.5f, 0.0f)
//                )
//        ), new Vector4f((float)96/255, (float)22/255, (float)22/255, 1.0f), // ini untuk warna object segitiganya
//                0.0f, 0.0f, 0.0f,
//                0.5f, 0.5f, 0.5f, 1
//        ));
//        listObj.get(7).scaleObject(0.5f,0.5f,1f);
//        listObj.get(7).translateObject(-0.4f, -0.2f,0f);

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
            }
        }

        if (tes == 0) {
            tes = 1;
        }
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

    public void input(){

        if (window.isKeyPressed(GLFW_KEY_W)) {
            camera.moveUp(0.03f);
        }

        if (window.isKeyPressed(GLFW_KEY_S)) {
            camera.moveDown(0.03f);
        }

        if (window.isKeyPressed(GLFW_KEY_A)) {
            camera.moveLeft(0.03f);

        }
        if (window.isKeyPressed(GLFW_KEY_D)) {
            camera.moveRight(0.03f);

        }

        if (window.isKeyPressed(GLFW_KEY_L)) {
            boolean phase1 = grizz.modeJalan();
            boolean phase2 = false;
            if (phase1){
                phase2 = pan.modeJalan(grizz);
            }

            if (phase2){
                ice.modeJalan(grizz);
            }
        }
        if (window.isKeyPressed(GLFW_KEY_MINUS)) {
//            pan.modeJalan(grizz);
            grizz.turunKiri();
        }


        if (window.isKeyPressed(GLFW_KEY_K)) {
//            grizz.jalan();
            pan.jalan();
            ice.jalan();
//            camera.moveLeft(0.006f);
//            camera.addRotation((float) Math.toRadians(0.0f), (float) Math.toRadians(0.1f));
        }
//        if (window.isKeyPressed(GLFW_KEY_O)) {
//            grizz.gerakTanganKanan();
//        }
        if (window.isKeyPressed(GLFW_KEY_P)) {
            grizz.angkatKiri();
        }
//        if (window.isKeyPressed(GLFW_KEY_0)) {
//            pan.angkatKiri();
//        }
        if (window.isKeyPressed(GLFW_KEY_I)) {
            grizz.gelengX();
            ice.GelengX();
            pan.GelengX();
        }
        if (window.isKeyPressed(GLFW_KEY_1)) {
            camera.addRotation(0.05f, 0f);
        }
        if (window.isKeyPressed(GLFW_KEY_2)) {
            camera.addRotation(-0.05f, 0f);
        }
        if (window.isKeyPressed(GLFW_KEY_J)) {
            grizz.AngkatTangan();
        }
        if (window.isKeyPressed(GLFW_KEY_H)) {
            grizz.resetTangan();
        }

//        if (window.isKeyRelease(GLFW_KEY_R)){
//
//            grizz.jalan();
//        }
        if (window.isKeyPressed(GLFW_KEY_T)){
            ice.kapak();
//            grizz.gelengX();
        }
        if (window.isKeyPressed(GLFW_KEY_0)){
            grizz.reset();
            ice.reset();
            pan.reset();
        }
        if (window.isKeyPressed(GLFW_KEY_B)){
            grizz.jalan();
            ice.listSphere.get(0).translateObject(0f, 0f, 0.01f);
            pan.listSphere.get(0).translateObject(0f, 0f, 0.01f);
            Vector3f vec = grizz.getPosition();
            camera.setPosition(vec.x + 5, vec.y+ 1, vec.z+2);
        }
        if (window.isKeyPressed(GLFW_KEY_Y)){


            ice.lepasKapak(grizz);
//            grizz.gelengX();
        }





        if (window.isKeyPressed(GLFW_KEY_C)) {
            camera.moveForward(0.05f);
        }
        if (window.isKeyPressed(GLFW_KEY_V)) {
            camera.moveBackwards(0.05f);
        }

        if (window.isKeyPressed(GLFW_KEY_Q)) {
            camera.addRotation((float) Math.toRadians(0.0f), (float) Math.toRadians(1f));
        }

        if (window.isKeyPressed(GLFW_KEY_E)) {
            camera.addRotation((float) Math.toRadians(0.0f), (float) Math.toRadians(-1f));
        }

        if (window.isKeyPressed(GLFW_KEY_G)){
            grizz.rotateSemua();
            ice.putarY();
        }

        if (window.isKeyPressed(GLFW_KEY_M)){
            grizz.TurunKepala();
            ice.TurunKepala();
            pan.turunKepala();
        }

        if (window.isKeyPressed(GLFW_KEY_9)){
            grizz.hadapKanan();
            ice.hadapKiri();
            pan.hadapKiri();
        }

        if (window.isKeyPressed(GLFW_KEY_COMMA)){
            grizz.putar();

        }

        if (window.isKeyPressed(GLFW_KEY_8)){
            if(!listBezier.get(0).getFlag2()) {
                listBezier.get(0).setFlag2();
            }
        }

        if (window.isKeyPressed(GLFW_KEY_7)){
            if(!clicked){
                grizz.listSphere.get(0).translateObject(-14f, 0f, 0f);
                pan.listSphere.get(0).translateObject(-14f, 0f, 0f);
                ice.listSphere.get(0).translateObject(-14f, 0f, 0f);


                grizz.hadapKiri();
                pan.hadapKiri();
                ice.hadapKiri();
                camera.addRotation((float) 0f, (float) Math.toRadians(285f));
                Vector3f vec = grizz.getPosition();
                camera.setPosition(vec.x + 5, vec.y+ 1, vec.z+2);

                clicked = true;
            }
        }


        if (window.isKeyPressed(GLFW_KEY_6)){
//            ice.GelengKiri();
//            pan.GelengKanan();

            pan.AngkatTangan();

            ice.AngkatTangan();
        }

        if(window.isKeyPressed(GLFW_KEY_Z)){
//            grizz.hadapKanan();
            pan.hadapKanan();
            ice.hadapKanan();

        }
        if(window.isKeyPressed(GLFW_KEY_X)){

            ice.putar();
        }



        if (window.isKeyPressed(GLFW_KEY_F)){
            grizz.hadapKiri();
        }
    }

    public void loop(){
        while (window.isOpen()){
            window.update();
            // untuk bersihin window (jadi hitam)
            glClearColor(0.5294117647f, 0.80784313725f, 0.92156862745f, 0.0f);
            GL.createCapabilities();
            // code . .
            input();

            for(Nicholas.Object3d obj : tree){
                obj.draw(camera, projection);
            }

            for(Nicholas.Object3d obj : this.listBezier){
                obj.drawLine(camera, projection);
            }

            for(Nicholas.Object3d obj : clouds){
                obj.draw(camera, projection);
            }

            for(Nicholas.Object3d obj : listObj){
                obj.draw(camera, projection);
            }

            for (Nicholas.Object3d object:grizz.listSphere){
                object.draw(camera, projection);
            }
            for (Nicholas.Object3d object:grizz.clouds){
                object.draw(camera, projection);
            }

            for (Gwan.Object3d object:pan.listSphere){
                object.draw(camera, projection);
            }

            for (Peter.Object3d object:ice.listBezier){
                object.drawLine(camera, projection);
            }

            for (Peter.Sphere object:ice.listSphere){
                object.draw(camera, projection);
            }




//            for (Object3d object: objects){
//                ((Circle)object).draw(camera, projection);
//            }

//            for (Object3d object:listBezier){
//                object.drawLine(camera, projection);
//            }

//            for (Object3d object:listBezier2){
//                object.drawLine(camera, projection);
//            }

//            for (Object3d object : berzier1) {
//                object.drawLine(camera, projection);
//
//            }
//            for (Object3d object : berzier2) {
//                object.drawLine(camera, projection);
//            }

//            for (Object3d object:objectsRectangle){
//                object.draw();
//            }
            //Restore state
            glDisableVertexAttribArray(0);
            // Poll for window events
            // the key callback above will only be
            // invoked during this call
            glfwPollEvents();

        }


    }

    public static void main(String[] args) {
        new Main().run();
    }
}
