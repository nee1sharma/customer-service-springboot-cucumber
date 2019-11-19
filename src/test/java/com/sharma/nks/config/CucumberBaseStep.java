package com.sharma.nks.config;

import com.customer.service.impl.model.Customer;
import com.customer.service.impl.model.CustomerList;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import java.util.List;
import java.util.Map;

@Component
public class CucumberBaseStep {

    ThreadLocal<ResponseEntity<Customer>> localCustomer = ThreadLocal.withInitial(() -> ResponseEntity.of(null));
    ThreadLocal<ResponseEntity<CustomerList>> localCustomerList = ThreadLocal
            .withInitial(() -> ResponseEntity.of(null));
    private FTRequestInterceptor ftRequestInterceptor;

    // @Value("${spring.application.server.url}")
    private String SERVER_URL = "http://localhost";

    // @Value("${spring.application.context}")
    private String CONTEXT_PATH = "cust-mgmt/customers/";

    protected static String ENDPOINT;

    // @Value("${spring.application.port}")
    private int port = 8090;

    private RestTemplate restTemplate;

    public CucumberBaseStep() {
        restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        ftRequestInterceptor = new FTRequestInterceptor();
        interceptors.add(ftRequestInterceptor);
        restTemplate.setInterceptors(interceptors);
    }

    private String buildEndpoint() {
        return SERVER_URL + ":" + port + CONTEXT_PATH + ENDPOINT;
    }

    public void createCustomer(Customer customerParam) {
        ENDPOINT = "";
        HttpEntity<Customer> customerHttpEntity = new HttpEntity<>(customerParam);
        ResponseEntity<Customer> responseEntity = restTemplate.postForEntity(buildEndpoint(), customerHttpEntity,
                Customer.class);

        localCustomer.set(responseEntity);
    }

    public void getCustomerDetails(String customerId) {
        ENDPOINT = "{customerId}";
        ResponseEntity<Customer> responseEntity = null;
        try {
            responseEntity = restTemplate.getForEntity(buildEndpoint(), Customer.class, customerId);
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            responseEntity = new ResponseEntity<>(ex.getStatusCode());
        } catch (UnknownHttpStatusCodeException ex) {
            responseEntity = new ResponseEntity<>(HttpStatus.resolve(ex.getRawStatusCode()));
        }
        System.out.println(responseEntity.getStatusCode());
        localCustomer.set(responseEntity);
    }

    public void getAllCustomers() {
        ENDPOINT = "";
        ResponseEntity<CustomerList> responseEntity = restTemplate.getForEntity(buildEndpoint(), CustomerList.class);
        localCustomerList.set(responseEntity);
    }

    @Override
    protected void finalize() throws Throwable {
        Map<String, String> calls = ftRequestInterceptor.getCalls();
        System.out.println(calls);
        super.finalize();
    }
}
