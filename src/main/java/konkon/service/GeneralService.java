package konkon.service;

import java.util.List;

public interface GeneralService<E> {
  List<E> findAll();
  E findById(Long id);
  E findByName(String name);
  void save(E e);
  void delete(Long id);
}
