package autopista2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 *
 * @author Jorge Bernad
 */
public class Main {
    /*ATRIBUTOS*/
    public static int SALIDA = 0;    //SENTIDO SALIDA
    public static int ENTRADA = 1;   //SENTIDO ENTRADA
    public static String IZQUIERDA = "Izquierda";
    public static String DERECHA = "Derecha";
    public static String ABAJO = "Abajo";
    public static String NOMBREAUTOPISTA;
    static int  numentradas;
    static int numsalidas;
    private static Thread [] detectorentrada;  //varios consumidores
    private static Thread [] detectorsalida;  //varios consumidores
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //pedimos el número de entradas
        //ENTRADAS INICIALES 
        String leido=null;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader (isr);
        boolean validar=false;        
        while (validar==false){
            System.out.println("Deme el número de Entradas de la autopista");
            leido = (br.readLine());  //leemos número de Fardos en suelo
            if (Comprueba_Valor(leido)==true){
                validar=true;
            }
        }
        numentradas=Integer.parseInt (leido);            
        //SALIDAS INICIALES 
        br = new BufferedReader (isr);
        validar=false;        
        while (validar==false){
            System.out.println("Deme el número de Salidas de la autopista");
            leido = (br.readLine());  //leemos número de Fardos en suelo
            if (Comprueba_Valor(leido)==true){
                validar=true;
            }
        }
        numsalidas=Integer.parseInt (leido);
        //obtenemos el nombre de la autovia
        br = new BufferedReader (isr);
        System.out.println("Deme el nombre de la autopista");
        leido = (br.readLine());  //leemos número de Fardos en suelo
        NOMBREAUTOPISTA=leido;    
        
        System.out.println("La autopista "+NOMBREAUTOPISTA+" con "+numentradas+" entradas y "+numsalidas+" salidas");         
        Carretera autopista=new Carretera(NOMBREAUTOPISTA,numentradas,numsalidas);  
        //Inicializamos los  hilos    
        
        detectorentrada=new Thread[numentradas];
        detectorsalida=new Thread[numsalidas];

        for (int i=0;i<numentradas;i++){
            //lanzamos los hilos de entradas
            detectorentrada[i] = new Thread(new DetectordeEntrada(autopista, ENTRADA, IZQUIERDA, i, 0));  //creamos el hilo
            detectorentrada[i].start();               //lanzamos los consumidores
            for (int j=0;j<numsalidas;j++){
                detectorsalida[j] = new Thread(new DetectordeEntrada(autopista, SALIDA, DERECHA, 0, j));  //creamos el hilo
                detectorsalida[j].start();               //lanzamos los hilos de salidas
            }
        }
        
        
        
        
                
        
    }
    
    
    //Comprueba tipo dato introducido es entero    
    private static boolean Comprueba_Valor (String entrada){
        if (Pattern.matches("[0-9]{1,}",entrada))  //cumple patrón            
            return true;        
        else 
            return false;
    }      
}
