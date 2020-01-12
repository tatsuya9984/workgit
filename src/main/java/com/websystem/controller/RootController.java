package com.websystem.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.websystem.db.noteRepository;
import com.websystem.entity.db.NoteEntity;

@Controller
public class RootController {
  @Autowired
  private noteRepository noteRepo;

	@RequestMapping(value="/",method=RequestMethod.GET)
	public String rootController(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		if (id == null) {
			return "redirect:login";
		}
		List<NoteEntity> items = noteRepo.findAll();
		model.addAttribute("name", id);
		model.addAttribute("items", items);
		return "top";
	}
}
