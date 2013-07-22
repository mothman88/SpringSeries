package net.mothman.persistence.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Child implements Serializable {

	private static final long serialVersionUID = -7133803764915811559L;

	@Id
    @GeneratedValue
    private long id;

    @OneToOne(mappedBy = "child")
    private Parent parent;

    public Child() {}

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(final Parent parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Child [id=").append(id).append("]");
        return builder.toString();
    }

}
