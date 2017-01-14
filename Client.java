package chatproject;

import java.io.*;
import java.net.*;

/**
 *
 * @author alessandro
 */
public class Client {
    protected Socket connection;
    protected int port = 3500;
    protected String serverName = "localhost";
    protected BufferedReader sIn;
    protected BufferedReader keyboard;
    protected PrintWriter sOut;
    protected String receive;
    protected String send;
    protected boolean exit = false;

    public Socket waitConnection() throws IOException{
        connection = new Socket(serverName, port);
        keyboard = new BufferedReader(new InputStreamReader(System.in));

        sIn = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        sOut = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
        return connection;
    }

    public void chat() throws IOException{
        System.out.println("inizio chat...");
        System.out.println("digitare stop per terminare la chat");
        System.out.println("INIZIA LA CHAT-->  ");
        while(!exit && !(send = keyboard.readLine()).equals("stop")){

            //invio la risposta al server
            sOut.print(send+"\n");
            sOut.flush();
            //leggo la risposta del server
            receive = sIn.readLine();
           if(receive.equals("stop")){
               exit = true;
           }
            System.out.println("Risposta server-->"+" "+receive);
            System.out.println("Invia al server-->");
        }
        System.out.println("CHAT IN CHIUSURA");
        connection.close();

    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Client c = new Client();
        c.waitConnection();
        c.chat();
    }
}
