package com.jetbrains;
import java.io.File;

public class User_rep {

    //LIST OF ATTRIBUTES OF THIS CLASS:
    private String g_id;
    private String g_pass;
    private String g_balance;



    public void login(){
        System_controller sys = new System_controller();
        String id;
        String pass;
        boolean valid;
        boolean pass_valid;

        String info_list[];

        do{
            id = sys.regex_val("type your ID: ", "[0-9]+", "You should type just numbers!");
            if (!sys.f_exists("src\\com\\jetbrains\\users\\"+id+".txt")){
                sys.print("Id not valid. Try again");
                valid = false;
            }else{
                valid = true;
            }
        }while(!valid);
        info_list = sys.file_reader(id, 3);

        //EVERYTHING SHOULD HAVE GONE RIGHT WITH THE ID AT THIS POINT, SO KEEP GOING ALONG;

        int counter = 0;
        do{
            pass = sys.regex_val("Type your password: ", "[0-9A-Za-z]+", "type just regular characters");
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
    sys.home_user(id);
    }
    // METHOD FOR CHECKING THE BALANCE
    public void check_bal(String id){
        System_controller sys = new System_controller();
        String balance = sys.file_reader(id, 3)[2];
        String[] lines_frame = new String[]{"Your Balance is", balance};
        Frame_model frame = new Frame_model(20, 0);
        frame.set_frame_centralized(lines_frame);
        sys.pause();
    }
    //METHOD FOR CHANGING THE PASSWORD
    public void cg_pass(String id){
        System_controller sys = new System_controller();
        String pass = sys.file_reader(id,3)[1];
        boolean equal1;
        boolean equal2;
        String new_pass;
        String confirming;
        String text;
        String tpd_pass;

        sys.print("IF YOU WANT TO GIVE UP ON CHANGING YOUR PASSWORD, JUST TYPE \"exit\" AT ANY TIME");
        do{
            tpd_pass = sys.text_cap("type your current password: ");
            if (tpd_pass.equals("exit")){
                equal1 = false;
                sys.home_user(id);
            }else if (!tpd_pass.equals(pass)){
                sys.print("Returning to Menu");
                sys.home_user(id);
                equal1 = false;
            }else{
                equal1 = true;
            }
        }while(!equal1);


        do{
            new_pass = sys.text_cap("type your new password: ");

            confirming = sys.text_cap("Type once again for confirming: ");

            if (new_pass.equals("exit")){
                sys.print("You typed different passwords, please, enter the same password two times");
                equal2 = false;
            }else if (!new_pass.equals(confirming)){
                sys.print("Returning to Menu");
                sys.home_user(id);
                equal2 = false;
            } else{

                sys.data_adder(id, "users", new_pass);
                sys.print("Password changed successfully\n\n");
                equal2 = true;

            }
        }while(!equal2);


        sys.pause();

    }
    //METHOD FOR WITHDRAWING THE VALUE
    public void withdraw(String id){
        System_controller sys = new System_controller();
        String[] withd_options =  new String[]{"5 euros", "10 euros", "20 euros", "50 euros"};
        Frame_model frame = new Frame_model(20, 0);
        frame.set_frame_centralized(withd_options);
        String[]lines = sys.file_reader(id, 3);


    }

}
