package data;

import authoring.entities.Entity;
import authoring.factories.ButtonElement;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;


public class KeyCodeSupplier extends Supplier {

    private KeyCode key;
    private TextArea keyInput;
    private static final String KEY_PROMPT = "Input a key and press enter";
    private static final int XSIZE = 200;
    private static final int YSIZE =70;
    private static final String FEEDBACK ="You have entered: ";
    private static final String INVALID = "Invalid Key";
    public KeyCodeSupplier(Entity e){
        super(KEY_PROMPT, XSIZE, YSIZE, e);
    }

    protected void configureMenu(){
        keyInput = new TextArea();
        keyInput.setPrefHeight(10);
        keyInput.setOnKeyPressed(e->checkCode(e.getCode()));
        menu.getChildren().add(0,keyInput);
        menu.setPrefHeight(YSIZE);
        prompt.setText(FEEDBACK + INVALID);

    }

    private void checkCode(KeyCode e){
        keyInput.clear();
        prompt.setText(FEEDBACK + e.getName());
        key = e;
    }


}
