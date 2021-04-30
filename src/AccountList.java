
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ASUS
 */
public class AccountList {

    ArrayList<Account> list = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    Iterator itr = list.iterator();

    public void Create() {
        int pos;
        boolean val;
        String id;
        String name;
        String password;
        String confirmpass;
        do {
            System.out.print("Input Account ID: ");
            id = sc.nextLine();
            pos = isDuplicated(id);
            if (pos >= 0) {
                System.out.println("The account name is duplicated");
            }
            if (id.contains(" ")) {
                System.out.println("The Account ID can not be blank");
            }
        } while (pos >= 0 || id.contains(" "));

        System.out.print("Input name: ");
        name = sc.nextLine();

        do {
            System.out.print("Input password: ");
            password = sc.nextLine();
            System.out.print("Confirm password: ");
            confirmpass = sc.nextLine();
            val = isValid(password);
            if (password.contains(" ")) {
                System.out.println("* The password can not be blank");
            }
            if (val != true) {
                System.out.println("* your password has to contain at least 6 characters, including uppercase letters,\nlower case letters, numeric characters and 1 special character ");
            }
            if (!password.equals(confirmpass)) {
                System.out.println("* The password dosent equal with confirm password");
            }

        } while (!password.equals(confirmpass) || !val || password.contains(" "));
        list.add(new Account(id, name, password));
        System.out.println("Created successfully");
    }

    public boolean isValid(String aPass) {
        String regex = "^(?=.*?\\p{Lu})(?=.*?\\p{Ll})(?=.*?\\d)"
                + "(?=.*?[`~!@#$%^&*()\\-_=+\\\\|\\[{\\]};:'\",<.>/?]).*$";
        if (aPass.length() < 6) {
            return false;
        } else {
            if (!aPass.matches(regex)) {
                return false;
            }
            return true;
        }
    }

