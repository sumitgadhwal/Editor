import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

class Editor extends Frame implements ActionListener , WindowListener,MouseListener
{
	Frame f;
	Frame fopen;
	Frame fnew;
	Frame ffind,frep;
	TextArea t;
	TextField tfind,tfindr,trepr;
	
	MenuBar mb;
	Menu m1,m2;
	MenuItem mnew,mopen,msave,msaveas,mexit,mfind,mfindr;
	File curr_file=new File("newfile");
	
	Button bsave,bns,bclose;///for open dialog
	
	Button nsave,nns,nclose;///for new Dialog
	
	
	Button bfn,bfnext,brp,brpall,bfn1,bfnext1;
	
	
	int start=-1,end=0;
	
	String str1="";
	
	public Editor()
	{
		f=new Frame("Editor");
		f.setSize(800,800);
		
		f.addWindowListener(this);
		t=new TextArea(null);
		f.add(t);
		
		mb=new MenuBar();
		m1=new Menu("File");
		m2=new Menu("Edit");
		
		mnew =new MenuItem("New");
		mopen=new MenuItem("Open");
		msave=new MenuItem("Save");
		msaveas=new MenuItem("Save As");
		mexit = new MenuItem("Exit");
		mfind=new MenuItem("Find");
		mfindr=new MenuItem("Find & Replace");
		
		m1.add(mnew);
		m1.add(mopen);
		m1.add(msave);
		m1.add(msaveas);
		m1.addSeparator();
		m1.add(mexit);
		
		m2.add(mfind);
		m2.addSeparator();
		m2.add(mfindr);
		
		
		mb.add(m1);
		mb.add(m2);
		
		f.setMenuBar(mb);
		
		mnew.addActionListener(this);
		mopen.addActionListener(this);
		msave.addActionListener(this);
		msaveas.addActionListener(this);
		mexit.addActionListener(this);
		
		
		mfind.addActionListener(this);
		mfindr.addActionListener(this);
		
		f.setVisible(true);
		
		
		t.addMouseListener(this);
		
	}
		
		
		
		
		
		
	
		
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==mnew)
		{	
			
			if(!str1.equals(t.getText()))
			{
			
			
				fnew=new Frame("Do u want to save this file?");
				fnew.setSize(300,100);
				fnew.setVisible(true);
				fnew.setLayout(new FlowLayout(FlowLayout.CENTER,15,15));
				fnew.addWindowListener(this);
				
				 nsave=new Button("Save");
				 nns=new Button("Don't Save");
				 nclose=new Button("close");
				
				fnew.add(nsave);
				fnew.add(nns);
				fnew.add(nclose);
				
				nsave.addActionListener(this);
				nns.addActionListener(this);
				nclose.addActionListener(this);
				
				
				
				
			
			
			}
			
			
			else
			{
				try
				{
					t.setText("");
					File fnew=new File("newfile");
					fnew.createNewFile();
					curr_file=fnew;
					str1="";
				}
				catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
			}
			
			
		}
		
		else if(e.getSource()==mopen)
		{
		
		
			
			if(!str1.equals(t.getText()))
			{
				
				fopen=new Frame("Do u want to save this file?");
				fopen.setSize(300,100);
				fopen.setVisible(true);
				fopen.setLayout(new FlowLayout(FlowLayout.CENTER,15,15));
				fopen.addWindowListener(this);
				
				 bsave=new Button("Save");
				 bns=new Button("Don't Save");
				 bclose=new Button("close");
				
				fopen.add(bsave);
				fopen.add(bns);
				fopen.add(bclose);
				
				bsave.addActionListener(this);
				bns.addActionListener(this);
				bclose.addActionListener(this);
				
				
				
				
			
			}
			
			
			else
			{
		
		
		
		
				String str="";
				 FileDialog fd=new FileDialog(f,"choose a file to open",FileDialog.LOAD);
				 fd.setVisible(true);
				if(fd.getFile()!=null)
				{
				 
				//System.out.println(fd.getFile()+ "   "+fd.getDirectory());
				curr_file=new File(fd.getDirectory()+fd.getFile());
				FileInputStream fis;
				try
				{
					fis=new FileInputStream(curr_file);
			
				int ch;
				while((ch=fis.read())!=-1)
				{	
					str+=(char)ch;
				}
				fis.close();
				}
				catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
				t.setText(str);
				str1=str;
				}
			
			}
			
		
		}
		
		else if(e.getSource()==msave)
		{
			
			try
			{	
				if((curr_file.getName()).equals("newfile"))
				{
					
					
					
				FileDialog fd=new FileDialog(f,"choose a file to open",FileDialog.SAVE);
				 
				 
				 fd.setVisible(true);
				if(fd.getFile()!=null)
				{
				//System.out.println(fd.getFile()+ "   "+fd.getDirectory());
				File new_file=new File(fd.getDirectory()+fd.getFile());
				new_file.createNewFile();
				FileOutputStream fos=new FileOutputStream(new_file);
				String str="";
				str=t.getText();
				int i=0;
				while(i<str.length())
				{
					fos.write(str.charAt(i));
					i++;
				}
				str1=str;
				curr_file.delete();
				curr_file=new_file;
				fos.close();
					
				}
					
				}
				else
				{
				
					String str=t.getText();
				
					FileOutputStream fos=new FileOutputStream(curr_file);
			
					int i=0;
					while(i<str.length())
					{
						fos.write(str.charAt(i));
						i++;
					}
					str1=str;
					fos.close();
				}
			}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
			
		}
		
		else if(e.getSource()==msaveas)
		{
			try
			{
				 FileDialog fd=new FileDialog(f,"choose a file to open",FileDialog.SAVE);
				 
				 
				 fd.setVisible(true);
				//System.out.println(fd.getFile()+ "   "+fd.getDirectory());
				if(fd.getFile()!=null)
				{
				File new_file=new File(fd.getDirectory()+fd.getFile());
				new_file.createNewFile();
				FileOutputStream fos=new FileOutputStream(new_file);
				String str="";
				str=t.getText();
				int i=0;
				while(i<str.length())
				{
					fos.write(str.charAt(i));
					i++;
				}
				fos.close();
				}
				
			}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
			
			
		}
		
		else if(e.getSource()==mexit)
		{
			
			if(!str1.equals(t.getText()))
			{
			
				new SaveDialog(t.getText(),curr_file,f);
			
			
		
			}
		
			else
			{
				f.setVisible(false);
				f.dispose();
				
			}
		
		
		}
		
		else if(e.getSource()==mfind)
		{
			
			ffind=new Frame("Enter Word u want to find");
			ffind.setSize(300,200);
			ffind.setLayout(new GridLayout(2,1,10,10));
			ffind.setVisible(true);
			ffind.addWindowListener(this);
		
		
			Panel p1=new Panel();
			p1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
			p1.add(new Label("Find "));
			tfind=new TextField(25);
			p1.add(tfind);
		
			ffind.add(p1);
		
		
			Panel p2=new Panel();
			p2.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
			bfn=new Button("Find ");
			bfnext=new Button("Find Next");
			p2.add(bfn);
			p2.add(bfnext);
			bfn.addActionListener(this);
			bfnext.addActionListener(this);
			
			
			ffind.add(p2);
		}
		
		else if(e.getSource()==mfindr)
		{
			
			frep=new Frame();
			frep.setSize(400,300);
			frep.setLayout(new GridLayout(3,1,10,10));
			frep.setVisible(true);
			frep.addWindowListener(this);
		
			Panel p1=new Panel();
			p1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
			p1.add(new Label("Find  "));
			tfindr=new TextField(25);
			p1.add(tfindr);
			frep.add(p1);
		
			Panel p2=new Panel();
			p2.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
			p2.add(new Label("Replace "));
			trepr=new TextField(25);
			p2.add(trepr);
			frep.add(p2);
		
		
			Panel p3=new Panel();
			p3.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		
			
			bfn1=new Button("Find ");
			bfnext1=new Button("Find Next");
			brp=new Button("Replace ");
			brpall=new Button("Replace All");
			
			bfn1.addActionListener(this);
			bfnext1.addActionListener(this);
			brp.addActionListener(this);
			brpall.addActionListener(this);
			
			
			p3.add(bfn1);
			p3.add(bfnext1);
			p3.add(brp);
			p3.add(brpall);
		
			frep.add(p3);
		
		
			
			
			
			
			
			
			
		}
		
		
		
		else if(e.getSource()==bsave)
		{
			
		
			try
			{		
				if((curr_file.getName()).equals("newfile"))
				{
					
					
					
					FileDialog fd=new FileDialog(f,"choose a file to open",FileDialog.SAVE);
					 
					 
					 fd.setVisible(true);
					//System.out.println(fd.getFile()+ "   "+fd.getDirectory());
					File new_file=new File(fd.getDirectory()+fd.getFile());
					new_file.createNewFile();
					FileOutputStream fos=new FileOutputStream(new_file);
					String str="";
					str=t.getText();
					int i=0;
					while(i<str.length())
					{
						fos.write(str.charAt(i));
						i++;
					}
					str1=str;
					curr_file.delete();
					
					fos.close();
					fopen.setVisible(false);
					fopen.dispose();
					
				
					
				}
				else
				
				{
					FileOutputStream fos=new FileOutputStream(curr_file);
					int i=0;
					String str="";
					str=t.getText();
					while(i<str.length())
					{
						fos.write(str.charAt(i));
						i++;
					}
					str1=str;
					fos.close();
					fopen.setVisible(false);
					fopen.dispose();
					//fparent.setVisible(false);
					//fparent.dispose();
				}
				
			
				String str="";
				 FileDialog fd=new FileDialog(f,"choose a file to open",FileDialog.LOAD);
				 
				 
				 fd.setVisible(true);
				//System.out.println(fd.getFile()+ "   "+fd.getDirectory());
				File new_file=new File(fd.getDirectory()+fd.getFile());
				FileInputStream fis;
				
					fis=new FileInputStream(new_file);
			
				int ch;
				while((ch=fis.read())!=-1)
				{	
					str+=(char)ch;
				}
				fis.close();
				
				
				t.setText(str);
				str1=str;
				curr_file=new_file;
				
				
				
				
			}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
		
		
		
		}
		else if(e.getSource()==bns)
		{
			
			
			
			if((curr_file.getName()).equals("newfile"))
			{
				curr_file.delete();
			}
			fopen.setVisible(false);
			fopen.dispose();
			//fparent.setVisible(false);
			//fparent.dispose();
			
				String str="";
				 FileDialog fd=new FileDialog(f,"choose a file to open",FileDialog.LOAD);
				 
				 
				 fd.setVisible(true);
				//System.out.println(fd.getFile()+ "   "+fd.getDirectory());
				File new_file=new File(fd.getDirectory()+fd.getFile());
				FileInputStream fis;
				try
				{
					fis=new FileInputStream(new_file);
			
				int ch;
				while((ch=fis.read())!=-1)
				{	
					str+=(char)ch;
				}
				fis.close();
				}
				catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
				t.setText(str);
				str1=str;
				curr_file=new_file;
		
		
		}
		else if(e.getSource()==bclose)
		{	
			fopen.setVisible(false);
			fopen.dispose();
		
		}
		
		else if(e.getSource()==nsave)
		{
			try
			{		
				if((curr_file.getName()).equals("newfile"))
				{
					
					
					
					FileDialog fd=new FileDialog(f,"choose a file to open",FileDialog.SAVE);
					 
					 
					 fd.setVisible(true);
					//System.out.println(fd.getFile()+ "   "+fd.getDirectory());
					File new_file=new File(fd.getDirectory()+fd.getFile());
					new_file.createNewFile();
					FileOutputStream fos=new FileOutputStream(new_file);
					String str="";
					str=t.getText();
					int i=0;
					while(i<str.length())
					{
						fos.write(str.charAt(i));
						i++;
					}
					str1=str;
					curr_file.delete();
					curr_file=new_file;
					fos.close();
					fnew.setVisible(false);
					fnew.dispose();
					//fparent.setVisible(false);
					//fparent.dispose();
				
					
				}
				else
				
				{
					FileOutputStream fos=new FileOutputStream(curr_file);
					int i=0;
					String str="";
					str=t.getText();
					while(i<str.length())
					{
						fos.write(str.charAt(i));
						i++;
					}
			
					fos.close();
					fnew.setVisible(false);
					fnew.dispose();
					//fparent.setVisible(false);
					//fparent.dispose();
				}
				
			
					curr_file=new File("newfile");
					t.setText("");
					str1="";
				
				
				
				
			}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
			
			
			
		}
		
		else if(e.getSource()==nns)
		{
			
			
			if((curr_file.getName()).equals("newfile"))
			{
				curr_file.delete();
			}
			fnew.setVisible(false);
			fnew.dispose();
			//fparent.setVisible(false);
			//fparent.dispose();
			curr_file=new File("newfile");
			t.setText("");
			str1="";
			
			
			
			
			
			
		}
		
		else if(e.getSource()==nclose)
		{
			fnew.setVisible(false);
			fnew.dispose();
		}
		
		
		else if(e.getSource()==bfn)
		{	
			start=-1;
			end=-1;
			String str2=t.getText();
			
			
			Pattern p=Pattern.compile(tfind.getText());
			Matcher m=p.matcher(str2);
			
			if(m.find(start+1))
			{
				start=m.start();
				end=m.end();		
					
			}
			
			t.select(start,end);
		
		
		}
		
		else if(e.getSource()==bfnext)
		{	
			
			
			
			String str2=t.getText();
			
			
			
			
			Pattern p=Pattern.compile(tfind.getText());
			Matcher m=p.matcher(str2);
			
			if(m.find(start+1))
			{
				start=m.start();
				end=m.end();		
					
			}
			
			t.select(start,end);
			
		}
		
		else if(e.getSource()==bfn1)
		{	
			start=-1;
			end=-1;
			String str2=t.getText();
			
			
			Pattern p=Pattern.compile(tfindr.getText());
			Matcher m=p.matcher(str2);
			
			if(m.find(start+1))
			{
				start=m.start();
				end=m.end();		
					
			}
			
			t.select(start,end);
		
		
		}
		
		else if(e.getSource()==bfnext1)
		{	
			
			
			String str2=t.getText();
			
			
			
			
			Pattern p=Pattern.compile(tfindr.getText());
			Matcher m=p.matcher(str2);
			
			if(m.find(start+1))
			{
				start=m.start();
				end=m.end();		
					
			}
			
			t.select(start,end);
			
		}
		else if(e.getSource()==brp)
		{
			
			if(tfindr.getText().equals(t.getText().substring(start,end)))
			{
				String st=t.getText();
			
				t.setText(st.substring(0,start)+trepr.getText()+st.substring(end));
			
				String str2=t.getText();
			
			
			
			
				Pattern p=Pattern.compile(tfindr.getText());
				Matcher m=p.matcher(str2);
			
				if(m.find(start+1))
				{
					start=m.start();
					end=m.end();	
					t.select(start,end);	
					
				}
			
			
			
			
			}
			
		
		}
		else if(e.getSource()==brpall)
		{
			String st=t.getText();
			String st1=tfindr.getText();
			String st2=trepr.getText();
			
			Pattern p=Pattern.compile(st1);
			Matcher m=p.matcher(st);
			
			if(m.find())
			{
				t.setText(m.replaceAll(st2));
			}
			
			
			
			
		}
		
		
		
	}
	
	
	public void mouseClicked(MouseEvent e1)
	{
		
		start=t.getCaretPosition()-1;
	}
	
	public void mouseEntered(MouseEvent e1)
	{
	}
	public void mouseExited(MouseEvent e1)
	{
	}
	public void mousePressed(MouseEvent e1)
	{
	}
	public void mouseReleased(MouseEvent e1)
	{
	}
	
	
	public static void main(String args[])
	{
		new Editor();
	}
	
	
	
	public void windowClosing(WindowEvent e)
	{
		if(e.getSource()==f && !str1.equals(t.getText()))
		{
			
			new SaveDialog(t.getText(),curr_file,f);
			
			
		
		}
		
		else
		{
		
			Window w=e.getWindow();
			w.setVisible(false);
			w.dispose();
		}
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
