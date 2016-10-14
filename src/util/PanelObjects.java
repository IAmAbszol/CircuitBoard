package util;

import javax.swing.Icon;
import javax.swing.JLabel;

import states.State;

public class PanelObjects {
	
	private MainLabel label;

	public PanelObjects(MainLabel label){
		this.label = label;
	}
	
	public void setValue(State value) {
		label.setValue(value);
	}
	
	public String getName() {
		return label.getName();
	}
	
	public State getValue() {
		return label.getValue();
	}
	
	public int getX(){
		return label.getX();
	}
	public int getY(){
		return label.getY();
	}
	public JLabel getLabel(){
		return label;
	}
	public State getAttribute(){
		return label.getValue();
	}
	
	public void update(){
		label.update();
	}
	
	public int getId() {
		return label.getId();
	}
	
	public boolean getDoubleOutput() {
		return label.getDoubleOutput();
	}
	
	public State getFirstValue() {
		return label.getFirstValue();
	}
	
	public State getSecondValue() {
		return label.getSecondValue();
	}

	public void setValue(State value, State value2) {
		label.setValue(value, value2);
	}
	
	public Orientation orientate() {
		return label.orientate();
	}
	
	public void setOrientation(Orientation orientate) {
		label.setOrientation(orientate);
	}
	
}
