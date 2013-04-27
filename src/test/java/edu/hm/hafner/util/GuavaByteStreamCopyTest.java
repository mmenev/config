package edu.hm.hafner.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.common.io.ByteStreams;
import com.google.common.io.OutputSupplier;

import org.junit.Test;

/**
 * FIXME: Document type GuavaByteStreamCopyTest.
 *
 * @author Ulli Hafner
 */
public class GuavaByteStreamCopyTest {

    /**
     * Test the guava Bytestream.copy method.
     * @throws Exception if something goes wrong.
     */
    @Test
    public void testname() throws Exception {
        // Given
        InputStream is = mock(ByteArrayInputStream.class);
        OutputSupplier<OutputStream> ous = new OutputSupplier<OutputStream>() {

            @Override
            public OutputStream getOutput() throws IOException {
                //return mock(OutputStream.class);
                return new BufferedOutputStream(System.out);
            }};
        byte[] toCopy = "Mockito".getBytes();
        when(is.read(toCopy)).thenReturn(-1);


        // When
        is = new ByteArrayInputStream(toCopy);
        long result = ByteStreams.copy(is, ous);


        //Then
        assertEquals("Expected to write a 6: ", toCopy.length, result);


    }

}