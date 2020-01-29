package org.design.pattern.chapter16;

import java.io.*;
import java.net.Socket;

/**
 * projectName: javaconcurrent
 * fileName: ClientHandler.java
 * packageName: org.design.pattern.chapter16
 * date: 2020年01月29日  10:32:39
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class ClientHandler implements Runnable
{
    private final Socket socket;

    private volatile boolean running = true;

    public ClientHandler(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        try(InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter printWriter = new PrintWriter(outputStream))
        {
            while(running){
                String message = br.readLine();
                if(null == message)
                    break;
                System.out.println("Come from Client -> "+message);
                printWriter.write("echo: "+message+"\n");
                printWriter.flush();
            }

        }catch(IOException e){
            e.printStackTrace();
            this.running = false;
        }finally
        {
            this.stop();
        }
    }

    public void stop(){
        if(!running){
            return;
        }
        this.running = false;
        try
        {
            this.socket.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
