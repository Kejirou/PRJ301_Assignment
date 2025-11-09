package model;

import java.util.Objects;

/**
 * Base model class for all entities.
 * Provides a shared ID field and common utility methods.
 */
public abstract class BaseModel {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id=" + id + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseModel that = (BaseModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
