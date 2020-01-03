package EXAM;

import lombok.Cleanup;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import EXAM.CardFaces.CardFace;

public class Main2C {
    public static void main(String[] args) throws Exception {


        Path path = Paths.get("C:\\Program Files (x86)\\Common Files\\Oracle\\Java\\javapath\\java.exe");
        FileChannel channel = FileChannel.open(path);
        long len = new File(path.toString()).length();

        int increaser = 2;
        int counter = 1;

        while ( counter < len) {
            channel.position(counter);
            var buf = ByteBuffer.allocate(1);
            buf.order(ByteOrder.LITTLE_ENDIAN);
            channel.read(buf);
            buf.rewind();
            int bufValue = buf.get();
            for (CardFace c : CardFace.values()) {
                if (c.getCardValue() == bufValue) System.out.println(c.toString());
            }
            counter += increaser;
            increaser++;
        }

    }
}