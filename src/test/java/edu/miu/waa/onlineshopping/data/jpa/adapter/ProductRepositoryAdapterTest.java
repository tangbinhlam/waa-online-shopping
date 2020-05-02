package edu.miu.waa.onlineshopping.data.jpa.adapter;

import edu.miu.waa.onlineshopping.domain.model.Product;
import edu.miu.waa.onlineshopping.domain.model.User;
import edu.miu.waa.onlineshopping.domain.vo.Role;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
class ProductRepositoryAdapterTest {

    @Autowired
    private  ProductRepositoryAdapter productRepositoryAdapter;

    @Autowired
    private  UserRepositoryAdapter userRepositoryAdapter;

    @Test
    void save() {
        User user = User.of(1, "Lamtangxx", "Lamtangxx", "Lam", "Tang", true, "lamtang@yahoo.com", "0978548677",
                Role.SELLER, "I'm a admin I manager to approve new seller and review the review are made by buyer"

        );
        User userSaved = userRepositoryAdapter.save(user);
        System.out.println(userSaved.toString());
        Product product = Product.of(500, "Lenspen", "abc/len.jpg", "Will not scratch or damages lenses", userSaved, 500.5, "Nikon");

        // Test save product
        Product productSaved = productRepositoryAdapter.save(product);
        System.out.println(productSaved);
        assertThat(productSaved).isNotNull();
        assertThat(productSaved.getProductName()).isEqualTo("Lenspen");
        assertThat(productSaved.getImagePath()).isEqualTo("abc/len.jpg");
        assertThat(productSaved.getDescription()).isEqualTo("Will not scratch or damages lenses");
        assertThat(productSaved.getPrice() == 500.5).isTrue();
        assertThat(productSaved.getSupplier().getAboutUs()).isEqualTo(userSaved.getAboutUs());

        // Test find product
        Product productFind = productRepositoryAdapter.findProductByProductId(productSaved.getProductId());
        System.out.println(productFind);
        assertThat(productFind).isNotNull();
        assertThat(productFind.getProductName()).isEqualTo("Lenspen");
        assertThat(productFind.getImagePath()).isEqualTo("abc/len.jpg");
        assertThat(productFind.getDescription()).isEqualTo("Will not scratch or damages lenses");
        assertThat(productFind.getPrice() == 500.5).isTrue();
        assertThat(productFind.getSupplier().getAboutUs()).isEqualTo(userSaved.getAboutUs());

        //Test Update
        Product productUpdated = productRepositoryAdapter.update(Product.of(productSaved.getProductId(), "Updated product",
                "newPathImage", "Updated-Descrpiton", userSaved, 55.20, "Nikon new"));
        System.out.println(productUpdated);
        assertThat(productUpdated).isNotNull();
        assertThat(productUpdated.getProductName()).isEqualTo("Updated product");
        assertThat(productUpdated.getImagePath()).isEqualTo("newPathImage");
        assertThat(productUpdated.getDescription()).isEqualTo("Updated-Descrpiton");
       // assertThat(productUpdated.getProducer()).isEqualTo("Nikon new");
        assertThat(productUpdated.getPrice()).isEqualTo(55.20);
        assertThat(productUpdated.getSupplier().getAboutUs()).isEqualTo(userSaved.getAboutUs());

        //Test Find by findAllBySupplier
        List<Product> products = productRepositoryAdapter.findAllBySupplier(userSaved.getUserId());
        assertThat(products).isNotEmpty();
        assertThat(products.size()).isEqualTo(1);
        assertThat(products.get(0).getPrice()).isEqualTo(55.20);

        List<Product> productsNotExits = productRepositoryAdapter.findAllBySupplier(4444);
        assertThat(productsNotExits).isEmpty();

        //Delete
        boolean result = productRepositoryAdapter.delete(productUpdated.getProductId());
        Product productDelete = productRepositoryAdapter.findProductByProductId(productUpdated.getProductId());
        assertThat(productDelete).isNull();
    }

}
