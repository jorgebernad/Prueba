/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autopista2;

import static autopista2.Main.numentradas;
import static java.lang.Thread.sleep;
import static java.lang.Thread.yield;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge Bernad
 */
class Carretera {
    String NomAutopista;
    private int numCoches = 0;
    private int [] nEntradas; 
    private int [] nSalidas;

    /*constructor*/
    public Carretera(String NomAutopista, int numentradas, int numsalidas) {
        this.NomAutopista = NomAutopista;   
        nEntradas=new int [numentradas]; 
        nSalidas=new int [numsalidas]; 
        for (int i=0;i<numentradas;i++){  //inicializamos a 0
            nEntradas[i]=0;
        }
        for (int i=0;i<numsalidas;i++){
            nSalidas[i]=0;
        }
       
        
    }
    
    public int getNumCoches() {
        return numCoches;
    }
    

    
    
    /*VARIABLES COMPARATIVAS*/
    String Izq="Izquierda";
    String Der="Derecha";
    

    
    
    
    
    /*MÉTODOS SINCRONIZADOS*/
    public synchronized void MuestraNumCoches() {
        System.out.print("-----Total de coches en "+NomAutopista+ " en este momento: "+getNumCoches()+"\n");
    }
    
    public synchronized void MuestraSalidas(int entrada,int salida){
        //numero entradas
        
        for (int i=0;i<nEntradas.length;i++){
            System.out.print("En la entrada "+i+" han entrado "+nEntradas[i]+"coches\n");
        }
        //numero salidas
        System.out.println("En la salida");
        for (int i=0;i<nSalidas.length;i++){
            System.out.print(i+" han salido "+nSalidas[i]+" coches\n");
        }
        System.out.print("---------------------\n");
        
    }
    
    //ENTRADA POR ALGUNA DE LAS TRES ENTRADAS
    public synchronized void incrementacuenta(String lado, int entrada){
        String Entrada="";
        numCoches++;  //aumentamos el número de coches 
        nEntradas[entrada]++;  //aumentamos ese valor
        System.out.println("Entra un coche por la " + entrada + " de la autopista "+NomAutopista+"\n");  
        notifyAll();
    }
    
    /*MÉTODO DE SALIDA DEL PUENTE*/
    public synchronized void decrementacuenta(String lado, int salida) {
        while (numCoches==0){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Carretera.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("No pueden salir coches de la autopista  "+NomAutopista+" hay "+numCoches+"\n"); 
        }
        String Salida="";
        numCoches--;  //aumentamos el número de coches        
        nSalidas[salida]++;  //aumentamos ese valor
        System.out.println("Sale un coche por la " + salida + " de la autopista "+NomAutopista+"\n");
    }
    
    public synchronized boolean direccion(int sentido) {
        if (sentido==0)
            return true;
        else
            return false;
    }
}




    
    
    
    
    
    

