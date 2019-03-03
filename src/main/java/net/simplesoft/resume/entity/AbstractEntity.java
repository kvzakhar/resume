package net.simplesoft.resume.entity;

import java.io.Serializable;

import net.simplesoft.resume.model.AbstractModel;

public abstract class AbstractEntity<T> extends AbstractModel implements Serializable {

	private static final long serialVersionUID = 8013322728157539017L;
	
	public abstract T getId();
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AbstractEntity<T> other = (AbstractEntity<T>) obj;
		if (getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!getId().equals(other.getId())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s[id=%s]", getClass().getSimpleName(),  getId());
	}

}
