package board;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SelectionMenu implements ActionListener{
	
	public static int selected = 0;
	
	public static String[] buttonNames = {"Switch","Led","Single Connection","Split Connection","Nott Gate",
			 									"Andd Gate", "Orr Gate","Xorr Gate", "Nandd Gate", "Nand Latch", "Reset", "Clear"};
	private JButton[] buttons = new JButton[buttonNames.length];
	public JPanel selectPanel;
	
	public SelectionMenu() {
		selectPanel = new JPanel(new GridLayout(4,2,5,5));
		for(int i = 0; i < buttonNames.length; i++){
			buttons[i] = new JButton(buttonNames[i]);
			buttons[i].addActionListener(this);
			selectPanel.add(buttons[i]);
		}
	}

	public void actionPerformed(ActionEvent event) {
		for(int i = 0; i < buttonNames.length; i++){
			if(event.getSource()==buttons[i]){
				selected = i;
				buttons[i].setFont(buttons[i].getFont().deriveFont(Font.BOLD));
			} else
				buttons[i].setFont(buttons[i].getFont().deriveFont(Font.PLAIN));
		}
	}
	public static int getSelected(){
		return selected;
	}
	
}
