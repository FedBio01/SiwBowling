package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Notice;
import it.uniroma3.siw.service.NoticeService;
import org.springframework.ui.Model;


@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("/admin/newNotice")
	public String formNewNotice(Model model) {
		model.addAttribute("notice", new Notice());
		return "/admin/formNewNotice.html";
	}
	
	@PostMapping("/admin/notice")
	public String NewNotice(@ModelAttribute("notice") Notice notice, Model model) {
		Notice createdNotice = this.noticeService.createNewNotice(notice);
		model.addAttribute("notice", createdNotice);
		return "/admin/noticeSuccessful.html";
	}

}
