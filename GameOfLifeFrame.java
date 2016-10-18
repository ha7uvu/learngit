import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;

public class GameOfLifeFrame extends JFrame {


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOfLifeFrame frame = new GameOfLifeFrame();
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
	public GameOfLifeFrame() {
		setTitle("Game Of Life");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 556);
		
		
		GPanel panel=new GPanel();
		  this.getContentPane().add(panel);
		  panel.InitGameMap();
		  panel.setLayout(null);
		  panel.test2();
	}

class GPanel extends JPanel
{
	 GameLogic gamel=new GameLogic();
	 
	 //��ʼ����Ϸ��ͼ
	 public void InitGameMap(){
		 gamel.InitMap();
	 }
	 
	//����������Ϸ��ͼ
 public void paintComponent(Graphics graph)
 {
	 graph.setColor(Color.WHITE);
	 graph.fillRect(0,0,this.getWidth(),this.getHeight());
	 
	 	 int w;//������
	  int h;//������
	  int nGameMap[][]=new int [52][52];
	  nGameMap=gamel.getGameMap();
	 for(int i=0;i<50;i++){
		    h=0+i*10;//���л�����ÿ������ı߳�Ϊ10
		    w=0; //��
		    for(int j=0;j<50;j++){
		          if(nGameMap[i+1][j+1]==1){
		        	  graph.drawRect(w, h, 10, 10); 
		        	  graph.setColor(Color.BLACK);
		        	  graph.fillRect(w,h,10,10);
		        	 // System.out.println(i+1);
		          }
		          else{ 
		        	  graph.setColor(Color.BLACK);
		        	  graph.drawRect(w, h, 10, 10);  
		          }
		          w=w+10;
		    	}//forj
		  }//fori
	 //���µ�ͼ
	
	 }//paintComponet()
  
	 public void test2() {  
	        new Timer().schedule(new TimerTask() {  
	            @Override  
	            public void run() {  
	                // TODO Auto-generated method stub  
	            	//System.out.println("!");
	            	gamel.UpdateGameMap();
	            	repaint();
	            }  
	        }, 100, 100);  
	    }  
}
	
}//class

class GameLogic{
	private int GameMap[][]=new int [52][52];//��Ϸ��ͼ
	private int TempMap[][]=new int [52][52];//ˢ�º����Ϸ��ͼ
	
	//��ʼ��GameMap����
	void InitMap(){
		
		/*	*/
		//�������
		for(int i=0;i<=51;i++)
		{
			GameMap[0][i]=0;
			GameMap[i][0]=0;
			GameMap[51][i]=0;
			GameMap[i][51]=0;
		}
		//���ô��״̬
		
				for(int i=1;i<=50;i++)
			{
					for(int j=1;j<=50;j++)
					{
						//�������GameMap
						int rand= (int)( Math.random()+0.5);
						GameMap[i][j]=rand;
					}
				}
		
		/*
		 * //���ù̶�GameMap���飬������
				for(int i=0;i<=51;i++){
					for(int j=0;j<=51;j++){
						if(i<24||i>27)
							GameMap[i][j]=0;
						else{
							if(j<24||j>27)
								GameMap[i][j]=0;
							else
								GameMap[i][j]=1;
							}//else
					}//forj
				}//fori
		//����GameMap
		for(int i=0;i<=51;i++){
			for(int j=0;j<=51;j++)
				System.out.print(GameMap[i][j]);
			System.out.println();
		}
		*/
		/*
		//����
		for(int i=0;i<=51;i++)
			for(int j=0;j<=51;j++){
				if(i==25){
					if(j>=20&&j<=22)
						GameMap[i][j]=1;
				}
					
				else 
					GameMap[i][j]=0;
			}*/
			/*//blinker
		for(int i=0;i<=51;i++)
			for(int j=0;j<=51;j++){
				if(i==25){
					if(j>=20&&j<=39)
						GameMap[i][j]=1;
				}	
				else 
					GameMap[i][j]=0;
			}
		*/
		
						/*
						 * //����tumbler
		for(int i=0;i<=51;i++)
			for(int j=0;j<=51;j++){
				if(i==25){
					if((j>=10&&j<=18)||(j>=23&&j<=32)||(j>=38&&j<=40))
						GameMap[i][j]=1;
				}	
				else 
					GameMap[i][j]=0;
			}
		*/
			
	}
	
	//���㵥λ�������ܵĻ�ϸ����
		int GetNeighborCount(int row, int col){
			int ret=0;
			for(int r=row-1;r<=row+1;r++)
				for(int c=col-1;c<=col+1;c++)
					if(GameMap[r][c]==1)
						ret++;
			if(GameMap[row][col]==1)
				ret--;
			return ret;		
		}
		
		//�����µ�ͼ
		void InitNewMap(){

			for(int i=0;i<=51;i++)
			{
				TempMap[0][i]=0;
				TempMap[i][0]=0;
				TempMap[51][i]=0;
				TempMap[i][51]=0;
			}
			
			for(int i=1;i<=50;i++)
				for(int j=1;j<=50;j++)
				{
					int LiveCnt=0;
					LiveCnt=GetNeighborCount(i,j);
					if((LiveCnt<2)||(LiveCnt>3))
						TempMap[i][j]=0;
					else if(LiveCnt==3)
						TempMap[i][j]=1;
					else
						TempMap[i][j]=GameMap[i][j];
				}
		}
		
		//���µ�ͼ
		void UpdateGameMap()
		{
			InitNewMap();	
			for(int i=1;i<=50;i++)
			{
				for(int j=1;j<=50;j++)
				{
					GameMap[i][j]=TempMap[i][j];
				}
			}
			
		}
		
		//��ȡGameMap
		int[][] getGameMap(){
			return GameMap;
		}
		
}
