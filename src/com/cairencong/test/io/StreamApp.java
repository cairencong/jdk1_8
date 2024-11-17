package com.cairencong.test.io;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author cairencong
 * @date 2024/11/17  17:12
 * @version 1.0
 * @desc 测试Socket通信进行读写操作
 * 注意：需要先启动服务端，再运行客户端
 * 0. 启动服务端：
 * 1. 打开命令行
 * 2. 打开监听 nc -l 111
 * 3. 运行客户端：运行StreamApp.java
 * 4. 看到服务器端显示的信息 Hello, world!
 * 5.在服务器端输入from server
 * 6.回车
 * 7.客户端显示from server
 *
 */

public class StreamApp {
    public static void main(String[] args) {

        try {
            Socket socket = new Socket(InetAddress.getByName("localhost"), 111);
//            PrintWriter writer = new PrintWriter(
//                    new BufferedWriter(
//                            new OutputStreamWriter(
//                                    socket.getOutputStream())));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            writer.println("Hello, world!");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream())
            );
            String response = reader.readLine();
            System.out.println(response);

            //writer.flush();
            writer.close();
            reader.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
