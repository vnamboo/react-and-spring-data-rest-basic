package com.greglturnquist.payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/search/{tag}")
public class PaymentRestController {

    private final PaymentRepository paymentRepository;

    @Autowired
    PaymentRestController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<Payment> search(@PathVariable String tag) {
        return this.paymentRepository.findByTag(tag);
    }

}
