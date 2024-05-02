import javax.imageio.plugins.tiff.TIFFImageReadParam;
import java.util.HashMap;
import java.util.Map;
import java.awt.Point;
import java.awt.Color;
import java.util.TreeMap;

public class Sketch {
    public TreeMap<Integer, Shape> shapeMap;
    Integer shapeID;

    public Sketch(){
        shapeMap = new TreeMap<>();
        shapeID = 0;
    }

    public Integer getShapeID(Shape shape){
        for (Integer key : shapeMap.keySet()) {
            if (shapeMap.get(key).equals(shape)){
                return key;
            }
        }
        return null;
    }
    public void addShape(Shape val){
        shapeMap.put(shapeID, val);
        System.out.println("Shape ID for that shape: " + shapeID);
        shapeID++;
    }

    public Integer getIDCounter(){
        return shapeID;
    }
    public Shape getShape(Integer key){
        return shapeMap.get(key);
    }

    public void removeShape(Integer key){
        if (shapeMap.containsKey(key)){
            System.out.println("Removing actually!");
            shapeMap.remove(key);
        }
    }

    public void recolorShape(Integer key, Color colorVal){
        if (shapeMap.containsKey(key)){
            shapeMap.get(key).setColor(colorVal);
        }

    }

    public Shape hasPoint(Point point){
        for (Integer key: shapeMap.descendingKeySet()){
            if (shapeMap.get(key).contains(point.x, point.y)){
                return shapeMap.get(key);
            }
        }
        return null;
    }

    public void moveShape(Integer key, Integer dX, Integer dY){
        if (shapeMap.containsKey(key)){
            shapeMap.get(key).moveBy(dX, dY);
        }
    }
}

