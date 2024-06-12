public class Main {
    public static void main(String args[]) {
        PersonaDao dao = new PersonaDao("jdbc:mysql://localhost:3306/tp6-patronproxy","root","");
        Persona p = dao.personaPorId(1);
        System.out.println(p.nombre());
        for (Telefono telefono : p.telefonos()) {
            long inicio = System.currentTimeMillis();
            System.out.println(telefono);
            long fin = System.currentTimeMillis();
            System.out.println("Duracion: " + (fin - inicio)/1000f);
        }
    }
}
