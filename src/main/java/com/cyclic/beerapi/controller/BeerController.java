package com.cyclic.beerapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cyclic.beerapi.database.BeerService;
import com.cyclic.beerapi.vo.Beer;

@RestController
public class BeerController {

	@Autowired
	private BeerService beerService;
	
	
}
