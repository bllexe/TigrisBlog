package com.tigrisblog.controller;

import com.tigrisblog.entity.Contact;
import com.tigrisblog.service.impl.ContactService;
import com.tigrisblog.utils.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.ContactCTRL.CTRL)
public class ContactController {

  private final ContactService contactService;


  @PostMapping
  public ResponseEntity<Contact> saveContact(@RequestBody Contact contact){
    return new ResponseEntity<>(contactService.saveContact(contact), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<Contact> getContact(){
    return new ResponseEntity<>(contactService.getContact(),HttpStatus.OK);
  }
 @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteContact(@PathVariable Long id){
    contactService.deleteContact(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
