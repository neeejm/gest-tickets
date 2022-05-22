package emsi.ssii.devoir.mvccontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController {
    @RequestMapping("/")
	public String index(Model model) {
        model.addAttribute("msg", "Hello World");
		return "index";
	}

    @RequestMapping("/tickets")
	public String ticketsList(Model model) {
		return "tickets";
	}

    @RequestMapping("/addticket")
	public String addTicket(Model model) {
		return "addTicket";
	}
}
