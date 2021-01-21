package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.wrapper.Functions;

public class MechanismAttribute extends LongAttribute {
   MechanismAttribute() {
   }

   public MechanismAttribute(Long type) {
      super(type);
   }

   public void setMechanism(iaik.pkcs.pkcs11.Mechanism mechanism) {
      this.ckAttribute_.pValue = mechanism != null ? new Long(mechanism.getMechanismCode()) : null;
      this.present_ = true;
   }

   public iaik.pkcs.pkcs11.Mechanism getMechanism() {
      return this.ckAttribute_ != null && this.ckAttribute_.pValue != null ? new iaik.pkcs.pkcs11.Mechanism((Long)this.ckAttribute_.pValue) : null;
   }

   protected String getValueString() {
      String valueString;
      if (this.ckAttribute_ != null && this.ckAttribute_.pValue != null) {
         if ((Long)this.ckAttribute_.pValue != 4294967295L) {
            valueString = Functions.mechanismCodeToString((Long)this.ckAttribute_.pValue);
         } else {
            valueString = "<Information unavailable>";
         }
      } else {
         valueString = "<NULL_PTR>";
      }

      return valueString;
   }
}
