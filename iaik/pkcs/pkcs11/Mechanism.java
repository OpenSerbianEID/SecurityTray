package iaik.pkcs.pkcs11;

import iaik.pkcs.pkcs11.parameters.Parameters;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.Functions;

public class Mechanism implements Cloneable {
   /** @deprecated */
   public static final Mechanism RSA_PKCS_KEY_PAIR_GEN = new Mechanism(0L);
   /** @deprecated */
   public static final Mechanism RSA_PKCS = new Mechanism(1L);
   /** @deprecated */
   public static final Mechanism RSA_9796 = new Mechanism(2L);
   /** @deprecated */
   public static final Mechanism RSA_X_509 = new Mechanism(3L);
   /** @deprecated */
   public static final Mechanism MD2_RSA_PKCS = new Mechanism(4L);
   /** @deprecated */
   public static final Mechanism MD5_RSA_PKCS = new Mechanism(5L);
   /** @deprecated */
   public static final Mechanism SHA1_RSA_PKCS = new Mechanism(6L);
   /** @deprecated */
   public static final Mechanism RIPEMD128_RSA_PKCS = new Mechanism(7L);
   /** @deprecated */
   public static final Mechanism RIPEMD160_RSA_PKCS = new Mechanism(8L);
   /** @deprecated */
   public static final Mechanism SHA256_RSA_PKCS = new Mechanism(64L);
   /** @deprecated */
   public static final Mechanism SHA384_RSA_PKCS = new Mechanism(65L);
   /** @deprecated */
   public static final Mechanism SHA512_RSA_PKCS = new Mechanism(66L);
   /** @deprecated */
   public static final Mechanism RSA_PKCS_OAEP = new Mechanism(9L);
   /** @deprecated */
   public static final Mechanism RSA_X9_31_KEY_PAIR_GEN = new Mechanism(10L);
   /** @deprecated */
   public static final Mechanism RSA_X9_31 = new Mechanism(11L);
   /** @deprecated */
   public static final Mechanism SHA1_RSA_X9_31 = new Mechanism(12L);
   /** @deprecated */
   public static final Mechanism RSA_PKCS_PSS = new Mechanism(13L);
   /** @deprecated */
   public static final Mechanism SHA1_RSA_PKCS_PSS = new Mechanism(14L);
   /** @deprecated */
   public static final Mechanism SHA256_RSA_PKCS_PSS = new Mechanism(67L);
   /** @deprecated */
   public static final Mechanism SHA384_RSA_PKCS_PSS = new Mechanism(68L);
   /** @deprecated */
   public static final Mechanism SHA512_RSA_PKCS_PSS = new Mechanism(69L);
   /** @deprecated */
   public static final Mechanism DSA_KEY_PAIR_GEN = new Mechanism(16L);
   /** @deprecated */
   public static final Mechanism DSA = new Mechanism(17L);
   /** @deprecated */
   public static final Mechanism DSA_SHA1 = new Mechanism(18L);
   /** @deprecated */
   public static final Mechanism DH_PKCS_KEY_PAIR_GEN = new Mechanism(32L);
   /** @deprecated */
   public static final Mechanism DH_PKCS_DERIVE = new Mechanism(33L);
   /** @deprecated */
   public static final Mechanism X9_42_DH_KEY_PAIR_GEN = new Mechanism(48L);
   /** @deprecated */
   public static final Mechanism X9_42_DH_DERIVE = new Mechanism(49L);
   /** @deprecated */
   public static final Mechanism X9_42_DH_HYBRID_DERIVE = new Mechanism(50L);
   /** @deprecated */
   public static final Mechanism X9_42_MQV_DERIVE = new Mechanism(51L);
   /** @deprecated */
   public static final Mechanism RC2_KEY_GEN = new Mechanism(256L);
   /** @deprecated */
   public static final Mechanism RC2_ECB = new Mechanism(257L);
   /** @deprecated */
   public static final Mechanism RC2_CBC = new Mechanism(258L);
   /** @deprecated */
   public static final Mechanism RC2_MAC = new Mechanism(259L);
   /** @deprecated */
   public static final Mechanism RC2_MAC_GENERAL = new Mechanism(260L);
   /** @deprecated */
   public static final Mechanism RC2_CBC_PAD = new Mechanism(261L);
   /** @deprecated */
   public static final Mechanism RC4_KEY_GEN = new Mechanism(272L);
   /** @deprecated */
   public static final Mechanism RC4 = new Mechanism(273L);
   /** @deprecated */
   public static final Mechanism DES_KEY_GEN = new Mechanism(288L);
   /** @deprecated */
   public static final Mechanism DES_ECB = new Mechanism(289L);
   /** @deprecated */
   public static final Mechanism DES_CBC = new Mechanism(290L);
   /** @deprecated */
   public static final Mechanism DES_MAC = new Mechanism(291L);
   /** @deprecated */
   public static final Mechanism DES_MAC_GENERAL = new Mechanism(292L);
   /** @deprecated */
   public static final Mechanism DES_CBC_PAD = new Mechanism(293L);
   /** @deprecated */
   public static final Mechanism DES_OFB64 = new Mechanism(336L);
   /** @deprecated */
   public static final Mechanism DES_OFB8 = new Mechanism(337L);
   /** @deprecated */
   public static final Mechanism DES_CFB64 = new Mechanism(338L);
   /** @deprecated */
   public static final Mechanism DES_CFB8 = new Mechanism(339L);
   /** @deprecated */
   public static final Mechanism DES2_KEY_GEN = new Mechanism(304L);
   /** @deprecated */
   public static final Mechanism DES3_KEY_GEN = new Mechanism(305L);
   /** @deprecated */
   public static final Mechanism DES3_ECB = new Mechanism(306L);
   /** @deprecated */
   public static final Mechanism DES3_CBC = new Mechanism(307L);
   /** @deprecated */
   public static final Mechanism DES3_MAC = new Mechanism(308L);
   /** @deprecated */
   public static final Mechanism DES3_MAC_GENERAL = new Mechanism(309L);
   /** @deprecated */
   public static final Mechanism DES3_CBC_PAD = new Mechanism(310L);
   /** @deprecated */
   public static final Mechanism CDMF_KEY_GEN = new Mechanism(320L);
   /** @deprecated */
   public static final Mechanism CDMF_ECB = new Mechanism(321L);
   /** @deprecated */
   public static final Mechanism CDMF_CBC = new Mechanism(322L);
   /** @deprecated */
   public static final Mechanism CDMF_MAC = new Mechanism(323L);
   /** @deprecated */
   public static final Mechanism CDMF_MAC_GENERAL = new Mechanism(324L);
   /** @deprecated */
   public static final Mechanism CDMF_CBC_PAD = new Mechanism(325L);
   /** @deprecated */
   public static final Mechanism MD2 = new Mechanism(512L);
   /** @deprecated */
   public static final Mechanism MD2_HMAC = new Mechanism(513L);
   /** @deprecated */
   public static final Mechanism MD2_HMAC_GENERAL = new Mechanism(514L);
   /** @deprecated */
   public static final Mechanism MD5 = new Mechanism(528L);
   /** @deprecated */
   public static final Mechanism MD5_HMAC = new Mechanism(529L);
   /** @deprecated */
   public static final Mechanism MD5_HMAC_GENERAL = new Mechanism(530L);
   /** @deprecated */
   public static final Mechanism SHA_1 = new Mechanism(544L);
   /** @deprecated */
   public static final Mechanism SHA_1_HMAC = new Mechanism(545L);
   /** @deprecated */
   public static final Mechanism SHA_1_HMAC_GENERAL = new Mechanism(546L);
   /** @deprecated */
   public static final Mechanism SHA256 = new Mechanism(592L);
   /** @deprecated */
   public static final Mechanism SHA256_HMAC = new Mechanism(593L);
   /** @deprecated */
   public static final Mechanism SHA256_HMAC_GENERAL = new Mechanism(594L);
   /** @deprecated */
   public static final Mechanism SHA384 = new Mechanism(608L);
   /** @deprecated */
   public static final Mechanism SHA384_HMAC = new Mechanism(609L);
   /** @deprecated */
   public static final Mechanism SHA384_HMAC_GENERAL = new Mechanism(610L);
   /** @deprecated */
   public static final Mechanism SHA512 = new Mechanism(624L);
   /** @deprecated */
   public static final Mechanism SHA512_HMAC = new Mechanism(625L);
   /** @deprecated */
   public static final Mechanism SHA512_HMAC_GENERAL = new Mechanism(626L);
   /** @deprecated */
   public static final Mechanism RIPEMD128 = new Mechanism(560L);
   /** @deprecated */
   public static final Mechanism RIPEMD128_HMAC = new Mechanism(561L);
   /** @deprecated */
   public static final Mechanism RIPEMD128_HMAC_GENERAL = new Mechanism(562L);
   /** @deprecated */
   public static final Mechanism RIPEMD160 = new Mechanism(576L);
   /** @deprecated */
   public static final Mechanism RIPEMD160_HMAC = new Mechanism(577L);
   /** @deprecated */
   public static final Mechanism RIPEMD160_HMAC_GENERAL = new Mechanism(578L);
   /** @deprecated */
   public static final Mechanism CAST_KEY_GEN = new Mechanism(768L);
   /** @deprecated */
   public static final Mechanism CAST_ECB = new Mechanism(769L);
   /** @deprecated */
   public static final Mechanism CAST_CBC = new Mechanism(770L);
   /** @deprecated */
   public static final Mechanism CAST_MAC = new Mechanism(771L);
   /** @deprecated */
   public static final Mechanism CAST_MAC_GENERAL = new Mechanism(772L);
   /** @deprecated */
   public static final Mechanism CAST_CBC_PAD = new Mechanism(773L);
   /** @deprecated */
   public static final Mechanism CAST3_KEY_GEN = new Mechanism(784L);
   /** @deprecated */
   public static final Mechanism CAST3_ECB = new Mechanism(785L);
   /** @deprecated */
   public static final Mechanism CAST3_CBC = new Mechanism(786L);
   /** @deprecated */
   public static final Mechanism CAST3_MAC = new Mechanism(787L);
   /** @deprecated */
   public static final Mechanism CAST3_MAC_GENERAL = new Mechanism(788L);
   /** @deprecated */
   public static final Mechanism CAST3_CBC_PAD = new Mechanism(789L);
   /** @deprecated */
   public static final Mechanism CAST5_KEY_GEN = new Mechanism(800L);
   /** @deprecated */
   public static final Mechanism CAST128_KEY_GEN = new Mechanism(800L);
   /** @deprecated */
   public static final Mechanism CAST5_ECB = new Mechanism(801L);
   /** @deprecated */
   public static final Mechanism CAST128_ECB = new Mechanism(801L);
   /** @deprecated */
   public static final Mechanism CAST5_CBC = new Mechanism(802L);
   /** @deprecated */
   public static final Mechanism CAST128_CBC = new Mechanism(802L);
   /** @deprecated */
   public static final Mechanism CAST5_MAC = new Mechanism(803L);
   /** @deprecated */
   public static final Mechanism CAST128_MAC = new Mechanism(803L);
   /** @deprecated */
   public static final Mechanism CAST5_MAC_GENERAL = new Mechanism(804L);
   /** @deprecated */
   public static final Mechanism CAST128_MAC_GENERAL = new Mechanism(804L);
   /** @deprecated */
   public static final Mechanism CAST5_CBC_PAD = new Mechanism(805L);
   /** @deprecated */
   public static final Mechanism CAST128_CBC_PAD = new Mechanism(805L);
   /** @deprecated */
   public static final Mechanism RC5_KEY_GEN = new Mechanism(816L);
   /** @deprecated */
   public static final Mechanism RC5_ECB = new Mechanism(817L);
   /** @deprecated */
   public static final Mechanism RC5_CBC = new Mechanism(818L);
   /** @deprecated */
   public static final Mechanism RC5_MAC = new Mechanism(819L);
   /** @deprecated */
   public static final Mechanism RC5_MAC_GENERAL = new Mechanism(820L);
   /** @deprecated */
   public static final Mechanism RC5_CBC_PAD = new Mechanism(821L);
   /** @deprecated */
   public static final Mechanism IDEA_KEY_GEN = new Mechanism(832L);
   /** @deprecated */
   public static final Mechanism IDEA_ECB = new Mechanism(833L);
   /** @deprecated */
   public static final Mechanism IDEA_CBC = new Mechanism(834L);
   /** @deprecated */
   public static final Mechanism IDEA_MAC = new Mechanism(835L);
   /** @deprecated */
   public static final Mechanism IDEA_MAC_GENERAL = new Mechanism(836L);
   /** @deprecated */
   public static final Mechanism IDEA_CBC_PAD = new Mechanism(837L);
   /** @deprecated */
   public static final Mechanism GENERIC_SECRET_KEY_GEN = new Mechanism(848L);
   /** @deprecated */
   public static final Mechanism CONCATENATE_BASE_AND_KEY = new Mechanism(864L);
   /** @deprecated */
   public static final Mechanism CONCATENATE_BASE_AND_DATA = new Mechanism(866L);
   /** @deprecated */
   public static final Mechanism CONCATENATE_DATA_AND_BASE = new Mechanism(867L);
   /** @deprecated */
   public static final Mechanism XOR_BASE_AND_DATA = new Mechanism(868L);
   /** @deprecated */
   public static final Mechanism EXTRACT_KEY_FROM_KEY = new Mechanism(869L);
   /** @deprecated */
   public static final Mechanism SSL3_PRE_MASTER_KEY_GEN = new Mechanism(880L);
   /** @deprecated */
   public static final Mechanism SSL3_MASTER_KEY_DERIVE = new Mechanism(881L);
   /** @deprecated */
   public static final Mechanism SSL3_KEY_AND_MAC_DERIVE = new Mechanism(882L);
   /** @deprecated */
   public static final Mechanism SSL3_MASTER_KEY_DERIVE_DH = new Mechanism(883L);
   /** @deprecated */
   public static final Mechanism TLS_PRE_MASTER_KEY_GEN = new Mechanism(884L);
   /** @deprecated */
   public static final Mechanism TLS_MASTER_KEY_DERIVE = new Mechanism(885L);
   /** @deprecated */
   public static final Mechanism TLS_KEY_AND_MAC_DERIVE = new Mechanism(886L);
   /** @deprecated */
   public static final Mechanism TLS_MASTER_KEY_DERIVE_DH = new Mechanism(887L);
   /** @deprecated */
   public static final Mechanism TLS_PRF = new Mechanism(888L);
   /** @deprecated */
   public static final Mechanism WTLS_PRE_MASTER_KEY_GEN = new Mechanism(976L);
   /** @deprecated */
   public static final Mechanism WTLS_MASTER_KEY_DERIVE = new Mechanism(977L);
   /** @deprecated */
   public static final Mechanism WTLS_MASTER_KEY_DERIVE_DH_ECC = new Mechanism(978L);
   /** @deprecated */
   public static final Mechanism WTLS_PRF = new Mechanism(979L);
   /** @deprecated */
   public static final Mechanism WTLS_SERVER_KEY_AND_MAC_DERIVE = new Mechanism(980L);
   /** @deprecated */
   public static final Mechanism WTLS_CLIENT_KEY_AND_MAC_DERIVE = new Mechanism(981L);
   /** @deprecated */
   public static final Mechanism SSL3_MD5_MAC = new Mechanism(896L);
   /** @deprecated */
   public static final Mechanism SSL3_SHA1_MAC = new Mechanism(897L);
   /** @deprecated */
   public static final Mechanism MD5_KEY_DERIVATION = new Mechanism(912L);
   /** @deprecated */
   public static final Mechanism MD2_KEY_DERIVATION = new Mechanism(913L);
   /** @deprecated */
   public static final Mechanism SHA1_KEY_DERIVATION = new Mechanism(914L);
   /** @deprecated */
   public static final Mechanism SHA256_KEY_DERIVATION = new Mechanism(915L);
   /** @deprecated */
   public static final Mechanism SHA384_KEY_DERIVATION = new Mechanism(916L);
   /** @deprecated */
   public static final Mechanism SHA512_KEY_DERIVATION = new Mechanism(917L);
   /** @deprecated */
   public static final Mechanism PBE_MD2_DES_CBC = new Mechanism(928L);
   /** @deprecated */
   public static final Mechanism PBE_MD5_DES_CBC = new Mechanism(929L);
   /** @deprecated */
   public static final Mechanism PBE_MD5_CAST_CBC = new Mechanism(930L);
   /** @deprecated */
   public static final Mechanism PBE_MD5_CAST3_CBC = new Mechanism(931L);
   /** @deprecated */
   public static final Mechanism PBE_MD5_CAST5_CBC = new Mechanism(932L);
   /** @deprecated */
   public static final Mechanism PBE_MD5_CAST128_CBC = new Mechanism(932L);
   /** @deprecated */
   public static final Mechanism PBE_SHA1_CAST5_CBC = new Mechanism(933L);
   /** @deprecated */
   public static final Mechanism PBE_SHA1_CAST128_CBC = new Mechanism(933L);
   /** @deprecated */
   public static final Mechanism PBE_SHA1_RC4_128 = new Mechanism(934L);
   /** @deprecated */
   public static final Mechanism PBE_SHA1_RC4_40 = new Mechanism(935L);
   /** @deprecated */
   public static final Mechanism PBE_SHA1_DES3_EDE_CBC = new Mechanism(936L);
   /** @deprecated */
   public static final Mechanism PBE_SHA1_DES2_EDE_CBC = new Mechanism(937L);
   /** @deprecated */
   public static final Mechanism PBE_SHA1_RC2_128_CBC = new Mechanism(938L);
   /** @deprecated */
   public static final Mechanism PBE_SHA1_RC2_40_CBC = new Mechanism(939L);
   /** @deprecated */
   public static final Mechanism PKCS5_PBKD2 = new Mechanism(944L);
   /** @deprecated */
   public static final Mechanism PBA_SHA1_WITH_SHA1_HMAC = new Mechanism(960L);
   /** @deprecated */
   public static final Mechanism KEY_WRAP_LYNKS = new Mechanism(1024L);
   /** @deprecated */
   public static final Mechanism KEY_WRAP_SET_OAEP = new Mechanism(1025L);
   /** @deprecated */
   public static final Mechanism SKIPJACK_KEY_GEN = new Mechanism(4096L);
   /** @deprecated */
   public static final Mechanism SKIPJACK_ECB64 = new Mechanism(4097L);
   /** @deprecated */
   public static final Mechanism SKIPJACK_CBC64 = new Mechanism(4098L);
   /** @deprecated */
   public static final Mechanism SKIPJACK_OFB64 = new Mechanism(4099L);
   /** @deprecated */
   public static final Mechanism SKIPJACK_CFB64 = new Mechanism(4100L);
   /** @deprecated */
   public static final Mechanism SKIPJACK_CFB32 = new Mechanism(4101L);
   /** @deprecated */
   public static final Mechanism SKIPJACK_CFB16 = new Mechanism(4102L);
   /** @deprecated */
   public static final Mechanism SKIPJACK_CFB8 = new Mechanism(4103L);
   /** @deprecated */
   public static final Mechanism SKIPJACK_WRAP = new Mechanism(4104L);
   /** @deprecated */
   public static final Mechanism SKIPJACK_PRIVATE_WRAP = new Mechanism(4105L);
   /** @deprecated */
   public static final Mechanism SKIPJACK_RELAYX = new Mechanism(4106L);
   /** @deprecated */
   public static final Mechanism KEA_KEY_PAIR_GEN = new Mechanism(4112L);
   /** @deprecated */
   public static final Mechanism KEA_KEY_DERIVE = new Mechanism(4113L);
   /** @deprecated */
   public static final Mechanism FORTEZZA_TIMESTAMP = new Mechanism(4128L);
   /** @deprecated */
   public static final Mechanism BATON_KEY_GEN = new Mechanism(4144L);
   /** @deprecated */
   public static final Mechanism BATON_ECB128 = new Mechanism(4145L);
   /** @deprecated */
   public static final Mechanism BATON_ECB96 = new Mechanism(4146L);
   /** @deprecated */
   public static final Mechanism BATON_CBC128 = new Mechanism(4147L);
   /** @deprecated */
   public static final Mechanism BATON_COUNTER = new Mechanism(4148L);
   /** @deprecated */
   public static final Mechanism BATON_SHUFFLE = new Mechanism(4149L);
   /** @deprecated */
   public static final Mechanism BATON_WRAP = new Mechanism(4150L);
   /** @deprecated */
   public static final Mechanism ECDSA_KEY_PAIR_GEN = new Mechanism(4160L);
   /** @deprecated */
   public static final Mechanism EC_KEY_PAIR_GEN = new Mechanism(4160L);
   /** @deprecated */
   public static final Mechanism ECDSA = new Mechanism(4161L);
   /** @deprecated */
   public static final Mechanism ECDSA_SHA1 = new Mechanism(4162L);
   /** @deprecated */
   public static final Mechanism ECDH1_DERIVE = new Mechanism(4176L);
   /** @deprecated */
   public static final Mechanism ECDH1_COFACTOR_DERIVE = new Mechanism(4177L);
   /** @deprecated */
   public static final Mechanism ECMQV_DERIVE = new Mechanism(4178L);
   /** @deprecated */
   public static final Mechanism JUNIPER_KEY_GEN = new Mechanism(4192L);
   /** @deprecated */
   public static final Mechanism JUNIPER_ECB128 = new Mechanism(4193L);
   /** @deprecated */
   public static final Mechanism JUNIPER_CBC128 = new Mechanism(4194L);
   /** @deprecated */
   public static final Mechanism JUNIPER_COUNTER = new Mechanism(4195L);
   /** @deprecated */
   public static final Mechanism JUNIPER_SHUFFLE = new Mechanism(4196L);
   /** @deprecated */
   public static final Mechanism JUNIPER_WRAP = new Mechanism(4197L);
   /** @deprecated */
   public static final Mechanism FASTHASH = new Mechanism(4208L);
   /** @deprecated */
   public static final Mechanism AES_KEY_GEN = new Mechanism(4224L);
   /** @deprecated */
   public static final Mechanism AES_ECB = new Mechanism(4225L);
   /** @deprecated */
   public static final Mechanism AES_CBC = new Mechanism(4226L);
   /** @deprecated */
   public static final Mechanism AES_MAC = new Mechanism(4227L);
   /** @deprecated */
   public static final Mechanism AES_MAC_GENERAL = new Mechanism(4228L);
   /** @deprecated */
   public static final Mechanism AES_CBC_PAD = new Mechanism(4229L);
   /** @deprecated */
   public static final Mechanism BLOWFISH_KEY_GEN = new Mechanism(4240L);
   /** @deprecated */
   public static final Mechanism BLOWFISH_CBC = new Mechanism(4241L);
   /** @deprecated */
   public static final Mechanism DSA_PARAMETER_GEN = new Mechanism(8192L);
   /** @deprecated */
   public static final Mechanism DH_PKCS_PARAMETER_GEN = new Mechanism(8193L);
   /** @deprecated */
   public static final Mechanism X9_42_DH_PARAMETER_GEN = new Mechanism(8194L);
   /** @deprecated */
   public static final Mechanism DES_ECB_ENCRYPT_DATA = new Mechanism(4352L);
   /** @deprecated */
   public static final Mechanism DES_CBC_ENCRYPT_DATA = new Mechanism(4353L);
   /** @deprecated */
   public static final Mechanism DES3_ECB_ENCRYPT_DATA = new Mechanism(4354L);
   /** @deprecated */
   public static final Mechanism DES3_CBC_ENCRYPT_DATA = new Mechanism(4355L);
   /** @deprecated */
   public static final Mechanism AES_ECB_ENCRYPT_DATA = new Mechanism(4356L);
   /** @deprecated */
   public static final Mechanism AES_CBC_ENCRYPT_DATA = new Mechanism(4357L);
   /** @deprecated */
   public static final Mechanism VENDOR_DEFINED = new Mechanism(2147483648L);
   protected long pkcs11MechanismCode_;
   protected Parameters parameters_;

