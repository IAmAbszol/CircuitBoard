package board;

import javax.swing.ImageIcon;

import states.State;
import states.Terminal;
import util.MainLabel;

@SuppressWarnings("serial")
class Led extends MainLabel{
	
	private int id;
	Terminal input, Pref;
	ImageIcon icon;
	
	public Led (Terminal input, int id){
		
		this.id = id;
		myName = "Led";
		this.input = input;
		Pref = new Terminal(State.PREFERRED);
		
		if (input == Pref){
			icon = new ImageIcon(getClass().getClassLoader().getResource("LedOn.jpg"));
		}
		else{
			icon = new ImageIcon(getClass().getClassLoader().getResource("LedOff.jpg"));
		}
		setIcon(icon);
	}
	
	public void show(java.io.OutputStream out){
		try{
			out.write(State.display(input.value));
		} 
		catch (java.io.IOException ex) { 
			ex.printStackTrace();
		}
	}

	public State getValue() {
		return input.value;
	}

	@Override
	public boolean shouldRemove() {
		return false;
	}

	@Override
	public void setValue(State val) {
		input.value = val;
		
	}

	@Override
	public String getName() {
		return myName;
	}

	@Override
	public void update() {
		if (input.value == State.PREFERRED){
			icon = new ImageIcon(getClass().getClassLoader().getResource("LedOn.jpg"));
			System.out.println("is preffered");
		}
		else{
			System.out.println("is other");
			icon = new ImageIcon(getClass().getClassLoader().getResource("LedOff.jpg"));
		}
		setIcon(icon);
		
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
