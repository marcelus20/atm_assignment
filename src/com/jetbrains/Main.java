package com.jetbrains;

public class Main {

    Main(){
        User_rep user = new User_rep();
        System_controller sys= new System_controller();

        sys.welcome_msg();
        user.login();

        //sys.array_printer(sys.array_lister("src\\com\\jetbrains\\users\\"));


    }

    public static void main(String[] args) {
	new Main();
    }
}
