import javax.swing.*;

/** Reader lexon k�rkesat e p�rdoruesit */
public class Reader
{ private String input_line;

  /** Reader konstruktori inicializon objektin q� lexon hyrjet e p�rdoruesit */
  public Reader()
  { input_line = ""; }

  /** readCommand lexon komand�n e dh�n�
    * @param message - k�rkes�n p�r p�rdoruesin
    * @return shkronja e par� t� komand�s e dh�n� */
  public char readCommand(String message)
  { // lexoni komand�n, pret shkronj�n e par� dhe e kthen at� n� shkronj� t� m�dhe:
    input_line = JOptionPane.showInputDialog(message).trim().toUpperCase();
    return input_line.charAt(0);  // kthen shkronj�n e par�
  }

  /** readAmount kthen vler�n numerike n� komand�n e dh�n�
    * @return shum�n; ose njofton p�r gabim dhe kthen 0. */
  public int readAmount()
  { int answer = 0;
    // nxjerr pjes�n tjet�r t� komand�s pa p�rfshir� shkronj�n:
    String s = input_line.substring(1, input_line.length());
    s = s.trim();
    if ( s.length() > 0 )  // kontrollon p�r gabime
         { int number = new Integer(s).intValue();
           answer = number;
         }
    else { JOptionPane.showMessageDialog(null,
              "Gabim n� shum�n e dh�n�!");
         }
    return answer;
  }
}