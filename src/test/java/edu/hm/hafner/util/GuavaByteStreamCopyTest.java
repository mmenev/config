package edu.hm.hafner.util;

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

    @Test
    public void testname() throws Exception {
        // Given
        InputStream is = mock(InputStream.class);
        OutputStream os = mock(OutputStream.class);

        // When
        long tmp = ByteStreams.copy(is, os);

        //Then
        verify(os).close();
    }

}