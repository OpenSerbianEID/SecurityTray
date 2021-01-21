package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;
import java.io.UnsupportedEncodingException;

public class UserInterface extends HardwareFeature {
   private LongAttribute pixelX_;
   private LongAttribute pixelY_;
   private LongAttribute resolution_;
   private LongAttribute charRows_;
   private LongAttribute charColumns_;
   private BooleanAttribute color_;
   private LongAttribute bitsPerPixel_;
   private ByteArrayAttribute charSets_;
   private ByteArrayAttribute encodingMethods_;
   private ByteArrayAttribute mimeTypes_;

   public UserInterface() {
      this.hardwareFeatureType_.setLongValue(HardwareFeature.FeatureType.USER_INTERFACE);
   }

   protected UserInterface(Session session, long objectHandle) throws TokenException {
      super(session, objectHandle);
      this.hardwareFeatureType_.setLongValue(HardwareFeature.FeatureType.USER_INTERFACE);
   }

   public static Object getInstance(Session session, long objectHandle) throws TokenException {
      return new UserInterface(session, objectHandle);
   }

   protected static void putAttributesInTable(UserInterface object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         object.attributeTable_.put(Attribute.PIXEL_X, object.pixelX_);
         object.attributeTable_.put(Attribute.PIXEL_Y, object.pixelY_);
         object.attributeTable_.put(Attribute.RESOLUTION, object.resolution_);
         object.attributeTable_.put(Attribute.CHAR_ROWS, object.charRows_);
         object.attributeTable_.put(Attribute.CHAR_COLUMNS, object.charColumns_);
         object.attributeTable_.put(Attribute.COLOR, object.color_);
         object.attributeTable_.put(Attribute.BITS_PER_PIXEL, object.bitsPerPixel_);
         object.attributeTable_.put(Attribute.CHAR_SETS, object.charSets_);
         object.attributeTable_.put(Attribute.ENCODING_METHODS, object.encodingMethods_);
         object.attributeTable_.put(Attribute.MIME_TYPES, object.mimeTypes_);
      }
   }

   protected void allocateAttributes() {
      super.allocateAttributes();
      this.pixelX_ = new LongAttribute(Attribute.PIXEL_X);
      this.pixelY_ = new LongAttribute(Attribute.PIXEL_Y);
      this.resolution_ = new LongAttribute(Attribute.RESOLUTION);
      this.charRows_ = new LongAttribute(Attribute.CHAR_ROWS);
      this.charColumns_ = new LongAttribute(Attribute.CHAR_COLUMNS);
      this.color_ = new BooleanAttribute(Attribute.COLOR);
      this.bitsPerPixel_ = new LongAttribute(Attribute.BITS_PER_PIXEL);
      this.charSets_ = new ByteArrayAttribute(Attribute.CHAR_SETS);
      this.encodingMethods_ = new ByteArrayAttribute(Attribute.ENCODING_METHODS);
      this.mimeTypes_ = new ByteArrayAttribute(Attribute.MIME_TYPES);
      putAttributesInTable(this);
   }

   public java.lang.Object clone() {
      UserInterface clone = (UserInterface)super.clone();
      clone.pixelX_ = (LongAttribute)this.pixelX_.clone();
      clone.pixelY_ = (LongAttribute)this.pixelY_.clone();
      clone.resolution_ = (LongAttribute)this.resolution_.clone();
      clone.charRows_ = (LongAttribute)this.charRows_.clone();
      clone.charColumns_ = (LongAttribute)this.charColumns_.clone();
      clone.color_ = (BooleanAttribute)this.color_.clone();
      clone.bitsPerPixel_ = (LongAttribute)this.bitsPerPixel_.clone();
      clone.charSets_ = (ByteArrayAttribute)this.charSets_.clone();
      clone.encodingMethods_ = (ByteArrayAttribute)this.encodingMethods_.clone();
      clone.mimeTypes_ = (ByteArrayAttribute)this.mimeTypes_.clone();
      putAttributesInTable(clone);
      return clone;
   }

   public boolean equals(java.lang.Object obj) {
      if (this == obj) {
         return true;
      } else if (!super.equals(obj)) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         UserInterface other = (UserInterface)obj;
         if (this.bitsPerPixel_ == null) {
            if (other.bitsPerPixel_ != null) {
               return false;
            }
         } else if (!this.bitsPerPixel_.equals(other.bitsPerPixel_)) {
            return false;
         }

         if (this.charColumns_ == null) {
            if (other.charColumns_ != null) {
               return false;
            }
         } else if (!this.charColumns_.equals(other.charColumns_)) {
            return false;
         }

         if (this.charRows_ == null) {
            if (other.charRows_ != null) {
               return false;
            }
         } else if (!this.charRows_.equals(other.charRows_)) {
            return false;
         }

         if (this.charSets_ == null) {
            if (other.charSets_ != null) {
               return false;
            }
         } else if (!this.charSets_.equals(other.charSets_)) {
            return false;
         }

         if (this.color_ == null) {
            if (other.color_ != null) {
               return false;
            }
         } else if (!this.color_.equals(other.color_)) {
            return false;
         }

         if (this.encodingMethods_ == null) {
            if (other.encodingMethods_ != null) {
               return false;
            }
         } else if (!this.encodingMethods_.equals(other.encodingMethods_)) {
            return false;
         }

         if (this.mimeTypes_ == null) {
            if (other.mimeTypes_ != null) {
               return false;
            }
         } else if (!this.mimeTypes_.equals(other.mimeTypes_)) {
            return false;
         }

         if (this.pixelX_ == null) {
            if (other.pixelX_ != null) {
               return false;
            }
         } else if (!this.pixelX_.equals(other.pixelX_)) {
            return false;
         }

         if (this.pixelY_ == null) {
            if (other.pixelY_ != null) {
               return false;
            }
         } else if (!this.pixelY_.equals(other.pixelY_)) {
            return false;
         }

         if (this.resolution_ == null) {
            if (other.resolution_ != null) {
               return false;
            }
         } else if (!this.resolution_.equals(other.resolution_)) {
            return false;
         }

         return true;
      }
   }

   public LongAttribute getPixelX() {
      return this.pixelX_;
   }

   public LongAttribute getPixelY() {
      return this.pixelY_;
   }

   public LongAttribute getResolution() {
      return this.resolution_;
   }

   public LongAttribute getCharRows() {
      return this.charRows_;
   }

   public LongAttribute getCharColumns() {
      return this.charColumns_;
   }

   public BooleanAttribute getColor() {
      return this.color_;
   }

   public LongAttribute getBitsPerPixel() {
      return this.bitsPerPixel_;
   }

   public ByteArrayAttribute getCharSets() {
      return this.charSets_;
   }

   public ByteArrayAttribute getEncodingMethods() {
      return this.encodingMethods_;
   }

   public ByteArrayAttribute getMimeTypes() {
      return this.mimeTypes_;
   }

   public int hashCode() {
      return this.pixelX_.hashCode() ^ this.pixelY_.hashCode() ^ this.resolution_.hashCode() ^ this.charRows_.hashCode() ^ this.charColumns_.hashCode() ^ this.color_.hashCode() ^ this.bitsPerPixel_.hashCode() ^ this.charSets_.hashCode() ^ this.encodingMethods_.hashCode() ^ this.mimeTypes_.hashCode();
   }

   public void readAttributes(Session session) throws TokenException {
      super.readAttributes(session);
      Object.getAttributeValues(session, this.objectHandle_, new Attribute[]{this.pixelX_, this.pixelY_, this.resolution_, this.charRows_, this.charColumns_, this.color_, this.bitsPerPixel_, this.charSets_, this.encodingMethods_, this.mimeTypes_});
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(256);
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Pixel X: ");
      buffer.append(this.pixelX_.getValueString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Pixel Y: ");
      buffer.append(this.pixelY_.getValueString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Resolution: ");
      buffer.append(this.resolution_.getValueString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Char Rows: ");
      buffer.append(this.charRows_.getValueString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Char Columns: ");
      buffer.append(this.charColumns_.getValueString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Color: ");
      buffer.append(this.color_.getValueString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Bits per Pixel: ");
      buffer.append(this.bitsPerPixel_.getValueString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Char sets:");

      try {
         buffer.append(new String(this.charSets_.getByteArrayValue(), "ASCII"));
      } catch (UnsupportedEncodingException var5) {
         buffer.append(new String(this.charSets_.getByteArrayValue()));
      }

      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Encoding methods: ");

      try {
         buffer.append(new String(this.encodingMethods_.getByteArrayValue(), "ASCII"));
      } catch (UnsupportedEncodingException var4) {
         buffer.append(new String(this.encodingMethods_.getByteArrayValue()));
      }

      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Mime Types: ");

      try {
         buffer.append(new String(this.mimeTypes_.getByteArrayValue(), "ASCII"));
      } catch (UnsupportedEncodingException var3) {
         buffer.append(new String(this.mimeTypes_.getByteArrayValue()));
      }

      return buffer.toString();
   }
}
