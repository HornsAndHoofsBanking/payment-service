package com.andersenlab.payment.dao.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

import com.andersenlab.payment.domain.Payment;

@Repository
public interface PaymentDaoElasticSearch extends ElasticsearchCrudRepository<Payment, Long> {

}
