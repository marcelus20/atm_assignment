package com.jetbrains;
import java.awt.*;
import java.io.*;
import java.text.DecimalFormat;

public class System_controller {

    //THIS METHOD PRINTS EVERYTHING
    public void print(Object element){
        System.out.println(element);
    }


    //THIS METHOD CAPTURES TEXT FROM KEYBOARD
    public String text_cap(String msg){
        System.out.print(msg);
        String element = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            element = br.readLine();
        }catch(Exception e){}
        return element;
    }


    //READING FILES
    public String[] file_reader(String id, int num_lines, String type){
        String ad_file = "src\\com\\jetbrains\\"+type+"\\"+id+".txt";
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
            return false;
        }else{
            return true;
        }
    }


    //THIS METHOD PRINTS THE SAME STRING THE TIMES YOU WANT IN THE SAME LINE
    //IF YOU DO, FOR INSTANCE str_mult("=",5), IT WILL RETURN "====="
    public String str_mult(String str, int times){
        String text = "";
        for (int i = 0; i < times; i++){
            text += str;
        }
        return text;
    }


    //JUST A METHOD TO WELCOME WHO STARTS THE SYSTEM
    public void welcome_msg(){

        String[] msg_element = new String[]{"WELCOME",
                "TO THE ","ATM SYSTEM BANK",
                "PLEASE,",
                "LOG INTO THE SYSTEM"};
        Frame_model frame = new Frame_model(24, 0);
        frame.set_frame_centralized(msg_element);
    }


    //THE MENU HOME AFTER THE PERSON HAS LOGGED IN
    public void home_user(String id){
        User_rep user = new User_rep();
        String key;
        String[] menu_options = new String[]{
                "Check your balance", "Change your password",
                "Withdraw a value", "Deposit a value ",
                "Check stock prices", "Log off ", "Shutdown"
        };
        String regex = "[1-"+menu_options.length+"]";
        Frame_model frame = new Frame_model(24,0);
        frame.set_frame_ordered(menu_options);

        key = regex_val("OPTION: ", regex,
                "You should type just numbers between 1 and "+menu_options.length);
        if(key.equals("1")){
            user.check_bal(id);
            home_user(id);
        }else if(key.equals("2")){
            user.cg_pass(id);
            home_user(id);
        }else if(key.equals("3")){
            user.withdraw(id);
            home_user(id);
        }else if(key.equals("4")){
            user.deposit(id);
            home_user(id);
        }else if(key.equals("5")){
            user.stck_pricing();
            home_user(id);
        }else if(key.equals("6")){
            print("loging off...");
            user.login();
            home_user(id);
        }else if(key.equals("7")){
            print("THANKS FOR USING OR SERVICES, COME BACK SOON, PLEASE!");
            System.exit(0);
    }}


    //THIS METHOD IS FOR THE USER HAVE SOME TIME TO ANALYSING THE OUTCOMES BEFORE THE CODE CONTINUES
    //IT'S A EQUIVALENT System.pause() FROM C LANGUAGE
    public void pause(){
        text_cap("Press enter to continue...");

    }


    // THIS METHOD IS RESPONSIBLE FOR WRITING THE WHOLE FILE.
    public void data_adder(String id, String type, String element, int index){
        String address = "src\\com\\jetbrains\\"+type+"\\"+id+".txt";



        String[] text = file_reader(id, 3, type);

        text[index] = element;


        //THIS VARIABLE IS WHERE ALL INFORMATION, EVEN THE UPDATED ELEMENT, WILL BE WRITTEN TO A FILE;
        String all_file_data = ar_toSring(text);
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(address));
            bw.write(all_file_data);
            bw.flush();
            bw.close();
        }catch(Exception e){System.out.println(e);}

    }


    // THIS METHOD IS USED TO GET THE WHOLE ARRAY AND SAVE IN JUST ONE STRING VARIABLE, BEING THE ELEMENTS
    // SEPARATED BY "\n", USED TO COMPLEMENT THE DATA_ADDER METHOD.
    public String ar_toSring(String[] array){
        String text = "";
        for (int i = 0; i<array.length;i++){
            text += array[i] + "\n";
        }
       return text;
    }


    // THIS METHOD ROUNDS THE STRING REPRESENTING MONEY TO 2 DECIMAL PLACES
    public String round_value(Double value){
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(value);
    }


    //THIS METHOD IS FOR UPDATING THE STOCK.TXT FILE. EVERY TIME USER CHECK THE STOCK PRICING, IT WILL UPDATE
    // (SIMULATION OF A REAL TIME MARKET DATA)
    public void update_stcks(){
        String text = "";
        int sign;
        String sign_;
        String[] companies = new String []{
                "FACEBOOK", "GOOGLE", "EBAY", "TESLA", "NASA", "SPACE-X", "PRETROBRAS",
                "BANK OF IRELAND", "BANK OF AMERICA", "CPL RESOURCES", "ANIMEX PLC",
                "DONEGAL INVESTIMENT", "GLAMBIA PLC"
        };



        for (int i = 0; i<companies.length;i++ ){
            sign = (int) (Math.random()*2+0);
            if(sign == 1){
                sign_ = "-";
            }else{
                sign_ = "+";
            }
            text +=companies[i]+" "+ round_value(Math.random()*40+10)+" "+" "+sign_+""+round_value(Math.random()*5+1)+"%"+"\n";
        }
        write_ent_file("src/com/jetbrains/stocks.txt", text);
    }


    // THIS METHOD WRITES AND REWRITES CONTENTS TO A ENTIRE FILE, NOT CARRYING ABOUT WHAT THERE WAS BEFORE,
    // IT WILL BE USED TO WRITE WHATEVER WAS UPDATED IN THE STOCK PRICING
    public void write_ent_file(String address, String element){
        try{
            BufferedWriter br = new BufferedWriter(new FileWriter(address));
            br.write(element);
            br.flush();
            br.close();
        }catch(Exception e){}
    }


    // THIS METHOD READS ANY FILE INDEPENDENTLY ON THE ID LOOGED IN.
    public void read_ent_file (String address, int lines){
        Frame_model frame = new Frame_model(45, 0);
        String[] text = new String[lines];
        String line;
        try{
            BufferedReader br = new BufferedReader(new FileReader(address));
            line = br.readLine();

            int counter = 0;
            while (line != null){
                text[counter] = line;
                counter++;
                line = br.readLine();

            }



        }catch(Exception e){}


        frame.set_frame_centralized(text);

    }


    //THIS METHOD CREATES A FILE, IT WILL BE USED IN CREATING USER STAFF_REP METHOD
    public void f_creater(String adress, String id, String pin, String deposit){
        File f;
        String element;
        String address_;

        address_ = adress+"\\"+id+".txt";
        print("Creating User...");
        f = new File(address_);
        element = id+"\n"+pin+"\n"+deposit;
        print("Saving information...");
        write_ent_file(address_ , element);
        print("User created sucessfuly!");
    }





}
