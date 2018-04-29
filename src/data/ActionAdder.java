package data;

import authoring.entities.Entity;
import engine.actions.Actions;
import engine.components.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionAdder {

    private Entity entity;

    public ActionAdder(Entity entity){
        this.entity=entity;
    }
    public Map<String, Component> getActiveComponents(){
        Map<String,Component> actives = new HashMap<>();
        for(Component component : entity.getComponentList()){
            actives.put(component.getKey(),component);
        }
        return actives;
    }
    public void deleteComponent(Component component){
        ((Map<String, Component>) entity).remove(component);
    }

    public List<String> getActions(){
        List<String> methods = new ArrayList<>();
        for(Method method : Actions.class.getMethods()){
            methods.add(method.getName());
        }
        return methods;
    }



}
