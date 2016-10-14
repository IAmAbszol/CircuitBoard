package states;

public class Terminal {
	public State value;
	
	public Terminal(State init){
		value = init;
	}
	
	public void set(){
		value = State.PREFERRED;
	}
	
	public void clear(){
		value = State.OTHER;
	}
	public void toggle(){
		if(value == State.PREFERRED) value = State.OTHER;
		else value = State.PREFERRED;
	}
}
