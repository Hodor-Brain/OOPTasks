package Task7;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private static final Logger log = Logger.getLogger(Client.class.getName());
    private SocketChannel socketChannel;
    private ByteBuffer buffer;

    public Client(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
        this.buffer = ByteBuffer.allocate(256);
    }

    public ByteBuffer getBuffer() {
        return buffer;
    }

    public static void main(String[] args) {
        Medicine medicine = new Medicine("Penicillin", 1, "HealthAndCare");
        try {
            new Client(SocketChannel.open(new InetSocketAddress("localhost", 4545))).run(medicine);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Exception: ", e);
        }
    }

    public void run(Medicine medicine) throws IOException {
        sendStudent(medicine);
        respondToClient();
    }

    public void sendStudent(Medicine medicine) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(medicine);
        objectOutputStream.flush();
        buffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        socketChannel.write(buffer);
    }

    public void respondToClient() throws IOException {
        ByteBuffer inputBuffer = ByteBuffer.allocate(256);
        socketChannel.read(inputBuffer);
        log.info("Server response: ");
        String response = new String(inputBuffer.array()).trim();
        log.info(response);
        buffer.clear();
    }
}
