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
  public boolean deposit(int amount)
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