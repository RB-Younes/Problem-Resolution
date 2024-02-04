import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarculaLaf;


////////////////////////////////////////////////////////////////////////////////-----------Fenetre Mot de passe indication------------///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class TP2GUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton START;
	private JButton One;
	private JButton Two;
	private JButton three;
	private JButton four;
	private JButton five;
	private JButton six;
	private JButton seven;
	private JButton eight;
	private ArrayList<obstacle> obstacles = new ArrayList<obstacle>();
	private String st;
	private int N;
	private  BufferedReader br = null;
	private boolean done= false;
	
	private int posX = 0;   
    private int posY = 0;
    /**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 */
	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		FlatDarculaLaf.install();	
		UIManager.setLookAndFeel(new FlatDarculaLaf() );
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TP2GUI frame = new TP2GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TP2GUI() {
		
// obstacles///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		obstacle O = new obstacle(1, 0, 0,27,89);
		obstacles.add(O);
		obstacle O1 = new obstacle(2, 0, 1,109,89);
		obstacles.add(O1);
		obstacle O2 = new obstacle(3, 0, 2,190,89);
		obstacles.add(O2);
		obstacle O3 = new obstacle(4, 1, 2,190,174);
		obstacles.add(O3);
		obstacle O4 = new obstacle(5, 2, 2,190,258);
		obstacles.add(O4);
		obstacle O6 = new obstacle(6, 2, 1,109,258);
		obstacles.add(O6);
		obstacle O7 = new obstacle(7, 2, 0,27,258);
		obstacles.add(O7);
		obstacle O8 = new obstacle(8, 1, 0,27,174);
		obstacles.add(O8);
		obstacle O9 = new obstacle(-1, 1, 1,109,174);
		obstacles.add(O9);
				
		setIconImage(Toolkit.getDefaultToolkit().getImage(TP2GUI.class.getResource("/Log_in_img/connection.png")));
//FAIRE bouger la fenetre////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		 addMouseListener(new MouseAdapter() {
	            @Override
	            //on recupere les coordonnées de la souris
	            public void mousePressed(MouseEvent e) {
	                posX = e.getX();    //Position X de la souris au clic
	                posY = e.getY();    //Position Y de la souris au clic
	            }
	        });
	         
	        addMouseMotionListener(new MouseMotionAdapter() {
	            // A chaque deplacement on recalcul le positionnement de la fenetre
	            @Override
	            public void mouseDragged(MouseEvent e) {
	                int depX = e.getX() - posX;
	                int depY = e.getY() - posY;
	                setLocation(getX()+depX, getY()+depY);
	            }
	        });
// initialisation///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		setUndecorated(true);
		setType(Type.POPUP);
		setResizable(false);
		//setIconImage(Toolkit.getDefaultToolkit().getImage(Indicationpass.class.getResource("/Passeindixation_img/question.png")));
		setTitle("TP2 RP");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 280, 417);
		setShape(new RoundRectangle2D.Double(0d, 0d, 280d, 417d, 25d, 25d));	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
