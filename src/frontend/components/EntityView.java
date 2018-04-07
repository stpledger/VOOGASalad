package frontend.components;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javax.swing.event.ChangeEvent;

import com.sun.prism.paint.Color;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

public class EntityView extends ViewComponent{
	private double entityViewWidth = 300;
	private TabPane pane;
	private String[] tabsList = {"Sprites", "Blocks", "Game Objects"};
	private Object clipboard;
	
	public EntityView() {
		super();
		pane = new TabPane();
		pane.setPrefWidth(entityViewWidth);
		pane.getStyleClass().add("entity-view");
		addTabs();
	}
	
	private void addTabs() {
		//TODO: Convert this to be reflection of all the components
		for(String k : Arrays.asList(tabsList)) {
			ClipboardListener c = new ClipboardListener();
			EntityTab temp = new EntityTab(k, entityViewWidth);
			temp.getSelectedElementProperty().addListener(c);
			pane.getTabs().add(temp);
		}
	}
	
	@Override
	public Node getNode() {
		return pane;
	}

	@Override
	protected Broadcast buildBroadcast() {
		Broadcast b = new Broadcast();
		return b;
	}
	
	private class ClipboardListener implements ChangeListener{

		@Override
		public void changed(ObservableValue clipboardObject, Object oldValue, Object newValue) {
			broadcast.setMessage("setClipboard", new Object[] {newValue});	
			broadcast.setMessage("setTool", new Object[] {"addTool"});
		}
	}

}
