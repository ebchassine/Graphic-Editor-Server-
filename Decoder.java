import java.awt.Color;
import java.awt.Point;
public class Decoder {


    public static String parseString(String message, Sketch sketch){

        System.out.println("Decoding!");
        String[] splitMessage = message.split("\\s+"); //splits by space, looked this up online

        //First val in String collection should be a command
        if (splitMessage[0].equals("draw")){ //"draw0 shapetype1 shapeID2 x1 3 y1 4 x2 5 y2 6 color 7"
            Shape newVal = null;
            if (splitMessage[1].equals("ellipse")){
                newVal = new Ellipse(Integer.parseInt(splitMessage[3]), Integer.parseInt(splitMessage[4]), Integer.parseInt(splitMessage[5]), Integer.parseInt(splitMessage[6]), new Color(Integer.parseInt(splitMessage[7])));
            } else if (splitMessage[1].equals("rectangle")){
                newVal = new Rectangle(Integer.parseInt(splitMessage[3]), Integer.parseInt((splitMessage[4])), Integer.parseInt((splitMessage[5])), Integer.parseInt((splitMessage[6])), new Color(Integer.parseInt((splitMessage[7]))));
            } else if (splitMessage[1].equals("segment")){
                newVal = new Segment(Integer.parseInt(splitMessage[3]), Integer.parseInt((splitMessage[4])), Integer.parseInt((splitMessage[5])), Integer.parseInt((splitMessage[6])), new Color(Integer.parseInt((splitMessage[7]))));
            } else if (splitMessage[1].equals("polyline")){
                newVal = new Polyline( new Point( Integer.parseInt(splitMessage[5]), Integer.parseInt(splitMessage[6])), new Color(Integer.parseInt(splitMessage[7])));

            }
            sketch.addShape(newVal);
        } else if (splitMessage[0].equals("move")){ //"move0 shapeID1 dX2 dY3"
            Integer shapeID = Integer.parseInt(splitMessage[1]);
            Integer dX = Integer.parseInt(splitMessage[2]);
            Integer dY = Integer.parseInt(splitMessage[3]);

            sketch.moveShape(shapeID, dX, dY);

        } else if (splitMessage[0].equals("recolor")){ // "recolor0 shapeID1 color2"

            sketch.recolorShape(Integer.parseInt(splitMessage[1]), new Color(Integer.parseInt(splitMessage[2])));

        } else if (splitMessage[0].equals("delete")){ // "delete0 shapeID1"
            System.out.println("Deleting message identified.");
            sketch.removeShape(Integer.parseInt(splitMessage[1]));

        } else if (splitMessage[0].equals("disconnect")) {
            return null;
        }
        return message;
    }


}
