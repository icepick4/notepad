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

/**
 *
 * @author Remi
 */
public class BlocNotes {
    private static GUI gui;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        gui = new GUI();
        gui.setVisible(true);
    }

    public static void openFile(String file) {
        System.out.print(file);
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
        gui.set_text(text);
    }

    public static void save_existing_file(String text, String file_name) {
        //create a File object with file name
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
        gui.set_text(text);
    }




        

    

    
}
