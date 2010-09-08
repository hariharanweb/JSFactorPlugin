package hari.jsfactor.ui.contants;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;


public interface IJSColorConstants {

	public Display DISPLAY = Display.getCurrent();

	public Color COMMENT_COLOR = new Color(DISPLAY, new RGB(0, 255, 0));
	public Color JS_KEYWORD_COLOR = new Color(DISPLAY, new RGB(0, 0, 255));
	public Color JS_OPERATOR_COLOR = new Color(DISPLAY, new RGB(255, 0, 0));
	public Color JS_STRING_COLOR = new Color(DISPLAY, new RGB(0, 0, 155));
	
}
