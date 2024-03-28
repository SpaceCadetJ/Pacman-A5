
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class Pacman {
    private int x, y, w, h, pacx, pacy;
    public static BufferedImage[] pacmanImages;
    private final int ImgDir = 3;
    private int currentImage = 0;
    private int direction = 2; 
    private double speed;

    public Pacman(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.pacx = pacx;
        this.pacy = pacy;
        this.w = w;
        this.h = h;
        this.speed = 0.5;

		pacmanImages = new BufferedImage[12];
        for(int i = 0; i < 12; i++) {
			try {
				String filename = "pacman" + (i + 1) + ".png";
				pacmanImages[i] = ImageIO.read(new File(filename));
				System.out.println("pacman image loaded: " + filename);
	
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}
		}
    }

    
    public void move(int dx, int dy)
    {
        x += dx;
        y += dy;
        if(x > Game.MAX_WINDOW_WIDTH) {
			x = 0;
		} else if(x < 0) {
			x = Game.MAX_WINDOW_WIDTH;
		}
    }

    public void fixPosition(Wall wall) {
        if(((getX() + getW()) >= wall.getX()) && (getPx() <= wall.getX())) {
            this.x = (wall.getX() - w);
        }
       
        if((getX() <= (wall.getX() + wall.getW())) && ((getPx() + getW()) >= wall.getX() + wall.getW())) {
            this.x = (wall.getX() + wall.getW());
        }

       
        if(((wall.getY() <= (getY() + getH())) && ((getY() + getH()) <= (wall.getY() + wall.getH())) && ((getPy() + wall.getH()) <= wall.getY()))) {
           this.y = (wall.getY() - getH());
        }

       
        if(getPy() >= (wall.getY() + wall.getH())) {
            this.y = (wall.getY() + wall.getH());
        }
    }

    public void PrevCoords(int px, int py)
    {
        this.pacx = x;
        this.pacy = y;
    }

    public void draw(Graphics g, int scrollY) {
       g.drawImage(pacmanImages[currentImage + direction * ImgDir], x, y - scrollY, w, h, null);
    }

    public void updateImage() {
        currentImage++;
        if(currentImage >= ImgDir) 
            currentImage = 0;
    }

    public int getX() {
        return x;
    }
    
    public int getPx() {
        return pacx;
    }

    public int getPy() {
        return pacy;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public double getSpeed() {
        return speed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setPx(int px) {
        this.pacx = px;
    }
    
    public void setPy(int py) {
        this.pacy = py;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Pacman (x,y) = (" + x + ", " + y + "), w=" + w + ", h=" + h + "\t speed = " + speed;
    }


    public void update() {
        
    }

}