package board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import states.Andd;
import states.NandLatch;
import states.Nandd;
import states.Nott;
import states.Orr;
import states.State;
import states.Terminal;
import states.Xorr;
import util.MainLabel;
import util.Orientation;
import util.PanelObjects;

@SuppressWarnings("serial")
public class CircuitBoard extends JFrame implements MouseListener, Runnable {

	public static CircuitBoard board;
	private static JPanel circuitPan;
	public SelectionMenu menu;
	private Switch switch1;
	
	private Led Led;
	private SingleConnection Line;
	private Nott nott;
	private Andd andd;
	private Orr orr;
	private Xorr xor;
	private Nandd nand;
	private NandLatch latch;
	private CrossOver co;
	
	private final int AGREED_ICON_SIZE = 50; // 50x50
	
	private static Dimension Screen = Toolkit.getDefaultToolkit().getScreenSize();
	public static double WIDTH = Screen.getWidth();
	public static double HEIGHT = Screen.getHeight();
	
	private boolean run = false;
	private int FPS = 10;
	private Thread thread;
	
	private int objectsAdded = 0;
	
	// rotate image stuff
	private BufferedImage image;
	
	private static ArrayList<PanelObjects> panelObjs;
	
	public CircuitBoard() {
		
		setLayout(null);
		
		panelObjs = new ArrayList<PanelObjects>();
		circuitPan = new JPanel();
		circuitPan.requestFocus();
		circuitPan.setFocusable(true);
		menu = new SelectionMenu();
		circuitPan.setLayout(null);
		circuitPan.addMouseListener(this);
		circuitPan.setBackground(Color.white);
		
		// adding locations
		int buffer = 125;
		circuitPan.setBounds(buffer, 0, (int)WIDTH - buffer, (int)HEIGHT);
		add(circuitPan);
		
		menu.selectPanel.setBounds(0,0,buffer, (int)HEIGHT);
		menu.selectPanel.setBackground(Color.gray);
		add(menu.selectPanel);
		
		if(thread == null) {
			run = true;
			thread = new Thread(this);
		}
		thread.start();
	}	
		
	
	public static void main(String args[]) {
		
		board = new CircuitBoard();
		
		board.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		board.setPreferredSize(new Dimension((int) WIDTH, (int) HEIGHT));
		board.getContentPane();
		board.setResizable(false);
		board.pack();
		board.setVisible(true);
	}

	
	public void mouseClicked(MouseEvent event) {
		
		int mouseX = event.getX();
		int mouseY = event.getY();
		if(SelectionMenu.selected < (SelectionMenu.buttonNames.length - 2)){
			switch(SelectionMenu.selected){
			case 0: 
				// Add switch
				switch1 = new Switch(State.OTHER, objectsAdded);
				objectsAdded++;
				snapable(switch1,mouseX,mouseY);
				if(canLayer(switch1)) {
					switch1.setOrientation(Orientation.NA);
					circuitPan.add(switch1);
					panelObjs.add(new PanelObjects(switch1));
					board.validate();
					board.repaint();
				} else
					switch1 = null;
				break;
			case 1:
				// Add Led
				Led = new Led(new Terminal(State.OTHER), objectsAdded);
				if(canLayer(Led)) {
					objectsAdded++;
					snapable(Led,mouseX,mouseY);
					Led.setOrientation(Orientation.NA);
					circuitPan.add(Led);
					panelObjs.add(new PanelObjects(Led));
					board.validate();
					board.repaint();
				} else Led = null;
				break;
			case 2:
				// Add Connection Line
				Line = new SingleConnection(new Terminal(State.OTHER), objectsAdded);
				if(canLayer(Line)) {
					objectsAdded++;
					snapable(Line,mouseX,mouseY);	
					Line.setOrientation(Orientation.NA);
					circuitPan.add(Line);
					panelObjs.add(new PanelObjects(Line));
					board.validate();
					board.repaint();
				} else Line = null;
				break;
			case 3:
				// Add Split Connection
				SplitConnection Line;
				Line = new SplitConnection(new Terminal(State.OTHER), objectsAdded);
				if(canLayer(Line)) {
					objectsAdded++;
					snapable(Line,mouseX,mouseY);	
					Line.setOrientation(Orientation.NA);
					circuitPan.add(Line);
					panelObjs.add(new PanelObjects(Line));
					board.validate();
					board.repaint();
				} else Line = null;
				break;
			case 4:
				co = new CrossOver(new Terminal(State.OTHER), objectsAdded);
				if(canLayer(co)) {
					objectsAdded++;
					snapable(co,mouseX,mouseY);	
					co = (CrossOver) rotate(co, SelectionMenu.jcb.getSelectedIndex());
					circuitPan.add(co);
					co.setOrientation(Orientation.RIGHT);
					panelObjs.add(new PanelObjects(co));
					board.validate();
					board.repaint();
				} else co = null;
				break;
			case 5:// Add Not Gate
				nott = new Nott(new Terminal(State.OTHER), objectsAdded);
				if(canLayer(nott)) {
					objectsAdded++;
					snapable(nott,mouseX,mouseY);	
					nott = (Nott) rotate(nott, SelectionMenu.jcb.getSelectedIndex());
					circuitPan.add(nott);
					nott.setOrientation(Orientation.RIGHT);
					panelObjs.add(new PanelObjects(nott));
					board.validate();
					board.repaint();
				} else nott = null;
				break;
			case 6:// Add And Gate
				andd = new Andd(new Terminal(State.OTHER), new Terminal(State.OTHER), objectsAdded);
				if(canLayer(andd)) {
					objectsAdded++;
					snapable(andd,mouseX,mouseY);
					andd = (Andd) rotate(andd, SelectionMenu.jcb.getSelectedIndex());
					circuitPan.add(andd);
					panelObjs.add(new PanelObjects(andd));
					board.validate();
					board.repaint();
				} else andd = null;
				break;
			case 7:// Add Or Gate
				orr = new Orr(new Terminal(State.OTHER), new Terminal(State.OTHER), objectsAdded);
				if(canLayer(orr)) {
					objectsAdded++;
					snapable(orr,mouseX,mouseY);	
					orr = (Orr) rotate(orr, SelectionMenu.jcb.getSelectedIndex());
					circuitPan.add(orr);
					panelObjs.add(new PanelObjects(orr));
					board.validate();
					board.repaint();
				} else orr = null;
				break;
			case 8:// Add xorr gate
				xor = new Xorr(new Terminal(State.OTHER), new Terminal(State.OTHER), objectsAdded);
				if(canLayer(xor)) {
					objectsAdded++;
					snapable(xor,mouseX,mouseY);	
					xor = (Xorr) rotate(xor, SelectionMenu.jcb.getSelectedIndex());
					circuitPan.add(xor);
					panelObjs.add(new PanelObjects(xor));
					board.validate();
					board.repaint();
				} else xor = null;
				break;
				
			case 9:// Add nandd gate
				nand = new Nandd(new Terminal(State.OTHER), new Terminal(State.OTHER), objectsAdded);
				if(canLayer(nand)) {
					objectsAdded++;
					snapable(nand,mouseX,mouseY);	
					nand = (Nandd) rotate(nand, SelectionMenu.jcb.getSelectedIndex());
					circuitPan.add(nand);
					panelObjs.add(new PanelObjects(nand));
					board.validate();
					board.repaint();
				} else nand = null;
				break;
			
			case 10:// nand latch
				latch = new NandLatch(new Terminal(State.OTHER), new Terminal(State.OTHER), objectsAdded);
				if(canLayer(latch)) {
					objectsAdded++;
					snapable(latch, mouseX, mouseY);
					latch = (NandLatch) rotate(latch, SelectionMenu.jcb.getSelectedIndex());
					circuitPan.add(latch);
					panelObjs.add(new PanelObjects(latch));
					board.validate();
					board.repaint();
				} else latch = null;
				break;
			}
		}else {
			if(SelectionMenu.selected == SelectionMenu.buttonNames.length - 1)
				clear(mouseX, mouseY);
			else if(SelectionMenu.selected == (SelectionMenu.buttonNames.length - 2))  
				cleanUpTheBoard();
			}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	private void scan() {
		int buffer = 5;
		// master
		for(int i = 0; i < panelObjs.size(); i++) {
			PanelObjects master = panelObjs.get(i);
			for(int j = 0; j < panelObjs.size(); j++) {
				PanelObjects slave = panelObjs.get(j);
				if(!slave.getName().equals("Switch") && master.getId() != slave.getId()) {
					int slaveWidth = slave.getLabel().getIcon().getIconWidth();
					int slaveHeight = slave.getLabel().getIcon().getIconHeight();
					int masterWidth = master.getLabel().getIcon().getIconWidth();
					int masterHeight = master.getLabel().getIcon().getIconHeight();
					Orientation slaveOrientation = slave.orientate();
					Orientation masterOrientation = master.orientate();
					if(masterWidth == 50 && masterHeight == 50) {
						// check sides
						if(slaveWidth == 50 && slaveHeight == 50) {
						// left - evaluate masters left
							if((slave.getX() + slaveWidth + buffer) >= master.getX() && (slave.getX() + slaveWidth + buffer) <= (master.getX() + masterWidth) && master.getY() == slave.getY()) {
										slave.setValue(master.getValue());
										//	System.out.println(slave.getName() + " left");
							}
							
							// right - evaluating slaves left with masters right
							if((slave.getX() - buffer) <= (master.getX() + masterWidth) && (slave.getX() - buffer) >= (master.getX()) && master.getY() == slave.getY()) {
										slave.setValue(master.getValue());
									//	System.out.println(slave.getName() + " right");
							}
							
							// top - evaluating master top
							if((slave.getY() + slaveHeight + buffer) >= master.getY() && (slave.getY() + slaveHeight + buffer) <= (master.getY() + masterHeight) && master.getX() == slave.getX()) {
										slave.setValue(master.getValue());
									//	System.out.println(slave.getName() + " top");
							}
							
							// bottom - evaluating masters bottom
							if((slave.getY() - buffer) <= (master.getY() + masterHeight) && (slave.getY() - buffer) >= (master.getY()) && master.getX() == slave.getX()) {
										slave.setValue(master.getValue());
									//	System.out.println(slave.getName() + " bottom");
							}
						} else
							if(slaveWidth == 150 && slaveHeight == 150) {
								// right
								if(slave.getY() == master.getY() && slave.getX() == master.getX() + 50 && (slaveOrientation == Orientation.RIGHT || slaveOrientation == Orientation.NA)) { // same plane
									// now to live capture the second wire/connection
									for(int k = 0; k < panelObjs.size(); k++) {
										PanelObjects secondMaster = panelObjs.get(k);
										if(slave.getY() + 100 == secondMaster.getY() && master.getX() == secondMaster.getX() && master.getId() != secondMaster.getId()) {
											slave.setValue(master.getValue(), secondMaster.getValue());
											break;
										}
									}
								}
								// left
								if(slave.getY() == master.getY() && slave.getX() == master.getX() - slaveWidth && slaveOrientation == Orientation.LEFT) { // same plane
									// now to live capture the second wire/connection
									for(int k = 0; k < panelObjs.size(); k++) {
										PanelObjects secondMaster = panelObjs.get(k);
										if(slave.getY() + 100 == secondMaster.getY() && master.getX() == secondMaster.getX() && master.getId() != secondMaster.getId()) {
											slave.setValue(master.getValue(), secondMaster.getValue());
											break;
										}
									}
								}
								// up
								if(slave.getX() == master.getX() && slave.getY() == master.getY() - slaveHeight && slaveOrientation == Orientation.UP) { // same plane
									// now to live capture the second wire/connection
									for(int k = 0; k < panelObjs.size(); k++) {
										PanelObjects secondMaster = panelObjs.get(k);
										if(slave.getX() + 100 == secondMaster.getX() && master.getY() == secondMaster.getY() && master.getId() != secondMaster.getId()) {
											slave.setValue(master.getValue(), secondMaster.getValue());
											break;
										}
									}
								}
								// down
								if(slave.getX() == master.getX() && slave.getY() == master.getY() + masterHeight && slaveOrientation == Orientation.DOWN) { // same plane
									// now to live capture the second wire/connection
									for(int k = 0; k < panelObjs.size(); k++) {
										PanelObjects secondMaster = panelObjs.get(k);
										if(slave.getX() + 100 == secondMaster.getX() && master.getY() == secondMaster.getY() && master.getId() != secondMaster.getId()) {
											slave.setValue(master.getValue(), secondMaster.getValue());
											break;
										}
									}
								}
							}
					
					} else
						// this is glitched?
						if(masterWidth == 150 && masterHeight == 150) {
							if(master.getDoubleOutput()) {
								if(master.getY() == slave.getY() && master.getX() + 150 == slave.getX() && (master.orientate() == Orientation.RIGHT || master.orientate() == Orientation.NA)) {
									for(int k = 0; k < panelObjs.size(); k++) {
										PanelObjects secondSlave = panelObjs.get(k);
										if(master.getY() + 100 == secondSlave.getY() && slave.getX() == secondSlave.getX()) {
											slave.setValue(master.getFirstValue());	// 1
											secondSlave.setValue(master.getSecondValue());
											break;
										}
									}
								}
								if(master.getY() == slave.getY() && master.getX() - 50 == slave.getX() && master.orientate() == Orientation.LEFT) {
									for(int k = 0; k < panelObjs.size(); k++) {
										PanelObjects secondSlave = panelObjs.get(k);
										if(master.getY() + 100 == secondSlave.getY() && slave.getX() == secondSlave.getX()) {
											slave.setValue(master.getFirstValue());	// 1
											secondSlave.setValue(master.getSecondValue());
											break;
										}
									}
								}
								if(master.getX() == slave.getX() && master.getY() - 50 == slave.getY() && master.orientate() == Orientation.UP) {
									for(int k = 0; k < panelObjs.size(); k++) {
										PanelObjects secondSlave = panelObjs.get(k);
										if(master.getX() + 100 == secondSlave.getX() && slave.getY() == secondSlave.getY()) {
											slave.setValue(master.getFirstValue());	// 1
											secondSlave.setValue(master.getSecondValue());
											break;
										}
									}
								}
								if(master.getX() == slave.getX() && master.getY() + 150 == slave.getY() && master.orientate() == Orientation.DOWN) {
									for(int k = 0; k < panelObjs.size(); k++) {
										PanelObjects secondSlave = panelObjs.get(k);
										if(master.getX() + 100 == secondSlave.getX() && slave.getY() == secondSlave.getY()) {
											slave.setValue(master.getFirstValue());	// 1
											secondSlave.setValue(master.getSecondValue());
											break;
										}
									}
								}
							} else {
								// to the right now
								if(master.getY() + slaveHeight == slave.getY()) { // same plane, lets play
									if(master.getX() + masterWidth == slave.getX()) {
										slave.setValue(master.getValue());
									}
								}
								// to the left now
								if(master.getY() + slaveHeight == slave.getY()) {
									if(master.getX() - slaveWidth == slave.getX()) {
										slave.setValue(master.getValue());
									}
								}
								// to the up now
								if(master.getX() + slaveWidth == slave.getX()) {
									if(master.getX() + slaveWidth == slave.getX()) {
										slave.setValue(master.getValue());
									}
								}
								// to the down now
								if(master.getX() - masterHeight == slave.getX()) {
									if(master.getX() + slaveWidth == slave.getX()) {
										slave.setValue(master.getValue());
									}
								}
							}	
						}
				}
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		g.setFont(new Font("Arial", Font.BOLD, 14));
		g.setColor(Color.black);
		for(int i = 0; i < panelObjs.size(); i++) {
			String s = panelObjs.get(i).getName();
			int width = g.getFontMetrics().stringWidth(s);
			g.drawString(
				panelObjs.get(i).getName(), 
				panelObjs.get(i).getX() + (panelObjs.get(i).getLabel().getWidth() / 2) - width / 2,
				panelObjs.get(i).getY() + (panelObjs.get(i).getLabel().getHeight() /2 ) - g.getFontMetrics().getHeight() / 2
			);
		}
		
		circuitPan.paintComponents(g);
		
	}
	
	public void resetBoard() {
		for(int i = 0; i < panelObjs.size(); i++) {
			panelObjs.get(i).setValue(State.OTHER, State.OTHER);
			panelObjs.get(i).setValue(State.OTHER);
			panelObjs.get(i).update();
		}
	}
	
	public static void cleanUpTheBoard() {
		for(int i = 0; i < panelObjs.size(); i++) {
			if(((MainLabel) panelObjs.get(i).getLabel()).shouldRemove()) {
				circuitPan.remove(panelObjs.get(i).getLabel());
				panelObjs.remove(i);
			}
		}
	}
	
	public void clear(int mouseX, int mouseY) {
		  /*int newX = mouseX % AGREED_ICON_SIZE;
			int newY = mouseY % AGREED_ICON_SIZE;
			int ultimateX = mouseX - newX;
			int ultimateY = mouseY - newY;
				if((panelObjs.get(i).getLabel().getX() == ultimateX) &&
						(panelObjs.get(i).getLabel().getY() == ultimateY)){
					circuitPan.remove(panelObjs.get(i).getLabel());
					panelObjs.remove(i);
					board.validate();
					board.repaint();				
				}
			}*/	
			
			// my attempt at clearing the panels 
			for(int i = 0; i < panelObjs.size(); i++){
				if(panelObjs.get(i).getLabel().getBounds().contains(mouseX, mouseY)){
					circuitPan.remove(panelObjs.get(i).getLabel());
					panelObjs.remove(i);
					board.validate();
					board.repaint();
				}
			}			
		}
	
	private boolean canLayer(JLabel label) {
		for(int i = 0; i < panelObjs.size(); i++) {
			if(label.getX() == panelObjs.get(i).getX() &&
					label.getY() == panelObjs.get(i).getY()) {
				return false;
			}
		}
		return true;
	}
	
	private MainLabel rotate(MainLabel ico, int or) {
		BufferedImage image = new BufferedImage(
				ico.getIcon().getIconWidth(),
				ico.getIcon().getIconHeight(),
				BufferedImage.TYPE_INT_RGB
		);
		Graphics g = image.createGraphics();
		ico.getIcon().paintIcon(null, g, 0, 0);
		g.dispose();
		
		Icon icon = null;
		
		//rotate
		if(or == 1) {
			icon = new ImageIcon(Orientation.rotateUp(image));
			ico.setIcon(icon);
			ico.setOrientation(Orientation.UP);
			return ico;
		}
		if(or == 3) {
			icon = new ImageIcon(Orientation.rotateLeft(image));
			ico.setIcon(icon);
			ico.setOrientation(Orientation.LEFT);
			return ico;
		}
		if(or == 0) {
			ico.setOrientation(Orientation.RIGHT);
			return ico;
		}
		if(or == 2) {
			icon = new ImageIcon(Orientation.rotateDown(image));
			ico.setIcon(icon);
			ico.setOrientation(Orientation.DOWN);
			return ico;
		}
		if(or == 0) {
			ico.setOrientation(Orientation.RIGHT);
			return ico;
		}
		
		return ico;
		
	}

	private void snapable(MainLabel snapable,int x, int y){
		int newX = x % AGREED_ICON_SIZE;
		int newY = y % AGREED_ICON_SIZE;
		Dimension size = snapable.getPreferredSize();		
		snapable.setBounds(x - newX, y - newY, size.width, size.height);
		snapable.setLocation(x - newX,y - newY);
	}
	
	
	// engine updater, keep components in check
	private void update() {
		
		cleanUpTheBoard();
		
		if(SelectionMenu.selected < (SelectionMenu.buttonNames.length - 2)){
			scan();
			for(int i = 0; i < panelObjs.size(); i++){
				if(!panelObjs.get(i).getName().equals("Switch"))
					panelObjs.get(i).update();
			}
		} else if(SelectionMenu.selected == (SelectionMenu.buttonNames.length - 2)) {
			resetBoard();
		} else if(SelectionMenu.selected == (SelectionMenu.buttonNames.length - 1)) {
			
		}
		board.validate();
		board.repaint();
		
	}
	
	private long redraw() {
		long start = System.currentTimeMillis();
		
		update();
		
		return System.currentTimeMillis() - start;
		
	}
	
	public void run() {
		
		while(run) {
			
			long durationMs = redraw();
			
			try {
				Thread.sleep(Math.max(0, FPS - durationMs));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		
	}
	
	
}	

