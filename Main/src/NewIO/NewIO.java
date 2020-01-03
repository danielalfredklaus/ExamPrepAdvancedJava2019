package NewIO;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NewIO {

    public static void main(String[] args) throws Exception {
        Path path = Paths.get("E:\\Users\\Daniel\\Documents\\CloudStation\\Studium\\Erasmus HS 19\\Scalable Enterprises\\enterprise-applications-examples.zip");
        FileChannel channel = FileChannel.open(path);
        //create buffer with capacity of 4 bytes
        channel.position(0);
        ByteBuffer buf = ByteBuffer.allocate(4); //Header signature
        buf.order(ByteOrder.LITTLE_ENDIAN);
        channel.read(buf); //read into buffer.
        buf.rewind();
        System.out.printf("%x%n", buf.getInt());

        channel.position(18); //Position at first byte of compressed size
        ByteBuffer buf2 = ByteBuffer.allocate(4); //Compressed size
        buf2.order(ByteOrder.LITTLE_ENDIAN);
        channel.read(buf2); //read into buffer.
        buf2.rewind();
        System.out.printf("%x%n", buf2.getInt());

        channel.position(26); //Position at first byte of file name length
        ByteBuffer buf3 = ByteBuffer.allocate(2);
        buf3.order(ByteOrder.LITTLE_ENDIAN);
        channel.read(buf3); //read into buffer.
        buf3.rewind();
        short length = buf3.getShort();
        System.out.printf("%x%n", length);

        channel.position(30); //Position at file name
        ByteBuffer buf4 = ByteBuffer.allocate(length);
        buf4.order(ByteOrder.LITTLE_ENDIAN);
        channel.read(buf4); //read into buffer.
        buf4.rewind();
        byte[] array = buf4.array();
        String s = new String(array);
        System.out.printf("%s%n", s);

        ByteBuffer buf5 = ByteBuffer.allocate(4);






    }

}
