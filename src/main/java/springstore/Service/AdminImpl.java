package springstore.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import springstore.Model.Product;
import springstore.Repository.ProductRepository;
import springstore.Service.ServiceInterface.AdminService;
import springstore.utils.FileUpload;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class AdminImpl implements AdminService {
    private final ProductRepository productRepository;
    @Autowired

    public AdminImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> showAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void addNewProduct(MultipartFile multipartFile, Product product) throws IOException {
        String imageFileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        String uploadDir = "src/main/resources/static/newimages";
        FileUpload.saveFile(uploadDir,imageFileName,multipartFile);
        product.setProductImage(imageFileName);
        Product products= productRepository.findProductByProductNameAndProductCategory(product.getProductName(),product.getProductCategory());
        if(products != null){
            products.setProductQuantity(product.getProductQuantity());
            productRepository.save(product);
        }
        else {
            productRepository.save(product);
        }
    }

    @Override
    public void deleteProduct(int proId) {
        productRepository.deleteById(proId);
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.getProductsByProdId(id);
    }

    @Override
    public void editProduct(int proId, Product product) {
        Product tmpProduct = productRepository.getProductsByProdId(proId);
        tmpProduct.setProductCategory(product.getProductCategory());
        tmpProduct.setProductPrice(product.getProductPrice());
        tmpProduct.setProductName(product.getProductName());
        tmpProduct.setProductQuantity(product.getProductQuantity());
        productRepository.save(tmpProduct);
    }

}
