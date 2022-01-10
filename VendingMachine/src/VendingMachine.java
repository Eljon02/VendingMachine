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