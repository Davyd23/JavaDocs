package ro.teamnet.zth.api.annotations;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface MyController {
    String urlPath();
}
