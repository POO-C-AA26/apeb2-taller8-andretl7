import java.util.Random;

abstract class Personaje {
    public int vida, experiencia, victoriasTotal;

    public Personaje(int vida) {
        this.vida = vida;
    }

    public abstract boolean ataque(Personaje objetivo);
    public abstract boolean defensa(Personaje objetivo);

    @Override
    public String toString() {
        return "vida=" + vida
                + ", experiencia=" + experiencia
                + ", victorias=" + victoriasTotal;
    }
}

class Guerrero extends Personaje {
    public int fuerza;

    public Guerrero(int vida, int fuerza) {
        super(vida);
        this.fuerza = fuerza;
    }

    @Override
    public boolean ataque(Personaje objetivo) {
        if (this.vida <= 0 || objetivo.vida <= 0) return false;

        this.experiencia++;
        objetivo.experiencia++;

        Random rnd = new Random();
        boolean exito = rnd.nextBoolean();

        if (exito) {
            objetivo.vida -= fuerza;
            this.victoriasTotal++;
        } else {
            this.vida--;
            objetivo.victoriasTotal++;
        }

        if (objetivo.vida < 0) objetivo.vida = 0;
        return exito;
    }

    @Override
    public boolean defensa(Personaje objetivo) {
        Random rnd = new Random();
        if (rnd.nextBoolean()) {
            this.vida++;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Guerrero{fuerza=" + fuerza + ", " + super.toString() + "}";
    }
}

class Mago extends Personaje {
    public String[] hechizos;

    public Mago(int vida, String[] hechizos) {
        super(vida);
        this.hechizos = hechizos;
    }

    @Override
    public boolean ataque(Personaje objetivo) {
        if (this.vida <= 0 || objetivo.vida <= 0) return false;

        this.experiencia++;
        objetivo.experiencia++;

        Random rnd = new Random();
        boolean exito = rnd.nextInt(100) < 70;

        if (exito) {
            objetivo.vida -= 2;
            this.victoriasTotal++;
        } else {
            this.vida--;
            objetivo.victoriasTotal++;
        }

        if (objetivo.vida < 0) objetivo.vida = 0;
        return exito;
    }

    @Override
    public boolean defensa(Personaje objetivo) {
        Random rnd = new Random();
        if (rnd.nextBoolean()) {
            this.vida += 2;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Mago{hechizos=" + hechizos.length + ", " + super.toString() + "}";
    }
}

class Arquero extends Personaje {
    public int punteria;

    public Arquero(int vida, int punteria) {
        super(vida);
        this.punteria = punteria;
    }

    @Override
    public boolean ataque(Personaje objetivo) {
        if (this.vida <= 0 || objetivo.vida <= 0) return false;

        this.experiencia++;
        objetivo.experiencia++;

        Random rnd = new Random();
        boolean exito = rnd.nextInt(10) < punteria;

        if (exito) {
            objetivo.vida -= punteria;
            this.victoriasTotal++;
        } else {
            this.vida--;
            objetivo.victoriasTotal++;
        }

        if (objetivo.vida < 0) objetivo.vida = 0;
        return exito;
    }

    @Override
    public boolean defensa(Personaje objetivo) {
        Random rnd = new Random();
        return rnd.nextBoolean();
    }

    @Override
    public String toString() {
        return "Arquero{punteria=" + punteria + ", " + super.toString() + "}";
    }
}

public class Problema_01_EjecutorBatalla {
    public static void main(String[] args) {
        String[] hechizos = {"Meteoro", "Tormenta Arcana", "Explosion Magica"};

        Personaje guerrero = new Guerrero(10, 5);
        Personaje mago = new Mago(8, hechizos);
        Personaje arquero = new Arquero(9, 7);

        System.out.println("=== Estado Inicial ===");
        System.out.println(guerrero);
        System.out.println(mago);
        System.out.println(arquero);
        System.out.println();

        System.out.println("Guerrero ataca al Mago");
        System.out.println("Resultado: " + (guerrero.ataque(mago) ? "Guerrero gana" : "Mago contraataca"));
        System.out.println();

        System.out.println("Mago ataca al Arquero");
        System.out.println("Resultado: " + (mago.ataque(arquero) ? "Mago gana" : "Arquero contraataca"));
        System.out.println();

        System.out.println("Arquero ataca al Guerrero");
        System.out.println("Resultado: " + (arquero.ataque(guerrero) ? "Arquero gana" : "Guerrero contraataca"));
        System.out.println();

        System.out.println("=== Estado Final ===");
        System.out.println(guerrero);
        System.out.println(mago);
        System.out.println(arquero);
        System.out.println();

        if (guerrero.vida <= 0) System.out.println("El Guerrero ha sido eliminado.");
        if (mago.vida <= 0) System.out.println("El Mago ha sido eliminado.");
        if (arquero.vida <= 0) System.out.println("El Arquero ha sido eliminado.");
    }
}