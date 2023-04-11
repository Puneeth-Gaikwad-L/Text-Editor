import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener { 
// Declaring the properties of TextEditor  
    JFrame frame;

    JMenuBar menubar;

    JMenu file,edit;

    JMenuItem newFile, OpenFile, saveFile;

    JMenuItem cut, copy, paste, SelectAll, close;

    JTextArea textArea;

    TextEditor(){
// Initialise a frame
        frame = new JFrame();

        textArea = new JTextArea();

// Initialising the Menu bar;
        menubar = new JMenuBar();

        file = new JMenu("File");
        edit = new JMenu("Edit");
// Initializing the file menu Items
        newFile = new JMenuItem("New Window");
        OpenFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

// Adding the ActionListener to all the menu Items in file menu
        newFile.addActionListener(this);
        OpenFile.addActionListener(this);
        saveFile.addActionListener(this);

// adding items into file menue
        file.add(newFile);
        file.add(OpenFile);
        file.add(saveFile);

// Initialising the edit menu Items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        SelectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
// Adding ActionListeners to edit menue Items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        SelectAll.addActionListener(this);
        close.addActionListener(this);

// Adding items into edit menue
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(SelectAll);
        edit.add(close);
// Adding the menue to the MenueBar
        menubar.add(file);
        menubar.add(edit);  


    //   adding the menubar to the frame;

        frame.setJMenuBar(menubar);
    // adding the Text Area 

    // Initialising a panel
        JPanel panel = new JPanel();
// setting up the borders
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0, 0));

        panel.add(textArea, BorderLayout.CENTER);
// creating the scroll pane and scroll bar
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);

        frame.add(panel);
        frame.setTitle("Text Editor");

    
        // panel.setBackground(Color.BLACK);
            // seting the dimentions
        frame.setBounds(100, 100, 500, 600);
        frame.setVisible(true);
        frame.setLayout(null);

    }
    public static void main(String[] args) throws Exception {
        TextEditor textEditor = new TextEditor();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cut) textArea.cut();
        if(e.getSource()==copy) textArea.copy();
        if(e.getSource()==paste) textArea.paste();
        if(e.getSource()==SelectAll) textArea.selectAll();
        if(e.getSource()==close) System.exit(0);

        // open file events
        if(e.getSource()==OpenFile){
            // open file chooser
            JFileChooser filechoser = new JFileChooser("C:");  /*passing the file path as C drive */
            int chooseOption = filechoser.showOpenDialog(null);   

            // if the open button is clicked
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file = filechoser.getSelectedFile();
                // getting the path of the selected file
                String filePath = file.getPath();
                try{
                    // Initialise the file reader
                    FileReader fileReader = new FileReader(filePath);   /*will go to the file path and read the selected file */
                    // Intialise bufferReader 
                    BufferedReader br = new BufferedReader(fileReader);   /* bufferReader will get the data line by line */
                    // create two string 
                    String Intermediate="", OutPut="";
                    /* Intermediate will have the data of the current line and output will have the final data */
                    while((Intermediate = br.readLine())!=null){
                        OutPut += Intermediate+"\n";
                    }
                    // adding the output string to the text Area
                    textArea.setText(OutPut);
                }catch(IOException fileNOtFoundException){
                    fileNOtFoundException.printStackTrace();
                }
            }

        }
        // if save button is clicked
        if(e.getSource()==saveFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int ChooseOption = fileChooser.showSaveDialog(fileChooser);
        
        if(ChooseOption == JFileChooser.APPROVE_OPTION){
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
        
            try{
            FileWriter fileWriter = new FileWriter(file);
        
            BufferedWriter br = new BufferedWriter(fileWriter);
        
            textArea.write(br);
            br.close();
            }catch(IOException ioException){
                ioException.printStackTrace();
            }
        }

        }
        if(e.getSource()==newFile){
            TextEditor newTextEditor = new TextEditor();
        }

    }
}
