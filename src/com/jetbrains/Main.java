package com.jetbrains;

public class Main {

    Main(){
        User_rep user = new User_rep();
        //user.login();
        System_controller sys= new System_controller();
        sys.array_printer(sys.array_lister("src\\com\\jetbrains\\users\\"));
    }

    public static void main(String[] args) {
	new Main();
    }
}
