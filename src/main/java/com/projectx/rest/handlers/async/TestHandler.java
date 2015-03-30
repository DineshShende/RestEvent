package com.projectx.rest.handlers.async;

import org.springframework.stereotype.Component;

@Component
public class TestHandler {

	public  String createAnswer() {
        int i = 0;
        for (int bit = 0; bit < 16; bit++) {
            i |= bit << bit;
        }
        return Integer.toString(i);
    }
	
}
