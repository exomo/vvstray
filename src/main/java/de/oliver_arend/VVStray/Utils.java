package de.oliver_arend.VVStray;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URI;
import java.net.URISyntaxException;

public class Utils {

    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, encoding);
    }

    public static void writeFile(String path, String body) throws IOException {
        byte[] encoded = body.getBytes("UTF-8");
        Files.write(Paths.get(path), encoded);
    }

    public static boolean isNumeric(String str)
    {
        try { int i = Integer.parseInt(str); }
        catch(NumberFormatException nfe) { return false; }
        return true;
    }

    public static String getResourcePath(String resource)
    {
        try {
            ClassLoader classLoader = Utils.class.getClassLoader();
            URI uri = classLoader.getResource("").toURI();
            String mainPath = Paths.get(uri).toString();
            Path path = Paths.get(mainPath, resource);
            System.out.println(path.toString());
            return path.toString();
        }
        catch(java.net.URISyntaxException e) {
            System.out.println(e.toString());
            return "";
        }
    }

}
