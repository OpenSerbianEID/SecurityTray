package iaik.pkcs.pkcs11.wrapper;

public class CK_ATTRIBUTE implements Cloneable {
   public long type;
   public Object pValue;

   public Object clone() {
      try {
         CK_ATTRIBUTE clone = (CK_ATTRIBUTE)super.clone();
         if (clone.pValue instanceof byte[]) {
            clone.pValue = ((byte[])((byte[])this.pValue)).clone();
         } else if (clone.pValue instanceof char[]) {
            clone.pValue = ((char[])((char[])this.pValue)).clone();
         } else if (clone.pValue instanceof CK_DATE) {
            clone.pValue = ((CK_DATE)this.pValue).clone();
         } else if (clone.pValue instanceof boolean[]) {
            clone.pValue = ((boolean[])((boolean[])this.pValue)).clone();
         } else if (clone.pValue instanceof int[]) {
            clone.pValue = ((int[])((int[])this.pValue)).clone();
         } else if (clone.pValue instanceof long[]) {
            clone.pValue = ((long[])((long[])this.pValue)).clone();
         } else if (clone.pValue instanceof Object[]) {
            clone.pValue = ((Object[])((Object[])this.pValue)).clone();
         } else {
            clone.pValue = this.pValue;
         }

         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new PKCS11RuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("type: ");
      buffer.append(this.type);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pValue: ");
      buffer.append(this.pValue.toString());
      return buffer.toString();
   }
}
