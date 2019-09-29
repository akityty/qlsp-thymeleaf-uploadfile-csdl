package konkon.controller;

import konkon.model.Product;
import konkon.model.ProductForm;
import konkon.service.ProductService;
import konkon.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

  @Autowired
  private ProductService productService;

  @RequestMapping(value = "/list*", method = RequestMethod.GET)
  public ModelAndView listCustomer() {
    ModelAndView modelAndView = new ModelAndView("/product/list");
    List<Product> products = productService.findAll();
    modelAndView.addObject("products", products);
    return modelAndView;
  }
  @PostMapping("/list")
  public ModelAndView saveCustomer(@ModelAttribute ProductForm product, BindingResult result){
    String fileName =  productService.uploadFile(product,result);
    // tao doi tuong de luu vao db
    Product customerObject = new Product(product.getId(),product.getName(), product.getPrice(), product.getDescription(),product.getSupplier(), fileName);

    // luu vao db
    //productService.save(productObject);
    productService.save(customerObject);

    ModelAndView modelAndView = new ModelAndView("/product/list");
    List<Product> customerList = productService.findAll();
    modelAndView.addObject("customers", customerList);
    return modelAndView;
  }
  @GetMapping("/create")
  public ModelAndView showCreateForm(){
    ModelAndView modelAndView = new ModelAndView("/product/create");
    modelAndView.addObject("product", new ProductForm());
    return modelAndView;
  }
  @PostMapping("/create")
  public ModelAndView createProduct(@ModelAttribute ProductForm productForm, BindingResult result){
    String fileName =  productService.uploadFile(productForm,result);
    Product product = new  Product(productForm.getId(),productForm.getName(), productForm.getPrice(), productForm.getDescription(),productForm.getSupplier(), fileName);
    productService.save(product);
    ModelAndView modelAndView = new ModelAndView("redirect:/product/list");
    modelAndView.addObject("product", product);
    return modelAndView;
  }
  @GetMapping("/edit")
  public ModelAndView showEditForm(@ModelAttribute Product product){
    ModelAndView modelAndView = new ModelAndView("/product/edit");
    Product productObject =  productService.findById(product.getId());
    modelAndView.addObject("product", productObject);
    return modelAndView;
  }

  @PostMapping("/edit")
  public ModelAndView editProduct(@ModelAttribute ProductForm productForm, BindingResult result){
    String fileName =  productService.uploadFile(productForm,result);
    Product productObject = productService.findById(productForm.getId());
    if(!fileName.equals("")){
      productObject.setPicture(fileName);
    }
    productObject.setId(productForm.getId());
    productObject.setName(productForm.getName());
    productObject.setPrice(productForm.getPrice());
    productObject.setDescription(productForm.getDescription());
    productObject.setSupplier(productForm.getSupplier());
    productService.save(productObject);
    ModelAndView modelAndView = new ModelAndView("redirect:/product/list");
    modelAndView.addObject("product", productObject);
    return modelAndView;
  }
  @GetMapping("/delete")
  public ModelAndView showDeleteForm(@ModelAttribute Product product){
    ModelAndView modelAndView = new ModelAndView("/product/delete");
    Product productObject = productService.findById(product.getId());
    modelAndView.addObject("product", productObject);
    return modelAndView;
  }

  @PostMapping("/delete")
  public ModelAndView deleteProduct(@ModelAttribute ProductForm productForm){
    Product productObject = productService.findById(productForm.getId());
    productService.delete(productObject.getId());
    ModelAndView modelAndView = new ModelAndView("redirect:/product/list");
    modelAndView.addObject("product", productObject);
    return modelAndView;
  }
  @PostMapping("/search")
  public ModelAndView search(@RequestParam String name){
   Product productObject = productService.findByName(name);
   if(productObject!=null){
     ModelAndView modelAndView = new ModelAndView(("/product/view"));
   modelAndView.addObject("product",productObject);
   return modelAndView;
   }else{
     ModelAndView modelAndView = new ModelAndView("/product/list");
     List<Product> products = productService.findAll();
     modelAndView.addObject("products", products);
     return modelAndView;
   }
  }
  @GetMapping("/view")
  public ModelAndView showViewForm(@ModelAttribute Product product){
    ModelAndView modelAndView = new ModelAndView("/product/view");
    Product productObject = productService.findById(product.getId());
    modelAndView.addObject("product", productObject);
    return modelAndView;
  }
}
