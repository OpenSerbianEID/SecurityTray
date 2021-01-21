package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.TokenRuntimeException;
import iaik.pkcs.pkcs11.objects.Object;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class ObjectHandleParameters implements Parameters {
   protected Object object_;

   public ObjectHandleParameters(Object object) {
      this.object_ = object;
   }

   public java.lang.Object clone() {
      try {
         ObjectHandleParameters clone = (ObjectHandleParameters)super.clone();
         clone.object_ = (Object)this.object_.clone();
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public java.lang.Object getPKCS11ParamsObject() {
      return new Long(this.object_.getObjectHandle());
   }

   public Object getObject() {
      return this.object_;
   }

   public void setObjectHandle(Object object) {
      this.object_ = object;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("The Object: ");
      buffer.append(Constants.NEWLINE);
      buffer.append(this.object_);
      return buffer.toString();
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof ObjectHandleParameters) {
         ObjectHandleParameters other = (ObjectHandleParameters)otherObject;
         equal = this == other || this != null && this.object_.equals(other.object_);
      }

      return equal;
   }

   public int hashCode() {
      return this.object_ != null ? this.object_.hashCode() : 0;
   }
}
