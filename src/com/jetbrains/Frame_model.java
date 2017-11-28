package com.jetbrains;
// THIS CLASS IS JUST A BLUEPRINT OF WHAT A FRAME IS, FOR PRINTING THE MENUS AND WELCOME MESSAGES WITH LINES
public class Frame_model {

    private int F_width;
    private int F_height;

    Frame_model(int F_width, int F_height){
        this.F_width = F_width;
        this.F_height = F_height;
    }

    //THIS METHOD PRINTS THE FRAME.
    public void set_frame_centralized(String[] options){
        System_controller sys = new System_controller();
        sys.print("+"+sys.str_mult("-",F_width)+"-"+"+");
        for (int i = 0; i<options.length;i++){
            sys.print("|"+sys.str_mult(" ", ((-options[i].length()+F_width+2)/2))+options[i]+sys.str_mult(" ",
                    ((-options[i].length()+F_width+1)/2))+"|");
        }
        sys.print("+"+sys.str_mult("-",F_width)+"-"+"+");
    }
    public void set_frame_ordered(String[] options){
         System_controller sys = new System_controller();
         //sys.print("+"+sys.str_mult("-",F_width+4)+"-"+"+");
         for (int i = 0; i<options.length;i++){
             sys.print("+"+sys.str_mult("-",F_width+4)+"-"+"+");
             sys.print("|"+String.valueOf(i+1)+". "+options[i].toUpperCase()+sys.str_mult(" ",
                     ((-options[i].length()+F_width+2)))+"|");

         }
         sys.print("+"+sys.str_mult("-",F_width+4)+"-"+"+");
     }
}
