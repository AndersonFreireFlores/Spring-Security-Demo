package andersonfflores.springsecuritydemo.Controllers;

import andersonfflores.springsecuritydemo.Services.ProductService;
import andersonfflores.springsecuritydemo.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("/produto")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllprodutos(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProdutoById(@PathVariable UUID id){
        return productService.getProductById(id);
    }

    @PostMapping
    public Product createProduto(@RequestBody Product product){
        return productService.postProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduto(@PathVariable UUID id, @RequestBody Product product){
        return productService.putProduct(product, id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(UUID id){
        productService.deleteById(id);
    }
}
