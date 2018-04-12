package data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import engine.components.*;
import frontend.components.Level;
import javafx.scene.input.KeyCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class SerializeTest {

    public static void main(String[] args)
    {
        Level level1 = new Level(1);
        Level level2 = new Level(2);

        Map<Integer, Map<String, Component>> level1State = new HashMap<>();
        Map<Integer, Map<String, Component>> level2State = new HashMap<>();

        Integer entity1 = 1;
        Integer entity2 = 2;

        Map<String, Component> entity1Components = new HashMap<>();
        Map<String, Component> entity2Components = new HashMap<>();

        ArrayList<String> params = new ArrayList<>();

        params.add("1");
        params.add("3");

        Acceleration entity1Acceleration = new Acceleration(1,1,0);
        entity1Components.put("Acceleration", entity1Acceleration);


        params.clear();
        params.add("10");
        params.add("30");

        Position entity1Position = new Position(1,10,30);
        entity1Components.put("Position", entity1Position);

        params.clear();
        params.add("5");
        params.add("0");

        Velocity entity1Velocity = new Velocity(1,5,0);
        entity1Components.put("Velocity", entity1Velocity);


        params.clear();
        params.add("Mario.png");

        try {
            Sprite entity1Sprite = new Sprite(1, "mario.png");
            entity1Components.put("Sprite", entity1Sprite);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        params.clear();
        params.add("4");
        params.add("10");
//__________________________________________________
        Acceleration entity2Acceleration = new Acceleration(2,4,10);
        entity2Components.put("Acceleration", entity2Acceleration);

        KeyInput k = new KeyInput(1, KeyCode.SPACE, e -> {
            System.out.println("lambda executed");
            entity1Position.setXPos(10000000); });
        entity1Components.put(k.KEY, k);


        params.clear();
        params.add("100");
        params.add("50");

        Position entity2Position = new Position(2, 0,0);
        entity2Components.put(Position.KEY, entity2Position);

        Dimension d = new Dimension (2, 20, 500);
        entity2Components.put(Dimension.KEY, d);

        params.clear();
        params.add("1");
        params.add("-1");

        Velocity entity2Velocity = new Velocity(2, 20,0);
        entity2Components.put("Velocity", entity2Velocity);


        params.clear();
        params.add("m ario.png");

        try {
            Sprite entity2Sprite = new Sprite(2, "mario.png");
            entity2Components.put("Sprite", entity2Sprite);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        level1State.put(1,entity1Components);
        level2State.put(2, entity2Components);



        Map<Level,Map<Integer,Map<String,Component>>> testState = new HashMap<>();
        testState.put(level1,level1State);
        testState.put(level2,level2State);
        DataGameState state = new DataGameState(testState);

        File xml = new File("sdf");
        try {
            xml =DataWrite.saveFile(state, "TestGame2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataGameState testDeserialize = new DataGameState();
        testDeserialize = DataRead.loadFile(xml);

    }

}

