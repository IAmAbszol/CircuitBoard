package board;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class SelectionMenu implements ActionListener{
	
	public static int selected = 0;
	public static int subselect = 0;

	public static String[] buttonNames = {"Switch","Led","Single Connection","Split Connection","CrossOver Connection", "Nott Gate",
			 									"Andd Gate", "Orr Gate","Xorr Gate", "Nandd Gate", "Nand Latch", "Reset", "Clear"};
	public static int[] blockNumbers = { (buttonNames.length - 1), (buttonNames.length - 2), 0, 1, 2 };
	private JButton[] buttons = new JButton[buttonNames.length];
	public static JComboBox<String> jcb;
	public JPanel selectPanel;
	
	public SelectionMenu() {
		selectPanel = new JPanel(new GridLayout(buttonNames.length,1,2,2));
		for(int i = 0; i < buttonNames.length; i++){
			buttons[i] = new JButton(buttonNames[i]);
			buttons[i].setToolTipText(buttonNames[i]);
			buttons[i].addActionListener(this);
			buttons[i].setFont(buttons[i].getFont().deriveFont(Font.PLAIN));
			selectPanel.add(buttons[i]);
		}
		buttons[0].setFont(buttons[0].getFont().deriveFont(Font.BOLD));
		jcb = new JComboBox<String>();
		jcb.addItem(">");
		jcb.addItem("^");
		jcb.addItem("v");
		jcb.addItem("<");
	}

	public void actionPerformed(ActionEvent event) {
		for(int i = 0; i < buttonNames.length; i++){
			if(event.getSource()==buttons[i]){
				boolean match = false;
				selected = i;
				buttons[i].setFont(buttons[i].getFont().deriveFont(Font.BOLD));
				for(int j = 0; j < blockNumbers.length; j++) {
					if(blockNumbers[j] == i) {
						match = true;
						break;
					}
				}
				if(!match) {
					buttons[i].add(jcb);
					jcb.setToolTipText(buttons[i].getName());
				}
			} else {
				buttons[i].setFont(buttons[i].getFont().deriveFont(Font.PLAIN));
				buttons[i].remove(jcb);
				buttons[i].repaint();
				buttons[i].revalidate();
			}
		}
	}
	public static int getSelected(){
		return selected;
	}
	
}
