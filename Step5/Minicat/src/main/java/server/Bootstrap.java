package server;

import server.handle.Mapper;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.concurrent.*;

import static server.handle.Mapper.servletMap;

/**
 * Minicat的主类
 */
public class Bootstrap {

    /**定义socket监听的端口号*/
//    private int port = 8080;
    HashSet<Integer> portSet = new HashSet<>();

    /**
     * Minicat启动需要初始化展开的一些操作
     */
    public void start() throws Exception {

        // 加载解析相关的配置，web.xml
        Mapper mapper = new Mapper();
        mapper.loadServer();


        // 定义一个线程池
        int corePoolSize = 10;
        int maximumPoolSize =50;
        long keepAliveTime = 100L;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(50);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                handler
        );

        servletMap.forEach((k,v)->{
            int port = Integer.parseInt(k.substring(k.indexOf(":") + 1, k.indexOf("/")));
            if(!portSet.contains(port)){
                ServerSocket serverSocket = null;
                try {
                    serverSocket = new ServerSocket(port);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("=====>>>Minicat start on port：" + port);

                ServerSocket finalServerSocket = serverSocket;
                // TODO 先用不规范的方式实现，以后更新
                new Thread(() -> {
                    while(true) {
                        Socket socket = null;
                        try {
                            socket = finalServerSocket.accept();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        RequestProcessor requestProcessor = new RequestProcessor(socket,servletMap);
                        threadPoolExecutor.execute(requestProcessor);
                    }
                }).start();

            }
            portSet.add(port);
        });



    }


    /**
     * Minicat 的程序启动入口
     * @param args
     */
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        try {
            // 启动Minicat
            bootstrap.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
