/** Controller kontrollon veprimet dhe balanc�n e p�rdoruesit */
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

 /** askForAction proceson veprimet e p�rdoruesit */
  public void askForAction()
  { char command =  reader.readCommand("Komanda (D, T, C, Q) dhe sasia:");
    if ( command == 'Q' )
          {System.exit(0);}   // ndalon programin
    else  { if ( command == 'D' )  // depoziton nj� shum� parash
               { int amount = reader.readAmount();
                 boolean ok = machine.deposit(amount);
                 if ( ok )
                      { writer.showAction("Depozitim:", amount); }
                 else { writer.showError("Gabim n� depozitim:", amount); }
               }

            else if ( command == 'T' )  // k�rkesa p�r �aj
               { int amount = reader.readAmount();
                 boolean ok = machine.askForTea(amount);
                 if ( ok )
                      { writer.showAction("Bler� me sukses, �aj", amount, amount); }
                 else { writer.showError("Gabim n� k�rkes�:", amount); }
               }
    
            else if ( command == 'C' )  // k�rkesa p�r kafe
            	{ int amount = reader.readAmount();
            	  boolean ok = machine.askForCoffee(amount);
            	  if ( ok )
            	  		{ writer.showAction("Bler� me sukses, kafe", amount, amount*2); }
            	  else { writer.showAction("Gabim n� k�rkes�:", amount); }
            }
    
            else { writer.showAction("Gabim n� komand�:" + command); }
            this.askForAction();   // p�rs�ritet derisa programi t� ndaloj�
          }
  }
}