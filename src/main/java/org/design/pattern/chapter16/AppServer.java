package org.design.pattern.chapter16;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * projectName: javaconcurrent
 * fileName: AppServer.java
 * packageName: org.design.pattern.chapter16
 * date: 2020年01月29日  10:00:52
 * create by enmuser
 * copyright(c) 1993-2020 enmuser.com
 */
public class AppServer extends Thread
{
    private final int port;

    private static final int DEFALUT_PORT = 12722;

    private volatile boolean start = true;

    private final List<ClientHandler> clientHandlers = new ArrayList<>();

    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    private ServerSocket server;

    public AppServer()
    {
        this(DEFALUT_PORT);
    }

    public AppServer(int port)
    {
        this.port = port;
    }

    @Override
    public void run()
    {
        try
        {
            this.server = new ServerSocket(port);
            while(start){
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                executor.submit(clientHandler);
                this.clientHandlers.add(clientHandler);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }finally
        {
            this.dispose();
        }
    }

    private void dispose()
    {
        System.out.println("dispose");
        this.clientHandlers.stream().forEach(ClientHandler::stop);
        this.executor.shutdown();
    }

    public void shutdown() throws IOException
    {
        this.start = false;
        this.interrupt();
        this.server.close();
    }
}