// Exit bouton/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		JButton Exit_BTN = new JButton("");
		Exit_BTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Exit_BTN.setIcon(new ImageIcon(TP2GUI.class.getResource("/Log_in_img/Exit ML selected.png")));//changer les couleurs button
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				Exit_BTN.setIcon(new ImageIcon(TP2GUI.class.getResource("/Log_in_img/Exit ML.png")));//remetre le bouton de base
			}
		});
		Exit_BTN.setToolTipText("Exit");
		ButtonStyle(Exit_BTN);
		Exit_BTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		Exit_BTN.setIcon(new ImageIcon(TP2GUI.class.getResource("/Log_in_img/Exit ML.png")));
		Exit_BTN.setBounds(238, 11, 32, 32);
		Exit_BTN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(Exit_BTN);
		
		
		START = new JButton("");
		START.setToolTipText("START");
		START.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = new ImageIcon(TP2GUI.class.getResource("/Log_in_img/unknown.png"));
				int ClickedButton	=JOptionPane.showConfirmDialog(null, "Vouler Vous vraiment commencer l'execution ?", "Confirmation",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
				if(ClickedButton==JOptionPane.YES_OPTION)
					{
					String numbers = "";
					ArrayList<COO> coo = new ArrayList<COO>();
					COO O = new COO(0,0);
					boolean emptyValid = false;
					coo.add(O);
					O = new COO(1,0);
					coo.add(O);
					O = new COO(2,0);
					coo.add(O);
					O = new COO(0,1);
					coo.add(O);
					O = new COO(2,1);
					coo.add(O);
					O = new COO(0,2);
					coo.add(O);
					O = new COO(1,2);
					coo.add(O);
					O = new COO(2,2);
					coo.add(O);
					for (int i = 0; i < coo.size(); i++) {
						O = coo.get(i);
						for (int j = 0; j < obstacles.size(); j++) 
						{
							obstacle obs = obstacles.get(j);
							if(obs.getX()== O.getX() && obs.getY()==O.getY())
							{
								if (!numbers.equals("")) numbers=numbers+"-"+String.valueOf(obs.getNumber());
								else numbers=  String.valueOf(obs.getNumber());
							}
							if(obs.getNumber()==-1) {
								if(obs.getX()== 1 && obs.getY()==1)
								{
									emptyValid=true;
								}
							}
							
						}
					}
					
					if (emptyValid) {
						//script path
						 String path_script ="";
			             path_script = TP2GUI.class.getResource("/scripts/script.py").getPath();
			             path_script = path_script.substring(1, path_script.length());
			             ProcessBuilder pb = null;

						 pb = new ProcessBuilder("C:/Users/LATITUDE/AppData/Local/Microsoft/WindowsApps/python3.8.exe",path_script,"--numb",String.valueOf(numbers) ).inheritIO();
	                        @SuppressWarnings("unused")
							Process process = null;
							try {
								process = pb.start();
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							try {
		                         process.waitFor();
		                    } catch (InterruptedException e1) {
		                        // TODO Auto-generated catch block
		                        e1.printStackTrace();
		                    }
							
							 String path_file ="";
							 path_file = TP2GUI.class.getResource("/scripts/Path_result.txt").getPath();
							 path_file = path_file.substring(1, path_file.length());
							 System.out.println(path_file);
							 
					        File file = new File(path_file);
					 
							try {
								br = new BufferedReader(new FileReader(file));
									
									Thread t1 = new Thread(){
									    public void run(){
									    	while (true)
											{
									    		try {
													Thread.sleep(50);
												} catch (InterruptedException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												if (done) {
												 Move(N);
												 done=false;
												}
											}
												
									    }};
									    Thread t2 = new Thread(){
										    public void run(){
										    	try {
													while ((st = br.readLine()) != null)
													{
														N = Integer.valueOf(st.substring(5));
														done=true;
														Thread.sleep(1000);
													}
													JOptionPane.showMessageDialog(null,  "Execution terminé avec succés.","state" ,JOptionPane.PLAIN_MESSAGE, new ImageIcon(TP2GUI.class.getResource("/Log_in_img/check.png")));	

												} catch (IOException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												} catch (InterruptedException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
										    }};
										    t2.start();
											t1.start();
										
								
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

					}
					else {	JOptionPane.showMessageDialog(null,  "S'il vous plait veuillez la case centrale vide.","state" ,JOptionPane.PLAIN_MESSAGE, new ImageIcon(TP2GUI.class.getResource("/Log_in_img/error.png")));	

					}				
					}
					
			}
		});
		START.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				START.setIcon(new ImageIcon(TP2GUI.class.getResource("/Log_in_img/start selected.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				START.setIcon(new ImageIcon(TP2GUI.class.getResource("/Log_in_img/start.png")));
			}
		});
		START.setIcon(new ImageIcon(TP2GUI.class.getResource("/Log_in_img/start.png")));
		START.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		START.setBounds(77, 333, 128, 73);
		ButtonStyle(START);
		contentPane.add(START);
		
		One = new JButton("");
		One.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int[] cooN = new int[4];
				cooN =CooNumber(1);
				
				int[] cooEmpty = new int[4];
				cooEmpty =CooEmpty();
				
			    obstacle obsN = new obstacle(1, cooN[0], cooN[1], cooN[2], cooN[3]);
			    obstacle obsEmpty = new obstacle(-1, cooEmpty[0], cooEmpty[1], cooEmpty[2], cooEmpty[3]);
			    
			    
			    if((cooN[0]+1 ==cooEmpty[0] && cooN[1]==cooEmpty[1]) || (cooN[0]-1 ==cooEmpty[0]  && cooN[1]==cooEmpty[1]) || (cooN[1]+1 ==cooEmpty[1]  && cooN[0]==cooEmpty[0]) || (cooN[1]-1 ==cooEmpty[1] && cooN[0]==cooEmpty[0]) ) {
			    	obstacles.remove(indexobstacle(obsN));
			    	obstacles.remove(indexobstacle(obsEmpty));
			    	
			    	obsN = new obstacle(1, cooEmpty[0], cooEmpty[1], cooEmpty[2], cooEmpty[3]);
			    	obsEmpty = new obstacle(-1, cooN[0], cooN[1], cooN[2], cooN[3]);
			    	
			    	obstacles.add(obsN);
			    	obstacles.add(obsEmpty);
			    	
			    	One.setBounds(cooEmpty[2], cooEmpty[3],  64, 64);
			    }

			}
		});
		One.setIcon(new ImageIcon(TP2GUI.class.getResource("/numbers/one.png")));
		One.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		One.setToolTipText("1");
		One.setBounds(27, 89,  64, 64);
		contentPane.add(One);
		
		Two = new JButton("");
		Two.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int[] cooN = new int[4];
				cooN =CooNumber(2);
				
				int[] cooEmpty = new int[4];
				cooEmpty =CooEmpty();
				
			    obstacle obsN = new obstacle(2, cooN[0], cooN[1], cooN[2], cooN[3]);
			    obstacle obsEmpty = new obstacle(-1, cooEmpty[0], cooEmpty[1], cooEmpty[2], cooEmpty[3]);
			    
			    if((cooN[0]+1 ==cooEmpty[0] && cooN[1]==cooEmpty[1]) || (cooN[0]-1 ==cooEmpty[0]  && cooN[1]==cooEmpty[1]) || (cooN[1]+1 ==cooEmpty[1]  && cooN[0]==cooEmpty[0]) || (cooN[1]-1 ==cooEmpty[1] && cooN[0]==cooEmpty[0]) ) {
			    	obstacles.remove(indexobstacle(obsN));
			    	obstacles.remove(indexobstacle(obsEmpty));
			    	
			    	obsN = new obstacle(2, cooEmpty[0], cooEmpty[1], cooEmpty[2], cooEmpty[3]);
			    	obsEmpty = new obstacle(-1, cooN[0], cooN[1], cooN[2], cooN[3]);
			    	
			    	obstacles.add(obsN);
			    	obstacles.add(obsEmpty);
			    	
			    	Two.setBounds(cooEmpty[2], cooEmpty[3],  64, 64);
			    }
			}
		});
		Two.setIcon(new ImageIcon(TP2GUI.class.getResource("/numbers/two.png")));
		Two.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Two.setToolTipText("2");
		Two.setBounds(109, 89,  64, 64);
		contentPane.add(Two);
		
		three = new JButton("");
		three.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int[] cooN = new int[4];
				cooN =CooNumber(3);
				
				int[] cooEmpty = new int[4];
				cooEmpty =CooEmpty();
				
			    obstacle obsN = new obstacle(3, cooN[0], cooN[1], cooN[2], cooN[3]);
			    obstacle obsEmpty = new obstacle(-1, cooEmpty[0], cooEmpty[1], cooEmpty[2], cooEmpty[3]);
			    
			    if((cooN[0]+1 ==cooEmpty[0] && cooN[1]==cooEmpty[1]) || (cooN[0]-1 ==cooEmpty[0]  && cooN[1]==cooEmpty[1]) || (cooN[1]+1 ==cooEmpty[1]  && cooN[0]==cooEmpty[0]) || (cooN[1]-1 ==cooEmpty[1] && cooN[0]==cooEmpty[0]) ) {
			    	obstacles.remove(indexobstacle(obsN));
			    	obstacles.remove(indexobstacle(obsEmpty));
			    	
			    	obsN = new obstacle(3, cooEmpty[0], cooEmpty[1], cooEmpty[2], cooEmpty[3]);
			    	obsEmpty = new obstacle(-1, cooN[0], cooN[1], cooN[2], cooN[3]);
			    	
			    	obstacles.add(obsN);
			    	obstacles.add(obsEmpty);
			    	
			    	three.setBounds(cooEmpty[2], cooEmpty[3],  64, 64);
			    }

			}
		});
		three.setIcon(new ImageIcon(TP2GUI.class.getResource("/numbers/three.png")));
		three.setToolTipText("3");
		three.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		three.setBounds(190, 89,  64, 64);
		contentPane.add(three);
		
		
		four = new JButton("");
		four.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] cooN = new int[4];
				cooN =CooNumber(4);
				
				int[] cooEmpty = new int[4];
				cooEmpty =CooEmpty();
				
			    obstacle obsN = new obstacle(4, cooN[0], cooN[1], cooN[2], cooN[3]);
			    obstacle obsEmpty = new obstacle(-1, cooEmpty[0], cooEmpty[1], cooEmpty[2], cooEmpty[3]);
			    
			    if((cooN[0]+1 ==cooEmpty[0] && cooN[1]==cooEmpty[1]) || (cooN[0]-1 ==cooEmpty[0]  && cooN[1]==cooEmpty[1]) || (cooN[1]+1 ==cooEmpty[1]  && cooN[0]==cooEmpty[0]) || (cooN[1]-1 ==cooEmpty[1] && cooN[0]==cooEmpty[0]) ) {
			    	obstacles.remove(indexobstacle(obsN));
			    	obstacles.remove(indexobstacle(obsEmpty));
			    	
			    	obsN = new obstacle(4, cooEmpty[0], cooEmpty[1], cooEmpty[2], cooEmpty[3]);
			    	obsEmpty = new obstacle(-1, cooN[0], cooN[1], cooN[2], cooN[3]);
			    	
			    	obstacles.add(obsN);
			    	obstacles.add(obsEmpty);
			    	
			    	four.setBounds(cooEmpty[2], cooEmpty[3],  64, 64);
			    }
			}
		});
		four.setIcon(new ImageIcon(TP2GUI.class.getResource("/numbers/four.png")));
		four.setToolTipText("4");
		four.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		four.setBounds(190, 174, 64, 64);
		contentPane.add(four);
		
		five = new JButton("");
		five.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] cooN = new int[4];
				cooN =CooNumber(5);
				
				int[] cooEmpty = new int[4];
				cooEmpty =CooEmpty();
				
			    obstacle obsN = new obstacle(5, cooN[0], cooN[1], cooN[2], cooN[3]);
			    obstacle obsEmpty = new obstacle(-1, cooEmpty[0], cooEmpty[1], cooEmpty[2], cooEmpty[3]);
			    
			    if((cooN[0]+1 ==cooEmpty[0] && cooN[1]==cooEmpty[1]) || (cooN[0]-1 ==cooEmpty[0]  && cooN[1]==cooEmpty[1]) || (cooN[1]+1 ==cooEmpty[1]  && cooN[0]==cooEmpty[0]) || (cooN[1]-1 ==cooEmpty[1] && cooN[0]==cooEmpty[0]) ) {
			    	obstacles.remove(indexobstacle(obsN));
			    	obstacles.remove(indexobstacle(obsEmpty));
			    	
			    	obsN = new obstacle(5, cooEmpty[0], cooEmpty[1], cooEmpty[2], cooEmpty[3]);
			    	obsEmpty = new obstacle(-1, cooN[0], cooN[1], cooN[2], cooN[3]);
			    	
			    	obstacles.add(obsN);
			    	obstacles.add(obsEmpty);
			    	
			    	five.setBounds(cooEmpty[2], cooEmpty[3],  64, 64);
			    }
				
			}
		});
		five.setIcon(new ImageIcon(TP2GUI.class.getResource("/numbers/five.png")));
		five.setToolTipText("5");
		five.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		five.setBounds(190, 258,  64, 64);
		contentPane.add(five);
		
		six = new JButton("");
		six.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] cooN = new int[4];
				cooN =CooNumber(6);
				
				int[] cooEmpty = new int[4];
				cooEmpty =CooEmpty();
				
			    obstacle obsN = new obstacle(6, cooN[0], cooN[1], cooN[2], cooN[3]);
			    obstacle obsEmpty = new obstacle(-1, cooEmpty[0], cooEmpty[1], cooEmpty[2], cooEmpty[3]);
			    
			    if((cooN[0]+1 ==cooEmpty[0] && cooN[1]==cooEmpty[1]) || (cooN[0]-1 ==cooEmpty[0]  && cooN[1]==cooEmpty[1]) || (cooN[1]+1 ==cooEmpty[1]  && cooN[0]==cooEmpty[0]) || (cooN[1]-1 ==cooEmpty[1] && cooN[0]==cooEmpty[0]) ) {
			    	obstacles.remove(indexobstacle(obsN));
			    	obstacles.remove(indexobstacle(obsEmpty));
			    	
			    	obsN = new obstacle(6, cooEmpty[0], cooEmpty[1], cooEmpty[2], cooEmpty[3]);
			    	obsEmpty = new obstacle(-1, cooN[0], cooN[1], cooN[2], cooN[3]);
			    	
			    	obstacles.add(obsN);
			    	obstacles.add(obsEmpty);
			    	
			    	six.setBounds(cooEmpty[2], cooEmpty[3],  64, 64);
			    }
			}
		});
		six.setIcon(new ImageIcon(TP2GUI.class.getResource("/numbers/six.png")));
		six.setToolTipText("6");
		six.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		six.setBounds(109, 258, 64, 64);
		contentPane.add(six);
		
		seven = new JButton("");
		seven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] cooN = new int[4];
				cooN =CooNumber(7);
				
				int[] cooEmpty = new int[4];
				cooEmpty =CooEmpty();
				
			    obstacle obsN = new obstacle(7, cooN[0], cooN[1], cooN[2], cooN[3]);
			    obstacle obsEmpty = new obstacle(-1, cooEmpty[0], cooEmpty[1], cooEmpty[2], cooEmpty[3]);
			    
			    if((cooN[0]+1 ==cooEmpty[0] && cooN[1]==cooEmpty[1]) || (cooN[0]-1 ==cooEmpty[0]  && cooN[1]==cooEmpty[1]) || (cooN[1]+1 ==cooEmpty[1]  && cooN[0]==cooEmpty[0]) || (cooN[1]-1 ==cooEmpty[1] && cooN[0]==cooEmpty[0]) ) {
			    	obstacles.remove(indexobstacle(obsN));
			    	obstacles.remove(indexobstacle(obsEmpty));
			    	
			    	obsN = new obstacle(7, cooEmpty[0], cooEmpty[1], cooEmpty[2], cooEmpty[3]);
			    	obsEmpty = new obstacle(-1, cooN[0], cooN[1], cooN[2], cooN[3]);
			    	
			    	obstacles.add(obsN);
			    	obstacles.add(obsEmpty);
			    	
			    	seven.setBounds(cooEmpty[2], cooEmpty[3],  64, 64);
			    }
			}
		});
		seven.setIcon(new ImageIcon(TP2GUI.class.getResource("/numbers/seven.png")));
		seven.setToolTipText("7");
		seven.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		seven.setBounds(27, 258,  64, 64);
		contentPane.add(seven);
		
		
		eight = new JButton("");
		eight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] cooN = new int[4];
				cooN =CooNumber(8);
				
				int[] cooEmpty = new int[4];
				cooEmpty =CooEmpty();
				
			    obstacle obsN = new obstacle(8, cooN[0], cooN[1], cooN[2], cooN[3]);
			    obstacle obsEmpty = new obstacle(-1, cooEmpty[0], cooEmpty[1], cooEmpty[2], cooEmpty[3]);
			    
			    if((cooN[0]+1 ==cooEmpty[0] && cooN[1]==cooEmpty[1]) || (cooN[0]-1 ==cooEmpty[0]  && cooN[1]==cooEmpty[1]) || (cooN[1]+1 ==cooEmpty[1]  && cooN[0]==cooEmpty[0]) || (cooN[1]-1 ==cooEmpty[1] && cooN[0]==cooEmpty[0]) ) {
			    	obstacles.remove(indexobstacle(obsN));
			    	obstacles.remove(indexobstacle(obsEmpty));
			    	
			    	obsN = new obstacle(8, cooEmpty[0], cooEmpty[1], cooEmpty[2], cooEmpty[3]);
			    	obsEmpty = new obstacle(-1, cooN[0], cooN[1], cooN[2], cooN[3]);
			    	
			    	obstacles.add(obsN);
			    	obstacles.add(obsEmpty);
			    	
			    	eight.setBounds(cooEmpty[2], cooEmpty[3],  64, 64);
			    }
			}
		});
		eight.setIcon(new ImageIcon(TP2GUI.class.getResource("/numbers/eight.png")));
		eight.setToolTipText("8");
		eight.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		eight.setBounds(27, 174,  64, 64);
		contentPane.add(eight);
		
		
		