   public Mechanism(long pkcs11MechanismCode) {
      this.pkcs11MechanismCode_ = pkcs11MechanismCode;
   }

   public static Mechanism get(long pkcs11MechanismCode) {
      return new Mechanism(pkcs11MechanismCode);
   }

   public Object clone() {
      Mechanism clone = null;

      try {
         clone = (Mechanism)super.clone();
      } catch (CloneNotSupportedException var3) {
      }

      return clone;
   }

   public boolean equals(Object otherObject) {
      boolean euqal = false;
      if (otherObject instanceof Mechanism) {
         Mechanism other = (Mechanism)otherObject;
         euqal = this == other || this.pkcs11MechanismCode_ == other.pkcs11MechanismCode_ && (this.parameters_ == null && other.parameters_ == null || this.parameters_ != null && this.parameters_.equals(other.parameters_));
      }

      return euqal;
   }

   public int hashCode() {
      return (int)this.pkcs11MechanismCode_;
   }

   public boolean isDigestMechanism() {
      return Functions.isDigestMechanism(this.pkcs11MechanismCode_);
   }

   public boolean isFullEncryptDecryptMechanism() {
      return Functions.isFullEncryptDecryptMechanism(this.pkcs11MechanismCode_);
   }

   public boolean isFullSignVerifyMechanism() {
      return Functions.isFullSignVerifyMechanism(this.pkcs11MechanismCode_);
   }

