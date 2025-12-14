package lewis;
import javax.swing.SwingUtilities;

public class MyMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Canvas canvas = new Canvas();

            // Create and add GameObjects (positions chosen arbitrarily)
            Type_A_GameObject a = new Type_A_GameObject(50, 50);
            a.setVelocity(2);

            Type_B_GameObject b = new Type_B_GameObject(200, 50);
            b.setVelocity(3);

            Type_C_GameObject c = new Type_C_GameObject(50, 300);
            c.setVelocity(2);

            Type_D_GameObject d = new Type_D_GameObject(300, 300);
            d.setVelocity(4);

            canvas.addGameObject(a);
            canvas.addGameObject(b);
            canvas.addGameObject(c);
            canvas.addGameObject(d);

            // Make sure the first object is selected initially
            canvas.setHighlighted(0);
            canvas.requestCanvasFocus();
        });
    }
}
