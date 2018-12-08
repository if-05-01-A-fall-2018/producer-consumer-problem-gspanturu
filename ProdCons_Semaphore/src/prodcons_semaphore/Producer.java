/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcons_semaphore;

import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.runtime.PropertyMap.getCount;
import static prodcons_semaphore.ProdCons_Semaphore.consumer;
import static prodcons_semaphore.ProdCons_Semaphore.getItemCount;
import static prodcons_semaphore.ProdCons_Semaphore.insertItem;
import static prodcons_semaphore.ProdCons_Semaphore.maxItemCount;
import static prodcons_semaphore.ProdCons_Semaphore.sem;

/**
 *
 * @author Sara P
 */
class Producer extends Thread{
   static Boolean holding = false;
    @Override
    public void run(){
        while (true) {
            try {
                //awake    
                sem.acquire();
                holding = true;
                if (getItemCount() >= maxItemCount) {
                    //asleep
                    try {
                        holding = false;
                        sem.release();
                        synchronized(this){ wait(); }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(holding){
                    sem.release();
                    holding = false;
                }
                
                
                sem.acquire();
                insertItem();
                sem.release();
                
                sem.acquire();
                holding = true;
                if (getItemCount() >= 1) {
                    holding = false;
                    sem.release();
                    synchronized(consumer){
                        consumer.notify();
                    }
                }
                if(holding){
                    sem.release();
                    holding = false;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
}
