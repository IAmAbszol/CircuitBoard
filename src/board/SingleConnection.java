package board;

import java.awt.Component;

import javax.swing.ImageIcon;

import states.State;
import states.Terminal;
import util.MainLabel;
import util.Orientation;

@SuppressWarnings("serial")
public class SingleConnection extends MainLabel{
	
	private int id;
	Terminal output;
	Component comp;
	ImageIcon icon;
	
	public SingleConnection(Terminal init, int id){
		
		myName = "Single Connection";
		icon = new ImageIcon(getClass().getClassLoader().getResource("ConnectionBend.jpg"));
		setIcon(icon);
		output = init;
		this.id = id;
	}

	@Override
	public State getValue() {
		return output.value;
	}

	@Override
	public boolean shouldRemove() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setValue(State val) {
		output.value = val;
		
	}

	@Override
	public String getName() {
		return myName;
	}

	@Override
	public void update() {
		//double MaX = getBounds().getMaxX();
		//double MaY = getBounds().getMaxY();
		//double MiX = getBounds().getMinX();
		//double MiY = getBounds().getMinY();
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setValue(State val1, State val2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getDoubleOutput() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public State getFirstValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State getSecondValue() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Orientation orientate() {
		return orientate;
	}

	@Override
	public void setOrientation(Orientation orientate) {
		this.orientate = orientate;
	}
}
