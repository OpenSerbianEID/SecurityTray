package iaik.pkcs.pkcs11.wrapper;

public class CK_DATE implements Cloneable {
   public char[] year;
   public char[] month;
   public char[] day;

   public Object clone() {
      CK_DATE clone = new CK_DATE();
      clone.year = (char[])((char[])this.year.clone());
      clone.month = (char[])((char[])this.month.clone());
      clone.day = (char[])((char[])this.day.clone());
      return clone;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append(new String(this.day));
      buffer.append('.');
      buffer.append(new String(this.month));
      buffer.append('.');
      buffer.append(new String(this.year));
      buffer.append(" (DD.MM.YYYY)");
      return buffer.toString();
   }
}
