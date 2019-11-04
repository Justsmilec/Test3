
package game;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class playballgame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Timer t = new Timer(10,this);
	//Timer tm = new Timer(10,this);
	
	private JLabel label;
	private JLabel rec1,rec2;
	private JLabel win1,win2;
	private JLabel clkmessage;
	private JLabel pause;
	
	private Shape circ;
	private int x = 100;
	private int y = 125;
	private int myx = 2;
	private int myy = 2;
	private int x_rec1 = 15;
	private int y_rec1 = 138;
	private int x_rec2 = 361;
	private int y_rec2 = 138;
	private int pl1_win = 0;
	private int pl2_win = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					playballgame window = new playballgame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public playballgame() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setBounds(0,0,400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		label = new JLabel();
		label.setBounds(10, 20, 366, 330);
		
		Border blackline = BorderFactory.createLineBorder(Color.black);
		getContentPane().setLayout(null);
		label.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		label.setBackground(Color.BLUE);
		getContentPane().add(label);
		
		
		rec1 = new JLabel();
		getContentPane().add(rec1);
		rec1.setBounds(15, 138, 10, 100);
		rec1.setLayout(null);
		rec1.setOpaque(true);
		rec1.setForeground(Color.BLUE);
		rec1.setBackground(Color.BLUE);
		rec1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		
		
		
		rec2 = new JLabel();
		getContentPane().add(rec2);
		rec2.setBounds(361, 138, 10, 100);
		rec2.setLayout(null);
		rec2.setOpaque(true);
		rec2.setForeground(Color.BLUE);
		rec2.setBackground(Color.BLUE);
		
		rec2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		addrec();
		startball();
		pause();
		showwinner();
		
	}
	
	
	public void addrec()
	{
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_UP)
				{
					if(y_rec1 > 25)
					{
					y_rec1 = y_rec1 - 6;
					rec1.setBounds(15,y_rec1, 10, 100);
					getContentPane().add(rec1);
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN)
				{
					if(y_rec1 < 243)
					{
					y_rec1 = y_rec1 + 6;
					rec1.setBounds(15,y_rec1, 10, 100);
					getContentPane().add(rec1);
					}
				}
			}
		});
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_A)
				{
					if(y_rec2 > 25)
					{
					y_rec2 = y_rec2 - 6;
					rec2.setBounds(361,y_rec2, 10, 100);
					getContentPane().add(rec2);
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_Z)
				{
					if(y_rec2 < 243)
					{
					y_rec2 = y_rec2 + 6;
					rec2.setBounds(361,y_rec2, 10, 100);
					getContentPane().add(rec2);
					}
				}
			}
		});
		repaint();
		
	}
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		circ = new Ellipse2D.Double(x,y,20,20);
		
		g2.setColor(Color.red);
		g2.fill(circ);
		g2.draw(circ);	
		
	}
	
	public void showwinner()
	{
		JLabel player1 = new JLabel("Player 1 ");
		player1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		player1.setBounds(120, 60, 80, 25);
		getContentPane().add(player1);
		
		win1 = new JLabel("" +pl1_win);
		win1.setBounds(140, 90, 40, 30);
		getContentPane().add(win1);
		
		
		JLabel player2 = new JLabel("Player 2 ");
		player2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		player2.setBounds(220, 60, 80, 25);
		getContentPane().add(player2);
		win2 = new JLabel("" +pl2_win);
		win2.setBounds(240, 90, 40, 30);
		getContentPane().add(win2);
		
		
	}
	
	//Create a function to stop when one player win
	
	public void startball()
	{
		//Add a label 
		clkmessage = new JLabel("Press Click");
		clkmessage.setBounds(160, 200, 100, 25);
		getContentPane().add(clkmessage);
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getSource() != pause)
				{
					clkmessage.setText("Press Click");
					clkmessage.setText("");
					t.start();
				}
				
				
				
			}
		});
	}
	
	public void pause()
	{
		pause = new JLabel("PAUSE");
		pause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource() == pause)
				{
					t.stop();
					//repaint();
					
				}
			}
		});
		pause.setBounds(170, 30, 60, 30);
		getContentPane().add(pause);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(x < 40 && (y > y_rec1 - 17 && y < y_rec1 + 121))
		{
			myx = - myx;
		}
		if(x > 341 && (y > y_rec2 - 17 && y < y_rec2  + 121))
		{
			myx = - myx;
		}
		if(x > 346)
		{
			JLabel mylb = new JLabel("Player 1 Win");
			mylb.setBounds(150, 150, 120, 40);
			label.add(mylb);
			pl1_win ++ ;
			win1.setText("" +pl1_win);
			//stopball();
			t.stop();
			clkmessage.setText("Press Click");
			if(e.getSource() != pause)
			{
				x = 150;
				y = 150;
				mylb.setText("");
				y_rec1 = 138;
				y_rec2 = 138;
				repaint();
				rec1.setBounds(15,y_rec1, 10, 100);
				getContentPane().add(rec1);
				rec2.setBounds(361,y_rec2, 10, 100);
				getContentPane().add(rec2);
			}
			//showwinner();
		}
		if(x < 19 )
		{
			JLabel mylb = new JLabel("Player 2 Win");
			mylb.setBounds(150, 150, 120, 40);
			label.add(mylb);
			pl2_win ++ ;
			win2.setText("" +pl2_win);
			//stopball();
			t.stop();
			clkmessage.setText("Press Click");
			if(e.getSource() != pause)
			{
				x = 150;
				y = 150;
				mylb.setText("");
				y_rec1 = 138;
				y_rec2 = 138;
				repaint();
				rec1.setBounds(15,y_rec1, 10, 100);
				getContentPane().add(rec1);
				rec2.setBounds(361,y_rec2, 10, 100);
				getContentPane().add(rec2);
			}
			//showwinner();
		}
		if(y < 50 || y > 360)
		{
			myy = - myy;
		}
		x = x + myx;
		y = y + myy; 
		
		repaint();
			
	}

}
