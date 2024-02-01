package ru.darek.processors;

import com.google.gson.Gson;
import ru.darek.HttpRequest;
import ru.darek.entities.Category;
import ru.darek.entities.Product;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class AddBodyRequestProcessor implements RequestProcessor {
    private Gson gson;

    public AddBodyRequestProcessor() {
        gson = new Gson();
    }

    @Override
    public void execute(HttpRequest httpRequest, OutputStream output) throws IOException {
        Product product = gson.fromJson(httpRequest.getBody(), Product.class);
        System.out.println(product);
        Product durian = new Product(777L,"durian",new Category(20L,"fruit"));
        String strDurian = gson.toJson(durian,Product.class);

        String response = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n<html><body>" + strDurian + "</body></html>";
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
