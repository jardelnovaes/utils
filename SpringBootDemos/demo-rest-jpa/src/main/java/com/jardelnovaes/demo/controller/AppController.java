package com.jardelnovaes.demo.controller;

import com.jardelnovaes.demo.Actions;
import com.jardelnovaes.demo.model.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.ArrayList;
   
  @Controller
  @RequestMapping("/")
  public class AppController {
        
		@Autowired
		@Qualifier("toLeft")
		private Actions l3ft;
		
		@Autowired
		private Actions toRight;
		
		@RequestMapping(value = "/test2", method = RequestMethod.GET)
        public String index(Model model) {
				/*
              List<Livro> listaLivros = livroRepository.findByAutor(autor);
              if (listaLivros != null) {
                    model.addAttribute("livros", listaLivros);
              }
			  */
			  //l3ft = new ToLeft();
			  //toRight = new ToRight();
			  model.addAttribute("left", l3ft);
			  model.addAttribute("right", toRight);
			  
              return "index";
        }
		
		@ResponseBody
		@RequestMapping(value = "/test3", method = RequestMethod.GET)
        public List<Actions> index3() {
			  //l3ft = new ToLeft();
			  //toRight = new ToRight();
			  List<Actions> actions = new ArrayList();
			  actions.add(l3ft);
			  actions.add(toRight);
			  return actions;
        }
		
		@ResponseBody
		@RequestMapping(value = "/test", method = RequestMethod.GET)
        public Entity index2(Model model) {
			  //l3ft = new ToLeft();
			  //toRight = new ToRight();
			  model.addAttribute("left", l3ft);
			  model.addAttribute("right", toRight);
			  
              return new Entity();
        }
  }