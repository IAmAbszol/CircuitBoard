package states;

import javax.swing.ImageIcon;

import util.MainLabel;
import util.Orientation;

public class Nandd extends MainLabel {
	private int id;
	private ImageIcon icon;
	
	private State output, inp1, inp2;
	
	private boolean remove = false;
	
	public Nandd(Terminal inp1, Terminal inp2,  int id){
		this.id = id;
		myName = "Orr";
		this.inp1 = inp1.value;
		this.inp2 = inp2.value;
		analyze();
		icon = new ImageIcon(getClass().getClassLoader().getResource("Nandd.png"));
		setIcon(icon);
	}
	
	private void analyze() {
		if(inp1 == State.PREFERRED && inp2 == State.PREFERRED) 
			output = State.OTHER;
		else
			output = State.PREFERRED;
	}

	@Override
	public String getName() {
		return myName;
	}

	@Override
	public void setValue(State val) {
		inp1 = val;
		inp2 = val;
		analyze();
	}

	@Override
	public State getValue() {
		return output;
	}

	@Override
	public boolean shouldRemove() {
		return remove;
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
		inp1 = val1;
		inp2 = val2;
		analyze();
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
