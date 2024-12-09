package com.runtrack.repository;

import com.runtrack.entity.Product;
import org.springframework.data.jdbc.repository.query.Query;
//import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, String> {

    @Query("SELECT * FROM Product WHERE LOWER(ProductName) LIKE CONCAT('%', LOWER(:name), '%')")
    List<Product> findByProductNameContainingIgnoreCase(@Param("name") String name);

    @Query("SELECT * FROM Product WHERE ProductPrice <= :maxPrice")
    List<Product> findByProductPriceLessThanEqual(@Param("maxPrice") double maxPrice);

    @Query("SELECT * FROM Product WHERE ProductPrice BETWEEN :minPrice AND :maxPrice")
    List<Product> findByProductPriceBetween(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

    @Query("SELECT * FROM Product " +
            "WHERE LOWER(ProductName) LIKE CONCAT('%', LOWER(:name), '%') " +
            "AND ProductPrice BETWEEN :minPrice AND :maxPrice")
    List<Product> findByProductNameContainingIgnoreCaseAndProductPriceBetween(
            @Param("name") String name,
            @Param("minPrice") double minPrice,
            @Param("maxPrice") double maxPrice);

    @Query("SELECT * FROM Product " +
            "WHERE LOWER(ProductName) LIKE CONCAT('%', LOWER(:name), '%') " +
            "AND ProductPrice >= :minPrice")
    List<Product> findByProductNameContainingIgnoreCaseAndProductPriceGreaterThanEqual(
            @Param("name") String name,
            @Param("minPrice") double minPrice);

    @Query("SELECT * FROM Product " +
            "WHERE LOWER(ProductName) LIKE CONCAT('%', LOWER(:name), '%') " +
            "AND ProductPrice <= :maxPrice")
    List<Product> findByProductNameContainingIgnoreCaseAndProductPriceLessThanEqual(
            @Param("name") String name,
            @Param("maxPrice") double maxPrice);

    @Query("SELECT * FROM Product WHERE ProductPrice >= :minPrice")
    List<Product> findByProductPriceGreaterThanEqual(@Param("minPrice") double minPrice);
}
