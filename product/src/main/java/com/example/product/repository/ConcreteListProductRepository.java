package com.example.product.repository;

import com.example.product.model.Product;
import com.example.product.model.dto.ListProductCriteriaDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@RequiredArgsConstructor
public class ConcreteListProductRepository implements ListProductRepository{
    private final EntityManager entityManager;
    @Override
    public List<Product> findProductsByCriteria(ListProductCriteriaDto listProductCriteriaDto,List<Long> storeIds,int size) {
        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery=criteriaBuilder.createQuery(Product.class);
        Root<Product> product=criteriaQuery.from(Product.class);

        List<Predicate> predicates=new ArrayList<>();
        if (product.get("productName")!=null && !listProductCriteriaDto.getProductName().trim().isEmpty()){
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(product.get("productName")),"%"+ listProductCriteriaDto.getProductName().toLowerCase()+"%"));
        }
        if (product.get("brand")!=null && !listProductCriteriaDto.getBrand().trim().isEmpty()){
            predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(product.get("brand")),listProductCriteriaDto.getBrand().toLowerCase()));
        }
        if (product.get("category")!=null && !listProductCriteriaDto.getCategory().trim().isEmpty()){
            predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(product.get("productCategory").get("categoryName")),listProductCriteriaDto.getCategory().toLowerCase()));
        }
        if (product.get("price")!=null && listProductCriteriaDto.getMaxPrice()!=null &&listProductCriteriaDto.getMinPrice()!=null){
            predicates.add(criteriaBuilder.between(product.get("price"),listProductCriteriaDto.getMinPrice(),listProductCriteriaDto.getMaxPrice()));
        }
        if(storeIds!=null && !storeIds.isEmpty()){
            predicates.add(product.get("stock").get("storeId").in(storeIds));
        }
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        criteriaQuery.orderBy(criteriaBuilder.asc(product.get("productName")));
        TypedQuery<Product> query = entityManager.createQuery(criteriaQuery);
        query.setMaxResults(size);
        return query.getResultList();
    }
}
