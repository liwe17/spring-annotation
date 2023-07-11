package com.weiliai.ext;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class Red {

    public Red() {
        System.err.println("Red create ...");
    }
}
