package iaik.pkcs.pkcs11.wrapper;

public interface Iterator {
   boolean hasNext();

   Object next();

   void remove();
}
