package unet.anyclick.aus.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import unet.anyclick.aus.controller.view.DownloadView;
import unet.anyclick.aus.error.ServiceException;

@Controller
@RequestMapping(value = "api")
public class FileController {

	private final String DOWNLOAD_PATH = "/WEB-INF/file";
	private final String UPLOAD_PATH = "/WEB-INF/file";

	private WebApplicationContext context = null;

	@RequestMapping(value = "download/{$file_name}", method = RequestMethod.GET)
	public ModelAndView downloadFile(@PathVariable String $file_name, HttpServletResponse response) {
		context = ContextLoader.getCurrentWebApplicationContext();
		String dir = context.getServletContext().getRealPath(DOWNLOAD_PATH);
		File file = new File(dir, $file_name);
		if (file.exists() == false) {
			throw new ServiceException("파일이 존재하지않습니다");
		}
		return new ModelAndView(new DownloadView(), "file", file);
	}

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public void uploadFile(@RequestParam("file") MultipartFile $file) {
		try {
			String file_name = $file.getOriginalFilename();
			String file_path = UPLOAD_PATH + File.separator + file_name;
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(file_path)));
			stream.write($file.getBytes());
			stream.close();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
