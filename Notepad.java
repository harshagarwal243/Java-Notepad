
import java.awt.*;
import java.awt.event.*;
import java.io.*;


class Notepad extends WindowAdapter implements ActionListener,TextListener
   {  static Frame f;
      MenuItem open,save,saveas,exit,cut,copy,paste,about;
      Menu file,edit,help;
      TextArea ta;
      Button b1,b2;
      static boolean newfileflag=true;
      String str = "untitled" ;
      File fileref;
      static boolean saved = true;
      Dialog d;
      
     
      
     Notepad()
      {
        f=new Frame("My notepad");
       MenuBar mb =new MenuBar();
       f.setMenuBar(mb);
       file= new Menu("File");
       edit=new Menu("Edit");
       help=new Menu("Help");
       mb.add(file); mb.add(edit); mb.add(help);
       save =new MenuItem("Save");
       open =new MenuItem("Open");
       saveas =new MenuItem("Save as");
       exit =new MenuItem("Exit");
       file.add(save); file.add(open); file.add(saveas); file.add(exit);
       cut =new MenuItem("Cut");
       copy =new MenuItem("Copy");
       paste =new MenuItem("Paste");
       edit.add(cut); edit.add(copy); edit.add(paste);
       about = new MenuItem("About Notepad");
       help.add(about);
       ta = new TextArea();
       f.add(ta);
       ta.addTextListener(this);
       open.addActionListener(this);
       save.addActionListener(this);
       saveas.addActionListener(this);
       exit.addActionListener(this);
      }

     public static void main(String args[]) throws IOException
      {  
        Notepad  nd = new Notepad();
        
        f.setSize(700,500); 
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter()
                                  {
                                     public void windowClosing(WindowEvent e)
                                        { 
                                          nd.exit();
                                        }
                                   });
        
      }
       
      
      public void textValueChanged(TextEvent te)
        { if(te.getSource()==ta)
            { saved =false;}
        }
      
       public void actionPerformed(ActionEvent e)
         { if(e.getSource()==open)
            {  try
                {
                   FileDialog fd = new FileDialog(f,"open your file",FileDialog.LOAD);
                   fd.setVisible(true);
                   String dir=fd.getDirectory() , fname = fd.getFile();
                   FileInputStream fis = new FileInputStream(dir+fname);
                   BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                   String str="";
                   while((str=br.readLine())!=null)
                     { ta.append(str+"\n");
                     }
                   
                   br.close();
                 }
                 catch(Exception ex)
                    { }
            }
            
          
          else if(e.getSource()==save)
              {
                 if(newfileflag==true)
                    saveasFile();
                 else saveFile();
               }              

          else if(e.getSource()==saveas)
              saveasFile();     
          
          else if(e.getSource()==exit)
              exit();

          else if(e.getSource()==b1)
              {  d.setVisible(false); System.exit(0);}
          
          else if(e.getSource()==b2)
             {  d.setVisible(false); }
 
         }
        

         public void saveasFile()
            { FileDialog fd = new FileDialog(f,"save your file",FileDialog.SAVE);
              fd.setVisible(true);
              String dir=fd.getDirectory();
              str = fd.getFile();
              try
               {
                 FileWriter fout = new FileWriter(dir+str);
                 fout.write(ta.getText());
                 fout.close();
                 newfileflag = false;
               }
               catch(Exception e)
                 {}
             }
       public void saveFile()
          { try
             {
                 FileWriter fout = new FileWriter(".\\"+str);
                 fout.write(ta.getText());
                 fout.close();
                 saved=true;
               }
               catch(Exception e)
                 {}
               
          }
       public void exit()
        { if(saved==true)  
             System.exit(0);
          
          else
             {  
               d = new Dialog(f,"Caution",true);
                d.setLayout(new FlowLayout());
               Label l= new Label("file is not saved.Do u want to exit? ");
              
               b1 = new Button("OK");
               b2= new Button("Cancel");
               d.add(l); d.add(b1); d.add(b2);
               b1.addActionListener(this);
               b2.addActionListener(this);
               //b1.setBounds(20,60,40,30); b2.setBounds(80,60,40,30);
                d.setSize(300,120);
               d.setVisible(true);
                
               
             }
         }
               
   }
     