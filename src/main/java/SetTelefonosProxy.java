import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class SetTelefonosProxy implements Set<Telefono> {
    private Set<Telefono> telefonos;
    private int personaId;
    private PersonaDao personaDao;
    private LocalDateTime tiempo;
    public SetTelefonosProxy(int id, PersonaDao personaDao) {
        this.personaId = id;
        this.personaDao = personaDao;
        tiempo = LocalDateTime.now();
    }
    private void cacheTelefonos() {
        if (telefonos != null ||LocalDateTime.now().isBefore(tiempo.plusMinutes(30))){
            telefonos = personaDao.obtenerTelefonosPorPersonaId(personaId);
            tiempo = LocalDateTime.now();
        }
    }

    @Override
    public int size() {
        cacheTelefonos();
        return telefonos.size() ;
    }

    @Override
    public boolean isEmpty() {
        cacheTelefonos();
        return telefonos.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        cacheTelefonos();
        return telefonos.contains(o);
    }

    @Override
    public Iterator<Telefono> iterator() {
        cacheTelefonos();
        return telefonos.iterator();
    }

    @Override
    public Object[] toArray() {
        cacheTelefonos();
        return telefonos.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        cacheTelefonos();
        return telefonos.toArray(a);
    }

    @Override
    public boolean add(Telefono telefono) {
        cacheTelefonos();
        return telefonos.add(telefono);
    }

    @Override
    public boolean remove(Object o) {
        cacheTelefonos();
        return telefonos.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        cacheTelefonos();
        return telefonos.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Telefono> c) {
        cacheTelefonos();
        return telefonos.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        cacheTelefonos();
        return telefonos.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        cacheTelefonos();
        return telefonos.removeAll(c);
    }

    @Override
    public void clear() {
        cacheTelefonos();
        telefonos.clear();
    }
}
