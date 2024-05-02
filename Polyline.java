import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * A multi-segment Shape, with straight lines connecting "joint" points -- (x1,y1) to (x2,y2) to (x3,y3) ...
 *
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Spring 2016
 * @author CBK, updated Fall 2016
 */
public class Polyline implements Shape {
    // TODO: YOUR CODE HERE
    ArrayList<Point> pointList = new ArrayList<>();
    ArrayList<Segment> segmentList = new ArrayList<>();
    Color color;
    public Polyline(Point point, Color color){
        addPoint(point);
        this.color = color;
    }
    @Override
    public void moveBy(int dx, int dy) {
        ArrayList<Point> tempPointList = new ArrayList();
        ArrayList<Segment> tempSegmentList = new ArrayList();
        while (pointList.size() != 0){
            Point temp = pointList.remove(0);
            tempPointList.add( new Point(temp.x + dx, temp.y + dy));
        }
        while (segmentList.size() != 0){
            Segment temp = segmentList.remove(0);
        }
        pointList = tempPointList;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean contains(int x, int y) {
        for (int iter = 0; iter < pointList.size() + 1; iter++) {
            if (Segment.pointToSegmentDistance(x ,y , pointList.get(iter).x, pointList.get(iter).y, pointList.get(iter + 1).x, pointList.get(iter + 1).y) <= 15){
                return true;
            }
        }
        return false;
    }

    @Override
    public void draw(Graphics g) {
        for (Integer i = 0; i < pointList.size()-1; i++){
            Point firstPoint = pointList.get(i);
            Point secondPoint = pointList.get(i+1);
            segmentList.add(new Segment((int) firstPoint.getX(),(int) firstPoint.getY(),(int) secondPoint.getX(),(int) secondPoint.getY(), color));

        }
        for (Segment seg : segmentList){
            seg.draw(g);
        }
    }

    @Override
    public void setCorners(int x, int y, int x1, int y1) {

    }

    public void addPoint(Point p){
        if (!(pointList.contains(p))){
            if(p.getX() % 1 == 0 && p.getY() % 1 == 0) {
                pointList.add(p);
            }
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (Point val: pointList){
            s += "("+ val.x+ val.y+ ") ";
        }
        return "Polyline" +  s + color.getRGB();
    }
}
