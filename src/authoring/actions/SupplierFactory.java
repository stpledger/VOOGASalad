package authoring.actions;

import authoring.entities.Entity;

public class SupplierFactory {
     /*Autthor @Conrad makes the supplier based on string interretations of method parametrs passed in from actionadder
      */

    private static final String POSITIONS = "java.util.List<java.awt.Point>";
    private static final String DOUBLE = "double";
    private static final String INT = "int";
    private static final String ENTITY = "authoring.entities.Entity";
    private static final String KEYCODE = "KeyInput";
    private static final String COLLIDABLE = "Collidable";
    private static final String COLLISIONACTION = "CollisionAction";

    public static Supplier makeSupplier(String param, Entity entity){
        if(param.equals(POSITIONS)){
            return new PositionSupplier(entity);
        }
        if(param.equals(DOUBLE)){
            return new DoubleSupplier(entity);
        }
        else
            return new EntitySupplier(entity);
    }
    public static Supplier makeInput(String param, Entity entity){
        if(param.equals(KEYCODE)){
            return new KeyCodeSupplier(entity);
        }
        if(param.equals(COLLIDABLE) || param.equals(COLLISIONACTION)){
            return new DirectionSupplier(entity);
        }
        else
            return null;
    }



}
