import org.bytedeco.javacpp.*;
import org.junit.Test;

import static org.bytedeco.javacpp.lept.*;
import static org.bytedeco.javacpp.tesseract.*;
import static org.junit.Assert.assertTrue;

public class TesseractExample {
    
    @Test
    public void generateTextFromImage() throws Exception {
        
    	BytePointer outText;

        TessBaseAPI api = new TessBaseAPI();
        if (api.Init(".", "ENG") != 0) {
            System.err.println("Could not initialize tesseract.");
            System.exit(1);
        }

       
        PIX image = pixRead("/home/nikhil/Downloads/img3.jpg");
        api.SetImage(image);
        
        outText = api.GetUTF8Text();
        String string = outText.getString();
        assertTrue(!string.isEmpty());
        System.out.println("OCR output:\n" + string);

       
        api.End();
        outText.deallocate();
        pixDestroy(image);
    }
}