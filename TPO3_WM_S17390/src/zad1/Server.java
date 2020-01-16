/**
 * @author Wrzesień Maciej S17390
 */

package zad1;


import java.net.InetSocketAddress;
import java.nio.channels.*;

public class Server {
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public static void main(String[] args) {
        new Server();
    }

    public Server() {

        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress("localhost", 9876));
            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
