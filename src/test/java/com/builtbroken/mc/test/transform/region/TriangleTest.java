package com.builtbroken.mc.test.transform.region;

import com.builtbroken.mc.lib.transform.vector.Point;
import junit.framework.TestCase;
import com.builtbroken.mc.lib.transform.region.Triangle;

/** Tests the triangle class for math based errors
 *
 * Created by robert on 12/17/2014.
 */
public class TriangleTest extends TestCase
{
    //TODO include a performance test to see how fast these method calls run on average

    public void testTriangleAreas()
    {
        //Dummy checks for the hell of it
        Triangle t = new Triangle(new Point(), new Point(), new Point());
        assertEquals("Expected zero due to all 3 points being zero zero", 0.0, t.getArea());
        t = new Triangle(new Point(0, 1), new Point(0, 2), new Point(0, 3));
        assertEquals("Expected zero due to all 3 points being in a line", 0.0, t.getArea());

        //http://www.mathopenref.com/coordtrianglearea.html
        t = new Triangle(new Point(0, 0), new Point(-1, 2), new Point(13, 5));
        assertEquals("Expected an exact match for area check one", 15.5, t.getArea());

        t = new Triangle(new Point(0, 0), new Point(-12, 27), new Point(37, 25));
        assertEquals("Expected an exact match for area check two", 649.5, t.getArea());

        t = new Triangle(new Point(0, 0), new Point(-12, 27), new Point(14, -10));
        assertEquals("Expected an exact match for area check three", 129.0, t.getArea());

        t = new Triangle(new Point(15, 15), new Point(23, 30), new Point(25, 8));
        assertEquals("Expected an exact match for area check four", 103.0, t.getArea());

        t = new Triangle(new Point(10, -6), new Point(42, -6), new Point(25, -12));
        assertEquals("Expected an exact match for area check five", 96.0, t.getArea());
    }

    /**
     * Tests area method using 4 triangles in each section
     * of pos & neg relations. It also tests the order of the points
     * when constructing the triangle to ensure there are no ordering
     * issues with how the math is handled
     */
    public void testRightTriangleAreas()
    {
        final double expectedArea = 2;
        //Using zero zero
        Point zeroZero = new Point();
        Point p1 = null;
        Point p2 = null;
        Triangle a, b, c;

        for (int i = 0; i < 4; i++)
        {
            switch (i)
            {
                case 0:
                    //positive positive
                    p1 = new Point(0, 2);
                    p2 = new Point(2, 0);
                    break;
                case 2:
                    //neg positive
                    p1 = new Point(0, 2);
                    p2 = new Point(-2, 0);
                    break;
                case 3:
                    //positive neg
                    p1 = new Point(0, -2);
                    p2 = new Point(2, 0);
                    break;
                case 4:
                    //neg neg
                    p1 = new Point(0, -2);
                    p2 = new Point(-2, 0);
                    break;
            }

            a = new Triangle(zeroZero, p1, p2);
            b = new Triangle(p1, p2, zeroZero);
            c = new Triangle(p2, zeroZero, p1);

            assertEquals("Failed area check for A" + i, expectedArea, a.getArea());
            assertEquals("Failed area check for B" + i, expectedArea, b.getArea());
            assertEquals("Failed area check for C" + i, expectedArea, c.getArea());

        }

    }

    /**
     * Tests area method using 4 triangles in each section
     * of pos & neg relations. It also tests the order of the points
     * when constructing the triangle to ensure there are no ordering
     * issues with how the math is handled
     */
    public void testObtuseTriangleAreas()
    {
        final double expectedArea = 4;
        //Using zero zero
        Point zeroZero = new Point();
        Point p1 = null;
        Point p2 = null;
        Triangle a, b, c;

        for (int i = 0; i < 4; i++)
        {
            switch (i)
            {
                case 0:
                    //UP
                    p1 = new Point(2, 2);
                    p2 = new Point(-2, 2);
                    break;
                case 2:
                    //DOWN
                    p1 = new Point(2, -2);
                    p2 = new Point(-2, -2);
                    break;
                case 3:
                    //RIGHT
                    p1 = new Point(2, 2);
                    p2 = new Point(2, -2);
                    break;
                case 4:
                    //LEFT
                    p1 = new Point(-2, 2);
                    p2 = new Point(-2, -2);
                    break;
            }

            a = new Triangle(zeroZero, p1, p2);
            b = new Triangle(p1, p2, zeroZero);
            c = new Triangle(p2, zeroZero, p1);

            assertEquals("Failed area check for A" + i, expectedArea, a.getArea());
            assertEquals("Failed area check for B" + i, expectedArea, b.getArea());
            assertEquals("Failed area check for C" + i, expectedArea, c.getArea());

        }

    }

    /**
     * Tests area method using 4 triangles in each section
     * of pos & neg relations. It also tests the order of the points
     * when constructing the triangle to ensure there are no ordering
     * issues with how the math is handled
     */
    public void testAcuteTriangleAreas()
    {
        final double expectedArea = 2;
        //Using zero zero
        Point zeroZero = new Point();
        Point p1 = null;
        Point p2 = null;
        Triangle a, b, c;

        for (int i = 0; i < 4; i++)
        {
            switch (i)
            {
                case 0:
                    //UP
                    p1 = new Point(1, 2);
                    p2 = new Point(-1, 2);
                    break;
                case 2:
                    //DOWN
                    p1 = new Point(1, -2);
                    p2 = new Point(-1, -2);
                    break;
                case 3:
                    //RIGHT
                    p1 = new Point(2, 1);
                    p2 = new Point(2, -1);
                    break;
                case 4:
                    //LEFT
                    p1 = new Point(-2, 1);
                    p2 = new Point(-2, -1);
                    break;
            }

            a = new Triangle(zeroZero, p1, p2);
            b = new Triangle(p1, p2, zeroZero);
            c = new Triangle(p2, zeroZero, p1);

            assertEquals("Failed area check for A" + i, expectedArea, a.getArea());
            assertEquals("Failed area check for B" + i, expectedArea, b.getArea());
            assertEquals("Failed area check for C" + i, expectedArea, c.getArea());
        }
    }
}
