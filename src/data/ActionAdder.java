package data;

import authoring.entities.Block;
import authoring.entities.Entity;
import engine.actions.Actions;
import engine.components.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public class ActionAdder {

    private Entity entity;
    private Map<Method, List<Type>> methodParams;

    public ActionAdder(Entity entity){
        this.entity=entity;
    }
    public static void main(String args[]) {
        Entity e = new Block(1, "Block");
        ActionAdder a = new ActionAdder(e);
        a.getActions();
    }

    public Map<String, List<String>> getActions(){
        Map<String, List<String>> paramChar = new HashMap<>();
        methodParams = new HashMap<>();
       for(Method method : Actions.class.getMethods()){
           methodParams.put(method, Arrays.asList(method.getParameters()));
           List<String> parameters = new ArrayList<>();
           System.out.println(method.getName());

           for(Type genericFieldType : method.getGenericParameterTypes()){
               if(genericFieldType instanceof ParameterizedType){
                   ParameterizedType aType = (ParameterizedType) genericFieldType;
                   Type[] fieldArgTypes = aType.getActualTypeArguments();
                   for(Type fieldArgType : fieldArgTypes){
                       Class fieldArgClass = (Class) fieldArgType;
                   }
               }
           }

           for(Parameter param : Arrays.asList(method.getParameters())){
               System.out.println("     "+param.getType().getName());
               parameters.add(param.getName());

           }
           paramChar.put(method.getName(),parameters);
       }
       return paramChar;
    }






}
