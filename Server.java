package chatproject;

import java.io.*;
import java.net.*;

/**
 *
 * @author alessandro
 */
public class Server {
    protected ServerSocket server;
    protected Socket connection;

    protected int port = 3500;

    protected BufferedReader keyboard;
    protected BufferedReader sIn;
    protected PrintWriter sOut;

    protected String read;
    protected String send;

    protected boolean exit = false;


    public Socket waitConnection() throws IOException{
        server = new ServerSocket(port);
        System.out.println("in attesa di connessione con il client...");
        connection = server.accept();
        System.out.println("connessione stabilita");

        sIn = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        sOut = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
        return connection;
    }

    public void chat() throws IOException{
        keyboard = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("in attesa della risposta del client");

        while(!exit && !(read = sIn.readLine()).equals("stop")){
            System.out.println("Risposta client-->"+" "+read);
            System.out.println("Invia al client:   ");

            send = keyboard.readLine();
            if(send.equals("stop")){
                exit = true;
            }
            sOut.write(send+"\n");
            sOut.flush();
        }
        System.out.println("CHAT IN CHIUSURA");
        connection.close();

    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Server s = new Server();
        s.waitConnection();
        s.chat();
    }
}
