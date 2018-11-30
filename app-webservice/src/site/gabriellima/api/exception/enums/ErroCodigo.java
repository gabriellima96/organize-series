package site.gabriellima.api.exception.enums;

public enum ErroCodigo {
	BAD_REQUEST(400),
    NOT_FOUND(404),
    SERVER_ERROR(500);
 
    private int codigo;
     
    ErroCodigo(int codigo) {
        this.codigo = codigo;
    }
     
    public int getCodigo() {
        return codigo;
    }
}
