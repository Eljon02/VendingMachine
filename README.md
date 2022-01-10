# VendingMachine

#### Kërkesa e Detyrës

Write an interface specification for a model class that represents a vending machine. In exchange for money, the machine distributes coffee or tea. When you design the specification, consider the machine’s attributes first: the quantities of coffee, tea, and excess change the machine holds. (Don’t forget to remember the amount of money a customer has inserted!).

When you design the machine’s methods, consider the machine’s responsibilities to **give coffee, tea, and change**, to **refund** a customer’s coins when asked, and to **refuse service** if no coffee or tea is left.

Two of the methods might be:

| Metoda                    | Pëshkrimi                                                    |
| ------------------------- | ------------------------------------------------------------ |
| `insertMoney(int amount)` | the customer inserts the amount of money into the vending machine, and the machine remembers this. |
| `askForCoffee(): boolean` | the customer requests a cup of coffee. If the customer has already inserted an adequate amount of money, and the machine still has coffee in it, the machine produces a cup of coffee, which it signifies by returning the answer, *true* . If the machine is unable to produce a cup of coffee, *false* is returned as the answer. |

Based on your specification, write a model class.

Next, write an output-view of the vending machine and write an input-view and controller that help a user type commands to “insert” money and “buy” coffee and tea from the machine.

> *Detyra 9 nga kapitulli VI ; Programming Principles in Java: Architectures and Interfaces, David Schmidt*

------

#### Qasja në Problem

Arkitektura e projektit bazohet kryesisht në ATM/Bank Account Case Study nga libri "Parimet në Java: Arkitekturat dhe Ndërfaqet, David Schmidt".

Metodat e dhëna në kërkesën e detyrës (nga libri), u konstruktuan fillimisht. Në mënyrë të ngjashme si metoda askForCoffee, u ndërtuan metoda e tjera si askForTea ose askForAction.

Input-View dhe Output-View janë krijuar duke u bazuar në kapitujt e mëparshëm të librit. Sigurisht me disa ndryshime të bëra për t'u përshtatur projektit.

Klasa Model është ndërtuar në atë mënyrë për të përmbushur kërkesën e projektit (përjashtim funksionin e rimbursimit, i cili nuk është i implementuar në projekt).

------

#### Kodi Burimor

###### Input-View Class (Reader)

```java
import javax.swing.*;

/** Reader lexon kërkesat e përdoruesit */
public class Reader
{ private String input_line;

  /** Reader konstruktori inicializon objektin që lexon hyrjet e përdoruesit */
  public Reader()
  { input_line = ""; }

  /** readCommand lexon komandën e dhënë
    * @param message - kërkesën për përdoruesin
    * @return shkronja e parë të komandës e dhënë */
  public char readCommand(String message)
  { // lexoni komandën, pret shkronjën e parë dhe e kthen atë në shkronjë të mëdhe:
    input_line = JOptionPane.showInputDialog(message).trim().toUpperCase();
    return input_line.charAt(0);  // kthen shkronjën e parë
  }

  /** readAmount kthen vlerën numerike në komandën e dhënë
    * @return shumën; ose njofton për gabim dhe kthen 0. */
  public int readAmount()
  { int answer = 0;
    // nxjerr pjesën tjetër të komandës pa përfshirë shkronjën:
    String s = input_line.substring(1, input_line.length());
    s = s.trim();
    if ( s.length() > 0 )  // kontrollon për gabime
         { int number = new Integer(s).intValue();
           answer = number;
         }
    else { JOptionPane.showMessageDialog(null,
              "Gabim në shumën e dhënë!");
         }
    return answer;
  }
}
```

###### Model Class (Machine)

