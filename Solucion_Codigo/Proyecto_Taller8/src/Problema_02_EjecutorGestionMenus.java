
import java.util.ArrayList;

/**
 * Problema 02: En un restaurant se tiene diferentes tipos de menú para ofrecer 
 * a los clientes. Una cuenta por pagar está compuesta por características como:
 * nombre del cliente, listado de todos las cartas(menú) solicitados por el 
 * cliente, valor a cancelar total, subtotal, Iva.
 * @author Andre Tixe
 */

class Cuenta {
    public String nombreCliente;
    public ArrayList<Menu> menus;
    public double total;
    public double subTotal;
    public double iva;

    public Cuenta(String nombreCliente) {
        this.nombreCliente = nombreCliente;
        this.menus = new ArrayList<>();
    }
    
    public void agregarMenu(Menu m) {
        this.menus.add(m);
    }
    
    public void calcularTotal() {
        for (Menu m : menus) {
            this.subTotal += m.getValorMenu();
        }
        this.iva = this.subTotal * 0.15;
        this.total = this.subTotal + this.iva;
    }

    @Override
    public String toString() {
        String result = "Cliente: " + nombreCliente + "\n";
        for (Menu m : menus) {
            result += m + "\n\n";
        }
        result += "|Subtotal: $" + String.format("%.2f", subTotal) + "|\n"
                + "|IVA: $" + String.format("%.2f", iva) + "|\n"
                + "|Total: $" + String.format("%.2f", total) + "|";
        return result;
    }
    
}

abstract class Menu {
    public String nombrePlato;
    public double valorMenu;
    public double valorInicial;

    public Menu(String nombrePlato, double valorInicial) {
        this.nombrePlato = nombrePlato;
        this.valorInicial = valorInicial;
    }
    
    abstract double calcularValorMenu();

    public double getValorMenu() {
        return valorMenu;
    }
    
    @Override
    public String toString() {
        return "  Plato: " + nombrePlato + " | Valor inicial: $" + valorInicial + " | Valor total: $" + valorMenu;
    }
}

class MenuCarta extends Menu {
    public double valorPorcionGuarnicion;
    public double valorBebida;
    public double porcentajeServicio;

    public MenuCarta(double valorPorcionGuarnicion, double valorBebida, double porcentajeServicio, String nombrePlato, double valorInicial) {
        super(nombrePlato, valorInicial);
        this.valorPorcionGuarnicion = valorPorcionGuarnicion;
        this.valorBebida = valorBebida;
        this.porcentajeServicio = porcentajeServicio;
    }

    @Override
    public double calcularValorMenu() {
        this.valorMenu = valorInicial + valorPorcionGuarnicion + valorBebida + (valorInicial * porcentajeServicio / 100);
        return this.valorMenu;
    }
    
    @Override
    public String toString() {
        return "[Menu a la Carta]\n" + super.toString()
                + "\n  Guarnicion: $" + valorPorcionGuarnicion 
                + " | Bebida: $" + valorBebida 
                + " | Servicio: " + porcentajeServicio + "%";
    }
}

class MenuDia extends Menu {
    public double valorPostre;
    public double valorBebida;

    public MenuDia(double valorPostre, double valorBebida, String nombrePlato, double valorInicial) {
        super(nombrePlato, valorInicial);
        this.valorPostre = valorPostre;
        this.valorBebida = valorBebida;
    }
    
    @Override
    public double calcularValorMenu() {
        this.valorMenu = valorInicial + valorPostre + valorBebida;
        return this.valorMenu;
    }
    
    @Override
    public String toString() {
        return "[Menu del Dia]\n" + super.toString()
                + "\n  Postre: $" + valorPostre 
                + " | Bebida: $" + valorBebida;
    }
}

class MenuNinios extends Menu {
    public double valorPorcionHelado;
    public double valorPorcionPastel;

    public MenuNinios(double valorPorcionHelado, double valorPorcionPastel, String nombrePlato, double valorInicial) {
        super(nombrePlato, valorInicial);
        this.valorPorcionHelado = valorPorcionHelado;
        this.valorPorcionPastel = valorPorcionPastel;
    }
    
    @Override
    public double calcularValorMenu() {
        this.valorMenu = valorInicial + valorPorcionHelado + valorPorcionPastel;
        return this.valorMenu;
    }
    
    @Override
    public String toString() {
        return "[Menu de Ninios]\n" + super.toString()
                + "\n  Helado: $" + valorPorcionHelado 
                + " | Pastel: $" + valorPorcionPastel;
    }
}

class MenuEconomico extends Menu {
    public double porcentajeDescuento;

    public MenuEconomico(double porcentajeDescuento, String nombrePlato, double valorInicial) {
        super(nombrePlato, valorInicial);
        this.porcentajeDescuento = porcentajeDescuento;
    }
    
    @Override
    public double calcularValorMenu() {
        this.valorMenu = valorInicial - (valorInicial * porcentajeDescuento / 100);
        return this.valorMenu;
    }
    
    @Override
    public String toString() {
        return "[Menu Economico]\n" + super.toString()
                + "\n  Descuento: " + porcentajeDescuento + "%";
    }
}

public class Problema_02_EjecutorGestionMenus {
    public static void main(String[] args) {
        Cuenta cuenta = new Cuenta ("Andre Tixe");
        
        MenuCarta mc = new MenuCarta(2.00, 1.50, 10, "Lomo fino", 8.50);
        mc.calcularValorMenu();

        MenuDia md = new MenuDia(1.00, 1.50, "Seco de pollo", 5.00);
        md.calcularValorMenu();

        MenuNinios mn = new MenuNinios(1.50, 1.00, "Nuggets", 4.00);
        mn.calcularValorMenu();

        MenuEconomico me = new MenuEconomico(15, "Arroz con menestra", 3.50);
        me.calcularValorMenu();

        cuenta.agregarMenu(mc);
        cuenta.agregarMenu(md);
        cuenta.agregarMenu(mn);
        cuenta.agregarMenu(me);

        cuenta.calcularTotal();
        System.out.println(cuenta);
    }
}
