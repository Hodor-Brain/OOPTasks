package Task2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class FTPServer {

    private int port;
    private String ip;
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private ByteBuffer inputBuffer = ByteBuffer.allocate(100);

    public FTPServer(int port, String ip) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(21));
        serverSocketChannel.configureBlocking(false);
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Server on.....");
    }

    public static void main(String args[]) throws IOException {
        FTPServer ftpServer = new FTPServer(21, "127.0.0.1");
        ftpServer.listen();
    }

    public void listen() throws IOException {
        while (!Thread.interrupted()) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectionKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                it.remove();
                handleKey(key);
            }
        }
    }

    public void handleKey(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            accept(key);
        }
        else if (key.isReadable()) {
            read(key);
        }
        else if (key.isWritable()) {
            write(key);
        }
    }

    public void accept(SelectionKey key) throws IOException {
        System.out.println("Establish connection。。。。");
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        SelectionKey socketkey = socketChannel.register(selector, SelectionKey.OP_WRITE);
        String response = "220 \r\n";
        socketkey.attach(response);
    }

    public void read(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        inputBuffer.clear();
        int count = socketChannel.read(inputBuffer);
        String command = new String(inputBuffer.array(), 0, count);
        inputBuffer.clear();
        if (command != null) {
            String[] datas = command.split("#");
            UserCommand commandSolver = CommandFactory.createCommand(datas[0]);
            String data = "";
            if (datas.length >= 2) {
                data = datas[1];
            }
            String response = commandSolver.getResult(data);
            key.interestOps(SelectionKey.OP_WRITE);
            key.attach(response);
        }
    }

    public void write(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        String response = ((String) key.attachment());
        ByteBuffer block = ByteBuffer.wrap(response.getBytes());
        block.clear();
        block.put(response.getBytes());
        block.flip();
        int i = socketChannel.write(block);
        System.out.println("Sent: " + i + " bytes of task2.data");
        System.out.println("The server sends task2.data to the client--：" + response);
        key.interestOps(SelectionKey.OP_READ);
    }
}
