package konkon.model;

import javax.persistence.*;

@Entity
@Table(name="customers")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private int price;
  private String description;
  private String supplier;
  private String picture;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getSupplier() {
    return supplier;
  }

  public void setSupplier(String supplier) {
    this.supplier = supplier;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public Product(Long id, String name, int price, String description, String supplier, String picture) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.description = description;
    this.supplier = supplier;
    this.picture = picture;
  }

  public Product() {
  }
}
