/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autopista2;

import static java.lang.Thread.sleep;
import static java.lang.Thread.yield;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge Bernad
 */
class DetectordeEntrada extends Thread{
    /*ATRIBUTOS*/
    private Carretera autopista;  //escenario trabajo
    private int sentido;              //entrar salir
    private String lado;              //izquierda, derecha
    private int identrada;
    private int idsalida;

    
    /*CONSTRUCTOR*/

    public DetectordeEntrada(Carretera autopista, int sentido, String lado, int identrada,int idsalida) {
        this.autopista = autopista;
        this.sentido = sentido;
        this.lado = lado;
        this.identrada = identrada;
        this.idsalida= idsalida;
    }
    
    public void run(){        
        do{
            if (autopista.direccion(sentido)==true)
                autopista.incrementacuenta(lado,identrada);   //entrar
            else                
                autopista.decrementacuenta(lado,idsalida);    //salir
            autopista.MuestraNumCoches(); 
            autopista.MuestraSalidas(identrada,idsalida);            
        yield();                      
        }while(true);
    }
}
