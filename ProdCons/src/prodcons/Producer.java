/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcons;

import java.util.logging.Level;
import java.util.logging.Logger;
import static prodcons.ProdCons.consumer;
import static prodcons.ProdCons.insertItem;
import static prodcons.ProdCons.itemCount;
import static prodcons.ProdCons.maxItemCount;

/**
 *
 * @author Sara P
 */
class Producer extends Thread{
   @Override
   public void run(){
     while (true) {
            System.out.println("Producer is awake");
            //item = produce();
            if (itemCount >= maxItemCount) {
                System.out.println("Producer sleeps");
                try {
                    synchronized(this){ wait(); }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            insertItem();
            if (itemCount >= 1) {
               synchronized(consumer){
                   consumer.notify();
               }}
            }  
   } 
}
