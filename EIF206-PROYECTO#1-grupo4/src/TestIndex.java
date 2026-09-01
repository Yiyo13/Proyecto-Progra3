import javax.swing.JFrame;

import com.proyecto.mvc.views.sistema.FormCategoria;
import com.proyecto.mvc.views.sistema.Index;
import com.proyecto.mvc.views.sistema.IndexCategorias;

public class TestIndex {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test Index");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new IndexCategorias());
        frame.pack();
        frame.setSize(700, 500);
        frame.setVisible(true);
    }
}