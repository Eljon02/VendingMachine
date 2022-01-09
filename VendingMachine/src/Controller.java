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
                 boolean ok = machine.deposit(amount);
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