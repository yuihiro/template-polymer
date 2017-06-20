package unet.anyclick.aus.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

@Controller
@RequestMapping("api/test")
public class TestController {

	@RequestMapping("hi")
	@ResponseBody
	public String printHi() {
		return "hi";
	}

	@RequestMapping("json")
	@ResponseBody
	public Map printJson() {
		Map result = new HashMap();
		result.put("name", "json");
		result.put("type", "test");
		return result;
	}

	@RequestMapping("jsp")
	public String viewJsp(Model model) {
		model.addAttribute("message", "하이");
		return "test";
	}

	@RequestMapping("http")
	public ResponseEntity returnHttpStatus() {
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	@RequestMapping("except")
	public String returnException() {
		throw new IllegalArgumentException("custom exception");
	}

	@RequestMapping("csv")
	public void downloadCSV(HttpServletResponse response) throws IOException {
		String file_name = "books.csv";
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file_name));

		List<String[]> data = new ArrayList<String[]>();
		data.add(new String[] { "1", "wild", "010-123-1234" });
		data.add(new String[] { "2", "chris", "011-234-1231" });
		data.add(new String[] { "3", "jack", "010-123-2341" });

		ICsvListWriter csvWriter = new CsvListWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		String[] header = { "Title", "Description", "Author" };
		csvWriter.writeHeader(header);
		for (String[] item : data) {
			csvWriter.write(item);
		}
		csvWriter.flush();
		csvWriter.close();
	}
}
