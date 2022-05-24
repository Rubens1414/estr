package monopoly;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class GameSketch extends PApplet{
    //Herramientas:
    int tiempo,tiempo2;
    int cambio_es1=0;
    int cambio_ju=0;
    Casillas casillas = new Casillas();
    Lista_Circular Prueba = new Lista_Circular();
    Jugadores jugadores =new Jugadores();
    PVector posicionficha,posicionficha2,posicionficha3,posicionficha4,casilla;
    int velocidadf=50;
    int Dado=0,Dado2=0,c=0,c1=0,tc,Dadof=0,Dadof2=0,a=0;
    @Override
    public void settings() {
        size(1280, 720);
    }

    @Override
    public void setup() {
        posicionficha = new PVector(290,604);
        posicionficha2= new PVector(290,604);
        posicionficha3= new PVector(290,604);
        posicionficha4= new PVector(290,604);

    }

    @Override
    public void draw() {
        background(0);
        tiempo = tiempo+1;
        if(cambio_es1==1){
            tiempo2= tiempo2+1;
        }   
        presentacion();
        if(cambio_es1==2){
           casillas.mostrar();
        }
        if(cambio_ju==1){
           jugadores.ficha_jugador1();
           jugadores.ficha_jugador2();
           Dados();
           movimiento();

        }
        if(cambio_ju==2){
            jugadores.ficha_jugador1();
            jugadores.ficha_jugador2();
            jugadores.ficha_jugador3();
            Dados();
            movimiento();

        }
        if(cambio_ju==3){
            jugadores.ficha_jugador1();
            jugadores.ficha_jugador2();
            jugadores.ficha_jugador3();
            jugadores.ficha_jugador4();
            Dados();
            movimiento();

        }
    }
    void presentacion(){
        PImage marca,inicio,seleccion,tablero;
        marca = loadImage("marca.png");
        inicio = loadImage("inicio.png");
        seleccion= loadImage("seleccion.png");
        tablero=loadImage("Tablero.png");
        if(cambio_es1==0){
        if(tiempo<=10){
          image(marca, 0,0);
          
        }else{
         image(inicio, 0,0);
         tiempo2=0;
        }
      }
        if(cambio_es1==1){
         
          image(seleccion,0,0);
        }
        if(cambio_es1==2){

          image(tablero,0,0);           
        }
    }
    
   
   void mostrar_tarjeta_casilla(){
            casillas.mostrar();
    }
    class Nodo {
        private int valor;
        private Nodo siguiente;
        
        public void Nodo(){
            this.valor = 0;
            this.siguiente = null;
        }
        
        
        public int getValor() {
            return valor;
        }
    
        public void setValor(int valor) {
            int info_casilla;
            this.valor = valor;
            info_casilla=valor;
            casillas.informacion(info_casilla);
            
        }
    
        public Nodo getSiguiente() {
            return siguiente;
        }
    
        public void setSiguiente(Nodo siguiente) {
            this.siguiente = siguiente;
        }   
    }
    class Lista_Circular {
        // Puntero que indica el inicio de la lista o conocida tambien
        // como cabeza de la lista.
        private Nodo inicio;
        // Puntero que indica el final de la lista o el ultimo nodo.
        private Nodo ultimo;
        // Variable para registrar el tamaño de la lista.
        private int tamanio;
        /**
         * Constructor por defecto.
         */
        public void Lista(){
            inicio = null;
            ultimo = null;
            tamanio = 0;
        }
        /**
         * Consulta si la lista esta vacia.
         * @return true si el primer nodo (inicio), no apunta a otro nodo.
         */
        public boolean esVacia(){
            return inicio == null;
        }
        /**
         * Consulta cuantos elementos (nodos) tiene la lista.
         * @return numero entero entre [0,n] donde n es el numero de elementos
         * que contenga la lista.
         */
        public int getTamanio(){
            return tamanio;
        }
        /**
         * Agrega un nuevo nodo al final de la lista circular.
         * @param valor a agregar.
         */
        public void agregarAlFinal(int valor){
            // Define un nuevo nodo.
            Nodo nuevo = new Nodo();
            // Agrega al valor al nodo.
            nuevo.setValor(valor);
            // Consulta si la lista esta vacia.
            if (esVacia()) {
                // Inicializa la lista agregando como inicio al nuevo nodo.
                inicio = nuevo;
                // De igual forma el ultimo nodo sera el nuevo.
                ultimo = nuevo;
                // Y el puntero del ultimo debe apuntar al primero.
                ultimo.setSiguiente(inicio);
            // Caso contrario el nodo se agrega al final de la lista.
            } else{
                // Apuntamos con el ultimo nodo de la lista al nuevo.
                ultimo.setSiguiente(nuevo);
                // Apuntamos con el nuevo nodo al inicio de la lista.
                nuevo.setSiguiente(inicio);
                // Como ahora como el nuevo nodo es el ultimo se actualiza
                // la variable ultimo.
                ultimo = nuevo;
            }
            // Incrementa el contador de tamaño de la lista
            tamanio++;
        }
        /**
         * Agrega un nuevo nodo al inicio de la lista circular.
         * @param valor a agregar.
         */   
        public void agregarAlInicio(int valor){
            // Define un nuevo nodo.
            Nodo nuevo = new Nodo();
            // Agrega al valor al nodo.
            nuevo.setValor(valor);
            // Consulta si la lista esta vacia.
            if (esVacia()) {
                // Inicializa la lista agregando como inicio al nuevo nodo.
                inicio = nuevo;
                // De igual forma el ultimo nodo sera el nuevo.
                ultimo = nuevo;  
                // Y el puntero del ultimo debe apuntar al primero.
                ultimo.setSiguiente(inicio);
            // Caso contrario va agregando los nodos al inicio de la lista.
            } else{
                // Une el nuevo nodo con la lista existente.
                nuevo.setSiguiente(inicio);
                // Renombra al nuevo nodo como el inicio de la lista.
                inicio = nuevo;
                // El puntero del ultimo debe apuntar al primero.
                ultimo.setSiguiente(inicio);
            }
            // Incrementa el contador de tamaño de la lista.
            tamanio++;
        }
       
        public int getValor(int posicion) throws Exception{
            // Verifica si la posición ingresada se encuentre en el rango
            // >= 0 y < que el numero de elementos del la lista.
            if(posicion>=0 && posicion<tamanio){
                // Consulta si la posicion es el inicio de la lista.
                if (posicion == 0) {
                    // Retorna el valor del inicio de la lista.
                    return inicio.getValor();
                }else{
                    // Crea una copia de la lista.
                    Nodo aux = inicio;
                    // Recorre la lista hasta la posición ingresada.
                    for (int i = 0; i < posicion; i++) {
                        aux = aux.getSiguiente();
                    }
                    // Retorna el valor del nodo.
                    return aux.getValor();
                }
            // Crea una excepción de Posicion inexistente en la lista.
            } else {
                throw new Exception("Posicion inexistente en la lista.");
            }
        }
       
        
        
        public void listar(){
            // Verifica si la lista contiene elementoa.
            if (!esVacia()) {
                // Crea una copia de la lista.
                Nodo aux = inicio;
                // Posicion de los elementos de la lista.
                int i = 0;
               
                // Recorre la lista hasta llegar nuevamente al incio de la lista.
                do{
                    // Imprime en pantalla el valor del nodo.
                    System.out.print("[ " + aux.getValor() + " ]");
                    
                    // Avanza al siguiente nodo.
                    aux = aux.getSiguiente();
                    // Incrementa el contador de la posión.
                    i++;
                }while(aux != inicio);
            }
        }
    }
    class Casillas {
        private String nombre_casilla="Default.png";
        void informacion(int info){
               if(info==2){
                this.nombre_casilla="Benito.png";
               }
               if(info==3){
                this.nombre_casilla="El_Rubens.png";
               }
               if(info==4){
                this.nombre_casilla="Parada_Coolitoral.png";
               }
               if(info==5){
                this.nombre_casilla="El_Padi.png";
               }
               if(info==6){
                this.nombre_casilla="Donde_Lopez.png";
               }
               if(info==7){
                this.nombre_casilla="La_Garosa.png";
               }
               if(info==8){
                this.nombre_casilla="El_Sephiroth.png";
               }
               if(info==9){
                this.nombre_casilla="Empresa_air-e.png";
               }
               if(info==10){
                this.nombre_casilla="El_Cachon.png";
               }
               if(info==11){
                this.nombre_casilla="La_Posada.png";
               }
               if(info==12){
                this.nombre_casilla="Parada_Sobusa.png";
               }
               if(info==13){
                this.nombre_casilla="La_Ñapa.png";
               }
               if(info==14){
                this.nombre_casilla="El_Cuchufli.png";
               }
               if(info==15){
                this.nombre_casilla="La_Chicha.png";
               }
               if(info==16){
                this.nombre_casilla="El_Simpatico.png";
               }
               if(info==17){
                this.nombre_casilla="La_Tramacua.png";
               }
               if(info==18){
                this.nombre_casilla="El_Romo.png";
               }
               if(info==19){
                this.nombre_casilla="Parada_Sodis.png";
               }
               if(info==20){
                this.nombre_casilla="La_susodicha.png";
               }
               if(info==21){
                this.nombre_casilla="Donde_Alejo.png";
               }
               if(info==22){
                this.nombre_casilla="Empresatriple_A.png";
               }
               if(info==23){
                this.nombre_casilla="La_inombrable.png";
               }
               if(info==24){
                this.nombre_casilla="El_chefcito.png";
               }
               if(info==25){
                this.nombre_casilla="El_carecodo.png";
               }
               if(info==26){
                this.nombre_casilla="El_chufi.png";
               }
               if(info==27){
                this.nombre_casilla="Parada_Transmetro.png";
               }
               if(info==28){
                this.nombre_casilla="La_flaca.png";
               }
               if(info==29){
                this.nombre_casilla="Donde_juan.png";
               }
   
        }
       void mostrar(){
            PImage casillas;
            casillas = loadImage(nombre_casilla);
            image(casillas,112,321);
            
        }
    }
    void Dados(){
        PImage dado1_1,dado2_2,boton;
        boton=loadImage("Boton_dado.png");
        image(boton,1007,400); 
        if(Dado==0){
            dado1_1 = loadImage("Dado_default.png");
            image(dado1_1,1040,326); 
        }
        if(Dado==1){
            dado1_1 = loadImage("Dado_1.png");
            image(dado1_1,1040,326); 
            if(Dadof==1){
                c=50;
            }
        }
        if(Dado==2){
            dado1_1 = loadImage("Dado_2.png");
            image(dado1_1,1040,326); 
            
            if(Dadof==2){
                c=100;
            }
        }
        if(Dado==3){
            dado1_1 = loadImage("Dado_3.png");
            image(dado1_1,1040,326); 
            if(Dadof==3){
                c=150;

            }
        }
        if(Dado==4){
            dado1_1 = loadImage("Dado_4.png");
            image(dado1_1,1040,326); 
            if(Dadof==4){
                c=200;

            }
        }
        if(Dado==5){
            dado1_1 = loadImage("Dado_5.png");
            image(dado1_1,1040,326); 
            c=250;
            if(Dadof==5){
            }
        }
        if(Dado==6){
            dado1_1 = loadImage("Dado_6.png");
            image(dado1_1,1040,326); 

            if(Dadof==6){
                c=300;

            }
        }
        if(Dado2==0){
            dado2_2 = loadImage("Dado_default.png");
            image(dado2_2,1110,326); 
            
        }
        if(Dado2==1){
            dado2_2 = loadImage("Dado_1.png");
            image(dado2_2,1110,326); 
            
            if(Dadof2==1){
                c1=50;
            }
            

        }
        if(Dado2==2){
            dado2_2 = loadImage("Dado_2.png");
            image(dado2_2,1110,326); 
            
            if(Dadof2==2){
                c1=100;
            }
        }
        if(Dado2==3){
            dado2_2 = loadImage("Dado_3.png");
            image(dado2_2,1110,326); 
            
            if(Dadof2==3){
                c1=150;
            }
        }
        if(Dado2==4){
            dado2_2 = loadImage("Dado_4.png");
            image(dado2_2,1110,326); 
            if(Dadof2==4){
                c1=200;
            }
            
        }
        if(Dado2==5){
            dado2_2 = loadImage("Dado_5.png");
            image(dado2_2,1110,326); 
            if(Dadof2==5){
                c1=250;
            }
            

        }
        if(Dado2==6){
            dado2_2 = loadImage("Dado_6.png");
            image(dado2_2,1110,326); 
            
            if(Dadof2==6){
                c1=300;
            }
        }
    }
    class Jugadores {
        void ficha_jugador1(){
            PImage ficha1;
            ficha1 = loadImage("Empanada.png");
            image(ficha1,posicionficha.x,posicionficha.y);
        }
        void ficha_jugador2(){
            PImage ficha1;
            ficha1 = loadImage("Arepa.png");
            image(ficha1,posicionficha2.x,posicionficha2.y);
        }
        void ficha_jugador3(){
            PImage ficha1;
            ficha1 = loadImage("Papa.png");
            image(ficha1,posicionficha3.x,posicionficha3.y);
        }
        void ficha_jugador4(){
            PImage ficha1;
            ficha1 = loadImage("Dedito.png");
            image(ficha1,posicionficha4.x,posicionficha4.y);
        }
    }
    public void collision(){
        
    }
    
    void movimiento(){
         
         if(Dadof>=1&&Dadof2>=1&&a==0){
            tc=c+c1;
            
            posicionficha.y=posicionficha.y -tc;
            if(posicionficha.x >= 254 && posicionficha.x<=338 && posicionficha.y>=40 && posicionficha.y<=130 ){
                tc=0;
                a=1;
             }
             Dadof=0;
             Dadof2=0;
         }
         if(Dadof>=1&&Dadof2>=1&&a==1){
            tc=c+c1;
            posicionficha.x=posicionficha.x +tc;
            Dadof=0;
            Dadof2=0;
         }
    }
    
    @Override
    public void keyPressed(){
       if(keyCode==UP){
           posicionficha.y=posicionficha.y - velocidadf;
          if(posicionficha.x >= 260 && posicionficha.x<=338 && posicionficha.y>=537 && posicionficha.y<=584 ){
              System.out.println("xd");
          }
       }
    }
    @Override

    public void mousePressed(){
        //Anotar puntos
        if(mouseButton==LEFT && mouseX>=259  && mouseX <= 337 && mouseY >= 595  && mouseY <= 674 &&cambio_es1==2 ){
            Prueba.agregarAlInicio(2);
        }
        if(mouseButton==LEFT){
          System.out.println("x: "+mouseX+" y: "+mouseY);
           
        }
    
        if(mouseButton==LEFT && mouseX>=1052 && mouseX <= 1169 && mouseY >= 423 && mouseY <= 464&&cambio_ju>=1){
            Dado = (int) (Math.random()*6+1);
            Dado2 = (int) (Math.random()*6+1);
            Dadof = Dado;
            Dadof2 = Dado2;
           
        }               
        
        //CAMBIOS DE ESCENAS:
        //ESCENA DE SELECCION:
        if(mouseButton==LEFT && mouseX>=403 && mouseX <= 870 && mouseY >= 389 && mouseY <= 441&&cambio_es1==0){
        cambio_es1=1;
        }
        //ESCENA PARA VOLVER :
        if(mouseButton==LEFT && mouseX>=543 && mouseX <= 737 && mouseY >= 594 && mouseY <= 642 &&cambio_es1==1){
            cambio_es1=0;       
            tiempo2=0;
        }
        //Tablero:
        // 2 jugadores
        if(mouseButton==LEFT && mouseX>=250 && mouseX <= 382 && mouseY >= 358 && mouseY <= 488 && cambio_es1==1&&tiempo2>2){
            cambio_es1=2;       
            cambio_ju=1;
        }
        //3 jugadores
        if(mouseButton==LEFT && mouseX>=565  && mouseX <= 700 && mouseY >= 356 && mouseY <= 488 && cambio_es1==1&&tiempo2>2){
            cambio_es1=2;
            cambio_ju=2;       
        }
        // 4 jugadores
        if(mouseButton==LEFT && mouseX>=878 && mouseX <= 1011 && mouseY >= 356 && mouseY <= 488 && cambio_es1==1&&tiempo2>2){
            cambio_es1=2;
            cambio_ju=3;       
        }
    }
    
    public void run() {
        String[] processingArgs = { this.getClass().getName() };
        PApplet.runSketch(processingArgs, this);
    
    }
    
}