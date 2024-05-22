package com.tigrisblog.service.impl;

import com.tigrisblog.entity.Contact;
import com.tigrisblog.repos.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {

  private final ContactRepository contactRepository;

  public Contact saveContact(Contact contact){
    return contactRepository.save(contact);
  }

 public Contact getContact(){
    return contactRepository.findAll().get(0);
  }

  public void deleteContact(long id){
    contactRepository.deleteById(id);
  }
}