    public int isDuplicated(String aCode) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(aCode)) {
                return i;
            }
        }
        return -1;
    }

    public void Remove(String DelID) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(DelID)) {
                list.remove(list.get(i));
            }
        }
    }
    
    public void savePnA(String fName){
        if(list.size()==0){
            System.out.println("Empty list");
            return;
        }
        try{
            File f=new File(fName);
            FileWriter fw=new FileWriter(f);
            PrintWriter pw=new PrintWriter(fw);
            for(Account o:list){
                pw.println(o.getId()+"|"+o.getPassword());
            }
            pw.close();fw.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void saveBank(String fName){
        if(list.size()==0){
            System.out.println("Empty list");
            return;
        }
        try{
            File f=new File(fName);
            FileWriter fw=new FileWriter(f);
            PrintWriter pw=new PrintWriter(fw);
            for(Account o:list){
                pw.println(o.getId()+"|"+o.getMoney());
            }
            pw.close();fw.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public int findId(String aCode) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId().equals(aCode)) {
				return i;
			}
		}
		return -1;
	}
    public void Login() {
        String logID;
        String logPass;
        int mark = 0;
        boolean checkquit = true;
        boolean checkIn = false;
        
        System.out.print("Input your account ID to login: ");
        logID = sc.nextLine();
        System.out.print("Input your password to login: ");
        logPass = sc.nextLine();
        SubMenu Smenu = new SubMenu();
        int pos=findId(logID);
        for (Account o : list) {
            if (logID.equals(o.getId()) && logPass.equals(o.getPassword())) {
                checkIn = true;
                mark++;
            }
        }
        if (checkIn) {
            System.out.println("Login successfully. Welcome " + list.get(pos).getName());
            Smenu.addItem("Withdrawal");
            Smenu.addItem("Deposit");
            Smenu.addItem("Transfer money");
            Smenu.addItem("Remove account");
            Smenu.addItem("Change password");
            Smenu.addItem("Logout");

            int choice;

            do {
                if (checkquit == false) {
                    break;
                }
                System.out.println("Your balance: " + list.get(pos).getMoney());
                choice = Smenu.getChoicce();

                switch (choice) {
                    case 1:
                        //Withdraw
                        double withdrawMoney;
                        double afterWithdraw;
                        System.out.print("Input the amount of money that you want to withdraw: ");
                        withdrawMoney = Double.parseDouble(sc.nextLine());
                        if (list.get(pos).getMoney() >= withdrawMoney) {
                            afterWithdraw = list.get(pos).getMoney() - withdrawMoney;
                            list.get(pos).setMoney(afterWithdraw);
                            System.out.println("Withdrawed successfully");
                        } else {
                            System.out.println("You dont have enough money to withdraw");
                        }
                        break;
                    case 2:
                        //Deposit
                        double depositMoney;
                        double afterDeposit;
                        String ask;

                        System.out.print("Input the amount of money that you want to deposit: ");
                        depositMoney = Double.parseDouble(sc.nextLine());

                        System.out.print("Are you sure to deposit: " + depositMoney + " (Y/N): ");
                        ask = sc.nextLine();
                        if (ask.equalsIgnoreCase("Y") || ask.equalsIgnoreCase("yes")) {
                            afterDeposit = list.get(pos).getMoney() + depositMoney*2;
                            list.get(pos).setMoney(afterDeposit);
                            System.out.println("Deposited Successfully");
                        } else {
                            System.out.println("Deposit was cancled");
                        }
                        break;
                    case 3:
                        //Trangsfer
                        String transferAccountID;
                        double transferMoney;
                        double afterTransfer;
                        double recieveTransfer;
                        String check;
                        int mark1 = 0;

                        System.out.print("Input the account ID you want to transfer money: ");
                        transferAccountID = sc.nextLine();
                        System.out.print("Input the amount of money that you want to transfer: ");
                        transferMoney = Double.parseDouble(sc.nextLine());
                        for (Account i : list) {
                            if (transferAccountID.equals(i.getId()) && transferMoney <= list.get(pos).getMoney()) {
                                mark1++;
                                System.out.print("Are you sure to transfer " + transferMoney + " to account " + transferAccountID + " (Y/N): ");
                                check = sc.nextLine();
                                if (check.equalsIgnoreCase("Y") || check.equalsIgnoreCase("yes")) {
                                    afterTransfer = list.get(pos).getMoney() - transferMoney;
                                    list.get(pos).setMoney(afterTransfer);
                                    transferMoney += i.getMoney();
                                    i.setMoney(transferMoney);
                                    System.out.println("Transfered successfully");
                                } else {
                                    System.out.println("Transfer was cancled");
                                }
                            }
                        }
                        if (mark1 == 0) {
                            System.out.println("Cant find the account or your balance is not enough to transfer");
                        }
                        break;
                    case 4:
                        //Remove
                        String check2;

                        System.out.print("Are you sure to remove this account? (Y/N): ");
                        check2 = sc.nextLine();
                        if (check2.equalsIgnoreCase("Y") || check2.equalsIgnoreCase("yes")) {
                            checkquit = false;
                            list.remove(pos);
                            System.out.println("Remove Successfully.");
                        } else {
                            System.out.println("Remove was cancled");
                        }
                        break;
                    case 5:
                        //ChangePassword
                        String changedPass;
                        String oldPass;
                        boolean val;
                        String confirmPass;
                             
                        do{
                        System.out.print("Input the old password: ");
                        oldPass=sc.nextLine();
                        if(!oldPass.equals(list.get(pos).password)){
                            System.out.println("password is not correct");
                        }
                        if(oldPass.equals(list.get(pos).password)){
                            do{
                            System.out.print("Input a new password: ");
                            changedPass=sc.nextLine();
                            System.out.print("Confirm password: ");
                            confirmPass=sc.nextLine();
                            val=isValid(changedPass);
                            
                            if(changedPass.equals(oldPass)){
                                System.out.println("This is old password, you have to have a new one");
                            }
                            if(!(changedPass.equals(confirmPass))){
                                System.out.println("The confirm password is not correct");
                            }
                         
                            if(val !=true){
                                System.out.println("* your password has to contain at least 6 characters, including uppercase letters,\nlower case letters, numeric characters and 1 special character ");
                            }
                            if(changedPass.contains(" ")){
                                System.out.println("Your password cannot be blank");
                            }
                            }while(val != true || changedPass.contains(" ") || changedPass.equals(oldPass) || !(changedPass.equals(confirmPass)) );
                            list.get(pos).setPassword(changedPass);
                            oldPass=changedPass;
                        }
                        }while(!(oldPass.equals(list.get(pos).password)));
                        System.out.println("Changed password successfully");
                        checkquit=false;
                        break;
                }

            } while (choice >= 1 && choice <= 5);

        }else{
             System.out.println("The accountID or password is not correct");
        }
//        if (mark == 0) {
//            System.out.println("The accountID or password is not correct");
//        }
    }
}
