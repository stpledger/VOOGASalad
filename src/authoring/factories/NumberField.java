package authoring.factories;

import javafx.scene.control.TextField;

public class NumberField extends TextField{
	
	@Override
	public void replaceText(int start, int end, String text) {
		if (isNumber(text))
			super.replaceText(start, end, text);
	}
	
	@Override
	public void replaceSelection(String text) {
		if (isNumber(text))
			super.replaceSelection(text);
	}
	
	private boolean isNumber(String input) {
		return input.matches("[0-9]*");
	}
}
