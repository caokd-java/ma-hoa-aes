package com.example.quan;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;

@Service
public class MaHoaService {

    private static final String key = "aesEncryptionKey";
    private static final String initVector = "encryptionIntVec";

    public String encrypt(String chuoiMaHoa, String matKhauMaHoa) throws Exception{

        IvParameterSpec ivParameterSpec = new IvParameterSpec(initVector.getBytes("UTF-8"));


        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        KeySpec spec = new PBEKeySpec(matKhauMaHoa.toCharArray(), key.getBytes("UTF-8"), 65536, 256);

        SecretKey tmp = factory.generateSecret(spec);

        SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        byte[] encrypted = cipher.doFinal(chuoiMaHoa.getBytes());

        return Base64.encodeBase64String(encrypted);
    }

    public String decrypt(String chuoiCanGiaiMa, String matKhauMaHoa) throws Exception{

        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(matKhauMaHoa.toCharArray(), key.getBytes(), 65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);

        byte[] original = cipher.doFinal(Base64.decodeBase64(chuoiCanGiaiMa));

        return new String(original);
    }
}
