package edu.mccc.cos210.dddial;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import com.cbthinkx.util.Debug;
public abstract class DDDial extends JProgressBar {
    private static final double clockRadius = 63.5;

	//private BoundedRangeModel model;
	private ArrayList<SuperTicks> superTicks = new ArrayList<SuperTicks>();
	private int angleStart = 0;
	private int angleExtent = 360;
    /** Where the needle 'origin' is */
    private int needleStart = 0;
	private int tickInterval = 10;
	private boolean round = true;
	private boolean ticks = true;
	private boolean counterClockwise = true;

    public DDDial(BoundedRangeModel model) {
		super(model);
		Debug.println("DDDial.DDDial()");
        this.model = model;
        setBorderPainted(false);
        setOpaque(false);
    }

    private int validAngle(int angle) {
		Debug.println("DDDial.validAngle()");
        if (angle < 0) {
            return 0;
        }
        if (this.round) {
            if (angle > 360) {
                return 360;
            }
        } else {
            if (angle > 180) {
                return 180;
            }
        }
        return angle;
    }
    private void ensureValidExtent() {
		Debug.println("DDDial.ensureValidExtent()");
        if (round) {
            //Do nothing
        } else {
            if (angleStart + angleExtent > 180) {
                angleExtent = 180 - angleStart;
            }
        }
    }
	public void setAngleStart(int n) {
		Debug.println("DDDial.setAngleStart()");
        this.angleStart = validAngle(n);
	}
	public void setAngleExtent(int n) {
		Debug.println("DDDial.setAngleExtent()");
        this.angleExtent = validAngle(n);
        ensureValidExtent();
	}
	public void setNeedleStart(int n) {
		Debug.println("DDDial.setNeedleStart()");
        this.needleStart = n;
	}
	public void setRound(boolean b) {
		Debug.println("DDDial.setRound()");
		this.round = b;
        if (!b) {
            ensureValidExtent();
        }
	}
	public void setCounterClockwise(boolean c) {
		Debug.println("DDDial.setCounterClockwise()");
		this.counterClockwise = c;
	}
	public void setTicks(boolean b, int i) {
		Debug.println("DDDial.setSuperTicks()");
		this.ticks = b;
		if (i < 0 ) {
			this.tickInterval = 1;
		} else {
			this.tickInterval = i;
		}
		if (i > this.angleExtent) {
			this.tickInterval = this.angleExtent;
		}
	}
	public void setSuperTicks(ArrayList <SuperTicks> superTicks) {
		Debug.println("DDDial.setSuperTicks()");
        this.superTicks = superTicks;
    }
	public void paintComponent(Graphics g) {
		Debug.println("DDDial.paintComponent()");
		//super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setStroke(new BasicStroke(1));
        //Draws ticks around the face
		drawClockFace(g);
		drawTicks(g);
		drawSuperTicks(g);
		drawNeedle(g, model.getValue());
		//drawNeedle(g, 180);
		g2d.dispose();
	}
    protected void drawClockFace(Graphics g) {
		Debug.println("DDDial.drawClockFace()");
        Graphics2D g2d = (Graphics2D) g.create();
        double centerRadius = clockRadius / 10;
        if (round) {
            //Draw round plate
            Shape s = new Ellipse2D.Double(
                0,
                0,
                clockRadius * 2,
                clockRadius * 2
            );
            g2d.setPaint(Color.GRAY);
            g2d.draw(s);

            //Middle small ring
            s = new Ellipse2D.Double(
                0,
                0,
                centerRadius,
                centerRadius
            );
            AffineTransform at = new AffineTransform();
            //Translate our center circle to the center!
            at.translate(
                clockRadius - centerRadius / 2,
                clockRadius - centerRadius / 2
            );
            s = at.createTransformedShape(s);
            g2d.setPaint(Color.GRAY);
            g2d.fill(s);
            g2d.draw(s);
        } else {
            //180-degree, semicircular
            //Arc2D.Double(double x, double y, double w, double h, double start, double extent, int type)
            //Constructs a new arc, initialized to the specified location, size, angular extents, and closure type.
            Shape s = new Arc2D.Double(
                (double) 0,
                (double) 0,
                (double) 127,
                (double) 127,
                (double) 0,
                (double) 180,
                Arc2D.PIE
            );
            //s = at.createTransformedShape(s);
            g2d.setPaint(Color.GRAY);
            //g2d.fill(s);
            g2d.draw(s);
            //type - The closure type for the arc: Arc2D.OPEN, Arc2D.CHORD, or Arc2D.PIE.
        }
		g2d.dispose();
	}

