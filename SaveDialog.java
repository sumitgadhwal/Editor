	
		
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

////Save Dialog		
		
		
		
		
public class SaveDialog extends Frame implements ActionListener , WindowListener
{

	Frame f,fparent;
	Button bs,bns,bclose;
	String str;
	File  file;
	public SaveDialog(String str1,File file1,Frame f1)
	{
		f=new Frame();
		f.setSize(300,100);
		f.setVisible(true);
		f.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		f.addWindowListener(this);
		fparent=f1;
		str=str1;
		file=file1;
		
		bs=new Button("Save");
		bns=new Button("Don't Save");
		bclose=new Button("Close");
		
		bs.addActionListener(this);
		bns.addActionListener(this);
		bclose.addActionListener(this);
		
		f.add(bs);
		f.add(bns);
		f.add(bclose);
		
	}
	
	
		
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==bs)
		{	
		
			try
			{		
				if((file.getName()).equals("newfile"))
				{
					
					
					
					FileDialog fd=new FileDialog(f,"choose a file to open",FileDialog.SAVE);
					 
					 
					 fd.setVisible(true);
					//System.out.println(fd.getFile()+ "   "+fd.getDirectory());
					File new_file=new File(fd.getDirectory()+fd.getFile());
					new_file.createNewFile();
					FileOutputStream fos=new FileOutputStream(new_file);
					//String str="";
					//str=t.getText();
					int i=0;
					while(i<str.length())
					{
						fos.write(str.charAt(i));
						i++;
					}
					//str1=str;
					file.delete();
					file=new_file;
					fos.close();
					f.setVisible(false);
					f.dispose();
					fparent.setVisible(false);
					fparent.dispose();
				
					
				}
				else
				
				{
					FileOutputStream fos=new FileOutputStream(file);
					int i=0;
					while(i<str.length())
					{
						fos.write(str.charAt(i));
						i++;
					}
			
					fos.close();
					f.setVisible(false);
					f.dispose();
					fparent.setVisible(false);
					fparent.dispose();
				}
			}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
		}
		else if(e.getSource()==bns)
		{
			if((file.getName()).equals("newfile"))
			{
				file.delete();
			}
			f.setVisible(false);
			f.dispose();
			fparent.setVisible(false);
			fparent.dispose();
		}
		else if(e.getSource()==bclose)
		{
			f.setVisible(false);
			f.dispose();
		}
	}
		
	
	
	
	public void windowClosing(WindowEvent e)
	{
		
			Window w=e.getWindow();
			w.setVisible(false);
			w.dispose();
		
		//System.exit(1);
	}
	
	
	
	public void windowActivated(WindowEvent e)
	{
	}
	
	public void windowDeactivated(WindowEvent e)
	{
	}
	
	public void windowClosed(WindowEvent e)
	{
	}
	public void windowIconified(WindowEvent e)
	{
	}
	
	public void windowDeiconified(WindowEvent e)
	{
	}
	
	public void windowOpened(WindowEvent e)
	{
	}
	
	
	
}
		
		
		
		
		
		
		

	
		
	
