package edu.hm.hafner.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.common.io.ByteStreams;
import com.google.common.io.OutputSupplier;

import org.junit.Test;

/**
 * ClassGuavaByteStreamsCopyTest.
 * Mockito exercise.
 *
 * @author Johann Vierthaler, johann.vierthaler@web.de
 * @author Mihail Menev, menev@hm.edu
 */
public class GuavaByteStreamCopyTest {

    /**
     * Test the guava Bytestream.copy method.
     * @throws Exception if something goes wrong.
     */
    @Test
    public void testname() throws Exception {
        // Given
        // Input- and Outputstream mocks
        InputStream is = mock(ByteArrayInputStream.class);
        OutputSupplier<OutputStream> ous = new OutputSupplier<OutputStream>() {

            @Override
            public OutputStream getOutput() throws IOException {
                return mock(OutputStream.class);
            }};

        // A byte array that should be copied
        byte[] toCopy = "Mockito".getBytes();
        // -1 closes the stream
        when(is.read(toCopy)).thenReturn(-1);

        // When
        is = new ByteArrayInputStream(toCopy);
        // result holds the number of bytes copied
        long result = ByteStreams.copy(is, ous);

        //Then
        assertEquals("Expected to write a 6: ", toCopy.length, result);
    }
}