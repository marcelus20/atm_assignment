package com.jetbrains;

public class Main {

    Main(){
        User_rep user = new User_rep();
        System_controller sys= new System_controller();

        sys.welcome_msg();
        user.login();

        //sys.array_printer(sys.array_lister("src\\com\\jetbrains\\users\\"));
        //sys.data_adder("2100", "users", "1234");
        //user.withdraw("2100");
        //user.deposit("2100");

    }

    public static void main(String[] args) {
	new Main();
    }
}
