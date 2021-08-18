package br.com.rchlo.store.controller;

import br.com.rchlo.store.domain.Payment;
import br.com.rchlo.store.dto.PaymentDto;
import br.com.rchlo.store.form.PaymentForm;
import br.com.rchlo.store.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> filterById(@PathVariable Long id){
        Optional<Payment> payment = paymentRepository.findById(id);
        if(payment.isPresent()){
            return ResponseEntity.ok(PaymentDto.convertOneToDto(payment.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PaymentDto> addPayment(@RequestBody @Valid PaymentForm form, UriComponentsBuilder uriBuilder){
        Payment payment = form.convertToPayment();
        paymentRepository.save(payment);
        URI uri = uriBuilder.path("payments/{id}").buildAndExpand(payment.getId()).toUri();
        return ResponseEntity.created(uri).body(PaymentDto.convertOneToDto(payment));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PaymentDto> confirmPayment(@PathVariable Long id){

        Optional<Payment> payment = paymentRepository.findById(id);

        try {
            payment.get().confirmPayment();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(PaymentDto.convertOneToDto(payment.get()));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<PaymentDto> cancelPayment(@PathVariable Long id){

        Optional<Payment> payment = paymentRepository.findById(id);

        try {
            payment.get().cancelPayment();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(PaymentDto.convertOneToDto(payment.get()));
    }
}
