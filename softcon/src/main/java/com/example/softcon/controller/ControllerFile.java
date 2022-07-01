package com.example.softcon.controller;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.example.softcon.model.User;
import com.example.softcon.model.Tracker;
import com.example.softcon.model.LogIn;
import com.example.softcon.service.ServiceFile;

@RestController
public class ControllerFile {

	@Autowired
	private ServiceFile ServiceFile;
	
	
	
	//for user and task
	@CrossOrigin
	@GetMapping("/user")
	public ResponseEntity<List<User>> retrieveAllUser() {
//		return ServiceFile.retrieveAllUser();
		List<User> userlist = ServiceFile.retrieveAllUser();
		
		
		if (userlist == null || userlist.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<List<User>>(userlist, HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/user/{userId}/tracker")
	public ResponseEntity<List<Tracker>> retrieveTaskForUser(@PathVariable int userId) {
//		return ServiceFile.retrieveTask(userId);
		List<Tracker> tasklist = ServiceFile.retrieveTracker(userId);
		
		
		if (tasklist == null || tasklist.isEmpty()) {
			return new ResponseEntity<List<Tracker>>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<List<Tracker>>(tasklist, HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<User>> retrieveSpecificUser(@PathVariable int userId) {
//		return ServiceFile.retrieveUser(userId);
		List<User> userlist = ServiceFile.retrieveUser(userId);
		
		
		if (userlist == null || userlist.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<List<User>>(userlist, HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/user/{userId}/{trackerId}")
	public ResponseEntity<List<Tracker>> retrieveSpecificTask(@PathVariable int userId, @PathVariable int trackerId) {
//		return ServiceFile.retrieveSpecificTask(userId,taskId);
		
		List<Tracker> tasklist = ServiceFile.retrieveSpecificTask(userId,trackerId);
		
		
		if (tasklist == null || tasklist.isEmpty()) {
			return new ResponseEntity<List<Tracker>>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<List<Tracker>>(tasklist, HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping("/user/signin")
	public ResponseEntity<List<User>> signInUser(@RequestBody LogIn newLogIn) {
//	public List<User> signInUser(@RequestBody LogIn newLogIn) {
		List<User> login = ServiceFile.funclogIn(newLogIn);
		
		
		if (login == null || login.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<List<User>>(login, HttpStatus.OK);

	}
	
	
	@CrossOrigin
	@PostMapping("/user/register")
	public ResponseEntity<User> registerUser(@RequestBody User newUser) {
		System.out.println("newUser");
		int user = ServiceFile.funcRegister(newUser);
		

		if (user != 0)
			return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
			
		
		return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
	}

	@CrossOrigin
	@PostMapping("/user/trackerinsert")
	public ResponseEntity<Tracker> registerUser(@RequestBody Tracker newTask) {

		Tracker task = ServiceFile.funcTaskInsert(newTask);
		

		if (task.getId()!=0)
			return new ResponseEntity<Tracker>(newTask, HttpStatus.CREATED);
			
		
		return new ResponseEntity<Tracker>(HttpStatus.BAD_REQUEST);
	}
	
	@CrossOrigin
	@DeleteMapping(value = "/trackerdelete/{id}")
    public ResponseEntity<Tracker> deleteTask(@PathVariable int id) {

        int isRemoved = ServiceFile.deleteTask(id);

        if (isRemoved!= 0) {
        	return new ResponseEntity<Tracker>(HttpStatus.OK);
        }
        return new ResponseEntity<Tracker>(HttpStatus.NOT_FOUND);
        
    }
	
	@CrossOrigin
	@PutMapping("/trackerupdate")
	public ResponseEntity<Tracker> updateCustomer(@RequestBody Tracker task) {
		int resultupdate=ServiceFile.updateTask(task);
		if (resultupdate!= 0) {
        	return new ResponseEntity<Tracker>(HttpStatus.OK);
        }
		return new ResponseEntity<Tracker>(HttpStatus.INTERNAL_SERVER_ERROR);
//		try {
//			return new ResponseEntity<Task>(ServiceFile.updateTask(task), HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<Task>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
	}


}
