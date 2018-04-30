package authoring.actions;

import authoring.entities.Entity;
import engine.actions.ActionReader;
import engine.actions.Actions;
import engine.systems.collisions.CollisionDirection;
import javafx.scene.input.KeyCode;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/** Author @Conrad This classss provides the backend utility for
    creating actions and pairing them with components that
    require actions. This class interacts with ActionAdderView and
    ActionReader/Actions in engine and allows the user to create
    consumers
 **/
public class ActionAdder {

    private Entity entity;
    private Map<String, List<String>> methodParams;
    private Supplier input;
    private List<Supplier> suppliers;
    private static final String AI = "AI";
    private static final String COLLIDABLE = "Collidable";
    private static final String KEYCODE = "KeyInput";

    ActionAdder(Entity entity){
        this.entity=entity;
        initParams();
    }

    public Map<String, List<String>> getActions(){
        /* returns the map of parameters
         */
       return methodParams;
    }

    private void initParams(){
        /*reflexively builds the map of what objects each method in action needs
          to create the desired consumer action
         */
        methodParams = new HashMap<>();
        for(Method method : Actions.class.getMethods()){
            if(Modifier.isPublic(method.getModifiers()) && Modifier.isStatic(method.getModifiers())) {
                List<String> parameters = new ArrayList<>();
                for (Type param : Arrays.asList(method.getGenericParameterTypes())) {
                    parameters.add(param.getTypeName());
                }
                methodParams.put(method.getName(), parameters);
            }
        }
    }

    public void buildConsumer(String actionName, String component){
        /*creates suppliers which are popup boxes that prompt the user
         *for parameters required by Actions for a method selected by the user
         */
        suppliers = new ArrayList<>();
        List<String> params = methodParams.get(actionName);
        for(String param : params){
           suppliers.add(SupplierFactory.makeSupplier(param,entity));
        }
        input = SupplierFactory.makeInput(component, entity);
    }

    public void buildActionComponent(String methodName, String component){
      /* method builds the parameter list of objects which are inputs to the actions
         class, makes a call to ActionReader with the method and paramters to get the consumer
         for that action and configures the selected component with that action
       */
        try {
          List<Object> args = new ArrayList<>();
          ActionReader aRead = new ActionReader();
          for (Supplier supplier : suppliers)
              args.add(supplier.getData());
          System.out.print("Supplier with " + args.size() + " items");
          if (component.equals(AI))
             configureAI(aRead.getAction(methodName,args));
          if (component.equals(COLLIDABLE))
              configureCollidable((BiConsumer)aRead.getAction(methodName, args));
          if(component.equals(KEYCODE))
              configureKeyInput(aRead.getAction(methodName,args));
      }
      catch(Exception e){
          e.printStackTrace();
      }
    }

    private void configureAI(Consumer action){
        ((engine.components.AI) entity.get(AI)).setAction(action);
    }

    private void configureCollidable(BiConsumer action){
        ((engine.components.Collidable) entity.get(COLLIDABLE)).setOnDirection((CollisionDirection) input.getData(),action);
    }

    private void configureKeyInput(Consumer action){
        ((engine.components.KeyInput) entity.get(KEYCODE)).addCode((KeyCode) input.getData(),action);
    }

    private List<Object> reverse(List<Object> array){
        Object temp;
        for(int a =0 ; a<array.size()/2; a++){
            temp = array.get(a);
            array.set(a,array.get(array.size()-1-a));
            array.set(array.size()-1-a, temp);
        }
        return array;
    }

}
