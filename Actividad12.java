package Actividad12;

public class Actividad12{
    public static void main(String[] args) {
        Gimnasio gym = new Gimnasio("El Sueño");

        Socio socio1 = new Socio("Carlos Alberto",2024,12,10);
        Socio socio2 = new Socio("María López",2024,12,10);
        Socio socio3=new Socio("Armando Casas",2024,11,3);
        Socio socio4=new Socio("Gerardo Flores", 2024, 11, 14);

        //El método "darAlta()" se ejecuta dentro de "agregarSocio()"
        gym.agregarSocio(socio1);
        gym.agregarSocio(socio2);
        gym.agregarSocio(socio3);
        gym.agregarSocio(socio4);

        socio1.pagoAnual();

        gym.noPagado(gym);

        gym.estaRemodelado();
        
        socio1.darBaja();

        gym.porcentajeDadosBaja();

        gym.mostrarSociosAlta(gym);

        gym.mostrarSociosAltaEspecifica(2024,12,10,gym);

    }
}
