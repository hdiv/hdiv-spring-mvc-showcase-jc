package org.hdiv.samples.mvc.controllers;

import javax.validation.Valid;

import org.hdiv.samples.mvc.bean.MultipartBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 * Class that controls the workflow of the FileUpload example.
 * 
 */
@Controller
@RequestMapping("/fileupload/")
@SessionAttributes("bean")
public class FileUploadController {

	@RequestMapping(value = "fileupload", method = RequestMethod.GET)
	public String prepareIndex() {
		return "/fileupload/fileupload";
	}

	@RequestMapping(value = "simple", method = RequestMethod.GET)
	public String prepareSimple() {
		return "/fileupload/simple";
	}

	@RequestMapping(value = "simple", method = RequestMethod.POST)
	public String processSimple(@RequestParam(value = "file", required = false) MultipartFile file, Model model) {

		model.addAttribute("message", "File '" + file.getOriginalFilename() + "' uploaded successfully");
		return "/fileupload/simple";
	}

	@RequestMapping(value = "binding", method = RequestMethod.GET)
	public String prepareBinding(Model model) {

		MultipartBean bean = new MultipartBean();
		bean.setHiddenValue(10);
		model.addAttribute("bean", bean);

		return "/fileupload/binding";
	}

	@RequestMapping(value = "binding", method = RequestMethod.POST)
	public String processBinding(@Valid @ModelAttribute("bean") MultipartBean bean,  Errors errors, Model model) {

		if (errors.hasErrors()) {
			model.addAttribute("error", "true");
		}
		else {
			model.addAttribute("message", "File '" + bean.getFile().getOriginalFilename()
				+ "' uploaded successfully. Name '" + bean.getName() + "'. Search '" + bean.getSearch()
				+ "'. HiddenValue '" + bean.getHiddenValue() + "'.");
		}
		
		return "/fileupload/binding";
	}

}
