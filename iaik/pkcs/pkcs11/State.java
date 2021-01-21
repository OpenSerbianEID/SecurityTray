package iaik.pkcs.pkcs11;

public class State implements Cloneable {
   public static final State RO_PUBLIC_SESSION = new State(0L);
   public static final State RO_USER_FUNCTIONS = new State(1L);
   public static final State RW_PUBLIC_SESSION = new State(2L);
   public static final State RW_USER_FUNCTIONS = new State(3L);
   public static final State RW_SO_FUNCTIONS = new State(4L);
   protected long code_;

   protected State(long code) {
      this.code_ = code;
   }

   public Object clone() {
      try {
         State clone = (State)super.clone();
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof State) {
         State other = (State)otherObject;
         equal = this == other || this.code_ == other.code_;
      }

      return equal;
   }

   public int hashCode() {
      return (int)this.code_;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      String name;
      if (this.code_ == 0L) {
         name = "Read-Only Public Session";
      } else if (this.code_ == 1L) {
         name = "Read-Only User Session";
      } else if (this.code_ == 2L) {
         name = "Read/Write Public Session";
      } else if (this.code_ == 3L) {
         name = "Read/Write User Functions";
      } else if (this.code_ == 4L) {
         name = "Read/Write Security Officer Functions";
      } else {
         name = "ERROR: unknown session state with code: " + this.code_;
      }

      buffer.append(name);
      return buffer.toString();
   }
}
