import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class FastCollinearPoints {

    private final LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException ("Points is null");
        for (Point p: points)
            if (p == null)
                throw new IllegalArgumentException ("Points has null elements");
        
        Point[] sortedPoints = points.clone();
        int N = sortedPoints.length;
        Arrays.sort(sortedPoints);
        for (int i = 0; i < N - 1; i++) {
            if (sortedPoints[i].compareTo(sortedPoints[i + 1]) == 0) 
                throw new IllegalArgumentException("Points has repeat elemenets");
        }

        List<LineSegment> list = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            Point p = sortedPoints[i];
            Point[] pointsSlopeOrder = sortedPoints.clone();
            Arrays.sort(pointsSlopeOrder, p.slopeOrder());

            int j = 1;
            while (j < N) {
                LinkedList<Point> pointList = new LinkedList<>();
                double slopeBase = p.slopeTo(pointsSlopeOrder[j]);

                do {
                    pointList.add(pointsSlopeOrder[j++]);
                } while (j < N && p.slopeTo(pointsSlopeOrder[j]) == slopeBase);

                if (pointList.size() > 3 && p.compareTo(pointList.peek()) < 0) {
                    list.add(new LineSegment(p, pointList.removeLast()));
                }
            }
        }
        lineSegments = list.toArray(new LineSegment[0]);
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments.clone();
    }

     public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
    
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
    
        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
