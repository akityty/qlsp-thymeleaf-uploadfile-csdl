package konkon.repository.impl;

import konkon.model.Product;
import konkon.repository.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
public class ProductRepositoryImpl implements ProductRepository {
  @PersistenceContext
  private EntityManager em;
 /* private static Map<Integer, Product> products;

  static {
    products = new HashMap<>();
    products.put(1, new Product(1, "Iphone", 1000, "3 camera", "apple", "1.jpeg"));
    products.put(2, new Product(2, "Bphone", 1000, "3 camera", "apple", "2.jpeg"));
  }
*/
  @Override
  public List<Product> findAll() {
    TypedQuery<Product> query = em.createQuery("select c from Product c", Product.class);
    return query.getResultList();
  }

  @Override
  public Product findById(Long id) {
    TypedQuery<Product> query = em.createQuery("select p from Product p where p.id=:id", Product.class);
    query.setParameter("id", id);
    try {
      return query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }


  @Override
  public Product findByName(String name) {
  /*  for (int i = 1; i <= products.size(); i++) {
      if (products.get(i).getName().equals(name)) {
        return products.get(i);
      }
    }
    return null;*/
    TypedQuery<Product> query = em.createQuery("select p from Product p where p.name=:name", Product.class);
    query.setParameter("name", name);
    try {
     return query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  @Override
  public void save(Product product) {
   if(product.getId()!=null){
     em.merge(product);
   }else{
     em.persist(product);
   }
  }

  @Override
  public void delete(Long id) {
    Product product = findById(id);
    if(product!=null){
      em.remove(product);
    }
  }

}
