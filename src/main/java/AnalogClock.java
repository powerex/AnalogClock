import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Calendar;

public class AnalogClock implements Runnable {

    private GraphicsContext gc;
    double xs, ys, xm, ym, xh, yh;
    double r, midX, midY;
    Thread localThread = new Thread(this);

    public Thread getThread() {
        return localThread;
    }

    public AnalogClock(GraphicsContext graphicsContext){
        this.gc = graphicsContext;
        drawClockFace();
        drawClockHands();
        localThread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                drawClockHands();
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                ie.printStackTrace();
            }
        }
    }

    public void drawClockHands() {
        gc.setFill(Color.rgb(175, 175, 175));
        gc.fillOval(midX-r*0.87, midY-r*0.87, 2 * r * 0.87, 2 * r * 0.87);

        int s = Calendar.getInstance().
                get(Calendar.SECOND);
        xs = r * 0.85 * Math.cos(s * 6 * Math.PI / 180 - Math.PI / 2) + midX;
        ys = r * 0.85 * Math.sin(s * 6 * Math.PI / 180 - Math.PI / 2) + midY;

        int m = Calendar.getInstance().
                get(Calendar.MINUTE);
        xm = r * 0.75 * Math.cos((m * 6 + s / 10.) * Math.PI / 180 - Math.PI / 2) + midX;
        ym = r * 0.75 * Math.sin((m * 6 + s / 10.) * Math.PI / 180 - Math.PI / 2) + midY;

        int h = Calendar.getInstance().
                get(Calendar.HOUR_OF_DAY);
        xh = r * 0.65 * Math.cos((h * 30 + m / 2.) * Math.PI / 180 - Math.PI / 2) + midX;
        yh = r * 0.65 * Math.sin((h * 30 + m / 2.) * Math.PI / 180 - Math.PI / 2) + midY;

        gc.setStroke(Color.WHITE);
        gc.setLineWidth(3.0f);
        gc.strokeLine(midX, midY, xm, ym);
        gc.setStroke(Color.rgb(220, 22, 10));
        gc.setLineWidth(5.0f);
        gc.strokeLine(midX, midY, xh, yh);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.0f);
        gc.strokeLine(midX, midY, xs, ys);

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.0f);
        gc.setFill(Color.WHITE);
        gc.fillOval(midX-5, midY-5, 10, 10);
        gc.strokeOval(midX-5, midY-5, 10, 10);

    }

    private void drawClockFace() {
        gc.setFill(Color.rgb(205, 205, 205));
        gc.fillRect(0,0,gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        r = (Math.min(gc.getCanvas().getWidth(), gc.getCanvas().getHeight())) * 0.9 / 2;
        midX = gc.getCanvas().getWidth() / 2;
        midY = gc.getCanvas().getHeight() / 2;
        gc.setFill(Color.rgb(175, 175, 175));
        gc.fillOval(midX - r, midY - r, 2 * r, 2 * r);
        gc.strokeOval(midX - r, midY - r, 2 * r, 2 * r);

        for (int i = 0; i < 360; i+=6) {
            if ((i % 5) != 0) {
                gc.setLineWidth(1.0f);
                gc.setStroke(Color.WHITE);
                gc.strokeLine(r * 0.90 * Math.cos(i * Math.PI / 180) + midX,
                              r * 0.90 * Math.sin(i * Math.PI / 180) + midY,
                              r * 0.95 * Math.cos(i * Math.PI / 180) + midX,
                              r * 0.95 * Math.sin(i * Math.PI / 180) + midY);
            } else {
                gc.setStroke(Color.rgb(255, 22, 10));
                gc.setLineWidth(2.0f);
                gc.strokeLine(r * 0.87 * Math.cos(i * Math.PI / 180) + midX,
                              r * 0.87 * Math.sin(i * Math.PI / 180) + midY,
                              r * 0.95 * Math.cos(i * Math.PI / 180) + midX,
                              r * 0.95 * Math.sin(i * Math.PI / 180) + midY);
            }
        }
    }

}
