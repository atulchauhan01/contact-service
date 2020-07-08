package com.springcloudmysql.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcloudmysql.model.Contact;
import com.springcloudmysql.repository.ContactRepository;

@RestController
@RequestMapping({ "/contacts" })
public class ContactController {

	//Autowiring repository
	@Autowired
	private ContactRepository repository;

	/*
	 * ContactController(ContactRepository contactRepository) { this.repository =
	 * contactRepository; }
	 */

	@GetMapping
	public String getContact() {
		return "Contact service is up and running";

	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Contact> getContact(@PathVariable long id) {

		/*
		 * return repository.findById(id).map(record ->
		 * ResponseEntity.ok().body(record)) .orElse(ResponseEntity.notFound().build());
		 */

		Optional<Contact> optional = repository.findById(id);
		if (optional.isPresent()) {
			Contact contact = optional.get();
			return ResponseEntity.ok().body(contact);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public void deleteContact(@PathVariable long id) {
		repository.deleteById(id);
	}

	@PostMapping
	public ResponseEntity<Contact> saveContact(@RequestBody Contact contact) {
		Contact savedObj = repository.save(contact);

		return ResponseEntity.ok().body(savedObj);
	}

	//Put mapping changes
	@PutMapping(value = "/{id}")
	public ResponseEntity<Contact> updateContact(@PathVariable long id, 
			@RequestBody Contact contact) {

		Optional<Contact> optionalObj = repository.findById(id);
		if (optionalObj.isPresent()) {
			Contact contactobj = optionalObj.get();
			contactobj.setName(contact.getName());
			contactobj.setEmail(contact.getEmail());
			contactobj.setPhone(contact.getPhone());
			repository.save(contactobj);
			return ResponseEntity.ok().body(contactobj);
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	 
}
