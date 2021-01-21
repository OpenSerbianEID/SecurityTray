package Crypto;

import java.lang.reflect.Field;

public class ReflectUtils {
   public static Object getValueOf(Object clazz, String lookingForValue) throws Exception {
      Field field = clazz.getClass().getField(lookingForValue);
      Class clazzType = field.getType();
      if (clazzType.toString().equals("double")) {
         return field.getDouble(clazz);
      } else {
         return clazzType.toString().equals("int") ? field.getInt(clazz) : field.get(clazz);
      }
   }
}
