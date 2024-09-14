package com.randomdev.firstapi.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.randomdev.firstapi.apirest.Entities.Productos; 

public interface ProductosRepository extends JpaRepository<Productos, Long>{


}
