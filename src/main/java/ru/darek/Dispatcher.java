package ru.darek;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.darek.processors.HelloWorldRequestProcessor;
import ru.darek.processors.OperationAddRequestProcessor;
import ru.darek.processors.RequestProcessor;
import ru.darek.processors.UnknownRequestProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Dispatcher {
    public static final Logger logger = LogManager.getLogger(Dispatcher.class.getName());
    private Map<String, RequestProcessor> router;
    private RequestProcessor unknownRequestProcessor;

    public Dispatcher() {
        this.router = new HashMap<>();
        this.router.put("/add", new OperationAddRequestProcessor());         // /GET /add => OperationAddRequestProcessor
        this.router.put("/hello_world", new HelloWorldRequestProcessor());   // /GET /hello_world => HelloWorldRequestProcessor
        this.unknownRequestProcessor = new UnknownRequestProcessor();
    }

    public void execute(HttpRequest httpRequest, OutputStream output) throws IOException {
        if (!router.containsKey(httpRequest.getUri())) {
            unknownRequestProcessor.execute(httpRequest, output);
            logger.debug("Unknown request: " + httpRequest.getRawRequest());
            return;
        }
        router.get(httpRequest.getUri()).execute(httpRequest, output);
    }
}


