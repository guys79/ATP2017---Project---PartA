package Server;

import java.util.concurrent.ExecutorService;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * This class implements runnable so we can use it in threadPool
 */
class RunMaze implements Runnable{

    private Socket clientSocket;//The serverSocket
    private IServerStrategy serverStrategy;//The strategy

    /**
     * The constructor of the RunMaze
     * @param socket - The given server socket
     * @param strategy- The given strategy
     */
    public RunMaze(Socket socket,IServerStrategy strategy)
    {
        clientSocket=socket;
        serverStrategy=strategy;
    }

    /**
     * This function will handle the client using the given strategy
     * @param clientSocket - The clientSocket
     */

    private void handleClient(Socket clientSocket) {
        try {

            //Implementing the strategy
            serverStrategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            //Closing the streams of the client
            clientSocket.getInputStream().close();
            clientSocket.getOutputStream().close();
            clientSocket.close();
        } catch (IOException e) {

        }
    }

    /**
     * Summoning the handleClient function
     */
    public void run(){
        handleClient(clientSocket);
    }
}

/**
 * This class will represent the server
 */
public class Server {
    private int port;//The port
    private int listeningInterval;//The listening intervals
    private IServerStrategy serverStrategy;//The server strategy
    private volatile boolean stop;//The variable that is responsible to stop the server


    /**
     * The constructor
     * @param port - The given port
     * @param listeningInterval - The listening intervals
     * @param serverStrategy - The server strategy
     */
    public Server(int port, int listeningInterval, IServerStrategy serverStrategy) {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;

    }

    /**
     * This function will use a new thread to run the server
     */
    public void start() {
        new Thread(() -> {
            runServer();//Runs the server
        }).start();
    }

    /**
     * This function is responsible for the wat the server runs
     */
    private void runServer() {
        //Initializing the threadPool
        int threadPoolSize=Runtime.getRuntime().availableProcessors()*2;
        ExecutorService executorService=Executors.newCachedThreadPool();
        ThreadPoolExecutor threadPoolExecutor= (ThreadPoolExecutor)executorService;
        threadPoolExecutor.setCorePoolSize(threadPoolSize);

        try {
            //Creating the server socket
            ServerSocket server = new ServerSocket(port);
            server.setSoTimeout(listeningInterval);

            //As long as we don;t tell the server to stop
            while (!stop) {
                try {
                    //The clientSocket of the client that is connecting to the server
                    Socket clientSocket=server.accept();
                    //Executing the client handler (adding the runnable to the threadPool and executing it)
                    threadPoolExecutor.execute(new RunMaze(clientSocket,this.serverStrategy));
                } catch (SocketTimeoutException e) {
                    e.getStackTrace();
                }
            }

            //Close the server
            server.close();
            threadPoolExecutor.shutdown();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }


    /**
     * This function will stop the server's running.
     */
    public void stop() {

        stop = true;
    }
}
