package states;

import javax.swing.ImageIcon;

import util.MainLabel;

public class Xorr extends MainLabel {

	private int id;
	private ImageIcon icon;
	
	private State output, inp1, inp2;
	
	private boolean remove = false;
	
	public Xorr(Terminal inp1, Terminal inp2,  int id){
		this.id = id;
		myName = "Orr";
		this.inp1 = inp1.value;
		this.inp2 = inp2.value;
		analyze();
		icon = new ImageIcon(getClass().getClassLoader().getResource("Xorr.png"));
		setIcon(icon);
	}
	
	private void analyze() {
		if(State.display(inp1) == State.display(inp2)) {
			output = State.OTHER;
		} else
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

}
