

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class Controller implements ActionListener, MouseListener, KeyListener
{
	private View view;
	private Model model;
	private boolean keyLeft;
	private boolean keyRight;
	private boolean keyUp;
	private boolean keyDown;
	private boolean editMode = false;
	private boolean addWalls = true;

	public Controller(Model m)
	{
		model = m;
	}

	void setView(View v)
	{
		view = v;
	}

	public void actionPerformed(ActionEvent e)
	{
		
	}


	public void mousePressed(MouseEvent e)
	{
		if(editMode)
		{
			if(addWalls) 
			{
				model.addWall(e.getX(), e.getY());
			} 
			else 
			{
				model.removeWallHere(e.getX(), e.getY());
			}
		view.repaint();
		}
	}

	public void mouseReleased(MouseEvent e) 
	{  
		if(editMode) 
		{
			if(addWalls) 
			{
				model.finishWall(e.getX(), e.getY());
			}
	  	}
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}

	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
		}
		
		char key = Character.toLowerCase(e.getKeyChar());
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE || key == 'q') 
		{
			System.out.println("exiting game");
			System.exit(0);
		}

		if(e.getKeyCode() == KeyEvent.VK_C) 
		{
			model.clearWalls();
			view.repaint();
			System.out.println("Walls cleared");
		}

		if(e.getKeyCode() == KeyEvent.VK_E) 
		{
			editMode = !editMode;
			if(editMode) 
			{
				addWalls = true;
			}
			
		}	

		if(e.getKeyCode() == KeyEvent.VK_A) 
		{
			if(editMode) 
			{
				addWalls = !addWalls;
			}
		}

	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
		}
	
		char key = Character.toLowerCase(e.getKeyChar());
	
		if(key == 's') 
		{
			Json mapsave = model.marshall();
			mapsave.save("map.json");
			System.out.println("Map saved");
		}

		if(key == 'l') 
		{
			Json mapload = Json.load("map.json");
			model.unmarshall(mapload);
			System.out.println("Map loaded");
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	public void update()
	{
		model.savePrevCoords();
		if(keyUp == true) {
			model.movePacman(0, -8);
			model.getPacman().updateImage();
		}

		if(keyDown == true) {
			model.movePacman(0, 8);
			model.getPacman().updateImage();
		}

		if(keyLeft == true) {
			model.movePacman(-8, 0);
			model.getPacman().updateImage();	
		}

		if(keyRight == true) {
			model.movePacman(8, 0);
			model.getPacman().updateImage();	
		}

	}
}
