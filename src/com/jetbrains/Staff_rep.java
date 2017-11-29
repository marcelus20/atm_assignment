package com.jetbrains;

public class Staff_rep {

    public void home(String id){
        User_rep user = new User_rep();
        System_controller sys = new System_controller();
        String key;
        String[] menu_options = new String[]{
                "Create a new User", "See balance per user",
                "See all balance together", "See activity log",
                "Log off", "Shutdown"
        };
        String regex = "[1-"+menu_options.length+"]";
        Frame_model frame = new Frame_model(24,0);
        frame.set_frame_ordered(menu_options);

        key = sys.regex_val("OPTION: ", regex,
                "You should type just numbers between 1 and "+menu_options.length);
        if(key.equals("1")){
            create_u_s();
            sys.pause();
            home(id);
        }else if(key.equals("2")){
            check_u_balance();
            sys.pause();
            home(id);
        }else if(key.equals("3")){
            check_balances();
            sys.pause();
            home(id);
        }else if(key.equals("4")){

            home(id);
        }else if(key.equals("5")){
            sys.print("loging off...");
            user.login();
            home(id);
        }else if(key.equals("6")){
            sys.print("THANKS FOR USING OR SERVICES, COME BACK SOON, PLEASE!");
            System.exit(0);
        }}
    // THIS METHOD CHECKS THE BALANCE OF ALL USERS AT THE SAME TIME
    public void check_balances(){
        System_controller sys = new System_controller();
        Frame_model frame = new Frame_model(30, 0);
        String[] user_list;
        String account;
        String balance;
        Double total_balance;

        //LISTING THE USERS
        user_list =  sys.array_lister("src\\com\\jetbrains\\users");

        total_balance = 0.0;


        sys.print(" ID      Value      Category");
        for (int i =0; i< user_list.length; i++){
            String id = user_list[i];
            balance = sys.file_reader(id, 3,"users")[2];
            if (Double.valueOf(balance)<=100){
                account = "small";
            }else if(Double.valueOf(balance)>100 && Double.valueOf(balance)<= 200){
                account = "medium";
            }else if((Double.valueOf(balance)>200 && Double.valueOf(balance)<= 300)){
                account = "large";
            }else {
                account = "extra large";
            }
            user_list[i] = id + " | "+balance+" | "+account;
            total_balance += Double.valueOf(balance);
            //sys.print() ;
        }
        frame.set_frame_ordered(user_list);
        sys.print("Total balance = "+ total_balance);

        }

    // THIS METHOD CHECKS THE BALANCE OF JUST THE USER THE STAFF CHOOSE
    public void check_u_balance(){
        System_controller sys = new System_controller();
        Frame_model frame = new Frame_model(10,0);
        String id;
        String balance;
        boolean valid;

        sys.print("List of IDs");
        frame.set_frame_centralized(sys.array_lister("src\\com\\jetbrains\\users"));


        do{
            id = sys.regex_val("Which of these Ids do you want to check the balance?",
                    "[0-9][0-9][0-9][0-9]", "it should contain a 4 digit number");

            balance = sys.file_reader(id, 3, "users")[2];
            if (balance == null){
                sys.print("please, only type the id listed above.");
                valid = false;
            }else{
                valid = true;
            }
        }while(!valid);

        sys.print("RETRIEVING THE BALANCE");
        sys.print("id: "+id);
        sys.print("balance: "+balance);


    }

    //THIS METHOD CREATES A STAFF OR A USER
    public void create_u_s(){
        Frame_model frame = new Frame_model(20,0);
        System_controller sys = new System_controller();
        String[] options;
        String option_;
        String address;
        String id;
        String[]u_list;
        String u_list_;
        String pin;
        String ini_deposit;
        boolean new_id;



        address = "src\\com\\jetbrains\\";
        options = new String[]{"Create a Staff","Create a User"};
        frame.set_frame_ordered(options);
        option_ = sys.regex_val("OPTION: ", "[1-"+options.length+"]",
                "You should type numbers between 1 and "+options.length);

        if (option_.equals("1")){
            address +="staff";
        }else{
            address +="users";
        }

        //MAKING SURE THE ID TO BE CREATED DOES NOT EXIST
        u_list = sys.array_lister(address);//listing existing ids
        u_list_= "";
        for (int i = 0; i<u_list.length; i++){
            u_list_+= u_list[i]+"/";//SAVING THE ID'S IN ONE STRING
        }


        do{
            //IF STAFF IS BEING CREATED, ID WILL BE NUMBERS AND CHARACTERS, CREATOR WILL CHOOSE;
            //IF NOT STAFF, ID WILL BE A RANDOM NUMBER BETWEEN 1 AND 9999;
            if(!address.split("\\\\")[address.split("\\\\").length-1].equals("staff")) {
                id = String.valueOf((int) (Math.random() * 9999 + 1));

                //IF ID DOES NOT HAVE LENGTH 4, IT WILL ADD ZEROS TO IT UNTIL IT GETS IT.
                id = sys.str_mult("0",4-id.length())+id;
            }else{
                id = sys.regex_val("type the staff id: ",
                        "[0-9a-zA-Z]+",
                        "You should type just letters or numbers");
            }




            //CREATING A RANDOM ID BETWEEN 0 AND 9999

            if (!u_list_.contains(id)){//CHECKING IF THAT STRING CONTAINS THE ID
                new_id = true;
            }else{

                new_id = false;
            }
        }while(!new_id);

        //AT THIS POINT THE ID CREATED IS ENTIRELY NEW


        //CREATING A RANDOM 4 DIGIT PIN (LATER USER CAN CHANGE IF HE WANTS TO)
        pin = String.valueOf((int)(Math.random()*9999+1));

        //INITIAL DEPOSIT.
        //IF STAFF, DEPOSIT IS NULL IF USER, DEPOSIT IS WHAT HE OR SHE DEPOSITS
        if(!address.split("\\\\")[address.split("\\\\").length-1].equals("staff")){
            ini_deposit = sys.regex_val("Insert you initial deposit, if none, type 0 and enter",
                    "^0$|^[1-9]\\d*$|^\\.\\d+$|^0\\.\\d*$|^[1-9]\\d*\\.\\d*$",
                    "You should type just numbers, either decimal or integers: ");
            ini_deposit = sys.round_value(Double.valueOf(ini_deposit));

        }else{
            ini_deposit = null;
        }

        sys.print("id: "+id+"\n"+
                "pin: "+pin+"\n"+
                "initial deposit: "+ini_deposit);
        sys.pause();

        sys.f_creater(address, id, pin, ini_deposit);
    }

}

