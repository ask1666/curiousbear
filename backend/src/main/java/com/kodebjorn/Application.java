package com.kodebjorn;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    servers = @Server(url = "localhost:8080"),
    info = @Info(
        title = "Curiousbear",
        version = "0.1",
        description = "My API",
        contact = @Contact(name = "Asbjorn Frostad")
    )
)
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
