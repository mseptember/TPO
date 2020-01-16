package zad1;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Bufor {

    public static void writeMessage(String text, SocketChannel socketChannel) {
        try {
            ByteBuffer buffer = Charset.forName("ISO-8859-2").encode(text);

            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void readMessage(SocketChannel socketChannel) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.setLength(0);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.clear();

        try {
            //todo
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
