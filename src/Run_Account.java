/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class Run_Account {
    public static void main(String[] args) {
        MainMenu Mmenu=new MainMenu();
        
        String bank="bank.dat";
        String user="user.dat";
        Mmenu.addItem("Create an account");
        Mmenu.addItem("Login");
        Mmenu.addItem("Save");
        Mmenu.addItem("Quit");
        
        AccountList list=new AccountList();
        int choice;
        do{
            choice=Mmenu.getChoicce();
            switch(choice){
                case 1:
                    list.Create();
                    break;
                case 2:
                    list.Login();
                    break;
                case 3:
                    list.savePnA(user);
                    list.saveBank(bank);
                    break;
            }
        }while(choice>=1&&choice<=3);
    }
}
