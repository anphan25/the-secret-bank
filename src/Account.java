
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ASUS
 */
public class Account {

    String id;
    String name;
    String password;
    String confirmpass;
    double money;

    Scanner sc = new Scanner(System.in);
    
    public Account(String id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
                this.money=0;
	}

    public Account(String id, String name, String password, double money) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.money = money;
    }
    
    public String getId() {
        return id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpass() {
        return confirmpass;
    }

    public void setConfirmpass(String confirmpass) {
        this.confirmpass = confirmpass;
    }

    
    public String toString(){
        return id+" | "+name+" | "+password+" | "+money;
    }
}
