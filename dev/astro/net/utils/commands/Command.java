package dev.astro.net.utils.commands;

import java.lang.annotation.*;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    String name();
    
    String permission() default "";
    
    String noPerm() default "You do not have permission to perform that action.";
    
    String[] aliases() default {};
    
    String description() default "";
    
    String usage() default "";
    
    boolean inGameOnly() default false;
}
