/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcons_semaphore;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Sara P
 */
public class ProdCons_Semaphore {

    public static final int maxItemCount = 10;
    public static int itemCount = 10;
    public static Semaphore sem = new Semaphore(1,true);
    public static Producer producer = new Producer();
    public static Consumer consumer = new Consumer();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        producer.start();
        consumer.start();
    }
    
    public static void insertItem(){
        itemCount++;
        System.out.println("+  " + itemCount);
        if (itemCount < 0) {
            System.out.println("error");
        }
    }
    
    public static int getItemCount(){
        return itemCount;
    }

    static void removeItem() {
        itemCount--;
        System.out.println("- " + itemCount);
        if (itemCount < 0) {
            System.out.println("error");
        }
    }
    
}
