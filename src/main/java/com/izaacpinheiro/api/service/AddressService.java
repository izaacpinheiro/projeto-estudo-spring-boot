package com.izaacpinheiro.api.service;

import com.izaacpinheiro.api.domain.address.Address;
import com.izaacpinheiro.api.domain.event.Event;
import com.izaacpinheiro.api.domain.event.EventRequestDTO;
import com.izaacpinheiro.api.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    // função para criar Address a partir de Event
    public Address createAddress(EventRequestDTO data, Event event){
        Address address = new Address();
        address.setCity(data.city());
        address.setUf(data.state());
        address.setEvent(event);

        // salvando o novo address no bd
        return addressRepository.save(address);
    }
}
