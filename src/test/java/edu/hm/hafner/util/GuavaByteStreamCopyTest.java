package edu.hm.hafner.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.InputStream;
import java.io.OutputStream;

import com.google.common.io.ByteStreams;

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
        InputStream is = mock(InputStream.class);
        OutputStream os = mock(OutputStream.class);
        int bufferSize = 4096;
        int byteToCopy = 6;
        when(is.read(new byte[bufferSize])).thenReturn(byteToCopy, -1);


        // When
        long result = ByteStreams.copy(is, os);


        //Then
        verify(os).write(new byte[bufferSize], 0, byteToCopy);
        assertEquals("Expected to write a 6: ", byteToCopy, result);
    }

}