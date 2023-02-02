package uic.bffweb.assignament;

import java.sql.Date;

import lombok.Data;

@Data
public class AssignamentDTO {
    
    private Long id;
    private Long nombre;
    private String razon_social;
    private int orden;
    private Date fechaInicio;
    private Date fechaFin;
    private String planning;
  
}

