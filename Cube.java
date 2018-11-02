import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Cube
{
	private static CubeDisplay cd1;
	private static int count=0;
	public static int move(int x,int y,int z,int limit)
	{
	//	try{new BufferedReader(new InputStreamReader(System.in)).readLine();}catch(Exception e){}
		cd1.move(x,y,z);
	if(x==limit && y==limit && z==limit)
	{
		count++;
		cd1.showMessage("Hit "+count);
		return 1;
	}
	else
	{
	int ct=0;
	if(x<limit)
	ct+=move(x+1,y,z,limit);
	if(y<limit)
	ct+=move(x,y+1,z,limit);
	if(z<limit)
	ct+=move(x,y,z+1,limit);
cd1.move(x,y,z);
	return ct;
}
	}
	public static void main(String args[])throws IOException
	{
		int i1=Integer.parseInt(args[0]);
		int sp=Integer.parseInt(args[1]);
		cd1=new CubeDisplay();
		cd1.setSpeed(sp);
		System.out.println(move(0,0,0,i1));
	}
};
class Point
{
public int x,y,z;
public Point(int x,int y,int z)
{
this.x=x;
this.y=y;
this.z=z;
}
};
class Polygon
{
ArrayList<Point> pts=new ArrayList<Point>();
private int len=10;
private int sx,sy,sz;
public Polygon(int sx,int sy,int sz)
{
	this.sx=sx;
	this.sy=sy;
	this.sz=sz;
	pts.add(new Point(sx,sy,sz));
}
public void setLength(int l)
{
	len=l;
}
public int getLength()
{
	return len;
}
public void addPoint(int x,int y,int z)
{
	x=sx+(x*len);
	y=sy+(y*len);
	z=sz+(z*len);
	//	System.out.println(x+" "+y+" "+z);
pts.add(new Point(x,y,z));
}
public ListIterator getIterator()
{
	if(pts.isEmpty())
		return null;
	else
	return pts.listIterator(0);
}
};
class CubeDisplay
{
private JFrame f;
private Polygon pl1;
private String msg;
private JPanel p1=new JPanel()
{
public void paint(Graphics g)
{
g.setColor(Color.white);
g.fillRect(0,0,getWidth(),getHeight());
	int rr=(int)(Math.random()*190)+10;
	int gg=(int)(Math.random()*190)+10;
	int bb=(int)(Math.random()*190)+10;
g.setColor(new Color(rr,gg,bb));
ListIterator li1=null;
try{
li1=pl1.getIterator();
}
catch(NullPointerException npexcepn){}
if(li1==null)
return;
int px=200,py=200,pz=200;
int len=pl1.getLength();
int zz=200;
boolean zzset=false;
while(li1.hasNext())
{
	Point p1=(Point)li1.next();
	if(!zzset)
	{
		zzset=true;
		zz=p1.z;
	}
	if(p1.x<px)
		px=p1.x;
	if(p1.y<py)
		py=p1.y;
	if(p1.z<pz)
		pz=p1.z;
	if(pz==p1.z)
	{
g.drawLine(px+(p1.z-zz)/2,py+(p1.z-zz)/2,p1.x+(p1.z-zz)/2,p1.y+(p1.z-zz)/2);
}
else
{
	g.drawLine(px+(pz-zz)/2,py+(pz-zz)/2,p1.x+(p1.z-zz)/2,p1.y+(p1.z-zz)/2);
}
px=p1.x;
py=p1.y;
pz=p1.z;
}
g.fillRect(px+(pz-zz)/2-4,py+(pz-zz)/2-4,8,8);
if(msg!=null)
{
	g.setFont(new Font("Default",Font.PLAIN,40));
g.drawString(msg,400,400);
}
}
};
public CubeDisplay()
{
	JWindow f1=new JWindow();
	f1.add(p1);
	f1.setVisible(true);
	GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(f1);
	p1.addMouseListener(new MouseAdapter()
	{
public void mouseClicked(MouseEvent e)
{
	System.exit(0);
}
	});
}
private int speed=100;
public void setSpeed(int sp)
{
speed=sp;
}
public void move(int x,int y,int z)
{
if(pl1==null)
{
	pl1=new Polygon(x+200,y+200,z+200);
	pl1.setLength(40);
}
pl1.addPoint(x,y,z);
p1.repaint();
try
{
Thread.sleep(speed);
}
catch(InterruptedException ie){}
}
public void showMessage(String msg)
{
this.msg=msg;
}
};
