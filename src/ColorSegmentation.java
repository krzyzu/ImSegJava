import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Vector;

public class ColorSegmentation {
         
    // value for visited pixel 
    int VISITED = 0x00FF0000;         
    // value for not visited pixel 
    int NOT_VISITED = 0x00000000;    
    // source image filename 
    String _srcFilename;     
    // destination image filename 
    String _dstFilename;     
     
    // the source image 
    BufferedImage _srcImage;     
    // the destination image 
    BufferedImage _dstImage;     
     
    // threshold value 0 - 255 
    int _threshold;         
    // image width 
    int _width;             
    // image height 
    int _height;         
    // "seed" color / segment color 
    int _color;             
    // red value from seed 
    int _red;             
    // green value from seed 
    int _green;             
    // blue value from seed 
    int _blue;             
     
    // pixels from source image 
    int[] _pixels;         
    // table for keeping track or visits 
    int[] _visited;         
    // keeping for candidate points 
    Vector<SPoint> _points;  
     
    class SPoint { 
        int x; 
        int y; 
        public SPoint(int x, int y) { 
            this.x = x; 
            this.y = y; 
        } 
    } 
    
    public ColorSegmentation(BufferedImage _srcImage) { 
         
        _width       = _srcImage.getWidth(); 
        _height       = _srcImage.getHeight(); 
        // extract pixels from source image 
        _pixels       = _srcImage.getRGB(0, 0, _width, _height, 
                                        null, 0, _width); 
        // create empty destination image 
        _dstImage  = new BufferedImage(_width,  
                                        _height,  
                                        BufferedImage.TYPE_INT_RGB); 
        _visited   = new int[_pixels.length]; 
        _points       = new Vector<ColorSegmentation.SPoint>(); 
    } 
     
    public BufferedImage segmentize(int threshold) { 
        _threshold = threshold;
        // initialize points 
        _points.clear(); 
        // clear table with NOT_VISITED value 
        Arrays.fill(_visited, NOT_VISITED); 
        // loop through all pixels 
        for (int x=0;x<_width;x++) { 
            for (int y=0;y<_height;y++) { 
                // if not visited, start new segment 
                if (_visited[_width*y+x]==NOT_VISITED) { 
                    // extract segment color info from pixel 
                    _color = _pixels[_width*y+x]; 
                    _red   = _color>>16&0xff; 
                    _green = _color>>8&0xff; 
                    _blue  = _color&0xff; 
                    // add "seed" 
                    _points.add(new ColorSegmentation.SPoint(x, y)); 
                    // start finding neighboring pixels 
                    flood(); 
                } 
            } 
        } 
        // save the result image 
        _dstImage.setRGB(0, 0, _width, _height, _pixels, 0, _width); 
        return _dstImage; 
    } 
     
    public void flood() { 
        // while there are candidates in points vector 
        while (_points.size()>0) { 
            // remove the first candidate 
            ColorSegmentation.SPoint current = _points.remove(0); 
            int x = current.x; 
            int y = current.y; 
            if ((x>=0)&&(x<_width)&&(y>=0)&&(y<_height)) { 
                // check if the candidate is NOT_VISITED yet 
                if (_visited[_width*y+x]==NOT_VISITED) { 
                    // extract color info from candidate pixel 
                    int _c = _pixels[_width*y+x]; 
                    int red   = _c>>16&0xff; 
                    int green = _c>>8&0xff; 
                    int blue  = _c>>0&0xff; 
                    // calculate difference between  
                    // seed's and candidate's 
                    // red, green and blue values 
                    int rx = Math.abs(red - _red); 
                    int gx = Math.abs(green - _green); 
                    int bx = Math.abs(blue - _blue); 
                    // if all colors are under threshold 
                    if (rx<=_threshold 
                            &&gx<=_threshold 
                                &&bx<=_threshold) { 
                        // add the candidate to the segment (image) 
                        _pixels[_width*y+x] = _color; 
                        // mark the candidate as visited 
                        _visited[_width*y+x] = VISITED; 
                        // add neighboring pixels as candidate 
                        // (8-connected here) 
                        _points.add(new ColorSegmentation.SPoint(x-1,y-1)); 
                        _points.add(new ColorSegmentation.SPoint(x  ,y-1)); 
                        _points.add(new ColorSegmentation.SPoint(x+1,y-1)); 
                        _points.add(new ColorSegmentation.SPoint(x-1,y)); 
                        _points.add(new ColorSegmentation.SPoint(x+1,y)); 
                        _points.add(new ColorSegmentation.SPoint(x-1,y+1)); 
                        _points.add(new ColorSegmentation.SPoint(x  ,y+1)); 
                        _points.add(new ColorSegmentation.SPoint(x+1,y+1)); 
                    } 
                } 
            } 
        } 
    } 
    
}