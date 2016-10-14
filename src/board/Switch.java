package board;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import states.State;
import states.Terminal;
import util.MainLabel;
import util.Orientation;

@SuppressWarnings("serial")
public class Switch extends MainLabel implements MouseListener {
	
	Terminal output;
	ImageIcon icon;
	int value;
	private int index = 1;
	private int id;
	
	// for cleanup
	private boolean remove;
	
	public Switch(State init, int id){
			myName = "Switch";
			this.id = id;
			initialize(init);
	}
	public void initialize(State init){

		output = new Terminal(init);	
		
		if (init == State.PREFERRED){
			icon = new ImageIcon(getClass().getClassLoader().getResource("switchOn.jpg"));
		}
		else{
			icon = new ImageIcon(getClass().getClassLoader().getResource("switchOff.jpg"));
		}
			
		addMouseListener(this);
		
		setIcon(icon);
		
		remove = false;
		
	}
	public void switchToggle(){
		if(SelectionMenu.getSelected() != SelectionMenu.buttonNames.length -1) {
			if (index == 1){
				index = 0;
				icon = new ImageIcon(getClass().getClassLoader().getResource("switchOff.jpg"));
				setIcon(icon);
				output.value = State.OTHER;
			}
			else if (index == 0){
				index = 1;
				icon = new ImageIcon(getClass().getClassLoader().getResource("switchOn.jpg"));
				setIcon(icon);
				output.value = State.PREFERRED;
			}
		} else {
			remove = true;
		}
	
		//CircuitBoard.scan();
		
	}

	
	public State getSwitchState() {
		if(State.preferredSymbol() == index) {
			return State.PREFERRED;
		} else
			return State.OTHER;
	}
	public State getValue(){
		return output.value;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		switchToggle();
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean shouldRemove() {
		return remove;
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
		if (index == 1){
			index = 0;
			icon = new ImageIcon(getClass().getClassLoader().getResource("switchOff.jpg"));
			setIcon(icon);
			output.value = State.OTHER;
		}
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
