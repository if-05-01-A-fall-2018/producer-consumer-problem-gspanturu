/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcons_semaphore;

import java.util.logging.Level;
import java.util.logging.Logger;
import static prodcons_semaphore.ProdCons_Semaphore.getItemCount;
import static prodcons_semaphore.ProdCons_Semaphore.itemCount;
import static prodcons_semaphore.ProdCons_Semaphore.maxItemCount;
import static prodcons_semaphore.ProdCons_Semaphore.producer;
import static prodcons_semaphore.ProdCons_Semaphore.removeItem;
import static prodcons_semaphore.ProdCons_Semaphore.sem;


/**
 *
 * @author Sara P
 */
class Consumer extends Thread{
    static Boolean holding = false;
    
    @Override
    public void run(){
        while (true) {
            try {
                //awake          
                sem.acquire();
                holding = true;
                if (getItemCount() <= 0) {
                    //asleep
                    try {
                        holding = false;
                        sem.release();
                        synchronized(this){ wait(); }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(holding){
                    sem.release();
                    holding = false;
                }
                
                sem.acquire();
                removeItem();
                sem.release();
                
                
                sem.acquire();
                holding = true;
                if (itemCount >= maxItemCount-1) {
                    holding = false;
                    sem.release();
                    synchronized(producer){
                        producer.notify();
                    }
                }
                if(holding == true)
                {
                    holding = false;
                    sem.release();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
