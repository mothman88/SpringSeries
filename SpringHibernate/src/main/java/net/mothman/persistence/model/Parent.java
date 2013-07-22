package net.mothman.persistence.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Parent implements Serializable {

	private static final long serialVersionUID = -3692104633307857919L;

	@Id
    @GeneratedValue
    private long id;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
    @JoinColumn(name = "child_fk")
    private Child child;

    public Parent() {}

    public Parent(final Child child) {
        this.child = child;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(final Child child) {
        this.child = child;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Parent [id=").append(id).append("]");
        return builder.toString();
    }

}
