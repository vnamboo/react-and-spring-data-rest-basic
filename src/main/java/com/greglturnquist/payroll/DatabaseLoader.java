/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.greglturnquist.payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@Component
public class DatabaseLoader implements CommandLineRunner {

	private final EmployeeRepository repository;
	private final PaymentRepository paymentRepository;

	@Autowired
	public DatabaseLoader(EmployeeRepository repository,PaymentRepository paymentRepository) {
		this.repository = repository;
		this.paymentRepository = paymentRepository;
	}

	@Override
	public void run(String... strings) throws Exception {
		this.repository.save(new Employee("Frodo", "Baggins", "ring bearer"));
		this.paymentRepository.save(new Payment("[id=1, value=200, cur=INR]", "sbi"));
		this.paymentRepository.save(new Payment("[id=2, value=200, cur=INR]", "sbi"));
		this.paymentRepository.save(new Payment("[id=3, value=200, cur=INR]", "sbt"));
		this.paymentRepository.save(new Payment("[id=4, value=200, cur=INR]", "scb"));
		this.paymentRepository.save(new Payment("[id=5, value=200, cur=INR]", "hdfc"));
		this.paymentRepository.save(new Payment("[id=6, value=200, cur=INR]", "hsbc"));
		List<Payment> sbi = this.paymentRepository.findByTag("sbi");
		sbi.stream().forEach(payment -> {
			System.out.println(payment.getMessage());
		});

	}
}
// end::code[]