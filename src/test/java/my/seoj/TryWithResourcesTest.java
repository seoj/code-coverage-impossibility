package my.seoj;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class TryWithResourcesTest
{
    @InjectMocks
    private TryWithResources tryWithResources;

    @Mock
    private FileInputStreamFactory fileInputStreamFactory;

    @Mock
    private FileInputStream fileInputStream;

    @Before
    public void before()
    {
        initMocks(this);
    }

    @Test
    public void testWhenNoExceptions() throws Exception
    {
        when(fileInputStreamFactory.getFileInputStream()).thenReturn(fileInputStream);

        tryWithResources.read();

        InOrder inOrder = inOrder(fileInputStreamFactory, fileInputStream);
        inOrder.verify(fileInputStreamFactory).getFileInputStream();
        inOrder.verify(fileInputStream).read();
        inOrder.verify(fileInputStream).close();
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testWhenIOExceptionRead() throws Exception
    {
        IOException expectedException = new IOException();

        when(fileInputStreamFactory.getFileInputStream()).thenReturn(fileInputStream);
        when(fileInputStream.read()).thenThrow(expectedException);

        try
        {
            tryWithResources.read();
            fail();
        }
        catch (Exception e)
        {
            assertEquals(RuntimeException.class, e.getClass());
            assertEquals(expectedException, e.getCause());
        }

        InOrder inOrder = inOrder(fileInputStreamFactory, fileInputStream);
        inOrder.verify(fileInputStreamFactory).getFileInputStream();
        inOrder.verify(fileInputStream).read();
        inOrder.verify(fileInputStream).close();
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testWhenIOExceptionOnClose() throws Exception
    {
        IOException expectedException = new IOException();

        when(fileInputStreamFactory.getFileInputStream()).thenReturn(fileInputStream);
        doThrow(expectedException).when(fileInputStream).close();

        try
        {
            tryWithResources.read();
            fail();
        }
        catch (Exception e)
        {
            assertEquals(RuntimeException.class, e.getClass());
            assertEquals(expectedException, e.getCause());
        }

        InOrder inOrder = inOrder(fileInputStreamFactory, fileInputStream);
        inOrder.verify(fileInputStreamFactory).getFileInputStream();
        inOrder.verify(fileInputStream).read();
        inOrder.verify(fileInputStream).close();
        inOrder.verifyNoMoreInteractions();
    }
}
