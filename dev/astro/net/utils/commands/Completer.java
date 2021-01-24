package dev.astro.net.utils.commands;

import java.lang.annotation.*;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Completer {
    String name();
    
    String[] aliases() default {};
}
