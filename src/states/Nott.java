package states;

import javax.swing.ImageIcon;
import util.MainLabel;
import util.Orientation;

@SuppressWarnings("serial")
public class Nott extends MainLabel{
	
	private int id;
	Terminal input, output;
	ImageIcon icon;
	
	public Nott(Terminal inp, int id){
		this.id = id;
		myName = "Nott";
		input = inp;
		State current = nott(input.value);
		output = new Terminal(current);
		icon = new ImageIcon(getClass().getClassLoader().getResource("NottGate.jpg"));
		setIcon(icon);
		
	}
	
	public static State nott(State operand){
		
		State result = State.OTHER;
		if(operand == State.PREFERRED) 
			result = State.OTHER;
		else
			result = State.PREFERRED;
		return result;

	}
	public void evaluate(){
		output.value = nott(input.value);
	}
	public State getValue(){
		return output.value;
	}

	@Override
	public boolean shouldRemove() {
		return false;
	}

	@Override
	public void setValue(State val) {
		output.value = nott(val);
	}

	@Override
	public String getName() {
		return myName;
	}

	@Override
	public void update() {
		evaluate();
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
