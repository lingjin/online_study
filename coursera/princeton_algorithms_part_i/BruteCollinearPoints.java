import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints {

    private final LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points) {
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

        for (int a = 0; a < N - 3; a++) {
            Point pointA = sortedPoints[a];

            for (int b = a + 1; b < N - 2; b++) {
                Point pointB = sortedPoints[b];
                double slopeAB = pointA.slopeTo(pointB);

                for (int c = b + 1; c < N -1; c++) {
                    Point pointC = sortedPoints[c];
                    double slopeAC = pointA.slopeTo(pointC);

                    if (slopeAB == slopeAC) {
                        for (int d = c + 1; d < N; d++) {
                            Point pointD = sortedPoints[d];
                            double slopeAD = pointA.slopeTo(pointD);
                            if (slopeAB == slopeAD) {
                                list.add(new LineSegment(pointA, pointD));
                            }
                        }

                    }

                }
                
            }
        }
        
        lineSegments = list.toArray(new LineSegment[list.size()]);
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
