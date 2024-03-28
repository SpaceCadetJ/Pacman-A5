

import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
	private Model model;
	private Controller controller;
	private View view;

	public static final int MAX_WORLD_HEIGHT = 700;
	public static final int MAX_WORLD_WIDTH = 500;
	public static final int MAX_WINDOW_HEIGHT = 500;
	public static final int MAX_WINDOW_WIDTH = 500;


	public Game()
	{
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model, this);
		controller.setView(view); 
		this.setTitle("A4 - Pacman Game");
		this.setSize(500, 500);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addKeyListener(controller);

		model.loadMap("map.json");
	}

	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
	}

	public void run()
	{
		while(true)
		{
			controller.update();
			model.update();
			view.repaint(); 
			Toolkit.getDefaultToolkit().sync(); 
			
			try
			{
				Thread.sleep(60);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
}
