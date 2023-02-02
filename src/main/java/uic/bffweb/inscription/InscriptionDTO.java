package uic.bffweb.inscription;


import lombok.Data;

@Data
public class InscriptionDTO {
    
    private Long id;
    private String titulo;
    private String tipo_solicitud;
    private String observacion;
    private boolean estado;
    private byte[] documento;
  
}