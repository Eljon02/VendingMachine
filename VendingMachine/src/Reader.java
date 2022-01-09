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