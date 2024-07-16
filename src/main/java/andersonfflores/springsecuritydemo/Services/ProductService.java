package andersonfflores.springsecuritydemo.Services;

import andersonfflores.springsecuritydemo.Repositories.ProductRepository;
import andersonfflores.springsecuritydemo.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(UUID id){
        return productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found")
        );
    }

    public Product postProduct(Product product){
        return productRepository.save(product);
    }

    public Product putProduct(Product newProduct,UUID id ){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found")
        );
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        return productRepository.save(product);
    }

    public void deleteById(UUID id){
        productRepository.deleteById(id);
    }

}
