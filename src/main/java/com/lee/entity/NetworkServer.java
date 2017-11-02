package com.lee.entity;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by lipengchun on 2017/11/2.
 */
public class NetworkServer {

    private final AsynchronousChannelGroup group;
    private AsynchronousServerSocketChannel server;

    private final int port;

    public NetworkServer() throws IOException {
        this(null);
    }

    public NetworkServer(Integer port) throws IOException {
        group = AsynchronousChannelGroup.withFixedThreadPool(10, Executors.defaultThreadFactory());
        this.port = port;

    }

    public void open() throws IOException {
        server = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(port));

        server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
            @Override
            public void completed(AsynchronousSocketChannel result, Void attachment) {
                System.out.println("连接一个客户端");
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                System.out.println("连接失败");
            }
        });
    }

    public void close() throws IOException, InterruptedException {
        if (!group.isShutdown())
            group.shutdown();

        if (!group.isTerminated())
            group.shutdownNow();

        group.awaitTermination(10, TimeUnit.SECONDS);
    }
}
