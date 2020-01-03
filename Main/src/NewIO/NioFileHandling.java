package NewIO;

import lombok.Cleanup;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NioFileHandling {
    public static void main(String[] args) throws Exception{
        Path path = Paths.get("E:\\Users\\Daniel\\Documents\\CloudStation\\Studium\\Erasmus HS 19\\Scalable Enterprises\\enterprise-applications-examples.zip");
        @Cleanup FileChannel channel = FileChannel.open(path, StandardOpenOption.READ);

        var buf = ByteBuffer.allocate(10_000);
        var buf2 = CharBuffer.allocate(1);
        var buf3 = IntBuffer.allocate(1);

        int readCount = channel.read(buf);
        System.out.println(readCount);

        @Cleanup var fc2 = FileChannel.open(path, StandardOpenOption.READ);
        var readCount2 = fc2.read(buf);
        System.out.println(readCount2);

        buf.rewind();
        byte[] bytes = buf.array();
        System.out.println(bytes[0] == 'Z');
        System.out.println(bytes[1]);
        System.out.println((char)bytes[0]);

        System.out.println(fc2.position());
        fc2.position(12345);
        System.out.println(fc2.position());

        var buf4 = ByteBuffer.allocate(1);
        fc2.read(buf4);
        buf4.rewind();
        System.out.println(buf4.get());

        System.out.println("Beginning buffer 5");

        fc2.position(50);
        var buf5 = ByteBuffer.allocate(4);
        buf5.order(ByteOrder.LITTLE_ENDIAN);
        fc2.read(buf5);
        buf5.rewind();
        //System.out.println(buf5.getInt()); Causes an underflow in following Call somehow
        System.out.printf("%x%n",buf5.getInt());

        fc2.position(50);
        var buf6 = ByteBuffer.allocate(4);
        fc2.read(buf5);
        buf5.rewind();
        buf5.order(ByteOrder.BIG_ENDIAN);
        System.out.println(buf5.getInt());
        System.out.printf("%x%n",buf6.getInt());


    }
}
