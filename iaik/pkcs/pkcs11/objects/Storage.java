package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class Storage extends Object {
   protected BooleanAttribute token_;
   protected BooleanAttribute private_;
   protected BooleanAttribute modifiable_;
   protected CharArrayAttribute label_;

   public Storage() {
   }

   protected Storage(Session session, long objectHandle) throws TokenException {
      super(session, objectHandle);
   }

   protected static void putAttributesInTable(Storage object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         object.attributeTable_.put(Attribute.TOKEN, object.token_);
         object.attributeTable_.put(Attribute.PRIVATE, object.private_);
         object.attributeTable_.put(Attribute.MODIFIABLE, object.modifiable_);
         object.attributeTable_.put(Attribute.LABEL, object.label_);
      }
   }

   protected void allocateAttributes() {
      super.allocateAttributes();
      this.token_ = new BooleanAttribute(Attribute.TOKEN);
      this.private_ = new BooleanAttribute(Attribute.PRIVATE);
      this.modifiable_ = new BooleanAttribute(Attribute.MODIFIABLE);
      this.label_ = new CharArrayAttribute(Attribute.LABEL);
      putAttributesInTable(this);
   }

   public java.lang.Object clone() {
      Storage clone = (Storage)super.clone();
      clone.token_ = (BooleanAttribute)this.token_.clone();
      clone.private_ = (BooleanAttribute)this.private_.clone();
      clone.modifiable_ = (BooleanAttribute)this.modifiable_.clone();
      clone.label_ = (CharArrayAttribute)this.label_.clone();
      putAttributesInTable(clone);
      return clone;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof Storage) {
         Storage other = (Storage)otherObject;
         equal = this == other || super.equals(other) && this.token_.equals(other.token_) && this.private_.equals(other.private_) && this.modifiable_.equals(other.modifiable_) && this.label_.equals(other.label_);
      }

      return equal;
   }

   public BooleanAttribute getToken() {
      return this.token_;
   }

   public BooleanAttribute getPrivate() {
      return this.private_;
   }

   public BooleanAttribute getModifiable() {
      return this.modifiable_;
   }

   public CharArrayAttribute getLabel() {
      return this.label_;
   }

   public void readAttributes(Session session) throws TokenException {
      super.readAttributes(session);
      Object.getAttributeValues(session, this.objectHandle_, new Attribute[]{this.token_, this.private_, this.modifiable_, this.label_});
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(128);
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Token: ");
      buffer.append(this.token_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Private: ");
      buffer.append(this.private_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Modifiable: ");
      buffer.append(this.modifiable_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Label: ");
      buffer.append(this.label_.toString());
      return buffer.toString();
   }

   public int hashCode() {
      return this.token_.hashCode() ^ this.private_.hashCode() ^ this.modifiable_.hashCode() ^ this.label_.hashCode();
   }
}