//le background////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JLabel lblBG = new JLabel("BG");
		lblBG.setIcon(new ImageIcon(TP2GUI.class.getResource("/Log_in_img/Taquin.png")));
		lblBG.setBounds(0, 0, 280, 417);
		contentPane.add(lblBG);
		
		
	}
//methode du style des buttons/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 private void ButtonStyle(JButton btn) {
	//enlever les bordures des btn 
	 btn.setContentAreaFilled(false);
}

//CooEmpty///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	 
	private int[] CooEmpty() {
		int[] coordinates = new int[4];
		coordinates[0] = -1;
	    coordinates[1] = -1;
	    coordinates[2] = -1;
	    coordinates[3] = -1;
		
		for (int i = 0; i < obstacles.size(); i++) 
		{
			obstacle obs = obstacles.get(i);
			if(obs.getNumber()== -1)
			{
				coordinates[0] = obs.getX();
			    coordinates[1] = obs.getY();
			    coordinates[2] = obs.getBx();
			    coordinates[3] = obs.getBy();
			}
		}
		return coordinates;
	}
//CooNumber///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	 
		private int[] CooNumber(int N) {
			int[] coordinates = new int[4];
			coordinates[0] = -1;
		    coordinates[1] = -1;
		    coordinates[2] = -1;
		    coordinates[3] = -1;
			
			for (int i = 0; i < obstacles.size(); i++) 
			{
				obstacle obs = obstacles.get(i);
				if(obs.getNumber()== N)
				{
					coordinates[0] = obs.getX();
				    coordinates[1] = obs.getY();
				    coordinates[2] = obs.getBx();
				    coordinates[3] = obs.getBy();
				}
			}
			return coordinates;
		}
