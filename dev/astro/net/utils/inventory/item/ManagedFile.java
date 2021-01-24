package dev.astro.net.utils.inventory.item;

import org.bukkit.plugin.java.*;
import java.util.logging.*;
import java.nio.charset.*;
import java.math.*;
import java.security.*;
import java.nio.file.*;
import dev.astro.net.*;
import java.util.*;
import java.io.*;

public class ManagedFile
{
    private static int BUFFER_SIZE;
    private transient File file;
    
    static {
        ManagedFile.BUFFER_SIZE = 8192;
    }
    
    public ManagedFile(final String filename, final JavaPlugin plugin) {
        this.file = new File(plugin.getDataFolder(), filename);
        if (!this.file.exists()) {
            try {
                copyResourceAscii(String.valueOf(String.valueOf(String.valueOf('/'))) + filename, this.file);
            }
            catch (IOException ex) {
                plugin.getLogger().log(Level.SEVERE, "items.csv has not been loaded", ex);
            }
        }
    }
    
    public static void copyResourceAscii(final String resourceName, final File file) throws IOException {
        Throwable t = null;
        try {
            final InputStreamReader reader = new InputStreamReader(ManagedFile.class.getResourceAsStream(resourceName), StandardCharsets.UTF_8);
            try {
                final MessageDigest digest = getDigest();
                Throwable t2 = null;
                final DigestOutputStream digestStream = new DigestOutputStream(new FileOutputStream(file), digest);
                try {
                    final OutputStreamWriter writer = new OutputStreamWriter(digestStream, StandardCharsets.UTF_8);
                    try {
                        final char[] buffer = new char[8192];
                        int length;
                        while ((length = reader.read(buffer)) >= 0) {
                            writer.write(buffer, 0, length);
                        }
                        writer.write("\n");
                        writer.flush();
                        digestStream.on(false);
                        digestStream.write(35);
                        digestStream.write(new BigInteger(1, digest.digest()).toString(16).getBytes(StandardCharsets.UTF_8));
                    }
                    finally {
                        if (writer != null) {
                            writer.close();
                        }
                    }
                    if (writer != null) {
                        writer.close();
                    }
                    if (writer != null) {
                        writer.close();
                    }
                    if (digestStream != null) {
                        digestStream.close();
                    }
                }
                finally {
                    if (t2 == null) {
                        t2 = null;
                    }
                    else {
                        final Throwable t3 = null;
                        if (t2 != t3) {
                            t2.addSuppressed(t3);
                        }
                    }
                    if (digestStream != null) {
                        digestStream.close();
                    }
                }
                if (t2 == null) {
                    t2 = null;
                }
                else {
                    final Throwable t3 = null;
                    if (t2 != t3) {
                        t2.addSuppressed(t3);
                    }
                }
                if (digestStream != null) {
                    digestStream.close();
                }
            }
            finally {
                if (reader != null) {
                    reader.close();
                }
            }
            if (reader != null) {
                reader.close();
            }
            if (reader != null) {
                reader.close();
            }
        }
        finally {
            if (t == null) {
                t = null;
            }
            else {
                final Throwable t4 = null;
                if (t != t4) {
                    t.addSuppressed(t4);
                }
            }
        }
        if (t == null) {
            t = null;
        }
        else {
            final Throwable t4 = null;
            if (t != t4) {
                t.addSuppressed(t4);
            }
        }
        if (t == null) {
            t = null;
        }
        else {
            final Throwable t5 = null;
            if (t != t5) {
                t.addSuppressed(t5);
            }
        }
    }
    
    public static MessageDigest getDigest() throws IOException {
        try {
            return MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException ex) {
            throw new IOException(ex);
        }
    }
    
    public File getFile() {
        return this.file;
    }
    
    public List<String> getLines() {
        try {
            Throwable t = null;
            try {
                final BufferedReader reader = Files.newBufferedReader(Paths.get(this.file.getPath(), new String[0]), StandardCharsets.UTF_8);
                try {
                    final List<String> lines = new ArrayList<String>();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        lines.add(line);
                    }
                    return lines;
                }
                finally {
                    if (reader != null) {
                        reader.close();
                    }
                }
            }
            finally {
                if (t == null) {
                    t = null;
                }
                else {
                    final Throwable t2 = null;
                    if (t != t2) {
                        t.addSuppressed(t2);
                    }
                }
            }
        }
        catch (IOException ex) {
            Vapor.getInstance().getLogger().log(Level.SEVERE, ex.getMessage(), ex);
            return Collections.emptyList();
        }
    }
    
    public static int getBufferSize() {
        return ManagedFile.BUFFER_SIZE;
    }
}
