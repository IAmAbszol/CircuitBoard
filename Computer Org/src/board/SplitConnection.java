package board;

import javax.swing.ImageIcon;
import states.State;
import states.Terminal;
import util.MainLabel;

@SuppressWarnings("serial")
public class SplitConnection extends MainLabel{
	
	private int id;
	Terminal output;
	ImageIcon icon;
	
	public SplitConnection(Terminal init, int id){
		myName = "Split Connection";
		icon = new ImageIcon(getClass().getClassLoader().getResource("ConnectionSplit.jpg"));
		setIcon(icon);
		output = init;
		this.id = id;
	}

	public State getValue() {
		return output.value;
	}

	public boolean shouldRemove() {
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
		// TODO Auto-generated method stub
		
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
}
