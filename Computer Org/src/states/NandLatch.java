package states;

import javax.swing.ImageIcon;

import util.MainLabel;

public class NandLatch extends MainLabel {

	private int id;
	private ImageIcon icon;
	
	private State output1, output2, inp1, inp2;
	
	public NandLatch(Terminal inp1, Terminal inp2, int id) {
		
		myName = "Nand Latch";
		this.id = id;
		this.inp1 = inp1.value;
		this.inp2 = inp2.value;
		
		icon = new ImageIcon(getClass().getClassLoader().getResource("nandlatch.png"));
		setIcon(icon);
		
	}
	
	private void analyze() {
		System.out.println("Evaluating Inp1 " + inp1 + " and Evaluating Inp2 " + inp2);
		if(inp1 == State.OTHER && inp2 == State.OTHER) {
			output1 = State.PREFERRED;
			output2 = State.PREFERRED;
			System.out.println("IF 1");
			return;
		}
		if(inp1 == State.OTHER) {
			output1 = State.PREFERRED;
			output2 = State.OTHER;
			System.out.println("IF 2");
			return;
		}
		if(inp1 == State.PREFERRED) {
			output1 = State.OTHER;
			output2 = State.PREFERRED;
			System.out.println("IF 3");
			return;
		}
	}
	
	@Override
	public String getName() {
		return myName;
	}

	@Override
	public void setValue(State val) {
		inp1 = val;
		inp2 = val;
	}

	@Override
	public void setValue(State val1, State val2) {
		inp1 = val1;
		inp2 = val2;
		analyze();
	}

	@Override
	public State getValue() {
		return output1;
	}

	@Override
	public boolean shouldRemove() {
		// TODO Auto-generated method stub
		return false;
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
	public boolean getDoubleOutput() {
		return true;
	}

	@Override
	public State getFirstValue() {
		return output1;
	}

	@Override
	public State getSecondValue() {
		return output2;
	}

}
