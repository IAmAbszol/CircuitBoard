package util;

import javax.swing.JLabel;
import states.State;

@SuppressWarnings("serial")
public abstract class MainLabel extends JLabel{
	
	protected String myName;
	protected boolean doubleOutput;
	protected Orientation orientate;
	
	public abstract String getName();
	public abstract void setValue(State val);
	public abstract void setValue(State val1, State val2);
	public abstract State getValue();
	public abstract boolean shouldRemove();
	public abstract void update();
	public abstract int getId();
	public abstract boolean getDoubleOutput();
	public abstract State getFirstValue();
	public abstract State getSecondValue();
	public abstract Orientation orientate();
	public abstract void setOrientation(Orientation orientate);
	
}
