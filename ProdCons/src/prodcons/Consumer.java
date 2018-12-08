/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcons;

import java.util.logging.Level;
import java.util.logging.Logger;
import static prodcons.ProdCons.itemCount;
import static prodcons.ProdCons.maxItemCount;
import static prodcons.ProdCons.producer;
import static prodcons.ProdCons.removeItem;

/**
 *
 * @author Sara P
 */
class Consumer extends Thread {
    @Override
        public void run(){
        while (true) {
            System.out.println("Consumer is awake");
            if (itemCount <= 0) {
                System.out.println("Consumer is asleep");
                try {
                    synchronized(this){ wait(); }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            removeItem();
            if (itemCount >= maxItemCount-1) {
                synchronized(producer){ producer.notify();}
            }
        } 
    }    
}
