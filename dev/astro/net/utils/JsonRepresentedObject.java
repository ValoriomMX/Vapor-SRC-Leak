package dev.astro.net.utils;

import org.bukkit.craftbukkit.libs.com.google.gson.stream.*;
import java.io.*;

interface JsonRepresentedObject
{
    void writeJson(final JsonWriter p0) throws IOException;
}
