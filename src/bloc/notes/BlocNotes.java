/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bloc.notes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import javax.swing.JOptionPane;


/**
 *
 * @author Remi
 */
public class BlocNotes {
    private GUI gui;

    public BlocNotes() {
        gui = new GUI(this);
        gui.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BlocNotes bloc_notes = new BlocNotes();
    }

    public void openFile(String file) {
        //create a File object with file name
        File f = new File(file);
        //create a FileReader object
        FileInputStream fr = null;
        try {
            fr = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        InputStreamReader isr = new InputStreamReader(fr, StandardCharsets.UTF_8);
        //create a BufferedReader object
        BufferedReader br = new BufferedReader(isr);
        //read file char by char
        String line;
        String text = "";
        try {
            while ((line = br.readLine()) != null) {
                text += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //close the file
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //set the text in the text area
        this.gui.set_text(text);
    }

    public boolean enregistrer_sous(String text, String file_name) {
        //get only the file name
        String file = file_name;
        file = file.substring(file.lastIndexOf("\\") + 1);
        //create a File object with file name, check if it exists
        File f = new File(file_name);
        if (f.exists()) {
            //open dialog to ask if you want to overwrite the file
            int dialog_result = JOptionPane.showConfirmDialog(this.gui,  file + "existe déjà\nVoulez-vous le remplacer ?", "Bloc-notes - Aller à la ligne", JOptionPane.YES_NO_OPTION);

            if (dialog_result == 0) {
                //if yes, delete the file
                f.delete();
                //create a new file
                f = new File(file_name);
            } else {
                //if no, return
                return false;
            }
        }
        //create a FileWriter object
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //create a BufferedWriter object
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        //write the text in the file
        try {
            bw.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //close the file
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.gui.set_text(text);
        return true;
    }

    public void enregistrer(String text, String file_name) {
        //create a File object with file name, check if it exists
        File f = new File(file_name);
        //create a FileWriter object
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //create a BufferedWriter object
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        //write the text in the file
        try {
            bw.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //close the file
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.gui.set_text(text);
    }
}
