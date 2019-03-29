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
        System.out.println("Reading file: " + getConfigPath(path));
        System.out.println("Reading file: " + Paths.get(path).toAbsolutePath().toString());

        System.out.println("abs1 " + Paths.get("").toFile().getAbsolutePath().toString());
        System.out.println("abs2 " + FileSystems.getDefault().getPath(".").toString());

        byte[] encoded = Files.readAllBytes(getConfigPath(path));
            return new String(encoded, encoding);
    }

    public static void writeFile(String path, String body, Charset encoding) throws IOException {
        System.out.println("Writing to file: " + path);
        System.out.println("Writing to file: " + Paths.get(path).toAbsolutePath().toString());

        System.out.println("abs1 " + Paths.get("").toFile().getAbsolutePath().toString());
        System.out.println("abs2 " + FileSystems.getDefault().getPath(".").toString());

        byte[] encoded = body.getBytes(encoding);
        try {
            Files.write(Paths.get(path), encoded);
        }
        catch(IOException e) {
            System.out.println(e.toString());
        }
    }

    public static boolean isNumeric(String str)
    {
        try { int i = Integer.parseInt(str); }
        catch(NumberFormatException nfe) { return false; }
        return true;
    }

    public static Path getConfigPath(String filename) {

		try {
			ClassLoader classLoader = UserSettingsProvider.class.getClassLoader();
			Properties props = new Properties();
			props.load(classLoader.getResourceAsStream("application.properties"));
			Path basedir = Paths.get(props.getProperty("project.basedir"), filename);
            System.out.println(basedir.toString());
            return basedir;
		}
		catch(IOException e) {
            System.out.println(e.toString());
            return Paths.get(filename);
		}
		catch(Exception e) {

			System.out.println(e.toString());
            return Paths.get(filename);
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
