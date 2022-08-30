package springstore.Service.ServiceInterface;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import springstore.Model.Product;

import java.io.IOException;
import java.util.List;


public interface AdminService {
    void addNewProduct(MultipartFile multipartFile, Product product) throws IOException;

    void deleteProduct(int prodId);
    List<Product> showAllProducts();
    Product getProductById(int id);

    void editProduct(int proId, Product product);
}