```java
import javax.swing.*;

/** Machine menaxhon funksionet */
public class Machine
{ private int balance;  // bilanci i klientit
  private int tea;  // sasia e çajit
  private int coffee; // sasia e kafesë

  /** Konstruktori Machine inicializon objektin machine.
    * @param coffee_amount - sasia e kafesë 
    * @param tea_amount - sasia e çajit */
  public Machine(int coffee_amount, int tea_amount)
  { if ( coffee_amount < 0 || tea_amount < 0 ) 
  	{
	  JOptionPane.showMessageDialog(null, "Gabim në vlerën e sasisë!", "Gabim", JOptionPane.PLAIN_MESSAGE);
	  System.exit(0);
  	}
    else 
    { balance = 0;
      coffee = coffee_amount;
      tea = tea_amount;
    }
  }

  /** insertMoney shton para në llogarinë e përdoruesit.
    * @param amount - shuma e parave
    * @return true nëse funksioni ishte i suksesshëm */
  public boolean insertMoney(int amount)
  { boolean result = false;
    if ( amount >= 0 )
         { balance = balance + amount;
           result = true;
         }
    else { JOptionPane.showMessageDialog(null, "Gabim në vlerën e dhënë!", "Gabim"
    		, JOptionPane.PLAIN_MESSAGE);}
    return result;
  }

  /** askForTea kontrollon dhe konfirmon blerjen e çajit.
   * @param amount - sasia e kërkuar e çajit
   * @return true nëse funksioni ishte i suksesshëm */
  public boolean askForTea(int amount)
  { boolean result = false;
    if ( amount < 0 )
       { JOptionPane.showMessageDialog(null, "Gabim në sasinë e kërkuar!", "Gabim"
    		   , JOptionPane.PLAIN_MESSAGE); }
    else if ( amount > tea )
         { JOptionPane.showMessageDialog(null,
             "Gabim: Makina nuk ka çaj të mjaftueshëm!"
             + "\nQaj i mbetur: " + tea, "Gabim", JOptionPane.PLAIN_MESSAGE);
         }
    else if ( amount > balance )
    	{ JOptionPane.showMessageDialog(null,
    		"Gabim: Nuk keni bilanc të mjaftueshëm!", "Gabim", JOptionPane.PLAIN_MESSAGE);
    	}
    else { balance = balance - amount;
    	   tea = tea - amount;
           result = true;
         }
    return result;
  }
  
  /** askForCoffee kontrollon dhe konfirmon blerjen e kafesë.
   * @param amount - sasia e kërkuar e kafesë
   * @return true nëse funksioni ishte i suksesshëm */
  public boolean askForCoffee(int amount)
  { boolean result = false;
    if ( amount < 0 )
       { JOptionPane.showMessageDialog(null,
             "Gabim në sasinë e kërkuar!", "Gabim", JOptionPane.PLAIN_MESSAGE); }
    else if ( amount > coffee )
    	{ JOptionPane.showMessageDialog(null,
    		"Gabim: Makina nuk ka kafe të mjaftueshme!"
    		+ "\nKafe të mbetura: " + coffee, "Gabim", JOptionPane.PLAIN_MESSAGE);
    	}
    else if ( (amount * 2) > balance )
         { JOptionPane.showMessageDialog(null,
             "Gabim: Nuk keni bilanc të mjaftueshëm!", "Gabim", JOptionPane.PLAIN_MESSAGE);
         }
    else { balance = balance - (amount * 2);
    	   coffee = coffee - amount;
           result = true;
         }
    return result;
  }

  /** getBalance kthen bilancin aktual të klientit.
   * @return kthen bilancin */
  public int getBalance()
  { return balance; }
  
  /** getTeaAmount kthen sasinë aktuale të çajit.
   * @return kthen sasinë aktuale të çajit */
  public int getTeaAmount()
  { return tea; }
  
  /** getCoffeeAmount kthen sasinë aktuale të kafesë.
   * @return kthen sasinë aktuale të kafesë */
  public int getCoffeeAmount()
  { return coffee; }
}
```

###### Output-View (Writer)

```java
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
```

###### Controller Class

```java
/** Controller kontrollon veprimet dhe balancën e përdoruesit */
public class Controller
{
  private Reader reader;  // input-view
  private Writer writer;  // output-view
  private Machine machine;  // model

  /** Controller konstruktori krijon objektin kontrollues
    * @param r - objekti input-view
    * @param w - objekti output-view
    * @param m - modeli */
  public Controller(Reader r, Writer w, Machine m)
  { reader = r;
    machine = m;
    writer = w;
  }

 /** askForAction proceson veprimet e përdoruesit */
  public void askForAction()
  { char command =  reader.readCommand("Komanda (D, T, C, Q) dhe sasia:");
    if ( command == 'Q' )
          {System.exit(0);}   // ndalon programin
    else  { if ( command == 'D' )  // depoziton një shumë parash
               { int amount = reader.readAmount();
                 boolean ok = machine.insertMoney(amount);
                 if ( ok )
                      { writer.showAction("Depozitim:", amount); }
                 else { writer.showError("Gabim në depozitim:", amount); }
               }

            else if ( command == 'T' )  // kërkesa për çaj
               { int amount = reader.readAmount();
                 boolean ok = machine.askForTea(amount);
                 if ( ok )
                      { writer.showAction("Blerë me sukses, çaj", amount, amount); }
                 else { writer.showError("Gabim në kërkesë:", amount); }
               }
    
            else if ( command == 'C' )  // kërkesa për kafe
            	{ int amount = reader.readAmount();
            	  boolean ok = machine.askForCoffee(amount);
            	  if ( ok )
            	  		{ writer.showAction("Blerë me sukses, kafe", amount, amount*2); }
            	  else { writer.showAction("Gabim në kërkesë:", amount); }
            }
    
            else { writer.showAction("Gabim në komandë:" + command); }
            this.askForAction();   // përsëritet derisa programi të ndalojë
          }
  }
}
```

###### VendingMachine (main)

```java
/** VendingMachine hap një aplikacion që i shet përdoruesit çaj ose kafe.
  * Komandat hyrëse:
  *   D xx (depozitim),
  *   T xx (çaj),
  *   C xx (kafe), ose
  *   Q    (ndalon programin).
  *  xx është vlera numerike.
  * Output: Një dritare që shfaq veprimin e fundit dhe balancën në kohë reale. */

public class VendingMachine
{ public static void main(String[] args)
  { // Instancimi i objekteve nga klasat
    Reader reader = new Reader();
    Machine machine = new Machine(10, 10);
    Writer writer = new Writer("Vending Machine", machine);
    Controller controller = new Controller(reader, writer, machine);
    // Ekzekutimi i programit
    controller.askForAction();
  }
}
```

![alt text](https://i.ibb.co/nDVhb9y/Arkitektura-MVCvm.jpg)
> *Arkitektura MVC e VendingMachine, e paraqitur grafikisht*

------

#### Testimet

![alt text](https://i.ibb.co/qRtMchk/Testimi.gif)
> *Testime. Kujdes imazhi është gif dhe përsëritet! Fundi i testimit pasi ipen ndryshoret (5, -5)*
