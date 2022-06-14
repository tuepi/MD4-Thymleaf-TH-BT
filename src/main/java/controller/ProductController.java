package controller;

import model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import serivce.IProductService;
import serivce.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final IProductService productService = new ProductService();

    @GetMapping
    public ModelAndView showList() {
        List<Product> products = productService.findAll();
        return new ModelAndView("/product/list", "products", products);
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate(Product product) {
        return new ModelAndView("/product/create", "product", product);
    }

    @PostMapping("/save")
    public ModelAndView save(Product product) {
        productService.save(product);
        return new ModelAndView("redirect:/products");
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showFormEdit(@PathVariable int id) {
        return new ModelAndView("/product/edit", "product", productService.findById(id));
    }

    @PostMapping("/update")
    public ModelAndView update(Product product) {
        productService.update(product.getId(), product);
        return new ModelAndView("redirect:/products");
    }

    @GetMapping("/{id}/delete")
    public ModelAndView showFormDelete(@PathVariable int id) {
        return new ModelAndView("/product/delete", "product", productService.findById(id));
    }

    @PostMapping("/delete")
    public ModelAndView delete(@RequestParam int id, RedirectAttributes redirectAttributes) {
        productService.remove(id);
        redirectAttributes.addFlashAttribute("success", "Xóa thành công");
        return new ModelAndView("redirect:/products");
    }
}