    protected void drawSuperTicks(Graphics g) {
		Debug.println("DDDial.drawSuperTicks()");
        Graphics2D g2d = (Graphics2D) g.create();
        double tickLength = clockRadius / 2.5;
        double tickWidth  = clockRadius / 30;
        //Create a tickmark in the top left of the screen, leaving blank space as a 'pivot arm'
        Shape tick = new Rectangle2D.Double(
            clockRadius - tickLength,  //Pivot arm
            0,
            tickLength,
            tickWidth
        );
        AffineTransform at = new AffineTransform();
        //Translate our tickmark circle from the top left to the center
        at.translate(
            clockRadius,
            clockRadius - (tickWidth / 2)
        );
        at.scale(
            1, -1
        );
        int prevAngle = 0;
        //Rotate the 'pivot arm,' draw each tick:
        //Rather than getting a new affineTransform, we're rotating back to
        //the initial angle after every supertick
        for (SuperTicks superTick : superTicks) {
            g2d.setPaint(superTick.getColor());
            at.rotate(Math.toRadians(-prevAngle));
            prevAngle = superTick.getValue();
            at.rotate(Math.toRadians(superTick.getValue()));
            Shape s = at.createTransformedShape(tick);
            g2d.draw(s);
            g2d.fill(s);
        }
    }
    protected void drawTicks(Graphics g) {
		Debug.println("DDDial.drawTicks()");
        Graphics2D g2d = (Graphics2D) g.create();
        double tickLength = clockRadius / 5;
        double tickWidth  = clockRadius / 60;
        Shape tick = new Rectangle2D.Double(
            clockRadius - tickLength,  //Pivot arm
            0,
            tickLength,
            tickWidth
        );
        AffineTransform at = new AffineTransform();
        at.translate(
            clockRadius,
            clockRadius - (tickWidth / 2)
        );
        at.scale(
            1, -1
        );
        at.rotate(Math.toRadians(angleStart));
        for (int angle = angleStart; angle <= angleStart + angleExtent; angle += tickInterval) {
            Shape s = at.createTransformedShape(tick);
            g2d.setPaint(Color.GRAY);
            g2d.draw(s);
            g2d.fill(s);
            at.rotate(Math.toRadians(tickInterval));
        }
    }
    protected void drawNeedle(Graphics g, double angle) {
		Debug.println("DDDial.drawNeedle()");
        //System.out.println("Angle is: " + angle);
        Graphics2D g2d = (Graphics2D) g.create();
		Shape s = new Line2D.Double(
             (clockRadius - 3.5),
			 0.0,
			 0.0,
			 0.0
		);
		AffineTransform at = new AffineTransform();
		at.translate(clockRadius, clockRadius);
        if (counterClockwise) {
            //at.scale(-1, 1);
            at.scale(1, -1);
        }
        //First rotate to designated angle
		at.rotate(Math.toRadians(needleStart));
        //Then rotate by user input
		at.rotate(Math.toRadians(angle));
        s = at.createTransformedShape(s);
        //g2d.setPaint(Color.YELLOW);
        g2d.setStroke(new BasicStroke(2));
        g2d.setPaint(Color.BLUE);
		g2d.draw(s);
	}
	public Dimension getPreferredSize() {
		Debug.println("DDDial.getPreferredSize()");
        if (round == true) {
            return new Dimension(128, 128);
        } else {
            return new Dimension(128, 64);
        }
	}
    public class SuperTicks {
        private int value;
        private Color color;
        public SuperTicks(int value, Color color) {
			Debug.println("SuperTicks.SuperTicks()");
            this.value = value;
            this.color = color;
        }
        public int getValue() {
			Debug.println("SuperTicks.getValue()");
            return this.value;
        }
        public Color getColor() {
			Debug.println("SuperTicks.getColor()");
            return this.color;
        }
    }
}
