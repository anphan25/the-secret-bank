
import java.util.ArrayList;
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
public class MainMenu {
    ArrayList<String> ds=new ArrayList<>();
    Scanner sc=new Scanner(System.in);
    
    public void addItem(String s){
        ds.add(s);
    }
    public int getChoicce(){
       int choice=0;
        System.out.println("----Account Manager----");
        for(int i=0;i<ds.size();i++){
        System.out.println((i+1)+"-"+ds.get(i));
        }
        System.out.print("Input your choice: ");
        choice=Integer.parseInt(sc.nextLine());
        return choice;
    }
}
