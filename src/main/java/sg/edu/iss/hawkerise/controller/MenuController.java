package sg.edu.iss.hawkerise.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import sg.edu.iss.hawkerise.model.Hawker;
import sg.edu.iss.hawkerise.model.MenuItem;
import sg.edu.iss.hawkerise.repo.MenuItemRepository;
import sg.edu.iss.hawkerise.service.MenuItemInterface;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	MenuItemInterface mservice;

	@RequestMapping(value = "/add")
	public String addMenu(Model model, HttpSession session) {
		if (session.getAttribute("hsession") == null) {
			return "forward:/hawker/login";
		}

		MenuItem m = new MenuItem();
		model.addAttribute("newMenu", m);
		return "menu/itemform";
	}

	@RequestMapping(value = "/save")
	public String saveMenu(@ModelAttribute("newMenu") MenuItem newMenuItem,
			@RequestParam("filePhoto") MultipartFile multipartFile, HttpSession session) throws IOException {
		if (session.getAttribute("hsession") == null) {
			return "forward:/hawker/login";
		}

		if (multipartFile.isEmpty()) {
			ClassPathResource classPathResource = new ClassPathResource("static/img/noPicture.png");
			File originPicture = classPathResource.getFile();
			String fileName = "noPicture.png";
			newMenuItem.setPhoto(fileName);
			Hawker h = (Hawker) session.getAttribute("hsession");
			newMenuItem.setHawker(h);
			mservice.saveMenuItem(newMenuItem);

			MenuItem savedMenuItem = newMenuItem;
			String uploadDir = "./item-photo/" + savedMenuItem.getId();

			Path uploadPath = Paths.get(uploadDir);
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			try (InputStream inputStream = new FileInputStream(originPicture)) {
				Path filePath = uploadPath.resolve(fileName);
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				throw new IOException("Could not save uploaded file: " + fileName);
			}

			String localUrl = "http://localhost:8080" + newMenuItem.getPhotoImagePath();
			newMenuItem.setLocalUrl(localUrl);

			mservice.saveMenuItem(newMenuItem);

			return "forward:/menu/list";

		} else {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			newMenuItem.setPhoto(fileName);

			Hawker h = (Hawker) session.getAttribute("hsession");
			newMenuItem.setHawker(h);

			mservice.saveMenuItem(newMenuItem);

			MenuItem savedMenuItem = newMenuItem;
			String uploadDir = "./item-photo/" + savedMenuItem.getId();

			Path uploadPath = Paths.get(uploadDir);
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			try (InputStream inputStream = multipartFile.getInputStream()) {
				Path filePath = uploadPath.resolve(fileName);
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				throw new IOException("Could not save uploaded file: " + fileName);
			}

			String localUrl = "http://localhost:8080" + newMenuItem.getPhotoImagePath();
			newMenuItem.setLocalUrl(localUrl);

			mservice.saveMenuItem(newMenuItem);

			return "forward:/menu/list";
		}
	}

	@RequestMapping(value = "/list")
	public String listMenu(Model model, HttpSession session) {
		if (session.getAttribute("hsession") == null) {
			return "forward:/hawker/login";
		}

		Hawker hawker = (Hawker) session.getAttribute("hsession");
		model.addAttribute("menuItems", mservice.findByHawker(hawker.getUserName()));
		return "menu/listmenu";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Integer id, HttpSession session) {
		if (session.getAttribute("hsession") == null) {
			return "hawker/hawkerlogin";
		}

		MenuItem menuToD = mservice.findById(id);
		mservice.deleteMenuItem(menuToD);

		return "forward:/menu/list";
	}

	@RequestMapping(value = "/update/{id}")
	public String update(@PathVariable("id") Integer id, Model model, HttpSession session) {
		if (session.getAttribute("hsession") == null) {
			return "forward:/hawker/login";
		} else {
			MenuItem menuItem = mservice.findById(id);
			model.addAttribute("menuToUpdate", menuItem);
			return "menu/update";
		}
	}

	@RequestMapping(value = "/saveUpdate")
	public String saveUpdate(@ModelAttribute("menuToUpdate") MenuItem menuToUpdate, HttpSession session, Model model,
			@RequestParam("filePhoto") MultipartFile multipartFile) throws IOException {
		if (session.getAttribute("hsession") == null) {
			return "forward:/hawker/login";
		}

		if (multipartFile.isEmpty()) {
			MenuItem oldMenuItem = mservice.findById(menuToUpdate.getId());

			menuToUpdate.setPhoto(oldMenuItem.getPhoto());
			menuToUpdate.setLocalUrl(oldMenuItem.getLocalUrl());

			mservice.updateMenuItem(menuToUpdate);

			return "forward:/menu/list";
		}

		else {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			menuToUpdate.setPhoto(fileName);

			String uploadDir = "./item-photo/" + menuToUpdate.getId();

			Path uploadPath = Paths.get(uploadDir);
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			try (InputStream inputStream = multipartFile.getInputStream()) {
				Path filePath = uploadPath.resolve(fileName);
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				throw new IOException("Could not save uploaded file: " + fileName);
			}

			String localUrl = "http://localhost:8080" + menuToUpdate.getPhotoImagePath();
			menuToUpdate.setLocalUrl(localUrl);

			mservice.updateMenuItem(menuToUpdate);

			return "forward:/menu/list";

		}

	}

}
