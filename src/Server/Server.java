package Server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class Server {
    private int port;
    private int listeningInterval;
    private IServerStrategy serverStrategy;
    private volatile boolean stop;

    public Server(int port, int listeningInterval, IServerStrategy serverStrategy) {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;
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
                    Socket clientSocket = server.accept();

                    new Thread(() -> {
                        handleClient(clientSocket);
                    }).start();
                } catch (SocketTimeoutException e) {

                }
            }
            server.close();
        } catch (IOException e) {

        }
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

    public void stop() {

        stop = true;
    }
}
