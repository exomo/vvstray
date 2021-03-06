package de.oliver_arend.VVStray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import java.awt.Image;

public class Utils {

    public static String readFile(String path, Charset encoding) throws IOException {
        Path readPath = Paths.get(path).toAbsolutePath();
        System.out.println("Reading file: " + readPath.toString());
        byte[] encoded = Files.readAllBytes(readPath);
            return new String(encoded, encoding);
    }

    public static void writeFile(String path, String body, Charset encoding) throws IOException {
        Path writePath = Paths.get(path).toAbsolutePath();;
        System.out.println("Writing to file: " + writePath.toString());
        byte[] encoded = body.getBytes(encoding);
        try {
            Files.write(writePath, encoded);
        }
        catch(IOException e) {
            System.out.println(e.toString());
        }
    }

    public static boolean isNumeric(String str)
    {
        try {
            Integer.parseInt(str);
            return true;
        }catch(NumberFormatException nfe){
            return false;
        }

    }

    public static String getTextFromResource(String resource, Charset encoding)
    {
        ClassLoader classLoader = Utils.class.getClassLoader();
        BufferedReader reader = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(resource), encoding));
        String text = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        return text;
    }

    public static Image getImageFromResource(String resource)
    {
        try {
            ClassLoader classLoader = Utils.class.getClassLoader();
            Image image = ImageIO.read(classLoader.getResource(resource));
            return image;
        }
        catch(IOException e) {
            System.out.println(e.toString());
            return null;
        }
    }

}
