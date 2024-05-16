package controlador;

import java.util.List;

public abstract class AbstractCrud<T, ID> {
    public abstract List<T> executeReadAll();
    public abstract T executeCreate(T entidad);
    public abstract T executeRead(ID id);
    public abstract boolean  executeUpdate(ID id);
    public abstract void executeDelete(ID id);
}
