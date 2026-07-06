import java.util.Random;

abstract class Jugador {
    private String nombre;
    private int dorsal;
    private String rut;
    private int goles;

    public Jugador() {
    }

    public Jugador(String nombre, int dorsal, String rut, int goles) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.rut = rut;
        this.goles = goles;
    }

    public abstract double calcularValoracion();

    protected double valorBase() {
        return goles * 30;
    }

    public String getNombre() { return nombre; }
    public int getDorsal() { return dorsal; }
    public String getRut() { return rut; }
    public int getGoles() { return goles; }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " | Dorsal: " + dorsal
                + " | Rut: " + rut + " | Goles: " + goles
                + " | Valoracion: " + calcularValoracion();
    }
}

class Atacante extends Jugador {
    private int pases;
    private int recuperaciones;

    public Atacante() {
    }

    public Atacante(String nombre, int dorsal, String rut, int goles, int pases, int recuperaciones) {
        super(nombre, dorsal, rut, goles);
        this.pases = pases;
        this.recuperaciones = recuperaciones;
    }

    @Override
    public double calcularValoracion() {
        return valorBase() + (recuperaciones * 3);
    }

    @Override
    public String toString() {
        return "[Atacante]\n  " + super.toString()
                + " | Pases: " + pases
                + " | Recuperaciones: " + recuperaciones;
    }
}

class Defensor extends Jugador {
    private int pases;
    private int recuperaciones;

    public Defensor() {
    }

    public Defensor(String nombre, int dorsal, String rut, int goles, int pases, int recuperaciones) {
        super(nombre, dorsal, rut, goles);
        this.pases = pases;
        this.recuperaciones = recuperaciones;
    }

    @Override
    public double calcularValoracion() {
        return valorBase() + (recuperaciones * 4);
    }

    @Override
    public String toString() {
        return "[Defensor]\n  " + super.toString()
                + " | Pases: " + pases
                + " | Recuperaciones: " + recuperaciones;
    }
}

class Portero extends Jugador {
    private int atajadas;

    public Portero() {
    }

    public Portero(String nombre, int dorsal, String rut, int goles, int atajadas) {
        super(nombre, dorsal, rut, goles);
        this.atajadas = atajadas;
    }

    @Override
    public double calcularValoracion() {
        return valorBase() + (atajadas * 5);
    }

    @Override
    public String toString() {
        return "[Portero]\n  " + super.toString()
                + " | Atajadas: " + atajadas;
    }
}

public class Problema_03_EjecutorEstadisticas {
    public static void main(String[] args) {
        Random ale = new Random();

        Atacante at1 = new Atacante("Carlos Perez", 9, "12345678-9",
                ale.nextInt(5), ale.nextInt(50), ale.nextInt(15));

        Atacante at2 = new Atacante("Luis Torres", 11, "98765432-1",
                ale.nextInt(5), ale.nextInt(50), ale.nextInt(15));

        Defensor def1 = new Defensor("Miguel Lopez", 4, "11223344-5",
                ale.nextInt(3), ale.nextInt(40), ale.nextInt(20));

        Defensor def2 = new Defensor("Juan Mora", 5, "55667788-9",
                ale.nextInt(3), ale.nextInt(40), ale.nextInt(20));

        Portero por1 = new Portero("Andre Tixe", 1, "99887766-5",
                0, ale.nextInt(10));

        System.out.println("=== ESTADISTICAS DEL PARTIDO ===\n");
        System.out.println(at1);
        System.out.println();
        System.out.println(at2);
        System.out.println();
        System.out.println(def1);
        System.out.println();
        System.out.println(def2);
        System.out.println();
        System.out.println(por1);
    }
}