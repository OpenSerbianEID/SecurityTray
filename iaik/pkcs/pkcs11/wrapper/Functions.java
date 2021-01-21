package iaik.pkcs.pkcs11.wrapper;

import java.math.BigInteger;
import java.util.Hashtable;

public class Functions {
   protected static Hashtable mechansimNames_;
   protected static Hashtable fullEncryptDecryptMechanisms_;
   protected static Hashtable singleOperationEncryptDecryptMechanisms_;
   protected static Hashtable fullSignVerifyMechanisms_;
   protected static Hashtable singleOperationSignVerifyMechanisms_;
   protected static Hashtable signVerifyRecoverMechanisms_;
   protected static Hashtable digestMechanisms_;
   protected static Hashtable keyGenerationMechanisms_;
   protected static Hashtable keyPairGenerationMechanisms_;
   protected static Hashtable wrapUnwrapMechanisms_;
   protected static Hashtable keyDerivationMechanisms_;
   protected static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

   public static String toFullHexString(long value) {
      long currentValue = value;
      StringBuffer stringBuffer = new StringBuffer(16);

      for(int j = 0; j < 16; ++j) {
         int currentDigit = (int)currentValue & 15;
         stringBuffer.append(HEX_DIGITS[currentDigit]);
         currentValue >>>= 4;
      }

      return stringBuffer.reverse().toString();
   }

   public static String toFullHexString(int value) {
      int currentValue = value;
      StringBuffer stringBuffer = new StringBuffer(8);

      for(int i = 0; i < 8; ++i) {
         int currentDigit = currentValue & 15;
         stringBuffer.append(HEX_DIGITS[currentDigit]);
         currentValue >>>= 4;
      }

      return stringBuffer.reverse().toString();
   }

   public static String toHexString(long value) {
      return Long.toHexString(value);
   }

   public static String toHexString(byte[] value) {
      if (value == null) {
         return null;
      } else {
         StringBuffer buffer = new StringBuffer(2 * value.length);

         for(int i = 0; i < value.length; ++i) {
            int single = value[i] & 255;
            if (single < 16) {
               buffer.append('0');
            }

            buffer.append(Integer.toString(single, 16));
         }

         return buffer.toString();
      }
   }

   public static String toBinaryString(long value) {
      return Long.toString(value, 2);
   }

   public static String toBinaryString(byte[] value) {
      BigInteger helpBigInteger = new BigInteger(1, value);
      return helpBigInteger.toString(2);
   }

