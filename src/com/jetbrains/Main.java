package com.jetbrains;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

    Main(){
        User_rep user = new User_rep();
        System_controller sys= new System_controller();
        Staff_rep staff = new Staff_rep();
        sys.welcome_msg();
        user.login();

        //sys.array_printer(sys.array_lister("src\\com\\jetbrains\\users\\"));
        //sys.data_adder("2100", "users", "1234");
        //user.withdraw("2100");
        //user.deposit("2100");

        //sys.read_ent_file("src/com/jetbrains/stocks.txt", 13);
        //staff.check_balances();
        //staff.check_u_balance();
        //staff.create_u_s();

    }


    public static void main(String[] args) {
	new Main();
    }
}
