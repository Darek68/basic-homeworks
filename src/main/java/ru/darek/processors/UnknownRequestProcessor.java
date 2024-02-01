package ru.darek.processors;

import ru.darek.HttpRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class UnknownRequestProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest httpRequest, OutputStream output) throws IOException {
        String response = "HTTP/1.1 404 OK\r\nContent-Type: text/html;charset=utf-8\r\n\r\n<html><body ALIGN='center'><h1>404</h1><h3>Страница не найдена</h3></body></html>";
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}