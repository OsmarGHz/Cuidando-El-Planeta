package Actividad12;

public class Actividad12{
    public static void main(String[] args) {
        Gimnasio gym = new Gimnasio("El Sueño");
        Gimnasio gym2=new Gimnasio("La Prueba");

        Socio socio1 = new Socio("Carlos Alberto",2024,12,10);
        Socio socio2 = new Socio("María López",2024,12,10);
        Socio socio3=new Socio("Armando Casas",2024,11,3);
        Socio socio4=new Socio("Gerardo Flores", 2024, 11, 14);
        Socio socio5=new Socio("Jacobo Mendez", 2024, 2, 3);

        //El método "darAlta()" se ejecuta dentro de "agregarSocio()"
        gym.agregarSocio(socio1);
        gym.agregarSocio(socio2);
        gym.agregarSocio(socio3);
        gym.agregarSocio(socio4);
        gym2.agregarSocio(socio5);

        socio1.pagoAnual();

        Gimnasio.noPagado(gym);

        gym.estaRemodelado();
        
        socio1.darBaja();
        socio5.darBaja();

        Gimnasio.porcentajeDadosBaja(gym,Gimnasio.totalDeRegistrados(gym));
        Gimnasio.porcentajeDadosBaja(gym2,Gimnasio.totalDeRegistrados(gym2));

        Gimnasio.mostrarSociosAlta(gym);

        Gimnasio.mostrarSociosAltaEspecifica(2024,12,10,gym);

    }
}
