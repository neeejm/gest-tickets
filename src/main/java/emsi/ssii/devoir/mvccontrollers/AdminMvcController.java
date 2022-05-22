package emsi.ssii.devoir.mvccontrollers;

import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import emsi.ssii.devoir.models.Admin;
import emsi.ssii.devoir.models.Software;
import emsi.ssii.devoir.models.User;
import emsi.ssii.devoir.services.SoftwareService;
import emsi.ssii.devoir.services.TicketService;
import emsi.ssii.devoir.services.UserService;
import emsi.ssii.devoir.utils.Constants;

@Controller
@RequestMapping("admin")
public class AdminMvcController {
    @Autowired
    private UserService<User> userService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private SoftwareService softwareService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/tickets")
    public ModelAndView getTickets(@RequestParam(name = "assigned", required = false) Optional<Boolean> assigned, Authentication auth) {
        ModelAndView mv = new ModelAndView("tickets");
        if (assigned.isPresent()) {
            if (!assigned.get()) {
                mv.addObject("tickets", ticketService.findAll().stream().filter(ticket -> ticket.getDev() == null).collect(Collectors.toList()));
            }
            if (assigned.get()) {
                mv.addObject("tickets", ticketService.findAll().stream().filter(ticket -> ticket.getDev() != null).collect(Collectors.toList()));
            }
        } else {
            mv.addObject("tickets", ticketService.findAll());
        }
        User user = userService.findByEmail(auth.getName());
        mv.addObject("admin", user.getRole().equals(Constants.getRole("admin")));
        mv.addObject("name", user.getDisplayName());
        return mv;
    }

    @GetMapping("/apps")
    public ModelAndView getApps(Authentication auth) {
        ModelAndView mv = new ModelAndView("apps");
        mv.addObject("apps", softwareService.findAll());
        User user = userService.findByEmail(auth.getName());
        mv.addObject("admin", user.getRole().equals(Constants.getRole("admin")));
        mv.addObject("name", user.getDisplayName());
        return mv;
    }

    @GetMapping("/apps/add")
	public String addApp(Model model, Authentication auth) {
        Software app = new Software();
		model.addAttribute("app", app);
        User user = userService.findByEmail(auth.getName());
        model.addAttribute("admin", user.getRole().equals(Constants.getRole("admin")));
        model.addAttribute("name", user.getDisplayName());
		return "addApp";

	}

    @PostMapping("/apps/add")
    public String saveApp(@ModelAttribute("app") Software app) {
        try {
            softwareService.add(app);
            logger.info("App added");
            return "redirect:/admin/apps";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "redirect:/admin/apps/add";
        }
	}

    @GetMapping("/affect")
	public String affectTicketToDev(Model model, Authentication auth) {
		model.addAttribute("tickets", ticketService.findAll().stream().filter(ticket -> ticket.getDev() == null).collect(Collectors.toList()));
		model.addAttribute("devs", userService.findAll().stream().filter(user -> user.getRole().equals(Constants.getRole("dev"))).collect(Collectors.toList()));

        User user = userService.findByEmail(auth.getName());
        model.addAttribute("admin", user.getRole().equals(Constants.getRole("admin")));
        model.addAttribute("name", user.getDisplayName());
		return "affectTicketToDev";

	}

    @PostMapping("/affect")
    public String assignTicketToDev(@RequestParam("ticketId") int ticket_id, @RequestParam("devId") int dev_id) {
       ticketService.assignToDev(ticket_id, dev_id);
       return "redirect:/admin/tickets";
    }
}
