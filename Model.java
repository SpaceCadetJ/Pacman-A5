

import java.util.ArrayList;
import java.io.IOException;
import java.util.Iterator;

public class Model
{
	private ArrayList<Wall> walls;
	private int startX, startY;
	private Pacman pacman;

	public Model()
	{
		walls = new ArrayList<Wall>();
		pacman = new Pacman(250,250, 30, 30);

	}

	public void addWall(int startingX, int startingY) 
	{
		
		startX = startingX;
		startY = startingY;
    }

	public void finishWall(int finalX, int finalY) 
	{
		int w = finalX - startX;
		int h = finalY - startY;
		System.out.println("finish wall");
		Wall wall = new Wall(this.startX, this.startY, w, h);
		walls.add(wall);
    }	

    public void removeWall(Wall wall) 
	{
        walls.remove(wall);
    }

	public void clearWalls() {

		walls.clear();
	}

	public void removeWallHere(int mouseX, int mouseY) 
	{
		walls.removeIf(wall -> mouseX >= wall.getX() && mouseX <= wall.getX() + wall.getW()
			&& mouseY >= (wall.getY() + View.scrollY) && mouseY <= (wall.getY() + View.scrollY) + wall.getH());
	}


    public ArrayList<Wall> getWalls() 
	{
        return walls;
    }

	public Iterator<Wall> getIterator() 
	{
        return walls.iterator();
	}

	public void update()
	{
		
		for(Wall wall: walls)
		{
			if(checkCollision(wall))
			{
				pacman.fixPosition(wall);	
			}
		}
		
	}


	public void savePrevCoords()
	{
		pacman.PrevCoords(pacman.getX(), pacman.getY());
	}

	public Pacman getPacman() 
	{
		return pacman;
	}


	public Json marshall() 
	{
		Json json = Json.newObject();
		Json wallArray = Json.newList();
    
        for (Wall wall : walls) 
		{
            wallArray.add(wall.marshalJson());
        }
        json.add("walls", wallArray);
        return json;
    }

    public void unmarshall(Json json) 
	{
        walls.clear();
        Json wallsArray = json.get("walls");

		for(int i = 0; i < wallsArray.size(); i++) 
		{
			Wall wall = new Wall(wallsArray.get(i));
        	walls.add(wall);
		}
    }

	public void loadMap(String filename) 
	{
		Json mapJson = Json.load(filename);
		unmarshall(mapJson);
	}

	public void movePacman(int dx, int dy) 
	{
		pacman.move(dx, dy);
	}

	public void keyPressed() 
	{

	}
	
	public boolean checkCollision(Wall wall) 
	{

		int pacLeft 	= pacman.getX();
		int pacRight 	= pacman.getX() + pacman.getW();
		int pacTop 		= pacman.getY();
		int pacBottom 	= pacman.getY() + pacman.getH();

		int wallLeft 	= wall.getX();
		int wallRight 	= wall.getX() 	+ wall.getW();
		int wallTop 	= wall.getY();
		int wallBottom 	= wall.getY() 	+ wall.getH();

		if(pacRight < wallLeft) 
		{
			return false;
		}
		if(pacLeft > wallRight)
		{
			return false;
		}
		if(pacBottom < wallTop)
		{
			return false;
		}
		if(pacTop > wallBottom)
		{
			return false;
		}
		return true;
	}

	public int getPacmanY()
	{
		return pacman.getY();
	}


}