//indexobstacle///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	 
 
		private int indexobstacle(obstacle o) {
			
			int i=0;
			for (i = 0; i < obstacles.size(); i++) 
			{
				obstacle obs = obstacles.get(i);
				if(obs.getNumber()==o.getNumber() )
				{
					return i;
				}
			}
			return i;
		}
//indexobstacle///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	 
		 
	private void Move(int N) {
		
		
		int[] cooN = new int[4];
		cooN =CooNumber(N);
		
		int[] cooEmpty = new int[4];
		cooEmpty =CooEmpty();
		
	    obstacle obsN = new obstacle(N, cooN[0], cooN[1], cooN[2], cooN[3]);
	    obstacle obsEmpty = new obstacle(-1, cooEmpty[0], cooEmpty[1], cooEmpty[2], cooEmpty[3]);
		   	obstacles.remove(indexobstacle(obsN));
	    	obstacles.remove(indexobstacle(obsEmpty));
	    	
	    	obsN = new obstacle(N, cooEmpty[0], cooEmpty[1], cooEmpty[2], cooEmpty[3]);
	    	obsEmpty = new obstacle(-1, cooN[0], cooN[1], cooN[2], cooN[3]);
	    	
	    	obstacles.add(obsN);
	    	obstacles.add(obsEmpty);
	    	
	    	switch(N){
	    	   
	        case 1: 
	        	One.setBounds(cooEmpty[2], cooEmpty[3],  64, 64);
	            break;
	        case 2:
	        	Two.setBounds(cooEmpty[2], cooEmpty[3],  64, 64);
	            break;
	    
	        case 3:
	        	three.setBounds(cooEmpty[2], cooEmpty[3],  64, 64);
	            break;
	        case 4: 
	        	four.setBounds(cooEmpty[2], cooEmpty[3],  64, 64);
	            break;
	    
	        case 5:
	        	five.setBounds(cooEmpty[2], cooEmpty[3],  64, 64);
	            break;
	    
	        case 6:
	        	six.setBounds(cooEmpty[2], cooEmpty[3],  64, 64);
	            break;
	        case 7: 
	        	seven.setBounds(cooEmpty[2], cooEmpty[3],  64, 64);
	            break;
	        case 8:
	        	eight.setBounds(cooEmpty[2], cooEmpty[3],  64, 64);
	            break;
	    
	    	}
	    	
	    }
					
		
}
