package com.jetbrains;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.File;

public class System_controller {

    //THIS METHOD PRINTS EVERYTHING
    public void print(Object element){
        System.out.print(element+"\n");
    }
    //THIS METHOD CAPTURES TEXT FROM KEYBOARD
    public String text_cap(String msg){
        print(msg);
        String element = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            element = br.readLine();
        }catch(Exception e){}
        return element;
    }
    //READING FILES
    public String[] file_reader(String id, int num_lines){
        String ad_file = "src\\com\\jetbrains\\users\\"+id+".txt";
        String [] data = new String[num_lines];
        String line = "";
        try{
            BufferedReader fr = new BufferedReader( new FileReader(ad_file));
            line = fr.readLine();
            int counter = 0;
            while(line != null){
                data[counter] = line;
                counter++;
                line = fr.readLine();
            }
            return data;

        }catch(Exception e){}


        return data;
    }
    //PRINTING ARRAYS
    public void array_printer(Object[] array){
        for (int i = 0; i<array.length; i++ ){
            print(array[i]);
        }
    }
    //LISTING FILES IN A DIRECTORY
    public String[] array_lister(String directory){
        File folder = new File(directory);
        File[] list_files = folder.listFiles();
        String[] list = new String[list_files.length];
        for (int i = 0; i<list_files.length; i++){
            list[i] = list_files[i].getName().split(".txt")[0];
        }
    return list;}
    //VALIDATING THE TEXT_CAP METHOD
    public String regex_val(String msg, String regex, String error_msg){
        String element;
        boolean valid;
        do{
            element = text_cap(msg);
            if (!element.matches(regex)){
                print(error_msg);
                valid = false;
            }else{
                valid = true;
            }
        }while(!valid);
    return element;}
    //CHECKING IF FILE EXISTS
    public boolean f_exists(String address){
        if(!new File(address).exists()){
            return true;
        }else{
            return false;
        }
    }


}
