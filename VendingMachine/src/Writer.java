import java.awt.*;
import javax.swing.*;

/** Writer konstrukton dritaren dalëse */
public class Writer extends JPanel
{ private int WIDTH = 400;  // gjerësia dhe lartësia e dritares
  private int HEIGHT = 200;
  private Machine machine;  // llogaria e klientit
  private String last_action = ""; // veprim që do të shfaqet

  /** Konstruktori Writer inicializon objektin writer
    * @param title - titulli i dritares
    * @param m - adresa e llogarisë së klientit që do të shfaqet */
  public Writer(String title, Machine m)
  { machine = m;
    JFrame my_frame = new JFrame();
    my_frame.getContentPane().add(this);
    my_frame.setTitle(title);
    my_frame.setSize(WIDTH, HEIGHT);
    my_frame.setVisible(true);
  }

  public void paintComponent(Graphics g)
  { g.setColor(Color.gray);
    g.fillRect(0, 0, WIDTH, HEIGHT);  // prapavija
    g.setColor(Color.white);
    int text_margin = 15;
    int text_baseline = 50;
    g.setFont(new Font("Arial", Font.ITALIC, 15));
    g.drawString("Sasia: " + machine.getTeaAmount(), text_margin, text_baseline + 15);
    g.drawString("Sasia: " + machine.getCoffeeAmount(), text_margin + 75, text_baseline + 15);
    g.setFont(new Font("Arial", Font.PLAIN, 20));
    g.drawString("Qaj: 1€", text_margin, text_baseline);
    g.drawString("Kafe: 2€", text_margin + 75, text_baseline);
    g.drawString(last_action, text_margin, text_baseline + 40);
    g.drawString("Bilanci: " + machine.getBalance() + "€",
                 text_margin, text_baseline + 80);
  }

  /** showAction shfaq veprimin e fundit të bërë nga përdoruesi
    * @param message - mesazhi konfirmues
    * @param amount - vlera
    * @param price - çmimi */
  public void showAction(String message, int amount, int price)
  { last_action = message + " " + amount + " copë. Totali: " + price + "€";
    this.repaint();
  }
  
  /** showAction shfaq veprimin e fundit të bërë nga përdoruesi
   * @param message - mesazhi
   * @param amount - vlera */
  public void showAction(String message, int amount)
  { last_action = message + " " + amount + "€";
    this.repaint();
  }

  /** showError shfaq gabimin
   * @param message - mesazhi
   * @param amount - vlera */
  public void showError(String message, int amount)
  { last_action = message + " " + amount;
    this.repaint();
  }
  
  /** showAction shfaq veprimin e fundit të bërë nga përdoruesi
    * @param message - mesazhi */
  public void showAction(String message)
  { last_action = message;
    this.repaint();
  }
}