/** VendingMachine hap nj� aplikacion q� i shet p�rdoruesit �aj ose kafe.
  * Komandat hyr�se:
  *   D xx (depozitim),
  *   T xx (�aj),
  *   C xx (kafe), ose
  *   Q    (ndalon programin).
  *  xx �sht� vlera numerike.
  * Output: Nj� dritare q� shfaq veprimin e fundit dhe balanc�n n� koh� reale. */

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