   public boolean isKeyDerivationMechanism() {
      return Functions.isKeyDerivationMechanism(this.pkcs11MechanismCode_);
   }

   public boolean isKeyGenerationMechanism() {
      return Functions.isKeyGenerationMechanism(this.pkcs11MechanismCode_);
   }

   public boolean isKeyPairGenerationMechanism() {
      return Functions.isKeyPairGenerationMechanism(this.pkcs11MechanismCode_);
   }

   public boolean isSignVerifyRecoverMechanism() {
      return Functions.isSignVerifyRecoverMechanism(this.pkcs11MechanismCode_);
   }

   public boolean isSingleOperationEncryptDecryptMechanism() {
      return Functions.isSingleOperationEncryptDecryptMechanism(this.pkcs11MechanismCode_);
   }

   public boolean isSingleOperationSignVerifyMechanism() {
      return Functions.isSingleOperationSignVerifyMechanism(this.pkcs11MechanismCode_);
   }

   public boolean isWrapUnwrapMechanism() {
      return Functions.isWrapUnwrapMechanism(this.pkcs11MechanismCode_);
   }

   public Parameters getParameters() {
      return this.parameters_;
   }

   public void setParameters(Parameters parameters) {
      this.parameters_ = parameters;
   }

   public long getMechanismCode() {
      return this.pkcs11MechanismCode_;
   }

   public String getName() {
      return Functions.mechanismCodeToString(this.pkcs11MechanismCode_);
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(128);
      buffer.append("  ");
      buffer.append("Mechanism: ");
      buffer.append(Functions.mechanismCodeToString(this.pkcs11MechanismCode_));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Parameters: ");
      buffer.append(Constants.NEWLINE);
      buffer.append(this.parameters_);
      return buffer.toString();
   }
}
