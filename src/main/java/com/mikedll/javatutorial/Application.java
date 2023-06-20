package com.mikedll.javatutorial;

import java.util.Base64;
import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.UnmappableCharacterException;
import java.nio.charset.CharacterCodingException;
import java.nio.CharBuffer;
import java.nio.ByteBuffer;
import java.nio.charset.CodingErrorAction;
import org.apache.commons.io.FileUtils;
import org.javatuples.Pair;

public class Application {
    
    public static void main(String[] args) {
        String s = "Hello Mike";

        byte[] asBytes = s.getBytes(StandardCharsets.UTF_8);

        Pair<String,String> easyResult = decode(asBytes);
        if(easyResult.getValue1() != null) {
            System.out.println("Failed to decode: " + easyResult.getValue1());
        } else {
            System.out.println("Decoded: " + easyResult.getValue0());
        }

        byte[] jungKookBytes;
        try {
            jungKookBytes = FileUtils.readFileToByteArray(new File("target/classes/jung_kook.jpg"));
        } catch (IOException ex) {
            throw new RuntimeException("IOException when reading", ex);
        }

        Pair<String,String> jungResult = decode(jungKookBytes);
        if(jungResult.getValue1() != null) {
            System.out.println("Failed to decode: " + jungResult.getValue1());
        }
    }

    public static Pair<String,String> decode(byte[] bytes) {
        CharBuffer result = null;
        try {
            result = StandardCharsets.UTF_8.newDecoder()
                .onMalformedInput(CodingErrorAction.REPORT)
                .onUnmappableCharacter(CodingErrorAction.REPORT)
                .decode(ByteBuffer.wrap(bytes));
        } catch (MalformedInputException ex) {
            return Pair.with(null, "MalformedInputException: " + ex.getMessage());
        } catch (UnmappableCharacterException ex) {
            return Pair.with(null, "UnmappableCharacterException: " + ex.getMessage());
        } catch (CharacterCodingException ex) {
            throw new RuntimeException("CharacterCodingException while decoding byte array", ex);
        }

        return Pair.with(result.toString(), null);
    }
    
}
