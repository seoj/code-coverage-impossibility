package my.seoj;

import java.io.FileInputStream;
import java.io.IOException;

public class TryWithResources
{
    private FileInputStreamFactory fileInputStreamFactory;

    public void read()
    {
        try (FileInputStream fileInputStream = fileInputStreamFactory.getFileInputStream())
        {
            fileInputStream.read();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
