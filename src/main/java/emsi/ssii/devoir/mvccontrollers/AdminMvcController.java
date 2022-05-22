package emsi.ssii.devoir.mvccontrollers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import emsi.ssii.devoir.models.Admin;
import emsi.ssii.devoir.models.Software;
import emsi.ssii.devoir.models.Ticket;
import emsi.ssii.devoir.services.SoftwareService;
import emsi.ssii.devoir.services.TicketService;
import emsi.ssii.devoir.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminMvcController {
    @Autowired
    private UserService<Admin> userService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private SoftwareService softwareService;

    @GetMapping("/tickets")
    public ModelAndView getTickets(@RequestParam(name = "assigned", required = false) Optional<Boolean> assigned) {
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
        return mv;
    }

    @GetMapping("/add")
	public String add(Model model) {
        Ticket ticket = new Ticket();
		// model.addAttribute("ticket", ticket);
		return "addTicket";

	}

    @PostMapping("/tickets/add")
    public String enregistrer(@ModelAttribute("ticket") Ticket ticket) {
        ticketService.add(ticket);
		return "redirect:admin/tickets";
	}

    // @PutMapping("/tickets/{ticket_id}/assignTo/{dev_id}")
    // public ResponseEntity<Ticket> assignTicketToDev(@PathVariable("ticket_id") int ticket_id, @PathVariable("dev_id") int dev_id) {
    //     return ResponseEntity.ok(ticketService.assignToDev(ticket_id, dev_id));
    // }

    // @GetMapping("/apps")
    // public ResponseEntity<List<Software>> getAllApps() {
    //     return ResponseEntity.ok(softwareService.findAll());
    // }

    // @PostMapping("/apps")
    // public ResponseEntity<Software> addApp(@RequestBody Software software) {
    //     return ResponseEntity.status(HttpStatus.CREATED).body(softwareService.add(software));
    // }
}
