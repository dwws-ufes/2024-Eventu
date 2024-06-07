package br.ufes.inf.eventu.app.services.interfaces;

public interface GenericService<T> {

    T save(T model) throws Exception;
    T edit(long id, T model) throws Exception;
    void delete(long id) throws Exception;
}
