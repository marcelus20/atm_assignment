package com.jetbrains;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

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
            if (new_pass.equals("exit")){
                sys.print("Returning to Menu");
                sys.pause();
                sys.home_user(id);
            }

            confirming = sys.text_cap("Type once again for confirming: ");
            if (confirming.equals("exit")){
                sys.print("Returning to Menu");
                sys.pause();
                sys.home_user(id);
            }

            if (new_pass.equals("exit")){
                sys.print("You typed different passwords, please, enter the same password two times");
                equal2 = false;
            }else if (!new_pass.equals(confirming)){
                sys.print("Returning to Menu");
                sys.home_user(id);
                equal2 = false;
            } else{

                sys.data_adder(id, "users", new_pass, 1);//INDEX IS 1 CAUSE IT IS CHANGING THE PASSWORD.
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
        String balance;
        Double value_;
        String value;
        String new_balance;


        frame.set_frame_ordered(withd_options);

        // HERE IS WHERE THE USER WILL INSERT THE OPTION FOR THE WITHDRAW OPERATION
        value = sys.regex_val("OPTION: ", "[1-"+withd_options.length+"]",
                "You should type just numbers between 1 and "+withd_options.length);
        String[]lines = sys.file_reader(id, 3);

        //INSTEAD OF DOING MANY IF AND ELSE STATEMENT, I AM USING DICTIONARIES, I KNOW THIS IS A MORE COMPLEX AND
        //ADVANCED STUFF, BUT I PREFER DOING THIS AS THIS IS MUCH EASER.

        Map<String, Double> dictionary = new HashMap<>();
        dictionary.put("1", 5.00);
        dictionary.put("2", 10.00);
        dictionary.put("3", 20.00);
        dictionary.put("4", 50.00);

        //RETRIEVING THE BALANCE
        balance = lines[2];
        value_ = dictionary.get(value);
        sys.print(value_);

        if (Double.valueOf(balance)<value_){
            sys.print("Your balance is :"+ balance);
            sys.print("You do not have enough income \n");
            sys.pause();
            sys.home_user(id);
        }else{
            new_balance = String.valueOf(Double.valueOf(balance)-value_);
            sys.data_adder(id, "users", new_balance, 2);//INDEX IS 2 BECAUSE IT'S CHAGING THE BALANCE IN THE FILE
            sys.print("amount withdrawn successfully\n");
        }
        sys.pause();











    }
    //METHOD FOR DEPOSITING THE VALUE
    public void deposit(String id){
        System_controller sys = new System_controller();
        String value;
        String current_balance;
        Double new_balance;
        value = sys.regex_val("Insert the amount you want to deposit: ",
                "^0$|^[1-9]\\d*$|^\\.\\d+$|^0\\.\\d*$|^[1-9]\\d*\\.\\d*$",// FOR ACCEPTING DECIMAL VALUES AND INTEGERS
                "just numbers should be deposited, integer or decimal");

        current_balance = sys.file_reader(id, 3)[2];
        new_balance = Double.valueOf(current_balance)+Double.valueOf(value);

        sys.data_adder(id, "users", sys.round_value(new_balance), 2);
        sys.print("Deposit carried out successfully");
        sys.pause();

    }

}
