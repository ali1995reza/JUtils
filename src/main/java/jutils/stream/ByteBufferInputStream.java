package jutils.stream;

import jutils.assertion.Assertion;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ByteBufferInputStream extends InputStream {

    public static ByteBufferInputStream stream(ByteBuffer buffer) {
        return new ByteBufferInputStream(buffer);
    }

    public static ByteBufferInputStream streamDuplicate(ByteBuffer buffer) {
        return new ByteBufferInputStream(buffer, true);
    }


    private final ByteBuffer buffer;

    public ByteBufferInputStream(ByteBuffer buffer) {
        this(buffer, false);
    }

    public ByteBufferInputStream(ByteBuffer buffer, boolean duplicate) {
        Assertion.ifNull("buffer is null", buffer);
        this.buffer = duplicate?buffer.duplicate():buffer;
    }

    @Override
    public int read() throws IOException {
        if(buffer.hasRemaining())
            return buffer.get() & 0xff;
        return -1;
    }
}