   public static String slotInfoFlagsToString(long flags) {
      StringBuffer buffer = new StringBuffer();
      boolean notFirst = false;
      if ((flags & 1L) != 0L) {
         buffer.append("CKF_TOKEN_PRESENT");
         notFirst = true;
      }

      if ((flags & 2L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_TOKEN_PRESENT");
         notFirst = true;
      }

      if ((flags & 4L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_HW_SLOT");
      }

      return buffer.toString();
   }

   public static String tokenInfoFlagsToString(long flags) {
      StringBuffer buffer = new StringBuffer();
      boolean notFirst = false;
      if ((flags & 1L) != 0L) {
         buffer.append("CKF_RNG");
         notFirst = true;
      }

      if ((flags & 2L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_WRITE_PROTECTED");
         notFirst = true;
      }

      if ((flags & 4L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_LOGIN_REQUIRED");
         notFirst = true;
      }

      if ((flags & 8L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_USER_PIN_INITIALIZED");
         notFirst = true;
      }

      if ((flags & 32L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_RESTORE_KEY_NOT_NEEDED");
         notFirst = true;
      }

      if ((flags & 64L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_CLOCK_ON_TOKEN");
         notFirst = true;
      }

      if ((flags & 256L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_PROTECTED_AUTHENTICATION_PATH");
         notFirst = true;
      }

      if ((flags & 512L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_DUAL_CRYPTO_OPERATIONS");
         notFirst = true;
      }

      if ((flags & 1024L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_TOKEN_INITIALIZED");
         notFirst = true;
      }

      if ((flags & 2048L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_SECONDARY_AUTHENTICATION");
         notFirst = true;
      }

      if ((flags & 65536L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_USER_PIN_COUNT_LOW");
         notFirst = true;
      }

      if ((flags & 131072L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_USER_PIN_FINAL_TRY");
         notFirst = true;
      }

      if ((flags & 262144L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_USER_PIN_LOCKED");
         notFirst = true;
      }

      if ((flags & 524288L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_USER_PIN_TO_BE_CHANGED");
         notFirst = true;
      }

      if ((flags & 1048576L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_SO_PIN_COUNT_LOW");
         notFirst = true;
      }

      if ((flags & 2097152L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_SO_PIN_FINAL_TRY");
         notFirst = true;
      }

      if ((flags & 4194304L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_USER_PIN_FINAL_TRY");
         notFirst = true;
      }

      if ((flags & 8388608L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_USER_PIN_LOCKED");
         notFirst = true;
      }

      return buffer.toString();
   }

   public static String sessionInfoFlagsToString(long flags) {
      StringBuffer buffer = new StringBuffer();
      boolean notFirst = false;
      if ((flags & 2L) != 0L) {
         buffer.append("CKF_RW_SESSION");
         notFirst = true;
      }

      if ((flags & 4L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_SERIAL_SESSION");
      }

      return buffer.toString();
   }

   public static String sessionStateToString(long state) {
      String name;
      if (state == 0L) {
         name = "CKS_RO_PUBLIC_SESSION";
      } else if (state == 1L) {
         name = "CKS_RO_USER_FUNCTIONS";
      } else if (state == 2L) {
         name = "CKS_RW_PUBLIC_SESSION";
      } else if (state == 3L) {
         name = "CKS_RW_USER_FUNCTIONS";
      } else if (state == 4L) {
         name = "CKS_RW_SO_FUNCTIONS";
      } else {
         name = "ERROR: unknown session state 0x" + toFullHexString(state);
      }

      return name;
   }

   public static String mechanismInfoFlagsToString(long flags) {
      StringBuffer buffer = new StringBuffer();
      boolean notFirst = false;
      if ((flags & 1L) != 0L) {
         buffer.append("CKF_HW");
         notFirst = true;
      }

      if ((flags & 256L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_ENCRYPT");
         notFirst = true;
      }

      if ((flags & 512L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_DECRYPT");
         notFirst = true;
      }

      if ((flags & 1024L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_DIGEST");
         notFirst = true;
      }

      if ((flags & 2048L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_SIGN");
         notFirst = true;
      }

      if ((flags & 4096L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_SIGN_RECOVER");
         notFirst = true;
      }

      if ((flags & 8192L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_VERIFY");
         notFirst = true;
      }

      if ((flags & 16384L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_VERIFY_RECOVER");
         notFirst = true;
      }

      if ((flags & 32768L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_GENERATE");
         notFirst = true;
      }

      if ((flags & 65536L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_GENERATE_KEY_PAIR");
         notFirst = true;
      }

      if ((flags & 131072L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_WRAP");
         notFirst = true;
      }

      if ((flags & 262144L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_UNWRAP");
         notFirst = true;
      }

      if ((flags & 524288L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_DERIVE");
         notFirst = true;
      }

      if ((flags & 1048576L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_EC_F_P");
         notFirst = true;
      }

      if ((flags & 2097152L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_EC_F_2M");
         notFirst = true;
      }

      if ((flags & 4194304L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_EC_ECPARAMETERS");
         notFirst = true;
      }

      if ((flags & 8388608L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_EC_NAMEDCURVE");
         notFirst = true;
      }

      if ((flags & 16777216L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_EC_UNCOMPRESS");
         notFirst = true;
      }

      if ((flags & 33554432L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_EC_COMPRESS");
         notFirst = true;
      }

      if ((flags & 2147483648L) != 0L) {
         if (notFirst) {
            buffer.append(" | ");
         }

         buffer.append("CKF_EXTENSION");
         notFirst = true;
      }

      return buffer.toString();
   }

   public static String mechanismCodeToString(long mechansimCode) {
      if (mechansimNames_ == null) {
         Hashtable mechansimNames = new Hashtable(200);
         mechansimNames.put(new Long(0L), "CKM_RSA_PKCS_KEY_PAIR_GEN");
         mechansimNames.put(new Long(1L), "CKM_RSA_PKCS");
         mechansimNames.put(new Long(2L), "CKM_RSA_9796");
         mechansimNames.put(new Long(3L), "CKM_RSA_X_509");
         mechansimNames.put(new Long(4L), "CKM_MD2_RSA_PKCS");
         mechansimNames.put(new Long(5L), "CKM_MD5_RSA_PKCS");
         mechansimNames.put(new Long(6L), "CKM_SHA1_RSA_PKCS");
         mechansimNames.put(new Long(7L), "CKM_RIPEMD128_RSA_PKCS");
         mechansimNames.put(new Long(8L), "CKM_RIPEMD160_RSA_PKCS");
         mechansimNames.put(new Long(9L), "CKM_RSA_PKCS_OAEP");
         mechansimNames.put(new Long(10L), "CKM_RSA_X9_31_KEY_PAIR_GEN");
         mechansimNames.put(new Long(11L), "CKM_RSA_X9_31");
         mechansimNames.put(new Long(12L), "CKM_SHA1_RSA_X9_31");
         mechansimNames.put(new Long(13L), "CKM_RSA_PKCS_PSS");
         mechansimNames.put(new Long(14L), "CKM_SHA1_RSA_PKCS_PSS");
         mechansimNames.put(new Long(16L), "CKM_DSA_KEY_PAIR_GEN");
         mechansimNames.put(new Long(17L), "CKM_DSA");
         mechansimNames.put(new Long(18L), "CKM_DSA_SHA1");
         mechansimNames.put(new Long(32L), "CKM_DH_PKCS_KEY_PAIR_GEN");
         mechansimNames.put(new Long(33L), "CKM_DH_PKCS_DERIVE");
         mechansimNames.put(new Long(48L), "CKM_X9_42_DH_KEY_PAIR_GEN");
         mechansimNames.put(new Long(49L), "CKM_X9_42_DH_DERIVE");
         mechansimNames.put(new Long(50L), "CKM_X9_42_DH_HYBRID_DERIVE");
         mechansimNames.put(new Long(51L), "CKM_X9_42_MQV_DERIVE");
         mechansimNames.put(new Long(64L), "CKM_SHA256_RSA_PKCS");
         mechansimNames.put(new Long(65L), "CKM_SHA384_RSA_PKCS");
         mechansimNames.put(new Long(66L), "CKM_SHA512_RSA_PKCS");
         mechansimNames.put(new Long(67L), "CKM_SHA256_RSA_PKCS_PSS");
         mechansimNames.put(new Long(68L), "CKM_SHA384_RSA_PKCS_PSS");
         mechansimNames.put(new Long(69L), "CKM_SHA512_RSA_PKCS_PSS");
         mechansimNames.put(new Long(256L), "CKM_RC2_KEY_GEN");
         mechansimNames.put(new Long(257L), "CKM_RC2_ECB");
         mechansimNames.put(new Long(258L), "CKM_RC2_CBC");
         mechansimNames.put(new Long(259L), "CKM_RC2_MAC");
         mechansimNames.put(new Long(260L), "CKM_RC2_MAC_GENERAL");
         mechansimNames.put(new Long(261L), "CKM_RC2_CBC_PAD");
         mechansimNames.put(new Long(272L), "CKM_RC4_KEY_GEN");
         mechansimNames.put(new Long(273L), "CKM_RC4");
         mechansimNames.put(new Long(288L), "CKM_DES_KEY_GEN");
         mechansimNames.put(new Long(289L), "CKM_DES_ECB");
         mechansimNames.put(new Long(290L), "CKM_DES_CBC");
         mechansimNames.put(new Long(291L), "CKM_DES_MAC");
         mechansimNames.put(new Long(292L), "CKM_DES_MAC_GENERAL");
         mechansimNames.put(new Long(293L), "CKM_DES_CBC_PAD");
         mechansimNames.put(new Long(304L), "CKM_DES2_KEY_GEN");
         mechansimNames.put(new Long(305L), "CKM_DES3_KEY_GEN");
         mechansimNames.put(new Long(306L), "CKM_DES3_ECB");
         mechansimNames.put(new Long(307L), "CKM_DES3_CBC");
         mechansimNames.put(new Long(308L), "CKM_DES3_MAC");
         mechansimNames.put(new Long(309L), "CKM_DES3_MAC_GENERAL");
         mechansimNames.put(new Long(310L), "CKM_DES3_CBC_PAD");
         mechansimNames.put(new Long(320L), "CKM_CDMF_KEY_GEN");
         mechansimNames.put(new Long(321L), "CKM_CDMF_ECB");
         mechansimNames.put(new Long(322L), "CKM_CDMF_CBC");
         mechansimNames.put(new Long(323L), "CKM_CDMF_MAC");
         mechansimNames.put(new Long(324L), "CKM_CDMF_MAC_GENERAL");
         mechansimNames.put(new Long(325L), "CKM_CDMF_CBC_PAD");
         mechansimNames.put(new Long(336L), "CKM_DES_OFB64");
         mechansimNames.put(new Long(337L), "CKM_DES_OFB8");
         mechansimNames.put(new Long(338L), "CKM_DES_CFB64");
         mechansimNames.put(new Long(339L), "CKM_DES_CFB8");
         mechansimNames.put(new Long(512L), "CKM_MD2");
         mechansimNames.put(new Long(513L), "CKM_MD2_HMAC");
         mechansimNames.put(new Long(514L), "CKM_MD2_HMAC_GENERAL");
         mechansimNames.put(new Long(528L), "CKM_MD5");
         mechansimNames.put(new Long(529L), "CKM_MD5_HMAC");
         mechansimNames.put(new Long(530L), "CKM_MD5_HMAC_GENERAL");
         mechansimNames.put(new Long(544L), "CKM_SHA_1");
         mechansimNames.put(new Long(545L), "CKM_SHA_1_HMAC");
         mechansimNames.put(new Long(546L), "CKM_SHA_1_HMAC_GENERAL");
         mechansimNames.put(new Long(560L), "CKM_RIPEMD128");
         mechansimNames.put(new Long(561L), "CKM_RIPEMD128_HMAC");
         mechansimNames.put(new Long(562L), "CKM_RIPEMD128_HMAC_GENERAL");
         mechansimNames.put(new Long(576L), "CKM_RIPEMD160");
         mechansimNames.put(new Long(577L), "CKM_RIPEMD160_HMAC");
         mechansimNames.put(new Long(578L), "CKM_RIPEMD160_HMAC_GENERAL");
         mechansimNames.put(new Long(592L), "CKM_SHA256");
         mechansimNames.put(new Long(593L), "CKM_SHA256_HMAC");
         mechansimNames.put(new Long(594L), "CKM_SHA256_HMAC_GENERAL");
         mechansimNames.put(new Long(608L), "CKM_SHA384");
         mechansimNames.put(new Long(609L), "CKM_SHA384_HMAC");
         mechansimNames.put(new Long(610L), "CKM_SHA384_HMAC_GENERAL");
         mechansimNames.put(new Long(624L), "CKM_SHA512");
         mechansimNames.put(new Long(625L), "CKM_SHA512_HMAC");
         mechansimNames.put(new Long(626L), "CKM_SHA512_HMAC_GENERAL");
         mechansimNames.put(new Long(768L), "CKM_CAST_KEY_GEN");
         mechansimNames.put(new Long(769L), "CKM_CAST_ECB");
         mechansimNames.put(new Long(770L), "CKM_CAST_CBC");
         mechansimNames.put(new Long(771L), "CKM_CAST_MAC");
         mechansimNames.put(new Long(772L), "CKM_CAST_MAC_GENERAL");
         mechansimNames.put(new Long(773L), "CKM_CAST_CBC_PAD");
         mechansimNames.put(new Long(784L), "CKM_CAST3_KEY_GEN");
         mechansimNames.put(new Long(785L), "CKM_CAST3_ECB");
         mechansimNames.put(new Long(786L), "CKM_CAST3_CBC");
         mechansimNames.put(new Long(787L), "CKM_CAST3_MAC");
         mechansimNames.put(new Long(788L), "CKM_CAST3_MAC_GENERAL");
         mechansimNames.put(new Long(789L), "CKM_CAST3_CBC_PAD");
         mechansimNames.put(new Long(800L), "CKM_CAST5_KEY_GEN");
         mechansimNames.put(new Long(800L), "CKM_CAST128_KEY_GEN");
         mechansimNames.put(new Long(801L), "CKM_CAST5_ECB");
         mechansimNames.put(new Long(801L), "CKM_CAST128_ECB");
         mechansimNames.put(new Long(802L), "CKM_CAST5_CBC");
         mechansimNames.put(new Long(802L), "CKM_CAST128_CBC");
         mechansimNames.put(new Long(803L), "CKM_CAST5_MAC");
         mechansimNames.put(new Long(803L), "CKM_CAST128_MAC");
         mechansimNames.put(new Long(804L), "CKM_CAST5_MAC_GENERAL");
         mechansimNames.put(new Long(804L), "CKM_CAST128_MAC_GENERAL");
         mechansimNames.put(new Long(805L), "CKM_CAST5_CBC_PAD");
         mechansimNames.put(new Long(805L), "CKM_CAST128_CBC_PAD");
         mechansimNames.put(new Long(816L), "CKM_RC5_KEY_GEN");
         mechansimNames.put(new Long(817L), "CKM_RC5_ECB");
         mechansimNames.put(new Long(818L), "CKM_RC5_CBC");
         mechansimNames.put(new Long(819L), "CKM_RC5_MAC");
         mechansimNames.put(new Long(820L), "CKM_RC5_MAC_GENERAL");
         mechansimNames.put(new Long(821L), "CKM_RC5_CBC_PAD");
         mechansimNames.put(new Long(832L), "CKM_IDEA_KEY_GEN");
         mechansimNames.put(new Long(833L), "CKM_IDEA_ECB");
         mechansimNames.put(new Long(834L), "CKM_IDEA_CBC");
         mechansimNames.put(new Long(835L), "CKM_IDEA_MAC");
         mechansimNames.put(new Long(836L), "CKM_IDEA_MAC_GENERAL");
         mechansimNames.put(new Long(837L), "CKM_IDEA_CBC_PAD");
         mechansimNames.put(new Long(848L), "CKM_GENERIC_SECRET_KEY_GEN");
         mechansimNames.put(new Long(864L), "CKM_CONCATENATE_BASE_AND_KEY");
         mechansimNames.put(new Long(866L), "CKM_CONCATENATE_BASE_AND_DATA");
         mechansimNames.put(new Long(867L), "CKM_CONCATENATE_DATA_AND_BASE");
         mechansimNames.put(new Long(868L), "CKM_XOR_BASE_AND_DATA");
         mechansimNames.put(new Long(869L), "CKM_EXTRACT_KEY_FROM_KEY");
         mechansimNames.put(new Long(880L), "CKM_SSL3_PRE_MASTER_KEY_GEN");
         mechansimNames.put(new Long(881L), "CKM_SSL3_MASTER_KEY_DERIVE");
         mechansimNames.put(new Long(882L), "CKM_SSL3_KEY_AND_MAC_DERIVE");
         mechansimNames.put(new Long(883L), "CKM_SSL3_MASTER_KEY_DERIVE_DH");
         mechansimNames.put(new Long(884L), "CKM_TLS_PRE_MASTER_KEY_GEN");
         mechansimNames.put(new Long(885L), "CKM_TLS_MASTER_KEY_DERIVE");
         mechansimNames.put(new Long(886L), "CKM_TLS_KEY_AND_MAC_DERIVE");
         mechansimNames.put(new Long(887L), "CKM_TLS_MASTER_KEY_DERIVE_DH");
         mechansimNames.put(new Long(896L), "CKM_SSL3_MD5_MAC");
         mechansimNames.put(new Long(897L), "CKM_SSL3_SHA1_MAC");
         mechansimNames.put(new Long(912L), "CKM_MD5_KEY_DERIVATION");
         mechansimNames.put(new Long(913L), "CKM_MD2_KEY_DERIVATION");
         mechansimNames.put(new Long(914L), "CKM_SHA1_KEY_DERIVATION");
         mechansimNames.put(new Long(915L), "CKM_SHA256_KEY_DERIVATION");
         mechansimNames.put(new Long(916L), "CKM_SHA384_KEY_DERIVATION");
         mechansimNames.put(new Long(917L), "CKM_SHA512_KEY_DERIVATION");
         mechansimNames.put(new Long(928L), "CKM_PBE_MD2_DES_CBC");
         mechansimNames.put(new Long(929L), "CKM_PBE_MD5_DES_CBC");
         mechansimNames.put(new Long(930L), "CKM_PBE_MD5_CAST_CBC");
         mechansimNames.put(new Long(931L), "CKM_PBE_MD5_CAST3_CBC");
         mechansimNames.put(new Long(932L), "CKM_PBE_MD5_CAST5_CBC");
         mechansimNames.put(new Long(932L), "CKM_PBE_MD5_CAST128_CBC");
         mechansimNames.put(new Long(933L), "CKM_PBE_SHA1_CAST5_CBC");
         mechansimNames.put(new Long(933L), "CKM_PBE_SHA1_CAST128_CBC");
         mechansimNames.put(new Long(934L), "CKM_PBE_SHA1_RC4_128");
         mechansimNames.put(new Long(935L), "CKM_PBE_SHA1_RC4_40");
         mechansimNames.put(new Long(936L), "CKM_PBE_SHA1_DES3_EDE_CBC");
         mechansimNames.put(new Long(937L), "CKM_PBE_SHA1_DES2_EDE_CBC");
         mechansimNames.put(new Long(938L), "CKM_PBE_SHA1_RC2_128_CBC");
         mechansimNames.put(new Long(939L), "CKM_PBE_SHA1_RC2_40_CBC");
         mechansimNames.put(new Long(944L), "CKM_PKCS5_PBKD2");
         mechansimNames.put(new Long(960L), "CKM_PBA_SHA1_WITH_SHA1_HMAC");
         mechansimNames.put(new Long(976L), "CKM_WTLS_PRE_MASTER_KEY_GEN");
         mechansimNames.put(new Long(977L), "CKM_WTLS_MASTER_KEY_DERIVE");
         mechansimNames.put(new Long(978L), "CKM_WTLS_MASTER_KEY_DERIVE_DH_ECC");
         mechansimNames.put(new Long(979L), "CKM_WTLS_PRF");
         mechansimNames.put(new Long(980L), "CKM_WTLS_SERVER_KEY_AND_MAC_DERIVE");
         mechansimNames.put(new Long(981L), "CKM_WTLS_CLIENT_KEY_AND_MAC_DERIVE");
         mechansimNames.put(new Long(1024L), "CKM_KEY_WRAP_LYNKS");
         mechansimNames.put(new Long(1025L), "CKM_KEY_WRAP_SET_OAEP");
         mechansimNames.put(new Long(1280L), "CKM_CMS_SIG");
         mechansimNames.put(new Long(4096L), "CKM_SKIPJACK_KEY_GEN");
         mechansimNames.put(new Long(4097L), "CKM_SKIPJACK_ECB64");
         mechansimNames.put(new Long(4098L), "CKM_SKIPJACK_CBC64");
         mechansimNames.put(new Long(4099L), "CKM_SKIPJACK_OFB64");
         mechansimNames.put(new Long(4100L), "CKM_SKIPJACK_CFB64");
         mechansimNames.put(new Long(4101L), "CKM_SKIPJACK_CFB32");
         mechansimNames.put(new Long(4102L), "CKM_SKIPJACK_CFB16");
         mechansimNames.put(new Long(4103L), "CKM_SKIPJACK_CFB8");
         mechansimNames.put(new Long(4104L), "CKM_SKIPJACK_WRAP");
         mechansimNames.put(new Long(4105L), "CKM_SKIPJACK_PRIVATE_WRAP");
         mechansimNames.put(new Long(4106L), "CKM_SKIPJACK_RELAYX");
         mechansimNames.put(new Long(4112L), "CKM_KEA_KEY_PAIR_GEN");
         mechansimNames.put(new Long(4113L), "CKM_KEA_KEY_DERIVE");
         mechansimNames.put(new Long(4128L), "CKM_FORTEZZA_TIMESTAMP");
         mechansimNames.put(new Long(4144L), "CKM_BATON_KEY_GEN");
         mechansimNames.put(new Long(4145L), "CKM_BATON_ECB128");
         mechansimNames.put(new Long(4146L), "CKM_BATON_ECB96");
         mechansimNames.put(new Long(4147L), "CKM_BATON_CBC128");
         mechansimNames.put(new Long(4148L), "CKM_BATON_COUNTER");
         mechansimNames.put(new Long(4149L), "CKM_BATON_SHUFFLE");
         mechansimNames.put(new Long(4150L), "CKM_BATON_WRAP");
         mechansimNames.put(new Long(4160L), "CKM_ECDSA_KEY_PAIR_GEN");
         mechansimNames.put(new Long(4160L), "CKM_EC_KEY_PAIR_GEN");
         mechansimNames.put(new Long(4161L), "CKM_ECDSA");
         mechansimNames.put(new Long(4162L), "CKM_ECDSA_SHA1");
         mechansimNames.put(new Long(4176L), "CKM_ECDH1_DERIVE");
         mechansimNames.put(new Long(4177L), "CKM_ECDH1_COFACTOR_DERIVE");
         mechansimNames.put(new Long(4178L), "CKM_ECMQV_DERIVE");
         mechansimNames.put(new Long(4192L), "CKM_JUNIPER_KEY_GEN");
         mechansimNames.put(new Long(4193L), "CKM_JUNIPER_ECB128");
         mechansimNames.put(new Long(4194L), "CKM_JUNIPER_CBC128");
         mechansimNames.put(new Long(4195L), "CKM_JUNIPER_COUNTER");
         mechansimNames.put(new Long(4196L), "CKM_JUNIPER_SHUFFLE");
         mechansimNames.put(new Long(4197L), "CKM_JUNIPER_WRAP");
         mechansimNames.put(new Long(4208L), "CKM_FASTHASH");
         mechansimNames.put(new Long(4224L), "CKM_AES_KEY_GEN");
         mechansimNames.put(new Long(4225L), "CKM_AES_ECB");
         mechansimNames.put(new Long(4226L), "CKM_AES_CBC");
         mechansimNames.put(new Long(4227L), "CKM_AES_MAC");
         mechansimNames.put(new Long(4228L), "CKM_AES_MAC_GENERAL");
         mechansimNames.put(new Long(4229L), "CKM_AES_CBC_PAD");
         mechansimNames.put(new Long(4240L), "CKM_BLOWFISH_KEY_GEN");
         mechansimNames.put(new Long(4241L), "CKM_BLOWFISH_CBC");
         mechansimNames.put(new Long(4242L), "CKM_TWOFISH_KEY_GEN");
         mechansimNames.put(new Long(4243L), "CKM_TWOFISH_CBC");
         mechansimNames.put(new Long(4352L), "CKM_DES_ECB_ENCRYPT_DATA");
         mechansimNames.put(new Long(4353L), "CKM_DES_CBC_ENCRYPT_DATA");
         mechansimNames.put(new Long(4354L), "CKM_DES3_ECB_ENCRYPT_DATA");
         mechansimNames.put(new Long(4355L), "CKM_DES3_CBC_ENCRYPT_DATA");
         mechansimNames.put(new Long(4356L), "CKM_AES_ECB_ENCRYPT_DATA");
         mechansimNames.put(new Long(4357L), "CKM_AES_CBC_ENCRYPT_DATA");
         mechansimNames.put(new Long(8192L), "CKM_DSA_PARAMETER_GEN");
         mechansimNames.put(new Long(8193L), "CKM_DH_PKCS_PARAMETER_GEN");
         mechansimNames.put(new Long(8194L), "CKM_X9_42_DH_PARAMETER_GEN");
         mechansimNames.put(new Long(2147483648L), "CKM_VENDOR_DEFINED");
         mechansimNames_ = mechansimNames;
      }

      Long mechansimCodeObject = new Long(mechansimCode);
      Object entry = mechansimNames_.get(mechansimCodeObject);
      String mechanismName = entry != null ? entry.toString() : "Unknwon mechanism with code: 0x" + toFullHexString(mechansimCode);
      return mechanismName;
   }

   public static String classTypeToString(long classType) {
      String name;
      if (classType == 0L) {
         name = "CKO_DATA";
      } else if (classType == 1L) {
         name = "CKO_CERTIFICATE";
      } else if (classType == 2L) {
         name = "CKO_PUBLIC_KEY";
      } else if (classType == 3L) {
         name = "CKO_PRIVATE_KEY";
      } else if (classType == 4L) {
         name = "CKO_SECRET_KEY";
      } else if (classType == 5L) {
         name = "CKO_HW_FEATURE";
      } else if (classType == 6L) {
         name = "CKO_DOMAIN_PARAMETERS";
      } else if (classType == 2147483648L) {
         name = "CKO_VENDOR_DEFINED";
      } else {
         name = "ERROR: unknown classType with code: 0x" + toFullHexString(classType);
      }

      return name;
   }

   public static boolean equals(byte[] array1, byte[] array2) {
      boolean equal = false;
      if (array1 == array2) {
         equal = true;
      } else if (array1 != null && array2 != null) {
         int length = array1.length;
         if (length == array2.length) {
            equal = true;

            for(int i = 0; i < length; ++i) {
               if (array1[i] != array2[i]) {
                  equal = false;
                  break;
               }
            }
         } else {
            equal = false;
         }
      } else {
         equal = false;
      }

      return equal;
   }

   public static boolean equals(char[] array1, char[] array2) {
      boolean equal = false;
      if (array1 == array2) {
         equal = true;
      } else if (array1 != null && array2 != null) {
         int length = array1.length;
         if (length == array2.length) {
            equal = true;

            for(int i = 0; i < length; ++i) {
               if (array1[i] != array2[i]) {
                  equal = false;
                  break;
               }
            }
         } else {
            equal = false;
         }
      } else {
         equal = false;
      }

      return equal;
   }

   public static boolean equals(long[] array1, long[] array2) {
      boolean equal = false;
      if (array1 == array2) {
         equal = true;
      } else if (array1 != null && array2 != null) {
         int length = array1.length;
         if (length == array2.length) {
            equal = true;

            for(int i = 0; i < length; ++i) {
               if (array1[i] != array2[i]) {
                  equal = false;
                  break;
               }
            }
         } else {
            equal = false;
         }
      } else {
         equal = false;
      }

      return equal;
   }

   public static boolean equals(CK_DATE date1, CK_DATE date2) {
      boolean equal = false;
      if (date1 == date2) {
         equal = true;
      } else if (date1 != null && date2 != null) {
         equal = equals(date1.year, date2.year) && equals(date1.month, date2.month) && equals(date1.day, date2.day);
      } else {
         equal = false;
      }

      return equal;
   }

   public static int hashCode(byte[] array) {
      int hash = 0;
      if (array != null) {
         for(int i = 0; i < 4 && i < array.length; ++i) {
            hash ^= (255 & array[i]) << (i % 4 << 3);
         }
      }

      return hash;
   }

   public static int hashCode(char[] array) {
      int hash = 0;
      if (array != null) {
         for(int i = 0; i < 4 && i < array.length; ++i) {
            hash ^= -1 & array[i];
         }
      }

      return hash;
   }

   public static int hashCode(long[] array) {
      int hash = 0;
      if (array != null) {
         for(int i = 0; i < 4 && i < array.length; ++i) {
            hash = (int)((long)hash ^ -1L & array[i] >> 4);
            hash = (int)((long)hash ^ -1L & array[i]);
         }
      }

      return hash;
   }

   public static int hashCode(CK_DATE date) {
      int hash = 0;
      if (date != null) {
         if (date.year.length == 4) {
            hash ^= ('\uffff' & date.year[0]) << 16;
            hash ^= '\uffff' & date.year[1];
            hash ^= ('\uffff' & date.year[2]) << 16;
            hash ^= '\uffff' & date.year[3];
         }

         if (date.month.length == 2) {
            hash ^= ('\uffff' & date.month[0]) << 16;
            hash ^= '\uffff' & date.month[1];
         }

         if (date.day.length == 2) {
            hash ^= ('\uffff' & date.day[0]) << 16;
            hash ^= '\uffff' & date.day[1];
         }
      }

      return hash;
   }

   public static boolean isFullEncryptDecryptMechanism(long mechanismCode) {
      if (fullEncryptDecryptMechanisms_ == null) {
         Hashtable fullEncryptDecryptMechanisms = new Hashtable();
         fullEncryptDecryptMechanisms.put(new Long(257L), "CKM_RC2_ECB");
         fullEncryptDecryptMechanisms.put(new Long(258L), "CKM_RC2_CBC");
         fullEncryptDecryptMechanisms.put(new Long(261L), "CKM_RC2_CBC_PAD");
         fullEncryptDecryptMechanisms.put(new Long(273L), "CKM_RC4");
         fullEncryptDecryptMechanisms.put(new Long(289L), "CKM_DES_ECB");
         fullEncryptDecryptMechanisms.put(new Long(290L), "CKM_DES_CBC");
         fullEncryptDecryptMechanisms.put(new Long(293L), "CKM_DES_CBC_PAD");
         fullEncryptDecryptMechanisms.put(new Long(306L), "CKM_DES3_ECB");
         fullEncryptDecryptMechanisms.put(new Long(307L), "CKM_DES3_CBC");
         fullEncryptDecryptMechanisms.put(new Long(310L), "CKM_DES3_CBC_PAD");
         fullEncryptDecryptMechanisms.put(new Long(321L), "CKM_CDMF_ECB");
         fullEncryptDecryptMechanisms.put(new Long(322L), "CKM_CDMF_CBC");
         fullEncryptDecryptMechanisms.put(new Long(325L), "CKM_CDMF_CBC_PAD");
         fullEncryptDecryptMechanisms.put(new Long(336L), "CKM_DES_OFB64");
         fullEncryptDecryptMechanisms.put(new Long(337L), "CKM_DES_OFB8");
         fullEncryptDecryptMechanisms.put(new Long(338L), "CKM_DES_CFB64");
         fullEncryptDecryptMechanisms.put(new Long(339L), "CKM_DES_CFB8");
         fullEncryptDecryptMechanisms.put(new Long(769L), "CKM_CAST_ECB");
         fullEncryptDecryptMechanisms.put(new Long(770L), "CKM_CAST_CBC");
         fullEncryptDecryptMechanisms.put(new Long(773L), "CKM_CAST_CBC_PAD");
         fullEncryptDecryptMechanisms.put(new Long(785L), "CKM_CAST3_ECB");
         fullEncryptDecryptMechanisms.put(new Long(786L), "CKM_CAST3_CBC");
         fullEncryptDecryptMechanisms.put(new Long(789L), "CKM_CAST3_CBC_PAD");
         fullEncryptDecryptMechanisms.put(new Long(801L), "CKM_CAST5_ECB");
         fullEncryptDecryptMechanisms.put(new Long(801L), "CKM_CAST128_ECB");
         fullEncryptDecryptMechanisms.put(new Long(802L), "CKM_CAST5_CBC");
         fullEncryptDecryptMechanisms.put(new Long(802L), "CKM_CAST128_CBC");
         fullEncryptDecryptMechanisms.put(new Long(805L), "CKM_CAST5_CBC_PAD");
         fullEncryptDecryptMechanisms.put(new Long(805L), "CKM_CAST128_CBC_PAD");
         fullEncryptDecryptMechanisms.put(new Long(817L), "CKM_RC5_ECB");
         fullEncryptDecryptMechanisms.put(new Long(818L), "CKM_RC5_CBC");
         fullEncryptDecryptMechanisms.put(new Long(821L), "CKM_RC5_CBC_PAD");
         fullEncryptDecryptMechanisms.put(new Long(4225L), "CKM_AES_ECB");
         fullEncryptDecryptMechanisms.put(new Long(4226L), "CKM_AES_CBC");
         fullEncryptDecryptMechanisms.put(new Long(4229L), "CKM_AES_CBC_PAD");
         fullEncryptDecryptMechanisms.put(new Long(4241L), "CKM_BLOWFISH_CBC");
         fullEncryptDecryptMechanisms.put(new Long(4243L), "CKM_TWOFISH_CBC");
         fullEncryptDecryptMechanisms.put(new Long(833L), "CKM_IDEA_ECB");
         fullEncryptDecryptMechanisms.put(new Long(834L), "CKM_IDEA_CBC");
         fullEncryptDecryptMechanisms.put(new Long(837L), "CKM_IDEA_CBC_PAD");
         fullEncryptDecryptMechanisms.put(new Long(4097L), "CKM_SKIPJACK_ECB64");
         fullEncryptDecryptMechanisms.put(new Long(4098L), "CKM_SKIPJACK_CBC64");
         fullEncryptDecryptMechanisms.put(new Long(4099L), "CKM_SKIPJACK_OFB64");
         fullEncryptDecryptMechanisms.put(new Long(4100L), "CKM_SKIPJACK_CFB64");
         fullEncryptDecryptMechanisms.put(new Long(4101L), "CKM_SKIPJACK_CFB32");
         fullEncryptDecryptMechanisms.put(new Long(4102L), "CKM_SKIPJACK_CFB16");
         fullEncryptDecryptMechanisms.put(new Long(4103L), "CKM_SKIPJACK_CFB8");
         fullEncryptDecryptMechanisms.put(new Long(4145L), "CKM_BATON_ECB128");
         fullEncryptDecryptMechanisms.put(new Long(4146L), "CKM_BATON_ECB96");
         fullEncryptDecryptMechanisms.put(new Long(4147L), "CKM_BATON_CBC128");
         fullEncryptDecryptMechanisms.put(new Long(4148L), "CKM_BATON_COUNTER");
         fullEncryptDecryptMechanisms.put(new Long(4149L), "CKM_BATON_SHUFFLE");
         fullEncryptDecryptMechanisms.put(new Long(4193L), "CKM_JUNIPER_ECB128");
         fullEncryptDecryptMechanisms.put(new Long(4194L), "CKM_JUNIPER_CBC128");
         fullEncryptDecryptMechanisms.put(new Long(4195L), "CKM_JUNIPER_COUNTER");
         fullEncryptDecryptMechanisms.put(new Long(4196L), "CKM_JUNIPER_SHUFFLE");
         fullEncryptDecryptMechanisms_ = fullEncryptDecryptMechanisms;
      }

      return fullEncryptDecryptMechanisms_.containsKey(new Long(mechanismCode));
   }

   public static boolean isSingleOperationEncryptDecryptMechanism(long mechanismCode) {
      if (singleOperationEncryptDecryptMechanisms_ == null) {
         Hashtable singleOperationEncryptDecryptMechanisms = new Hashtable();
         singleOperationEncryptDecryptMechanisms.put(new Long(1L), "CKM_RSA_PKCS");
         singleOperationEncryptDecryptMechanisms.put(new Long(9L), "CKM_RSA_PKCS_OAEP");
         singleOperationEncryptDecryptMechanisms.put(new Long(3L), "CKM_RSA_X_509");
         singleOperationEncryptDecryptMechanisms_ = singleOperationEncryptDecryptMechanisms;
      }

      return singleOperationEncryptDecryptMechanisms_.containsKey(new Long(mechanismCode));
   }

   public static boolean isFullSignVerifyMechanism(long mechanismCode) {
      if (fullSignVerifyMechanisms_ == null) {
         Hashtable fullSignVerifyMechanisms = new Hashtable();
         fullSignVerifyMechanisms.put(new Long(4L), "CKM_MD2_RSA_PKCS");
         fullSignVerifyMechanisms.put(new Long(5L), "CKM_MD5_RSA_PKCS");
         fullSignVerifyMechanisms.put(new Long(6L), "CKM_SHA1_RSA_PKCS");
         fullSignVerifyMechanisms.put(new Long(7L), "CKM_RIPEMD128_RSA_PKCS");
         fullSignVerifyMechanisms.put(new Long(8L), "CKM_RIPEMD160_RSA_PKCS");
         fullSignVerifyMechanisms.put(new Long(14L), "CKM_SHA1_RSA_PKCS_PSS");
         fullSignVerifyMechanisms.put(new Long(12L), "CKM_SHA1_RSA_X9_31");
         fullSignVerifyMechanisms.put(new Long(18L), "CKM_DSA_SHA1");
         fullSignVerifyMechanisms.put(new Long(64L), "CKM_SHA256_RSA_PKCS");
         fullSignVerifyMechanisms.put(new Long(65L), "CKM_SHA384_RSA_PKCS");
         fullSignVerifyMechanisms.put(new Long(66L), "CKM_SHA512_RSA_PKCS");
         fullSignVerifyMechanisms.put(new Long(67L), "CKM_SHA256_RSA_PKCS_PSS");
         fullSignVerifyMechanisms.put(new Long(68L), "CKM_SHA384_RSA_PKCS_PSS");
         fullSignVerifyMechanisms.put(new Long(69L), "CKM_SHA512_RSA_PKCS_PSS");
         fullSignVerifyMechanisms.put(new Long(259L), "CKM_RC2_MAC");
         fullSignVerifyMechanisms.put(new Long(260L), "CKM_RC2_MAC_GENERAL");
         fullSignVerifyMechanisms.put(new Long(291L), "CKM_DES_MAC");
         fullSignVerifyMechanisms.put(new Long(292L), "CKM_DES_MAC_GENERAL");
         fullSignVerifyMechanisms.put(new Long(308L), "CKM_DES3_MAC");
         fullSignVerifyMechanisms.put(new Long(309L), "CKM_DES3_MAC_GENERAL");
         fullSignVerifyMechanisms.put(new Long(323L), "CKM_CDMF_MAC");
         fullSignVerifyMechanisms.put(new Long(324L), "CKM_CDMF_MAC_GENERAL");
         fullSignVerifyMechanisms.put(new Long(513L), "CKM_MD2_HMAC");
         fullSignVerifyMechanisms.put(new Long(514L), "CKM_MD2_HMAC_GENERAL");
         fullSignVerifyMechanisms.put(new Long(529L), "CKM_MD5_HMAC");
         fullSignVerifyMechanisms.put(new Long(530L), "CKM_MD5_HMAC_GENERAL");
         fullSignVerifyMechanisms.put(new Long(545L), "CKM_SHA_1_HMAC");
         fullSignVerifyMechanisms.put(new Long(546L), "CKM_SHA_1_HMAC_GENERAL");
         fullSignVerifyMechanisms.put(new Long(561L), "CKM_RIPEMD128_HMAC");
         fullSignVerifyMechanisms.put(new Long(562L), "CKM_RIPEMD128_HMAC_GENERAL");
         fullSignVerifyMechanisms.put(new Long(577L), "CKM_RIPEMD160_HMAC");
         fullSignVerifyMechanisms.put(new Long(578L), "CKM_RIPEMD160_HMAC_GENERAL");
         fullSignVerifyMechanisms.put(new Long(593L), "CKM_SHA256_HMAC");
         fullSignVerifyMechanisms.put(new Long(594L), "CKM_SHA256_HMAC_GENERAL");
         fullSignVerifyMechanisms.put(new Long(609L), "CKM_SHA384_HMAC");
         fullSignVerifyMechanisms.put(new Long(610L), "CKM_SHA384_HMAC_GENERAL");
         fullSignVerifyMechanisms.put(new Long(625L), "CKM_SHA512_HMAC");
         fullSignVerifyMechanisms.put(new Long(626L), "CKM_SHA512_HMAC_GENERAL");
         fullSignVerifyMechanisms.put(new Long(771L), "CKM_CAST_MAC");
         fullSignVerifyMechanisms.put(new Long(772L), "CKM_CAST_MAC_GENERAL");
         fullSignVerifyMechanisms.put(new Long(787L), "CKM_CAST3_MAC");
         fullSignVerifyMechanisms.put(new Long(788L), "CKM_CAST3_MAC_GENERAL");
         fullSignVerifyMechanisms.put(new Long(803L), "CKM_CAST5_MAC");
         fullSignVerifyMechanisms.put(new Long(803L), "CKM_CAST128_MAC");
         fullSignVerifyMechanisms.put(new Long(804L), "CKM_CAST5_MAC_GENERAL");
         fullSignVerifyMechanisms.put(new Long(804L), "CKM_CAST128_MAC_GENERAL");
         fullSignVerifyMechanisms.put(new Long(819L), "CKM_RC5_MAC");
         fullSignVerifyMechanisms.put(new Long(820L), "CKM_RC5_MAC_GENERAL");
         fullSignVerifyMechanisms.put(new Long(4227L), "CKM_AES_MAC");
         fullSignVerifyMechanisms.put(new Long(4228L), "CKM_AES_MAC_GENERAL");
         fullSignVerifyMechanisms.put(new Long(835L), "CKM_IDEA_MAC");
         fullSignVerifyMechanisms.put(new Long(836L), "CKM_IDEA_MAC_GENERAL");
         fullSignVerifyMechanisms.put(new Long(896L), "CKM_SSL3_MD5_MAC");
         fullSignVerifyMechanisms.put(new Long(897L), "CKM_SSL3_SHA1_MAC");
         fullSignVerifyMechanisms.put(new Long(4162L), "CKM_ECDSA_SHA1");
         fullSignVerifyMechanisms_ = fullSignVerifyMechanisms;
      }

      return fullSignVerifyMechanisms_.containsKey(new Long(mechanismCode));
   }

   public static boolean isSingleOperationSignVerifyMechanism(long mechanismCode) {
      if (singleOperationSignVerifyMechanisms_ == null) {
         Hashtable singleOperationSignVerifyMechanisms = new Hashtable();
         singleOperationSignVerifyMechanisms.put(new Long(1L), "CKM_RSA_PKCS");
         singleOperationSignVerifyMechanisms.put(new Long(13L), "CKM_RSA_PKCS_PSS");
         singleOperationSignVerifyMechanisms.put(new Long(2L), "CKM_RSA_9796");
         singleOperationSignVerifyMechanisms.put(new Long(3L), "CKM_RSA_X_509");
         singleOperationSignVerifyMechanisms.put(new Long(11L), "CKM_RSA_X9_31");
         singleOperationSignVerifyMechanisms.put(new Long(17L), "CKM_DSA");
         singleOperationSignVerifyMechanisms.put(new Long(4128L), "CKM_FORTEZZA_TIMESTAMP");
         singleOperationSignVerifyMechanisms.put(new Long(4161L), "CKM_ECDSA");
         singleOperationSignVerifyMechanisms_ = singleOperationSignVerifyMechanisms;
      }

      return singleOperationSignVerifyMechanisms_.containsKey(new Long(mechanismCode));
   }

   public static boolean isSignVerifyRecoverMechanism(long mechanismCode) {
      if (signVerifyRecoverMechanisms_ == null) {
         Hashtable signVerifyRecoverMechanisms = new Hashtable();
         signVerifyRecoverMechanisms.put(new Long(1L), "CKM_RSA_PKCS");
         signVerifyRecoverMechanisms.put(new Long(2L), "CKM_RSA_9796");
         signVerifyRecoverMechanisms.put(new Long(3L), "CKM_RSA_X_509");
         signVerifyRecoverMechanisms_ = signVerifyRecoverMechanisms;
      }

      return signVerifyRecoverMechanisms_.containsKey(new Long(mechanismCode));
   }

   public static boolean isDigestMechanism(long mechanismCode) {
      if (digestMechanisms_ == null) {
         Hashtable digestMechanisms = new Hashtable();
         digestMechanisms.put(new Long(512L), "CKM_MD2");
         digestMechanisms.put(new Long(528L), "CKM_MD5");
         digestMechanisms.put(new Long(544L), "CKM_SHA_1");
         digestMechanisms.put(new Long(560L), "CKM_RIPEMD128");
         digestMechanisms.put(new Long(576L), "CKM_RIPEMD160");
         digestMechanisms.put(new Long(592L), "CKM_SHA256");
         digestMechanisms.put(new Long(608L), "CKM_SHA384");
         digestMechanisms.put(new Long(624L), "CKM_SHA512");
         digestMechanisms.put(new Long(4208L), "CKM_FASTHASH");
         digestMechanisms_ = digestMechanisms;
      }

      return digestMechanisms_.containsKey(new Long(mechanismCode));
   }

   public static boolean isKeyGenerationMechanism(long mechanismCode) {
      if (keyGenerationMechanisms_ == null) {
         Hashtable keyGenerationMechanisms = new Hashtable();
         keyGenerationMechanisms.put(new Long(8192L), "CKM_DSA_PARAMETER_GEN");
         keyGenerationMechanisms.put(new Long(8193L), "CKM_DH_PKCS_PARAMETER_GEN");
         keyGenerationMechanisms.put(new Long(8194L), "CKM_X9_42_DH_PARAMETER_GEN");
         keyGenerationMechanisms.put(new Long(256L), "CKM_RC2_KEY_GEN");
         keyGenerationMechanisms.put(new Long(272L), "CKM_RC4_KEY_GEN");
         keyGenerationMechanisms.put(new Long(288L), "CKM_DES_KEY_GEN");
         keyGenerationMechanisms.put(new Long(304L), "CKM_DES2_KEY_GEN");
         keyGenerationMechanisms.put(new Long(305L), "CKM_DES3_KEY_GEN");
         keyGenerationMechanisms.put(new Long(320L), "CKM_CDMF_KEY_GEN");
         keyGenerationMechanisms.put(new Long(768L), "CKM_CAST_KEY_GEN");
         keyGenerationMechanisms.put(new Long(784L), "CKM_CAST3_KEY_GEN");
         keyGenerationMechanisms.put(new Long(800L), "CKM_CAST5_KEY_GEN");
         keyGenerationMechanisms.put(new Long(800L), "CKM_CAST128_KEY_GEN");
         keyGenerationMechanisms.put(new Long(816L), "CKM_RC5_KEY_GEN");
         keyGenerationMechanisms.put(new Long(4224L), "CKM_AES_KEY_GEN");
         keyGenerationMechanisms.put(new Long(4240L), "CKM_BLOWFISH_KEY_GEN");
         keyGenerationMechanisms.put(new Long(4242L), "CKM_TWOFISH_KEY_GEN");
         keyGenerationMechanisms.put(new Long(832L), "CKM_IDEA_KEY_GEN");
         keyGenerationMechanisms.put(new Long(848L), "CKM_GENERIC_SECRET_KEY_GEN");
         keyGenerationMechanisms.put(new Long(880L), "CKM_SSL3_PRE_MASTER_KEY_GEN");
         keyGenerationMechanisms.put(new Long(884L), "CKM_TLS_PRE_MASTER_KEY_GEN");
         keyGenerationMechanisms.put(new Long(928L), "CKM_PBE_MD2_DES_CBC");
         keyGenerationMechanisms.put(new Long(929L), "CKM_PBE_MD5_DES_CBC");
         keyGenerationMechanisms.put(new Long(930L), "CKM_PBE_MD5_CAST_CBC");
         keyGenerationMechanisms.put(new Long(931L), "CKM_PBE_MD5_CAST3_CBC");
         keyGenerationMechanisms.put(new Long(932L), "CKM_PBE_MD5_CAST5_CBC");
         keyGenerationMechanisms.put(new Long(932L), "CKM_PBE_MD5_CAST128_CBC");
         keyGenerationMechanisms.put(new Long(933L), "CKM_PBE_SHA1_CAST5_CBC");
         keyGenerationMechanisms.put(new Long(933L), "CKM_PBE_SHA1_CAST128_CBC");
         keyGenerationMechanisms.put(new Long(934L), "CKM_PBE_SHA1_RC4_128");
         keyGenerationMechanisms.put(new Long(935L), "CKM_PBE_SHA1_RC4_40");
         keyGenerationMechanisms.put(new Long(936L), "CKM_PBE_SHA1_DES3_EDE_CBC");
         keyGenerationMechanisms.put(new Long(937L), "CKM_PBE_SHA1_DES2_EDE_CBC");
         keyGenerationMechanisms.put(new Long(938L), "CKM_PBE_SHA1_RC2_128_CBC");
         keyGenerationMechanisms.put(new Long(939L), "CKM_PBE_SHA1_RC2_40_CBC");
         keyGenerationMechanisms.put(new Long(944L), "CKM_PKCS5_PBKD2");
         keyGenerationMechanisms.put(new Long(960L), "CKM_PBA_SHA1_WITH_SHA1_HMAC");
         keyGenerationMechanisms.put(new Long(976L), "CKM_WTLS_PRE_MASTER_KEY_GEN");
         keyGenerationMechanisms.put(new Long(4096L), "CKM_SKIPJACK_KEY_GEN");
         keyGenerationMechanisms.put(new Long(4144L), "CKM_BATON_KEY_GEN");
         keyGenerationMechanisms.put(new Long(4192L), "CKM_JUNIPER_KEY_GEN");
         keyGenerationMechanisms_ = keyGenerationMechanisms;
      }

      return keyGenerationMechanisms_.containsKey(new Long(mechanismCode));
   }

   public static boolean isKeyPairGenerationMechanism(long mechanismCode) {
      if (keyPairGenerationMechanisms_ == null) {
         Hashtable keyPairGenerationMechanisms = new Hashtable();
         keyPairGenerationMechanisms.put(new Long(0L), "CKM_RSA_PKCS_KEY_PAIR_GEN");
         keyPairGenerationMechanisms.put(new Long(10L), "CKM_RSA_X9_31_KEY_PAIR_GEN");
         keyPairGenerationMechanisms.put(new Long(16L), "CKM_DSA_KEY_PAIR_GEN");
         keyPairGenerationMechanisms.put(new Long(32L), "CKM_DH_PKCS_KEY_PAIR_GEN");
         keyPairGenerationMechanisms.put(new Long(4112L), "CKM_KEA_KEY_PAIR_GEN");
         keyPairGenerationMechanisms.put(new Long(4160L), "CKM_ECDSA_KEY_PAIR_GEN");
         keyPairGenerationMechanisms.put(new Long(4160L), "CKM_EC_KEY_PAIR_GEN");
         keyPairGenerationMechanisms.put(new Long(32L), "CKM_DH_PKCS_KEY_PAIR_GEN");
         keyPairGenerationMechanisms.put(new Long(48L), "CKM_X9_42_DH_KEY_PAIR_GEN");
         keyPairGenerationMechanisms_ = keyPairGenerationMechanisms;
      }

      return keyPairGenerationMechanisms_.containsKey(new Long(mechanismCode));
   }

   public static boolean isWrapUnwrapMechanism(long mechanismCode) {
      if (wrapUnwrapMechanisms_ == null) {
         Hashtable wrapUnwrapMechanisms = new Hashtable();
         wrapUnwrapMechanisms.put(new Long(1L), "CKM_RSA_PKCS");
         wrapUnwrapMechanisms.put(new Long(3L), "CKM_RSA_X_509");
         wrapUnwrapMechanisms.put(new Long(9L), "CKM_RSA_PKCS_OAEP");
         wrapUnwrapMechanisms.put(new Long(257L), "CKM_RC2_ECB");
         wrapUnwrapMechanisms.put(new Long(258L), "CKM_RC2_CBC");
         wrapUnwrapMechanisms.put(new Long(261L), "CKM_RC2_CBC_PAD");
         wrapUnwrapMechanisms.put(new Long(289L), "CKM_DES_ECB");
         wrapUnwrapMechanisms.put(new Long(290L), "CKM_DES_CBC");
         wrapUnwrapMechanisms.put(new Long(293L), "CKM_DES_CBC_PAD");
         wrapUnwrapMechanisms.put(new Long(306L), "CKM_DES3_ECB");
         wrapUnwrapMechanisms.put(new Long(307L), "CKM_DES3_CBC");
         wrapUnwrapMechanisms.put(new Long(310L), "CKM_DES3_CBC_PAD");
         wrapUnwrapMechanisms.put(new Long(321L), "CKM_CDMF_ECB");
         wrapUnwrapMechanisms.put(new Long(322L), "CKM_CDMF_CBC");
         wrapUnwrapMechanisms.put(new Long(325L), "CKM_CDMF_CBC_PAD");
         wrapUnwrapMechanisms.put(new Long(769L), "CKM_CAST_ECB");
         wrapUnwrapMechanisms.put(new Long(770L), "CKM_CAST_CBC");
         wrapUnwrapMechanisms.put(new Long(773L), "CKM_CAST_CBC_PAD");
         wrapUnwrapMechanisms.put(new Long(785L), "CKM_CAST3_ECB");
         wrapUnwrapMechanisms.put(new Long(786L), "CKM_CAST3_CBC");
         wrapUnwrapMechanisms.put(new Long(789L), "CKM_CAST3_CBC_PAD");
         wrapUnwrapMechanisms.put(new Long(801L), "CKM_CAST5_ECB");
         wrapUnwrapMechanisms.put(new Long(801L), "CKM_CAST128_ECB");
         wrapUnwrapMechanisms.put(new Long(802L), "CKM_CAST5_CBC");
         wrapUnwrapMechanisms.put(new Long(802L), "CKM_CAST128_CBC");
         wrapUnwrapMechanisms.put(new Long(805L), "CKM_CAST5_CBC_PAD");
         wrapUnwrapMechanisms.put(new Long(805L), "CKM_CAST128_CBC_PAD");
         wrapUnwrapMechanisms.put(new Long(817L), "CKM_RC5_ECB");
         wrapUnwrapMechanisms.put(new Long(818L), "CKM_RC5_CBC");
         wrapUnwrapMechanisms.put(new Long(821L), "CKM_RC5_CBC_PAD");
         wrapUnwrapMechanisms.put(new Long(833L), "CKM_IDEA_ECB");
         wrapUnwrapMechanisms.put(new Long(834L), "CKM_IDEA_CBC");
         wrapUnwrapMechanisms.put(new Long(837L), "CKM_IDEA_CBC_PAD");
         wrapUnwrapMechanisms.put(new Long(1024L), "CKM_KEY_WRAP_LYNKS");
         wrapUnwrapMechanisms.put(new Long(1025L), "CKM_KEY_WRAP_SET_OAEP");
         wrapUnwrapMechanisms.put(new Long(4104L), "CKM_SKIPJACK_WRAP");
         wrapUnwrapMechanisms.put(new Long(4105L), "CKM_SKIPJACK_PRIVATE_WRAP");
         wrapUnwrapMechanisms.put(new Long(4106L), "CKM_SKIPJACK_RELAYX");
         wrapUnwrapMechanisms.put(new Long(4150L), "CKM_BATON_WRAP");
         wrapUnwrapMechanisms.put(new Long(4197L), "CKM_JUNIPER_WRAP");
         wrapUnwrapMechanisms.put(new Long(4225L), "CKM_AES_ECB");
         wrapUnwrapMechanisms.put(new Long(4226L), "CKM_AES_CBC");
         wrapUnwrapMechanisms.put(new Long(4229L), "CKM_AES_CBC_PAD");
         wrapUnwrapMechanisms.put(new Long(4241L), "CKM_BLOWFISH_CBC");
         wrapUnwrapMechanisms.put(new Long(4243L), "CKM_TWOFISH_CBC");
         wrapUnwrapMechanisms_ = wrapUnwrapMechanisms;
      }

      return wrapUnwrapMechanisms_.containsKey(new Long(mechanismCode));
   }

   public static boolean isKeyDerivationMechanism(long mechanismCode) {
      if (keyDerivationMechanisms_ == null) {
         Hashtable keyDerivationMechanisms = new Hashtable();
         keyDerivationMechanisms.put(new Long(33L), "CKM_DH_PKCS_DERIVE");
         keyDerivationMechanisms.put(new Long(864L), "CKM_CONCATENATE_BASE_AND_KEY");
         keyDerivationMechanisms.put(new Long(866L), "CKM_CONCATENATE_BASE_AND_DATA");
         keyDerivationMechanisms.put(new Long(867L), "CKM_CONCATENATE_DATA_AND_BASE");
         keyDerivationMechanisms.put(new Long(868L), "CKM_XOR_BASE_AND_DATA");
         keyDerivationMechanisms.put(new Long(869L), "CKM_EXTRACT_KEY_FROM_KEY");
         keyDerivationMechanisms.put(new Long(881L), "CKM_SSL3_MASTER_KEY_DERIVE");
         keyDerivationMechanisms.put(new Long(883L), "CKM_SSL3_MASTER_KEY_DERIVE_DH");
         keyDerivationMechanisms.put(new Long(882L), "CKM_SSL3_KEY_AND_MAC_DERIVE");
         keyDerivationMechanisms.put(new Long(885L), "CKM_TLS_MASTER_KEY_DERIVE");
         keyDerivationMechanisms.put(new Long(887L), "CKM_TLS_MASTER_KEY_DERIVE_DH");
         keyDerivationMechanisms.put(new Long(886L), "CKM_TLS_KEY_AND_MAC_DERIVE");
         keyDerivationMechanisms.put(new Long(888L), "CKM_TLS_PRF");
         keyDerivationMechanisms.put(new Long(912L), "CKM_MD5_KEY_DERIVATION");
         keyDerivationMechanisms.put(new Long(913L), "CKM_MD2_KEY_DERIVATION");
         keyDerivationMechanisms.put(new Long(914L), "CKM_SHA1_KEY_DERIVATION");
         keyDerivationMechanisms.put(new Long(915L), "CKM_SHA256_KEY_DERIVATION");
         keyDerivationMechanisms.put(new Long(916L), "CKM_SHA384_KEY_DERIVATION");
         keyDerivationMechanisms.put(new Long(917L), "CKM_SHA512_KEY_DERIVATION");
         keyDerivationMechanisms.put(new Long(977L), "CKM_TLS_MASTER_KEY_DERIVE");
         keyDerivationMechanisms.put(new Long(978L), "CKM_WTLS_MASTER_KEY_DERIVE_DH_ECC");
         keyDerivationMechanisms.put(new Long(980L), "CKM_WTLS_SERVER_KEY_AND_MAC_DERIVE");
         keyDerivationMechanisms.put(new Long(981L), "CKM_WTLS_CLIENT_KEY_AND_MAC_DERIVE");
         keyDerivationMechanisms.put(new Long(979L), "CKM_WTLS_PRF");
         keyDerivationMechanisms.put(new Long(4113L), "CKM_KEA_KEY_DERIVE");
         keyDerivationMechanisms.put(new Long(4352L), "CKM_DES_ECB_ENCRYPT_DATA");
         keyDerivationMechanisms.put(new Long(4353L), "CKM_DES_CBC_ENCRYPT_DATA");
         keyDerivationMechanisms.put(new Long(4354L), "CKM_DES3_ECB_ENCRYPT_DATA");
         keyDerivationMechanisms.put(new Long(4355L), "CKM_DES3_CBC_ENCRYPT_DATA");
         keyDerivationMechanisms.put(new Long(4356L), "CKM_AES_ECB_ENCRYPT_DATA");
         keyDerivationMechanisms.put(new Long(4357L), "CKM_AES_CBC_ENCRYPT_DATA");
         keyDerivationMechanisms_ = keyDerivationMechanisms;
      }

      return keyDerivationMechanisms_.containsKey(new Long(mechanismCode));
   }
}
