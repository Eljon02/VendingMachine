import javax.swing.*;

/** Machine menaxhon funksionet */
public class Machine
{ private int balance;  // bilanci i klientit
  private int tea;  // sasia e �ajit
  private int coffee; // sasia e kafes�

  /** Konstruktori Machine inicializon objektin machine.
    * @param coffee_amount - sasia e kafes� 
    * @param tea_amount - sasia e �ajit */
  public Machine(int coffee_amount, int tea_amount)
  { if ( coffee_amount < 0 || tea_amount < 0 ) 
  	{
	  JOptionPane.showMessageDialog(null, "Gabim n� vler�n e sasis�!", "Gabim", JOptionPane.PLAIN_MESSAGE);
	  System.exit(0);
  	}
    else 
    { balance = 0;
      coffee = coffee_amount;
      tea = tea_amount;
    }
  }

  /** insertMoney shton para n� llogarin� e p�rdoruesit.
    * @param amount - shuma e parave
    * @return true n�se funksioni ishte i suksessh�m */
  public boolean deposit(int amount)
  { boolean result = false;
    if ( amount >= 0 )
         { balance = balance + amount;
           result = true;
         }
    else { JOptionPane.showMessageDialog(null, "Gabim n� vler�n e dh�n�!", "Gabim"
    		, JOptionPane.PLAIN_MESSAGE);}
    return result;
  }

  /** askForTea kontrollon dhe konfirmon blerjen e �ajit.
   * @param amount - sasia e k�rkuar e �ajit
   * @return true n�se funksioni ishte i suksessh�m */
  public boolean askForTea(int amount)
  { boolean result = false;
    if ( amount < 0 )
       { JOptionPane.showMessageDialog(null, "Gabim n� sasin� e k�rkuar!", "Gabim"
    		   , JOptionPane.PLAIN_MESSAGE); }
    else if ( amount > tea )
         { JOptionPane.showMessageDialog(null,
             "Gabim: Makina nuk ka �aj t� mjaftuesh�m!"
             + "\nQaj i mbetur: " + tea, "Gabim", JOptionPane.PLAIN_MESSAGE);
         }
    else if ( amount > balance )
    	{ JOptionPane.showMessageDialog(null,
    		"Gabim: Nuk keni bilanc t� mjaftuesh�m!", "Gabim", JOptionPane.PLAIN_MESSAGE);
    	}
    else { balance = balance - amount;
    	   tea = tea - amount;
           result = true;
         }
    return result;
  }
  
  /** askForCoffee kontrollon dhe konfirmon blerjen e kafes�.
   * @param amount - sasia e k�rkuar e kafes�
   * @return true n�se funksioni ishte i suksessh�m */
  public boolean askForCoffee(int amount)
  { boolean result = false;
    if ( amount < 0 )
       { JOptionPane.showMessageDialog(null,
             "Gabim n� sasin� e k�rkuar!", "Gabim", JOptionPane.PLAIN_MESSAGE); }
    else if ( amount > coffee )
    	{ JOptionPane.showMessageDialog(null,
    		"Gabim: Makina nuk ka kafe t� mjaftueshme!"
    		+ "\nKafe t� mbetura: " + coffee, "Gabim", JOptionPane.PLAIN_MESSAGE);
    	}
    else if ( (amount * 2) > balance )
         { JOptionPane.showMessageDialog(null,
             "Gabim: Nuk keni bilanc t� mjaftuesh�m!", "Gabim", JOptionPane.PLAIN_MESSAGE);
         }
    else { balance = balance - (amount * 2);
    	   coffee = coffee - amount;
           result = true;
         }
    return result;
  }

  /** getBalance kthen bilancin aktual t� klientit.
   * @return kthen bilancin */
  public int getBalance()
  { return balance; }
  
  /** getTeaAmount kthen sasin� aktuale t� �ajit.
   * @return kthen sasin� aktuale t� �ajit */
  public int getTeaAmount()
  { return tea; }
  
  /** getCoffeeAmount kthen sasin� aktuale t� kafes�.
   * @return kthen sasin� aktuale t� kafes� */
  public int getCoffeeAmount()
  { return coffee; }
}