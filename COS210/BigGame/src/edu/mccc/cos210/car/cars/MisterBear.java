package edu.mccc.cos210.car.cars;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import edu.mccc.cos210.car.*;
import com.cbthinkx.util.Debug;
public class MisterBear extends Car {

       protected void paintTop(JPanel jp, Graphics2D g2d, AffineTransform at) {
               Debug.println("MisterBear:paintTop()");
               g2d.setStroke( new BasicStroke(3));
               Path2D bear = new Path2D.Double();
               bear.moveTo(0, 1.25);
               bear.lineTo(0.05, 1.23);
               bear.lineTo(0.1, 1.2);
               bear.lineTo(0.08, 1.15);
               bear.lineTo(0.1, 1.1);
               bear.lineTo(0.2, 0.9);
               bear.lineTo(0.3, 0.87);
               bear.lineTo(0.25, 0.85);
               bear.lineTo(0.3, 0.7);
               bear.curveTo(0.3, 0.65, 0.45, 0.35, 0.5, 0.3);//1
               bear.curveTo(0.55, 0.25, 0.55, 0.15, 0.5, 0.1);//2
               bear.curveTo(0.45, -0.1, 0.45, -0.6, 0.5, -0.8);//3
               bear.curveTo(0.55, -0.85, 0.55, -0.95, 0.5, -1.0);//4
               bear.curveTo(0.3, -1.2, -0.3, -1.2, -0.5, -1.0);//5
               bear.curveTo(-0.55, -0.95, -0.55, -0.85, -0.5, -0.8);//4
               bear.curveTo(-0.45, -0.6, -0.45, -0.1, -.5, 0.1);//3
               bear.curveTo(-0.55, 0.15, -0.55, 0.25, -0.5, 0.3);//2
               bear.curveTo(-0.45, 0.35, -0.3, 0.65, -0.3, 0.7);//1
               bear.lineTo(-0.25, 0.85);
               bear.lineTo(-0.3, 0.87);
               bear.lineTo(-0.2, 0.9);
               bear.lineTo(-0.1, 1.1);
               bear.lineTo(-0.08, 1.15);
               bear.lineTo(-0.1, 1.2);
               bear.lineTo(-0.05, 1.23);
               bear.lineTo(0, 1.25);
               bear.closePath();
               g2d.setPaint(Color.BLUE);
               Shape s = bear.createTransformedShape(at);
               g2d.fill(s);

               g2d.setPaint(Color.BLACK);
               g2d.draw(s);
       }
       protected void paintSide(JPanel jp, Graphics2D g2d, AffineTransform at) {
               Debug.println("MisterBear:paintSide()");
               g2d.setStroke( new BasicStroke(3));
               Path2D bear = new Path2D.Double();
               bear.moveTo(-.13, 0);
               bear.lineTo(0.38, 0);
               bear.curveTo(0.35, 0.04, 0.28, 0.042, 0.29, 0.048);
               bear.curveTo(0.44, 0.32, 0.6, 0.28, 0.7, 0.26);
               bear.curveTo(0.8, 0.2, 1, 0.13, 1.03, 0.13);
               bear.curveTo(1.1, 0.11, 1.15, 0.08, 1.28, 0.13);
               bear.curveTo(1.28, 0.15, 1.26, 0.2, 1.22, 0.3);
               bear.curveTo(1.2, 0.34, 1.2, 0.4, 1.17, 0.5);
               bear.curveTo(1.15, 0.55, 1.1, 0.6, 1, 0.7);
               bear.curveTo(0.9, 0.78, 0.8, 0.87, 0.7, 0.91);
               bear.curveTo(0.62, 0.96, 0.6, 0.96, 0.55, 1.06);
               bear.curveTo(0.5, 1.08, 0.48, 1.08, 0.47, 1.1);
               bear.curveTo(0.4, 1.11, 0.35, 1.12, 0.2, 1.113);
               bear.curveTo(0.1, 1.16, 0, 1.19, -0.2, 1.24);
               bear.curveTo(-0.25, 1.28, -0.3, 1.285, -0.5, 1.29);
               bear.curveTo(-0.55, 1.288, -0.6, 1.28, -0.7, 1.266);
               bear.curveTo(-0.8, 1.2, -0.9, 1.145, -0.98, 1.06);
               bear.curveTo(-1.07, 0.96, -1.1, 0.9, -1.105, 0.8);
               bear.curveTo(-1.1, 0.76, -1.08, 0.7, -1.03, 0.67);
               bear.curveTo(-1, 0.57, -0.95, 0.43, -0.9, 0.41);
               bear.curveTo(-0.88, 0.35, -0.87, 0.3, -0.88, 0.2);
               bear.curveTo(-0.882, 0.15, -0.88, 0.1, -0.83, 0);
               bear.lineTo(-0.4, 0);
               bear.curveTo(-0.44, 0.08, -0.5, 0.082, -0.57, 0.1);
               bear.curveTo(-0.52, 0.25, -0.4, 0.23, -0.3, 0.23);
               bear.curveTo(-0.14, 0.29, -0.13, 0.26, -0.16, 0.2);
               bear.curveTo(-0.165, 0.13, -0.15, 0.03, -0.13, 0);
               bear.moveTo(-0.52, 0.25);
               bear.curveTo(-0.4 ,0.4 ,-0.4 ,0.7 , -0.6, 0.9);
//              bear.append(
//                      new CubicCurve2D.Double(-0.44, 0.32, -0.35, 0.35, -0.5, 0.5, -0.6, 0.6)
//                      , false);
               bear.append(
                       new CubicCurve2D.Double(0.98, 0.43, 0.9 ,0.4 ,0.9 ,0.7 , 1.02, 0.51)
                       , false);
               bear.append(
                       new Ellipse2D.Double(1.05, 0.38, 0.05, 0.05)
                       , false);
               bear.append(
                       new QuadCurve2D.Double(1.16, 0.098, 1.13, 0.103, 1.12, 0.14)
                       , false);
               bear.append(
                       new Ellipse2D.Double(1.24, 0.15, 0.025, 0.025)
                       , false);
               g2d.setPaint(Color.BLUE);
               Shape s = bear.createTransformedShape(at);
               g2d.fill(s);

               g2d.setPaint(Color.BLACK);
               g2d.draw(s);
               Ellipse2D origin = new Ellipse2D.Double(-0.025, -0.025, 0.05, 0.05);
               g2d.setPaint(Color.RED);
               s = at.createTransformedShape(origin);

               g2d.fill(s);
       }
       public void paintShifter(JPanel jp, Graphics2D g2d, int gear, int maxGear) {
               Debug.println("MisterBear:paintShifter()");
               Path2D bear = new Path2D.Double();
               AffineTransform at = new AffineTransform();
               at.scale(14, 14);
               bear.moveTo(1, 1);
               bear.curveTo(0, 2, 1, 4, 2, 5);
               bear.curveTo(1.5, 4, 1.5, 2, 2, 1);
               bear.curveTo(1.8, 2, 1.9, 3, 2.1, 3.8);
               bear.curveTo(2.3, 3, 2.4, 2, 2.8, 1);
               bear.curveTo(2.6, 1.9, 2.7, 2.8, 2.9, 3.6);
               bear.curveTo(3.1, 2.8, 3.2, 1.9, 3.5, 1);
               bear.curveTo(3.3, 1.8, 3.4, 2.6, 3.6, 3.4);
               bear.curveTo(3.8, 2.6, 3.9, 1.8, 4.3, 1);
               bear.curveTo(4.1, 1.7, 4.2, 2.5, 4.4, 3.2);
               bear.curveTo(4.6, 2.5, 4.7, 1.7, 5, 1);
               bear.moveTo(9, 1);
               bear.curveTo(10, 2, 9, 4, 8, 5);
               bear.curveTo(8.5, 4, 8.5, 2, 8, 1);
               bear.curveTo(8.2, 2, 8.1, 3, 7.9, 3.8);
               bear.curveTo(7.7, 3, 7.6, 2, 7.2, 1);
               bear.curveTo(7.4, 1.9, 7.3, 2.8, 7.1, 3.6);
               bear.curveTo(6.9, 2.8, 6.8, 1.9, 6.5, 1);
               bear.curveTo(6.7, 1.8, 6.6, 2.6, 6.4, 3.4);
               bear.curveTo(6.2, 2.6, 6.1, 1.8, 5.7, 1);
               bear.curveTo(5.9, 1.7, 5.8, 2.5, 5.6, 3.2);
               bear.curveTo(5.4, 2.5, 5.3, 1.7, 5, 1);
               bear.moveTo(1, 9);
               bear.curveTo(0, 8, 1, 6, 2, 5);
               bear.curveTo(1.5, 6, 1.5, 8, 2, 9);
               bear.curveTo(1.8, 8, 1.9, 7, 2.1, 6.2);
               bear.curveTo(2.3, 7, 2.4, 8, 2.8, 9);
               bear.curveTo(2.6, 8.1, 2.7, 7.2, 2.9, 6.4);
               bear.curveTo(3.1, 7.2, 3.2, 8.1, 3.5, 9);
               bear.curveTo(3.3, 8.2, 3.4, 7.4, 3.6, 6.6);
               bear.curveTo(3.8, 7.4, 3.9, 8.2, 4.3, 9);
               bear.curveTo(4.1, 8.3, 4.2, 7.5, 4.4, 6.8);
               bear.curveTo(4.6, 7.5, 4.7, 8.3, 5, 9);
               bear.moveTo(9, 9);
               bear.curveTo(10, 8, 9, 6, 8, 5);
               bear.curveTo(8.5, 6, 8.5, 8, 8, 9);
               bear.curveTo(8.2, 8, 8.1, 7, 7.9, 6.2);
               bear.curveTo(7.7, 7, 7.6, 8, 7.2, 9);
               bear.curveTo(7.4, 8.1, 7.3, 7.2, 7.1, 6.4);
               bear.curveTo(6.9, 7.2, 6.8, 8.1, 6.5, 9);
               bear.curveTo(6.7, 8.2, 6.6, 7.4, 6.4, 6.6);
               bear.curveTo(6.2, 7.4, 6.1, 8.2, 5.7, 9);
               bear.curveTo(5.9, 8.3, 5.8, 7.5, 5.6, 6.8);
               bear.curveTo(5.4, 7.5, 5.3, 8.3, 5, 9);
               bear.transform(at);
               g2d.setPaint(Color.WHITE);
               g2d.fill(bear);
               g2d.setPaint(Color.BLACK);
               g2d.draw(bear);
       }
}
