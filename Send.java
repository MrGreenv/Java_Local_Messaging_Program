import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
class send{
public String m,c;
static boolean flag=true,c_f=false;
	public static void main(String varun[])
	{	
		send ob=new send();
		JFrame f=new JFrame();
		JButton b=new JButton("Send");
		JTextArea msg=new JTextArea("Write your msg here!");
		msg.setLineWrap(true);
		msg.setWrapStyleWord(true);
		JTextArea old=new JTextArea();
		old.setLineWrap(true);
		old.setEditable(false);
		b.setBounds(300,325,80,30);
		msg.setBounds(0,327,290,30);
		old.setBounds(0,0,382,325);
		f.add(old);
		f.add(b);
		f.add(msg);
		f.setTitle("send");
		f.setResizable(false);
		f.setLayout(null);
		f.setSize(400,400);
		f.setVisible(true);
		msg.addMouseListener(new MouseAdapter()
		{
		  public void mouseClicked(MouseEvent e)
		   {
		    if(flag==true)
		    {
		     msg.setText("");
		     flag=false;
		    }
		   }
		});
		b.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		   {
			
			ob.m=msg.getText();
			old.setText(old.getText()+"\nME: "+ob.m);
			msg.setText("");
			try{
			ServerSocket ss=new ServerSocket(6666);
			Socket s=ss.accept();
			DataOutputStream out=new DataOutputStream(s.getOutputStream());
			out.writeUTF(ob.m);
			out.flush();
			}catch (Exception w){}
		   }
		});
		f.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e){
		System.out.println("Exiting");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		});
		while(true){
			try
			{
			ServerSocket ss=new ServerSocket(6666);
			Socket s=ss.accept();
			DataInputStream in=new DataInputStream(s.getInputStream());
			ob.c=in.readUTF();
			old.setText(old.getText()+"\nCLIENT: "+ob.c);
			ss.close();
			}catch(Exception e){}
			}
}
}