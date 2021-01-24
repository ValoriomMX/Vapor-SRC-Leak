package dev.astro.net.utils.inventory;

import net.minecraft.util.com.google.common.base.*;
import java.util.*;
import net.minecraft.util.com.google.common.collect.*;

public class MapSorting
{
    private static Function EXTRACT_KEY;
    private static Function EXTRACT_VALUE;
    
    static {
        MapSorting.EXTRACT_KEY = (Function)new Function<Map.Entry<Object, Object>, Object>() {
            public Object apply(final Map.Entry<Object, Object> input) {
                return (input == null) ? null : input.getKey();
            }
        };
        MapSorting.EXTRACT_VALUE = (Function)new Function<Map.Entry<Object, Object>, Object>() {
            public Object apply(final Map.Entry<Object, Object> input) {
                return (input == null) ? null : input.getValue();
            }
        };
    }
    
    public static <T, V> List<Map.Entry<T, V>> sortedValues(final Map<T, V> map, final Comparator<V> valueComparator) {
        return (List<Map.Entry<T, V>>)Ordering.from((Comparator)valueComparator).onResultOf((Function)extractValue()).sortedCopy((Iterable)map.entrySet());
    }
    
    public static <T, V> Iterable<T> keys(final List<Map.Entry<T, V>> entryList) {
        return (Iterable<T>)Iterables.transform((Iterable)entryList, (Function)extractKey());
    }
    
    public static <T, V> Iterable<V> values(final List<Map.Entry<T, V>> entryList) {
        return (Iterable<V>)Iterables.transform((Iterable)entryList, (Function)extractValue());
    }
    
    private static <T, V> Function<Map.Entry<T, V>, T> extractKey() {
        return (Function<Map.Entry<T, V>, T>)MapSorting.EXTRACT_KEY;
    }
    
    private static <T, V> Function<Map.Entry<T, V>, V> extractValue() {
        return (Function<Map.Entry<T, V>, V>)MapSorting.EXTRACT_VALUE;
    }
}
