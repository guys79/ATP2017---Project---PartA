package Server;

import java.util.concurrent.ExecutorService;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

class RunMaze implements Runnable{

    private Socket clientSocket;
    private IServerStrategy serverStrategy;
    public RunMaze(Socket s,IServerStrategy is)
    {
        clientSocket=s;
        serverStrategy=is;
    }

    private void handleClient(Socket clientSocket) {
        try {

            serverStrategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.getInputStream().close();
            clientSocket.getOutputStream().close();
            clientSocket.close();
        } catch (IOException e) {

        }
    }

    public void run(){
        handleClient(clientSocket);
    }
}

public class Server {
    private int port;
    private int listeningInterval;
    private IServerStrategy serverStrategy;
    private volatile boolean stop;
    private ThreadPoolExecutor executorservice;


    public Server(int port, int listeningInterval, IServerStrategy serverStrategy) {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;
        int threadpoolSize=Runtime.getRuntime().availableProcessors()*2;
        ExecutorService executorService=Executors.newCachedThreadPool();
        this.executorservice= (ThreadPoolExecutor)executorService;
        this.executorservice.setCorePoolSize(threadpoolSize);
    }

    public void start() {
        new Thread(() -> {
            runServer();
        }).start();
    }

    private void runServer() {
        try {
            ServerSocket server = new ServerSocket(port);
            server.setSoTimeout(listeningInterval);

            while (!stop) {
                try {
                    Socket clientSocket=server.accept();

                    this.executorservice.execute(new RunMaze(clientSocket,this.serverStrategy));
                } catch (SocketTimeoutException e) {

                }
            }

            server.close();
        } catch (IOException e) {

        }
    }



    public void stop() {

        stop = true;
    }
}
