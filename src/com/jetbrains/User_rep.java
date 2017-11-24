package com.jetbrains;

public class User_rep {
    public String login(){
        System_controller sys = new System_controller();
        String id = "";
        id = sys.text_cap("type your ID: ");
        sys.array_printer(sys.file_reader(id, 3));
        String pass = "";


    return null;
    }

}
