package com.jetbrains;
import java.io.File;

public class User_rep {

    //LIST OF ATTRIBUTES OF THIS CLASS:
    String g_id;
    String g_pass;
    String g_balance;



    public String login(){
        System_controller sys = new System_controller();
        String id;
        String pass;
        String[] user_list = sys.array_lister("src\\com\\jetbrains\\users\\");
        boolean valid;
        boolean pass_valid;

        String info_list[];

        do{
            id = sys.regex_val("type your ID: ", "[0-9]+", "You should type just numbers!");
            if (!sys.f_exists("src\\com\\jetbrains\\users")){
                valid = true;

            }else{
                sys.print("Id not valid. Try again");
                valid = false;
            }
        }while(!valid);
        info_list = sys.file_reader(id, 3);

        //EVERYTHING SHOULD HAVE GONE RIGHT WITH THE ID, SO KEEP GOING ALONG;

        int counter = 0;
        do{
            pass = sys.regex_val("Type your password", "[0-9A-Za-z]+", "type just regular characters");
            if (counter < 3 && !pass.equals(info_list[1])){
                sys.print("Password not correct");
                pass_valid = false;
                    counter++;
            }else{

                pass_valid = true;
            }
            if (counter == 3){
                sys.print("Application is closing due to many log in attempts");
                System.exit(0);
            }
        }while(!pass_valid);










        g_id = info_list[0];
        g_pass = info_list[1];
        g_balance = info_list[2];
    return id;
    }

}
