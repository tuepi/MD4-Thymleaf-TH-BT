package controller;

import model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import serivce.CustomerService;
import serivce.ICustomerService;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final ICustomerService customerService = new CustomerService();

    @GetMapping
    public ModelAndView index() {
        List<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("/index", "customers", customers);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("/create", "customer", new Customer());
    }

    @PostMapping("/save")
    public ModelAndView save(Customer customer) {
        customer.setId((int) (Math.random() * 10000));
        customerService.save(customer);
        return new ModelAndView("redirect:/customers");
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showFormEdit(@PathVariable int id) {
        return new ModelAndView("/edit", "customer", customerService.findById(id));
    }

    @PostMapping("/update")
    public ModelAndView update(Customer customer) {
        customerService.update(customer.getId(), customer);
        return new ModelAndView("redirect:/customers");
    }

    @GetMapping("/{id}/delete")
    public ModelAndView showFormDelete(@PathVariable int id) {
        return new ModelAndView("/delete", "customer", customerService.findById(id));
    }

    @PostMapping("/delete")
    public ModelAndView delete(@RequestParam int id, RedirectAttributes redirectAttributes) {
        customerService.remove(id);
        redirectAttributes.addFlashAttribute("success", "Removed customer successfully!");
        return new ModelAndView("redirect:/customers");
    }

    @GetMapping("/{id}/view")
    public ModelAndView view(@PathVariable int id) {
        return new ModelAndView("/view", "customer", customerService.findById(id));
    }
}
