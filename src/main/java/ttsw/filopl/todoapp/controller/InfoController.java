package ttsw.filopl.todoapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by T. Filo Zegarlicki on 31.08.2022
 **/


@RestController
public class InfoController {

    private String url;
    private String myProp;

    @GetMapping("/info/url")
    String url() {
        return "url";
    }

    @GetMapping("/info/prop")
    String myProp() {
        return "prop";
    }
}
