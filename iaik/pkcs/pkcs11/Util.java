package iaik.pkcs.pkcs11;

import iaik.pkcs.pkcs11.wrapper.CK_ATTRIBUTE;
import iaik.pkcs.pkcs11.wrapper.CK_DATE;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Vector;

public class Util {
   public static Date parseTime(char[] timeChars) {
      Date time = null;
      if (timeChars != null && timeChars.length > 2) {
         String timeString = new String(timeChars, 0, timeChars.length - 2);

         try {
            SimpleDateFormat utc = new SimpleDateFormat("yyyyMMddhhmmss");
            utc.setTimeZone(TimeZone.getTimeZone("UTC"));
            time = utc.parse(timeString);
         } catch (ParseException var4) {
         }
      }

      return time;
   }

   public static Date convertToDate(CK_DATE ckDate) {
      Date date = null;
      if (ckDate != null) {
         int year = Integer.parseInt(new String(ckDate.year));
         int month = Integer.parseInt(new String(ckDate.month));
         int day = Integer.parseInt(new String(ckDate.day));
         Calendar calendar = new GregorianCalendar();
         calendar.set(year, 0 + (month - 1), day);
         date = calendar.getTime();
      }

      return date;
   }

   public static CK_DATE convertToCkDate(Date date) {
      CK_DATE ckDate = null;
      if (date != null) {
         Calendar calendar = new GregorianCalendar();
         calendar.setTime(date);
         int year = calendar.get(1);
         int month = calendar.get(2) + 1;
         int day = calendar.get(5);
         ckDate = new CK_DATE();
         ckDate.year = toCharArray(year, 4);
         ckDate.month = toCharArray(month, 2);
         ckDate.day = toCharArray(day, 2);
      }

      return ckDate;
   }

   public static char[] toCharArray(int number, int exactArrayLength) {
      char[] charArray = null;
      String numberString = Integer.toString(number);
      char[] numberChars = numberString.toCharArray();
      int offset;
      char[] charArray;
      if (numberChars.length > exactArrayLength) {
         charArray = new char[exactArrayLength];

         for(offset = 0; offset < charArray.length; ++offset) {
            charArray[offset] = numberChars[offset];
         }
      } else if (numberChars.length < exactArrayLength) {
         charArray = new char[exactArrayLength];
         offset = exactArrayLength - numberChars.length;

         for(int i = 0; i < charArray.length; ++i) {
            charArray[i] = i < offset ? 48 : numberChars[i - offset];
         }
      } else {
         charArray = numberChars;
      }

      return charArray;
   }

   public static char[] toPaddedCharArray(String string, int exactArrayLength, char paddingChar) {
      char[] charArray = null;
      if (string != null) {
         int stringLength = string.length();
         charArray = new char[exactArrayLength];
         string.getChars(0, Math.min(stringLength, exactArrayLength), charArray, 0);

         for(int i = stringLength; i < charArray.length; ++i) {
            charArray[i] = paddingChar;
         }
      }

      return charArray;
   }

   public static byte[] unsignedBigIntergerToByteArray(BigInteger bigInteger) {
      if (bigInteger == null) {
         return null;
      } else {
         byte[] integerBytes = bigInteger.toByteArray();
         byte[] unsignedIntegerBytes;
         if (integerBytes.length > 0 && integerBytes[0] == 0) {
            unsignedIntegerBytes = new byte[integerBytes.length - 1];

            for(int i = 0; i < unsignedIntegerBytes.length; ++i) {
               unsignedIntegerBytes[i] = integerBytes[i + 1];
            }
         } else {
            unsignedIntegerBytes = integerBytes;
         }

         return unsignedIntegerBytes;
      }
   }

   public static CK_ATTRIBUTE[] convertAttributesVectorToArray(Vector attributes) {
      if (attributes == null) {
         return null;
      } else {
         int numberOfAttributes = attributes.size();
         CK_ATTRIBUTE[] attributeArray = new CK_ATTRIBUTE[numberOfAttributes];

         for(int i = 0; i < numberOfAttributes; ++i) {
            Object currentVectorEntry = attributes.elementAt(i);
            attributeArray[i] = currentVectorEntry instanceof CK_ATTRIBUTE ? (CK_ATTRIBUTE)currentVectorEntry : null;
         }

         return attributeArray;
      }
   }
}
