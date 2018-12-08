/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcons;

/**
 *
 * @author Sara P
 */
public class ProdCons {

    public static final int maxItemCount = 10;
    public static int itemCount = 5;
    public static Producer producer = new Producer();
    public static Consumer consumer = new Consumer();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        producer.start();
        consumer.start();
    }
    
    public static void insertItem(){
        itemCount++;
    }
    
    public static void removeItem(){
        itemCount--;
    }
}
