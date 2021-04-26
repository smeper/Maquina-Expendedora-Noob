package maquinaexpendedora;
import java.io.IOException;
import java.util.Scanner;
public class MaquinaExpendedora{
    private static Scanner sn = new Scanner(System.in);
    
    public static int [][] menu(String[][] nombresGolosinas, double[][] precio, int [][]cantidadProductos){
            int codigo1=0, codigo2=0;
      
            System.out.println("\nEstas son nuestras golosinas. ");
            for (int i=0; i<nombresGolosinas.length; i++) {
                for(int j=0; j<nombresGolosinas.length; j++){
                    System.out.print(nombresGolosinas[i][j] + " | ");
                }
                System.out.println();
            }
            
            System.out.println("\nEstos son sus precios. ");
            for (int i=0; i<precio.length; i++) {
                for(int j=0; j<precio.length; j++){
                    System.out.print(precio[i][j] + " ");
                }
                System.out.println();
            }
            
            System.out.println("\nEstos sus codigos. ");
            for(int i=0; i<precio.length; i++){
                for (int j=0; j<precio.length; j++){
                    System.out.print("[" + codigo1 + " " +codigo2 + "]" + " ");
                    codigo2++;
                }
                codigo1++;
                codigo2 = 0;
                System.out.println();
            }
            System.out.println("\nLa cantidad de producto que quedan en la maquina. ");
            for(int i=0; i<cantidadProductos.length; i++){
                for(int j=0; j<cantidadProductos.length; j++){
                    System.out.print(cantidadProductos[i][j] + " ");
                }
                System.out.println();
            }
            
            return cantidadProductos;
    }
     
    public static int pedido(String[][] nombresGolosinas, double[][] precio, int cod, int [][]cantidadProductos, int  VentasTotales){
        boolean comprar=true;
        int auxCOD, auxcod1, auxcod2;
        float dinero = 20;

        while(comprar){
           System.out.println("\nIntroduce el codigo del productor que quieres comprar,"
           + " sino desea comprar introduzca un codigo no admitido. ");
            cod = sn.nextInt();
            auxcod1=cod%10;
            auxcod2=cod/10;
            if(dinero>0.8){
            if((auxcod1==0 || auxcod1==1 || auxcod1==2 || auxcod1==3) &&
                    (auxcod2==0 || auxcod2==1 || auxcod2==2 || auxcod2==3)){
                    comprar=true;
                    auxCOD = cod/10;
                    cod=cod%10;
                    for(int i=0; i<cantidadProductos.length; i++){
                        for(int j=0; j<cantidadProductos.length; j++){
                            if(i==auxCOD && cod==j){
                                if(cantidadProductos[auxCOD][cod]>0){
                                    cantidadProductos[auxCOD][cod]--;
                                    VentasTotales++;
                                }
                                else{
                                    System.out.println("No tengo mas producto disponibles con ese codigo. ");
                                }
                                System.out.print("La cantidad de productos que quedan con dicho codigo " + cantidadProductos[i][j] + " ");
                            }
                        }
                    }
                    for(int i=0; i<nombresGolosinas.length; i++){
                        for(int j=0; j<nombresGolosinas.length; j++){
                            if(i==auxCOD && cod==j){
                                System.out.print("\nHas pedido un " + nombresGolosinas[i][j]);
                            }
                        }
                    }
                    for(int i=0; i<precio.length; i++){
                        for(int j=0; j<precio[i].length; j++){
                            if(i==auxCOD && cod==j){
                                System.out.print("\nCuyo precio es: " + precio[i][j]);
                                dinero -= precio[i][j];
                                System.out.print("\nTienes aun para gastar " + dinero + " €");
                            }
                        }
                    }
            }
            else{
                System.out.print("\nEl codigos no esta en la maquina. ");
                comprar=false;
            }
            }
            else{
                System.out.println("No tienes dinero suficiente. ");
            }
        }
        System.out.println("\nEl numero total de ventas es: " + VentasTotales);
        return VentasTotales;
    }
    
    
    public static void Tecnico(int password, int x, int cod, int [][]cantidadProductos, String [][]nombresGolosinas){
        System.out.println("Hola tecnico introduca el codigo para poder rellenar la maquina con productos. ");
        int passwdIntroducida = sn.nextInt(), cantidad, aux;
        boolean llena = false; 
        
        if(password==passwdIntroducida){
            System.out.println("Vamos a rellenar la maquina. ");

                for(int i=0; i<cantidadProductos.length; i++){
                    for(int j=0; j<cantidadProductos.length; j++){
                        if(cantidadProductos[i][j] == 5){
                            llena=true;
                        }
                        else{
                            llena=false;
                            if(cantidadProductos[i][j] != 5){
                                System.out.println("Introduce la cantidad de producto a añadir a la maquina. ");
                                cantidad = sn.nextInt();
                                if(cantidad>5 || cantidad<5){
                                    cantidadProductos[i][j] = 5;
                                    llena=true;
                                }
                                else{
                                    cantidadProductos[i][j] = cantidad;
                                    llena=true;
                                }
                            }
                        }
                    }
                }
        }
        else{
            System.out.println("Usted no es un tecnico. ");
        }
        x=0;
    }
    
    public static void main(String[] args)throws IOException{
        int password=123456;
        int x=0, cod=0,  VentasTotales=0;
        int [][]cantidadProductos = {{5,5,5,5}, {5,5,5,5}, {5,5,5,5}, {5,5,5,5}};
        String[][] nombresGolosinas = {{"KitKat", "Chicles fresa", "Lacasitos", "Palotes"},
                                       {"Kinder Bueno", "Bolsa Haribo", "Chetoos", "Twix"},
                                       {"Kinder Bueno", "M&M'S", "Papa Delta", "Chicles menta"},
                                       {"Lacasitos", "Crunch", "Milkybar", "KitKat"}};
        
        double[][] precio = {{1.1, 0.8, 1.5, 0.9}, 
                             {1.8, 1, 1.2, 1},
                             {1.8, 1.3, 1.2, 0.8},
                             {1.5, 1.1, 1.1, 1.1}};
        
         do{
            switch(x){    
                case 1:
                    pedido(nombresGolosinas, precio, cod,cantidadProductos, VentasTotales);
                    x=0;
                    break;
                case 2:
                    menu(nombresGolosinas, precio, cantidadProductos);
                    x=0;
                    break;
                case 3:  
                    Tecnico(password, x, cod, cantidadProductos, nombresGolosinas);
                    x=0;
                    break;
                default:
                    System.out.println("\n1: Para realizar un perido. ");
                    System.out.println("2: Mostrar Golosias. ");
                    System.out.println("3: Rellenar Golosinas. ");
                    System.out.println("4: Apagar Maquina. ");
                    x =  sn.nextInt();
                break;
            }            
         }while(x != 4);
         
    }
}