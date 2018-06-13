package Server;

import javax.security.auth.login.Configuration;

public class Initialize {

    public static void init(){
        int num=Runtime.getRuntime().availableProcessors()*2;
        String stringNum=Integer.toString(num);
        Server.configurations.set("threadPoolSize",stringNum);
        Server.configurations.set("ASearchingAlgorithm","bfs");
    }

    //public static void initialize()
    //{

    //}

}
