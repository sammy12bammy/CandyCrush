import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main {
    static int sX = -1;
    static int sY= - 1;
    static int eX = -1;
    static int eY = -1;
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                Visuals vs = new Visuals(new Crush(5, 5));           
                JFrame frame = new JFrame();
                frame.add(vs);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                frame.addMouseListener(new MouseListener(){
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //figure out what click
                        //store click
                        //make move
                        if(sX == -1){
                            sX = e.getX();
                            sY = e.getY();
                            System.out.println("First Click: " + sX + ", " + sY);
                        } else{
                            eX = e.getX();
                            eY = e.getY();
                            System.out.println("Second Click: " + eX + ", " + eY);

                            if(sX > 100 && sX < 700 && sY > 100 && sY < 700 && eX > 100 && eX < 700 && eY > 100 && eY < 700){
                                int size = vs.getCrushSize();
                                int fRow = (sY + 100) / size;
                                int fCol = (sY + 100) / size;
                                int tRow = (sY + 100) / size;
                                int tCol = (sY + 100) / size;

                                vs.makeGameMove(fRow, fCol, tRow, tCol);
                            } else {
                                sX = -1;
                                sY = -1;
                                eX = -1;
                                eY = -1;
                            }
                        }
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {
                
                    }
             
                    @Override
                    public void mouseReleased(MouseEvent e) {
                
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                
                    }
                });
            }
        });
    } 

}
