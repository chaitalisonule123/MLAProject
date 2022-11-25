package com.jbk.Mla.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.jbk.Mla.entity.Mla;
import com.jbk.Mla.service.MlaService;

//incoming request ko map karata hai and responce ko transfer krta hai controller
//controller ko call client dega.
//controller se call service jayega then vahase dao me.
//response Entity ke andar wrapper class hi dwtw hai
//body ke thorough data aane vala hai for tha @RequestBody
//@RestCOntroller MlaController(object) ko container me load krega.


@Controller
public class MlaController {
	
	@Autowired
	private  MlaService service;
	//container se service ko load kraega.
	

	@PostMapping(value= "/savemla")
	 public ResponseEntity<Boolean> saveMla(@Valid @RequestBody Mla mla){
		boolean isAdded = service.saveMla(mla);

		if (isAdded) {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT);
 
		}

	} //
	
	
	@GetMapping(value= "/getallmla")
	public ResponseEntity<List<Mla>> getAllMla(){
		 List<Mla> allSupplier =service.getAllMla();
		 

			if (!allSupplier.isEmpty()) {
				return new ResponseEntity<List<Mla>>(allSupplier, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<List<Mla>>(HttpStatus.NO_CONTENT);
		 }
	}//	
	

	//request param
		//url..?..parameter=value
		//localhost:8080/getmlabyid?id=1
	//or using path variable:-8080/getmlabyid/1
		
	@GetMapping(value= "/getmlabyid/{id}")
	 public ResponseEntity<Mla> getMlaById(@PathVariable int id) {
		 Mla mla = service.getMlaById(id);
	              if(mla!=null) {
	            	  
	            	  return new ResponseEntity<Mla>(mla, HttpStatus.OK);  
	            	  //succesfully api hit hui to ok ka status code denge.
	              }
	              else {
	            	  return new ResponseEntity<Mla>(HttpStatus.NO_CONTENT);
	              }		 
		 
	 }//
	
	@PutMapping(value="/updatemla")
	public ResponseEntity<Boolean> updateMla(@RequestBody Mla mla) {
		boolean isUpdated=service.updateMla(mla);
		
		return new ResponseEntity<Boolean>(isUpdated, HttpStatus.OK); 
		
		
	}
	
	@DeleteMapping(value="/deletemla")
	public ResponseEntity<Boolean> deleteMla (@RequestParam int id){

		boolean isDeleted =service.deleteMla(id);
		return new ResponseEntity<Boolean>(isDeleted, HttpStatus.OK); 
		
		
	}
	
	@GetMapping(value = "/gettotalcountofmla")
   public ResponseEntity<Long> getTotalCountOfMla() {
	   
	   long count = service.getTotalCountOfMla();
		if (count > 0)
			return new ResponseEntity<Long>(count, HttpStatus.OK);
		else {
			return new ResponseEntity<Long>(HttpStatus.NO_CONTENT);

		}
	   
	     
   }
	@GetMapping(value = "/getavgOfmlavote")
	 public ResponseEntity<Double> getAvgOfMlaVote() {
		 
		 Double avg	=service.getAvgOfMlaVote();
			if (avg > 0)
				return new ResponseEntity<Double>(avg, HttpStatus.OK);
			else {
				return new ResponseEntity<Double>(HttpStatus.NO_CONTENT);

			}
		
		 
		 
	 }
	@GetMapping(value = "/getmaxvotemla")
	 public ResponseEntity<Mla> getMaxVoteMla() {
		 Mla mla=service.getMaxVoteMla();
			if(mla!=null) {
				return new ResponseEntity<Mla>(mla, HttpStatus.OK);  
				
			}
			else {
				return new ResponseEntity<Mla>( HttpStatus.NO_CONTENT);  
			} 
		 
	 }
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


