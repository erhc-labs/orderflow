package org.erhc.orderflow.adaptersoutmongo.repository;

import org.erhc.orderflow.adaptersoutmongo.model.OrderDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface MongoOrderSpringRepository extends MongoRepository<OrderDocument, String>{

}
